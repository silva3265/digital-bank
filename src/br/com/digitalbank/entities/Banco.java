package br.com.digitalbank.entities;

public class Banco {

	private Long id;
	private Integer idEnderecoMatriz;
	private String nome;

	public Banco(Long id, Integer idEnderecoMatriz, String nome) {
		this.idEnderecoMatriz = idEnderecoMatriz;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
