package ex2.cop2;

public class ChartDisplay {
	private AbstractChart chart;

	public void setChart(AbstractChart chart) {
		this.chart = chart;
	}

	public void show(){
		chart.display();
	}
}
