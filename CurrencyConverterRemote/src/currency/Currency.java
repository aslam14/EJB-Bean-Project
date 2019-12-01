package currency;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.BeanRemote;
import business.ConverterModel;

@WebServlet("/converter")
public class Currency extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(lookup="java:global/BeanProject/CurrencyConv!business.BeanRemote")
	BeanRemote converterBean;

	public Currency() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String name = request.getParameter("username");
			out.println("<html>");
			out.println("<head><title>Currency Converter</title></head>");
			out.println("<body><center>");
			out.println("<center><h1>Currency Converter Form (WS2019)</h1>");
			out.print("<table border='1'><tr><th>ID</th><th>username</th><th>input</th><th>target</th><th>result</th>");

			Connection connection = DatabaseInitializer.GetConnection();

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM currency WHERE username='" + name + "'");
			while (rs.next()) {
				int id = rs.getInt("id");
				Double input = rs.getDouble("input");
				String username = rs.getString("username");
				String target = rs.getString("target");
				Double result = rs.getDouble("result");

				out.print("<tr><td>");
				out.println(id);
				out.print("</td>");
				out.print("<td>");
				out.println(username);
				out.print("</td>");
				out.print("<td>");
				out.println(input);
				out.print("</td>");
				out.print("<td>");
				out.println(target);
				out.print("</td>");
				out.print("<td>");
				out.println(result);
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
		// TODO Auto-generated method stub

		try {

			ConverterModel converterModel = new ConverterModel();
			converterModel.setInputCurrency(Double.parseDouble(request.getParameter("inputCurrency")));
			converterModel.setUsername(request.getParameter("username"));
			converterModel.setTargetCurrency(request.getParameter("targetCurrency"));

			double result = converterBean.GetResultantValue(converterModel);

			Connection connection = DatabaseInitializer.GetConnection();

			PreparedStatement statement = connection.prepareStatement(
					"Insert INTO currency_converter.currency (username, input, target, result) values (?,?,?,?)");
			statement.setString(1, converterModel.getUsername());
			statement.setDouble(2, converterModel.getInputCurrency());
			statement.setString(3, converterModel.getTargetCurrency());
			statement.setDouble(4, result);

			statement.executeUpdate();

			// Close all the connections
			statement.close();
			connection.close();

			response.getWriter().println(result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
