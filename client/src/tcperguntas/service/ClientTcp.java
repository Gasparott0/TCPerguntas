package tcperguntas.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import tcperguntas.bean.FileMessage;
import tcperguntas.model.Player;
import tcperguntas.model.Question;

public class ClientTcp {

	private Socket socket;
	private FileMessage messageReceived = null;
	private ObjectOutputStream outputStream;

	public ClientTcp() {
		try {
			String ip = JOptionPane.showInputDialog("Digite o endereço ip:");
			Integer port = Integer.parseInt(JOptionPane.showInputDialog("Digite a porta:"));
			this.socket = new Socket(ip, port);
			this.outputStream = new ObjectOutputStream(socket.getOutputStream());

			new Thread(new ListenerSocket(socket)).start();

//			menu();

		} catch (UnknownHostException e) {
			System.out.println("Erro no construtor clienttcp primeiro catch " + e);
		} catch (IOException e) {
			System.out.println("Erro no construtor clienttcp segundo catch " + e);
		}
	}

	private void menu() throws IOException {
		String nickcname = JOptionPane.showInputDialog("Digite seu nickcname:");
		Player player = new Player(nickcname, 0);

		while (true) {
			String opt = JOptionPane.showInputDialog("1 - Jogar" + "\n2 - Ver pontuação" + "\n3 - Sair");

			switch (opt) {
			case "1":
				Question[] teste = messageReceived.getQuestions();

				for (int i = 0; i <= 9; i++) {
					JOptionPane.showMessageDialog(null, "Pergunta: " + teste[i].getAnswer());
				}
				break;
			case "3":
				System.exit(0);
				break;
			}
		}

	}

	private class ListenerSocket implements Runnable {

		private ObjectInputStream inputStream;

		public ListenerSocket(Socket socket) throws IOException {
			this.inputStream = new ObjectInputStream(socket.getInputStream());

		}

		@Override
		public void run() {

			try {
				if ((messageReceived = (FileMessage) inputStream.readObject()) != null) {
					menu();
				}
			} catch (IOException e) {
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
		}
	}
}
