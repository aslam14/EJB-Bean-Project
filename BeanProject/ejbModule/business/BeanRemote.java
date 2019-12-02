package business;

import javax.ejb.Remote;

@Remote
public interface BeanRemote {
	
	public double GetResultantValue(ConverterModel converterModel);
	
}
