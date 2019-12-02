package business;

import javax.ejb.Local;

@Local
public interface BeanLocal {
	
	public double GetResultantValue(ConverterModel converterModel);
	
}
