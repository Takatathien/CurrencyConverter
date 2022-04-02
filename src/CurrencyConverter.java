import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

/*
 * Thien Trinh
 * 
 * This is a simple currency converter program with API.
 */
public class CurrencyConverter {

	public static void main(String[] args) throws IOException {
		
		boolean running = true;
		do {
			HashMap<Integer, String> currencyCodes = new HashMap<Integer, String>();
			
			// Add currency codes.
			currencyCodes.put(1, "USD");
			currencyCodes.put(2, "CAD");
			currencyCodes.put(3, "EUR");
			currencyCodes.put(4, "HKD");
			currencyCodes.put(5, "INR");
	
			int from, to;
			String fromCode, toCode;
			double amount;
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Welcome to the currency converter!");
			
			System.out.println("Currency converting FROM?");
			System.out.println("1:USD (US Dollar)\t 2:CAD (Canadian Dollar)\t 3:EUR (Euro)\t 4:HKD (Hong Kong Dollar)\t 5:INR (Indian Rupee)");
			from = sc.nextInt();
			while (!currencyCodes.containsKey(from)) {
				System.out.println("Please select a valid currency code.");
				System.out.println("1:USD (US Dollar)\t 2:CAD (Canadian Dollar)\t 3:EUR (Euro)\t 4:HKD (Hong Kong Dollar)\t 5:INR (Indian Rupee)");
				from = sc.nextInt();
			}
			fromCode = currencyCodes.get(from);
			
			System.out.println("Currency converting TO?");
			System.out.println("1:USD (US Dollar)\t 2:CAD (Canadian Dollar)\t 3:EUR (Euro)\t 4:HKD (Hong Kong Dollar)\t 5:INR (Indian Rupee)");
			to = sc.nextInt();
			while (!currencyCodes.containsKey(to)) {
				System.out.println("Please select a valid currency code.");
				System.out.println("1:USD (US Dollar)\t 2:CAD (Canadian Dollar)\t 3:EUR (Euro)\t 4:HKD (Hong Kong Dollar)\t 5:INR (Indian Rupee)");
				to = sc.nextInt();
			}
			toCode = currencyCodes.get(to);
			
			System.out.println("Amount you with to convert?");
			amount = sc.nextFloat();
			
			sendHttpGETRequest(fromCode, toCode, amount);
			System.out.println("Would you like to make another conversion?");
			System.out.println("1: Yes\t Any other integer: No");
			if (sc.nextInt() != 1) {
				running = false;
				sc.close();
			}
		} while (running);
		
		System.out.println("Thank you for using the currency converter!");
	}
	
	private static void sendHttpGETRequest(String fromCode, String toCode, double amount) throws IOException {
		
		DecimalFormat f = new DecimalFormat("00.00");
		String GET_URL = "https://open.er-api.com/v6/latest/" + fromCode;
		URL url = new URL(GET_URL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		int responseCode = httpURLConnection.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // Success.
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			} in.close();
			
			JSONObject obj = new JSONObject(response.toString());
			Double exchangeRate = obj.getJSONObject("rates").getDouble(toCode);
			System.out.println(exchangeRate); // Debugging.
			System.out.println();
			System.out.println(f.format(amount) + fromCode + " = " + f.format(amount * exchangeRate) + toCode);
		} else {
			System.out.println("GET request failed!");
		}
		
	}

}
