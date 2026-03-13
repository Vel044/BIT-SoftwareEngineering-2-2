object LoginForm: TLoginForm
  Left = 1006
  Top = 232
  Width = 462
  Height = 361
  Caption = 'Login'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 80
    Top = 104
    Width = 33
    Height = 13
    Caption = 'UserID'
  end
  object Label2: TLabel
    Left = 80
    Top = 160
    Width = 46
    Height = 13
    Caption = 'Password'
  end
  object UserIDEdit: TEdit
    Left = 168
    Top = 104
    Width = 145
    Height = 21
    TabOrder = 0
  end
  object UserTypeComboBox: TComboBox
    Left = 168
    Top = 48
    Width = 145
    Height = 21
    ItemHeight = 13
    TabOrder = 1
    Text = 'UserType'
    Items.Strings = (
      'Consumer'
      'Merchant')
  end
  object PasswordEdit: TEdit
    Left = 168
    Top = 160
    Width = 145
    Height = 21
    TabOrder = 2
  end
  object LoginButton: TButton
    Left = 120
    Top = 232
    Width = 81
    Height = 33
    Caption = 'Login'
    TabOrder = 3
    OnClick = LoginButtonClick
  end
  object RegisterButton: TButton
    Left = 264
    Top = 232
    Width = 81
    Height = 33
    Caption = 'Register'
    TabOrder = 4
    OnClick = RegisterButtonClick
  end
  object ADOConnection1: TADOConnection
    Connected = True
    ConnectionString = 
      'Provider=MSDASQL.1;Password=vel@1234;Persist Security Info=True;' +
      'User ID=my_root;Data Source=gauss'
    LoginPrompt = False
    Left = 408
    Top = 32
  end
  object ADOQuery1: TADOQuery
    Connection = ADOConnection1
    Parameters = <>
    Left = 408
    Top = 88
  end
  object DataSource1: TDataSource
    DataSet = ADOQuery1
    Left = 408
    Top = 152
  end
end
