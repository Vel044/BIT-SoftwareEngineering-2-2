import pandas as pd
import requests
import numpy as np
from python_tsp.exact import solve_tsp_dynamic_programming


# 读取Excel文件中的POI数据
file_path = 'POI.xlsx'
df_poi = pd.read_excel(file_path)

# 定义一个函数，通过高德API获取两个点之间的距离和时间
def get_dis_tm(origin, destination):
    url = 'https://restapi.amap.com/v3/direction/driving?'
    key = '6a186a3cb0f3af7939876f5d9e3e32c7'  # 请将此处替换为您的高德API密钥
    link = f'{url}origin={origin}&destination={destination}&key={key}'
    response = requests.get(link)
    dis, tm = 999999, 999999
    if response.status_code == 200:
        results = response.json()
        if results['status'] == '1':
            dis = int(results['route']['paths'][0]['distance'])
            tm = int(results['route']['paths'][0]['duration'])
    return dis, tm

# 获取POI数量
n_pois = len(df_poi)

# 初始化时间矩阵
time_matrix = np.zeros((n_pois, n_pois))

# 填充时间矩阵
for i in range(n_pois):
    for j in range(n_pois):
        if i != j:
            origin = f"{df_poi.loc[i, 'WGS84_经度']},{df_poi.loc[i, 'WGS84_纬度']}"
            destination = f"{df_poi.loc[j, 'WGS84_经度']},{df_poi.loc[j, 'WGS84_纬度']}"
            _, time_matrix[i, j] = get_dis_tm(origin, destination)

# 求解TSP问题
permutation, distance = solve_tsp_dynamic_programming(time_matrix)

# 输出结果
print("最优路径 (permutation):", permutation)
print("总时间 (time):", distance)
