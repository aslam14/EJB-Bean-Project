package currency;

import java.io.IOException;
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
		response.getWriter().println("Hello ");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			ConverterModel converterModel = new ConverterModel();
			converterModel.setInputCurrerncy(Double.parseDouble(request.getParameter("inputCurrency")));
			converterModel.setUsername(request.getParameter("username"));
			converterModel.setTargetCurrencyType(request.getParameter("targetCurrency"));
			
			double resultantCurrency = getConverterdValue(converterModel.getTargetCurrencyType(), converterModel.getInputCurrerncy());
			converterModel.setResultantCurrency(resultantCurrency);

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
			return 0.0;
		}
	}
}
