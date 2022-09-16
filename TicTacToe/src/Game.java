import java.awt.event.*;
import javax.swing.*;

public class Game {
	static volatile String currentMark = "";
	
	public static void main(String[] args) 
	{
		final int BOARD_SPACES = 6;
		create_board(BOARD_SPACES);
	}
	

	
	// Construction of game board with Swing.
	private static void create_board(int boardSize)
	{
		
		// Setup of Labels and buttons.
		JFrame board = new JFrame();
		JLabel name = new JLabel("SOS");
		JLabel player1 = new JLabel("Player 1");
		JLabel player2 = new JLabel("Player 2");
		JCheckBox simple = new JCheckBox("Simple Game");
		JCheckBox general = new JCheckBox("General Game");
		JRadioButton s1, o1, s2, o2;
		ButtonGroup pl1 = new ButtonGroup(); // Only one button group should exist since only one button will be active.
		ButtonGroup pl2 = new ButtonGroup();
		s1 = new JRadioButton("S");
		o1 = new JRadioButton("O");
		s2 = new JRadioButton("S");
		o2 = new JRadioButton("O");
		
		// Setting positioning of buttons. A layout manager should be used to better align components.
		s1.setBounds(0, 100, 100, 30);
		o1.setBounds(0, 125, 100, 30);
		s2.setBounds(500, 100, 100, 30);
		o2.setBounds(500, 125, 100, 30);
		pl1.add(s1);
		pl1.add(o1);
		pl2.add(s2);
		pl2.add(o2);
		simple.setBounds(40, 0, 100, 20);
		general.setBounds(140, 0, 120, 20);
		name.setBounds(0, 0, 40, 20);
		player1.setBounds(0, 70, 100, 30);
		player2.setBounds(500,70,100,30);
		
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
		
		simple.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				general.setSelected(false);
			}
		});
		
		general.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				simple.setSelected(false);
			}
		});
		
		// Automatically populates the game board with responsive buttons.
		for(int i = 0; i < boardSize; i++)
		{
			for(int j = 0; j < boardSize; j++)
			{
				JButton b = new JButton("");
				b.setBounds((150 + 50 * j), (150 + 50 * i), 50, 50);
				
				// Adds the responsiveness to the game board.
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						b.setText(currentMark);
					}
				});
				board.add(b);
			}
		}
		
		// Adds the components to the gameboard.
		board.add(name);
		board.add(simple);
		board.add(general);
		board.add(s1);
		board.add(o1);
		board.add(s2);
		board.add(o2);
		board.add(player1);
		board.add(player2);
		
		// Sets the size and visibility of the gameboard.
		board.setSize(600, 600);
		board.setLayout(null);
		board.setVisible(true);
		
		
	}

}
