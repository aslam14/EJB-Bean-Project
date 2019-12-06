package bean;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CartLocal {
	public void AddToCart(int oderId);
	
	public List<Integer> GetCart();
}
