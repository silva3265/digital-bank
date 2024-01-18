package br.com.digitalbank.entities;

import java.math.BigDecimal;
import java.util.Scanner;

import br.com.digitalbank.enums.AccountType;

public class Account {

	private Long idAgencia;
	private AccountType tipoConta;
	private BigDecimal saldo;
	private Integer idCliente;

	public Account(Long idAgencia, AccountType tipoConta, BigDecimal saldo, Integer idCliente) {
		this.idAgencia = idAgencia;
		this.tipoConta = tipoConta;
		this.saldo = saldo;
		this.idCliente = idCliente;

	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public AccountType getAccountType() {
		return tipoConta;
	}

	public void setAccountType(AccountType tipoConta) {
		this.tipoConta = tipoConta;
	}

	public BigDecimal getValue() {
		return saldo;
	}

	public void setValue(BigDecimal value) {
		this.saldo = value;
	}

	public Integer getIdClient() {
		return idCliente;
	}

	public void setIdClient(Integer idClient) {
		this.idCliente = idClient;
	}

	public BigDecimal withdraw(BigDecimal value) { // Saque
		if (saldo  ) {
			
		}
		return value;

	}
	
	public BigDecimal deposit(BigDecimal value) {
		
		Scanner sc  = new Scanner(System.in);
		value = sc.nextBigDecimal();
		
		return value;
		
	}

}
