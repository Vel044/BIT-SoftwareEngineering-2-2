# 导入需要的模块
from flask import Flask, render_template

# 创建Flask应用程序
app = Flask(__name__)

# 首页路由
@app.route('/')
def home():
    return render_template('home.html')

# 登录页面路由
@app.route('/login')
def login():
    return render_template('login.html')

# 注册页面路由
@app.route('/register')
def register():
    return render_template('register.html')

# 个人主页页面路由
@app.route('/dashboard')
def dashboard():
    # 这里可以传入一些用户信息到个人主页模板中，例如用户名、邮箱等
    username = "John Doe"  # 替换为实际的用户名
    email = "johndoe@example.com"  # 替换为实际的邮箱
    return render_template('dashboard.html', username=username, email=email)

if __name__ == '__main__':
    app.run(debug=True)
