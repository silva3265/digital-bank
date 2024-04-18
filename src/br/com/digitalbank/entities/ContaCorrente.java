package br.com.digitalbank.entities;

import java.util.Scanner;

public class ContaCorrente extends Conta {

	public ContaCorrente(Long idAgencia, Long idCliente, Double taxa, Double limiteChequeEspecial, Long id,
			String password) {
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

	public Boolean saque(double valor) {
		Scanner sc = new Scanner(System.in);
		if (saldo <= 0 && valor <= getLimiteChequeEspecial()) {
			System.out.println("Gostaria de usar o seu Limite de Cheque especial");
			String resposta = sc.next();
			if (resposta.equalsIgnoreCase("Sim")) {
				System.out.println("Limite Disponivel: " + getLimiteChequeEspecial());
				setLimiteChequeEspecial(limiteChequeEspecial - valor);
				this.saldo -= valor;
			} else {
				System.out.println("Operação Cancelada!!");
				return false;

			}
		}
		this.saldo -= valor;
		return true;

	}
	
	@Override
	public Boolean deposito(double valor) {
		
		Scanner sc = new Scanner(System.in);

		if (valor > 0) {
			saldo = saldo + valor;
		}else {
			System.out.println("O Valor deve ser acima de 0");
			return false; //Operação deu Errado
		}

		return true; //Vai Retornar verdadeiro

	}

}
