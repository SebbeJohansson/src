package GBallClient;

import java.awt.Graphics;

import Shared.Const;

public class ScoreKeeper {
	private static class ScoreKeeperSingletonHolder {
		public static final ScoreKeeper instance = new ScoreKeeper();
	}

	public static ScoreKeeper getInstance() {
		return ScoreKeeperSingletonHolder.instance;
	}

	private int m_team1Score;
	private int m_team2Score;

	private ScoreKeeper() {
		m_team1Score = 0;
		m_team2Score = 0;
	}

	public void render(Graphics g) {
		g.setFont(Const.SCORE_FONT);
		g.setColor(Const.TEAM1_COLOR);
		g.drawString(new Integer(m_team1Score).toString(), (int) Const.TEAM1_SCORE_TEXT_POSITION.getX(),
				(int) Const.TEAM1_SCORE_TEXT_POSITION.getY());

		g.setColor(Const.TEAM2_COLOR);
		g.drawString(new Integer(m_team2Score).toString(), (int) Const.TEAM2_SCORE_TEXT_POSITION.getX(),
				(int) Const.TEAM2_SCORE_TEXT_POSITION.getY());
	}
}