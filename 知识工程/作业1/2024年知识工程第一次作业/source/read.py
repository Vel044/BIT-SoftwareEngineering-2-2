import os
import re
import json


# 假设数据和文件结构已经定义
data_path = '1998-01-2003.txt'
train_path = 'train/'
validation_path = 'validation/'
test_path = 'test/'

# 储存语句
sentences = {}

# 加载文本数据
def load_corpus(path):
    try:
        with open(path, 'r', encoding='gb18030') as file:
            for line in file:
                line = line.strip()  # 去除两端的空白字符
                if not line:  # 如果行为空，跳过这一行
                    continue
                line = re.sub(r'/[a-z]+', '', line)  # 使用正则表达式去除所有的'/<字母>'标记
                parts = line.split(' ')
                sentence_id = parts[0]
                content = ' '.join(parts[1:])
                sentences[sentence_id] = content
                print(sentence_id)  # 打印句子ID以验证
                print(content)
    except UnicodeDecodeError as e:
        print(f"Decode error: {e}")


corpus = load_corpus(data_path)


# 加载JSON数据
def load_json_data(path):
    data = []
    for filename in os.listdir(path):
        with open(os.path.join(path, filename), 'r', encoding='gb18030') as file:
            data.append(json.load(file))
    return data


train_data = load_json_data(train_path)
validation_data = load_json_data(validation_path)
test_data = load_json_data(test_path)

