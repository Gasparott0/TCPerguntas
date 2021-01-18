package tcperguntas.model;

import java.io.Serializable;

public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	private String question;
	private String answer;

	public Question(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

}
