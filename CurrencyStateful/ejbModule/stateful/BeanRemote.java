package stateful;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface BeanRemote {
	public void AddToCart(int oderId);
	public List<Integer> GetCart();
}
