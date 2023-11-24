package br.com.gs.unicorncake.repositorio;

import java.util.List;

import br.com.gs.unicorncake.beans.Exame;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;

public interface RepositorioExame {
	
	public int buscarProximoID() throws ErroInfraestruturaException;
	
	public Exame buscarPorIdExame(int idExame) throws ErroInfraestruturaException;
	
	public List<Exame> buscarPorCPF(String cpf) throws ErroInfraestruturaException;
	
	public void salvar(Exame novoExame) throws ErroInfraestruturaException;
	
	public void update(Exame exameAtualizado) throws ErroInfraestruturaException;
	
	public int delete(int idExame) throws ErroInfraestruturaException;
}
