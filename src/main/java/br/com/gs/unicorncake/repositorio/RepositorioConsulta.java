package br.com.gs.unicorncake.repositorio;

import java.util.List;

import br.com.gs.unicorncake.beans.Consulta;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;

public interface RepositorioConsulta {

	public int buscarProximoID() throws ErroInfraestruturaException;
	
	public Consulta buscarPorIdConsulta(int idConsulta) throws ErroInfraestruturaException;
	
	public List<Consulta> buscarPorCPF(String cpf) throws ErroInfraestruturaException;
	
	public void salvar(Consulta novaConsulta) throws ErroInfraestruturaException;
	
	public void update(Consulta consultaAtualizada) throws ErroInfraestruturaException;
	
	public int delete(int idConsulta) throws ErroInfraestruturaException;
}
