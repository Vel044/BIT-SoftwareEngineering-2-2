package ex5.v2;

public class Computer {
	private IMobileStorage  usbDriver;
		
	public IMobileStorage getUsbDriver() {
		return usbDriver;
	}

	public void setUsbDriver(IMobileStorage usbDriver) {
		this.usbDriver = usbDriver;
	}
	
	public Computer(IMobileStorage usbDriver) {
		this.usbDriver = usbDriver;
	}

	public Computer() {

	}

	public void readData(){
		
		usbDriver.read();
	}
	
	public void wrietData(){
		usbDriver.write();
	}
}
