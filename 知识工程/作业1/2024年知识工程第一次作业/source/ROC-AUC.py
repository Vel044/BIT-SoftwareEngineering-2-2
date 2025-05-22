from sklearn.metrics import auc
from sklearn.metrics import roc_curve, roc_auc_score
import matplotlib.pyplot as plt
import json
import os
import numpy as np
from sklearn.feature_extraction import DictVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report, precision_recall_curve


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


def train_glm(features, labels):
    X_train, X_test, y_train, y_test = train_test_split(
        features, labels, test_size=0.2, random_state=42)
    model = LogisticRegression(
        solver='saga', class_weight='balanced', max_iter=100, tol=1e-4, verbose=1)

    model.fit(X_train, y_train)
    y_pred = model.predict(X_test)
    print(classification_report(y_test, y_pred))
    # Return X_train, y_train, X_test, and y_test
    return model, X_train, y_train, X_test, y_test


# 主流程
corpus = read_corpus('1998-01-2003.txt')
training_json = load_json_data('train')
features, labels, vec = extract_features_and_convert(corpus, training_json)
# Adjusted to receive X_train, y_train, X_test, and y_test
model, X_train, y_train, X_test, y_test = train_glm(features, labels)


# 此处逻辑应放置于能访问X_test和y_test的地方
probabilities = model.predict_proba(X_test)[:, 1]  # 使用模型进行概率预测
precision, recall, thresholds = precision_recall_curve(
    y_test, probabilities)  # 计算精确度-召回率曲线
idx = np.where(recall >= 0.6)[0][-1]  # 找到召回率阈值索引
selected_threshold = thresholds[idx]  # 选择阈值
predictions_adjusted = (
    probabilities >= selected_threshold).astype(int)  # 应用阈值
print(classification_report(y_test, predictions_adjusted))  # 输出调整后的分类报告


# 计算 ROC 曲线的各项参数
fpr, tpr, thresholds = roc_curve(y_test, probabilities)

# 计算 AUC
auc_score = roc_auc_score(y_test, probabilities)

# 绘制 ROC 曲线
plt.figure(figsize=(8, 6))
plt.plot(fpr, tpr, color='blue', lw=2,
         label='ROC curve (area = %0.2f)' % auc_score)
plt.plot([0, 1], [0, 1], color='gray', lw=1, linestyle='--')
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('Receiver Operating Characteristic (ROC) Curve')
plt.legend(loc='lower right')
plt.show()
