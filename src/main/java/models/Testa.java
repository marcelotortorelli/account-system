package models;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Testa {
    public static void main(String[] args){
        Cliente cli1 = new Cliente("Marcelo", true, "161913", 425);
        Cliente cli2 = new Cliente("Romero", true, "600300", 126);
        Cliente cli3 = new Cliente("Ronaldo", true, "123456", 125);
        Cliente cli4 = new Cliente("Romarinho", true, "548721", 87);



        List<Cliente> clientes = Arrays.asList(cli1, cli2, cli3, cli4);
        //clientes.forEach( cli -> System.out.println(cli.getCompras()));

        Stream<Cliente> stream = clientes.stream().filter(cliente -> cliente.getCompras() > 100);
        List<Cliente> selecionados = stream.collect(Collectors.toList());
        selecionados.forEach( c -> System.out.println(c.getCompras()));



        //Caso seja necessário mostrar somente UM único objeto podemos utilizar a seguinte expressão Lambda:
        //Consumer<Cliente> consumer = Cliente::getNome; consumer.accept(cli2);
    }
}
