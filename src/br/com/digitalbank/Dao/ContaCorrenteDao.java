package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;


public class ContaCorrenteDao {
	/* o metodo cadastrar conta só cadastra informações gerais da Conta Corrente na tabela 'Conta' */
	public Integer cadastroConta(ContaCorrente conta) { // o metodo cadastro conta retorna o id gerado no momento da inserção da conta
		
		/* METODOS TRANSACIONAIS */
		String sql = " INSERT INTO Conta (idAgencia, idCliente, password) VALUES (?, ?, ? )";
		
		
		Connection connection = null;
		PreparedStatement stmt = null;
		Integer idGerado = null;
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setLong(1, conta.getIdAgencia()); /* o indice '2' éo nosso segundo coringa '?' */
			stmt.setLong(2, conta.getIdCliente());
			stmt.setString(3, conta.getPassword());
			
			stmt.execute();
			connection.commit(); /* se chegou no execute e não der exception, ele faz o commit 'salve as informaçoes'*/
			
			ResultSet rs = stmt.getGeneratedKeys(); // vai retornar as chaves geradas
			
			if (rs.next()) {
				idGerado = rs.getInt(1); // o valor que gerar aqui vai estar na coluna 1 do resultset
			}
			
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
		finally {
			
			try {
				connection.close(); // FECHANDO A CONEXÃO, MESMO DANDO CERTO OU NÃO
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return idGerado;
	}
/* o metodo cadastrar conta corrente só cadastra informações ESPECIFICAS da Conta Corrente na tabela 'Conta Corrente' */
public void cadastroContaCorrente(ContaCorrente conta) {
		
		/* METODOS TRANSACIONAIS */
		
		String sql = " INSERT INTO Conta_Corrente (taxa, saldo, limiteChequeEspecial, idConta) VALUES (?, ?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql);
			
			stmt.setDouble(1, conta.getTaxa()); /* o indice '1' é o nosso primeiro coringa '?' */
			stmt.setDouble(2, conta.getSaldo()); /* o indice '2' éo nosso segundo coringa '?' */
			stmt.setDouble(3, conta.getLimiteChequeEspecial());
			int idConta = cadastroConta(conta); // como nao temos como cadastrar uma 'conta corrente' sem cadastrar uma 'conta' primeiro, chamamos o metodo cadastroConta recebendo o parametro do metodo cadastroContaCorrente 
			stmt.setInt(4, idConta);
			
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
		finally {
			
			try {
				connection.close(); // FECHANDO A CONEXÃO, MESMO DANDO CERTO OU NÃO
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

public Boolean temContaCorrente(Long id) {
	
	String sql = "SELECT * FROM Conta_Corrente WHERE idConta  = ? "; 
	
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
		if (resultSet.next()) { /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
			return true;
			
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	return false;
	
}

}
