package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.digitalbank.entities.Cliente;
import br.com.digitalbank.entities.ContaCorrente;
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

public Cliente getClienteByIdContaCorrente(Long idContaCorrente) {
	
	String sql = " SELECT cl.* FROM Conta_Corrente cc INNER JOIN Conta c on cc.idConta = c.id INNER JOIN   Cliente cl on c.idCliente  = cl.id WHERE cc.id = ? "; 
	
	Connection conexao;
	PreparedStatement stmt;
	Cliente cliente = null;
	try {
		conexao = new Conexao().getConnection();
		stmt = conexao.prepareStatement(sql);
		
		stmt.setLong(1, idContaCorrente); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */

		ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
		
		
		// resultSet - ele vai retornar verdadeiro se ele existir
		// Ele vai retornar apenas o primeiro objeto 
		 /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
		if (resultSet.next()) { // só vai ser chamado uma vez, só vai retornar um resultado, por estamos buscando apenas UMA conta
			// estamos convertendo os dados que vieram do banco de dados "Problema: No banco tem tabela e no Java só temos Objeto, por isso usamos o 'resultset' pra fazer a conversão"
			cliente = new Cliente(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("cpf"), resultSet.getLong("idEndereco"), resultSet.getString("telefone")); // resultSet.getLong("c.idAgencia") - entre as aspas esta o nome da coluna
		}
		conexao.close(); 	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	return cliente;
	
	}

	public Boolean isTelefoneNovoExistente(String telefone) {
	
	String sql = "SELECT cl.* FROM Cliente cl inner join Conta c on cl.id = c.IdCliente WHERE cl.telefone = ?"; 
	
	Connection conexao;
	PreparedStatement stmt;
	
	try {
		conexao = new Conexao().getConnection();
		stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, telefone); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */
		ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
		
		
		// resultSet - ele vai retornar verdadeiro se ele existir
		// Ele vai retornar apenas o primeiro objeto 
		if (resultSet.next()) { /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
			return true;
		}
		conexao.close(); 
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	return false;
	
	}
	
	public Boolean updateTelefone(Long idCliente, String telefoneAtualiado, String telefoneAntigo) { // Passamos o id como parametro porque precisamos filtrar qual é o cliente que queremos atualizar (Update sem where atualiza todos os cliente)
		/* METODOS TRANSACIONAIS */
		
		String sqlCliente = " UPDATE Cliente SET telefone = ? where id = ?";
		String sqlChavePix = " UPDATE ChavePix_Contas_Correntes SET chave = ? where chave = ?";
		
		Connection connection = null;
		PreparedStatement stmtCliente = null;
		PreparedStatement stmtChavePix = null;
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmtCliente = connection.prepareStatement(sqlCliente);
			stmtChavePix = connection.prepareStatement(sqlChavePix);
			
			stmtCliente.setString(1, telefoneAtualiado); /* o indice '1' é o nosso primeiro coringa '?' */
			stmtCliente.setLong(2, idCliente);
			stmtChavePix.setString(1, telefoneAtualiado); // a chave é o telefone
			stmtChavePix.setString(2, telefoneAntigo);

			stmtCliente.execute();
			stmtChavePix.execute();
			connection.commit(); /* se chegou no execute e não der exception, ele faz o commit 'salve as informaçoes'*/
			
			return true;
			
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
				stmtCliente.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
		public Boolean isTelefoneAntigo(String telefoneAntigo, Long idCliente) {
		
		String sql = "SELECT cl.* FROM Cliente cl inner join Conta c on cl.id = c.IdCliente WHERE cl.telefone = ? and cl.idCliente "; 
		
		Connection conexao;
		PreparedStatement stmt;
		
		try {
			conexao = new Conexao().getConnection();
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, telefoneAntigo); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */
			stmt.setLong(2, idCliente);
			ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
			
			
			// resultSet - ele vai retornar verdadeiro se ele existir
			// Ele vai retornar apenas o primeiro objeto 
			if (resultSet.next()) { /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
				return true;
			}
			conexao.close(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return false;
		
		}

		public Cliente getClienteById(Long idCliente) {
			
			String sql = " SELECT * FROM Cliente where id = ? "; 
			
			Connection conexao;
			PreparedStatement stmt;
			Cliente cliente = null;
			try {
				conexao = new Conexao().getConnection();
				stmt = conexao.prepareStatement(sql);
				
				stmt.setLong(1, idCliente); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */

				ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
				
				
				// resultSet - ele vai retornar verdadeiro se ele existir
				// Ele vai retornar apenas o primeiro objeto 
				 /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
				if (resultSet.next()) { // só vai ser chamado uma vez, só vai retornar um resultado, por estamos buscando apenas UMA conta
					// estamos convertendo os dados que vieram do banco de dados "Problema: No banco tem tabela e no Java só temos Objeto, por isso usamos o 'resultset' pra fazer a conversão"
					cliente = new Cliente(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("cpf"), resultSet.getLong("idEndereco"), resultSet.getString("telefone")); // resultSet.getLong("c.idAgencia") - entre as aspas esta o nome da coluna
				}
				conexao.close(); 	
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			return cliente;
			
			
		}
		
}
