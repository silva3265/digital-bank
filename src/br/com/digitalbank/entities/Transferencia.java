package br.com.digitalbank.entities;

import java.time.LocalDate;

public class Transferencia {

	private Long id;
	private Long idContaOrigem;
	private Long idContaDestino;
	private Double valorTransferido;
	private LocalDate data;

	public Transferencia(Long id, Long idContaOrigem, Long idContaDestino, Double valorTransferido, LocalDate data) {
		this.id = id;
		this.idContaOrigem = idContaOrigem;
		this.idContaDestino = idContaDestino;
		this.valorTransferido = valorTransferido;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getValorTransferido() {
		return valorTransferido;
	}

	public void setValorTransferido(Double valorTransferido) {
		this.valorTransferido = valorTransferido;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
