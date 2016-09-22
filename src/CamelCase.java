import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private static String currentLetterInUpperCaseAndNextLetterInLowerCase(
											List<String> listaPalavras, String palavra, char letra){		
		if( !palavra.equals("") ){
			listaPalavras.add(palavra);	
			palavra = "";						
		}
		palavra = palavra + letra;
		
		return palavra;		
	}
	
	private static String currentLetterInLowerCaseAndNextLetterInUpperCase(
											List<String> listaPalavras, String palavra, char letra){		
		palavra = palavra + letra;	
		listaPalavras.add(palavra);	
		palavra = "";	
		
		return palavra;		
	}
	
	private static String verifyLetterUpperOrLowerCase(List<String> listaPalavras, String palavra, 
														char letra, Character proximaLetra){
		
		if( Character.isUpperCase(letra) && Character.isUpperCase(proximaLetra) ){			
			palavra = palavra + letra;					
			
		} else if( !Character.isUpperCase(letra) && Character.isUpperCase(proximaLetra) ){
			palavra = currentLetterInLowerCaseAndNextLetterInUpperCase(listaPalavras, palavra, letra);
			
		} else if( !Character.isUpperCase(letra) && !Character.isUpperCase(proximaLetra) ){
			palavra = palavra + letra;	
	
		} else if( Character.isUpperCase(letra) && !Character.isUpperCase(proximaLetra) ){			
			palavra = currentLetterInUpperCaseAndNextLetterInLowerCase(listaPalavras, palavra, letra);
			
		}
		
		return palavra;
	}
	
	private static String verifyLetterCurrentAndNext(List<String> listaPalavras, String palavra, 
														char letra, Character proximaLetra) {
		
		if( ( !Character.isDigit(letra) && Character.isDigit(proximaLetra) ) || ( Character.isDigit(letra) && !Character.isDigit(proximaLetra) ) ){			
			palavra = palavra + letra;	
			listaPalavras.add(palavra);	
			palavra = "";
					
		} else if( Character.isDigit(letra) && Character.isDigit(proximaLetra) ){			
			palavra = palavra + letra;	
			
		} else {			
			palavra = verifyLetterUpperOrLowerCase(listaPalavras, palavra, letra, proximaLetra);

		}

		return palavra;
	}
	
	private static void travelLetters(List<String> listaPalavras, char[] letras) {
		String palavra = "";
		
		for(int i=0; i<letras.length; i++){			
			char letra = letras[i];
			
			Character proximaLetra = ( (i+1) < letras.length) ? letras[i+1] : Character.MIN_VALUE;
			
			if( isFinalArray(i, letras) ){
				palavra = palavra + letra;
				listaPalavras.add(palavra);	
				break;
			}
			
			palavra = verifyLetterCurrentAndNext(listaPalavras, palavra, letra, proximaLetra);		
		}
	}

	private static List<String> verifyIfExistsWordUpperCase(List<String> listWords){		
		String[] arrayPalavras = Arrays.copyOf(listWords.toArray(), listWords.size(), String[].class);
		
		for(int i=0; i<arrayPalavras.length; i++){
			if( !isUpperCase(arrayPalavras[i]) )
				arrayPalavras[i] = arrayPalavras[i].toLowerCase();	
		}
		
		listWords = new ArrayList<String>(Arrays.asList(arrayPalavras));
		
		return listWords;		
	}
	
	private static boolean isThereSpecialCharacter(String original) {
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(original);

		return m.find();
	}
	
	public static List<String> converterCamelCase(String original) throws Exception{
		List<String> listaPalavras = new ArrayList<String>();		
		
		char[] letras = original.toCharArray();	
		
		if( isThereSpecialCharacter(original) )
			throw new CamelCaseComCaracterEspecialException("Inválido -> caracteres especiais não são permitidos, somente letras e números");			
		
		if( Character.isDigit(letras[0]) )
			throw new CamelCaseComecaComNumeroException("Inválido -> não deve começar com números");
		
		if( Character.isUpperCase(letras[0]) && !Character.isUpperCase(letras[1]) )
			letras[0] = Character.toLowerCase(letras[0]);
			
		travelLetters(listaPalavras, letras);

		return verifyIfExistsWordUpperCase(listaPalavras);		
	}

}