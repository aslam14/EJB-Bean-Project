package business;

import java.io.Serializable;

public class ConverterModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private double InputCurrency;
	private String TargetCurrency;
	private String Username;
	
	
	public double getInputCurrency() {
		return InputCurrency;
	}
	public void setInputCurrency(double inputCurrency) {
		InputCurrency = inputCurrency;
	}
	public String getTargetCurrency() {
		return TargetCurrency;
	}
	public void setTargetCurrency(String targetCurrency) {
		TargetCurrency = targetCurrency;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	
	
	 
	
}
