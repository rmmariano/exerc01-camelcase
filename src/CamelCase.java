import java.util.ArrayList;
import java.util.List;

public class CamelCase {
	
	public static List<String> converterCamelCase(String original){
		List<String> listaPalavras = new ArrayList<String>();
		
		listaPalavras.add(original.toLowerCase());		
		
		return listaPalavras;		
	}

}