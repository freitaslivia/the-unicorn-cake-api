package br.com.gs.unicorncake.dto;

public class AlergiaDTO {
	private String cpf;
	private String tipo;
	private String descricaoGrau;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
