package tcperguntas.model;

import java.util.List;

public class Ranking {

	public static void ranking(List<Player> players) {
		players.sort((a, b) -> b.getScore() - a.getScore());
	}
}
