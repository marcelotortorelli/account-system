package models;
@FunctionalInterface
public interface Autenticador {

    public abstract boolean autentica(String senha);
}
