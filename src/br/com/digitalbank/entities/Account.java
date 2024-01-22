package br.com.digitalbank.entities;

import java.math.BigDecimal;
import java.util.Scanner;

import br.com.digitalbank.enums.AccountType;

public class Account {

	private Long idAgencia;
	private AccountType tipoConta;
	private BigDecimal saldo;
	private Integer idCliente;
	private Long id;
	private String password;

	public Account(Long idAgencia, AccountType tipoConta, BigDecimal saldo, Integer idCliente, Long id,
			String password) {
		this.idAgencia = idAgencia;
		this.tipoConta = tipoConta;
		this.saldo = saldo;
		this.idCliente = idCliente;
		this.id = id;
		this.password = password;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public AccountType getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(AccountType tipoConta) {
		this.tipoConta = tipoConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public BigDecimal withdraw(BigDecimal value) { // Saque
//		if (saldo) {
//
//		}
//		return value;
//
//	}

	public BigDecimal deposit(double value) {

		if (value > 0) {
			saldo = saldo.add(BigDecimal.valueOf(value)); // converteu um double para Bigdecimal

		}

		return saldo;

	}

}
