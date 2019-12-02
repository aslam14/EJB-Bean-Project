package business;
import javax.ejb.Stateless;

@Stateless(name="CurrencyConv")
public class Bean implements BeanLocal, BeanRemote {

	
    public Bean() {
    }
    
    public double GetResultantValue(ConverterModel model) {

    	double resultantValue = getConverterdValue(model.getTargetCurrency(), model.getInputCurrency());
    
    	return resultantValue;
	}

	
	
	private double getConverterdValue(String targetValue, double inputCurrency) {
		switch (targetValue) {
		  case "Dollar":
		    return inputCurrency * 1.10;
		  case "Pound":
			    return inputCurrency * 0.86;
		  case "Yen":
			    return inputCurrency * 120.39;
		  case "Pkr":
			    return inputCurrency * 173.5;
		  default:
			  return 0;
	}
	}

}
