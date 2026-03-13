object ConsumerMenuForm: TConsumerMenuForm
  Left = 383
  Top = 103
  Width = 951
  Height = 717
  Caption = 'ConsumerMenu'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object HelloConsumerLabel: TLabel
    Left = 176
    Top = 32
    Width = 74
    Height = 13
    Caption = 'Hello,Consumer'
  end
  object Label1: TLabel
    Left = 40
    Top = 72
    Width = 78
    Height = 13
    Caption = 'You can choose'
  end
  object Label2: TLabel
    Left = 40
    Top = 136
    Width = 69
    Height = 13
    Caption = '2.Add Address'
  end
  object Label3: TLabel
    Left = 40
    Top = 104
    Width = 88
    Height = 13
    Caption = '1.Search Products'
  end
  object Label4: TLabel
    Left = 40
    Top = 272
    Width = 51
    Height = 13
    Caption = 'Product ID'
  end
  object Label7: TLabel
    Left = 200
    Top = 272
    Width = 28
    Height = 13
    Caption = 'Stock'
  end
  object Label5: TLabel
    Left = 40
    Top = 168
    Width = 52
    Height = 13
    Caption = 'Address ID'
  end
  object Label6: TLabel
    Left = 40
    Top = 240
    Width = 94
    Height = 13
    Caption = '3.Purchase Product'
  end
  object Label8: TLabel
    Left = 40
    Top = 328
    Width = 40
    Height = 13
    Caption = 'Order ID'
  end
  object Label9: TLabel
    Left = 40
    Top = 384
    Width = 97
    Height = 13
    Caption = '4.Search Your Order'
  end
  object Label10: TLabel
    Left = 40
    Top = 416
    Width = 96
    Height = 13
    Caption = '5.Pay for Your Order'
  end
  object Label11: TLabel
    Left = 40
    Top = 440
    Width = 55
    Height = 13
    Caption = 'Payment ID'
  end
  object Label12: TLabel
    Left = 200
    Top = 440
    Width = 40
    Height = 13
    Caption = 'Order ID'
  end
  object Label13: TLabel
    Left = 40
    Top = 528
    Width = 103
    Height = 13
    Caption = '6.Receive Your Order'
  end
  object Label14: TLabel
    Left = 40
    Top = 552
    Width = 40
    Height = 13
    Caption = 'Order ID'
  end
  object DBGrid1: TDBGrid
    Left = 368
    Top = 72
    Width = 513
    Height = 497
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
    Caption = 'Search Products'
    TabOrder = 1
    OnClick = SearchProductsButtonClick
  end
  object ProductIDEdit: TEdit
    Left = 40
    Top = 296
    Width = 145
    Height = 21
    TabOrder = 2
  end
  object StockEdit: TEdit
    Left = 200
    Top = 296
    Width = 145
    Height = 21
    TabOrder = 3
  end
  object AddAddressEdit: TEdit
    Left = 40
    Top = 200
    Width = 305
    Height = 21
    TabOrder = 4
  end
  object AddAddressButton: TButton
    Left = 184
    Top = 136
    Width = 161
    Height = 17
    Caption = 'Add Your Address'
    TabOrder = 5
    OnClick = AddAddressButtonClick
  end
  object AddressIDEdit: TEdit
    Left = 184
    Top = 168
    Width = 161
    Height = 21
    TabOrder = 6
  end
  object PurchaseProductButton: TButton
    Left = 184
    Top = 240
    Width = 161
    Height = 17
    Caption = 'Purchase Product'
    TabOrder = 7
    OnClick = PurchaseProductButtonClick
  end
  object AddressComboBox: TComboBox
    Left = 200
    Top = 352
    Width = 145
    Height = 21
    ItemHeight = 13
    TabOrder = 8
    Text = 'Address'
  end
  object OrderIDEdit: TEdit
    Left = 40
    Top = 352
    Width = 145
    Height = 21
    TabOrder = 9
  end
  object SearchOrderButton: TButton
    Left = 184
    Top = 384
    Width = 161
    Height = 17
    Caption = 'Search Your Oeder'
    TabOrder = 10
    OnClick = SearchOrderButtonClick
  end
  object PayButton: TButton
    Left = 184
    Top = 416
    Width = 161
    Height = 17
    Caption = 'Pay'
    TabOrder = 11
    OnClick = PayButtonClick
  end
  object PaymentIDEdit: TEdit
    Left = 40
    Top = 464
    Width = 145
    Height = 21
    TabOrder = 12
  end
  object OrderIDEdit2: TEdit
    Left = 200
    Top = 464
    Width = 145
    Height = 21
    TabOrder = 13
  end
  object PaymentMethodComboBox: TComboBox
    Left = 40
    Top = 496
    Width = 305
    Height = 21
    ItemHeight = 13
    TabOrder = 14
    Text = 'PaymentMethod'
    Items.Strings = (
      'Wechat'
      'AliPay')
  end
  object OrderIDEdit3: TEdit
    Left = 40
    Top = 576
    Width = 145
    Height = 21
    TabOrder = 15
  end
  object ReceiveOrderButton: TButton
    Left = 184
    Top = 528
    Width = 161
    Height = 17
    Caption = 'Receive your Order'
    TabOrder = 16
    OnClick = ReceiveOrderButtonClick
  end
  object ADOQuery1: TADOQuery
    Connection = LoginForm.ADOConnection1
    Parameters = <>
    Left = 896
    Top = 80
  end
  object DataSource1: TDataSource
    DataSet = ADOQuery1
    Left = 896
    Top = 120
  end
end
