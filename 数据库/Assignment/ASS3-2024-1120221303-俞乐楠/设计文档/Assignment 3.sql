DROP TABLE IF EXISTS Payments;
DROP TABLE IF EXISTS Addresses;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Merchants;
DROP TABLE IF EXISTS Consumers;

CREATE TABLE Consumers (
    ConsumerID INT PRIMARY KEY,
    Password VARCHAR(50) NOT NULL
);

CREATE TABLE Merchants (
    MerchantID INT PRIMARY KEY,
    Password VARCHAR(50) NOT NULL
);

CREATE TABLE Products (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Stock INT NOT NULL,
    MerchantID INT NOT NULL,
    FOREIGN KEY (MerchantID) REFERENCES Merchants(MerchantID) ON DELETE CASCADE
);

CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    ConsumerID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL,
    TotalAmount DECIMAL(10, 2) NOT NULL,
    Status VARCHAR(20) NOT NULL,
    FOREIGN KEY (ConsumerID) REFERENCES Consumers(ConsumerID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);

CREATE TABLE Addresses (
    AddressID INT PRIMARY KEY,
    ConsumerID INT NOT NULL,
    Address VARCHAR(255) NOT NULL,
    FOREIGN KEY (ConsumerID) REFERENCES Consumers(ConsumerID) ON DELETE CASCADE
);

CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY,
    OrderID INT NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentMethod VARCHAR(50) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE
);

SELECT * FROM consumers;
SELECT * FROM merchants;
SELECT * FROM products;
SELECT * FROM addresses;
SELECT * FROM orders;
SELECT * FROM payments;


-- 消费者查看产品信息视图
-- 如果视图已经存在，则删除它
DROP VIEW IF EXISTS ConsumerOrdersView;

-- 创建消费者查看订单的视图
CREATE VIEW ConsumerOrdersView AS
SELECT
    o.OrderID,
    o.ConsumerID,
    c.Password AS ConsumerPassword,
    o.ProductID,
    p.ProductName,
    o.Quantity,
    o.TotalAmount,
    o.Status,
    p.MerchantID,
    m.Password AS MerchantPassword
FROM
    Orders o
JOIN
    Consumers c ON o.ConsumerID = c.ConsumerID
JOIN
    Products p ON o.ProductID = p.ProductID
JOIN
    Merchants m ON p.MerchantID = m.MerchantID;

-- 查询视图以验证其内容
SELECT * FROM ConsumerOrdersView;


-- 如果存储过程 AddProduct 已存在，则将其删除
DROP PROCEDURE IF EXISTS AddProduct;

-- 创建新的存储过程 AddProduct
CREATE OR REPLACE PROCEDURE AddProduct(
    IN p_ProductID INT,
    IN p_ProductName VARCHAR(100),
    IN p_Price DECIMAL(10, 2),
    IN p_Stock INT,
    IN p_MerchantID INT
)
AS
BEGIN
    -- 插入新产品到 Products 表中
    INSERT INTO Products (ProductID, ProductName, Price, Stock, MerchantID)
    VALUES (p_ProductID, p_ProductName, p_Price, p_Stock, p_MerchantID);
    
    -- 输出一条消息，指示插入成功
    RAISE NOTICE 'Product % has been added successfully.', p_ProductName;
END;



-- 删除已有的触发器函数
DROP FUNCTION IF EXISTS UpdateProductStock;

-- 创建新的触发器函数
CREATE OR REPLACE FUNCTION UpdateProductStock()
RETURNS TRIGGER
AS $$
BEGIN
    -- 减少产品库存
    UPDATE Products
    SET Stock = Stock - NEW.Quantity
    WHERE ProductID = NEW.ProductID;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
