package models;

import db.ClienteDB;
import db.ContaDB;



import java.util.Calendar;
import java.util.Scanner;

public class TestaConta {

	public static ContaDB contasDB = new ContaDB();
	public static ClienteDB clientesDB = new ClienteDB();

	public static void main(String[] args) {

		System.out.println("------------ BEM VINDO ------------");
		int opcao = 0;
		do {
			System.out.println("1- Cadastrar novo cliente");
			System.out.println("2- Exibir lista de clientes");
			System.out.println("3- Cadastrar nova conta");
			System.out.println("4- Exibir lista de contas");
			System.out.println("5- Depositar");
			System.out.println("6- Sacar");
			System.out.println("7- Exibir soma dos saldos de todas as contas");
			System.out.println("8- Transferir entre contas");
			System.out.println("0- Sair");
			Scanner scanner = new Scanner(System.in);
			System.out.print("Digite a opção desejada: ");
			opcao = scanner.nextInt();

			menu(opcao);

		} while (opcao != 0);

	}

	public static void menu(int opcao) {
		switch (opcao) {
			case 1 : { // CADASTRO DE CLIENTE
				Scanner scanner = new Scanner(System.in);
				System.out.print("Digite o nome do novo cliente: ");
				String nome = scanner.nextLine();

				Cliente cliente = new Cliente(nome);
				clientesDB.addNovoCliente(cliente);
				break;
			}

			case 2 : { // Exibe a lista de clientes
				System.out.println("------ LISTA DE CLIENTES ------");
				for (Cliente cliente : clientesDB.getClientes()) {
					System.out.print("ID: " + cliente.getId() + " -> NOME: " + cliente.getNome());
					System.out.println("");
				}
				System.out.println("-------------------------------");
				break;
			}

			case 3 : { // criação de nova conta
				if (clientesDB.getClienteDBMap().isEmpty())
					System.out.println("Para cadastrar uma nova conta, cadastre pelo menos 1 cliente.");
				else {
					int opcao1 = 0;
					do {
						System.out.println("1- Cadastrar nova Conta Corrente");
						System.out.println("2- Cadastrar nova Conta Poupança");
						System.out.println("3- Cadastrar nova Conta Salário");
						System.out.println("0- Voltar");
						System.out.print("Digite a opção desejada: ");
						Scanner scanner = new Scanner(System.in);
						opcao1 = scanner.nextInt();

						submenu1(opcao1);

					} while (opcao1 != 0);
				}
				break;
			}

			case 4 : { // Exibe lista de contas cadastradas
				System.out.println("----------------------- LISTA DE CONTAS -----------------------");
				for (int numeroConta : contasDB.getContasMap().keySet()) {
					System.out.print("Número: " + numeroConta +
							" | Agência: " + contasDB.getContasMap().get(numeroConta).getAgencia() +
							" | Cliente: " + contasDB.getContasMap().get(numeroConta).getCliente().getNome()
					);

					if (contasDB.getContasMap().get(numeroConta).getTipoConta() == TipoConta.CORRENTE) {
						ContaCorrente contaCorrente = (ContaCorrente) contasDB.getContasMap().get(numeroConta);
						System.out.print(" | Saldo: R$ " + contaCorrente.getSaldo());
						System.out.print(" | Tipo: Corrente");
						System.out.print(" | Cheque Especial: R$ " + contaCorrente.getChequeEspecial());
						System.out.print(" | Imposto: R$ " + contaCorrente.getImposto());
					}
					if (contasDB.getContasMap().get(numeroConta).getTipoConta() == TipoConta.POUPANCA) {
						ContaPoupanca contaPoupanca = (ContaPoupanca) contasDB.getContasMap().get(numeroConta);
						int diaDeHoje = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
						System.out.print(" | Saldo: R$ " + contaPoupanca.getSaldo(diaDeHoje));
						System.out.print(" | Tipo: Poupança");
						System.out.print(" | Dia Aniversário: " + contaPoupanca.getDiaAniversario());
						System.out.print(" | Taxa de Juros: " + contaPoupanca.getTaxaDeJuros());
					}
					if (contasDB.getContasMap().get(numeroConta).getTipoConta() == TipoConta.SALARIO) {
						ContaSalario contaSalario = (ContaSalario) contasDB.getContasMap().get(numeroConta);
						System.out.print(" | Saldo: R$ " + contaSalario.getSaldo());
						System.out.print(" | Tipo: Salário");
						System.out.print(" | Limite de Saques: " + contaSalario.getNumLimiteSaques());
						System.out.print(" | Imposto: R$ " + contaSalario.getImposto());
					}
					System.out.println("");
				}
				System.out.println("----------------------------------------------------");
				break;
			}

			case 5 : { // metodo scanner para depositar
				Scanner scanner = new Scanner(System.in);

				if (contasDB.getContasMap().isEmpty()) {
					System.out.println("Cadastre uma conta para fazer depósitos.");
				} else {
					boolean existeConta = false;
					int numeroConta;
					// numero de contas existentes
					do {
						System.out.print("Digite o número da conta que deseja depositar: ");
						numeroConta = scanner.nextInt();

						for (Integer i : contasDB.getContasMap().keySet()) {
							if (i == numeroConta) {
								existeConta = true;
							}
						}
						if (!existeConta)
							System.out.println("Conta não existe. Digite um número inteiro entre 1 e " +
									contasDB.getContasMap().size() + ".");
					} while (!existeConta);

					System.out.print("Saldo: R$ " + contasDB.getContaPorNumero(numeroConta).getSaldo() + ". ");

					System.out.print("Digite o valor que deseja depositar: R$ ");
					double valorDeposito = scanner.nextDouble();
					contasDB.getContaPorNumero(numeroConta).depositar(valorDeposito);
				}
				break;
			}

			case 6 : { // metodo scanner para saque.
				Scanner scanner = new Scanner(System.in);

				if (contasDB.getContasMap().isEmpty()) {
					System.out.println("Cadastre uma conta para fazer saques.");
				} else {

					boolean existeConta = false;
					int numeroConta;
					// Regra para receber número de contas, aplicado nos screencasts
					do {
						System.out.print("Digite o número da conta que deseja sacar: ");
						numeroConta = scanner.nextInt();

						for (Integer numConta : contasDB.getContasMap().keySet()) {
							if (numConta == numeroConta) {
								existeConta = true;
							}
						}
						if (!existeConta)
							System.out.println("Conta não existe. Digite um número inteiro entre 1 e " +
									contasDB.getContasMap().size() + ".");
					} while (!existeConta);

					System.out.print("Saldo: R$ " + contasDB.getContaPorNumero(numeroConta).getSaldo() + ". ");
					System.out.print("Digite o valor que deseja sacar: R$ ");
					double valorSaque = scanner.nextDouble();
					contasDB.getContaPorNumero(numeroConta).sacar(valorSaque);
				}

				break;
			}

			case 7 : { // montante das contas.
				double somaSaldo = 0;
				for (Integer numConta : contasDB.getContasMap().keySet()) {
					if (contasDB.getContasMap().get(numConta).getTipoConta() == TipoConta.POUPANCA) {
						int diaHoje = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
						ContaPoupanca contaPoupanca = (ContaPoupanca) contasDB.getContasMap().get(numConta);
						somaSaldo +=  contaPoupanca.getSaldo(diaHoje);
					} else {
						somaSaldo += contasDB.getContasMap().get(numConta).getSaldo();
					}
				}
				System.out.println("A soma dos saldos de todas as contas é: R$ " + somaSaldo);
				break;
			}

			case 8 : { // Este método achei BEM dificil de ser implementado, confesso que vi
				// outras atividades para tentar entender e não obtive exito
				Scanner scanner = new Scanner(System.in);

				if (contasDB.getContasMap().size() <= 1) {
					System.out.println("Cadastre mais de 1 conta para fazer transferências.");
				} else {

					boolean existeContaOrigem = false;
					int numeroContaOrigem;
					// Regra para receber número de conta existente
					do {
						System.out.print("Digite o número da conta de origem: ");
						numeroContaOrigem = scanner.nextInt();

						for (Integer numConta : contasDB.getContasMap().keySet()) {
							if (numConta == numeroContaOrigem) {
								existeContaOrigem = true;
								break;
							}
						}
						if (!existeContaOrigem)
							System.out.println("Conta não existe. Digite um número inteiro entre 1 e " +
									contasDB.getContasMap().size() + ".");
					} while (!existeContaOrigem);

					System.out.print("Saldo: R$ " + contasDB.getContaPorNumero(numeroContaOrigem).getSaldo() + ". ");

					boolean existeContaDestino = false;
					int numeroContaDestino;
					do {
						System.out.print("Digite o número da conta de destino: ");
						numeroContaDestino = scanner.nextInt();

						if (numeroContaDestino == numeroContaOrigem) {
							System.out.println("Digite uma conta de destino diferente da conta de origem.");
						} else {
							for (Integer numConta : contasDB.getContasMap().keySet()) {
								if (numConta == numeroContaDestino) {
									existeContaDestino = true;
									break;
								}
							}
							if (!existeContaDestino)
								System.out.println("Conta não existe. Digite um número inteiro entre 1 e " +
										contasDB.getContasMap().size() + ".");
						}

					} while (!existeContaDestino);

					System.out.print("Saldo: R$ " + contasDB.getContaPorNumero(numeroContaDestino).getSaldo() + ". ");


					System.out.print("Digite o valor que deseja transferir: R$ ");
					double valorTransferencia = scanner.nextDouble();
					contasDB.getContaPorNumero(numeroContaOrigem).sacar(valorTransferencia);
					contasDB.getContaPorNumero(numeroContaDestino).depositar(valorTransferencia);
					System.out.println("Transferência efetuada.");
				}
				break;
			}
		}
	}

	public static void submenu1(int opcao1) {
		switch (opcao1) {
			case 1 : { // Nova conta corrente
				Scanner scanner = new Scanner(System.in);

				boolean existeCliente = false;
				int idCliente;
				// Regra para receber ID de cliente existente
				do {
					System.out.print("Digite o ID do titular da conta(favor digitar numeros inteiros): ");
					idCliente = scanner.nextInt();

					for (Cliente cliente1 : clientesDB.getClientes()) {
						if (idCliente == cliente1.getId()) {
							existeCliente = true;
						}
					}
					if (!existeCliente)
						System.out.println("Cliente não existe. Digite um número inteiro entre 1 e " +
								clientesDB.getClienteDBMap().size() + ".");

				} while (!existeCliente);

				Cliente cliente = clientesDB.getClientePorID(idCliente);

				System.out.print("Digite o número da agência: ");
				int agencia = scanner.nextInt();
				System.out.print("Digite o saldo da conta: R$ ");
				double saldo = scanner.nextDouble();
				System.out.print("Digite o valor do Cheque Especial: R$ ");
				double chequeEspecial = scanner.nextDouble();

				ContaCorrente contaCorrente = new ContaCorrente(agencia, cliente, saldo, chequeEspecial, TipoConta.CORRENTE);
				contasDB.addNovaConta(contaCorrente);
				break;
			}

			case 2 : { // Nova conta poupança
				Scanner scanner = new Scanner(System.in);

				boolean existeCliente = false;
				int idCliente;

				do {
					System.out.print("Digite o ID do titular da conta: ");
					idCliente = scanner.nextInt();

					for (Cliente cliente1 : clientesDB.getClientes()) {
						if (idCliente == cliente1.getId()) {
							existeCliente = true;
						}
					}
					if (!existeCliente)
						System.out.println("Cliente não existe. Digite um número inteiro entre 1 e " +
								clientesDB.getClienteDBMap().size() + ".");

				} while (!existeCliente);

				Cliente cliente = clientesDB.getClientePorID(idCliente);

				System.out.print("Digite o número da agência: ");
				int agencia = scanner.nextInt();
				System.out.print("Digite o saldo da conta: R$ ");
				double saldo = scanner.nextDouble();
				System.out.print("Digite o dia do aniversário: ");
				int diaAniversario = scanner.nextInt();
				System.out.print("Digite a taxa de juros: ");
				double taxaDeJuros = scanner.nextDouble();

				ContaPoupanca contaPoupanca = new ContaPoupanca(agencia,cliente,saldo,diaAniversario,taxaDeJuros);
				contasDB.addNovaConta(contaPoupanca);
				break;
			}

			case 3 : { // criação de conta salário
				Scanner scanner = new Scanner(System.in);

				boolean existeCliente = false;
				int idCliente;

				do {
					System.out.print("Digite o ID do titular da conta: ");
					idCliente = scanner.nextInt();

					for (Cliente cliente1 : clientesDB.getClientes()) {
						if (idCliente == cliente1.getId()) {
							existeCliente = true;
						}
					}
					if (!existeCliente)
						System.out.println("Cliente não existe. Digite um número inteiro entre 1 e " +
								clientesDB.getClienteDBMap().size() + ".");

				} while (!existeCliente);

				Cliente cliente = clientesDB.getClientePorID(idCliente);

				System.out.print("Digite o número da agência: ");
				int agencia = scanner.nextInt();
				System.out.print("Digite o saldo da conta: R$ ");
				double saldo = scanner.nextDouble();
				System.out.print("Digite o número limite de saques: ");
				int numLimiteSaques= scanner.nextInt();

				ContaSalario contaSalario = new ContaSalario(agencia,cliente,saldo,numLimiteSaques);
				contasDB.addNovaConta(contaSalario);
				break;
			}

		}
	}
}