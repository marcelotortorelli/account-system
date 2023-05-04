package models;

public abstract class Conta {

	private int numero;
	private int agencia;
	private String cliente;
	protected double saldo;
	private TipoConta tipoConta;

	public Conta(int agencia, String cliente, double saldo, TipoConta tipoConta) {
		this.agencia = agencia;
		this.cliente = cliente;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
	}

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public abstract void depositar(double valorDeposito);

	public abstract void sacar(double valorSaque);

	@Override
	public String toString() {
		return "Conta{" +
				"numero=" + numero +
				", agencia=" + agencia +
				", cliente=" + cliente +
				", saldo=" + saldo +
				", tipoConta=" + tipoConta +
				'}';
	}
}
