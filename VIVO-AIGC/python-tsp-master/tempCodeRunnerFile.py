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
