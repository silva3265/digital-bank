package br.com.digitalbank.model;

import br.com.digitalbank.Dao.ContaDao;
import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;

public class ContaModel {

	public Conta getLogin(Long numeroConta, String senha) {

		ContaDao contaDao = new ContaDao();
		Conta conta = contaDao.getLogin(numeroConta, senha);
		
		return conta;

	}
	
	public Conta selectContaByCpf (String cpf) {
		
		ContaDao contaDao = new ContaDao();
		Conta conta = contaDao.selectContaByCpf(cpf);
		
		return conta;
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
