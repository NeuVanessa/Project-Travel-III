package jdbcTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class View_Aluno {

	JFrame frame;
	private JTextField textField_Nome;
	private JTextField textField_cpf;
	private JTextField textField_semestre;
	private JTextField textField_curso;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton button;

	private Connection conexao = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Aluno window = new View_Aluno();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View_Aluno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ALUNOS");
		lblNewLabel.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(lblNewLabel);

		textField_Nome = new JTextField();
		textField_Nome.setToolTipText("Nome do Aluno");
		textField_Nome.setBounds(10, 34, 414, 31);
		frame.getContentPane().add(textField_Nome);
		textField_Nome.setColumns(10);

		textField_cpf = new JTextField();
		textField_cpf.setToolTipText("CPF Do aluno");
		textField_cpf.setColumns(10);
		textField_cpf.setBounds(10, 70, 414, 31);
		frame.getContentPane().add(textField_cpf);

		textField_semestre = new JTextField();
		textField_semestre.setToolTipText("Semestre do Aluno");
		textField_semestre.setColumns(10);
		textField_semestre.setBounds(10, 155, 414, 34);
		frame.getContentPane().add(textField_semestre);

		textField_curso = new JTextField();
		textField_curso.setToolTipText("Curso do Aluno");
		textField_curso.setColumns(10);
		textField_curso.setBounds(10, 110, 414, 34);
		frame.getContentPane().add(textField_curso);

		JButton btnSalvar = new JButton("+");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField_Nome.getText() == "" || textField_Nome.getText() == null) {
					return;
				}

				Aluno temp = new Aluno(textField_Nome.getText(), textField_cpf.getText(), textField_curso.getText(),
						Integer.parseInt(textField_semestre.getText()));

				try {
					salvarAluno(temp);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSalvar.setBounds(10, 213, 89, 23);
		frame.getContentPane().add(btnSalvar);

		btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setBounds(122, 213, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		btnNewButton_2 = new JButton("X");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// salvar as informacoes do aluno no banco
			}
		});
		btnNewButton_2.setBounds(335, 213, 89, 23);
		frame.getContentPane().add(btnNewButton_2);

		JLabel lblNewLabel_1 = new JLabel("ALUNO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(192, 11, 66, 25);
		frame.getContentPane().add(lblNewLabel_1);

		button = new JButton("Limpar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LimparCampos();
			}
		});
		button.setBounds(221, 213, 89, 23);
		frame.getContentPane().add(button);
	}

	public void salvarAluno(Aluno a) throws SQLException {

		PreparedStatement minhaSetenca = null;
		// ResultSet meuResultado = null;

		try {

			conexao = mySQLConnection.dbConnector();

			System.out.println(" Banco de dados conectado!");

			minhaSetenca = conexao
					.prepareStatement("insert into aluno (nome_aluno, cpf, curso,semestre) values (?,?,?,?)");
			minhaSetenca.setString(1, a.nome_aluno);
			minhaSetenca.setString(2, a.cpf);
			minhaSetenca.setString(3, a.curso);
			minhaSetenca.setInt(4, a.semestre);
			minhaSetenca.execute();

			JOptionPane.showMessageDialog(null, "Aluno Cadastrado com Sucesso no banco de dados");
			LimparCampos();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void LimparCampos() {

		// limpar campos
		textField_Nome.setText("");
		textField_cpf.setText("");
		textField_curso.setText("");
		textField_semestre.setText("");
	}
}
