package br.com.digitalbank.model;

import br.com.digitalbank.Dao.ContaCorrenteDao;
import br.com.digitalbank.Dao.ContaDao;
import br.com.digitalbank.Dao.ContaPoupancaDao;
import br.com.digitalbank.entities.ChavePixContaCorrente;
import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;
import br.com.digitalbank.entities.ContaPoupanca;

public class ContaModel {

	public Conta getLogin(String cpf, String senha) {

		ContaDao contaDao = new ContaDao();
		Conta conta = contaDao.getLogin(cpf, senha);
		
		return conta;

	}
	
	public Conta selectContaByCpf (String cpf) {
		
		ContaDao contaDao = new ContaDao();
		Conta conta = contaDao.selectContaByCpf(cpf);
		
		return conta;
	} 
	
	public Long cadastroContaCorrente(ContaCorrente contaCorrente) {
		
		ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();
		
		Long idConta = contaCorrenteDao.cadastroContaCorrente(contaCorrente);
		
		return idConta;
		
	}
	
	public void cadastroContaPoupanca(ContaPoupanca contaPoupanca) {
		
		ContaPoupancaDao contaPoupancaDao = new ContaPoupancaDao();
		
		if (!temContaPoupanca(contaPoupanca.getId())) { // se ele nao tiver conta poupança, vai criar uma
			contaPoupancaDao.cadastroContaPoupanca(contaPoupanca);
		}
		
	}

	public Boolean temContaCorrente(Long id) {
		
		ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();
		Boolean temContaCorrente = contaCorrenteDao.temContaCorrente(id);
		
		return temContaCorrente;
	}

	public Boolean temContaPoupanca(Long id) {
		ContaPoupancaDao contaPoupancaDao = new ContaPoupancaDao();
		Boolean temContaPoupanca = contaPoupancaDao.temContaPoupanca(id);
		
		return temContaPoupanca;
	}

	public ContaCorrente getContaCorrente(Long id) {
		ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();
		return contaCorrenteDao.getContaCorrenteByIdConta(id);
		
	}
	
	public ContaPoupanca getContaPoupanca(Long id) {
		ContaPoupancaDao contaPoupancaDao = new ContaPoupancaDao();
		return contaPoupancaDao.getContaPoupanca(id);
	}

	public void updateContaCorrente(ContaCorrente contaCorrente) {
		ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();
		contaCorrenteDao.updateContaCorrente(contaCorrente);
	}
	
	public void updateContaPoupanca(ContaPoupanca contaPoupanca) {
		ContaPoupancaDao ContaPoupancaDao = new ContaPoupancaDao();
		ContaPoupancaDao.updateContaPoupanca(contaPoupanca);
	}
	
	public void depositoContaCorrente(Conta conta, Double valor) {
		
		ContaCorrente contaCorrente = getContaCorrente(conta.getId()); // pra buscar precisamos buscar pelo o id da conta
		Boolean depositoContaCorrente = contaCorrente.depositar(valor); // quem faz a alteração do valor do saldo é o objeto conta corrente, por meio do metoo deposito
		if (depositoContaCorrente == true) {
			System.out.println("Deposito Foi concluido com sucesso!!");
			updateContaCorrente(contaCorrente);
		}else {
			System.out.println("O Valor deve ser acima de 0");
		}
	}
	
	public void depositoContaPoupanca(Conta conta, Double valor) {
		ContaPoupanca contaPoupanca = getContaPoupanca(conta.getId()); // pra buscar precisamos buscar pelo o id da conta
		Boolean depositoContaPoupanca = contaPoupanca.depositar(valor); // quem faz a alteração do valor do saldo é o objeto conta corrente, por meio do metoo deposito
		if (depositoContaPoupanca == true) {
			System.out.println("Deposito Foi concluido com sucesso!!");
			updateContaPoupanca(contaPoupanca);
		}else {
			System.out.println("O Valor deve ser acima de 0");
		}
	}
	
	public Double getSaldoContaCorrente(Long idConta) {
		ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();
		Double saldo = contaCorrenteDao.getSaldoContaCorrente(idConta);
		return saldo;
	}
	
	public Double getSaldoContaPoupanca(Long idConta) {
		ContaPoupancaDao contaPoupancaDao = new ContaPoupancaDao();
		Double saldoContaPoupanca = contaPoupancaDao.getSaldoContaPoupanca(idConta);
		
		return saldoContaPoupanca;
	}
	
	public ChavePixContaCorrente getChavePixContaCorrente(String chave) {
		
		ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();
		
		return contaCorrenteDao.getChavePixContaCorrente(chave);
		
	} 
	
	
	public void temChavePix() {
		
	}

	public ContaCorrente getContaCorrenteByIdContaCorrente(Long idContaCorrente) {
		ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();
		
		return contaCorrenteDao.getContaCorrenteByIdContaCorrente(idContaCorrente);
		
	}
}
