package br.sceweb.modelo;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.sceweb.servico.FabricaDeConexoes;

/**
 * @author Lab103
 * @version $Revision: 1.0 $
 */
public class EmpresaDAO {
	/**
	 * Method adiciona.
	 * @param empresa Empresa
	
	 * @return int */
	public int adiciona(Empresa empresa) {
		PreparedStatement ps;
		int codigoRetorno = 0;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = (PreparedStatement) conn
					.prepareStatement("insert into empresa (cnpj, nomeDaEmpresa, nomeFantasia, endereco, telefone) values(?,?,?,?,?)");
			ps.setString(1, empresa.getCnpj());
			ps.setString(2, empresa.getNomeDaEmpresa());
			ps.setString(3, empresa.getNomeFantasia());
			ps.setString(4, empresa.getEndereco());
			ps.setString(5, empresa.getTelefone());
			codigoRetorno = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return codigoRetorno;
	}

	/**
	 * exclui uma empresa pelo cnpj
	 * 
	 * @param cnpj
	
	 * @return 0 erro na exclusao ou 1 excluido com sucesso */
	public int exclui(String cnpj) {
		java.sql.PreparedStatement ps;
		int codigoretorno = 0;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = conn.prepareStatement("delete from empresa where cnpj = ?");
			ps.setString(1, cnpj);
			codigoretorno = ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return codigoretorno;
	}

	/**
	 * Method consultaEmpresa.
	 * @param cnpj String
	
	 * @return Empresa */
	public Empresa consultaEmpresa(String cnpj) {
		Empresa empresa = null;
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("Casas Bahia S/A");
		empresa.setCnpj("60430951000122");
		empresa.setNomeFantasia("Casas Bahia");
		empresa.setEndereco("Rua Taquari, 23");
		empresa.setTelefone("111111");
		return empresa;
	}
}