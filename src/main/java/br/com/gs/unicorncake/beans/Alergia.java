package br.com.gs.unicorncake.beans;

import br.com.gs.unicorncake.excecoes.DadosInvalidosException;

public class Alergia {
	private Usuario usuario;
	private int idAlergia;
	private String tipo;
	private String descricaoGrau;

	public Alergia(Usuario usuario, int idAlergia, String tipo, String descricaoGrau) throws DadosInvalidosException {
		if (tipo == null || tipo.isBlank()) {
			throw new DadosInvalidosException("Tipo deve ser informado");
		}
		
		if (descricaoGrau == null || descricaoGrau.isBlank()) {
			throw new DadosInvalidosException("Descrição deve ser informado");
		}
		
		this.usuario = usuario;
		this.idAlergia = idAlergia;
		this.tipo = tipo;
		this.descricaoGrau = descricaoGrau;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getIdAlergia() {
		return idAlergia;
	}

	public void setIdAlergia(int idAlergia) {
		this.idAlergia = idAlergia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricaoGrau() {
		return descricaoGrau;
	}

	public void setDescricaoGrau(String descricaoGrau) {
		this.descricaoGrau = descricaoGrau;
	}


}
