package util;
import java.text.NumberFormat;

public class MyFormat {

	public String  moneyFormat(int price) {
		NumberFormat comFormat = NumberFormat.getNumberInstance();
		String str = "\\"+comFormat.format(price);
		return str;
	}
}
