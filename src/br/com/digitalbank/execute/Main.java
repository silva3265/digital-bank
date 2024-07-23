package br.com.digitalbank.execute;

import java.math.BigDecimal;
import java.util.Scanner;

import br.com.digitalbank.entities.Agencia;
import br.com.digitalbank.entities.ChavePixContaCorrente;
import br.com.digitalbank.entities.Cliente;
import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;
import br.com.digitalbank.entities.ContaPoupanca;
import br.com.digitalbank.entities.Endereco;
import br.com.digitalbank.model.AgenciaModel;
import br.com.digitalbank.model.ClienteModel;
import br.com.digitalbank.model.ContaModel;
import br.com.digitalbank.model.EnderecoModel;

public class Main {

	public static void main(String[] args) {

		System.out.println(" ** Bem Vindo ao DIGITAL BANK ** ");

		menuDeslogado();

	}

	public static void depositar(Conta conta) {

		ContaModel contaModel = new ContaModel();
		Scanner sc = new Scanner(System.in);
		System.out.println("Quanto voce gostaria de depositar?");
		double valor = sc.nextDouble();
		// Vamos verificar se ele tem um conta corrente e uma conta poupança
		Boolean temContaCorrente = contaModel.temContaCorrente(conta.getId());
		Boolean temContaPoupanca = contaModel.temContaPoupanca(conta.getId());

		if (temContaCorrente && temContaPoupanca) {
			System.out.println("Gostaria de depositar em qual tipo de conta? \n1 - Conta Corrente \n2 - Conta Poupança");
			Integer resposta = sc.nextInt();
			switch (resposta) {
			case 1:
				contaModel.depositoContaCorrente(conta, valor); 
				break;
			case 2:
				contaModel.depositoContaPoupanca(conta, valor);
				break;
				
			default:
				break;
			}
		} else if (temContaCorrente) {
			contaModel.depositoContaCorrente(conta, valor);
		} else if (temContaPoupanca) {
			contaModel.depositoContaPoupanca(conta, valor);

		}
		// Verificar se tem conta poupança ou conta corrente associada (ou se tem as
		// duas)
		// Verificar qual conta sera realizada o deposito(corrente ou poupança)

	}
	
	public static void saque(Conta conta) {
		
		Scanner sc = new Scanner(System.in);
		ContaModel contaModel = new ContaModel();
		
		System.out.println("Digite o Valor para Saque: ");
		Double valor = sc.nextDouble();
		
		
		ContaCorrente contaCorrente = contaModel.getContaCorrente(conta.getId()); // pra buscar precisamos buscar pelo o id da conta
		if (contaCorrente != null) {
			Double saldoInicial = contaCorrente.getSaldo(); //250
			
			Double valorRetiradoChequeEspecial = saldoInicial - valor; // quanto vai tirar do saldo do cheque especial
			
			Double valorAbsoluto = Math.abs(valorRetiradoChequeEspecial);
			
			Integer saqueContaCorrente = contaCorrente.sacar(valor); // vai tentar sacar o valor e vai ser atualizado
			if (saqueContaCorrente == 1) {
				contaModel.updateContaCorrente(contaCorrente);				
				System.out.println("Dirija-se a um caixa eletronico do Digital Bank para efetuar o saque");
			}else if (saqueContaCorrente == 0) {
					System.out.println("Sem Saldo e sem Limite de cheque especial Disponiveis");
				
			}else if (saqueContaCorrente == -1) {
				contaModel.updateContaCorrente(contaCorrente);
				System.out.println("Dirija-se a um caixa eletronico do Digital Bank para efetuar o Saque: " + valor + 
						"\nSaldo Atual:" + contaCorrente.getSaldo() + 
						"\nValor retirado do Cheque Especial: " + valor +
						"\nValor Disponivel do Cheque Especial: " + contaCorrente.getSaldoChequeEspecial());
			}else if (saqueContaCorrente == -2) {
				contaModel.updateContaCorrente(contaCorrente);
				System.out.println("Foi Retirado: R$: " + saldoInicial + " de Sua Conta Corrente" + "\nFoi Retirado: R$: " + 
						valorAbsoluto + " do seu ChequeEspecial");
			}
		}
		else {
			
		}
		
	}
	
	public static void saldo(Conta conta) {
		
		ContaModel contaModel = new ContaModel();
	
		Scanner sc = new Scanner(System.in);
		
			Integer resposta;
			Boolean temContaCorrente = contaModel.temContaCorrente(conta.getId());
			Boolean temContaPoupanca = contaModel.temContaPoupanca(conta.getId());

			if (temContaCorrente && temContaPoupanca) {
				
				ContaCorrente contaCorrente = contaModel.getContaCorrente(conta.getId());
				System.out.println("Gostaria de Consultar o Saldo de qual tipo de conta? \n1 - Conta Corrente \n2 - Conta Poupança");
				resposta = sc.nextInt();
				switch (resposta) {
				case 1:
					//Double saldoContaCorrente = contaModel.getSaldoContaCorrente(conta.getId()); 
					System.out.println("O Saldo da sua Conta Corrente é: R$: " + contaCorrente.getSaldo());
					System.out.println("Valor disponivel no Cheque Especial: " + contaCorrente.getSaldoChequeEspecial());
					break;
				case 2:
					Double saldoContaPoupanca = contaModel.getSaldoContaPoupanca(conta.getId());
					System.out.println("O Saldo da sua Conta Poupanca é: R$: " + saldoContaPoupanca);
					break;
					
				default:
					break;
				}
			} else if (temContaCorrente) {
				ContaCorrente contaCorrente = contaModel.getContaCorrente(conta.getId());
				//Double saldo = contaModel.getSaldoContaCorrente(conta.getId()); 
				System.out.println("O Saldo da sua Conta Corrente é: " + contaCorrente.getSaldo());
				System.out.println("Valor disponivel no Cheque Especial: " + contaCorrente.getSaldoChequeEspecial());
			} else if (temContaPoupanca) {
				contaModel.getSaldoContaPoupanca(conta.getId());

			}
		}

	public static void cadastrar() {

		EnderecoModel enderecoModel = new EnderecoModel();

		ClienteModel clienteModel = new ClienteModel();
		
		Long idConta = null;

		Scanner sc = new Scanner(System.in);

		System.out.println(" Insira seu Nome: ");
		String nome = sc.nextLine();

		System.out.println(" Insira seu CPF: ");
		String cpf = sc.nextLine();

		System.out.println(" Insira seu Telefone: ");
		String telefone = sc.nextLine();

		System.out.println(" Cadastro de Endereço: ");

		System.out.println("Rua: ");
		String rua = sc.nextLine();
		System.out.println("Numero: ");
		String numero = sc.nextLine();
		System.out.println("CEP: ");
		String cep = sc.nextLine();
		System.out.println("Complemento: ");
		String complemento = sc.nextLine();

		System.out.println("Digite uma Senha");
		String senha = sc.nextLine();

		System.out.println("Confirme a senha novamente");
		String segundaSenha = sc.nextLine();

		Endereco endereco = new Endereco(rua, Integer.parseInt(numero), cep, complemento);
		Long idGeradoEndereco = enderecoModel.cadastroEndereco(endereco);

		Cliente cliente = new Cliente(nome, cpf, idGeradoEndereco, telefone);

		Long idGeradoCliente = clienteModel.cadastroCliente(cliente);

		System.out.println("Seleciona uma Agencia mais proxima: ");

		for (Agencia agencia : new AgenciaModel().getAgencias()) { // pra cada agencia, da LISTA de Agencias
			agencia.imprimirAgencia();

		}

		String idAgencia = sc.nextLine();
		
		if (senha.equals(segundaSenha)) {
			idConta = cadastroContaCorrente(Long.parseLong(idAgencia), idGeradoCliente, senha);
			
		}
		
		System.out.println(" ** Conta Corrente Cadastrada com Sucesso ** ");
		System.out.println(" Gostaria Tambem de Criar uma Conta Poupanca? \n1 - Sim, \n2 - Não ");
		
		String resposta = sc.nextLine();
		switch (Integer.parseInt(resposta)) {
		case 1:
			cadastroContaPoupanca(idConta);
			System.out.println("Conta Poupanca Cadastrada com Sucesso!");
			break;

		default:
			break;
		}
		
		System.out.println(" ** Agora Faça o Login no App do Banco ** ");
		
		getLogin();

	}

	public static void getLogin() {

		Scanner sc = new Scanner(System.in);
		ContaModel contaModel = new ContaModel();

		System.out.println("Insira o CPF: ");
		String cpf = sc.next();

		System.out.println("Insira a Senha: ");
		String senha = sc.next();

		if (verificarCPF(cpf) == true) {
			Conta contaLogin = contaModel.getLogin(cpf, senha);

			if (contaLogin == null) {
				System.out.println("Login e senha não conferem");
				menuDeslogado();
			} else {
				menuLogado(contaLogin);
			}
		}

	}

	public static void menuDeslogado() {

		Scanner sc = new Scanner(System.in);

		System.out.println(" O que voce gostaria de Fazer? \n 1 - Login \n 2 - Cadastrar Nova Conta");

		int opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			getLogin();
			break;

		case 2:
			cadastrar();
		default:
			break;
		}
	}

	public static void menuLogado(Conta conta) {
		ContaModel contaModel = new ContaModel();
		
		Boolean temConta = contaModel.temContaPoupanca(conta.getId());

		Scanner sc = new Scanner(System.in);
		
		if (temConta == false) {
			System.out.println(" O que voce gostaria de Fazer? \n1 - Deposito \n2 - Saldo \n3 - Saque \n4 - Tranferencia, \n5 - Cadastrar Conta Poupanca");
		}else {
			System.out.println(" O que voce gostaria de Fazer? \n1 - Deposito \n2 - Saldo \n3 - Saque \n4 - Tranferencia ");
		}

		int opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			depositar(conta);
			menuLogado(conta); // Recursividade
			break;
			
		case 2:
			saldo(conta);
			menuLogado(conta); // Recursividade
			break;
			
		case 3:
			saque(conta); // ** o valor nao esta sendo atualizado no banco de dados, ARRUMAR AMANHA
			menuLogado(conta); // Recursividade
			break;
			
		case 4:
			transferenciaViaPix(conta);
			menuLogado(conta);
			break;
			
		case 5:
			cadastroContaPoupanca(conta.getId());
			System.out.println(" ** Conta Poupança Cadastrada com Sucesso ** ");
			menuLogado(conta); // Recursividade
			break;
			
		case 6:
			//gerenciarChavesPix();
			menuLogado(conta);
		default:
			break;
		}
	
	}

	private static void transferenciaViaPix(Conta conta) {
		
		ContaModel contaModel = new ContaModel();
		ClienteModel clienteModel = new ClienteModel();
		ContaCorrente contaCorrenteOrigem = null;
		ContaCorrente contaCorrenteDestino = null;
		
		Cliente cliente = null;
		
		ChavePixContaCorrente chavePixContaCorrente = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Quanto Voce gostaria de Tranferir: ");
		Double valor = sc.nextDouble();
	
		Boolean temContaCorrente = contaModel.temContaCorrente(conta.getId());
		Boolean temContaPoupanca = contaModel.temContaPoupanca(conta.getId());

		if (temContaCorrente && temContaPoupanca) {
			
			System.out.println("De qual conta Vai sair o Dinheiro: \n1 - Conta Corrente, \n2 - Conta Poupanca" );
			Integer opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				
				Double saldoContaCorrente = contaModel.getSaldoContaCorrente(conta.getId());
				
				System.out.println("Saldo Disponivel Atualmente: " + saldoContaCorrente);
				
				if (saldoContaCorrente >= valor) {
					System.out.println("Insira a Chave Pix: ");
					String chave = sc.next();
					
					chavePixContaCorrente = contaModel.getChavePixContaCorrente(chave);
					
					cliente = clienteModel.getClienteByIdContaCorrente(chavePixContaCorrente.getIdContaCorrente());
					
					contaCorrenteDestino  = contaModel.getContaCorrenteByIdContaCorrente(chavePixContaCorrente.getIdContaCorrente());
					
					if (chavePixContaCorrente == null) {
						System.out.println("Chave Pix nao Encontrado na Base de Dados");
					}else if (cliente != null) { 
						System.out.printf("Chave: " + chavePixContaCorrente.getChave() + "Tipo da Chave: " + chavePixContaCorrente.getTipoChave() + "Nome: " + cliente.getNome());
					}else {
						System.out.println("Cliente Não Encontrado!");
					} 
					
				}else if (saldoContaCorrente < valor) {
					System.out.println("Valor Solicitado é menor que o saldo: " + saldoContaCorrente);
				
					System.out.println("Gostaria de Usar o Cheque Especial: \1 - Sim \2 - Nao" );
					Integer entrada = sc.nextInt();
					switch (entrada) {
					case 1:
						
						contaCorrenteOrigem = contaModel.getContaCorrente(conta.getId()); // essa variavel esta armazenando a 'conta corrente' da onde o dinheiro vai sair
						System.out.println("Saldo Disponivel do Cheque Especial: " + contaCorrenteOrigem.getSaldoChequeEspecial());
						contaCorrenteOrigem.transferir(valor, contaCorrenteDestino);
						break;

					default:
						break;
					}
				}
				else{
						System.out.println("Saldo Indisponivel");
					}
				
				
				break;

			default:
				break;
			}
			
			
		}
		
		
	}

	public static Boolean verificarCPF(String cpf) {

		ContaModel contaModel = new ContaModel();
		Conta contaCpf = contaModel.selectContaByCpf(cpf);

		if (contaCpf != null) {
			// System.out.println(" Voce Ja tem uma Conta Associada a esse Cpf: " +
			// "Agencia: " + contaCpf.getIdAgencia() + "Conta: " + contaCpf.getId());
			return true;
		}
			System.out.println(" CPF não encontrado na Base de Dados!");
			menuDeslogado();
			return false;
			
	}
	
	public static Long cadastroContaCorrente(Long idAgencia, Long idGeradoCliente, String senha ) {
		ContaCorrente contaCorrente = new ContaCorrente(idAgencia, idGeradoCliente, senha);

		ContaModel contaModel = new ContaModel();
		return contaModel.cadastroContaCorrente(contaCorrente);
		
	}
	
	public static void cadastroContaPoupanca(Long id) {
		ContaPoupanca contaPoupanca = new ContaPoupanca(id);

		ContaModel contaModel = new ContaModel();
		contaModel.cadastroContaPoupanca(contaPoupanca);
		
	}

}
