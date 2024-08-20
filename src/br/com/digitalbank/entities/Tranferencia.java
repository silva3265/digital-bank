package br.com.digitalbank.entities;

import java.math.BigDecimal;

import br.com.digitalbank.enums.TipoTranferencia;

public class Tranferencia {

	private Long id;
	private Integer prazo;
	private Long idContaOrigem;
	private Long idContaDestino;
	private BigDecimal valorTransferido;

	public Tranferencia(Long id, Integer prazo, Long idContaOrigem, Long idContaDestino, BigDecimal valorTransferido) {

		this.prazo = prazo;
		this.idContaOrigem = idContaOrigem;
		this.idContaDestino = idContaDestino;
		this.valorTransferido = valorTransferido;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public Long getIdContaOrigem() {
		return idContaOrigem;
	}

	public void setIdContaOrigem(Long idContaOrigem) {
		this.idContaOrigem = idContaOrigem;
	}

	public Long getIdContaDestino() {
		return idContaDestino;
	}

	public void setIdContaDestino(Long idContaDestino) {
		this.idContaDestino = idContaDestino;
	}

	public BigDecimal getValueTransfer() {
		return valorTransferido;
	}

	public void setValueTransfer(BigDecimal valorTransferido) {
		this.valorTransferido = valorTransferido;
	}

}
