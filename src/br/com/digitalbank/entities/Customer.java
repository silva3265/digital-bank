package br.com.digitalbank.entities;

public class Customer {

	private Long id;
	private String nome;
	private String cpf;
	private Long IdEndereco;
	private String telefone;

	public Customer(Long id, String nome, String cpf, Long idEndereco, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		IdEndereco = idEndereco;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getIdEndereco() {
		return IdEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		IdEndereco = idEndereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
