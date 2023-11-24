package br.com.gs.unicorncake.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gs.unicorncake.beans.Alergia;
import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioAlergia;

public class AlergiaDao implements RepositorioAlergia {
	private PreparedStatement pst = null;
	private UsuarioDao usuarioDao = new UsuarioDao();

	@Override
	public int buscarProximoID() throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {

			pst = conn.prepareStatement("SELECT SQ_UNICAKE_ALERGIA.NEXTVAL AS ID FROM DUAL");

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
	public Alergia buscarPorIdAlergia(int idAlergia) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("SELECT * FROM T_UNICAKE_ALERGIA WHERE ID_ALERGIA=?");

			pst.setInt(1, idAlergia);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String tipo = rs.getString("TP_ALERGIA");
				String descricaoGrau = rs.getString("DS_GRAU_ALERGIA");
				int idUsuario = rs.getInt("ID_USUARIO");

				Usuario usuario = usuarioDao.buscarPorID(idUsuario);

				Alergia alergia = new Alergia(usuario, idAlergia, tipo, descricaoGrau);
				pst.close();
				return alergia;
			} else {
				pst.close();
				return null;
			}
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar alergia por id", ex);
		}
	}

	@Override
	public List<Alergia> buscarPorCPF(String cpf) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(
					"SELECT * FROM T_UNICAKE_ALERGIA WHERE ID_USUARIO IN (SELECT ID_USUARIO FROM T_UNICAKE_USUARIO WHERE NR_CPF=?)");

			pst.setString(1, cpf);
			List<Alergia> lista = new ArrayList<>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String tipo = rs.getString("TP_ALERGIA");
				String descricaoGrau = rs.getString("DS_GRAU_ALERGIA");
				int idUsuario = rs.getInt("ID_USUARIO");
				int idAlergia = rs.getInt("ID_ALERGIA");

				Usuario usuario = usuarioDao.buscarPorID(idUsuario);
				Alergia medicamento = new Alergia(usuario, idAlergia, tipo, descricaoGrau);
				
				lista.add(medicamento);
			}
			pst.close();
			return lista;

		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao buscar lista de alergia por cpf", ex);
		}
	}

	@Override
	public void salvar(Alergia novaAlergia) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {

			pst = conn.prepareStatement(
					"INSERT INTO T_UNICAKE_ALERGIA(ID_ALERGIA, ID_USUARIO, TP_ALERGIA, DS_GRAU_ALERGIA) VALUES(?, ?, ?, ?)");

			pst.setInt(1, novaAlergia.getIdAlergia());
			pst.setInt(2, novaAlergia.getUsuario().getIdUsuario());
			pst.setString(3, novaAlergia.getTipo());
			pst.setString(4, novaAlergia.getDescricaoGrau());

			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao inserir alergia", ex);
		}
	}

	@Override
	public void update(Alergia alergiaAtualizada) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(
					"UPDATE T_UNICAKE_ALERGIA SET TP_ALERGIA=?, DS_GRAU_ALERGIA=? WHERE ID_ALERGIA=?");

			pst.setString(1, alergiaAtualizada.getTipo());
			pst.setString(2, alergiaAtualizada.getDescricaoGrau());
			pst.setInt(3, alergiaAtualizada.getIdAlergia());

			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao atualizar alergia", ex);
		}
	}

	@Override
	public int delete(int idAlergia) throws ErroInfraestruturaException {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement("DELETE FROM T_UNICAKE_ALERGIA WHERE ID_ALERGIA=?");
			pst.setInt(1, idAlergia);
			int retorno = pst.executeUpdate();
			pst.close();
			return retorno;
		} catch (Exception ex) {
			throw new ErroInfraestruturaException("Erro ao excluir a alegia", ex);
		}
	}

}
