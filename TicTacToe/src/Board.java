import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class Board {
	static volatile String currentMark = " ";
	JPanel playSpace; 
	JFrame board; 
	JLabel currPlayer;
	Logic sos = new Logic();
	private int boardSize = 3;
	private int gameNumber = 0; // What game it is.
	private boolean isRecording = false;

	public Board()
	{		
		// Setup of board and layout. Using GridBag layout
		board = new JFrame();
		board.setSize(600, 600);
		board.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Declaration of buttons, labels, and playspace components.
		playSpace = new JPanel(new GridLayout(boardSize, boardSize));
		JTextField usrInput = new JTextField("Size");
		currPlayer = new JLabel("Current Player: Player 1");
		JLabel name = new JLabel("SOS");
		JLabel player1 = new JLabel("Player 1");
		JLabel player2 = new JLabel("Player 2");
		JRadioButton s1, o1, s2, o2, simpleGame, generalGame;
		ButtonGroup pl1 = new ButtonGroup(); 
		ButtonGroup gameMode = new ButtonGroup();
		s1 = new JRadioButton("S");
		o1 = new JRadioButton("O");
		s2 = new JRadioButton("S");
		o2 = new JRadioButton("O");
		simpleGame = new JRadioButton("Simple Game");
		generalGame = new JRadioButton("General Game");
		JCheckBox recordGame = new JCheckBox("Record game?");
		
		// Setting the size of components, layout is handled by layout manager.
		s1.setSize(100,30);
		o1.setSize(100, 30);
		s2.setSize(100, 30);
		o2.setSize(100, 30);
		simpleGame.setSize(100, 30);
		generalGame.setSize(100,30);
		pl1.add(s1);
		pl1.add(o1);
		pl1.add(s2);
		pl1.add(o2);
		gameMode.add(generalGame);
		gameMode.add(simpleGame);
		name.setSize(40, 20);
		player1.setSize(100, 30);
		player2.setSize(100,30);
		recordGame.setSize(100,30);
		
		//Listener to change the board size based on user input.
		usrInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				adjustGrid(Integer.parseInt(usrInput.getText()));
		}
		});
		
		// Adding functionality to the buttons.
		s1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentMark = "S";
			}
		});
		
		s2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				currentMark = "S";
			}
		});
		
		//Starts recording the game.
		recordGame.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sos.toggleRecord();
				isRecording = !isRecording;
				gameNumber = 0;
				adjustGrid(boardSize);
			}
		});
		
		o1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentMark = "O";
			}
		});
		
		o2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentMark = "O";
			}
		});
		
		simpleGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sos = new Simple(isRecording);
				adjustGrid(boardSize);
			}
		});
		
		generalGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sos = new General(isRecording);
				adjustGrid(boardSize);
			}
		});
			
		// Adjusts the constraints and adds components to the frame.
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0; //Column
		c.gridy = 0; //Row
		board.add(name, c);
		
		c.fill = GridBagConstraints.BOTH; //Just keeps the spacing consistent.
		c.gridx = 1;
		c.gridy = 0;
		board.add(simpleGame, c);

		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		board.add(generalGame, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 0;
		board.add(usrInput,c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 0;
		board.add(recordGame,c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		board.add(player1, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		board.add(s1,c);

		c.fill = GridBagConstraints.BOTH;
		c.gridy = 3;
		board.add(o1, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 5;
		c.gridy = 1;
		board.add(player2,c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 2;
		board.add(s2,c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 3;
		board.add(o2,c);
		
		c.gridx = 2;
		c.gridy = 10;
		c.weightx = 1;
		c.weighty = 1;
		board.add(playSpace, c);
		adjustGrid(boardSize);
		
		c.gridx = 2;
		c.gridy = 11;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.SOUTH;
		board.add(currPlayer, c);

		board.setVisible(true);
		playSpace.setVisible(true);
	}
	
	//Helper methods to adjust the size of the playspace based on user input. 
	public void adjustGrid(int boardSize) 
	{
		currPlayer.setText("Current Player: Player 1");
		this.boardSize = boardSize;
		
		if(playSpace.getComponentCount() > 0) //Resets the components on the board after the board is reset.
		{
			playSpace.removeAll();
		}
		if(boardSize < 3) 
		{
			throw new IllegalArgumentException("Board size is less than three");
		}
		
		playSpace.setLayout(new GridLayout(boardSize, boardSize));
		
		//Adds the buttons to the play space.
		for(int i = 0; i < boardSize; i++)
		{
			for(int j = 0; j < boardSize; j++)
			{
				class Square //A square is what the players mark with either an S or an O. Therefore it is composed of a button and its coordinates.
				{
					public JButton b;
					public int indexx;
					public int indexy;		
				}	
				Square button = new Square();
				button.b = new JButton("");		
				button.indexx = i;
				button.indexy = j;
				
				// Adds the responsiveness to the playspace
				button.b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//The results from taking a turn.
						final int nextTurn = 1;
						final int winner = 2;
						final int draw = 3;
						
						if(currentMark.charAt(0) != ' ') //Prevents input when neither an S or O is selected.
						{
							int result = sos.takeTurn(button.indexx, button.indexy, currentMark.charAt(0)); //Results in one of three outcomes.
							
							if(result == nextTurn) //Game is not over and another turn should be taken.
							{
								button.b.setText(currentMark);
								
								if(sos.isPlayerOne())
								{
									currPlayer.setText("Current Player: Player 1");
								}
								else 
								{
									currPlayer.setText("Current Player: Player 2");
								}
							}
							else if(result == winner) //Game has finished with a winner.
							{
								button.b.setText(currentMark);
								if(sos.isPlayerOne())
								{
									currPlayer.setText("Winner: Player 1");
								}
								else 
								{
									currPlayer.setText("Winner: Player 2");
								}
							}
							else if(result == draw) //Game has finished in a draw.
							{
								button.b.setText(currentMark);
								currPlayer.setText("A Draw!");
							}
						}		
					}
				});
				playSpace.add(button.b);
			}
		}
		playSpace.revalidate();
		sos.startGame(boardSize, gameNumber);
		gameNumber++;
	}	
}