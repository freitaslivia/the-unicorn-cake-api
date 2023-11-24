 package br.com.gs.unicorncake.beans;

import br.com.gs.unicorncake.excecoes.DadosInvalidosException;

public class Usuario {
	private int idUsuario;
	private String nome;
	private String cpf;
	private String email;
	private String tpSangue;
	private int idade;
	private String genero;
	private float peso;
	private float altura;
	private String senha;
	
	public Usuario(int idUsuario, String nome, String email, String cpf, String tpSangue, int idade, String genero, float peso, float altura, String senha) throws DadosInvalidosException{
		
		if (nome == null || nome.isBlank()) {
			throw new DadosInvalidosException("Nome deve ser informado");
		}
		
		if (email == null || email.isBlank()) {
			throw new DadosInvalidosException("Email deve ser informado");
		}
		
		if (!isCPFValido(cpf)) {
			throw new DadosInvalidosException("CPF deve ser informado");
		}
		
		if (tpSangue == null || tpSangue.isBlank()) {
			throw new DadosInvalidosException("Tipo Sanguineo deve ser informado");
		}
		
		if (idade == -1) {
			throw new DadosInvalidosException("Idade deve ser informado");
		}
		
		if (genero == null || genero.isBlank()) {
			throw new DadosInvalidosException("Genero deve ser informado");
		}
		
		if (Float.isNaN(peso)) {
			throw new DadosInvalidosException("Peso deve ser informado");
		}
		
		if (Float.isNaN(altura)) {
			throw new DadosInvalidosException("Altura deve ser informada");
		}
		
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.tpSangue = tpSangue;
		this.idade = idade;
		this.genero = genero;
		this.peso = peso;
		this.altura = altura;
		this.senha = senha;
	}
	
	private boolean isCPFValido(String numero) {
		return numero != null && !numero.isBlank() && numero.length() == 14;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCpf() {
		return cpf;
	}
	
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTpSangue() {
		return tpSangue;
	}

	public void setTpSangue(String tpSangue) {
		this.tpSangue = tpSangue;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
