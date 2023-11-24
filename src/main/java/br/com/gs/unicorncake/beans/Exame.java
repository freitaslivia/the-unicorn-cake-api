package br.com.gs.unicorncake.beans;

import java.time.LocalDateTime;

import br.com.gs.unicorncake.excecoes.DadosInvalidosException;

public class Exame {
	private Usuario usuario;
	private int idExame;
	private String nome;
	private String lugar;
	private LocalDateTime dataHoraInicio;

	public Exame(Usuario usuario, int idExame, String nome, String lugar, LocalDateTime dataHoraInicio) throws DadosInvalidosException {
		if (nome == null || nome.isBlank()) {
			throw new DadosInvalidosException("Nome deve ser informado");
		}
		
		if (lugar == null || lugar.isBlank()) {
			throw new DadosInvalidosException("Lugar deve ser informado");
		}
		
		if (dataHoraInicio == null) {
			throw new DadosInvalidosException("Data e hora deve ser informado");
		}
		
		this.usuario = usuario;
		this.idExame = idExame;
		this.nome = nome;
		this.lugar = lugar;
		this.dataHoraInicio = dataHoraInicio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getIdExame() {
		return idExame;
	}

	public void setIdExame(int idExame) {
		this.idExame = idExame;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

}
