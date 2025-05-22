这是一个通过输入DialogID和UserID来获取用户行程规划的接口。


安装 TSP-solver
```
python setup.py install
```

安装 GDAL-2.3.2-cp36-cp36m-manylinux1_x86_64.whl
```
pip install GDAL-2.3.2-cp36-cp36m-manylinux1_x86_64.whl
```

运行接口
```
nohup uvicorn get_plan:app --reload --host 0.0.0.0 --port 7780 > plan.log 2>&1 &
```

监视接口
```
tail -f plan.log
```