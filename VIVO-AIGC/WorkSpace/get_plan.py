from tsp_solver.greedy import solve_tsp
import traceback
import multiprocessing
import pyproj
import argparse
import numpy as np
from scipy.spatial import cKDTree
from shapely.geometry import Point, LineString
import pickle
import networkx as nx
import pandas as pd
import geopandas as gpd
import math
import requests

from fastapi.middleware.cors import CORSMiddleware



# 根据用户需求五元组、用户偏好以及初步筛选POI筛选得到最终POI
import requests
from urllib.parse import quote
import json


from fastapi import FastAPI
from pydantic import BaseModel

# 创建FastAPI实例
app = FastAPI()

# 允许所有域、所有方法（GET, POST等）、所有标头
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# 定义请求体模型
class InputData(BaseModel):
    id: str
    dialogId: str


def getFinalPOI(startPOI, endPOI, startTime, endTime, numPOI,userId):
    '''
    :param startPOI:用户需求五元组-起点
    :param endPOI:用户需求五元组-终点
    :param startTime:用户需求五元组-起始时间
    :param endTime:用户需求五元组-终止时间
    :param numPOI:用户需求五元组-途径点个数
    :param username:用户名
    :param password:用户密码
    :return:返回给用户推荐的numPOI个POI信息
    '''
    # 调用用户偏好获取接口获取用户的旅行偏好
    url = f"http://43.138.118.221:7778/preferences?id={userId}"
    payload = {}
    headers = {
        'User-Agent': 'Apifox/1.0.0 (https://apifox.com)'
    }
    response_pre = requests.request("GET", url, headers=headers, data=payload)
    # 检查请求是否成功
    if response_pre.status_code == 200:
        response_pre_json = response_pre.json()
        # 获取用户的旅行偏好
        interested_places = response_pre_json.get(
            'data', {}).get('interestedPlaces')
        interested_ways = response_pre_json.get(
            'data', {}).get('interestedWays')
        travel_companion = response_pre_json.get(
            'data', {}).get('travelCompanion')
    else:
        print(f"Request failed with status code: {response_pre.status_code}")

    # 由于用户偏好获取接口暂时返回的数据为null，可用以下代码进行测试
    '''
    interested_places = "自然风光，美食"
    interested_ways = "常规路线"
    travel_companion = "恋人"
    '''

    # 调用POI初步筛选接口获得初步POI候选集
    start_name = quote(startPOI)
    end_name = quote(endPOI)
    url = f"http://43.138.118.221:7778/position/filter?begin={start_name}&end={end_name}&num=23"
    payload = {}
    headers = {
        'User-Agent': 'Apifox/1.0.0 (https://apifox.com)',
        'Accept': '*/*',
        'Host': '43.138.118.221:7778',
        'Connection': 'keep-alive'
    }
    response_poi = requests.request("GET", url, headers=headers, data=payload)
    selected_poi = response_poi.json().get('data', {})
    # print(selected_poi)

    # 构建prompt，根据用户需求五元组、用户偏好以及候选POI得到最终推荐给用户的POI

    preference = "{感兴趣的旅行地点："+ str(interested_places) + "；感兴趣的旅行方式：" + str(interested_ways )+ "；旅行伙伴：" + str(travel_companion )+ "};"

    input = "1.出发地点：" + startPOI + "；2.出发时间：" + startTime + "；3.终止地点：" + endPOI + "；4.终止时间：" + \
        endTime + "；5.途径点个数：" + \
            str(numPOI) + "个；6.旅行偏好：" + preference + \
        "；7.可参考的地点信息为：" + str(selected_poi)
    prompt = """
        TASK：作为旅游行程规划师，假设你充分了解北京市的所有地点以及交通信息，且十分擅长于根据不同人群的需求安排合理的游玩地点和时间请根据给定信息推荐n个POI地点，构成一条顺畅且完整的旅行路线。
        REQUEST：规划一个旅游路线，包括POI地点序列。请根据以下信息规划路线：""" + input + """
        ACTION：
        1. 确定本次行程的大概活动范围，根据起终点的位置规划路线。
        2. 根据游客的偏好找到所有可能感兴趣的景点、美食、购物以及休闲娱乐场所等。
        3. 综合考虑行程起终时间、交通时间、地点之间是否顺路等因素，规划一条合理的路线。
        4. 安排就餐地点时尽量选择距离参观景点一公里以内的地点。
        5. 你只需要返回"""+str(numPOI)+"""个你认为最合适的地点即可，不要多于"""+str(numPOI)+"""个也不要少于"""+str(numPOI)+"""个。
        EXAMPLE：
        以下是一个推荐POI地点的示例，最终输出应为 JSON 格式文本：
        {
        "pois":[
            {
            "name": "[地点名称]",
            "longitude": [地点经度],
            "latitude": [地点维度]
            },
        ]
        }
        """
    encoded_prompt = quote(prompt)
    url = f"http://43.138.118.221:7778/vivo/gpt_discontinuous?input={encoded_prompt}&dialogId=2&userId=1"

    payload = {}
    headers = {
        'User-Agent': 'Apifox/1.0.0 (https://apifox.com)',
        'Accept': '*/*',
        'Host': '43.138.118.221:7778',
        'Connection': 'keep-alive'
    }
    response_finalpoi = requests.request(
        "POST", url, headers=headers, data=payload)
    poi_content = response_finalpoi.json()["data"]["output"]
    final_poi = eval(poi_content.strip())
    '''
    返回如下的POI集合(final_poi是一个dictionary)：
    {
    "pois": [
        {
            "name": "北京故宫博物院",
            "longitude": 116.403414,
            "latitude": 39.924091
        },
        {
            "name": "中国美术馆",
            "longitude": 116.48,
            "latitude": 39.56
        },
        {
            "name": "颐和园",
            "longitude": 116.443594,
            "latitude": 39.986244
        },
        {
            "name": "北京理工大学",
            "longitude": 116.09190503816461,
            "latitude": 39.72773209078721
        }
    ]
    }
    '''
    return final_poi


def get_travel_plan(input_text):

    url = f"http://43.138.118.221:7778/vivo/gpt_discontinuous?input={input_text}&dialogId=89&userId=2"

    payload = {}
    headers = {
        'User-Agent': 'Apifox/1.0.0 (https://apifox.com)',
        'Accept': '*/*',
        'Host': '43.138.118.221:7778',
        'Connection': 'keep-alive'
    }

    response = requests.request("POST", url, headers=headers, data=payload)
    print(response.text)

    response_data = response.json()

    if response_data.get("code") == 1:
        content = response_data["data"]["output"]
        print(content)

        # Convert the JSON content to a Python dictionary
        travel_plan = eval(content.strip())

        return travel_plan
    else:
        return None


def get_location(address):
    par = {'address': address, 'key': '6a186a3cb0f3af7939876f5d9e3e32c7'}
    url = 'http://restapi.amap.com/v3/geocode/geo'
    res = requests.get(url, par)
    json_data = json.loads(res.text)
    geo = json_data['geocodes'][0]['location']
    longitude = float(geo.split(',')[0])
    latitude = float(geo.split(',')[1])
    return longitude, latitude


x_pi = 3.14159265358979324 * 3000.0 / 180.0
pi = 3.1415926535897932384626  # π
a = 6378245.0  # 长半轴
ee = 0.00669342162296594323  # 偏心率平方



def _transformlat(lng, lat):
    ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + \
        0.1 * lng * lat + 0.2 * math.sqrt(math.fabs(lng))
    ret += (20.0 * math.sin(6.0 * lng * pi) + 20.0 *
            math.sin(2.0 * lng * pi)) * 2.0 / 3.0
    ret += (20.0 * math.sin(lat * pi) + 40.0 *
            math.sin(lat / 3.0 * pi)) * 2.0 / 3.0
    ret += (160.0 * math.sin(lat / 12.0 * pi) + 320 *
            math.sin(lat * pi / 30.0)) * 2.0 / 3.0
    return ret


def _transformlng(lng, lat):
    ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + \
        0.1 * lng * lat + 0.1 * math.sqrt(math.fabs(lng))
    ret += (20.0 * math.sin(6.0 * lng * pi) + 20.0 *
            math.sin(2.0 * lng * pi)) * 2.0 / 3.0
    ret += (20.0 * math.sin(lng * pi) + 40.0 *
            math.sin(lng / 3.0 * pi)) * 2.0 / 3.0
    ret += (150.0 * math.sin(lng / 12.0 * pi) + 300.0 *
            math.sin(lng / 30.0 * pi)) * 2.0 / 3.0
    return ret


def out_of_china(lng, lat):
    """
    判断是否在国内，不在国内不做偏移
    :param lng:
    :param lat:
    :return:
    """
    return not (lng > 73.66 and lng < 135.05 and lat > 3.86 and lat < 53.55)


def gcj02_to_wgs84(lng, lat):
    if out_of_china(lng, lat):
        return [lng, lat]
    dlat = _transformlat(lng - 105.0, lat - 35.0)
    dlng = _transformlng(lng - 105.0, lat - 35.0)
    radlat = lat / 180.0 * pi
    magic = math.sin(radlat)
    magic = 1 - ee * magic * magic
    sqrtmagic = math.sqrt(magic)
    dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi)
    dlng = (dlng * 180.0) / (a / sqrtmagic * math.cos(radlat) * pi)
    mglat = lat + dlat
    mglng = lng + dlng
    return [lng * 2 - mglng, lat * 2 - mglat]


def create_and_save_graph(shapefile_path, graph_pickle_path):
    print('create and save graph!')
    # 读取 Shapefile
    gdf = gpd.read_file(shapefile_path)
    print("列名:", gdf.columns)

    # 检查 GeoDataFrame 是否有 CRS
    print("原始 CRS:", gdf.crs)

    # 如果 GeoDataFrame 没有 CRS，假设它是 WGS84（经纬度坐标，EPSG:4326）
    if gdf.crs is None:
        gdf.set_crs('EPSG:4326', inplace=True)
        print("已设置 CRS 为 EPSG:4326")

    geod = pyproj.Geod(ellps="WGS84")
    gdf['length'] = gdf.geometry.apply(geod.geometry_length)

    print("列名:", gdf.columns)
    print(gdf)

    # 创建 NetworkX 图
    G = nx.Graph()
    for idx, row in gdf.iterrows():
        geom = row.geometry
        if isinstance(geom, LineString):
            coords = list(geom.coords)
            for start, end in zip(coords[:-1], coords[1:]):
                # 假设 'length' 是边的权重
                G.add_edge(start, end, weight=row['length'])
    print('start saving graph!')
    # 使用 pickle 将图形结构存储到文件
    with open(graph_pickle_path, 'wb') as f:
        pickle.dump(G, f)
    return G


def load_graph(graph_pickle_path):
    # 从 pickle 文件加载图
    with open(graph_pickle_path, 'rb') as f:
        G = pickle.load(f)
    return G


# 投影点到最近的路网节点
def project_points_to_graph(points, G):
    # 构建空间索引
    nodes = np.array([node for node in G.nodes])
    tree = cKDTree(nodes)

    # 投影点
    to_nearest_node_delta = []
    nearest_node_idxs = []
    for point in points:
        dist, idx = tree.query([point[0], point[1]], k=1)
        nearest_node_idxs.append(idx)
        to_nearest_node_delta.append(dist)
    return nearest_node_idxs, to_nearest_node_delta, nodes


def part_shortest_path_cal(args):
    lower, upper = args[0], args[1]
    print(f"son process ({lower},{upper})")
    distances_via_rn = np.frombuffer(shared_arr.get_obj(), dtype=np.float64).reshape(len(nearest_node_idxs),
                                                                                     len(nearest_node_idxs))
    cnt = 0
    for i, j in products[lower: upper]:
        try:
            path_length = nx.shortest_path_length(G, tuple(nodes[nearest_node_idxs[i]]),
                                                  tuple(
                                                      nodes[nearest_node_idxs[j]]),
                                                  weight='weight')
        except Exception as e:
            print(f"{e.args}\n======")
            print(
                f"ERROR:{tuple(nodes[nearest_node_idxs[i]])}->{tuple(nodes[nearest_node_idxs[j]])}!")
            print(f"{traceback.format_exc()}")
            path_length = float("inf")

        distances_via_rn[i][j] = path_length
        cnt += 1
        print(f"\tson process ({lower},{upper}):{cnt}/{upper - lower}", end='')
        print(
            f"最短路径长度从点 {nodes[nearest_node_idxs[i]]} 到点 {nodes[nearest_node_idxs[j]]} 是：{path_length/1000} 千米")
    print(f"FINISHED! son process ({lower},{upper})")


def init_pool(shared_arr_, G_, nearest_node_idxs_, products_):
    global shared_arr, G, nearest_node_idxs, products
    shared_arr = shared_arr_
    G = G_
    nearest_node_idxs = nearest_node_idxs_
    products = products_


def get_result(input_text, travel_plan):

    prompt = input_text + "我的旅行计划是出发地点=" + travel_plan["出发地点"] + ", 出发时间=" + travel_plan["出发时间"] + ", 结束地点=" + travel_plan["结束地点"] + ", 结束时间=" + travel_plan["结束时间"] + ", 经过个数=" + str(travel_plan["经过个数"]) + '''。你需要输出一个合理的规划，用json格式发给我。
    请注意：
    1. 返回的引号一定要用双引号"，要有回车。
    2. 地点与地点之间的通勤时间应该和我发给你的相同。
    3. 每个地点从到达时间到离开时间大概停留1-2个小时。

输出示例如下：
{
    "出发地点": "出发地点A",
    "出发时间": "hh:mm",
    "途径景点": [
        {
            "到达时间": "hh:mm",
            "名称": "景点1",
            "离开时间": "hh:mm"
        },
        {
            "到达时间": "hh:mm",
            "名称": "景点2",
            "离开时间": "hh:mm"
        },
        {
            "到达时间": "hh:mm",
            "名称": "景点3",
            "离开时间": "hh:mm"
        },
        {
            "到达时间": "hh:mm",
            "名称": "景点4",
            "离开时间": "hh:mm"
        }
    ],
    "结束地点": "结束地点B",
    "结束时间": "hh:mm"
}
'''
    # print(prompt)

    url = f"http://43.138.118.221:7778/vivo/gpt_discontinuous?input={prompt}&dialogId=2&userId=1"

    payload = {}
    headers = {
        'User-Agent': 'Apifox/1.0.0 (https://apifox.com)',
        'Accept': '*/*',
        'Host': '43.138.118.221:7778',
        'Connection': 'keep-alive'
    }

    response = requests.request("POST", url, headers=headers, data=payload)
    response_data = response.json()

    if response_data.get("code") == 1:
        content = response_data["data"]["output"]

        # Convert the JSON content to a Python dictionary
        result = eval(content.strip())

        return result
    else:
        return None




def get_description(dialogId):
    url = f"http://43.138.118.221:7778/dialog/records?id={dialogId}"

    headers = {
        'User-Agent': 'Apifox/1.0.0 (https://apifox.com)',
        'Accept': '*/*',
        'Host': '43.138.118.221:7778',
        'Connection': 'keep-alive'
    }

    response = requests.get(url, headers=headers)

    if response.status_code == 200:
        data = response.json()
        if data['code'] == 1:
            inputs = [record['input'] for record in data['data']]
            return ' '.join(inputs)
        else:
            return f"Error: {data['msg']}"
    else:
        return f"HTTP Error: {response.status_code}"





if __name__ == '__main__':
    userId ="6"
    dialogId = "96"
    
    # 读取和存储图数据的路径
    shapefile_path = 'beijing/edges.shp'  # 替换为你的 Shapefile 路径
    graph_pickle_path = 'beijing/networkx_beijing.pkl'  # 图形数据的存储路径

    create_g = False  # 替换为你的参数

    if create_g:
        G = create_and_save_graph(shapefile_path, graph_pickle_path)
    else:
        G = load_graph(graph_pickle_path)
        
    description=get_description(dialogId)
    
    print("description="+description)
    
    input_text = """请从下面对话中提取出五元组并以JSON格式输出："""+description+"""请将行程按以下格式输出，并确保以JSON格式提供：
    { 
        "出发地点": "[出发地点]",
        "出发时间": "yyyy.mm.dd hh:mm",
        "结束地点": "[结束地点]",
        "结束时间": "yyyy.mm.dd hh:mm",
        "经过个数": [经过个数]
    }"""
    travel_plan = get_travel_plan(input_text)
    if not travel_plan:
        raise HTTPException(status_code=400, detail="Failed to get travel plan")

    startPOI = travel_plan["出发地点"]
    endPOI = travel_plan["结束地点"]
    startTime = travel_plan["出发时间"]
    endTime = travel_plan["结束时间"]
    numPOI = travel_plan["经过个数"]

    start_coords = get_location(startPOI)
    end_coords = get_location(endPOI)
    if not start_coords or not end_coords:
        raise HTTPException(status_code=400, detail="Failed to get coordinates")

    start_coords_wgs84 = gcj02_to_wgs84(*start_coords)
    end_coords_wgs84 = gcj02_to_wgs84(*end_coords)

    poi = getFinalPOI(startPOI, endPOI, startTime, endTime, numPOI, userId)
    if not poi:
        raise HTTPException(status_code=400, detail="Failed to get POI data")

    points = [start_coords_wgs84] + [(poi_item['longitude'], poi_item['latitude']) for poi_item in poi['pois']] + [end_coords_wgs84]

    nearest_node_idxs, to_nearest_node_delta, nodes = project_points_to_graph(points, G)

    shape = (len(points), len(points))
    size = shape[0] * shape[1]
    distances_via_rn = np.full(shape, np.inf)
    np.fill_diagonal(distances_via_rn, 0.0)

    products = [(i, j) for i in range(len(points)) for j in range(len(points)) if j > i]

    for i, j in products:
        try:
            path_length = nx.shortest_path_length(G, tuple(nodes[nearest_node_idxs[i]]),
                                                  tuple(nodes[nearest_node_idxs[j]]),
                                                  weight='weight')
        except Exception as e:
            print(f"{e.args}\n======")
            print(f"ERROR:{tuple(nodes[nearest_node_idxs[i]])}->{tuple(nodes[nearest_node_idxs[j]])}!")
            print(f"{traceback.format_exc()}")
            path_length = float("inf")

        distances_via_rn[i][j] = path_length
        distances_via_rn[j][i] = path_length
        print(f"最短路径长度从点 {nodes[nearest_node_idxs[i]]} 到点 {nodes[nearest_node_idxs[j]]} 是：{path_length/1000} 千米")

    path = solve_tsp(distances_via_rn, endpoints=(0, numPOI+1))

    speed_m_per_m = 20 * 1000 / 60
    commute_times = []
    for i in range(len(path) - 1):
        from_idx = path[i]
        to_idx = path[i + 1]
        distance = distances_via_rn[max(from_idx, to_idx), min(from_idx, to_idx)]
        if distance == np.inf:
            commute_times.append(np.inf)
        else:
            time_in_minutes = math.ceil(distance / speed_m_per_m) + 2
            commute_times.append(time_in_minutes)

    poi_names = [travel_plan["出发地点"]] + [poi_item['name'] for poi_item in poi['pois']] + [travel_plan["结束地点"]]
    output_string = ""

    for i in range(len(commute_times)):
        from_name = poi_names[path[i]]
        to_name = poi_names[path[i + 1]]
        time = commute_times[i]
        if time == np.inf:
            output_string += f"从 {from_name} 到 {to_name} 的通勤时间为 无法到达\n"
        else:
            output_string += f"从 {from_name} 到 {to_name} 需要 {time} 分钟\n"

    result = get_result(output_string, travel_plan)

    poi_dict = {poi['name']: {'longitude': poi['longitude'], 'latitude': poi['latitude']} for poi in poi['pois']}
    result['出发地点经纬度'] = {'longitude': start_coords_wgs84[0], 'latitude': start_coords_wgs84[1]}
    result['结束地点经纬度'] = {'longitude': end_coords_wgs84[0], 'latitude': end_coords_wgs84[1]}

    for spot in result['途径景点']:
        name = spot['名称']
        if name in poi_dict:
            spot.update(poi_dict[name])

    print(json.dumps(result, ensure_ascii=False, indent=4))
    
    
    
shapefile_path = 'beijing/edges.shp'  # 替换为你的 Shapefile 路径
graph_pickle_path = 'beijing/networkx_beijing.pkl'  # 图形数据的存储路径

create_g = False  # 根据需要设定是否创建图形数据

if create_g:
    G = create_and_save_graph(shapefile_path, graph_pickle_path)
else:
    G = load_graph(graph_pickle_path)

import json

import folium
import requests
from fastapi import FastAPI
from folium import PolyLine

@app.get("/get-plan/")
async def get_plan(userId: int, dialogId: int):
        
    description=get_description(dialogId)
    
    print("description="+description)
    
    input_text = """请从下面对话中判断是否有以下五元组，若有，则提取出五元组并以JSON格式输出："""+description+"""请将行程按以下格式输出，并确保以JSON格式提供：
    { 
        "是否存在": "是",
        "出发地点": "[出发地点]",
        "出发时间": "hh:mm",
        "结束地点": "[结束地点]",
        "结束时间": "hh:mm",
        "经过个数": [经过个数]
    }
    若其中有一些不存在，则返回
    { 
        "是否存在": "否",
        "出发地点": None,
        "出发时间": None,
        "结束地点": None,
        "结束时间": None,
        "经过个数": None
    }
    请注意，只要回复json文件即可，不需要回复多余的内容"""
    travel_plan = get_travel_plan(input_text)
    if not travel_plan:
        raise HTTPException(status_code=400, detail="Failed to get travel plan")
    
    if(travel_plan["是否存在"] == "否"):
        return {"html": None, "routeData": None  , "have_tuple":0}
    

    startPOI = travel_plan["出发地点"]
    endPOI = travel_plan["结束地点"]
    startTime = travel_plan["出发时间"]
    endTime = travel_plan["结束时间"]
    numPOI = travel_plan["经过个数"]

    start_coords = get_location(startPOI)
    end_coords = get_location(endPOI)
    if not start_coords or not end_coords:
        raise HTTPException(status_code=400, detail="Failed to get coordinates")

    start_coords_wgs84 = gcj02_to_wgs84(*start_coords)
    end_coords_wgs84 = gcj02_to_wgs84(*end_coords) 
    print(start_coords_wgs84)
    print(end_coords_wgs84)

    poi = getFinalPOI(startPOI, endPOI, startTime, endTime, numPOI, userId)
    if not poi:
        raise HTTPException(status_code=400, detail="Failed to get POI data")

    points = [start_coords_wgs84] + [(poi_item['longitude'], poi_item['latitude']) for poi_item in poi['pois']] + [end_coords_wgs84]

    nearest_node_idxs, to_nearest_node_delta, nodes = project_points_to_graph(points, G)

    shape = (len(points), len(points))
    size = shape[0] * shape[1]
    distances_via_rn = np.full(shape, np.inf)
    np.fill_diagonal(distances_via_rn, 0.0)

    products = [(i, j) for i in range(len(points)) for j in range(len(points)) if j > i]

    for i, j in products:
        try:
            path_length = nx.shortest_path_length(G, tuple(nodes[nearest_node_idxs[i]]),
                                                  tuple(nodes[nearest_node_idxs[j]]),
                                                  weight='weight')
        except Exception as e:
            print(f"{e.args}\n======")
            print(f"ERROR:{tuple(nodes[nearest_node_idxs[i]])}->{tuple(nodes[nearest_node_idxs[j]])}!")
            print(f"{traceback.format_exc()}")
            path_length = float("inf")

        distances_via_rn[i][j] = path_length
        distances_via_rn[j][i] = path_length
        print(f"最短路径长度从点 {nodes[nearest_node_idxs[i]]} 到点 {nodes[nearest_node_idxs[j]]} 是：{path_length/1000} 千米")

    path = solve_tsp(distances_via_rn, endpoints=(0, numPOI+1))

    speed_m_per_m = 20 * 1000 / 60
    commute_times = []
    for i in range(len(path) - 1):
        from_idx = path[i]
        to_idx = path[i + 1]
        distance = distances_via_rn[max(from_idx, to_idx), min(from_idx, to_idx)]
        if distance == np.inf:
            commute_times.append(np.inf)
        else:
            time_in_minutes = math.ceil(distance / speed_m_per_m) + 2
            commute_times.append(time_in_minutes)

    poi_names = [travel_plan["出发地点"]] + [poi_item['name'] for poi_item in poi['pois']] + [travel_plan["结束地点"]]
    output_string = ""

    for i in range(len(commute_times)):
        from_name = poi_names[path[i]]
        to_name = poi_names[path[i + 1]]
        time = commute_times[i]
        if time == np.inf:
            output_string += f"从 {from_name} 到 {to_name} 的通勤时间为 无法到达\n"
        else:
            output_string += f"从 {from_name} 到 {to_name} 需要 {time} 分钟\n"

    result = get_result(output_string, travel_plan)

    poi_dict = {poi['name']: {'longitude': poi['longitude'], 'latitude': poi['latitude']} for poi in poi['pois']}
    result['出发地点经纬度'] = {'longitude': start_coords_wgs84[0], 'latitude': start_coords_wgs84[1]}
    result['结束地点经纬度'] = {'longitude': end_coords_wgs84[0], 'latitude': end_coords_wgs84[1]}


    # Assume `result` is the initial dictionary containing the travel plan data
    for spot in result['途径景点']:
        name = spot['名称']
        if name in poi_dict:
            spot.update(poi_dict[name])

    # Convert the updated result to a JSON string
    response = json.dumps(result, ensure_ascii=False, indent=4)

    # Load the JSON string into a Python dictionary
    route_data = json.loads(response)
    if route_data:
        # 创建地图对象
        map_beijing = folium.Map(location=[39.9066355, 116.3913935], zoom_start=12)

        # 标注出发地点
        folium.Marker(
            location=[route_data["出发地点经纬度"]["latitude"], route_data["出发地点经纬度"]["longitude"]],
            popup=route_data["出发地点"],
            icon=folium.Icon(color="green")
        ).add_to(map_beijing)

        # 标注途径景点
        for poi in route_data["途径景点"]:
            folium.Marker(
                location=[poi["latitude"], poi["longitude"]],
                popup=f"{poi['名称']} (到达: {poi['到达时间']}, 离开: {poi['离开时间']})"
            ).add_to(map_beijing)

        # 标注结束地点
        folium.Marker(
            location=[route_data["结束地点经纬度"]["latitude"], route_data["结束地点经纬度"]["longitude"]],
            popup=route_data["结束地点"],
            icon=folium.Icon(color="red")
        ).add_to(map_beijing)

        # 获取路径点
        path_points = [
                          (route_data["出发地点经纬度"]["latitude"], route_data["出发地点经纬度"]["longitude"])
                      ] + [(poi["latitude"], poi["longitude"]) for poi in route_data["途径景点"]] + [
                          (route_data["结束地点经纬度"]["latitude"], route_data["结束地点经纬度"]["longitude"])
                      ]

        # 绘制路径
        PolyLine(path_points, color="blue", weight=2.5, opacity=1).add_to(map_beijing)

        map_file = f"beijing_route_map_(dialogId){dialogId}.html"
        map_beijing.save(map_file)
        print(f"地图已保存为 {map_file}")

        # 确保文件可以通过浏览器访问
        import os
        abs_path = os.path.abspath(map_file)

        return {"html": abs_path, "routeData": route_data  , "have_tuple":1}
    