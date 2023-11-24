package br.com.gs.unicorncake.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;

public class Conexao {
	private static final String driver_class = "oracle.jdbc.driver.OracleDriver";
	private static Connection cnx = null;
	private static String usuario = "rm99892";
	private static String senha = "231004";
	private static final String URL = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL";

	public static Connection conectar() throws ErroInfraestruturaException {
		
		if(cnx == null) {
			try {
				Class.forName(driver_class);
				
				cnx = DriverManager.getConnection(URL, usuario, senha);
					
			} catch (Exception ex) {
				throw new ErroInfraestruturaException("Erro ao Conectar ao Banco", ex);
			}
		}
		
		return cnx;
	}
	
	public static void fecharCnx() throws ErroInfraestruturaException {
	    try {
	        cnx.close();
	        cnx = null;
	    } catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao fechar conexão", ex);
		}
		
	}
}
