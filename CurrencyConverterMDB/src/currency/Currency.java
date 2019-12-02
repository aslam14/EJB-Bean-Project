package currency;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mdb.ConverterModel;

@WebServlet("/converter")
public class Currency extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		try {

			ConverterModel converterModel = new ConverterModel();
			converterModel.setInputCurrerncy(Double.parseDouble(request.getParameter("inputCurrency")));
			converterModel.setUsername(request.getParameter("username"));
			converterModel.setTargetCurrencyType(request.getParameter("targetCurrency"));
			converterModel.setResultantCurrency(
					getConverterdValue(converterModel.getTargetCurrencyType(), converterModel.getInputCurrerncy()));

			MessageBeanManager beanManager = new MessageBeanManager();
			beanManager.sendMessage(converterModel);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private double getConverterdValue(String targetValue, double inputCurrency) {
		switch (targetValue) {
		case "Dollar":
			return inputCurrency * 1.10;
		case "Pound":
			return inputCurrency * 0.86;
		case "Yen":
			return inputCurrency * 120.39;
		case "Pkr":
			return inputCurrency * 173.5;
		default:
			return 0;
		}
	}
}
