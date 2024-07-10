package br.com.digitalbank.entities;

public class ChavePixContaCorrente {

	private Long id;
	private String chave;
	private String tipoChave;
	private Long idContaCorrente;

	public ChavePixContaCorrente(Long id, String chave, String tipoChave, Long idContaCorrente) {
		this.id = id;
		this.chave = chave;
		this.tipoChave = tipoChave;
		this.idContaCorrente = idContaCorrente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getTipoChave() {
		return tipoChave;
	}

	public void setTipoChave(String tipoChave) {
		this.tipoChave = tipoChave;
	}

	public Long getIdContaCorrente() {
		return idContaCorrente;
	}

	public void setIdContaCorrente(Long idContaCorrente) {
		this.idContaCorrente = idContaCorrente;
	}

}
