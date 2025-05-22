# 打开原始文件并读取内容
with open('1998-01-2003.txt', 'r') as file:
    lines = file.readlines()

# 移除空行
cleaned_lines = [line for line in lines if line.strip()]

# 写入新文件
with open('1998-01-2003-cleaned.txt', 'w') as file:
    file.writelines(cleaned_lines)

print("空行已被清除，并且内容已保存到 '1998-01-2003-cleaned.txt'.")
