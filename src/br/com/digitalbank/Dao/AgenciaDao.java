package br.com.digitalbank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.digitalbank.entities.Agencia;
import br.com.digitalbank.entities.Conta;

public class AgenciaDao {

		/* Metodo Não Transacional */
		/* AS TRES PRINCIPAIS INSTRUÇÕES DE DENTRO DE UM METODO DA DAO SÃO: SQL (QUERY), CONEXÃO, E CHAMADA DA CONEXÃO */
		public List<Agencia> getAgencias() {
			String sql = "SELECT * FROM Agencia"; 
			
			Connection conexao;
			PreparedStatement stmt;
			Agencia agencia = null;
			List<Agencia> retornoAgencia = new ArrayList<Agencia>();
			try {
				conexao = new Conexao().getConnection();
				stmt = conexao.prepareStatement(sql);
				
				ResultSet resultSet = stmt.executeQuery(); /* resultSet - Representa uma tabela do banco de dados, ele aponta para o cabeçalho da tabela*/
				
				 
				// resultSet - ele vai retornar verdadeiro se ele existir
				// Ele vai retornar apenas o primeiro objeto 
				while (resultSet.next()) { /* next() - enquanto tiver um proximo resultado, sera criado um objeto do tipo 'Agencia' */
					// ele criar uma instancia do objeto conta, se nao existir a conta vai retornar uma conta Nula
					agencia = new Agencia(resultSet.getLong("id"), resultSet.getLong("idBanco"), resultSet.getLong("idEndereco"));
//					agencia = new Agencia(resultSet.getLong(1), resultSet.getLong(2), resultSet.getLong(3)); // os metodos do ResultSet aceitam tanto String ou numeros (atraves de uma sobrecarga de metodos do ResultSet)
					retornoAgencia.add(agencia);
					
				}
				conexao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			return retornoAgencia;
		
	}

}
