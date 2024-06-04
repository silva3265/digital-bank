package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.digitalbank.entities.Conta;

public class ContaPoupancaDao {
	
	public Boolean temContaPoupanca(Long id) {
		
		String sql = "SELECT * FROM Conta_Poupanca WHERE idConta  = ? "; 
		
		Connection conexao;
		PreparedStatement stmt;
		Conta conta = null;
		try {
			conexao = new Conexao().getConnection();
			stmt = conexao.prepareStatement(sql);
			
			stmt.setLong(1, id); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */

			ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
			
			conexao.close(); 
			// resultSet - ele vai retornar verdadeiro se ele existir
			// Ele vai retornar apenas o primeiro objeto 
			 /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
			return resultSet.next();
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return false;
		
	}

}
