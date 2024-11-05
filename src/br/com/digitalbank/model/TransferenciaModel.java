package br.com.digitalbank.model;

import br.com.digitalbank.Dao.TransferenciaDao;
import br.com.digitalbank.entities.Transferencia;

public class TransferenciaModel {
	
	public void cadastroTransferencia(Transferencia transferencia) {
		
		TransferenciaDao transferenciaDao = new TransferenciaDao();
		transferenciaDao.cadastroTransferencia(transferencia);
		
	}

}
