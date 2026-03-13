package ex3;

public class UserDaoForArray implements UserDao{
	private User[] data;
	private int count=0;
	
	public UserDaoForArray(){
		data=new User[10];
	}
	public boolean addUser(User user) {
		if(count==data.length){ //�û������Ѵﵽ
			return false;
		}
		
		//�����û��Ƿ��Ѵ���
		for(int i=0; i<count; i++){
			if(data[i].getUserName().equals(user.getUserName())){
				return false;
			}
		}
		
		//������û�
		data[count]=user;
		count++;
		return true;
	}

	
	public User getUser(String userName, String password) {
		for(int i=0;i<count;i++){
			if(data[i].getUserName().equals(userName) && data[i].getPassword().equals(password) ){
				return new User(userName, password);
			}
		}
		return null;
	}
}
