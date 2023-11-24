package br.com.gs.unicorncake.beans;

import java.time.LocalDateTime;

import br.com.gs.unicorncake.excecoes.DadosInvalidosException;

public class Consulta {
	private Usuario usuario;
	private int idConsulta;
	private String lugar;
	private LocalDateTime dataHoraInicio;

	public Consulta(Usuario usuario, int idConsulta, String lugar, LocalDateTime dataHoraInicio) throws DadosInvalidosException {	
		if (lugar == null || lugar.isBlank()) {
			throw new DadosInvalidosException("Lugar deve ser informado");
		}
		
		if (dataHoraInicio == null) {
			throw new DadosInvalidosException("Data e hora deve ser informado");
		}
		
		this.usuario = usuario;
		this.idConsulta = idConsulta;
		this.lugar = lugar;
		this.dataHoraInicio = dataHoraInicio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
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
