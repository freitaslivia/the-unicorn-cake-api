package br.com.gs.unicorncake.repositorio;

import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;

public interface RepositorioUsuario {
	
	public int buscarProximoID() throws ErroInfraestruturaException;

	public Usuario buscarPorCPF(String cpf) throws ErroInfraestruturaException;

	public void salvar(Usuario novoUsuario) throws ErroInfraestruturaException;
	
	public void update(Usuario usuarioAtualizado)throws ErroInfraestruturaException;

	public Usuario buscarPorID(int idUsuario) throws ErroInfraestruturaException;
	
	public Usuario buscarPorCPFeSenha(String cpf, String senha) throws ErroInfraestruturaException;

}
