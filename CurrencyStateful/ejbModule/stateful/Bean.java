package stateful;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;


@Stateful
public class Bean implements BeanRemote, BeanLocal {

	List<Integer> OrderIds;  
	
    public Bean() {
    	OrderIds = new ArrayList<Integer>();
    }

	@Override
	public void AddToCart(int oderId) {
		OrderIds.add(oderId);
	}

	@Override
	public List<Integer> GetCart() {
		return OrderIds;
	}

}
