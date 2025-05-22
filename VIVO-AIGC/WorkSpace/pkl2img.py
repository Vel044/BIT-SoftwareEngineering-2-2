import pickle
import networkx as nx

path = 'networkx_beijing.pkl'
with open(path, 'rb') as f:
    G = pickle.load(f)

import random

sample_nodes = random.sample(G.nodes(), 1000)
subgraph = G.subgraph(sample_nodes)

import matplotlib.pyplot as plt

nx.draw(subgraph, with_labels=False, node_size=10)
plt.show()
