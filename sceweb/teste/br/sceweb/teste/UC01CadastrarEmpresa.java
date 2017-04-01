package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

/**
 * @author Lab103
 * @version $Revision: 1.0 $
 */
public class UC01CadastrarEmpresa {
	/**
	 * Field empresa.
	 */
	public static Empresa empresa;
	/**
	 * Field empresaDAO.
	 */
	public static EmpresaDAO empresaDAO;

	/**
	 * Method setUpBeforeClass.
	
	 * @throws Exception */
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

	/**
	 * Method tearDownAfterClass.
	
	 * @throws Exception */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Method CT01UC01FBCadastraSucesso.
	 */
	@Before
	public void CT01UC01FBCadastraSucesso() {
		assertEquals(1, empresaDAO.adiciona(empresa));

	}

	/**
	 * Method CT01UC02FBExcluiSucesso.
	 */
	@Test
	public void CT01UC02FBExcluiSucesso() {
		assertEquals(1, empresaDAO.exclui(empresa.getCnpj()));

	}

	/**
	 * Method CT01UC02FBCadastraErrado.
	 */
	@Test
	public void CT01UC02FBCadastraErrado() {
		empresa.setCnpj("89424232000180");
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
	}

	/**
	 * Method CT01UC01ACadastra_cnpj_invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void CT01UC01ACadastra_cnpj_invalido() {
		empresa.setCnpj("");
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setNomeFantasia("empresa y");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
	}

	/**
	 * Method CT01UC01ACadastra_nome_empresa_invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void CT01UC01ACadastra_nome_empresa_invalido() {
		empresa.setCnpj("89424232000180");
		empresa.setNomeDaEmpresa("");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
	}

	/**
	 * Method CT01UC01ACadastra_nome_fantasia_invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void CT01UC01ACadastra_nome_fantasia_invalido() {
		empresa.setCnpj("89424232000180");
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setNomeFantasia("");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
	}

	/**
	 * Method CT01UC01ACadastra_empresa_invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void CT01UC01ACadastra_empresa_invalido() {
		empresa.setCnpj("89424232000180");
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("");
		empresa.setTelefone("2542");
	}

	/**
	 * Method CT01UC01ACadastra_telefone_invalido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void CT01UC01ACadastra_telefone_invalido() {
		empresa.setCnpj("89424232000180");
		empresa.setNomeDaEmpresa("empresa y");
		empresa.setNomeFantasia("empresa y");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("");
	}

	/**
	 * Method excluiEmpresa.
	
	 * @throws Exception */
	@After
	public void excluiEmpresa() throws Exception {
		empresaDAO.exclui("89424232000180");
	}
}