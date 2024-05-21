package br.com.digitalbank.entities;

import java.math.BigDecimal;

import br.com.digitalbank.enums.TipoTranferencia;

public class Tranferencia {

	private Long id;
	private TipoTranferencia tipo;
	private Integer prazo;
	private Long idContaOrigem;
	private Long idContaDestino;
	private BigDecimal valueTransfer;

	public Tranferencia(Long id, TipoTranferencia tipo, Integer prazo, Long idContaOrigem, Long idContaDestino,
			BigDecimal valueTransfer) {

		this.tipo = tipo;
		this.prazo = prazo;
		this.idContaOrigem = idContaOrigem;
		this.idContaDestino = idContaDestino;
		this.valueTransfer = valueTransfer;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoTranferencia getTipo() {
		return tipo;
	}

	public void setTipo(TipoTranferencia tipo) {
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
