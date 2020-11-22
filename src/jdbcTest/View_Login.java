package jdbcTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class View_Login {
	static Connection conn = null;
	private JFrame frame;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	
	private Connection conexao = null;
	public static Connection dbConnector(String un, String pw) {

		try {
			
			
			String enderecoBD = "jdbc:mysql://127.0.0.1:3306/lp3_semestre2020.2";
			conn = DriverManager.getConnection(enderecoBD + "?useTimezone=true&serverTimezone=UTC", un,
					pw);
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Login window = new View_Login();
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
	public View_Login() {
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
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setToolTipText("Usu\u00E1rio");
		textFieldUsuario.setBounds(22, 75, 388, 40);
		frame.getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLogin.setBounds(183, 11, 51, 43);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblUsurio = new JLabel("Senha");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUsurio.setBounds(22, 107, 151, 43);
		frame.getContentPane().add(lblUsurio);
		
		JLabel label = new JLabel("Usu\u00E1rio");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(22, 37, 151, 43);
		frame.getContentPane().add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(22, 144, 388, 43);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textFieldUsuario.getText() == "" || textFieldUsuario.getText() == null) {
					
					JOptionPane.showMessageDialog(null, "Nome do Usuáirio Necessário");
					return;
				}
				
				else if (passwordField.getText() == "" || passwordField.getText() == null) {
					JOptionPane.showMessageDialog(null, "Senha Necessário");
					return;
				}
  try {
	  
	  conexao = dbConnector("studant", "12345");
	  JOptionPane.showMessageDialog(null, "Bem Vindo ao SCA");
	  String query = "select * from aluno where usuario=? and senha=?";
	  PreparedStatement pst = conexao.prepareStatement(query);
	  pst.setString(1, textFieldUsuario.getText());
	  pst.setString(2, passwordField.getText());
	  ResultSet rs = pst.executeQuery();
	
	  int  count = 0;
	  while (rs.next()) {
	
		  count++;
		
	}
	  
	  if (count == 1) {
		
		  JOptionPane.showMessageDialog(null, "Bem Vindo ao SCA");
		  
	}
	  else if (count > 1) {

		  JOptionPane.showMessageDialog(null, "Usuário Duplicado");
		  
	  }
	  else {
		  
		  JOptionPane.showMessageDialog(null, "Nome do usuário/senha estão incorretos, tente novamente!");
	  }
	  LimparCampos();
	  rs.close();
	  pst.close();
	  conexao.close();
} catch (Exception e2) {
	// TODO: handle exception
	
}

			}
		});
		btnLogin.setBounds(307, 211, 104, 39);
		frame.getContentPane().add(btnLogin);
	}
	
	void LimparCampos() {

		// limpar campos
		textFieldUsuario.setText("");
		passwordField.setText("");
			}
}
