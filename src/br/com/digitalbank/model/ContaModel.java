package br.com.digitalbank.model;

import br.com.digitalbank.Dao.ContaDao;
import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;

public class ContaModel {

	public Conta login(Long conta, String senha) {

		ContaDao accountDao = new ContaDao();
		Conta account = accountDao.getAccount(conta, senha);
		
		return account;

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
