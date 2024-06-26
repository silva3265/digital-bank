package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.digitalbank.entities.Cliente;
import br.com.digitalbank.entities.Endereco;

public class ClienteDao {
	
public Long cadastroCliente(Cliente cliente) { // o metodo cadastro Cliente retorna o id gerado no momento da inserção da conta
		
		/* METODOS TRANSACIONAIS */
		String sql = " INSERT INTO Cliente (nome, cpf, IdEndereco, telefone) VALUES (?, ?, ?, ?)";
		
		
		Connection connection = null;
		PreparedStatement stmt = null;
		Long idGerado = null;
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setLong(3, cliente.getIdEndereco());
			stmt.setString(4, cliente.getTelefone());
			
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
