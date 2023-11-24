package br.com.gs.unicorncake.beans;

import java.time.LocalDateTime;

import br.com.gs.unicorncake.excecoes.DadosInvalidosException;

public class Medicamento {
	private Usuario usuario;
	private int idMedicamento;
	private String nome;
	private String dosagem;
	private String viaDeAdmin;
	private LocalDateTime dataHoraInicio;
	private int quantidadeDias;

	
	public Medicamento(Usuario usuario, int idMedicamento, String nome, String dosagem, String viaDeAdmin,
			LocalDateTime dataHoraInicio, int quantidadeDias) throws DadosInvalidosException {
		if (nome == null || nome.isBlank()) {
			throw new DadosInvalidosException("Nome deve ser informado");
		}
		
		if (dosagem == null || dosagem.isBlank()) {
			throw new DadosInvalidosException("Dosagem deve ser informado");
		}
		
		if (viaDeAdmin == null || viaDeAdmin.isBlank()) {
			throw new DadosInvalidosException("A via deve ser informado");
		}
		if (dataHoraInicio == null) {
			throw new DadosInvalidosException("Data e hora deve ser informado");
		}
		if (quantidadeDias == -1) {
			throw new DadosInvalidosException("Quantidade de dias deve ser informado");
		}
		
		this.usuario = usuario;
		this.idMedicamento = idMedicamento;
		this.nome = nome;
		this.dosagem = dosagem;
		this.viaDeAdmin = viaDeAdmin;
		this.dataHoraInicio = dataHoraInicio;
		this.quantidadeDias = quantidadeDias;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public int getIdMedicamento() {
		return idMedicamento;
	}


	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDosagem() {
		return dosagem;
	}


	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}


	public String getViaDeAdmin() {
		return viaDeAdmin;
	}


	public void setViaDeAdmin(String viaDeAdmin) {
		this.viaDeAdmin = viaDeAdmin;
	}


	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}


	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}


	public int getQuantidadeDias() {
		return quantidadeDias;
	}


	public void setQuantidadeDias(int quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}
	
	
	
}
