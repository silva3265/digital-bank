package br.com.digitalbank.entities;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountCurrent extends Account {

	public AccountCurrent(Long idAgencia, Integer idCliente, Double taxa,
			Double limiteChequeEspecial, Long id, String password) {
		super(idAgencia, idCliente, id, password);
		this.taxa = taxa; // o Atributo esta recebendo a taxa do parametro
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

	private Double taxa;
	private Double limiteChequeEspecial = 1000.0;
	private Double saldo;
	
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Double getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public void setLimiteChequeEspecial(Double limiteChequeEspecial) {
		this.limiteChequeEspecial = limiteChequeEspecial;
	}
	
	public Double saque(double valor) { 
		Scanner sc = new Scanner(System.in);
		if (saldo <= 0 && valor <= getLimiteChequeEspecial()) {
			System.out.println("Gostaria de usar o seu Limite de Cheque especial" + getLimiteChequeEspecial());
			String resposta = sc.next();
			if (resposta.equalsIgnoreCase("Sim")) {
				this.saldo -= valor;
				setLimiteChequeEspecial(limiteChequeEspecial - valor);
			}
		}else {
			this.saldo -= valor;
		}
		return saldo;

	}

	public BigDecimal deposito(double value) {

	if (value > 0) {
			saldo = saldo.add(BigDecimal.valueOf(value)); // converteu um double para Bigdecimal

		}

		return saldo;

	}
	
	

}
