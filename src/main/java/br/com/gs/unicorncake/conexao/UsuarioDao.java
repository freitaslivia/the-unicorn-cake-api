package br.com.gs.unicorncake.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioUsuario;

public class UsuarioDao implements RepositorioUsuario {
	private PreparedStatement pst = null;
	
	@Override
	public int buscarProximoID() throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			
			pst = conn.prepareStatement("SELECT SQ_UNICAKE_USUARIO.NEXTVAL AS ID FROM DUAL");
			
			try (ResultSet registros = pst.executeQuery()) {						
				while(registros.next()) {
					int id = registros.getInt("ID");
					pst.close();
					return id;
				}
				pst.close();
				return 1;
			}
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar proximo id", ex);
		}
	}

	@Override
	public void salvar(Usuario novoUsuario) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			
			pst = conn.prepareStatement("insert into T_UNICAKE_USUARIO(ID_USUARIO, NM_USUARIO, NM_EMAIL, NR_CPF, NM_GRUPO_SANGUINEO, NR_IDADE, FL_GENERO, NR_PESO, NR_ALTURA, NM_SENHA) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pst.setInt(1, novoUsuario.getIdUsuario());
			pst.setString(2, novoUsuario.getNome());
			pst.setString(3, novoUsuario.getEmail());
			pst.setString(4, novoUsuario.getCpf());
			pst.setString(5, novoUsuario.getTpSangue());
			pst.setInt(6, novoUsuario.getIdade());
			pst.setString(7, novoUsuario.getGenero());
			pst.setFloat(8, novoUsuario.getPeso());
			pst.setFloat(9, novoUsuario.getAltura());
			pst.setString(10, novoUsuario.getSenha());
			
			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao inserir cliente", ex);
		}
	}
	
	@Override
	public Usuario buscarPorCPF(String cpf) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("SELECT * FROM T_UNICAKE_USUARIO WHERE NR_CPF=?");
			
			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				int idUsuario = rs.getInt("ID_USUARIO");
				String nome = rs.getString("NM_USUARIO");
				String email = rs.getString("NM_EMAIL");
				cpf = rs.getString("NR_CPF");
				String tpSangue = rs.getString("NM_GRUPO_SANGUINEO");
				int idade = rs.getInt("NR_IDADE");
				String genero = rs.getString("FL_GENERO");
				float peso = rs.getFloat("NR_PESO");
				float altura = rs.getFloat("NR_ALTURA");
				String senha  = rs.getString("NM_SENHA");
				Usuario usuario = new Usuario(idUsuario, nome, email, cpf, tpSangue, idade, genero, peso, altura, senha);
				pst.close();
				return usuario;
			} else {
				pst.close();
				return null;
			}
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar usuario por cpf", ex);
		}
	}
	
	@Override
	public Usuario buscarPorID(int idUsuario) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("SELECT * FROM T_UNICAKE_USUARIO WHERE ID_USUARIO=?");
			
			pst.setInt(1, idUsuario);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				idUsuario = rs.getInt("ID_USUARIO");
				String nome = rs.getString("NM_USUARIO");
				String email = rs.getString("NM_EMAIL");
				String cpf = rs.getString("NR_CPF");
				String tpSangue = rs.getString("NM_GRUPO_SANGUINEO");
				int idade = rs.getInt("NR_IDADE");
				String genero = rs.getString("FL_GENERO");
				float peso = rs.getFloat("NR_PESO");
				float altura = rs.getFloat("NR_ALTURA");
				String senha  = rs.getString("NM_SENHA");
				Usuario usuario = new Usuario(idUsuario, nome, email, cpf, tpSangue, idade, genero, peso, altura, senha);
				pst.close();
				return usuario;
			} else {
				pst.close();
				return null;
			}
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar usuario por id", ex);
		}
	}
	
	@Override
	public Usuario buscarPorCPFeSenha(String cpf, String senha) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("SELECT * FROM T_UNICAKE_USUARIO WHERE NR_CPF=? AND NM_SENHA=?");
			
			pst.setString(1, cpf);
			pst.setString(2, senha);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				int idUsuario = rs.getInt("ID_USUARIO");
				String nome = rs.getString("NM_USUARIO");
				String email = rs.getString("NM_EMAIL");
				cpf = rs.getString("NR_CPF");
				String tpSangue = rs.getString("NM_GRUPO_SANGUINEO");
				int idade = rs.getInt("NR_IDADE");
				String genero = rs.getString("FL_GENERO");
				float peso = rs.getFloat("NR_PESO");
				float altura = rs.getFloat("NR_ALTURA");
				senha  = rs.getString("NM_SENHA");
				Usuario usuario = new Usuario(idUsuario, nome, email, cpf, tpSangue, idade, genero, peso, altura, senha);
				pst.close();
				return usuario;
			} else {
				pst.close();
				return null;
			}
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar usuario por cpf", ex);
		}
	}
	
	@Override
	public void update(Usuario usuarioAtualizado)throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(
					"UPDATE T_UNICAKE_USUARIO SET NM_USUARIO = ?, NM_EMAIL = ?, NM_GRUPO_SANGUINEO = ?, NR_IDADE = ?, FL_GENERO = ?, NR_PESO = ?, NR_ALTURA = ?, NM_SENHA = ? WHERE ID_USUARIO = ?");

			pst.setString(1, usuarioAtualizado.getNome());
			pst.setString(2, usuarioAtualizado.getEmail());
			pst.setString(3, usuarioAtualizado.getTpSangue());
			pst.setInt(4, usuarioAtualizado.getIdade());
			pst.setString(5, usuarioAtualizado.getGenero());
			pst.setFloat(6, usuarioAtualizado.getPeso());
			pst.setFloat(7, usuarioAtualizado.getAltura());
			pst.setString(8, usuarioAtualizado.getSenha());
			pst.setInt(9, usuarioAtualizado.getIdUsuario());
			
			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao atualizar usuario", ex);
		}
	}	

}
