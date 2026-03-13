package ex2.ocp1;

public class ChartDisplay {

	public void show(String type){
		if(type.equalsIgnoreCase("pie")){
			PieChart chart = new PieChart();
			chart.display();
		}else if(type.equalsIgnoreCase("bar")){
			BarChart chart = new BarChart();
			chart.display();
		}else if(type.equalsIgnoreCase("line")){
			LineChart chart = new LineChart();
			chart.display();
		}	
	}
}
