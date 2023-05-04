package db;

import models.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteDB {

    private Map<Integer, Cliente> clienteDBMap = new HashMap<>();

    public void addNovoCliente(Cliente cliente) {
        int id = clienteDBMap.size() + 1;
        //cliente.setId(id); // Atribuindo id do cliente automaticamente conforme o tamanho da lista.
        clienteDBMap.put(cliente.getId(),cliente);
    }

    public Cliente getClientePorID(int id) {
        return clienteDBMap.get(id);
    }

    public Map<Integer, Cliente> getClienteDBMap() {
        return clienteDBMap;
    }

    // Método para retornar listas usado tambem no programa de cadastro de produtos.
    public List<Cliente> getClientes() {
        List<Cliente> clientes = new ArrayList<>();
        for (Map.Entry<Integer, Cliente> cliente : clienteDBMap.entrySet()) {
            clientes.add(cliente.getValue());
        }
        return clientes;
    }


}