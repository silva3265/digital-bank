package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.digitalbank.entities.ContaCorrente;


public class ContaCorrenteDao {
	
	public void cadastroConta(ContaCorrente conta) {
		
		/* METODOS TRANSACIONAIS */
		String sql = " INSERT INTO Conta (id, idAgencia, idCliente, password) VALUES (?, ?, ?, ? )";
		
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, conta.getId()); /* o indice '1' é o nosso primeiro coringa '?' */
			stmt.setLong(2, conta.getIdAgencia()); /* o indice '2' éo nosso segundo coringa '?' */
			stmt.setLong(3, conta.getIdCliente());
			stmt.setString(4, conta.getPassword());
			
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

}
