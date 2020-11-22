package jdbcTest;

public class Aluno {

	int id;
	String nome_aluno, cpf, curso;
	int semestre;

	public Aluno(String nome_aluno, String cpf, String curso, int semestre) {
		this.nome_aluno = nome_aluno;
		this.cpf = cpf;
		this.curso = curso;
		this.semestre = semestre;

	}

}
