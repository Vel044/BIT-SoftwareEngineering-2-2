import json
import requests
from bs4 import BeautifulSoup
import urllib.parse

def get_baidu_baike_url(entity):
    # 对实体名称进行URL编码
    entity_encoded = urllib.parse.quote(entity)
    
    # 百度百科的搜索URL格式
    search_url = f"https://baike.baidu.com/item/{entity_encoded}"

    # 请求该URL
    response = requests.get(search_url)
    
    # 检查请求是否成功
    if response.status_code == 200:
        # 解析页面内容
        soup = BeautifulSoup(response.content, 'html.parser')
        
        # 检查是否存在错误页面的提示
        error_message = soup.find(text="抱歉，您所访问的页面不存在")
        if error_message:
            return None
        return search_url
    return None

# 读取JSON文件
def read_json_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        data = json.load(f)
    return data

# 将更新后的数据写回到JSON文件
def write_json_file(file_path, data):
    with open(file_path, 'w', encoding='utf-8') as f:
        json.dump(data, f, ensure_ascii=False, indent=4)

# 处理JSON文件中的实体并获取对应的百度百科URL
def process_entities(file_path):
    entities = read_json_file(file_path)
    
    for entity_data in entities:
        entity = entity_data['entity']
        url = get_baidu_baike_url(entity)
        if url:
            print(f"{entity}: {url}")
            entity_data['url'] = url  # 将URL添加到实体数据中
        else:
            print(f"{entity}: 没有找到相关百度百科页面")
            entity_data['url'] = None  # 表示未找到

    # 将更新后的数据写回文件
    write_json_file(file_path, entities)

# 示例使用
file_path = '1120221303.json'  # 您的JSON文件路径
process_entities(file_path)
