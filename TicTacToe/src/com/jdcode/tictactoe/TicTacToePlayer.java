package com.jdcode.tictactoe;

import java.awt.*;

public class TicTacToePlayer {
	
	final String playerLabel;
	final Color playerColor;
		
	public TicTacToePlayer(String singlePlayerLabel, Color singlePlayerColor) {
		playerLabel= singlePlayerLabel;
		playerColor= singlePlayerColor;
	}

	public String getPlayerLabel() {return playerLabel;}
	public Color getPlayerColor() {return playerColor;}

}

