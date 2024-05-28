package br.com.digitalbank.model;

import java.util.List;

import br.com.digitalbank.Dao.AgenciaDao;
import br.com.digitalbank.entities.Agencia;

public class AgenciaModel {
	
	public List<Agencia> getAgencias(){
		
		AgenciaDao agenciaDao = new AgenciaDao();
		
		List<Agencia> agencias = agenciaDao.getAgencias();
		
		return agencias;
	}

}
