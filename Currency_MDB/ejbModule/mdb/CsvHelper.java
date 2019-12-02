package mdb;

import java.io.IOException;
import java.io.Writer;

public class CsvHelper {

	private static final char DEFAULT_SEPARATOR = ',';

	public static void WriteLine(Writer writer, ConverterModel converterModel) throws IOException {

		writer.append(converterModel.getUsername());
		writer.append(DEFAULT_SEPARATOR);
		writer.append(String.valueOf(converterModel.getInputCurrerncy()));
		writer.append(DEFAULT_SEPARATOR);
		writer.append(converterModel.getTargetCurrencyType());
		writer.append(DEFAULT_SEPARATOR);
		writer.append(String.valueOf(converterModel.getResultantCurrency()));
		writer.append(DEFAULT_SEPARATOR);

		writer.append("\n");
		writer.flush();
		writer.close();
	}

}
