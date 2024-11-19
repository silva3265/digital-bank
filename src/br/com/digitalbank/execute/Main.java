package br.com.digitalbank.execute;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import br.com.digitalbank.entities.Agencia;
import br.com.digitalbank.entities.ChavePixContaCorrente;
import br.com.digitalbank.entities.Cliente;
import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;
import br.com.digitalbank.entities.ContaPoupanca;
import br.com.digitalbank.entities.Endereco;
import br.com.digitalbank.entities.Transferencia;
import br.com.digitalbank.model.AgenciaModel;
import br.com.digitalbank.model.ClienteModel;
import br.com.digitalbank.model.ContaModel;
import br.com.digitalbank.model.EnderecoModel;
import br.com.digitalbank.model.TransferenciaModel;

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
		
		
		ContaCorrente contaCorrente = contaModel.getContaCorrenteByIdConta(conta.getId()); // pra buscar precisamos buscar pelo o id da conta
		if (contaCorrente != null) {
			Double saldoInicial = contaCorrente.getSaldo(); //250
			
			Double valorRetiradoChequeEspecial = saldoInicial - valor; // quanto vai tirar do saldo do cheque especial
			
			Double valorAbsoluto = Math.abs(valorRetiradoChequeEspecial);
			
			Integer saqueContaCorrente = contaCorrente.sacar(valor); // vai tentar sacar o valor e vai ser atualizado
			if (saqueContaCorrente == 1) {
				contaModel.updateContaCorrente(contaCorrente);				
				System.out.println("Dirija-se a um caixa eletronico do Digital Bank para efetuar o saque");
			}else if (saqueContaCorrente == 0) { // Nao tem saldo e nem limite de cheque especial 
					System.out.println("Sem Saldo e sem Limite de cheque especial Disponiveis");
				
			}else if (saqueContaCorrente == -1) { // Nao tem nada de saldo e o usuario usa o limite de cheque especial
				contaModel.updateContaCorrente(contaCorrente);
				System.out.println("Dirija-se a um caixa eletronico do Digital Bank para efetuar o Saque: " + valor + 
						"\nSaldo Atual:" + contaCorrente.getSaldo() + 
						"\nValor retirado do Cheque Especial: " + valor +
						"\nValor Disponivel do Cheque Especial: " + contaCorrente.getSaldoChequeEspecial());
			}else if (saqueContaCorrente == -2) { // Tem um pouco de saldo e usa um pouco do limite de cheque especial
				contaModel.updateContaCorrente(contaCorrente);
				System.out.println("Foi Retirado: R$: " + saldoInicial + " de Sua Conta Corrente" + "\nFoi Retirado: R$: " + 
						valorAbsoluto + " do seu ChequeEspecial");
			}
		}
		
	}
	
	public static void saldoConta(Conta conta) {
		
		ContaModel contaModel = new ContaModel();
		
		Scanner sc = new Scanner(System.in);
		
		Integer resposta;
		Boolean temContaCorrente = contaModel.temContaCorrente(conta.getId());
		Boolean temContaPoupanca = contaModel.temContaPoupanca(conta.getId());
		
		
					
			if (temContaCorrente && temContaPoupanca) {
				
				ContaCorrente contaCorrente = contaModel.getContaCorrenteByIdConta(conta.getId());
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
				ContaCorrente contaCorrente = contaModel.getContaCorrenteByIdConta(conta.getId());
				//Double saldo = contaModel.getSaldoContaCorrente(conta.getId()); 
				System.out.println("O Saldo da sua Conta Corrente é: " + contaCorrente.getSaldo());
				System.out.println("Valor disponivel no Cheque Especial: " + contaCorrente.getSaldoChequeEspecial());
			} else if (temContaPoupanca) {
				contaModel.getSaldoContaPoupanca(conta.getId());

			}
	}
	
	public static Double saldoChequeEspecial(Conta conta) {
		
		ContaModel contaModel = new ContaModel();
		
		ContaCorrente contaCorrente = contaModel.getContaCorrenteByIdConta(conta.getId());
		
		Double limiteChequeEspecial = contaCorrente.getLimiteChequeEspecial();
		
		Double saldoChequeEspecial = contaCorrente.getSaldoChequeEspecial();
		
		Double valorDevedor = limiteChequeEspecial - saldoChequeEspecial;
		
		
		System.out.println("Limite Definido: R$: " + limiteChequeEspecial);
		System.out.println("Saldo Devedor: R$:-" + valorDevedor);
		System.out.println("Saldo Disponivel: R$: " + contaCorrente.getSaldo());
		
		return contaCorrente.getSaldo();
	}
	
	public static void saldo(Conta conta) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(" Gostaria de Consultar Qual tipo de saldo: \n1 - Saldo da Conta \n2 - Saldo Cheque Especial ");
		
		Integer resposta = sc.nextInt();
		
		switch (resposta) {
		case 1:
			saldoConta(conta);
			break;
			
		case 2:
			saldoChequeEspecial(conta);;
			break;
		}
			
	}

	public static void cadastrarCliente() {

		EnderecoModel enderecoModel = new EnderecoModel();

		ClienteModel clienteModel = new ClienteModel();
		
		ContaModel contaModel = new ContaModel();
		
		Cliente cliente = null;
		
		Long idConta = null;

		Scanner sc = new Scanner(System.in);

		System.out.println(" Insira seu Nome: ");
		String nome = sc.nextLine();

		System.out.println(" Insira seu CPF: ");
		String cpf = sc.nextLine();
		
		Boolean isCpfCadastrado = contaModel.verificarCpfUsuarioCadastrado(cpf);
		
		Boolean validacaoCpf = false; 
		
		Boolean validacaoSenha = true;
		
		if (!isCpfCadastrado) {
			validacaoCpf = true; // ela vai salvar uma informação quando a gente saber que deu certo
		}else {
			
			while (isCpfCadastrado) {
				System.out.println(" CPF ja Vinculado em Outra Conta "); 
				System.out.println(" Digite Novamente o CPF: ");
				cpf = sc.next();
				isCpfCadastrado = contaModel.verificarCpfUsuarioCadastrado(cpf); // Ponto de parada , isCpfCadastrado = falso para parar o while que é sempre verdadeiro enquanto o usuario digitar um CPF que existe no banco de dados
				if (!isCpfCadastrado) { // se nao existir cadastro nesse cpf, continue o cadastro
					validacaoCpf = true;
					//isCpfCadastrado = false; // Ponto de parada , isCpfCadastrado = falso para parar o while que é sempre verdadeiro enquanto o usuario digitar um CPF que existe no banco de dados
				}
			}
			
		}

		System.out.println(" Insira seu Telefone: ");
		String telefone = sc.nextLine();
		
		Boolean isTelefoneCadastrado = contaModel.verificarNumeroUsuarioCadastrado(telefone);
		
		Boolean validacaoTelefone = false; 
		
		if (!isTelefoneCadastrado) {
			validacaoTelefone = true; // ela vai salvar uma informação quando a gente saber que deu certo
		}else {
			
			while (isTelefoneCadastrado) {
				System.out.println(" Telefone ja Vinculado em Outra Conta "); 
				System.out.println(" Digite Novamente o Telefone: ");
				telefone = sc.next();
				isTelefoneCadastrado = contaModel.verificarNumeroUsuarioCadastrado(telefone); // Ponto de parada , isTelefoneCadastrado = falso para parar o while que é sempre verdadeiro enquanto o usuario digitar um CPF que existe no banco de dados
				if (!isTelefoneCadastrado) { // se nao existir cadastro nesse telefone, continue o cadastro
					validacaoTelefone = true;
					//isTelefoneCadastrado = false; // Ponto de parada , isTelefoneCadastrado = falso para parar o while que é sempre verdadeiro enquanto o usuario digitar um CPF que existe no banco de dados
				}
			}
		}	

		System.out.println(" Cadastro de Endereço: ");

		System.out.println("Rua: ");
		String rua = sc.nextLine();
		System.out.println("Numero: ");
		String numero = sc.nextLine();
		System.out.println("CEP: ");
		String cep = sc.nextLine();
		System.out.println("Complemento: ");
		String complemento = sc.nextLine();

		

		Endereco endereco = new Endereco(rua, Integer.parseInt(numero), cep, complemento);
		Long idGeradoEndereco = enderecoModel.cadastroEndereco(endereco);
		
		
		cliente = new Cliente(nome, cpf, idGeradoEndereco, telefone);
		//Boolean isTelefoneCadastrado = contaModel.verificarNumeroUsuarioCadastrado(telefone);
		
		

		Long idGeradoCliente = clienteModel.cadastroCliente(cliente);

		System.out.println("Seleciona uma Agencia mais proxima: ");

		for (Agencia agencia : new AgenciaModel().getAgencias()) { // pra cada agencia, da LISTA de Agencias
			agencia.imprimirAgencia();

		}

		String idAgencia = sc.nextLine();
		
		System.out.println("Cadastre uma Senha");
		String senha = sc.nextLine();

		System.out.println("Confirme a senha novamente");
		String segundaSenha = sc.nextLine();
		
		while (!senha.equals(segundaSenha)) {
			System.out.println(" ** Senhas Diferentes ** Confirme a senha novamente: ");
			segundaSenha = sc.nextLine(); // a opção mais usada para parar o loop do while é chamando e atualizando a variavel
			validacaoSenha = false;
			
		}
		idConta = cadastroContaCorrente(Long.parseLong(idAgencia), idGeradoCliente, senha);
		
		
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

		System.out.println(" O que voce gostaria de Fazer? \n 1 - Login \n 2 - Cadastrar Nova Conta \n 3 - Encerrar Programa");

		int opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			getLogin();
			break;

		case 2:
			cadastrarCliente();
			
		case 3:
			encerrarPrograma();
		default:
			break;
		}
	}
	
	public static void menuLogadoTemContaPoupanca(Conta conta) {
		Scanner sc = new Scanner(System.in);
		ContaModel contaModel = new ContaModel();
		
		System.out.println(" O que voce gostaria de Fazer? \n1 - Deposito \n2 - Saldo \n3 - Saque \n4 - Gerenciar Chaves Pix \n5 - Sair da Conta");
		
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
			menuPix(conta);
			menuLogado(conta);
			break;
			
		case 5:
			sairDaConta(conta);
		default:
			break;
		}
	}
	
	public static void menuLogadoNaoTemContaPoupanca(Conta conta) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(" O que voce gostaria de Fazer? \n1 - Deposito \n2 - Saldo \n3 - Saque  \n4 - Cadastrar Conta Poupança \n5 - Gerenciar Chaves Pix \n6 - Sair da Conta");
		
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
			saque(conta);
			menuLogado(conta); // Recursividade
			break;
			
		case 4:
			cadastroContaPoupanca(conta.getId());
			System.out.println(" ** Conta Poupança Cadastrada com Sucesso ** ");
			menuLogado(conta); // Recursividade
			break;
			
		case 5:
			menuPix(conta);
			menuLogado(conta);
			break;
			
		case 6:
			sairDaConta(conta);
		default:
			break;
		}
	}

	public static void menuLogado(Conta conta) {
		ContaModel contaModel = new ContaModel();
		
		Boolean temConta = contaModel.temContaPoupanca(conta.getId());

		Scanner sc = new Scanner(System.in);
		
		if (temConta == false) {
			menuLogadoNaoTemContaPoupanca(conta);
			
		}else {
			menuLogadoTemContaPoupanca(conta);
		}

	}
	
	
	private static void menuPix(Conta conta) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(" O que voce gostaria de Fazer? \n1 - Cadastrar uma Chave \n2 - Listar Chaves \n3 - Deletar uma Chave  \n4 - Atualizar uma Chave Pix \n5 - Tranferencia via Pix \n6 - Historico de Transferencias");
		Integer entrada = sc.nextInt();
		
		switch (entrada) {
		case 1:
			cadastrarChavesPix(conta);
			menuPix(conta);
			break;
		case 2:
			listarMinhasChavesPix(conta);
			menuPix(conta);
			break;
			
		case 3:
			deletarChavesPix(conta);
			menuPix(conta);
			break;
			
		case 4:
			atualizarTelefone(conta); // esta atualizando o telefone do cliente se consequentemente o telefone da Chaves Pix
			menuPix(conta);
			break;
			
		case 5:
			transferenciaViaPix(conta);
			menuLogado(conta);
			break;
			
		case 6:
			historicoTransferencia(conta);
			menuLogado(conta);
			break;

		default:
			break;
		}
		
	}


	private static void historicoTransferencia(Conta conta) {
		
		TransferenciaModel transferenciaModel = new TransferenciaModel();
		
		System.out.println(" ** Historico de Transferencias ** ");
		
		List<Transferencia> historicoTransferencia = transferenciaModel.getTransferencias();
		
		for (Transferencia transferencia : historicoTransferencia) {
			System.out.println(" Transferido da Conta: " + transferencia.getIdContaOrigem() + "\n Id Conta Destino: " + transferencia.getIdContaDestino() + "\n Valor Transferido: " + transferencia.getValorTransferido() + "\n Data: " + transferencia.getData());
		}
		
		System.out.println();
		
	}

	private static void atualizarTelefone(Conta conta) {
		
		Scanner sc = new Scanner(System.in);
		ContaModel contaModel = new ContaModel();
		
		listarMinhasChavesPix(conta);
		
		System.out.println(" *** Somente a Chave Pix do Tipo (Telefone) Pode ser Atualizada *** ");
		System.out.println(" *** Para as Demais Chaves como (Email, CPF e Chave Aleatoria) entre em contato com a Central *** ");
		
		
		System.out.println("Digite o telefone para atualizar");
		
		String telefoneAtualizado = sc.next();
		
		Boolean verificarTelefoneNovo = contaModel.isTelefoneNovoExistente(telefoneAtualizado); // verificando se o telefone é de outro cliente
		
		Cliente cliente = contaModel.getClienteById(conta.getIdCliente());
		
		if (verificarTelefoneNovo) {
			System.out.println(" Numero de telefone ja pertence a outro cliente ");
		}else {
			contaModel.updateTelefone(cliente.getId(), telefoneAtualizado, cliente.getTelefone());
			System.out.println(" Telefone Atualizado com Sucesso!! ");
		}
		
	}

	private static void deletarChavesPix(Conta conta) {
		
		Scanner sc = new Scanner(System.in);
		ContaModel contaModel = new ContaModel();
		
		System.out.println("Digite a Chave que Deseja Deletar");
		String chave = sc.next();
		
		Boolean deletarChave = contaModel.deletarChavesPix(chave);
		
		if (deletarChave != null) {
			System.out.println(" ** Chave Pix Deletada com Sucesso ** ");
		}else {
			System.out.println(" **  Chave Pix Não encontrada ** ");
		}
		
		
	}

	private static void transferenciaViaPix(Conta conta) {
		
		ContaModel contaModel = new ContaModel();
		ClienteModel clienteModel = new ClienteModel();
		ContaCorrente contaCorrenteOrigem = null;
		ContaCorrente contaCorrenteDestino = null;
		Transferencia transferencia = null;
		TransferenciaModel transferenciaModel = new TransferenciaModel();
		
		
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
						System.out.printf("\nChave: " + chavePixContaCorrente.getChave() + "\nTipo da Chave: " + chavePixContaCorrente.getTipoChave() + "\nNome: " + cliente.getNome());
						
						contaCorrenteOrigem = contaModel.getContaCorrenteByIdConta(conta.getId());
						// Passo 1: Os dados estao sendo atualizados na logica do metodo tranferir da Instancia da entidade 'Conta Corrente'
						contaCorrenteOrigem.transferir(valor, contaCorrenteDestino);
						
						System.out.println("\nValor Transferido com Sucesso!!");
						
						
						transferencia = new Transferencia(null, contaCorrenteOrigem.getId(), contaCorrenteDestino.getId(), valor, LocalDate.now());
						
						transferenciaModel.cadastroTransferencia(transferencia);
						
						// Passo 2: Os dados agora precisam ser persistidos no banco de dados
						contaModel.updateContaCorrente(contaCorrenteOrigem);
						contaModel.updateContaCorrente(contaCorrenteDestino);
						
						menuLogado(conta);
						
					
					}else {
						System.out.println("Cliente Não Encontrado!");
					} 
					
				}else if (saldoContaCorrente < valor) {
					System.out.println("Valor Solicitado é menor que o saldo: " + saldoContaCorrente);
				
					System.out.println("Gostaria de Usar o Cheque Especial: \1 - Sim \2 - Nao" );
					Integer entrada = sc.nextInt();
					switch (entrada) {
					case 1:
						
						contaCorrenteOrigem = contaModel.getContaCorrenteByIdConta(conta.getId()); // essa variavel esta armazenando a 'conta corrente' da onde o dinheiro vai sair
						System.out.println("Saldo Disponivel do Cheque Especial: " + contaCorrenteOrigem.getSaldoChequeEspecial());
						Integer valorTranferido = contaCorrenteOrigem.transferir(valor, contaCorrenteDestino);
						
						System.out.println("Valor Tranferido com Sucesso: " + valorTranferido);
						
						break;

					case 2:
						
						transferenciaViaPix(conta);
						
					default:
						System.out.println("Retornando ao Menu Principal");
						menuLogado(conta);
						
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
			
			
		}else if (temContaCorrente) {
	
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
						System.out.printf("\nChave: " + chavePixContaCorrente.getChave() + "\nTipo da Chave: " + chavePixContaCorrente.getTipoChave() + "\nNome: " + cliente.getNome());
						
						contaCorrenteOrigem = contaModel.getContaCorrenteByIdConta(conta.getId());
						// Passo 1: Os dados estao sendo atualizados na logica do metodo tranferir da Instancia da entidade 'Conta Corrente'
						contaCorrenteOrigem.transferir(valor, contaCorrenteDestino);
						
						// Passo 2: Os dados agora precisam ser persistidos no banco de dados
						contaModel.updateContaCorrente(contaCorrenteOrigem);
						contaModel.updateContaCorrente(contaCorrenteDestino);
						
						System.out.println("\nValor Transferido com Sucesso!!");
						
						menuLogado(conta);
						
					
					}else {
						System.out.println("Cliente Não Encontrado!");
					} 
					
				}else if (saldoContaCorrente < valor) {
					System.out.println("Valor Solicitado é menor que o saldo: " + saldoContaCorrente);
				
					System.out.println("Gostaria de Usar o Cheque Especial: \1 - Sim \2 - Nao" );
					Integer entrada = sc.nextInt();
					switch (entrada) {
					case 1:
						
						contaCorrenteOrigem = contaModel.getContaCorrenteByIdConta(conta.getId()); // essa variavel esta armazenando a 'conta corrente' da onde o dinheiro vai sair
						System.out.println("Saldo Disponivel do Cheque Especial: " + contaCorrenteOrigem.getSaldoChequeEspecial());
						Integer valorTranferido = contaCorrenteOrigem.transferir(valor, contaCorrenteDestino);
						
						System.out.println("Valor Tranferido com Sucesso: " + valorTranferido);
						
						break;

					case 2:
						
						transferenciaViaPix(conta);
						
					default:
						System.out.println("Retornando ao Menu Principal");
						menuLogado(conta);
						
						break;
					}
				}
				else{
						System.out.println("Saldo Indisponivel");
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
	
	public static void chavePixEmail(Conta conta) {
		
		Scanner sc = new Scanner(System.in);
		ContaCorrente contaCorrente;
		
		ContaModel contaModel = new ContaModel();
		System.out.println("Digite o Email para cadastro: ");
		String email = sc.next();
		
		ChavePixContaCorrente chavePix =  contaModel.getChavePixContaCorrente(email);
		
		
		if (chavePix != null) { // se a chave pix for diferente de nulo
			System.out.println("Email ja Cadastrado");
		}else {
			// se a chave for nula, temos que cadastrar
			contaCorrente = contaModel.getContaCorrenteByIdConta(conta.getId()); // pegando a 'conta corrente' passando o id da conta (conta.getId())
			chavePix = new ChavePixContaCorrente(email, "Email", contaCorrente.getIdContaCorrente());
			contaModel.cadastroChavePix(chavePix);
			System.out.println(" ** Chave Pix Email Cadastrada com Sucesso ** ");
		}
	}
		public static void chavePixNumeroCelular(Conta conta) {
			
			Scanner sc = new Scanner(System.in);
			ContaCorrente contaCorrente;
			
			ContaModel contaModel = new ContaModel();
			System.out.println("Digite o Numero para cadastro: ");
			String numero = sc.next();
			
			ChavePixContaCorrente chavePix =  contaModel.getChavePixContaCorrente(numero);
			
			Boolean isNumeroUsuarioLogado = contaModel.verificarNumeroUsuario(conta.getId(), numero);
			
			
			if (chavePix != null) { // se a chave pix for diferente de nulo
				System.out.println("Numero ja Cadastrado ou este Numero nao esta Vinculado a sua Conta");
			}else if(isNumeroUsuarioLogado){
				// se a chave for nula, temos que cadastrar
				contaCorrente = contaModel.getContaCorrenteByIdConta(conta.getId()); // pegando a 'conta corrente' passando o id da conta (conta.getId())
				chavePix = new ChavePixContaCorrente(numero, "Numero", contaCorrente.getIdContaCorrente());
				contaModel.cadastroChavePix(chavePix);
				System.out.println(" ** Chave Pix Numero Cadastrado com Sucesso ** ");
			}else {
				System.out.println(" Chave Pix Numero deve ser o Mesmo Numero do Proprietario ");
			}
		}
		
		public static void chavePixAleatoria(Conta conta) {
			
			Scanner sc = new Scanner(System.in);
			ContaCorrente contaCorrente;
			
			ContaModel contaModel = new ContaModel();
			
			System.out.println("Gerando chave aleatoria...: ");
			
			// vai gerar a chave aleatoria
			String chaveGerada = UUID.randomUUID().toString(); // retorna um objeto UUID e converte para string usando o toString
			
			contaCorrente = contaModel.getContaCorrenteByIdConta(conta.getId()); // pegando a 'conta corrente' passando o id da conta (conta.getId())
			ChavePixContaCorrente chavePix = new ChavePixContaCorrente(chaveGerada, "Chave Aleatoria", contaCorrente.getIdContaCorrente());
			contaModel.cadastroChavePix(chavePix);
			System.out.println("** Chave Pix Gerada com Sucesso **" + "\nChave: " + chaveGerada);
			}
		
		
		public static void chavePixCPf(Conta conta) {
			
			Scanner sc = new Scanner(System.in);
			ContaCorrente contaCorrente;
			
			ContaModel contaModel = new ContaModel();
			System.out.println("Digite o seu CPF para cadastro: ");
			String chaveCpf = sc.next();
			
			ChavePixContaCorrente chavePixCpf =  contaModel.getChavePixContaCorrente(chaveCpf);
			
			Boolean isCpfUsuarioLogado = contaModel.verificarCpfUsuario(conta.getId(), chaveCpf);
			
			
			if (chavePixCpf != null) { // se a chave pix for diferente de nulo
				System.out.println("Chave Pix CPF ja Cadastrada");
			}else if (isCpfUsuarioLogado) {
				contaCorrente = contaModel.getContaCorrenteByIdConta(conta.getId()); // pegando a 'conta corrente' passando o id da conta (conta.getId())
				chavePixCpf = new ChavePixContaCorrente(chaveCpf, "CPF", contaCorrente.getIdContaCorrente());
				contaModel.cadastroChavePix(chavePixCpf);
				System.out.println(" ** Chave Pix CPF Cadastrada com Sucesso ** ");
			}else {
				System.out.println(" Chave Pix CPF deve ser o Mesmo CPF do Prprpietario ");
			}
		}
	
	
	public static void cadastrarChavesPix(Conta conta) {
		
		Scanner sc = new Scanner(System.in);
	
		
		System.out.println("Qual tipo de chave voce gostaria de Inserir: \n1 - Email \n2 - Celular \n3 -  Chave Aleatoria  \n4 - CPF ");
		
		Integer resposta = sc.nextInt();
		
		switch (resposta) {
		case 1:
			chavePixEmail(conta);
			break;
		case 2:
			chavePixNumeroCelular(conta);
			break;
		case 3:
			chavePixAleatoria(conta);;
			break;
		case 4:
			chavePixCPf(conta);
			break;

		default:
			break;
		}
		
		
	}
	
	public static void listarMinhasChavesPix(Conta conta) {
		
		ContaModel contaModel = new ContaModel();
		
		System.out.println(" ** Minhas Chaves Pix ** ");
		
		List<ChavePixContaCorrente> listaChavesPix = contaModel.listarMinhasChavesPix(conta.getId());
		
		for (ChavePixContaCorrente chavesPixContaCorrente : listaChavesPix) { // para cada item, da lista de itens
			System.out.println("Id: " + chavesPixContaCorrente.getId() + "\nChave: " + chavesPixContaCorrente.getChave() + "\nTipo da Chave: " + chavesPixContaCorrente.getTipoChave());
		}
		
	}
	
	private static void encerrarPrograma() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Gostaria de Sair do Aplicativo: \n1 - Sim \n2 - Nao");
		
		Integer resposta = sc.nextInt();
		
		switch (resposta) {
		case 1:
			break;
			
		case 2:
			menuDeslogado();
			System.out.println("Saindo do Aplicativo...");
			break;

		default:
			break;
		}
		
	}
	
	private static void sairDaConta(Conta conta) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Gostaria de Sair da Conta: \n1 - Sim \n2 - Nao");
		
		Integer resposta = sc.nextInt();
		
		switch (resposta) {
		case 1:
			System.out.println("Saindo da Conta....");
			menuDeslogado();
			//encerrarPrograma(conta);
			break;
			
		case 2:
			menuDeslogado();
			
			break;

		default:
			break;
		}
		
	}

}
