program Project2;

uses
  Forms,
  Unit1 in 'Unit1.pas' {LoginForm},
  Unit2 in 'Unit2.pas' {ConsumerMenuForm},
  Unit3 in 'Unit3.pas' {MerchantMenuForm};

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TLoginForm, LoginForm);
  Application.CreateForm(TConsumerMenuForm, ConsumerMenuForm);
  Application.CreateForm(TMerchantMenuForm, MerchantMenuForm);
  Application.Run;
end.
