package tcperguntas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ranking implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Player> players = new ArrayList<Player>();

	public Ranking(List<Player> players) {
		players.sort((a, b) -> b.getScore() - a.getScore());
		this.players = players;
	}

	public Ranking() {

	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("Rank dos players \n\n");

		for (Player player : players) {
			if (player.getScore() == 10)
				str.append("Pontuação: " + player.getScore() + " Nick: " + player.getNickcname() + "\n");
			else
				str.append("Pontuação: " + player.getScore() + "   Nick: " + player.getNickcname() + "\n");
		}

		return str.toString();
	}
}
