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
	
	String sql = " INSERT INTO Transferencia (idContaOrigem, idContaDestino, valor, idChavePixDestino, data) VALUES (?, ?, ?, ?, ?)";
	
	Connection connection = null;
	PreparedStatement stmt = null;
	Long idConta = null;
	try {
		connection = new Conexao().getConnection();
		connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
		stmt = connection.prepareStatement(sql);
		
		//stmt.setDouble(1, transferencia.getId()); /* id auto incremento */
		stmt.setDouble(1, transferencia.getIdContaOrigem()); /* o indice '2' éo nosso segundo coringa '?' */
		stmt.setDouble(2, transferencia.getIdContaDestino());
		stmt.setDouble(3, transferencia.getValorTransferido());
		stmt.setLong(4, transferencia.getIdChavePixDestino());
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
				transferencia = new Transferencia(result.getLong("id"), result.getLong("idContaOrigem"), result.getLong("idContaDestino"), result.getDouble("valorTransferido"), result.getLong("idChavePixOrigem"), result.getLong("idChavePixDestino") , result.getDate("data").toLocalDate());
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
	
	public Boolean vericarChavePixProprietario(Long idConta, String chavePix) { // verificar se a chave pix inserida é do prorprio prorpiegtario
		
		/* METODOS NÃO TRANSACIONAIS */

		// 
		String sql = " SELECT c.*, cc.*, cpx.* FROM Conta c inner join Conta_Corrente cc on c.id = cc.idConta inner join chavepix_contas_correntes cpx on cc.id = cpx.idContaCorrente where c.id = ? and cpx.chave = ? ";
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql);
		
			stmt.setLong(1, idConta);
			stmt.setString(2, chavePix);
			
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				return true;
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
		
		return false;
	}
	
	
	
	

}
