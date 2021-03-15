package tcperguntas.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import tcperguntas.bean.Player;
import tcperguntas.bean.Quiz;
import tcperguntas.bean.Ranking;
import tcperguntas.model.Question;

public class ClientTcp {

	private Socket socket;

	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	private Quiz quizReceived = null;
	private boolean played = false;

	public ClientTcp() throws IOException {

		try {

			String ip = JOptionPane.showInputDialog("Digite o endereço ip:");
			Integer port = Integer.parseInt(JOptionPane.showInputDialog("Digite a porta:"));
			
			this.socket = new Socket(ip, port);
			this.inputStream = new ObjectInputStream(socket.getInputStream());
			this.outputStream = new ObjectOutputStream(socket.getOutputStream());

			if ((quizReceived = (Quiz) inputStream.readObject()) != null) {
				menu();
			}

		} catch (UnknownHostException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	private void menu() throws IOException, ClassNotFoundException {
		String nickcname = JOptionPane.showInputDialog("Digite seu nickcname:");

		while (true) {
			String opt = JOptionPane.showInputDialog("1 - Jogar" + "\n2 - Ver pontuação" + "\n3 - Sair");

			switch (opt) {
			case "1":
				Question[] questions = this.quizReceived.getQuestions();
				String[] answers = new String[10];

				for (int i = 0; i <= 9; i++)
					answers[i] = JOptionPane.showInputDialog("Pergunta: " + questions[i].getQuestion()).toLowerCase();

				Integer score = calcScore(answers, questions);
				JOptionPane.showMessageDialog(null, "Sua pontuação foi: " + score);

				Player player = new Player(nickcname, score);

				this.outputStream.writeObject(player);
				this.outputStream.flush();

				this.played = true;

				break;

			case "2":

				if (this.played) {
					this.outputStream.writeObject(new Ranking());
					this.outputStream.flush();

					Ranking ranking = null;

					ranking = (Ranking) this.inputStream.readObject();
					JOptionPane.showMessageDialog(null, ranking);

				} else {
					JOptionPane.showMessageDialog(null, "Jogue uma partida antes");
				}
				break;

			case "3":
				System.exit(0);
				break;

			default:
				JOptionPane.showMessageDialog(null, "Digite uma opção válida");
				break;
			}
		}

	}

	private Integer calcScore(String[] answers, Question[] questions) {
		Integer score = 0;
		for (int i = 0; i <= 9; i++)
			if (answers[i].equals(questions[i].getAnswer()))
				score++;
		return score;
	}

}
