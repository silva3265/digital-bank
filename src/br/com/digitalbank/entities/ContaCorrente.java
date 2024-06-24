package br.com.digitalbank.entities;

import java.util.Scanner;

public class ContaCorrente extends Conta {

	public ContaCorrente(Long id, Long idAgencia, Long idContaCorrente, Long idCliente,  String password, Double saldo) {
		super(idAgencia, idCliente, id, password);
		this.taxa = 0.05;
		this.limiteChequeEspecial = 0.0;
		this.idContaCorrente = idContaCorrente;
		this.saldo = saldo;
	}

	public ContaCorrente(Long idAgencia, Long idCliente, String password) {
		super(idAgencia, idCliente, password);
		this.taxa = 0.05;
		this.limiteChequeEspecial = 0.0;
		this.saldo = 0.0;
	}

	private Long idContaCorrente;
	private Double taxa;
	private Double limiteChequeEspecial = 1000.0;
	private Double saldo;

	public Long getIdContaCorrente() {
		return idContaCorrente;
	}

	public void setIdContaCorrente(Long idContaCorrente) {
		this.idContaCorrente = idContaCorrente;
	}

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

	public Integer saque(double valor) {
		// 1 - Certo - Ele tem saldo e o saque foi feito (somente o saldo)
		// 0 - Errado - Nao tem saldo e nem limite de cheque especial - OK
		// -1 - Nao tem nada de saldo e o usuario usa o limite de cheque especial
		// -2 - Tem um pouco de saldo e usa um pouco do limite de cheque especial
		if (saldo >= valor) { // 1
			return 1;
		}else if (saldo < valor && getLimiteChequeEspecial() < valor) { // 0
			return 0;
		}if (saldo < valor) {
			//setLimiteChequeEspecial(limiteChequeEspecial - valor);
			this.limiteChequeEspecial = this.limiteChequeEspecial - valor; // -1
			return -1;
		} else {
			double resultado = valor - this.saldo;
			Double resultadoPositivo = Math.abs(resultado); // vai converter
			if (resultadoPositivo > this.limiteChequeEspecial) {
				this.limiteChequeEspecial = this.limiteChequeEspecial - resultadoPositivo;
			}
		}

		this.saldo -= valor;
		return true;

	}

	@Override
	public Boolean deposito(double valor) {

		if (valor > 0) {
			saldo = saldo + valor;
		} else {
			return false; // Operação deu Errado
		}

		return true; // Vai Retornar verdadeiro

	}

}
