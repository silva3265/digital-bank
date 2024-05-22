package br.com.digitalbank.model;

import br.com.digitalbank.Dao.EnderecoDao;
import br.com.digitalbank.entities.Endereco;

public class EnderecoModel {
	
	public Long cadastroEndereco(Endereco endereco) {
		
		EnderecoDao enderecoDao = new EnderecoDao();
		Integer idGerado = enderecoDao.cadastroEndereco(endereco);
		
		return idGerado;
	}
}
