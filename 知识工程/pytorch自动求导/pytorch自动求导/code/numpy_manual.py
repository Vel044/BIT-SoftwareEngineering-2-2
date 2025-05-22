import numpy as np
import matplotlib.pyplot as plt

N, D_in, H, D_out = 64, 1000, 100, 10  # 64个训练数据（只是一个batch），输入是1000维，hidden是100维，输出是10维

'''随机创建一些训练数据'''
X = np.random.randn(N, D_in)
y = np.random.randn(N, D_out)

W1 = np.random.randn(D_in, H)  # 1000维转成100维
W2 = np.random.randn(H, D_out)  # 100维转成10维

learning_rate = 1e-6

all_loss = []

epoch = 500

for t in range(500):  # 做500次迭代
    '''前向传播（forward pass）'''
    h = X.dot(W1)  # N * H
    h_relu = np.maximum(h, 0)  # 激活函数，N * H
    y_hat = h_relu.dot(W2)  # N * D_out

    '''计算损失函数（compute loss）'''
    loss = np.square(y_hat - y).sum()  # 均方误差，忽略了÷N
    print("Epoch:{}   Loss:{}".format(t, loss))  # 打印每个迭代的损失
    all_loss.append(loss)

    '''后向传播（backward pass）'''
    # 计算梯度（此处没用torch，用最普通的链式求导，最终要得到 d{loss}/dX）
    grad_y_hat = 2.0 * (y_hat - y)  # d{loss}/d{y_hat}，N * D_out
    grad_W2 = h_relu.T.dot(grad_y_hat)  # 看前向传播中的第三个式子，d{loss}/d{W2}，H * D_out

    grad_h_relu = grad_y_hat.dot(W2.T)  # 看前向传播中的第三个式子，d{loss}/d{h_relu}，N * H
    grad_h = grad_h_relu.copy()  # 这是h>0时的情况，d{h_relu}/d{h}=1
    grad_h[h < 0] = 0  # d{loss}/d{h}
    grad_W1 = X.T.dot(grad_h)  # 看前向传播中的第一个式子，d{loss}/d{W1}

    '''参数更新（update weights of W1 and W2）'''
    W1 -= learning_rate * grad_W1
    W2 -= learning_rate * grad_W2

plt.plot(all_loss)
plt.xlabel("epoch")
plt.ylabel("Loss")
plt.show()