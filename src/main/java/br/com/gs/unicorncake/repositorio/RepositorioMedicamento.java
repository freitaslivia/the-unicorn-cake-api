package br.com.gs.unicorncake.repositorio;

import java.sql.SQLException;
import java.util.List;

import br.com.gs.unicorncake.beans.Medicamento;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;

public interface RepositorioMedicamento {
	
	public int buscarProximoID()throws ErroInfraestruturaException;
	
	public Medicamento buscarPorIdMedicamento(int idMedicamento) throws ErroInfraestruturaException;
	
	public List<Medicamento> buscarPorCPF(String cpf) throws ErroInfraestruturaException;
	
	public void salvar(Medicamento novoMedicamento)throws ErroInfraestruturaException;
	
	public void update(Medicamento medicamentoAtualizado)throws ErroInfraestruturaException;
	
	public int delete(int idMedicamento)throws ErroInfraestruturaException;

}
