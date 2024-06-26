package br.com.digitalbank.entities;

public class ContaCorrente extends Conta {

	public ContaCorrente(Long id, Long idAgencia, Long idContaCorrente, Long idCliente, String password, Double saldo) {
		super(idAgencia, idCliente, id, password);
		this.taxa = 0.05;
		this.limiteChequeEspecial = 1000.0;
		this.idContaCorrente = idContaCorrente;
		this.saldoChequeEspecial = 1000.0;
		this.saldo = saldo;
	}

	public ContaCorrente(Long idAgencia, Long idCliente, String password) {
		super(idAgencia, idCliente, password);
		this.taxa = 0.05;
		this.limiteChequeEspecial = 1000.0;
		this.saldoChequeEspecial = 1000.0;
		this.saldo = 0.0;
	}

	private Long idContaCorrente;
	private Double taxa;
	private Double limiteChequeEspecial;
	private Double saldo;
	private Double saldoChequeEspecial;

	public Double getSaldoChequeEspecial() {
		return saldoChequeEspecial;
	}

	public void setSaldoChequeEspecial(Double saldoChequeEspecial) {
		this.saldoChequeEspecial = saldoChequeEspecial;
	}

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
		// -2 - Tem um pouco de saldo e usa um pouco do limite de cheque especial
		// -1 - Nao tem nada de saldo e o usuario usa o limite de cheque especial

		if (this.saldo >= valor) { // 1
			this.saldo = this.saldo - valor;

			return 1;
		} else if (this.saldo < valor && getLimiteChequeEspecial() < valor) { // 0

			return 0;

		} else if (this.saldo < valor && saldo > 0) { // siguinifica que ele nao tem todo o valor pra sacar, mas tem um
														// pouco de saldo + cheque especial
			// setLimiteChequeEspecial(limiteChequeEspecial - valor);
			Double resultado = valor - this.saldo;
			Double resultadoPositivo = Math.abs(resultado); // vai converter pra absouluto e vai retornar um valor
															// absoluto do double
			if (resultadoPositivo < this.saldoChequeEspecial) {
				this.saldoChequeEspecial = this.saldoChequeEspecial - resultadoPositivo; // vai caber no limite cheque
																							// especial
				this.saldo = 0.0; // zeramos o saldo porque estamos ja usando o limite de cheque especial e o
									// valor ficara negativo
				return -2;
			}

		} else {
			this.saldoChequeEspecial = this.saldoChequeEspecial - valor; // -1
			return -1;
		}
		return 0; // vai retornar 0 caso nao der certo nenhuma das condições

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
