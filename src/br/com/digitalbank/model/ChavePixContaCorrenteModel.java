package br.com.digitalbank.model;

import br.com.digitalbank.Dao.ChavePixContaCorrenteDao;

public class ChavePixContaCorrenteModel {
	
	public String getChavePixContaCorrente(Long id) {
		
		ChavePixContaCorrenteDao chavePixContaCorrenteDao = new ChavePixContaCorrenteDao();
		
		return chavePixContaCorrenteDao.getChavePixContaCorrente(id);
		
	}
	
	public Long getChavePixByChave(String chave) {
		
		ChavePixContaCorrenteDao chavePixContaCorrenteDao = new ChavePixContaCorrenteDao();
		
		return chavePixContaCorrenteDao.getChavePixByChave(chave);
	}

}
