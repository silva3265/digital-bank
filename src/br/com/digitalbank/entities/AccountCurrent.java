package br.com.digitalbank.entities;

import java.math.BigDecimal;

import br.com.digitalbank.enums.AccountType;

public class AccountCurrent extends Account {

	public AccountCurrent(Long idAgencia, AccountType tipoConta, BigDecimal saldo, Integer idCliente, Double taxa,
			BigDecimal limiteChequeEspecial) {
		super(idAgencia, tipoConta, saldo, idCliente);
		this.taxa = taxa; // o Atributo esta recebendo a taxa do parametro
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

	private Double taxa;
	private BigDecimal limiteChequeEspecial;

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public BigDecimal getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public void setLimiteChequeEspecial(BigDecimal limiteChequeEspecial) {
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

}
