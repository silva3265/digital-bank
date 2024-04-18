package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.digitalbank.entities.Conta;


public class ContaDao {
	
	public Conta getAccount(Long idAccount, String password) {
		String sql = "SELECT * FROM account WHERE id = ? AND password = ?";
		
		Connection conexao;
		PreparedStatement stmt;
		Conta account = null;
		try {
			conexao = new Conexao().getConnection();
			stmt = conexao.prepareStatement(sql);
			
			stmt.setLong(1, idAccount); /* Essa função esta substituindo o nosso coringa da query nome = '?'*/
			stmt.setString(2, password);
			
			ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
			
			 
			// resultSet - ele vai retornar verdadeiro se ele existir
			// Ele vai retornar apenas o primeiro objeto 
			if (resultSet.next()) { /* next() - informa se existe um proximo Objeto (Registro), proxima coluna */
				account = new Conta(resultSet.getLong("id"),resultSet.getLong("idCliente"), resultSet.getLong("idConta"), resultSet.getString("password"));
			}
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return account;
		
	}

}
