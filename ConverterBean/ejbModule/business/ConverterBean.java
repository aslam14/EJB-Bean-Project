package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import business.DatabaseInitializer;

/**
 * Session Bean implementation class ConverterBean
 */
@Stateless
@LocalBean
public class ConverterBean implements ConveterBeanLocalInterface {

    /**
     * Default constructor. 
     */
    public ConverterBean() {
        // TODO Auto-generated constructor stub
    }

	public void SaveData(ConverterModel model) {
		System.out.println(model);
		try {
			Connection connection= DatabaseInitializer.GetConnection();
			
			PreparedStatement statement = connection.prepareStatement("Insert INTO currency_converter.currency (username, input, target, result) values (?,?,?,?)"); 
			statement.setString(1, model.getUsername());
			statement.setDouble(2, model.getInputCurrency());
			statement.setString(3, model.getTargetCurrency());
			statement.setDouble(4,  model.getResultantCurrency() );
	       
	        statement.executeUpdate(); 	       
	  
            // Close all the connections 
            statement.close(); 
            connection.close();
			
		} catch (Exception e) {
		System.out.println(e);
			return;
		}		
		
		
	}

	
	public ArrayList<ConverterModel> GetData(String username) {
		
		ArrayList<ConverterModel> listOfUserData = new ArrayList<ConverterModel>();
		
		Connection connection= DatabaseInitializer.GetConnection();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM currency WHERE username='"+username+"'");
		
			while (rs.next())
			{
				ConverterModel converterModel = new ConverterModel();
				
				converterModel.setInputCurrency(rs.getDouble("input"));
				converterModel.setUsername(rs.getString("username"));
				converterModel.setTargetCurrency(rs.getString("target"));
				converterModel.setResultantCurrency( rs.getDouble("result"));
				listOfUserData.add(converterModel);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return listOfUserData;
		
	}
}
