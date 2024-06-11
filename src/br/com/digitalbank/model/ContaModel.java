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
		// TODO Auto-generated method stub
		return null;
	}
	
	public ContaCorrente getContaPoupanca(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateContaCorrente(ContaCorrente contaCorrente) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateContaPoupanca(ContaPoupanca contaPoupanca) {
		// TODO Auto-generated method stub
		
	}
}
