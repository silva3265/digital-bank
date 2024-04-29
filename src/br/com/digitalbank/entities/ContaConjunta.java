package br.com.digitalbank.entities;

public class ContaConjunta {

	private Long id;
	private Long idContaCorrente;
	private Long idContaPoupanca;

	public ContaConjunta(Long id, Long idContaCorrente, Long idContaPoupanca) {
		this.id = id;
		this.idContaCorrente = idContaCorrente;
		this.idContaPoupanca = idContaPoupanca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdContaCorrente() {
		return idContaCorrente;
	}

	public void setIdContaCorrente(Long idContaCorrente) {
		this.idContaCorrente = idContaCorrente;
	}

	public Long getIdContaPoupanca() {
		return idContaPoupanca;
	}

	public void setIdContaPoupanca(Long idContaPoupanca) {
		this.idContaPoupanca = idContaPoupanca;
	}

}
