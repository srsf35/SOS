import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Board {
	static volatile String currentMark = "";
	JPanel playSpace; 
	JFrame board; 
	JLabel currPlayer;
	Logic sos = new Logic();
	private int boardSize = 3;
	private JButton gameSpace[][];

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
		JRadioButton s1, o1, s2, o2, simpleGame, generalGame, human1, human2, computer1, computer2;
		ButtonGroup pl1 = new ButtonGroup(); 
		ButtonGroup gameMode = new ButtonGroup();
		ButtonGroup currPlayer1 = new ButtonGroup();
		ButtonGroup currPlayer2 = new ButtonGroup();
		
		s1 = new JRadioButton("S");
		o1 = new JRadioButton("O");
		s2 = new JRadioButton("S");
		o2 = new JRadioButton("O");
		human1 = new JRadioButton("Human");
		computer1 = new JRadioButton("Computer");
		human2 = new JRadioButton("Human");
		computer2 = new JRadioButton("Computer");
		simpleGame = new JRadioButton("Simple Game");
		generalGame = new JRadioButton("General Game");
		
		// Setting the size of components, layout is handled by layout manager.
		s1.setSize(100,30);
		o1.setSize(100, 30);
		s2.setSize(100, 30);
		o2.setSize(100, 30);
		human1.setSize(100, 30);
		computer1.setSize(100,30);
		human2.setSize(100, 30);
		computer2.setSize(100,30);
		simpleGame.setSize(100, 30);
		generalGame.setSize(100,30);
		pl1.add(s1);
		pl1.add(o1);
		pl1.add(s2);
		pl1.add(o2);
		currPlayer1.add(human1);
		currPlayer1.add(computer1);
		currPlayer2.add(human2);
		currPlayer2.add(computer2);
		gameMode.add(generalGame);
		gameMode.add(simpleGame);
		name.setSize(40, 20);
		player1.setSize(100, 30);
		player2.setSize(100,30);
		human1.doClick();
		human2.doClick();
		
		//Listener to change the board size based on user input.
		usrInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				adjustGrid(Integer.parseInt(usrInput.getText()));
		}
		});
		
		human1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				sos.setPlayer1(true);
		}
		});
		
		human2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				sos.setPlayer2(true);
		}
		});
		
		computer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				sos.setPlayer1(false);
				if(!sos.playerTwoHuman())
				{
					AIGame();
				}
				else
				{
					AImove();
				}
		}
		});
		
		computer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				sos.setPlayer2(false);
				if(!sos.playerOneHuman())
				{
					AIGame();
				}
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
				human1.doClick();
				human2.doClick();
				
				if(!sos.playerOneHuman() && !sos.playerTwoHuman())
				{
					AIGame();
				}
				
			}
		});
		
		generalGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sos = new General();
				adjustGrid(boardSize);
				human1.doClick();
				human2.doClick();
				
				if(!sos.playerOneHuman() && !sos.playerTwoHuman())
				{
					AIGame();
				}
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
		c.gridy = 4;
		board.add(human1, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 5;
		board.add(computer1, c);
		
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
		
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 4;
		board.add(human2, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 5;
		board.add(computer2, c);
		
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
	
	private void AImove()
	{
			int[] results = new int[3];
			results = sos.AITurn();
			int x = results[0];
			int y = results[1];
			String markAt = String.valueOf(sos.returnPosition(x, y));
	
			
			if(results[2] == 1)
			{
				if(sos.isPlayerOne())
				{
					currPlayer.setText("Current Player: Player 1");
				}
				else 
				{
					currPlayer.setText("Current Player: Player 2");
				}
			}
			else if(results[2] == 2)
			{
				if(sos.isPlayerOne())
				{
					currPlayer.setText("Winner: Player 1");
				}
				else 
				{
					currPlayer.setText("Winner: Player 2");
				}
			}
			else if(results[2] == 3)
			{
				currPlayer.setText("A Draw!");
			}
			
			
			gameSpace[x][y].setText(markAt);
			
	}
	
	private void AIGame()
	{
		adjustGrid(boardSize);
		do
		{
			AImove();
		}while( (!sos.playerOneHuman()) && (!sos.playerTwoHuman()) && (!sos.isOver));
	}
	
	//Helper methods to adjust the size of the playspace based on user input. TODO: Error checking
	public void adjustGrid(int boardSize) // TODO: change to private. Currently public for testing.
	{
		currPlayer.setText("Current Player: Player 1");
		this.boardSize = boardSize;
		gameSpace = new JButton [boardSize][boardSize];
		
		
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
				gameSpace[i][j] = button.b;
				
				// Adds the responsiveness to the playspace
				button.b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(currentMark != "")
						{
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
							if( (result != 2 || result != 3) && (sos.playerOneHuman() == false || sos.playerTwoHuman() == false) && !sos.isOver)
							{
								AImove();
							}
							
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