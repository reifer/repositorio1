package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastrarEmpresa {
	public static Empresa empresa;
	public static EmpresaDAO empresaDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/*@Before
	public void CT01UC01FBCadastraSucesso(){
		assertEquals(1, empresaDAO.adiciona(empresa));;
		
	}*/
	
	@Test
	public void CT01UC02FBExcluiSucesso(){
		assertEquals(1, empresaDAO.exclui(empresa.getCnpj()));;
		
	}
	
	@Test
	public void CT01UC02FBCadastraErrado(){
		empresa.setCnpj("");
		empresa.setNomeDaEmpresa("");
		empresa.setNomeFantasia("");
		empresa.setEndereco("");
		empresa.setTelefone("");		
	}
}