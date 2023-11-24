package br.com.gs.unicorncake.bo;

import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.DadosInvalidosException;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioUsuario;

public class CadastrarUsuario {
	private RepositorioUsuario repositorio;
	
	public CadastrarUsuario(RepositorioUsuario repositorio) {
		this.repositorio = repositorio;
	}
	
	public void cadastrar(String nome, String email, String cpf, 
			String tpSangue, int idade, String genero, float peso, float altura, 
			String senha) throws DadosInvalidosException, ErroInfraestruturaException{
		Usuario usuarioExistente = repositorio.buscarPorCPF(cpf);
		
		int proximoId = repositorio.buscarProximoID();
		
		if (usuarioExistente != null) {
			throw new DadosInvalidosException(
					"Já existe um usuario com o cpf informado");
		}
		
		Usuario novoUsuario= new Usuario(proximoId, nome, email, cpf, tpSangue, idade, genero, peso, altura, senha );
		this.repositorio.salvar(novoUsuario);
	}
	
	public Usuario logar(String cpf, String senha) throws DadosInvalidosException, ErroInfraestruturaException {
		Usuario usuarioExistente = repositorio.buscarPorCPFeSenha(cpf, senha);
		
		if (usuarioExistente == null) {
			throw new DadosInvalidosException(
					"Usuario não encontrado");
		}
		
		return usuarioExistente;
	}
	
	public void atualizar(int idUsuario, String nome, String email, String cpf, 
			String tpSangue, int idade, String genero, float peso, float altura, 
			String senha) throws DadosInvalidosException, ErroInfraestruturaException {
		Usuario usuarioExistente = repositorio.buscarPorID(idUsuario);
		
		if (usuarioExistente == null) {
			throw new DadosInvalidosException(
					"Este usuario não está cadastrado");
		}
		
		if (!usuarioExistente.getCpf().equals(cpf)) {
		    throw new DadosInvalidosException("Não é permitido alterar o CPF");
		}
		
		Usuario novoUsuario = new Usuario(idUsuario, nome, email, cpf, tpSangue, idade, genero, peso, altura, senha);
		this.repositorio.update(novoUsuario);
	}
}
