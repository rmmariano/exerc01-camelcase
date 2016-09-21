import java.util.ArrayList;
import java.util.List;

public class CamelCase {
	
	private static boolean isFinalArray(int i, char[] arrayChars){		
		return ( (i+1) >= arrayChars.length );		
	}
	
	private static boolean isUpperCase(String word){		
		char[] letters = word.toCharArray();
		
		for(int i=0; i<letters.length; i++){
			if( Character.isLowerCase( letters[i] ) )
				return false;		
		}
		
		return true;		
	}
	
	private static String travelLetters(List<String> listaPalavras, char[] letras) {
		String palavra = "";
		
		for(int i=0; i<letras.length; i++){			
			char letra = letras[i];
			
			Character proximaLetra = ( (i+1) < letras.length) ? letras[i+1] : Character.MIN_VALUE;

			if( Character.isUpperCase(letra) && !palavra.isEmpty() 
					&& !isFinalArray(i, letras) && !Character.isUpperCase(proximaLetra) ){
				listaPalavras.add(palavra.toLowerCase());		
				palavra = "";
			}

			palavra = palavra + letra;						
		}
		return palavra;
	}
	
	public static List<String> converterCamelCase(String original){
		List<String> listaPalavras = new ArrayList<String>();		
		
		char[] letras = original.toCharArray();
				
		String palavra = travelLetters(listaPalavras, letras);	
		
		if(isUpperCase(palavra))
			listaPalavras.add(palavra);
		else		
			listaPalavras.add(palavra.toLowerCase());
		
		return listaPalavras;		
	}

	

}