package br.com.digitalbank.entities;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Long id, Long idContaPoupanca, Long idAgencia, Long idCliente, String password, Double taxaCdi,
			Double saldo) {
		super(id, idAgencia, idCliente, password);
		this.taxaCdi = taxaCdi;
		this.saldo = saldo;
		this.idContaPoupanca = idContaPoupanca;
	}

	public ContaPoupanca(Long idAgencia, Long idCliente, Long id, String password) {
		super(idAgencia, idCliente, id, password);

	}
	
	public ContaPoupanca(Long id) {
		super(id);

	}
	
	public ContaPoupanca(Long idConta, Long idContaPoupanca, Double taxaCdi, Double saldo) {
		super(idConta);
		
		this.idContaPoupanca = idContaPoupanca;
		this.taxaCdi = taxaCdi;
		this.saldo = saldo;
	}

	private Long idContaPoupanca;
	private Double taxaCdi = 1.50;
	private Double saldo;

	public Double getSaldo() {
		return saldo;
	}

	public Long getIdContaPoupanca() {
		return idContaPoupanca;
	}

	public void setIdContaPoupanca(Long idContaPoupanca) {
		this.idContaPoupanca = idContaPoupanca;
	}

	public void setTaxaCdi(Double taxaCdi) {
		this.taxaCdi = taxaCdi;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getTaxaCdi() {
		return taxaCdi;
	}

	@Override
	public Boolean depositar(double valor) {

		if (valor > 0) {
			saldo = saldo + valor;
		} else {
			return false; // Operação deu Errado
		}

		return true; // Vai Retornar verdadeiro

	}

}
