package br.com.gs.unicorncake.bo;

import java.time.LocalDateTime;
import java.util.List;

import br.com.gs.unicorncake.beans.Exame;
import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.DadosInvalidosException;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioExame;
import br.com.gs.unicorncake.repositorio.RepositorioUsuario;

public class CadastrarExame {
	private RepositorioExame repositorio;
	private RepositorioUsuario repositorioUsuario;

	public CadastrarExame(RepositorioExame repositorio, RepositorioUsuario repositorioUsuario) {
		this.repositorio = repositorio;
		this.repositorioUsuario = repositorioUsuario;
	}

	public void cadastrar(String cpf, String nome, String lugar, LocalDateTime dataHoraInicio) throws DadosInvalidosException, ErroInfraestruturaException {
		Usuario usuarioExistente = repositorioUsuario.buscarPorCPF(cpf);
		
		
		if (usuarioExistente == null) {
			throw new DadosInvalidosException(
					"Este usuario não está cadastrado");
		}
		
		int proximoID = repositorio.buscarProximoID();
		
		Exame novoExame = new Exame(usuarioExistente, proximoID, nome, lugar, dataHoraInicio);
		this.repositorio.salvar(novoExame);
	}
	
	public void atualizar(String cpf, int idExame, String nome, String lugar, LocalDateTime dataHoraInicio) throws DadosInvalidosException, ErroInfraestruturaException {
		Usuario usuarioExistente = repositorioUsuario.buscarPorCPF(cpf);
		
		
		if (usuarioExistente == null) {
			throw new DadosInvalidosException(
					"Este usuario não está cadastrado");
		}
		
		if (idExame <= 0) {
			throw new DadosInvalidosException(
					"Este exame não está cadastrado");
		}
		
		Exame novoExame = new Exame(usuarioExistente, idExame, nome, lugar, dataHoraInicio);
		this.repositorio.update(novoExame);
	}
	
	public int deletar(int idExame) throws DadosInvalidosException, ErroInfraestruturaException {
		
		if (idExame <= 0) {
			throw new DadosInvalidosException(
					"Este exame não está cadastrado");
		}
		return this.repositorio.delete(idExame);
	}
	
	public Exame buscarPorIdExame(int idExame) throws ErroInfraestruturaException{
		return this.repositorio.buscarPorIdExame(idExame);
	}
	
	public List<Exame> buscarPorCPF(String cpf) throws ErroInfraestruturaException{
		return this.repositorio.buscarPorCPF(cpf);
	}
	
}