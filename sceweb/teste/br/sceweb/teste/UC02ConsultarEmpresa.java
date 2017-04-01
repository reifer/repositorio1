package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

/**
 * @author Lab103
 * @version $Revision: 1.0 $
 */
public class UC02ConsultarEmpresa {
	/**
	 * Field empresa.
	 */
	static Empresa empresa;
	/**
	 * Field empresaDAO.
	 */
	static EmpresaDAO empresaDAO ;
	
	/**
	 * Method setUpBeforeClass.
	
	 * @throws Exception */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresa = new Empresa();
		empresaDAO = new EmpresaDAO();
		empresa.setNomeDaEmpresa("Casas Bahia S/A");
		empresa.setCnpj("60430951000122");
		empresa.setNomeFantasia("Casas Bahia");
		empresa.setEndereco("Rua Taquari, 23");
		empresa.setTelefone("111111");
	}
	
	/**
	 * Method preCondicaoInserirEmpresa.
	 */
	@Before
	public void preCondicaoInserirEmpresa(){
		empresaDAO.adiciona(empresa);
	}
	
	/**
	 * Method CT05UC02FB_consultar_empresa_com_sucesso.
	 */
	@Test
	public void CT05UC02FB_consultar_empresa_com_sucesso(){
		assertTrue(empresa.equals(empresaDAO.consultaEmpresa("60430951000122")));
	}
	
	/**
	 * Method excluirEmpresa.
	 */
	@After
	public void excluirEmpresa(){
		empresaDAO.exclui(empresa.getCnpj());
	}
}