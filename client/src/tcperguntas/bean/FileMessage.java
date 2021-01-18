package tcperguntas.bean;

import java.io.Serializable;

import tcperguntas.model.Player;
import tcperguntas.model.Question;

public class FileMessage implements Serializable {

	private static final long serialVersionUID = -8294732378791068233L;

	private Player player;
	private Question[] questions = new Question[10];

	public FileMessage(Player player, Question[] questions) {
		this.player = player;
		this.questions = questions;
	}

	public FileMessage(Player player) {
		this.player = player;
	}

	public FileMessage() {
	}

	public Player getPlayer() {
		return player;
	}

	public Question[] getQuestions() {
		return questions;
	}

}
