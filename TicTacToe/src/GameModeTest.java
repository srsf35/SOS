import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameModeTest {

	@Test
	void moveEmpty() // Checking if a turn was valid
	{
		Logic sos = new Logic();
		sos.startGame(5, 0);
		assertEquals(sos.takeTurn(0, 0, 'S'), 1);	
	}
	
	@Test
	void moveOccupied() // Checking if a turn was invalid
	{
		Logic sos = new Logic();
		sos.startGame(5, 0);
		sos.takeTurn(0, 0, 'S');
		assertEquals(sos.takeTurn(0, 0, 'S'), 0);	
	}
	
	@Test
	void full() // If the game is being declared as over after the board is full.
	{
		Logic sos = new Logic();
		sos.startGame(3, 0);
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				sos.takeTurn(i, j, 'S');
			}
		}
		assertEquals(sos.over(), true);
	}

	@Test
	void generalDraw() // If a general game is a draw (Board filled)
	{
		General sos = new General();
		int turn = -1;
		sos.startGame(3, 0);
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				turn = sos.takeTurn(i, j, 'S');
			}
		}
		assertEquals(turn, 3);
	}
	
	@Test
	void simpleDraw() //If a simple game is a draw
	{
		Simple sos = new Simple();
		int turn = -1;
		sos.startGame(3, 0);
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				turn = sos.takeTurn(i, j, 'S');
			}
		}
		assertEquals(turn, 3);
	}
	
	@Test
	void generalScore() // Tests that general game is scoring.
	{
		General sos = new General();
		sos.startGame(3, 0);
		sos.takeTurn(0, 0, 'S');
		sos.takeTurn(0, 1, 'O');
		sos.takeTurn(0, 2, 'S');
		assertEquals(sos.player1Score,1);
	}
	
	@Test
	void generalPlayer1Win() // Test player 1 winning a general game.
	{
		General sos = new General();
		sos.startGame(3, 0);
		sos.takeTurn(0, 0, 'S');
		sos.takeTurn(0, 1, 'O');
		sos.takeTurn(0, 2, 'S');
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				sos.takeTurn(i, j, 'S');
			}
		}
		assertEquals(sos.isPlayerOne(), true);
	}
	
	@Test
	void simplePlayer1Win() // Tests Player 1 winning a simple game.
	{
		Simple sos = new Simple();
		sos.startGame(3, 0);
		sos.takeTurn(0, 0, 'S');
		sos.takeTurn(0, 1, 'O');
		sos.takeTurn(0, 2, 'S');
		assertEquals( (sos.isPlayerOne()) && (sos.winner(0, 2, 'S')), true);
	}
}
