package tcperguntas.model;

import java.util.List;

public class Game {

	public static void showRanking(List<Player> players) {
		Ranking.ranking(players);

		for (Player player : players)
			System.out.println(player);
	}

}
