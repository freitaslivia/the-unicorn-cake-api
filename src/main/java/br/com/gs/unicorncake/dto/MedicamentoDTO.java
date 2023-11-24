package br.com.gs.unicorncake.dto;

import java.time.LocalDateTime;

public class MedicamentoDTO {
	private String cpf;
	private String nome;
	private String dosagem;
	private String viaDeAdmin;
	private LocalDateTime dataHoraInicio;
	private int quantidadeDias;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
