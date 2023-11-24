package br.com.gs.unicorncake.repositorio;

import java.util.List;

import br.com.gs.unicorncake.beans.Alergia;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;

public interface RepositorioAlergia {
	
	public int buscarProximoID() throws ErroInfraestruturaException;
	
	public Alergia buscarPorIdAlergia(int idAlergia) throws ErroInfraestruturaException;
	
	public List<Alergia> buscarPorCPF(String cpf) throws ErroInfraestruturaException;
	
	public void salvar(Alergia novaAlergia) throws ErroInfraestruturaException;
	
	public void update(Alergia alergiaAtualizada) throws ErroInfraestruturaException;
	
	public int delete(int idAlergia) throws ErroInfraestruturaException;
}
