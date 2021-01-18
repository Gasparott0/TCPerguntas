package tcperguntas.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import tcperguntas.bean.FileMessage;
import tcperguntas.model.Player;
import tcperguntas.model.Question;

@SuppressWarnings("unused")
public class ServerTcp {

	private ServerSocket serverSocket;
	private Socket socket;
	private Map<Player, ObjectOutputStream> streamMap = new HashMap<Player, ObjectOutputStream>();

	public ServerTcp(Question questions[]) throws IOException {

		try {

			serverSocket = new ServerSocket(12000);

			while (true) {
				socket = serverSocket.accept();

				new Thread(new ListenerSocket(socket, questions)).start();
			}

		} catch (Exception e) {
			System.out.println("Erro no construtor servertcp " + e);
		} finally {
			serverSocket.close();
		}

	}

	private class ListenerSocket implements Runnable {

		private ObjectOutputStream outputStream;
		private ObjectInputStream inputStream;
		private Question questions[];

		public ListenerSocket(Socket socket, Question questions[]) throws IOException {
			this.outputStream = new ObjectOutputStream(socket.getOutputStream());
			this.inputStream = new ObjectInputStream(socket.getInputStream());
			this.questions = questions;
		}

		@Override
		public void run() {

			FileMessage message = new FileMessage(null, questions);

			try {
				this.outputStream.flush();
				this.outputStream.writeObject(message);
				this.outputStream.flush();
				this.outputStream.close();
			} catch (IOException e) {
				System.out.println("Erro envio do message para o cliente " + e);
			}

//			try {
//				while ((message = (FileMessage) inputStream.readObject()) != null) {
//					streamMap.put(message.getPlayer(), outputStream);
//
//				}
//			} catch (IOException e) {
//				streamMap.remove(message.getPlayer());
//				System.out.println("Erro conexão com cliente primeiro catch " + e);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//				System.out.println("Erro conexão com cliente segundo catch " + e);
//			}

		}
	}
}
