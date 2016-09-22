import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Tests {

	@Test
	public void palavraSimplesTodaMinuscula() throws Exception {		
		List<String> listaPalavras = CamelCase.converterCamelCase("nome");
		
		assertEquals(1, listaPalavras.size());		
		assertEquals("nome", listaPalavras.get(0));
	}
	
	@Test
	public void palavraSimplesPrimeiraMaiuscula() throws Exception {		
		List<String> listaPalavras = CamelCase.converterCamelCase("Nome");
		
		assertEquals(1, listaPalavras.size());		
		assertEquals("nome", listaPalavras.get(0));
	}
	
	@Test
	public void palavraCompostaSegundaMaiuscula() throws Exception {		
		List<String> listaPalavras = CamelCase.converterCamelCase("nomeComposto");
				
		assertEquals(2, listaPalavras.size());	
		
		List<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("nome");
		listaEsperada.add("composto");
		
		assertEquals(listaEsperada, listaPalavras);
	}
	
	@Test
	public void palavraCompostaTodosMaiusculos() throws Exception {		
		List<String> listaPalavras = CamelCase.converterCamelCase("NomeComposto");
		
		assertEquals(2, listaPalavras.size());	
		
		List<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("nome");
		listaEsperada.add("composto");
		
		assertEquals(listaEsperada, listaPalavras);
	}
	
	@Test
	public void palavraSimplesEmUpperCase() throws Exception {		
		List<String> listaPalavras = CamelCase.converterCamelCase("CPF");
		
		assertEquals(1, listaPalavras.size());	
		assertEquals("CPF", listaPalavras.get(0));
	}
	
	
	@Test
	public void palavraCompostaSegundaTodaMaiuscula() throws Exception {		
		List<String> listaPalavras = CamelCase.converterCamelCase("numeroCPF");
		
		assertEquals(2, listaPalavras.size());	
		
		List<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("numero");
		listaEsperada.add("CPF");
		
		assertEquals(listaEsperada, listaPalavras);
	}
	
	@Test
	public void palavraCompostaDoMeioTodaMaiuscula() throws Exception {		
		List<String> listaPalavras = CamelCase.converterCamelCase("numeroCPFContribuinte");
		
		assertEquals(3, listaPalavras.size());	
		
		List<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("numero");
		listaEsperada.add("CPF");
		listaEsperada.add("contribuinte");
		
		assertEquals(listaEsperada, listaPalavras);
	}
	
	
	@Test
	public void palavraCompostaComMeioNumero() throws Exception {		
		List<String> listaPalavras = CamelCase.converterCamelCase("recupera10Primeiros");
		
		assertEquals(3, listaPalavras.size());	
		
		List<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("recupera");
		listaEsperada.add("10");
		listaEsperada.add("primeiros");
		
		assertEquals(listaEsperada, listaPalavras);
	}

	@Test(expected=CamelCaseComecaComNumeroException.class)
	public void palavraCompostaComecaComNumero() throws Exception {		
		CamelCase.converterCamelCase("10Primeiros");
	}
	
	@Test(expected=CamelCaseComCaracterEspecialException.class)
	public void palavraCompostaComCaracterEspecial() throws Exception {		
		CamelCase.converterCamelCase("nome#Composto");
	}

}
