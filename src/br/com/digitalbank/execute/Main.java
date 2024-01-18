package br.com.digitalbank.execute;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println(" ** Bem Vindo ao DIGITAL BANK ** ");
	
		System.out.println(" Acesse sua Conta: ");
		Long conta = sc.nextLong();
		
		System.out.println(" Digite sua Senha: ");
		Long senha = sc.nextLong();
		
		System.out.println(" O que voce gostaria de Fazer? \n1 - Saldo \n2 - Saque \n3 - Deposito \n4 - Tranferencia");
	
		
		int opcao = sc.nextInt();
		
		switch (opcao) {
		case 1:
			
			break;

		default:
			break;
		}
	
	}

}
