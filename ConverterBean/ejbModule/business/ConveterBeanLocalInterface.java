package business;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface ConveterBeanLocalInterface {

	
	public void SaveData(ConverterModel model);
	
	public ArrayList<ConverterModel> GetData(String username);
}
