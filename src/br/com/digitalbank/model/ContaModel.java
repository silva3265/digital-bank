package br.com.digitalbank.model;

import br.com.digitalbank.Dao.ContaCorrenteDao;
import br.com.digitalbank.Dao.ContaDao;
import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;

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
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean temContaPoupanca(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ContaCorrente getAccountCurrent(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
