import pandas as pd
import requests
import json

def get_dis_tm(origin, destination):
    url = 'https://restapi.amap.com/v3/direction/driving?'
    key = '6a186a3cb0f3af7939876f5d9e3e32c7'
    link = '{}origin={}&destination={}&key={}'.format(url, origin ,destination , key)
    response = requests.get(link)
    dis, tm = 999999, 999999
    if response.status_code == 200:
        results = response.json()
        if results['status'] == '1':
            dis = int(results['route']['paths'][0]['distance'])
            tm = int(results['route']['paths'][0]['duration'])
        else:
            print(link)
    return dis, tm

start_point = '116.1300879,39.72838963'
end_point = '116.1627336,39.73047058'
print(get_dis_tm(start_point,end_point))
