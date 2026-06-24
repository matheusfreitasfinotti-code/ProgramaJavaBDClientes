package br.com.cadastroempregados.persistencia;

import br.com.cadastroempregados.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryPostgres{
    private final ConexaoFactory conexaoFactory;

    public ClienteRepositoryPostgres(ConexaoFactory conexaoFactory) {
        this.conexaoFactory = conexaoFactory;

    }

    public void inserir(Cliente cliente) {
        String sql = "insert into cliente (nome, cpf, telefone, email) values (?, ?, ?, ?)";

        try (Connection conexao = conexaoFactory.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getCpf());
            comando.setString(3, cliente.getTelefone());
            comando.setString(4, cliente.getEmail());
            comando.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Nao foi possivel inserir o cliente.", e);
        }
    }

    
    public List<Cliente> listar() {
        String sql = "select nome, cpf, telefone, email from cliente order by nome";
        List<Cliente> clientes = new ArrayList<Cliente>();

        try (Connection conexao = conexaoFactory.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {
                Cliente cliente = new Cliente(
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("telefone"),
                        resultado.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Nao foi possivel listar os clientes.", e);
        }

        return clientes;
    }

    public boolean existeCpf(String cpf) {
        String sql = "select 1 from empregado where cpf = ?";

        try (Connection conexao = conexaoFactory.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, cpf);

            try (ResultSet resultado = comando.executeQuery()) {
                return resultado.next();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Nao foi possivel consultar o CPF.", e);
        }
    }

}
