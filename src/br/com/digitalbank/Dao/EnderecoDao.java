package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.digitalbank.entities.Endereco;

public class EnderecoDao {
	
public Long cadastroEndereco(Endereco endereco) { // o metodo cadastro endereco retorna o id gerado no momento da inserção da conta
		
		/* METODOS TRANSACIONAIS */
		String sql = " INSERT INTO Endereco (rua, numero, cep, complemento) VALUES (?, ?, ?, ?)";
		
		
		Connection connection = null;
		PreparedStatement stmt = null;
		Long idGerado = null;
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // pra saber o numero do id
			
			stmt.setString(1, endereco.getRua()); /* o indice '2' éo nosso segundo coringa '?' */
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getCep());
			stmt.setString(4, endereco.getComplemento());
			
			stmt.execute();
			connection.commit(); /* se chegou no execute e não der exception, ele faz o commit 'salve as informaçoes'*/
			
			ResultSet rs = stmt.getGeneratedKeys(); // vai retornar as chaves geradas
			
			if (rs.next()) {
				idGerado = rs.getLong(1); // o valor que gerar aqui vai estar na coluna 1 do resultset
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

}
