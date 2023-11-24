package br.com.gs.unicorncake.excecoes;

public class ErroInfraestruturaException extends Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroInfraestruturaException(String mensagem, Throwable causaRaiz) {
		super(mensagem, causaRaiz);
	}

}