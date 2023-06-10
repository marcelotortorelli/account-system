package models;

import db.ClienteDB;
import db.ContaDB;


import java.util.*;
import java.util.function.Consumer;

public class TestaConta {
	public static void main(String[] args) {
		ContaCorrente cc1 = new ContaCorrente(1, "Marcelo", 2500, 250,  TipoConta.CORRENTE);
		ContaCorrente cc2 = new ContaCorrente(2, "João", 6000, 600,  TipoConta.CORRENTE);
		ContaCorrente cc3 = new ContaCorrente(3, "Gabriel", 21000, 2100,  TipoConta.CORRENTE);


		List<ContaCorrente> contas = Arrays.asList(cc1, cc2, cc3);

		contas.forEach(ContaCorrente :: getTipoConta);

	}
}



