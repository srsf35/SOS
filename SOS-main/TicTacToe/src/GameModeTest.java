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
		sos.startGame(5);
		assertEquals(sos.takeTurn(0, 0, 'S'), 1);	
	}
	
	@Test
	void moveOccupied() // Checking if a turn was invalid
	{
		Logic sos = new Logic();
		sos.startGame(5);
		sos.takeTurn(0, 0, 'S');
		assertEquals(sos.takeTurn(0, 0, 'S'), 0);	
	}
	
	@Test
	void full() // If the game is being declared as over after the board is full.
	{
		Logic sos = new Logic();
		sos.startGame(3);
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
		sos.startGame(3);
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
		sos.startGame(3);
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
		sos.startGame(3);
		sos.takeTurn(0, 0, 'S');
		sos.takeTurn(0, 1, 'O');
		sos.takeTurn(0, 2, 'S');
		assertEquals(sos.player1Score,1);
	}
	
	@Test
	void generalPlayer1Win() // Test player 1 winning a general game.
	{
		General sos = new General();
		sos.startGame(3);
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
		sos.startGame(3);
		sos.takeTurn(0, 0, 'S');
		sos.takeTurn(0, 1, 'O');
		sos.takeTurn(0, 2, 'S');
		assertEquals( (sos.isPlayerOne()) && (sos.winner(0, 2, 'S')), true);
	}
	
	@Test
	void AITakesTurnSimple() // Tests that an AI makes a valid move in a simple game
	{
		Simple sos = new Simple();
		sos.startGame(3);
		assertNotNull(sos.AITurn());
	}
	
	@Test
	void AIEndsSimple() // Tests if the AI can end a game.
	{
		Simple sos = new Simple();
		sos.startGame(3);
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		assertEquals(sos.isOver, true);
	}
	
	@Test
	void AINotOverSimple() // Tests to make sure the game does not end early. Minimum number of moves to end a game is 3
	{
		Simple sos = new Simple();
		sos.startGame(3);
		sos.AITurn();
		sos.AITurn();
		assertEquals(sos.isOver, false);
	}
	
	@Test
	void AITakesTurnGeneral() // Tests to make sure the AI makes a valid move in a general game.
	{
		General sos = new General();
		sos.startGame(3);
		assertNotNull(sos.AITurn());
	}
	
	@Test
	void AIEndsGeneral() // Tests to make sure the AI can end a general game.
	{
		General sos = new General();
		sos.startGame(3);
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		sos.AITurn();
		assertEquals(sos.isOver, true);
	}
	
	@Test
	void AINotOverGeneral() // Tests to make sure a general game is not ending early.
	{
		General sos = new General();
		sos.startGame(3);
		sos.AITurn();
		sos.AITurn();
		assertEquals(sos.isOver, false);
	}
	
}
