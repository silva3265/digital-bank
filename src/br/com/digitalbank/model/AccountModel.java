package br.com.digitalbank.model;

import br.com.digitalbank.Dao.AccountDao;
import br.com.digitalbank.entities.Account;

public class AccountModel {

	public Account login(Long conta, String senha) {

		AccountDao accountDao = new AccountDao();
		Account account = accountDao.getAccount(conta, senha);
		
		return account;

	}
}
