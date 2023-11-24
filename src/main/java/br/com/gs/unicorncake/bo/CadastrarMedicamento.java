package br.com.gs.unicorncake.bo;

import java.time.LocalDateTime;
import java.util.List;

import br.com.gs.unicorncake.beans.Medicamento;
import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.excecoes.DadosInvalidosException;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioMedicamento;
import br.com.gs.unicorncake.repositorio.RepositorioUsuario;

public class CadastrarMedicamento {
	private RepositorioMedicamento repositorio;
	private RepositorioUsuario repositorioUsuario;

	public CadastrarMedicamento(RepositorioMedicamento repositorio, RepositorioUsuario repositorioUsuario) {
		this.repositorio = repositorio;
		this.repositorioUsuario = repositorioUsuario;
	}

	public void cadastrar(String cpf, String nome, String dosagem, String viaDeAdmin,
			LocalDateTime dataHoraInicio, int quantidadeDias) throws DadosInvalidosException, ErroInfraestruturaException {
		Usuario usuarioExistente = repositorioUsuario.buscarPorCPF(cpf);
		
		
		if (usuarioExistente == null) {
			throw new DadosInvalidosException(
					"Este usuario não está cadastrado");
		}
		
		int proximoID = repositorio.buscarProximoID();
		
		Medicamento novoMedicamento = new Medicamento(usuarioExistente, proximoID, nome, dosagem, viaDeAdmin, dataHoraInicio, quantidadeDias);
		this.repositorio.salvar(novoMedicamento);
	}
	
	public void atualizar(String cpf, int idMedicamento, String nome, String dosagem, String viaDeAdmin,
			LocalDateTime dataHoraInicio, int quantidadeDias) throws DadosInvalidosException, ErroInfraestruturaException {
		Usuario usuarioExistente = repositorioUsuario.buscarPorCPF(cpf);
		
		
		if (usuarioExistente == null) {
			throw new DadosInvalidosException(
					"Este usuario não está cadastrado");
		}
		
		if (idMedicamento <= 0) {
			throw new DadosInvalidosException(
					"Este medicamento não está cadastrado");
		}
		
		Medicamento novoMedicamento = new Medicamento(usuarioExistente, idMedicamento, nome, dosagem, viaDeAdmin, dataHoraInicio, quantidadeDias);
		this.repositorio.update(novoMedicamento);
	}
	
	public int deletar(int idMedicamento) throws DadosInvalidosException, ErroInfraestruturaException {
		
		if (idMedicamento <= 0) {
			throw new DadosInvalidosException(
					"Este medicamento não está cadastrado");
		}
		return this.repositorio.delete(idMedicamento);
	}
	
	public Medicamento buscarPorIdMedicamento(int idMedicamento) throws ErroInfraestruturaException{
		return this.repositorio.buscarPorIdMedicamento(idMedicamento);
	}	
	
	public List<Medicamento> buscarPorCPF(String cpf) throws ErroInfraestruturaException{
		return this.repositorio.buscarPorCPF(cpf);
	}
}
