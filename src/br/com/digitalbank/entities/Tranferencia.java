package br.com.digitalbank.entities;

import java.math.BigDecimal;

import br.com.digitalbank.enums.TransferType;

public class Tranferencia {

	private TransferType tipo;
	private Integer prazo;
	private Long idContaOrigem;
	private Long idContaDestino;
	private BigDecimal valueTransfer;

	public Tranferencia(TransferType tipo, Integer prazo, Long idContaOrigem, Long idContaDestino,
			BigDecimal valueTransfer) {

		this.tipo = tipo;
		this.prazo = prazo;
		this.idContaOrigem = idContaOrigem;
		this.idContaDestino = idContaDestino;
		this.valueTransfer = valueTransfer;
	}

	public TransferType getTipo() {
		return tipo;
	}

	public void setTipo(TransferType tipo) {
		this.tipo = tipo;
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
		return valueTransfer;
	}

	public void setValueTransfer(BigDecimal valueTransfer) {
		this.valueTransfer = valueTransfer;
	}

}
