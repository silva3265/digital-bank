package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.digitalbank.entities.Conta;


public class ContaDao {
	
	/* AS TRES PRINCIPAIS INSTRUÇÕES DE DENTRO DE UM METODO DA DAO SÃO: SQL (QUERY), CONEXÃO, E CHAMADA DA CONEXÃO */
	public Conta getLogin(Long idConta, String senha) {
		String sql = "SELECT * FROM Conta WHERE id = ? AND password = ?";
		
		Connection conexao;
		PreparedStatement stmt;
		Conta conta = null;
		try {
			conexao = new Conexao().getConnection();
			stmt = conexao.prepareStatement(sql);
			
			stmt.setLong(1, idConta); /* Essa função esta substituindo o nosso coringa da query nome = '?'*/
			stmt.setString(2, senha);
			
			ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
			
			 
			// resultSet - ele vai retornar verdadeiro se ele existir
			// Ele vai retornar apenas o primeiro objeto 
			if (resultSet.next()) { /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
				// ele criar uma instancia do objeto conta, se nao existir a conta vai retornar uma conta Nula
				conta = new Conta(resultSet.getLong("id"),resultSet.getLong("idAgencia"), resultSet.getLong("idCliente"), resultSet.getString("password"));
			}
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return conta;
		
	}
	
	public Conta selectContaByCpf (String cpf) {
		
		// Conta ct = new Conta(); exemplo
		// 1 - Forma
		// String sql = " SELECT Cliente.id, Conta.id, Conta.idAgencia, Conta.idCliente FROM Cliente cliente inner join Conta ct on Cliente.id = Conta.idCliente WHERE Cliente.cpf = ? ";
		// - Forma
		String sql = " SELECT cl.id, ct.id, ct.idAgencia, ct.idCliente FROM Cliente cl inner join Conta ct on cl.id = ct.idCliente WHERE cl.cpf = ? "; // cl.id - id da cliente
		
		Connection conexao;
		PreparedStatement stmt;
		Conta conta = null;
		try {
			conexao = new Conexao().getConnection();
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, cpf); /* Essa função esta substituindo o nosso coringa da query nome = '?'*/
		
			ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
			
			 
			// resultSet - ele vai retornar verdadeiro se ele existir
			// Ele vai retornar apenas o primeiro objeto 
			if (resultSet.next()) { /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
				// ele criar uma instancia do objeto conta, se nao existir a conta vai retornar uma conta Nula
				conta = new Conta(resultSet.getLong("ct.id"),resultSet.getLong("ct.idAgencia"), resultSet.getLong("ct.idCliente"));
			}
			conexao.close();
		} catch (SQLException e) { // só vai uma exceção dentro do cacth caso ocorra um sql exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return conta;
		
		
	}

}
