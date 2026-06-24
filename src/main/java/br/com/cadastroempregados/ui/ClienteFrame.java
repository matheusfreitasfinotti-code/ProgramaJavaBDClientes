package br.com.cadastroempregados.ui;

import br.com.cadastroempregados.aplicacao.ClienteService;
import br.com.cadastroempregados.modelo.Cliente;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ClienteFrame extends JFrame {
    private final ClienteService service;
    private final JTextField nomeField = new JTextField(50);
    private final JTextField cpfField = new JTextField(11);
    private final JTextField telefoneField = new JTextField(11);
    private final JTextField emailField = new JTextField(50);
    private final DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Telefone", "Nome",  "Email", "CPF"}, 0);

    public ClienteFrame(ClienteService service) {
        this.service = service;

        setTitle("Cadastro de Clientes");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        add(criarFormulario(), BorderLayout.NORTH);
        add(new JScrollPane(new JTable(tableModel)), BorderLayout.CENTER);

        atualizarTabela();
    }

    private JPanel criarFormulario() {
        JPanel camposPanel = new JPanel(new GridLayout(2, 2, 3, 4));
        camposPanel.add(new JLabel("Nome:"));
        camposPanel.add(nomeField);
        camposPanel.add(new JLabel("CPF:"));
        camposPanel.add(cpfField);
        camposPanel.add(new JLabel("Telefone:"));
        camposPanel.add(telefoneField);
        camposPanel.add(new JLabel("Email:"));
        camposPanel.add(emailField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(event -> salvarCliente());

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesPanel.add(salvarButton);

        JPanel formularioPanel = new JPanel(new BorderLayout(8, 8));
        formularioPanel.add(camposPanel, BorderLayout.CENTER);
        formularioPanel.add(botoesPanel, BorderLayout.SOUTH);

        return formularioPanel;
    }

    private void salvarCliente() {
        try {
            service.inserir(nomeField.getText(), cpfField.getText(), telefoneField.getText(), emailField.getText());
            limparCampos();
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Dados invalidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        cpfField.setText("");
        telefoneField.setText("");
        emailField.setText("");
        nomeField.requestFocus();
    }

 private void atualizarTabela() {
    tableModel.setRowCount(0);

    List<Cliente> clientes = service.listar();
    for (Cliente cliente : clientes) {
        tableModel.addRow(new Object[]{
                cliente.getTelefone(),  
                cliente.getNome(),      
                cliente.getEmail(),   
                cliente.getCpf()      
        });
    }
}}