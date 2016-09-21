import java.util.ArrayList;
import java.util.List;

public class CamelCase {
	
	public static List<String> converterCamelCase(String original){
		List<String> listaPalavras = new ArrayList<String>();		
		
		char[] letras = original.toCharArray();
				
		String palavra = "";

		for(int i=0; i<letras.length; i++){			
			char letra = letras[i];
			
			if( Character.isUpperCase(letra) && !palavra.isEmpty()){
				listaPalavras.add(palavra.toLowerCase());		
				palavra = "";
			}

			palavra = palavra + letra;						
		}	
		
		listaPalavras.add(palavra.toLowerCase());
		
		return listaPalavras;		
	}

}