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

def create_neural_network(input_dim):
    model = Sequential([
        Dense(16, activation='relu', input_dim=input_dim),
        Dropout(0.3),
        Dense(8, activation='relu'),
        Dropout(0.3),
        Dense(1, activation='sigmoid')
    ])

    model.compile(optimizer=Adam(learning_rate=0.0001),
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

# 创建和训练神经网络
model = create_neural_network(X_train.shape[1])
model.fit(X_train, y_train, epochs=10, batch_size=32, validation_data=(X_val, y_val), verbose=1)

# 预测验证集的概率
probabilities = model.predict(X_val)

# 计算PR曲线
precision, recall, _ = precision_recall_curve(y_val, probabilities)

# 计算AUC值
pr_auc = auc(recall, precision)

# 绘制PR曲线
plt.figure(figsize=(12, 8))
plt.plot(recall, precision, lw=2, label=f'PR curve (AUC = {pr_auc:.2f})')
plt.xlabel('Recall')
plt.ylabel('Precision')
plt.title('Precision-Recall Curve')
plt.legend(loc='lower left')
plt.grid(True)
plt.show()
