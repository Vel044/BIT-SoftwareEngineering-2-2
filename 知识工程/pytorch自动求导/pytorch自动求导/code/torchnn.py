import torch
import torch.nn as nn  # 各种定义 neural network 的方法

N, D_in, H, D_out = 64, 1000, 100, 10  # 64个训练数据（只是一个batch），输入是1000维，hidden是100维，输出是10维

'''随机创建一些训练数据'''
X = torch.randn(N, D_in)
y = torch.randn(N, D_out)

model = torch.nn.Sequential(
    torch.nn.Linear(D_in, H, bias=True),  # W1 * X + b，默认True
    torch.nn.ReLU(),
    torch.nn.Linear(H, D_out)
)

# model = model.cuda()  #这是使用GPU的情况

loss_fn = nn.MSELoss(reduction='sum')

learning_rate = 1e-4

for t in range(500):  # 做500次迭代
    '''前向传播（forward pass）'''
    y_hat = model(X)  # model(X) = model.forward(X), N * D_out

    '''计算损失函数（compute loss）'''
    loss = loss_fn(y_hat, y)  # 均方误差，忽略了÷N，loss就是一个计算图（computation graph）
    print("Epoch:{}   Loss:{}".format(t, loss.item()))  # 打印每个迭代的损失

    '''后向传播（backward pass）'''
    loss.backward()

    '''参数更新（update weights of W1 and W2）'''
    with torch.no_grad():
        for param in model.parameters():
            param -= learning_rate * param.grad  # 模型中所有的参数更新

    model.zero_grad()

