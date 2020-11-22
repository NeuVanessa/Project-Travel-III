package jdbcTest;

import java.sql.*;

public class ConnectionTest {

	public static void main(String[] args) throws SQLException {
		Connection minhaConexao = null;
		PreparedStatement minhaSetenca = null;
		Statement minhaSetenca2 = null;
		ResultSet meuResultado = null;

		String enderecoBD = "jdbc:mysql://127.0.0.1:3306/lp3_semestre2020.2";
		String usuario = "studant";
		String senha = "12345";

		try {
			// Fazendo conex�o com o banco de dados
			minhaConexao = DriverManager.getConnection(enderecoBD + "?useTimezone=true&serverTimezone=UTC", usuario,
					senha);

			System.out.println("Conex�o ok mano!");

			minhaSetenca = minhaConexao.prepareStatement("delete from aluno where nome_aluno=? and cpf=?");
			minhaSetenca.setString(1, "Lucas");
			minhaSetenca.setString(2, "251.859.585-11");
			minhaSetenca.execute();

			// linha 28 a 31 faz a inclus�o de um novo aluno no banco de dados
			// Adicionando um novo usu�rio no banco de dados
			// o int rowsAffected = 0; com ele da para saber se existe usu�rio ou n�o
//		int rowsAffected = 
//	rowsAffected = minhaSetenca.executeUpdate(" insert into aluno (nome_aluno, cpf, curso,semestre)"
//				+ " values ('Lucas','251.859.585-11','Direito',2)");
//			

			// Update atualizando as informa�oes, primeiro passa o cpf novo e por fim no and
			// os dados do cpf antigo
//			
//			int rowsAffected = 
//			rowsAffected = minhaSetenca.executeUpdate(
//					"update aluno set cpf='865.968.585-96' where nome_aluno='Genison' and cpf='111.555.555-11'");

			// deletando um aluno da base de dados
//			int rowsAffected = rowsAffected = minhaSetenca
//					.executeUpdate("delete from aluno where nome_aluno='Julio' and cpf='586.685.585-11'");

			// Criando uma Statement
			minhaSetenca2 = minhaConexao.createStatement();
			// executando uma query no banco de dados
			meuResultado = minhaSetenca2.executeQuery(" select * from aluno ");

			// printando os resultados
			while (meuResultado.next()) {
				// imprimindo as informa�oes
				System.out.println(meuResultado.getString("nome_aluno") + ", " + meuResultado.getString("cpf"));

			}

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}

	}
}
