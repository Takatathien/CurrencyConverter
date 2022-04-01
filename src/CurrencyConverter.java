import java.util.HashMap;
import java.util.Scanner;

/*
 * Thien Trinh
 * 
 * This is a simple currency converter program with API.
 */
public class CurrencyConverter {

	public static void main(String[] args) {
		
		HashMap<Integer, String> currencyCodes = new HashMap<Integer, String>();
		
		// Add currency codes.
		currencyCodes.put(1, "USD");
		currencyCodes.put(2, "CAD");
		currencyCodes.put(3, "EUR");
		currencyCodes.put(4, "HKD");
		currencyCodes.put(5, "INR");

		String fromCode, toCode;
		double amount;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to the currency converter!");
		
		System.out.println("Currency converting FROM?");
		System.out.println("1:USD (US Dollar)\t 2:CAD (Canadian Dollar)\t 3:EUR (Euro)\t 4:HKD (Hong Kong Dollar)\t 5:INR (Indian Rupee)");
		fromCode = currencyCodes.get(sc.nextInt());
		
		System.out.println("Currency converting TO?");
		System.out.println("1:USD (US Dollar)\t 2:CAD (Canadian Dollar)\t 3:EUR (Euro)\t 4:HKD (Hong Kong Dollar)\t 5:INR (Indian Rupee)");
		toCode = currencyCodes.get(sc.nextInt());
		
		System.out.println("Amount you with to convert?");
		amount = sc.nextFloat();
		
		//sendHttpGETRequest(fromCode, toCode, amount);
		
		System.out.println("Thank you for using the currency converter!");
	}
	
	private static void sendHttpGETRequest(String fromCode, String toCode, double amoung) {
		
		// String GET_URL = ""
	}

}
