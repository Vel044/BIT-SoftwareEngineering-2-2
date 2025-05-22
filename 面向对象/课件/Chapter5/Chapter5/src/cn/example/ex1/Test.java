package cn.example.ex1;

/**
 * 快递员继承Person
 */
public class Test {
	public static void main(String[] args) {
		DeliveryMan ds = new DeliveryMan();
		//从父类继承的方法
		ds.setId("007");
		ds.setName("Bang");

		//子类新增方法
		ds.setDeliveryArea(new String[]{"南锣鼓巷","烟袋斜街","雨儿胡同","帽儿胡同","黑芝麻胡同"});
//		System.out.println("快递员信息:"+ds.getInfo());
		System.out.println("快递员信息:"+ds);
		if(ds.isArrived("方家胡同")){
			System.out.println("欢迎光临，可以配送至方家胡同");
		}else{
			System.out.println("对不起，不能配送至方家胡同");
		}
	}
}