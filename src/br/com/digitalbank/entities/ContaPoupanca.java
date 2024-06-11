package br.com.digitalbank.entities;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Long idAgencia, Long idCliente, Long id, String password) {
		super(idAgencia, idCliente, id, password);

	}

	private Double taxaCdi = 1.50;
	private Double saldo;

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getTaxaCdi() {
		return taxaCdi;
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
