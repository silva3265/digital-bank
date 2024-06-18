package br.com.digitalbank.model;

import br.com.digitalbank.Dao.ContaCorrenteDao;
import br.com.digitalbank.Dao.ContaDao;
import br.com.digitalbank.Dao.ContaPoupancaDao;
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
	
	public void cadastroConta(ContaCorrente contaCorrente) {
		
		ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();
		
		contaCorrenteDao.cadastroContaCorrente(contaCorrente);
		
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
		return contaCorrenteDao.getContaCorrente(id);
		
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
		Boolean depositoContaCorrente = contaCorrente.deposito(valor); // quem faz a alteração do valor do saldo é o objeto conta corrente, por meio do metoo deposito
		if (depositoContaCorrente == true) {
			System.out.println("Deposito Foi concluido com sucesso!!");
			updateContaCorrente(contaCorrente);
		}else {
			System.out.println("O Valor deve ser acima de 0");
		}
	}
	
	public void depositoContaPoupanca(Conta conta, Double valor) {
		ContaPoupanca contaPoupanca = getContaPoupanca(conta.getId()); // pra buscar precisamos buscar pelo o id da conta
		Boolean depositoContaPoupanca = contaPoupanca.deposito(valor); // quem faz a alteração do valor do saldo é o objeto conta corrente, por meio do metoo deposito
		if (depositoContaPoupanca == true) {
			System.out.println("Deposito Foi concluido com sucesso!!");
			updateContaPoupanca(contaPoupanca);
		}else {
			System.out.println("O Valor deve ser acima de 0");
		}
	}
	
	public void getSaldoContaCorrente() {
		ContaCorrenteDao contaCorrenteDao = new ContaCorrenteDao();
		contaCorrenteDao.getSaldoContaCorrente();
	}
	
	public void getSaldoContaPoupanca() {
		
	}
}
