object MerchantMenuForm: TMerchantMenuForm
  Left = 576
  Top = 196
  Width = 985
  Height = 679
  Caption = 'MerchantMenu'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object HelloMerchantLabel: TLabel
    Left = 176
    Top = 32
    Width = 72
    Height = 13
    Caption = 'Hello,Merchant'
  end
  object Label3: TLabel
    Left = 40
    Top = 104
    Width = 113
    Height = 13
    Caption = '1.Search Your Products'
  end
  object Label2: TLabel
    Left = 40
    Top = 136
    Width = 73
    Height = 13
    Caption = '2.Add Products'
  end
  object Label1: TLabel
    Left = 40
    Top = 168
    Width = 51
    Height = 13
    Caption = 'Product ID'
  end
  object Label4: TLabel
    Left = 168
    Top = 168
    Width = 68
    Height = 13
    Caption = 'Product Name'
  end
  object Label5: TLabel
    Left = 40
    Top = 224
    Width = 24
    Height = 13
    Caption = 'Price'
  end
  object Label6: TLabel
    Left = 168
    Top = 224
    Width = 28
    Height = 13
    Caption = 'Stock'
  end
  object Label7: TLabel
    Left = 40
    Top = 72
    Width = 78
    Height = 13
    Caption = 'You can choose'
  end
  object Label8: TLabel
    Left = 40
    Top = 280
    Width = 105
    Height = 13
    Caption = '3.Search Your Oeders'
  end
  object Label9: TLabel
    Left = 40
    Top = 312
    Width = 104
    Height = 13
    Caption = '4.Delivery Your Oeder'
  end
  object Label12: TLabel
    Left = 40
    Top = 336
    Width = 40
    Height = 13
    Caption = 'Order ID'
  end
  object DBGrid1: TDBGrid
    Left = 392
    Top = 104
    Width = 505
    Height = 449
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'MS Sans Serif'
    TitleFont.Style = []
  end
  object SearchProductsButton: TButton
    Left = 184
    Top = 104
    Width = 161
    Height = 17
    Caption = 'Search Your Products'
    TabOrder = 1
    OnClick = SearchProductsButtonClick
  end
  object ProductIDEdit: TEdit
    Left = 40
    Top = 192
    Width = 121
    Height = 21
    TabOrder = 2
  end
  object ProductNameEdit: TEdit
    Left = 168
    Top = 192
    Width = 121
    Height = 21
    TabOrder = 3
  end
  object PriceEdit: TEdit
    Left = 40
    Top = 248
    Width = 121
    Height = 21
    TabOrder = 4
  end
  object StockEdit: TEdit
    Left = 168
    Top = 248
    Width = 121
    Height = 21
    TabOrder = 5
  end
  object AddProductButton: TButton
    Left = 184
    Top = 136
    Width = 161
    Height = 17
    Caption = 'Add Product'
    TabOrder = 6
    OnClick = AddProductButtonClick
  end
  object SearchOrderButton: TButton
    Left = 184
    Top = 280
    Width = 161
    Height = 17
    Caption = 'Search Order'
    TabOrder = 7
    OnClick = SearchOrderButtonClick
  end
  object DeliveryOrderButton: TButton
    Left = 184
    Top = 312
    Width = 161
    Height = 17
    Caption = 'Delivery Order'
    TabOrder = 8
    OnClick = DeliveryOrderButtonClick
  end
  object OrderIDEdit: TEdit
    Left = 40
    Top = 360
    Width = 121
    Height = 21
    TabOrder = 9
  end
  object AddressComboBox: TComboBox
    Left = 184
    Top = 360
    Width = 145
    Height = 21
    ItemHeight = 13
    TabOrder = 10
    Text = 'Address'
  end
  object ADOQuery1: TADOQuery
    Connection = LoginForm.ADOConnection1
    Parameters = <>
    Left = 920
    Top = 80
  end
  object DataSource1: TDataSource
    DataSet = ADOQuery1
    Left = 920
    Top = 120
  end
end
