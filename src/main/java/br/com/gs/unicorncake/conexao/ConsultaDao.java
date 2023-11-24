package br.com.gs.unicorncake.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.gs.unicorncake.beans.Consulta;
import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioConsulta;

public class ConsultaDao implements RepositorioConsulta {
	private PreparedStatement pst = null;
	private UsuarioDao usuarioDao = new UsuarioDao();

	@Override
	public int buscarProximoID() throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {

			pst = conn.prepareStatement("SELECT SQ_UNICAKE_CONSULTA.NEXTVAL AS ID FROM DUAL");

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
			throw new ErroInfraestruturaException("Erro ao procurar ultimo id", ex);
		}
	}

	@Override
	public Consulta buscarPorIdConsulta(int idConsulta) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("SELECT * FROM T_UNICAKE_CONSULTA WHERE ID_CONSULTA=?");

			pst.setInt(1, idConsulta);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String lugar = rs.getString("NM_ENDERECO");
				Timestamp timestamp = rs.getTimestamp("DT_HR_CONSULTA");
				LocalDateTime dataHoraInicio = timestamp.toLocalDateTime();
				int idUsuario = rs.getInt("ID_USUARIO");

				Usuario usuario = usuarioDao.buscarPorID(idUsuario);

				Consulta consulta = new Consulta(usuario, idConsulta, lugar, dataHoraInicio);

				pst.close();
				return consulta;
			} else {
				pst.close();
				return null;
			}
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar usuario por id", ex);
		}
	}

	@Override
	public List<Consulta> buscarPorCPF(String cpf) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(
					"SELECT * FROM T_UNICAKE_CONSULTA WHERE ID_USUARIO IN (SELECT ID_USUARIO FROM T_UNICAKE_USUARIO WHERE NR_CPF=?)");

			pst.setString(1, cpf);
			List<Consulta> lista = new ArrayList<>();
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String lugar = rs.getString("NM_ENDERECO");
				Timestamp timestamp = rs.getTimestamp("DT_HR_CONSULTA");
				LocalDateTime dataHoraInicio = timestamp.toLocalDateTime();
				int idUsuario = rs.getInt("ID_USUARIO");
				int idConsulta = rs.getInt("ID_CONSULTA");

				Usuario usuario = usuarioDao.buscarPorID(idUsuario);

				Consulta consulta = new Consulta(usuario, idConsulta, lugar, dataHoraInicio);
			
				lista.add(consulta);
			}
			pst.close();
			return lista;
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar lista de consulta por cpf", ex);
		}
	}

	@Override
	public void salvar(Consulta novaConsulta) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {

			pst = conn.prepareStatement(
					"insert into T_UNICAKE_CONSULTA (ID_CONSULTA, ID_USUARIO, NM_ENDERECO, DT_HR_CONSULTA) values (?, ?, ?, ?)");
			pst.setInt(1, novaConsulta.getIdConsulta());
			pst.setInt(2, novaConsulta.getUsuario().getIdUsuario());
			pst.setString(3, novaConsulta.getLugar());
			pst.setTimestamp(4, Timestamp.valueOf(novaConsulta.getDataHoraInicio()));

			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao inserir Consulta", ex);
		}
	}

	@Override
	public void update(Consulta consultaAtualizada) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(
					"UPDATE T_UNICAKE_CONSULTA SET NM_ENDERECO=?, DT_HR_CONSULTA=? WHERE ID_CONSULTA=?");

			pst.setString(1, consultaAtualizada.getLugar());
			pst.setTimestamp(2, Timestamp.valueOf(consultaAtualizada.getDataHoraInicio()));
			pst.setInt(3, consultaAtualizada.getIdConsulta());

			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao atualizar Consulta", ex);
		}
	}

	@Override
	public int delete(int idConsulta) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("DELETE FROM T_UNICAKE_CONSULTA WHERE ID_CONSULTA=?");
			pst.setInt(1, idConsulta);
			int retorno = pst.executeUpdate();
			pst.close();
			return retorno;
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao excluir consulta", ex);
		}
	}

}
