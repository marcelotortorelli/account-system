package models;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Cliente {
    private String nome;
    private double totalCompras;

    public Cliente(String nome, double totalCompras) {
        this.nome = nome;
        this.totalCompras = totalCompras;
    }

    public String getNome() {
        return nome;
    }

    public double getTotalCompras() {
        return totalCompras;
    }
}

public class CalculadoraCompras {

    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Cliente 1", 100.0));
        clientes.add(new Cliente("Cliente 2", 150.0));
        clientes.add(new Cliente("Cliente 3", 50.0));
        clientes.add(new Cliente("Cliente 4", 200.0));
        clientes.add(new Cliente("Cliente 5", 75.0));

        // Cliente com mais compras
        Cliente clienteMenosCompras = clientes.stream()
                .min(Comparator.comparingDouble(Cliente::getTotalCompras))
                .orElse(null);

        if (clienteMenosCompras != null) {
            System.out.println("Cliente com menos compras: " + clienteMenosCompras.getNome());
        }

        // cliebnte que ibteve maias compras
        Cliente clienteMaisCompras = clientes.stream()
                .max(Comparator.comparingDouble(Cliente::getTotalCompras))
                .orElse(null);

        if (clienteMaisCompras != null) {
            System.out.println("Cliente com mais compras: " + clienteMaisCompras.getNome());
        }

        // Média das compras
        clientes.stream()
                .mapToDouble(Cliente::getTotalCompras)
                .average()
                .ifPresent(media -> System.out.println("Média de compras: " + media));
    }
}
    // PS O PROF: confesso não ter entendido muito bem, acabei pedindo ajuda de um amigo!