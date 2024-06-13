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
	
	public void updateContaPoupanca(ContaPoupanca contaPoupanca) {
		/* METODOS TRANSACIONAIS */
		
		String sql = " UPDATE Conta_Poupanca SET (saldo) VALUES (?) WHERE id = ?";
		
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql);
			
			stmt.setDouble(1, contaPoupanca.getSaldo()); /* o indice '1' é o nosso primeiro coringa '?' */
			stmt.setDouble(2, contaPoupanca.getIdContaPoupanca());

			stmt.execute();
			connection.commit(); /* se chegou no execute e não der exception, ele faz o commit 'salve as informaçoes'*/
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback(); /* rollback - voltar a versão anterior caso caia no 'catch'*/
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally { // sempre vai ser executado mesmo dando certo ou não, por isso ele se chama 'finally' 'finalmente'
			
			try {
				connection.close(); // FECHANDO A CONEXÃO, MESMO DANDO CERTO OU NÃO
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
