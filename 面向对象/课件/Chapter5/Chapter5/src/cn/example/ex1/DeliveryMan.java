package cn.example.ex1;

public class DeliveryMan extends Person {
	//新增属性
	private String[] deliveryArea;

	//新增方法
	public void setDeliveryArea(String[] deliveryArea) {
		this.deliveryArea = deliveryArea;
	}
	public String getInfo(){//拼写快递员信息字符串
		String str = getId()+","+getName()+"\n配送范围:";
		int i;
		for(i=0; i<deliveryArea.length-1; i++){//除最后一个外，都有一个逗号
			str+=deliveryArea[i]+",";
		}
		return str+deliveryArea[i];
	}

	public boolean isArrived(String area){//检查area是否在配送范围
		for(String d: deliveryArea){
			if(d.equalsIgnoreCase(area)){
				return true;
			}
		}
		return false;
	}
	public String toString(){
		//System.out.println(super.toString());
		String str = getId()+","+getName()+"\n配送范围:";
		int i;
		for(i=0; i<deliveryArea.length-1; i++){//除最后一个外，都有一个逗号
			str+=deliveryArea[i]+",";
		}
		return str+deliveryArea[i];
	}
}