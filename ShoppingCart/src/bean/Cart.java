package bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class Cart implements CartLocal {

    public Cart() {
    	OrderIds = new ArrayList<Integer>();
    }
    
   List<Integer> OrderIds;  
	


	@Override
	public void AddToCart(int oderId) {
		OrderIds.add(oderId);
	}

	@Override
	public List<Integer> GetCart() {
		return OrderIds;
	}

}
