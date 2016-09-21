import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Tests {

	@Test
	public void palavraSimplesTodaMinuscula() {		
		List<String> listaPalavras = CamelCase.converterCamelCase("nome");
		
		assertEquals(1, listaPalavras.size());		
		assertEquals("nome", listaPalavras.get(0));
	}
	
	@Test
	public void palavraSimplesPrimeiraMaiuscula() {		
		List<String> listaPalavras = CamelCase.converterCamelCase("Nome");
		
		assertEquals(1, listaPalavras.size());		
		assertEquals("nome", listaPalavras.get(0));
	}
	
	@Test
	public void palavraCompostaSegundaMaiuscula() {		
		List<String> listaPalavras = CamelCase.converterCamelCase("nomeComposto");
		
		assertEquals(2, listaPalavras.size());	
		
		List<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("nome");
		listaEsperada.add("composto");
		
		assertEquals(listaEsperada, listaPalavras);
	}

}
