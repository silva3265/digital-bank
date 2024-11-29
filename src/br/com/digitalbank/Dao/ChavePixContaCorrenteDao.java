package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.digitalbank.entities.Cliente;

public class ChavePixContaCorrenteDao {
	
	public String getChavePixContaCorrente(Long id) {
		
		String sql = " SELECT * FROM ChavePixContaCorrente where id = ? "; 
		
		Connection conexao;
		PreparedStatement stmt;
		Cliente cliente = null;
		String chavePixContaCorrente = null;
		try {
			conexao = new Conexao().getConnection();
			stmt = conexao.prepareStatement(sql);
			
			stmt.setLong(1, id); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */

			ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
			
			
			// resultSet - ele vai retornar verdadeiro se ele existir
			// Ele vai retornar apenas o primeiro objeto 
			 /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
			if (resultSet.next()) { // só vai ser chamado uma vez, só vai retornar um resultado, por estamos buscando apenas UMA conta
				// estamos convertendo os dados que vieram do banco de dados "Problema: No banco tem tabela e no Java só temos Objeto, por isso usamos o 'resultset' pra fazer a conversão"
				chavePixContaCorrente = resultSet.getString("chave");
			}
			conexao.close(); 	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return chavePixContaCorrente;
		
		
	} 

}
