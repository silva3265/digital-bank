package br.com.digitalbank.model;

import br.com.digitalbank.Dao.ClienteDao;
import br.com.digitalbank.entities.Cliente;

public class ClienteModel {

	public Long cadastroCliente(Cliente cliente) {

		ClienteDao clienteDao = new ClienteDao();
		Long idGerado = clienteDao.cadastroCliente(cliente);

		return idGerado;
	}

	public Cliente getClienteByIdContaCorrente(Long idContaCorrente) {

		ClienteDao clienteDao = new ClienteDao();
		return clienteDao.getClienteByIdContaCorrente(idContaCorrente);

	}
	
	public Cliente getClienteById(Long idCliente) {
		ClienteDao clienteDao = new ClienteDao();
		
		return clienteDao.getClienteById(idCliente);
	}

}
