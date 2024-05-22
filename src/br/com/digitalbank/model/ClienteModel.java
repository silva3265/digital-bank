package br.com.digitalbank.model;

import br.com.digitalbank.Dao.ClienteDao;
import br.com.digitalbank.entities.Cliente;

public class ClienteModel {
	
public Integer cadastroCliente(Cliente cliente) {
		
		ClienteDao clienteDao = new ClienteDao();
		Integer idGerado = clienteDao.cadastroCliente(cliente);
		
		return idGerado;
	}

}
