package br.com.gs.unicorncake.bo;

import java.util.List;

import br.com.gs.unicorncake.beans.Alergia;
import br.com.gs.unicorncake.beans.Medicamento;
import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.DadosInvalidosException;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioAlergia;
import br.com.gs.unicorncake.repositorio.RepositorioUsuario;

public class CadastrarAlergia {
	private RepositorioAlergia repositorio;
	private RepositorioUsuario repositorioUsuario;

	public CadastrarAlergia(RepositorioAlergia repositorio, RepositorioUsuario repositorioUsuario) {
		this.repositorio = repositorio;
		this.repositorioUsuario = repositorioUsuario;
	}

	public void cadastrar(String cpf, String tipo, String descricaoGrau) throws DadosInvalidosException, ErroInfraestruturaException {
		Usuario usuarioExistente = repositorioUsuario.buscarPorCPF(cpf);
		
		
		if (usuarioExistente == null) {
			throw new DadosInvalidosException(
					"Este usuario não está cadastrado");
		}
		
		int proximoID = repositorio.buscarProximoID();
		
		Alergia novaAlergia = new Alergia(usuarioExistente, proximoID, tipo, descricaoGrau);
		this.repositorio.salvar(novaAlergia);
	}
	
	public void atualizar(String cpf,  int idAlergia, String tipo, String descricaoGrau) throws DadosInvalidosException, ErroInfraestruturaException {
		Usuario usuarioExistente = repositorioUsuario.buscarPorCPF(cpf);
		
		
		if (usuarioExistente == null) {
			throw new DadosInvalidosException(
					"Este usuario não está cadastrado");
		}
		
		if (idAlergia <= 0) {
			throw new DadosInvalidosException(
					"Esta alergia não está cadastrada");
		}
		
		Alergia novaAlergia = new Alergia(usuarioExistente, idAlergia, tipo, descricaoGrau);
		this.repositorio.update(novaAlergia);
	}
	
	public int deletar(int idAlergia) throws DadosInvalidosException, ErroInfraestruturaException {
		
		if (idAlergia <= 0) {
			throw new DadosInvalidosException(
					"Esta alergia não está cadastrada");
		}
		return this.repositorio.delete(idAlergia);
	}

	public Alergia buscarPorIdAlergia(int idAlergia) throws ErroInfraestruturaException{
		return this.repositorio.buscarPorIdAlergia(idAlergia);
	}
	
	public List<Alergia> buscarPorCPF(String cpf) throws ErroInfraestruturaException{
		return this.repositorio.buscarPorCPF(cpf);
	}
	
}