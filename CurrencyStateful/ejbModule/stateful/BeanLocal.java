package stateful;

import java.util.List;

import javax.ejb.Local;

@Local
public interface BeanLocal {

	public void AddToCart(int oderId);
	
	public List<Integer> GetCart();
}
