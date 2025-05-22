from tensorflow.keras.optimizers import Adam
from tensorflow.keras.layers import Dense, Dropout
from tensorflow.keras.models import Sequential
import tensorflow as tf
from sklearn.metrics import auc, roc_curve, roc_auc_score, classification_report, precision_recall_curve, f1_score
import json
import os
import numpy as np
from sklearn.feature_extraction import DictVectorizer
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt


def read_corpus(file_path):
    sentences = {}
    with open(file_path, 'r', encoding='gb18030') as file:
        for line in file:
            if line.strip():
                parts = line.strip().split()
                sentence_id = parts[0].rstrip('/m')
                words = parts[1:]
                sentences[sentence_id] = words
    return sentences


def load_json_data(folder_path):
    data = []
    for filename in os.listdir(folder_path):
        file_path = os.path.join(folder_path, filename)
        if os.path.isfile(file_path):
            with open(file_path, 'r', encoding='gb18030') as file:
                try:
                    json_data = json.load(file)
                    if json_data is not None:
                        data.append(json_data)
                except json.JSONDecodeError as e:
                    print(f"Error decoding JSON from {filename}: {e}")
    return data


def extract_features_and_convert(corpus, json_data, vec=None):
    features = []
    labels = []
    for item in json_data:
        if item is None or 'pronoun' not in item or item['pronoun'] is None:
            continue
        sentence_id = item['pronoun']['id']
        if sentence_id not in corpus:
            continue
        pronoun_index = item['pronoun']['indexFront']
        sentence = corpus[sentence_id]
        for key in item:
            if key.isdigit():
                antecedent = item[key]
                ant_start = antecedent['indexFront']
                ant_end = antecedent['indexBehind']
                for i in range(len(sentence)):
                    word_base, pos_tag = sentence[i].split('/')
                    feature_vector = {
                        'distance': abs(pronoun_index - i),
                        'candidate_pos': pos_tag,
                        'is_pronoun': int(pos_tag == 'rr')
                    }
                    features.append(feature_vector)
                    labels.append(int(ant_start <= i <= ant_end))
    if vec is None:
        vec = DictVectorizer(sparse=False)
        features = vec.fit_transform(features)
    else:
        features = vec.transform(features)
    return features, np.array(labels), vec


def create_neural_network(input_dim, learning_rate=0.0001):
    model = Sequential([
        Dense(16, activation='relu', input_dim=input_dim),
        Dropout(0.3),
        Dense(8, activation='relu'),
        Dropout(0.3),
        Dense(1, activation='sigmoid')
    ])

    model.compile(optimizer=Adam(learning_rate=learning_rate),
                  loss='binary_crossentropy',
                  metrics=['accuracy'])
    return model


# 主流程
corpus = read_corpus('1998-01-2003.txt')
training_json = load_json_data('train')
features, labels, vec = extract_features_and_convert(corpus, training_json)

# Convert features and labels to numpy arrays
X = np.array(features)
y = np.array(labels)

# Split data into training and validation sets
X_train, X_val, y_train, y_val = train_test_split(
    X, y, test_size=0.2, random_state=42)

# 训练轮数列表
epochs_list = [1, 2, 3, 5, 10]

# 初始化画图
plt.figure(figsize=(12, 8))

# 记录不同训练轮数下的ROC AUC
for epochs in epochs_list:
    model = create_neural_network(X_train.shape[1], learning_rate=0.0001)
    print(f"Training model with {epochs} epochs...")
    model.fit(X_train, y_train, epochs=epochs, batch_size=32,
              validation_data=(X_val, y_val), verbose=1)

    # Predict probabilities on validation set
    probabilities = model.predict(X_val)

    # Compute ROC curve and ROC AUC
    fpr, tpr, _ = roc_curve(y_val, probabilities)
    roc_auc = auc(fpr, tpr)

    # 绘制ROC曲线
    plt.plot(fpr, tpr, lw=2, label=f'Epochs: {epochs} (AUC = {roc_auc:.2f})')

# 绘制随机猜测的基线
plt.plot([0, 1], [0, 1], color='navy', lw=2, linestyle='--')

# 图形细节
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('ROC Curve for Different Epochs')
plt.legend(loc='lower right')
plt.show()
