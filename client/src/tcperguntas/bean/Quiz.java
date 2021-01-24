package tcperguntas.bean;

import java.io.Serializable;

import tcperguntas.model.Question;

public class Quiz implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Question[] questions = new Question[10];

	public Quiz(Question[] questions) {
		this.questions = questions;
	}

	public Question[] getQuestions() {
		return questions;
	}
	
	

}
