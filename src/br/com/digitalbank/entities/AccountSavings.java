package br.com.digitalbank.entities;

import java.math.BigDecimal;

public class AccountSavings extends Account {

	public AccountSavings(Long idAgencia, Integer idCliente, Long id, String password) {
		super(idAgencia, idCliente, id, password);

	}

	private Double taxaCdi = 1.50;
	private BigDecimal saldo;

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Double getTaxaCdi() {
		return taxaCdi;
	}

}
