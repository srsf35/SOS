import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class Board {
	static volatile String currentMark = "";
	JPanel playSpace; // TODO: Set name as playspace so that the components of board can be searched rather.
	JFrame board; 
	JLabel currPlayer;
	Logic sos = new Logic();
	private int boardSize = 3;

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
				sos = new Simple();
				adjustGrid(boardSize);
			}
		});
		
		generalGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sos = new General();
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
	
	//Helper methods to adjust the size of the playspace based on user input. TODO: Error checking
	public void adjustGrid(int boardSize) // TODO: change to private. Currently public for testing.
	{
		currPlayer.setText("Current Player: Player 1");
		this.boardSize = boardSize;
		if(playSpace.getComponentCount() > 0)
		{
			playSpace.removeAll();
		}
		if(boardSize < 3) //TODO: Add catch for this exception
		{
			throw new IllegalArgumentException("Board size is less than three");
		}
		playSpace.setLayout(new GridLayout(boardSize, boardSize));
		for(int i = 0; i < boardSize; i++)
		{
			for(int j = 0; j < boardSize; j++)
			{
				class Square
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
						int result = sos.takeTurn(button.indexx, button.indexy, currentMark.charAt(0));
						
						if(result == 1)
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
						else if(result == 2)
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
						else if(result == 3)
						{
							button.b.setText(currentMark);
							currPlayer.setText("A Draw!");
						}
					}
				});
				playSpace.add(button.b);
			}
		}
		playSpace.revalidate();
		sos.startGame(boardSize);
	}	
}