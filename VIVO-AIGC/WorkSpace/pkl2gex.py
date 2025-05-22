import pickle
import networkx as nx
import random

# 设置文件路径
pkl_path = 'networkx_beijing.pkl'  # 修改为您的 pkl 文件路径
gexf_path = 'networkx_beijing.gexf'  # 导出的 GEXF 文件路径

# 1. 加载 NetworkX 图
print("加载图形数据...")
with open(pkl_path, 'rb') as f:
    G = pickle.load(f)

print(f"图形加载完成，包含 {G.number_of_nodes()} 个节点和 {G.number_of_edges()} 条边。")

# 2. 检查并转换节点 ID 类型（确保节点 ID 为字符串或整数）
def ensure_node_id_type(G):
    mapping = {}
    for node in G.nodes():
        if not isinstance(node, (str, int)):
            mapping[node] = str(node)
    if mapping:
        print("转换节点 ID 类型...")
        G = nx.relabel_nodes(G, mapping)
    else:
        print("所有节点 ID 类型均符合要求。")
    return G

G = ensure_node_id_type(G)

# 3. 导出为 GEXF 格式
print("导出为 GEXF 格式...")
try:
    nx.write_gexf(G, gexf_path)
    print(f"GEXF 文件已成功保存到 {gexf_path}")
except Exception as e:
    print("导出 GEXF 文件时发生错误：", e)

# 可选步骤：如果图形过大，建议抽取子图进行可视化
# Gephi 对于百万级别的节点和边可能会运行缓慢或无法处理
# 可以根据需要选择以下步骤之一

# 方法一：随机抽取子图
def export_subgraph_randomly(G, sample_size, output_path):
    print(f"随机抽取 {sample_size} 个节点生成子图...")
    if sample_size >= G.number_of_nodes():
        print("样本大小大于或等于节点总数，使用原图。")
        subgraph = G
    else:
        sample_nodes = random.sample(list(G.nodes()), sample_size)
        subgraph = G.subgraph(sample_nodes).copy()
        print(f"子图包含 {subgraph.number_of_nodes()} 个节点和 {subgraph.number_of_edges()} 条边。")
    
    print(f"导出子图为 {output_path} ...")
    try:
        nx.write_gexf(subgraph, output_path)
        print(f"子图 GEXF 文件已成功保存到 {output_path}")
    except Exception as e:
        print("导出子图 GEXF 文件时发生错误：", e)

# 示例：导出包含 10000 个节点的子图
# Uncomment the following line if需要导出子图
export_subgraph_randomly(G, 10000, 'networkx_beijing_subgraph_10000.gexf')
export_subgraph_randomly(G, 1000, 'networkx_beijing_subgraph_1000.gexf')
export_subgraph_randomly(G, 1000, 'networkx_beijing_subgraph_100.gexf')
# 方法二：根据节点属性抽取子图（如果有）
# 如果节点有特定属性，可以根据属性进行筛选
# 例如，选择某一类别的节点
# def export_subgraph_by_attribute(G, attribute, value, output_path):
#     print(f"根据属性 {attribute} = {value} 抽取子图...")
#     selected_nodes = [n for n, attr in G.nodes(data=True) if attr.get(attribute) == value]
#     subgraph = G.subgraph(selected_nodes).copy()
#     print(f"子图包含 {subgraph.number_of_nodes()} 个节点和 {subgraph.number_of_edges()} 条边。")
#     nx.write_gexf(subgraph, output_path)
#     print(f"子图 GEXF 文件已成功保存到 {output_path}")

# 示例：根据属性导出子图
# Uncomment and modify the following lines if需要根据属性导出子图
# export_subgraph_by_attribute(G, 'category', 'desired_value', 'networkx_beijing_filtered.gexf')
