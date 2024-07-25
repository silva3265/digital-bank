package br.com.digitalbank.entities;

public class ContaCorrente extends Conta {

	// Construtor para Seleção (Busca)
	public ContaCorrente(Long id, Long idAgencia, Long idContaCorrente, Long idCliente, String password, Double saldo, Double saldoChequeEspecial, Double limiteChequeEspecial, Double taxa) {
		super(id, idAgencia, idCliente, password);
		this.taxa = 0.05;
		this.limiteChequeEspecial = limiteChequeEspecial;
		this.idContaCorrente = idContaCorrente;
		this.saldoChequeEspecial = saldoChequeEspecial;
		this.saldo = saldo;
	}
	// Construtor para Inserção
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

	public Integer sacar(double valor) {
		// 1 - Certo - Ele tem saldo e o saque foi feito (somente o saldo)
		// 0 - Errado - Nao tem saldo e nem limite de cheque especial - OK
		// -2 - Tem um pouco de saldo e usa um pouco do limite de cheque especial
		// -1 - Nao tem nada de saldo e o usuario usa o limite de cheque especial

		if (this.saldo >= valor) { // 1
			this.saldo = this.saldo - valor;

			return 1;
		} else if (this.saldo < valor && getSaldoChequeEspecial() < valor && (this.saldo + this.saldoChequeEspecial) < valor) { // 0

			return 0;

		} else if ((this.saldo < valor && this.saldo > 0) && (this.saldo + this.saldoChequeEspecial) >= valor) { // siguinifica que ele nao tem todo o valor pra sacar, mas tem um
														// pouco de saldo + cheque especial
			// setLimiteChequeEspecial(limiteChequeEspecial - valor);
			Double resultado = valor - this.saldo;
			Double resultadoPositivo = Math.abs(resultado); // vai converter pra absoluto e vai retornar um valor
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
	public Boolean depositar(double valor) {

		if (valor > 0) {
			saldo = saldo + valor;
		} else {
			return false; // Operação deu Errado
		}

		return true; // Vai Retornar verdadeiro

	}
	
	public Integer transferir(double valor, ContaCorrente contaCorrenteDestino) { // na entidades definimos somente metodos relacionado a conta corrente
		
		// 1 - Certo - Ele tem saldo e a Transferencia foi feita (somente o saldo)
		// 0 - Errado - Nao tem saldo e nem limite de cheque especial - OK
		// -2 - Tem um pouco de saldo e usa um pouco do limite de cheque especial (vai juntar um pouco de cada)
				// * o saldo é menor que o valor e o saldo do cheque especial nao importa se menor ou maior que o valor
		// -1 - Nao tem nada de saldo e o usuario usa o limite de cheque especial

		// Usando a propria Instancia
		if (this.saldo > valor) { // chamando o proprio atributo
			this.saldo = this.saldo - valor; // processo para retirar o valor da conta que estamos enviando
			
			// Usando a instancia passada no parametro
			contaCorrenteDestino.saldo = contaCorrenteDestino.saldo + valor; // conta corrente destino que esta recebendo
			
			return 1; // Deu Certo
		}else if (this.saldo < valor && getSaldoChequeEspecial() < valor && (this.saldo + this.saldoChequeEspecial) < valor) { // (this.saldo + this.saldoChequeEspecial) -  a soma do saldo mais cheque especial
			
			return 0; // Deu Errado
		}else if (this.saldo < valor && this.saldo > 0 && (this.saldo + this.saldoChequeEspecial) >= valor) {
			
			Double resultadoAbsoluto = Math.abs(valor - this.saldo); // convertendo em valor absoluto 
			
			if (resultadoAbsoluto <= this.saldoChequeEspecial) {
				this.saldoChequeEspecial = this.saldoChequeEspecial - resultadoAbsoluto; 
				this.saldo = 0.0; 
				
				return -2; // tem um pouco de saldo e um pouco de limite de cheque especial
			}
		}else if (this.saldo <= 0) {
			this.setSaldoChequeEspecial(this.getSaldoChequeEspecial() - valor); // o this é a conta de origem e 'contaCorrenteDestino' é a conta destino
			
			return -1;
		} 
		
		return 0; // 0 - Errado - Nao tem saldo e nem limite de cheque especial
		
	}
	
}
