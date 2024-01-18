package br.com.digitalbank.entities;

import java.math.BigDecimal;

import br.com.digitalbank.enums.AccountType;

public class AccountSavings extends Account {

	public AccountSavings(Long idAgencia, AccountType tipoConta, BigDecimal saldo, Integer idCliente) {
		super(idAgencia, tipoConta, saldo, idCliente);

	}

	private Double taxaCdi = 1.50;

	public Double getTaxaCdi() {
		return taxaCdi;
	}

}
