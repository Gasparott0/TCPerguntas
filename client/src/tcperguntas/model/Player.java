package tcperguntas.model;

public class Player {

	private String nickcname;
	private Integer score;

	public Player(String nickcname, Integer score) {
		this.nickcname = nickcname;
		this.score = score;
	}

	public String getNickcname() {
		return nickcname;
	}

	public Integer getScore() {
		return score;
	}

	public String toString() {
		return "Apelido: " + this.nickcname + " Pontuação: " + this.score + "\n";
	}
}
