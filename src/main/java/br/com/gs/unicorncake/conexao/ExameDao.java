package br.com.gs.unicorncake.conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.gs.unicorncake.beans.Exame;
import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioExame;

public class ExameDao implements RepositorioExame {
	private PreparedStatement pst = null;
	private UsuarioDao usuarioDao = new UsuarioDao();

	@Override
	public int buscarProximoID() throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {

			pst = conn.prepareStatement("SELECT SQ_UNICAKE_EXAME.NEXTVAL AS ID FROM DUAL");

			try (ResultSet registros = pst.executeQuery()) {
				while (registros.next()) {
					int id = registros.getInt("ID");
					pst.close();
					return id;
				}
				pst.close();
				return 0;
			}
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao procurar proximo id", ex);
		}
	}

	@Override
	public Exame buscarPorIdExame(int idExame) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("SELECT * FROM T_UNICAKE_EXAME WHERE ID_EXAME=?");

			pst.setInt(1, idExame);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String nome = rs.getString("NM_EXAME");
				String lugar = rs.getString("NM_ENDERECO");
				Timestamp timestamp = rs.getTimestamp("DT_HR_EXAME");
				LocalDateTime dataHoraInicio = timestamp.toLocalDateTime();
				int idUsuario = rs.getInt("ID_USUARIO");

				Usuario usuario = usuarioDao.buscarPorID(idUsuario);

				Exame exame = new Exame(usuario, idExame, nome, lugar, dataHoraInicio);
				pst.close();
				return exame;
			} else {
				pst.close();
				return null;
			}
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar exame por cpf", ex);
		}
	}

	@Override
	public List<Exame> buscarPorCPF(String cpf) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("SELECT * FROM T_UNICAKE_EXAME WHERE ID_USUARIO IN (SELECT ID_USUARIO FROM T_UNICAKE_USUARIO WHERE NR_CPF=?)");

			pst.setString(1, cpf);
			List<Exame> lista = new ArrayList<>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("NM_EXAME");
				String lugar = rs.getString("NM_ENDERECO");
				Timestamp timestamp = rs.getTimestamp("DT_HR_EXAME");
				LocalDateTime dataHoraInicio = timestamp.toLocalDateTime();
				int idUsuario = rs.getInt("ID_USUARIO");
				int idExame = rs.getInt("ID_EXAME");

				Usuario usuario = usuarioDao.buscarPorID(idUsuario);

				Exame exame = new Exame(usuario, idExame, nome, lugar, dataHoraInicio);
				
				lista.add(exame);
			}
			pst.close();
			return lista;
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar lista de exame por cpf", ex);
		}
	}

	@Override
	public void salvar(Exame novoExame) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {

			pst = conn.prepareStatement(
					"insert into T_UNICAKE_EXAME (ID_EXAME, ID_USUARIO, NM_EXAME, NM_ENDERECO, DT_HR_EXAME) values (?, ?, ?, ?, ?)");
			pst.setInt(1, novoExame.getIdExame());
			pst.setInt(2, novoExame.getUsuario().getIdUsuario());
			pst.setString(3, novoExame.getNome());
			pst.setString(4, novoExame.getLugar());
			pst.setTimestamp(5, Timestamp.valueOf(novoExame.getDataHoraInicio()));

			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao inserir Exame", ex);
		}
	}

	@Override
	public void update(Exame exameAtualizado) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(
					"UPDATE T_UNICAKE_EXAME SET NM_EXAME=?, NM_ENDERECO=?, DT_HR_EXAME=? WHERE ID_EXAME=?");

			pst.setString(1, exameAtualizado.getNome());
			pst.setString(2, exameAtualizado.getLugar());
			pst.setTimestamp(3, Timestamp.valueOf(exameAtualizado.getDataHoraInicio()));
			pst.setInt(4, exameAtualizado.getIdExame());

			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao atualizar exame", ex);
		}
	}

	@Override
	public int delete(int idExame) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("DELETE FROM T_UNICAKE_EXAME WHERE ID_EXAME=?");
			pst.setInt(1, idExame);
			int retorno = pst.executeUpdate();
			pst.close();
			return retorno;
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao excluir exame", ex);
		}
	}

}
