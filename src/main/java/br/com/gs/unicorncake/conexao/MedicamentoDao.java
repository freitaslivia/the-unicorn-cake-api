package br.com.gs.unicorncake.conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.gs.unicorncake.beans.Medicamento;
import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioMedicamento;

public class MedicamentoDao implements RepositorioMedicamento {
	private PreparedStatement pst = null;
	private UsuarioDao usuarioDao = new UsuarioDao();

	@Override
	public int buscarProximoID() throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {

			pst = conn.prepareStatement("SELECT SQ_UNICAKE_MEDICAMENTO.NEXTVAL AS ID FROM DUAL");

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
			throw new ErroInfraestruturaException("Erro ao buscar proximo id", ex);
		}

	}

	@Override
	public Medicamento buscarPorIdMedicamento(int idMedicamento) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("SELECT * FROM T_UNICAKE_MEDICAMENTO WHERE ID_MEDICAMENTO=?");

			pst.setInt(1, idMedicamento);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String nome = rs.getString("NM_MEDICAMENTO");
				String dosagem = rs.getString("DS_DOSAGEM");
				String viaDeAdmin = rs.getString("DS_VIA_ADMIN");
				Timestamp timestamp = rs.getTimestamp("DT_HR_INICIO");
				LocalDateTime dataHoraInicio = timestamp.toLocalDateTime();
				int quantidadeDias = rs.getInt("QT_DIAS_ADMIN");
				int idUsuario = rs.getInt("ID_USUARIO");

				Usuario usuario = usuarioDao.buscarPorID(idUsuario);
				Medicamento medicamento = new Medicamento(usuario, idMedicamento, nome, dosagem, viaDeAdmin,
						dataHoraInicio, quantidadeDias);
				pst.close();
				return medicamento;
			} else {
				pst.close();
				return null;
			}
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar medicamento por id", ex);
		}
	}

	@Override
	public List<Medicamento> buscarPorCPF(String cpf) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(
					"SELECT * FROM T_UNICAKE_MEDICAMENTO WHERE ID_USUARIO IN (SELECT ID_USUARIO FROM T_UNICAKE_USUARIO WHERE NR_CPF=?)");

			pst.setString(1, cpf);
			List<Medicamento> lista = new ArrayList<>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("NM_MEDICAMENTO");
				String dosagem = rs.getString("DS_DOSAGEM");
				String viaDeAdmin = rs.getString("DS_VIA_ADMIN");
				Timestamp timestamp = rs.getTimestamp("DT_HR_INICIO");
				LocalDateTime dataHoraInicio = timestamp.toLocalDateTime();
				int quantidadeDias = rs.getInt("QT_DIAS_ADMIN");
				int idUsuario = rs.getInt("ID_USUARIO");
				int idMedicamento = rs.getInt("ID_MEDICAMENTO");

				Usuario usuario = usuarioDao.buscarPorID(idUsuario);
				Medicamento medicamento = new Medicamento(usuario, idMedicamento, nome, dosagem, viaDeAdmin,
						dataHoraInicio, quantidadeDias);

				lista.add(medicamento);
			}
			pst.close();
			return lista;
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar medicamento por id", ex);
		}
	}

	@Override
	public void salvar(Medicamento novoMedicamento) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {

			pst = conn.prepareStatement(
					"insert into T_UNICAKE_MEDICAMENTO (ID_MEDICAMENTO, ID_USUARIO, NM_MEDICAMENTO, DS_DOSAGEM, DS_VIA_ADMIN, DT_HR_INICIO, QT_DIAS_ADMIN) values (?, ?, ?, ?, ?, ?, ?)");
			pst.setInt(1, novoMedicamento.getIdMedicamento());
			pst.setInt(2, novoMedicamento.getUsuario().getIdUsuario());
			pst.setString(3, novoMedicamento.getNome());
			pst.setString(4, novoMedicamento.getDosagem());
			pst.setString(5, novoMedicamento.getViaDeAdmin());
			pst.setTimestamp(6, Timestamp.valueOf(novoMedicamento.getDataHoraInicio()));
			pst.setInt(7, novoMedicamento.getQuantidadeDias());

			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao inserir Medicamento", ex);
		}
	}

	@Override
	public void update(Medicamento medicamentoAtualizado) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(
					"UPDATE T_UNICAKE_MEDICAMENTO SET NM_MEDICAMENTO=?, DS_DOSAGEM=?, DS_VIA_ADMIN=?, DT_HR_INICIO=?, QT_DIAS_ADMIN=? WHERE ID_MEDICAMENTO=?");

			pst.setString(1, medicamentoAtualizado.getNome());
			pst.setString(2, medicamentoAtualizado.getDosagem());
			pst.setString(3, medicamentoAtualizado.getViaDeAdmin());
			pst.setTimestamp(4, Timestamp.valueOf(medicamentoAtualizado.getDataHoraInicio()));
			pst.setInt(5, medicamentoAtualizado.getQuantidadeDias());
			pst.setInt(6, medicamentoAtualizado.getIdMedicamento());

			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao atualizar medicamento", ex);
		}
	}

	@Override
	public int delete(int idMedicamento) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("DELETE FROM T_UNICAKE_MEDICAMENTO WHERE ID_MEDICAMENTO=?");
			pst.setInt(1, idMedicamento);
			int retorno = pst.executeUpdate();
			pst.close();
			return retorno;
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao excluir medicamento", ex);
		}
	}

}
