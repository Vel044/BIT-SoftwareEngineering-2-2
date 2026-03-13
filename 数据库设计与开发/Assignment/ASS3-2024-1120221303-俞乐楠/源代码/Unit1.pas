unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Buttons, DB, ADODB;

type
  TLoginForm = class(TForm)
    ADOConnection1: TADOConnection;
    ADOQuery1: TADOQuery;
    DataSource1: TDataSource;
    UserTypeComboBox: TComboBox;
    UserIDEdit: TEdit;
    PasswordEdit: TEdit;
    LoginButton: TButton;
    RegisterButton: TButton;
    Label1: TLabel;
    Label2: TLabel;

    procedure LoginButtonClick(Sender: TObject);
    procedure RegisterButtonClick(Sender: TObject);

  private
    { Private declarations }
    procedure AuthenticateUser(const UserID, Password, UserType: string);
  public
    { Public declarations }
  end;

var
  LoginForm: TLoginForm;

implementation

uses
  Unit2,
  Unit3;

{$R *.dfm}



//注册
procedure TLoginForm.RegisterButtonClick(Sender: TObject);
var
  Query: TADOQuery;
  UserType, UserID, Password: string;
begin
  UserType := UserTypeComboBox.Text;
  UserID := UserIDEdit.Text;
  Password := PasswordEdit.Text;

  // 确保输入框中有内容
  if (UserID = '') or (Password = '') or (UserType = '') then
  begin
    ShowMessage('Please fill in all fields.');
    Exit;
  end;

  Query := TADOQuery.Create(nil);
  try
    Query.Connection := ADOConnection1;

    // 检查ID是否已存在
    if UserType = 'Consumer' then
      Query.SQL.Text := Format('SELECT COUNT(*) AS UserCount FROM Consumers WHERE ConsumerID = %s', [QuotedStr(UserID)])
    else if UserType = 'Merchant' then
      Query.SQL.Text := Format('SELECT COUNT(*) AS UserCount FROM Merchants WHERE MerchantID = %s', [QuotedStr(UserID)]);

    Query.Open;

    if Query.FieldByName('UserCount').AsInteger > 0 then
    begin
      ShowMessage('ID already exists.');
      Exit;
    end;
    Query.Close;

    // 插入新用户到数据库
    if UserType = 'Consumer' then
      Query.SQL.Text := Format('INSERT INTO Consumers (ConsumerID, Password) VALUES (%s, %s)', [QuotedStr(UserID), QuotedStr(Password)])
    else if UserType = 'Merchant' then
      Query.SQL.Text := Format('INSERT INTO Merchants (MerchantID, Password) VALUES (%s, %s)', [QuotedStr(UserID), QuotedStr(Password)]);

    Query.ExecSQL;

    ShowMessage('Registration successful.');
  finally
    Query.Free;
  end;
end;


procedure TLoginForm.AuthenticateUser(const UserID, Password, UserType: string);
begin
  ADOQuery1.Close;

  if UserType = 'Consumer' then
  begin
    ADOQuery1.SQL.Text := Format('SELECT * FROM Consumers WHERE ConsumerID = %s AND Password = %s', [QuotedStr(UserID), QuotedStr(Password)]);
    
    ADOQuery1.Open;

    if not ADOQuery1.IsEmpty then
    begin
      // 登录成功，显示主界面
      ShowMessage('Login successful.');
      // 在这里你可以打开主界面并隐藏登录界面
      // 显示消费者菜单
      ConsumerMenuForm := TConsumerMenuForm.Create(nil, UserID);
      ConsumerMenuForm.Show;
      LoginForm.Hide; // 关闭登录窗口
    end
    else
      ShowMessage('Invalid UserID or Password.');

  end
  else if UserType = 'Merchant' then
  begin
    ADOQuery1.SQL.Text := Format('SELECT * FROM Merchants WHERE MerchantID = %s AND Password = %s', [QuotedStr(UserID), QuotedStr(Password)]);
    
    ADOQuery1.Open;

    if not ADOQuery1.IsEmpty then
    begin
      // 登录成功，显示主界面
      ShowMessage('Login successful.');
      // 在这里你可以打开主界面并隐藏登录界面
      // 显示商家菜单
      MerchantMenuForm := TMerchantMenuForm.Create(nil, UserID);
      MerchantMenuForm.Show;
      LoginForm.Hide; // 关闭登录窗口
    end
    else
      ShowMessage('Invalid UserID or Password.');
  end

end;

procedure TLoginForm.LoginButtonClick(Sender: TObject);
begin
  AuthenticateUser(UserIDEdit.Text, PasswordEdit.Text, UserTypeComboBox.Text);
end;


end.
