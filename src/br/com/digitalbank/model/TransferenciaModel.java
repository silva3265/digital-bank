package br.com.digitalbank.model;

import java.util.List;

import br.com.digitalbank.Dao.TransferenciaDao;
import br.com.digitalbank.entities.Transferencia;

public class TransferenciaModel {
	
	public void cadastroTransferencia(Transferencia transferencia) {
		
		TransferenciaDao transferenciaDao = new TransferenciaDao();
		transferenciaDao.cadastroTransferencia(transferencia);
		
	}
	
	public List<Transferencia> getTransferencias() {
		
		TransferenciaDao transferenciaDao = new TransferenciaDao();
		return transferenciaDao.getHistoricoTransferencia();
		
	}
	
	public Boolean vericarChavePixProprietario(Long idConta, String chavePix) {
		
		TransferenciaDao transferenciaDao = new TransferenciaDao();
		
		return transferenciaDao.vericarChavePixProprietario(idConta, chavePix);
	}

}
