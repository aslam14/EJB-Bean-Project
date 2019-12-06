package mdb;

import java.io.Serializable;

public class ConverterModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String Username;
	double InputCurrerncy;
	double ResultantCurrency;
	String TargetCurrencyType;
	
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public double getInputCurrerncy() {
		return InputCurrerncy;
	}
	public void setInputCurrerncy(double inputCurrerncy) {
		InputCurrerncy = inputCurrerncy;
	}
	public double getResultantCurrency() {
		return ResultantCurrency;
	}
	public void setResultantCurrency(double resultantCurrency) {
		ResultantCurrency = resultantCurrency;
	}
	public String getTargetCurrencyType() {
		return TargetCurrencyType;
	}
	public void setTargetCurrencyType(String targetCurrencyType) {
		TargetCurrencyType = targetCurrencyType;
	}
}
