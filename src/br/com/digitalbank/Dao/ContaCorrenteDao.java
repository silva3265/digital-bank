package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.digitalbank.entities.ChavePixContaCorrente;
import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;


public class ContaCorrenteDao {
	/* o metodo cadastrar conta só cadastra informações gerais da Conta Corrente na tabela 'Conta' */
	public Long cadastroConta(ContaCorrente contaCorrente) { // o metodo cadastro conta retorna o id gerado no momento da inserção da conta
		
		/* METODOS TRANSACIONAIS */
		String sql = " INSERT INTO Conta (idAgencia, idCliente, password) VALUES (?, ?, ? )";
		
		
		Connection connection = null;
		PreparedStatement stmt = null;
		Long idGerado = null;
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setLong(1, contaCorrente.getIdAgencia()); /* o indice '2' éo nosso segundo coringa '?' */
			stmt.setLong(2, contaCorrente.getIdCliente());
			stmt.setString(3, contaCorrente.getPassword());
			
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
/* o metodo cadastrar conta corrente só cadastra informações ESPECIFICAS da Conta Corrente na tabela 'Conta Corrente' */
public Long cadastroContaCorrente(ContaCorrente contaCorrente) {
		
		/* METODOS TRANSACIONAIS */
		
		String sql = " INSERT INTO Conta_Corrente (taxa, saldo, limiteChequeEspecial, idConta) VALUES (?, ?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement stmt = null;
		Long idConta = null;
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql);
			
			stmt.setDouble(1, contaCorrente.getTaxa()); /* o indice '1' é o nosso primeiro coringa '?' */
			stmt.setDouble(2, contaCorrente.getSaldo()); /* o indice '2' éo nosso segundo coringa '?' */
			stmt.setDouble(3, contaCorrente.getLimiteChequeEspecial());
			idConta = cadastroConta(contaCorrente); // como nao temos como cadastrar uma 'conta corrente' sem cadastrar uma 'conta' primeiro, chamamos o metodo cadastroConta recebendo o parametro do metodo cadastroContaCorrente 
			stmt.setLong(4, idConta);
			
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
		return idConta;
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

public ContaCorrente getContaCorrenteByIdConta(Long idConta) { // getContaCorrente - depois do get vem o retorno e depois do 'by' o parametro
	
	// o 'idConta' da Conta Corrente tem que ser igual ao 'id' da Conta
	String sql = " SELECT cc.*, c.* FROM Conta_Corrente cc INNER JOIN Conta c on cc.idConta = c.id where c.id = ? "; 
	
	Connection conexao;
	PreparedStatement stmt;
	ContaCorrente contaCorrente = null;
	try {
		conexao = new Conexao().getConnection();
		stmt = conexao.prepareStatement(sql);
		
		stmt.setLong(1, idConta); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */

		ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
		
		
		// resultSet - ele vai retornar verdadeiro se ele existir
		// Ele vai retornar apenas o primeiro objeto 
		 /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
		if (resultSet.next()) { // só vai ser chamado uma vez, só vai retornar um resultado, por estamos buscando apenas UMA conta
			// estamos convertendo os dados que vieram do banco de dados "Problema: No banco tem tabela e no Java só temos Objeto, por isso usamos o 'resultset' pra fazer a conversão"
			contaCorrente = new ContaCorrente(resultSet.getLong("c.id"), resultSet.getLong("c.idAgencia"), resultSet.getLong("cc.id"),resultSet.getLong("c.IdCliente"), resultSet.getString("c.password"), resultSet.getDouble("cc.saldo"), resultSet.getDouble("cc.saldoChequeEspecial"), resultSet.getDouble("cc.limiteChequeEspecial"), resultSet.getDouble("cc.taxa")); // resultSet.getLong("c.idAgencia") - entre as aspas esta o nome da coluna
		}
		conexao.close(); 	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	return contaCorrente;
	
	}



public void updateContaCorrente(ContaCorrente contaCorrente) {
	/* METODOS TRANSACIONAIS */
	
	String sql = " UPDATE Conta_Corrente SET saldo = ?, saldoChequeEspecial = ? WHERE id = ?";
	
	Connection connection = null;
	PreparedStatement stmt = null;
	try {
		connection = new Conexao().getConnection();
		connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
		stmt = connection.prepareStatement(sql);
		
		stmt.setDouble(1, contaCorrente.getSaldo()); /* o indice '1' é o nosso primeiro coringa '?' */
		stmt.setDouble(2, contaCorrente.getSaldoChequeEspecial());
		stmt.setDouble(3, contaCorrente.getIdContaCorrente());

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

public Double getSaldoContaCorrente(Long idConta) { 
	
	String sql = " SELECT cc.saldo FROM Conta_Corrente cc INNER JOIN Conta c on cc.idConta = c.id where c.id = ? "; 
	
	Connection conexao;
	PreparedStatement stmt;
	Double saldo = 0.0;
	try {
		conexao = new Conexao().getConnection();
		stmt = conexao.prepareStatement(sql);
		
		
		stmt.setLong(1, idConta); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */

		ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
		
		
		// resultSet - ele vai retornar verdadeiro se ele existir
		// Ele vai retornar apenas o primeiro objeto 
		 /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
		if (resultSet.next()) { // só vai ser chamado uma vez, só vai retornar um resultado, por estamos buscando apenas UMA conta
			saldo = resultSet.getDouble(1);
		}
		conexao.close(); 	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return saldo;
	
	}

public Double getSaldoContaPoupanca(Long idConta) { 
	
	String sql = " SELECT cp.saldo FROM Conta_Poupanca cp INNER JOIN Conta c on cp.idConta = c.id where c.id = ? "; 
	
	Connection conexao;
	PreparedStatement stmt;
	Double saldo = 0.0;
	try {
		conexao = new Conexao().getConnection();
		stmt = conexao.prepareStatement(sql);
		
		
		stmt.setLong(1, idConta); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */

		ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
		
		
		// resultSet - ele vai retornar verdadeiro se ele existir
		// Ele vai retornar apenas o primeiro objeto 
		 /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
		if (resultSet.next()) { // só vai ser chamado uma vez, só vai retornar um resultado, por estamos buscando apenas UMA conta
			saldo = resultSet.getDouble(1);
		}
		conexao.close(); 	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return saldo;
	
	}

public ChavePixContaCorrente getChavePixContaCorrente(String chave) {
	
	String sql = "SELECT * FROM ChavePix_Contas_Correntes WHERE chave = ? "; 
	
	Connection conexao;
	PreparedStatement stmt;
	ChavePixContaCorrente chavePixContaCorrente = null;
	try {
		conexao = new Conexao().getConnection();
		stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, chave); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */

		ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
		
		
		// resultSet - ele vai retornar verdadeiro se ele existir
		// Ele vai retornar apenas o primeiro objeto 
		if (resultSet.next()) { /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
			chavePixContaCorrente = new ChavePixContaCorrente(resultSet.getLong("id"), resultSet.getString("chave"), resultSet.getString("tipoChave"), resultSet.getLong("idContaCorrente"));
			
		}
		conexao.close(); 
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	return chavePixContaCorrente;
	
}
public ContaCorrente getContaCorrenteByIdContaCorrente(Long idContaCorrente) {
	
// fazemos esse iner join para trazer tanto as colunas da Conta tanto da Conta Corrente (evita o erro de 'Column 'coluna' not found.')	
String sql = "SELECT cc.* , c.* FROM Conta_Corrente cc INNER JOIN Conta c on cc.idConta = c.id WHERE cc.id = ? "; 
	
	Connection conexao;
	PreparedStatement stmt;
	ContaCorrente contaCorrente = null;
	try {
		conexao = new Conexao().getConnection();
		stmt = conexao.prepareStatement(sql);
		
		stmt.setLong(1, idContaCorrente); /* Essa função esta substituindo o nosso coringa da query nome = '?', '1, cpf' - posição 1, '2, senha' - posição 2 - na String SQL (query)  */

		ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
		
		
		// resultSet - ele vai retornar verdadeiro se ele existir
		// Ele vai retornar apenas o primeiro objeto 
		if (resultSet.next()) { /* next() - informa se existe um proximo Objeto (Registro), uma proxima linha */
			// aqui estamos pegando os valore das colunas "get" e passando esses valores como parametro pro metodo construtor 'ContaCorrente'
			contaCorrente = new ContaCorrente(resultSet.getLong("c.id"), resultSet.getLong("idAgencia"), resultSet.getLong("cc.id"), resultSet.getLong("idCliente"), resultSet.getString("password"), resultSet.getDouble("saldo"), resultSet.getDouble("saldoChequeEspecial"), resultSet.getDouble("limiteChequeEspecial"), resultSet.getDouble("taxa"));
			
		}
		conexao.close(); 
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	return contaCorrente;
	
	
	}

	public void cadastroChavePix(ChavePixContaCorrente chavePixContaCorrente) {
		
		/* METODOS TRANSACIONAIS */
		String sql = " INSERT INTO ChavePix_Contas_Correntes (chave, tipoChave, idContaCorrente) VALUES (?, ?, ?)";
		
		
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = new Conexao().getConnection();
			connection.setAutoCommit(false); /* só vai fazer o commit quando a gente disser pra fazer, por isso iniciamos com 'false'*/
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, chavePixContaCorrente.getChave()); 
			stmt.setString(2, chavePixContaCorrente.getTipoChave());
			stmt.setLong(3, chavePixContaCorrente.getIdContaCorrente());
		
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


