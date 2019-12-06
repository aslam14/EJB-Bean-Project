package statefull;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CartLocal;

@WebServlet("/cart")
public class Currency extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CartLocal shoppingCart;

	public Currency() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>Currency Converter</title></head>");
			out.println("<body><center>");
			out.println("<center><h1>Currency Converter Form (WS2019)</h1>");
			out.print("<table border='1'><tr><th>oderID</th>");

			List<Integer> orders = shoppingCart.GetCart();

			for (Integer order : orders) {

				out.print("<tr><td>");
				out.println(order);
				out.print("</td>");
			}

			out.print("</table>");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			int orderNumber = Integer.parseInt(request.getParameter("orderNumber"));
			shoppingCart.AddToCart(orderNumber);
			doGet(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
