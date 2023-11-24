package br.com.gs.unicorncake.bo;

import java.time.LocalDateTime;
import java.util.List;

import br.com.gs.unicorncake.beans.Consulta;
import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.DadosInvalidosException;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioConsulta;
import br.com.gs.unicorncake.repositorio.RepositorioUsuario;


public class CadastrarConsulta {
	private RepositorioConsulta repositorio;
	private RepositorioUsuario repositorioUsuario;

	public CadastrarConsulta(RepositorioConsulta repositorio, RepositorioUsuario repositorioUsuario) {
		this.repositorio = repositorio;
		this.repositorioUsuario = repositorioUsuario;
	}

	public void cadastrar(String cpf, String lugar, LocalDateTime dataHoraInicio) throws DadosInvalidosException, ErroInfraestruturaException {
		Usuario usuarioExistente = repositorioUsuario.buscarPorCPF(cpf);
		
		
		if (usuarioExistente == null) {
			throw new DadosInvalidosException(
					"Este usuario não está cadastrado");
		}
		
		int proximoID = repositorio.buscarProximoID();
		
		Consulta novaConsulta = new Consulta(usuarioExistente, proximoID, lugar, dataHoraInicio);
		this.repositorio.salvar(novaConsulta);
	}
	
	public void atualizar(String cpf, int idConsulta, String lugar, LocalDateTime dataHoraInicio) throws DadosInvalidosException, ErroInfraestruturaException {
		Usuario usuarioExistente = repositorioUsuario.buscarPorCPF(cpf);
		
		
		if (usuarioExistente == null) {
			throw new DadosInvalidosException(
					"Este usuario não está cadastrado");
		}
		
		if (idConsulta <= 0) {
			throw new DadosInvalidosException(
					"Esta consulta não está cadastrada");
		}
		
		Consulta novaConsulta = new Consulta(usuarioExistente, idConsulta, lugar, dataHoraInicio);
		this.repositorio.update(novaConsulta);
	}
	
	public int deletar(int idConsulta) throws DadosInvalidosException, ErroInfraestruturaException {
		
		if (idConsulta <= 0) {
			throw new DadosInvalidosException(
					"Esta consulta não está cadastrada");
		}
		return this.repositorio.delete(idConsulta);
	}
	
	public Consulta buscarPorIdConsulta(int idConsulta) throws ErroInfraestruturaException{
		return this.repositorio.buscarPorIdConsulta(idConsulta);
	}
	
	public List<Consulta> buscarPorCPF(String cpf) throws ErroInfraestruturaException{
		return this.repositorio.buscarPorCPF(cpf);
	}
}