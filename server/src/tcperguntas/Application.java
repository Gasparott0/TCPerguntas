package tcperguntas;

import java.io.IOException;

import javax.swing.JOptionPane;

import tcperguntas.model.Question;
import tcperguntas.service.ServerTcp;

public class Application {

	public static void main(String[] args) throws IOException {

		Question questions[] = new Question[10];

		for (int i = 0; i <= 9; i++) {
			String question = JOptionPane.showInputDialog("Digite a " + (i + 1) + "º pergunta:");
			String answer = JOptionPane.showInputDialog("Digite a resposta da " + (i + 1) + "º pergunta").toLowerCase();

			questions[i] = new Question(question, answer);
		}

		new ServerTcp(questions);
	}
}
