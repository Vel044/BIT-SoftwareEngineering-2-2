unit Unit3;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, DB, ADODB, Grids, DBGrids;

type
  TMerchantMenuForm = class(TForm)

    HelloMerchantLabel: TLabel;
    DBGrid1: TDBGrid;
    ADOQuery1: TADOQuery;
    DataSource1: TDataSource;
    SearchProductsButton: TButton;
    Label3: TLabel;
    Label2: TLabel;
    ProductIDEdit: TEdit;
    ProductNameEdit: TEdit;
    PriceEdit: TEdit;
    StockEdit: TEdit;
    Label1: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    AddProductButton: TButton;
    Label7: TLabel;
    Label8: TLabel;
    SearchOrderButton: TButton;
    Label9: TLabel;
    DeliveryOrderButton: TButton;
    Label12: TLabel;
    OrderIDEdit: TEdit;
    AddressComboBox: TComboBox;
    procedure SearchProductsButtonClick(Sender: TObject);
    procedure AddProductButtonClick(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure SearchOrderButtonClick(Sender: TObject);
    procedure DeliveryOrderButtonClick(Sender: TObject);
  private
    { Private declarations }
    FUserID: string;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent; const UserID: string); reintroduce;
  end;

var
  MerchantMenuForm: TMerchantMenuForm;

implementation

uses
  Unit1;  // 确保使用 LoginForm

{$R *.dfm}

constructor TMerchantMenuForm.Create(AOwner: TComponent; const UserID: string);
begin
  inherited Create(AOwner);
  FUserID := UserID;
  // 在这里可以使用 FUserID 初始化界面或执行其他操作
  HelloMerchantLabel.Caption := 'Hello,Merchant' + FUserID;  // 修改这里设置 HelloConsumerLabel 的文本
end;




procedure TMerchantMenuForm.SearchProductsButtonClick(Sender: TObject);
begin
  // 查询商家相关的产品
  ADOQuery1.Close;
  ADOQuery1.SQL.Text := Format(
    'SELECT ProductID, ProductName, Price, Stock ' +
    'FROM Products ' +
    'WHERE MerchantID = %s',
    [QuotedStr(FUserID)]
  );
  ADOQuery1.Open;

  // 将查询结果绑定到 DBGrid
  DBGrid1.DataSource := DataSource1;
  DataSource1.DataSet := ADOQuery1;

  // 设置列宽
  DBGrid1.Columns[0].Width := 50;   // ProductID 列宽度
  DBGrid1.Columns[1].Width := 150;  // ProductName 列宽度
  DBGrid1.Columns[2].Width := 100;  // Price 列宽度
  DBGrid1.Columns[3].Width := 80;   // Stock 列宽度
end;



//加入产品
procedure TMerchantMenuForm.AddProductButtonClick(Sender: TObject);
var
  ProductID, ProductName: string;
  Price: Double;
  Stock: Integer;
begin
  // 读取输入框的值
  ProductID := ProductIDEdit.Text;
  ProductName := ProductNameEdit.Text;
  Price := StrToFloat(PriceEdit.Text);
  Stock := StrToInt(StockEdit.Text);

  // 插入新产品到数据库
  ADOQuery1.Close;
  ADOQuery1.SQL.Text := Format(
    'INSERT INTO Products (ProductID, ProductName, Price, Stock, MerchantID) ' +
    'VALUES (%s, %s, %f, %d, %s)', 
    [QuotedStr(ProductID), QuotedStr(ProductName), Price, Stock, QuotedStr(FUserID)]
  );
  ADOQuery1.ExecSQL;

  // 重新查询并刷新 DBGrid 显示
  SearchProductsButtonClick(Sender);

  // 清空输入框
  ProductIDEdit.Clear;
  ProductNameEdit.Clear;
  PriceEdit.Clear;
  StockEdit.Clear;

  ShowMessage('Product added successfully.');
end;


procedure TMerchantMenuForm.FormClose(Sender: TObject; var Action: TCloseAction);
begin
  // 在窗体关闭时显示登录窗体并隐藏当前窗体
  LoginForm.Show;
  Action := caHide;
end;

procedure TMerchantMenuForm.SearchOrderButtonClick(Sender: TObject);
begin
  // 查询该商家的所有订单
  ADOQuery1.Close;
  ADOQuery1.SQL.Text := Format(
    'SELECT OrderID, ConsumerID, ProductID, Quantity, TotalAmount, Status ' +
    'FROM Orders ' +
    'WHERE ProductID IN (SELECT ProductID FROM Products WHERE MerchantID = %s)',
    [QuotedStr(FUserID)]
  );
  ADOQuery1.Open;

  // 将查询结果绑定到 DBGrid
  DBGrid1.DataSource := DataSource1;
  DataSource1.DataSet := ADOQuery1;

  // 设置列宽（可根据需要调整）
  DBGrid1.Columns[0].Width := 50;  // OrderID 列宽度
  DBGrid1.Columns[1].Width := 100; // ConsumerID 列宽度
  DBGrid1.Columns[2].Width := 50;  // ProductID 列宽度
  DBGrid1.Columns[3].Width := 50;  // Quantity 列宽度
  DBGrid1.Columns[4].Width := 100; // TotalAmount 列宽度
  DBGrid1.Columns[5].Width := 100; // Status 列宽度
end;
procedure TMerchantMenuForm.DeliveryOrderButtonClick(Sender: TObject);
var
  OrderID: Integer;
  OrderStatus: string;
begin
  // 读取订单号
  if not TryStrToInt(OrderIDEdit.Text, OrderID) then
  begin
    ShowMessage('Invalid Order ID.');
    Exit;
  end;

  // 检查订单状态是否为“待发货”
  ADOQuery1.Close;
  ADOQuery1.SQL.Text := Format(
    'SELECT Status FROM Orders WHERE OrderID = %d',
    [OrderID]
  );
  ADOQuery1.Open;
  if ADOQuery1.IsEmpty then
  begin
    ShowMessage('Order not found.');
    Exit;
  end;

  OrderStatus := ADOQuery1.FieldByName('Status').AsString;
  if OrderStatus <> '待发货' then
  begin
    ShowMessage('Only orders with status "Paid" can be marked as "Delivered".');
    Exit;
  end;

  // 更新订单状态为“已发货”
  ADOQuery1.Close;
  ADOQuery1.SQL.Text := Format(
    'UPDATE Orders SET Status = %s WHERE OrderID = %d',
    [QuotedStr('已发货'), OrderID]
  );
  ADOQuery1.ExecSQL;

  // 在 AddressComboBox 显示该顾客的地址
  ADOQuery1.Close;
  ADOQuery1.SQL.Text := Format(
    'SELECT Address FROM Addresses WHERE ConsumerID IN (SELECT ConsumerID FROM Orders WHERE OrderID = %d)',
    [OrderID]
  );
  ADOQuery1.Open;
  if not ADOQuery1.IsEmpty then
    AddressComboBox.Text := ADOQuery1.FieldByName('Address').AsString
  else
    AddressComboBox.Text := '';

  ShowMessage('Order has been marked as "Delivered" and customer address is displayed.');
end;

end.

