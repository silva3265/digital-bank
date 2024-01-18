package br.com.digitalbank.entities;

public class Bank {

	private Integer idEnderecoMatriz;
	private String nome;

	public Bank(Integer idEnderecoMatriz, String nome) {
		this.idEnderecoMatriz = idEnderecoMatriz;
		this.nome = nome;
	}

	public Integer getIdEnderecoMatriz() {
		return idEnderecoMatriz;
	}

	public void setIdEnderecoMatriz(Integer idEnderecoMatriz) {
		this.idEnderecoMatriz = idEnderecoMatriz;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
