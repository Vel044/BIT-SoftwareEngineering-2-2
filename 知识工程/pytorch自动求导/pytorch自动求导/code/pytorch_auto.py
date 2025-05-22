import torch

N, D_in, H, D_out = 64, 1000, 100, 10  # 64个训练数据（只是一个batch），输入是1000维，hidden是100维，输出是10维

'''随机创建一些训练数据'''
X = torch.randn(N, D_in)
y = torch.randn(N, D_out)

W1 = torch.randn(D_in, H, requires_grad=True)  # 1000维转成100维
W2 = torch.randn(H, D_out, requires_grad=True)  # 100维转成10维

learning_rate = 1e-6

for t in range(500):  # 做500次迭代
    '''前向传播（forward pass）'''
    y_hat = X.mm(W1).clamp(min=0).mm(W2)  # N * D_out

    '''计算损失函数（compute loss）'''
    loss = (y_hat - y).pow(2).sum()  # 均方误差，忽略了÷N，loss就是一个计算图（computation graph）
    print("Epoch:{}   Loss:{}".format(t, loss.item()))  # 打印每个迭代的损失

    '''后向传播（backward pass）'''
    loss.backward()

    '''参数更新（update weights of W1 and W2）'''
    with torch.no_grad():
        W1 -= learning_rate * W1.grad
        W2 -= learning_rate * W2.grad
        W1.grad.zero_()
        W2.grad.zero_()
