package com.jdcode.tictactoe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class TicTacToe implements ActionListener {

	//  Instance Variables
	private ArrayList<JButton> buttonArrayList = new ArrayList<JButton>();
	private JFrame window= new JFrame("Tic-Tac-Toe");
	private int turn= 0;
	UIManager.LookAndFeelInfo lnf[];
	String[] myStringArray= new String[]{"No, exit game", "Yes, play again"};
	TicTacToePlayer[] playerArray= new TicTacToePlayer[2];
	private TicTacToePlayer currentPlayer;
	
	//  Tic-Tac-Toe Constructor
	public TicTacToe() {
		playerArray[0]= new TicTacToePlayer("O", Color.YELLOW);
		playerArray[1]= new TicTacToePlayer("X", Color.GREEN);
		createWindow();
		for (int i = 0; i <= 8; i++) {
			window.add(createButton());
		}
		makeWindowVisible();
	}  //  End public TicTacToe()

	private void createWindow() {
		window.setSize(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new GridLayout(3, 3));
	}  //  End createWindow
	
	private JButton createButton() {
		JButton button = new JButton("");
		buttonArrayList.add(button);
		button.addActionListener(this);
		return button;
	}
	
	private void makeWindowVisible() {
			window.setVisible(true);
		}  //  End makeWindowVisible

	public void actionPerformed(ActionEvent a) {
		turn++;
		currentPlayer= playerArray[turn % 2];
		changeButtonAppearance((JButton) a.getSource(), currentPlayer);  //  Casting as JButton
		seeIfThereIsAWinner(currentPlayer);
	}  //  End public void actionPerformed
	
	public void changeButtonAppearance(JButton aButton, TicTacToePlayer myCurrentPlayer) {
		aButton.setFont(new Font("Verdana",Font.BOLD, 40));
		aButton.setForeground(Color.BLACK);  //Currently has no effect
		aButton.setText(myCurrentPlayer.getPlayerLabel());
		aButton.setBackground(myCurrentPlayer.getPlayerColor());
		aButton.setEnabled(false);
	}  //  End changeButtonAppearance

	private void seeIfThereIsAWinner(TicTacToePlayer myCurrentPlayer) {
		
		boolean didSomeoneWin= false;		
		didSomeoneWin= 	didIwin(0, 1, 2) || didIwin(3, 4, 5) || didIwin(6, 7, 8) ||		//  Horizontal Wins						
						didIwin(0, 3, 6) || didIwin(1, 4, 7) || didIwin(2, 5, 8) ||		//  Vertical Wins
						didIwin(0, 4, 8) || didIwin(2, 4, 6);  							//  Diagonal Wins
	
		if(didSomeoneWin){
			gameOver("      " + myCurrentPlayer.playerLabel + " Wins the game!\n Would you like to play again?");
		} else if(turn == 9 && !didSomeoneWin){
			gameOver("   Tie Game!  No Winner \n Would you like to play again?");
		}  //  End if
	}  //  seeIfThereIsAWinner

	public boolean didIwin(int button1, int button2, int button3) {
		// Must return boolean
		JButton buttonA= buttonArrayList.get(button1);
		JButton buttonB= buttonArrayList.get(button2);
		JButton buttonC= buttonArrayList.get(button3);
		return (buttonA.getText() == buttonB.getText() && buttonB.getText() == buttonC.getText() && buttonA.getText() != "");
	} // End didIwin
	
	public void gameOver(String myMessage) {
		int n = JOptionPane.showOptionDialog(null, myMessage, "Game Over",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
			    null, myStringArray, myStringArray[1]);
		if(n == 0){
			window.setVisible(false);
			window.dispose();
		}else{
			resetTheGame();
		}  //  End if n == 0
	}  //  End gameOver

	private void resetTheGame() {
		turn= 0;
		for (int i = 0; i <= 8; i++) {
			resetButtonAppearance(buttonArrayList.get(i));
		}
	}  //  End resetTheGame
	
	public void resetButtonAppearance(JButton aButton) {
		aButton.setText("");
		aButton.setBackground(null);
		aButton.setEnabled(true);
	}  //  End resetBtnAppear
	
	
	public static void main(String[] args) {
		new TicTacToe();
	}  //  End Main
	
}  //  End class TicTacToe implements ActionListner

