package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.digitalbank.entities.Transferencia;

public class TransferenciaDao {
	
	
	public void cadastroTransferencia(Transferencia transferencia) {
	
	/* METODOS TRANSACIONAIS */
	
	String sql = " INSERT INTO Transferencia (id, idContaOrigem, idContaDestino, valorTransferido, data) VALUES (?, ?, ?, ?, ?)";
	
	Connection connection = null;
	PreparedStatement stmt = null;
	Long idConta = null;
	try {
		connection = new Conexao().getConnection();
		connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
		stmt = connection.prepareStatement(sql);
		
		stmt.setDouble(1, transferencia.getId()); /* o indice '1' é o nosso primeiro coringa '?' */
		stmt.setDouble(2, transferencia.getIdContaOrigem()); /* o indice '2' éo nosso segundo coringa '?' */
		stmt.setDouble(3, transferencia.getIdContaDestino());
		stmt.setDouble(4, transferencia.getValorTransferido());
		stmt.setDate(5, Date.valueOf(transferencia.getData()) );
		
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
	
	public List<Transferencia> getHistoricoTransferencia() {
		
		/* METODOS TRANSACIONAIS */
		
		String sql = " SELECT * FROM Transferencia";
		
		Connection connection = null;
		PreparedStatement stmt = null;
		Long idConta = null;
		Transferencia transferencia = null;
		
		List<Transferencia> lista = new ArrayList<>();
		
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql);
		
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				transferencia = new Transferencia(result.getLong("id"), result.getLong("idOrigem"), result.getLong("idDestino"), result.getDouble("valorTransferido"), result.getDate("data").toLocalDate());
				lista.add(transferencia);
			}
			
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
		
		return lista;
	}
	
	

}
