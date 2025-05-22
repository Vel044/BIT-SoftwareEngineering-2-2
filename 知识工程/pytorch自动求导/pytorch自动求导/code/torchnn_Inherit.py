import torch
import torch.nn as nn  # 各种定义 neural network 的方法
from torchsummary import summary
# pip install torchsummary
N, D_in, H, D_out = 64, 1000, 100, 10  # 64个训练数据（只是一个batch），输入是1000维，hidden是100维，输出是10维

'''随机创建一些训练数据'''
X = torch.randn(N, D_in)
y = torch.randn(N, D_out)

'''定义两层网络'''


class TwoLayerNet(torch.nn.Module):
    def __init__(self, D_in, H, D_out):
        super(TwoLayerNet, self).__init__()
        # 定义模型结构
        self.linear1 = torch.nn.Linear(D_in, H, bias=False)
        self.linear2 = torch.nn.Linear(H, D_out, bias=False)

    def forward(self, x):
        y_hat = self.linear2(self.linear1(X).clamp(min=0))
        return y_hat


model = TwoLayerNet(D_in, H, D_out)

loss_fn = nn.MSELoss(reduction='sum')
learning_rate = 1e-4
optimizer = torch.optim.Adam(model.parameters(), lr=learning_rate)

for t in range(500):  # 做500次迭代
    '''前向传播（forward pass）'''
    y_hat = model(X)  # model.forward(), N * D_out

    '''计算损失函数（compute loss）'''
    loss = loss_fn(y_hat, y)  # 均方误差，忽略了÷N，loss就是一个计算图（computation graph）
    print("Epoch:{}   Loss:{}".format(t, loss.item()))  # 打印每个迭代的损失

    optimizer.zero_grad()  # 求导之前把 gradient 清空
    '''后向传播（backward pass）'''
    loss.backward()

    '''参数更新（update weights of W1 and W2）'''
    optimizer.step()  # 一步把所有参数全更新


print(summary(model, (64, 1000)))