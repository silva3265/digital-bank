package br.com.digitalbank.entities;

public class Agencia {

	private Long id;
	private Long idBanco;
	private Long idEndereco;

	public Agencia(Long id, Long idBanco, Long idEndereco) {
		this.id = id;
		this.idBanco = idBanco;
		this.idEndereco = idEndereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

}
