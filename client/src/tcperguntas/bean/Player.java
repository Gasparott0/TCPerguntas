package tcperguntas.bean;

import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = 1L;

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
