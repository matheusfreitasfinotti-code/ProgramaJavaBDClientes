package br.com.cadastroempregados.aplicacao;

import br.com.cadastroempregados.modelo.Cliente;
import br.com.cadastroempregados.persistencia.ClienteRepositoryPostgres;

import java.util.List;

public class ClienteService {
    private final ClienteRepositoryPostgres repository;

    public ClienteService(ClienteRepositoryPostgres repository) {
        this.repository = repository;
    }

    public void inserir(String nome, String cpf, String telefone, String email) {
        if (estaVazio(nome) || estaVazio(cpf) || estaVazio(telefone) || estaVazio(email)) {
            throw new IllegalArgumentException("Preencha nome, CPF, telefone e e-mail.");
        }


        if (repository.existeCpf(cpf)) {
            throw new IllegalArgumentException("Ja existe um cliente com este CPF.");
        }

        Cliente cliente = new Cliente(nome.trim(), cpf, telefone, email);
        repository.inserir(cliente);
    }

    public List<Cliente> listar() {
        return repository.listar();
    }

    private boolean estaVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

}
