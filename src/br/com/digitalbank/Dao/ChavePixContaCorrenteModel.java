package br.com.digitalbank.Dao;

public class ChavePixContaCorrenteModel {
	
	public String getChavePixContaCorrente(Long id) {
		
		ChavePixContaCorrenteDao chavePixContaCorrenteDao = new ChavePixContaCorrenteDao();
		
		return chavePixContaCorrenteDao.getChavePixContaCorrente(id);
		
	}

}
