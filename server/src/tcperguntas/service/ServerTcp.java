package tcperguntas.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import tcperguntas.bean.Player;
import tcperguntas.bean.Quiz;
import tcperguntas.bean.Ranking;
import tcperguntas.model.Question;

public class ServerTcp {

	private ServerSocket serverSocket;
	private Socket socket;
	private List<Player> players = new ArrayList<Player>();

	public ServerTcp(Question[] questions) {

		try {
			serverSocket = new ServerSocket(12000);

			while (true) {
				socket = serverSocket.accept();

				new Thread(new ListenerSocket(socket, questions)).start();
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	private class ListenerSocket implements Runnable {

		private ObjectOutputStream outputStream;
		private ObjectInputStream inputStream;
		private Question[] questions;

		public ListenerSocket(Socket socket, Question[] questions) throws IOException {
			this.outputStream = new ObjectOutputStream(socket.getOutputStream());
			this.inputStream = new ObjectInputStream(socket.getInputStream());
			this.questions = questions;
		}

		@Override
		public void run() {

			Quiz quiz = new Quiz(questions);

			try {
				this.outputStream.writeObject(quiz);
				this.outputStream.flush();
				while (true) {
					Player player;
					Object obj = this.inputStream.readObject();
					
					if(obj instanceof Player) {
						player = (Player) obj;
						players.add(player);
					}
					if (obj instanceof Ranking) {
						Ranking ranking = new Ranking(players);
						this.outputStream.writeObject(ranking);
						this.outputStream.flush();
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}

		}
	}
}
