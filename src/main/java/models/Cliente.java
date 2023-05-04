package models;

public class Cliente implements Autenticador{
    private int id;
    private String nome;
    private boolean status;
    private String  senha;
    private int compras;

    public Cliente(String nome, boolean status, String senha, int compras){
        this.nome = nome;
        this.status = status;
        this.senha = senha;
        this.compras = compras;

    }

    public int getCompras() {
        return this.compras;
    }

    public int getId() {
        return id;

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;

    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
        public boolean autentica(String senha){
            if (this.senha != senha){
                System.out.println("Não autenticado.");
                return false;
            }
            else {
                System.out.println("Autenticado.");
                return true;
            }
        };


    }


