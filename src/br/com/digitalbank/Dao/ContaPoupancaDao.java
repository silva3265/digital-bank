package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;
import br.com.digitalbank.entities.ContaPoupanca;

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
	
	public ContaPoupanca getContaPoupanca(Long id) { // getContaPoupanca - depois do get vem o retorno e depois do 'by' o parametro
		String sql = "SELECT * FROM Conta_Poupanca WHERE idConta  = ? "; 
		
		Connection conexao;
		PreparedStatement stmt;
		ContaPoupanca contaPoupanca = null;
		try {
			conexao = new Conexao().getConnection();
			stmt = conexao.prepareStatement(sql);
			
			stmt.setLong(1, id); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */

			ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
			
			conexao.close(); 
			// resultSet - ele vai retornar verdadeiro se ele existir
			// Ele vai retornar apenas o primeiro objeto 
			 /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
			if (resultSet.next()) { // só vai ser chamado uma vez, só vai retornar um resultado, por estamos buscando apenas UMA conta
				// estamos convertendo os dados que vieram do banco de dados "Problema: No banco tem tabela e no Java só temos Objeto, por isso usamos o 'resultset' pra fazer a conversão"
				contaPoupanca = new ContaPoupanca(resultSet.getLong("id"), resultSet.getLong("idContaPoupanca") ,resultSet.getLong("idAgencia"), resultSet.getLong("idCliente"), resultSet.getString("password"), resultSet.getDouble("taxaCdi"), resultSet.getDouble("saldo")); // resultSet.getLong("idAgencia") - entre as aspas esta o nome da coluna
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return contaPoupanca;
		
	}

}
