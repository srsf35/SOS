import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameModeTest {

	@Test
	void generalGame() 
	{
		Logic sos = new Logic();
		sos.setGameMode("General");
		assertEquals(sos.isSimple(), false);
	}
	
	@Test
	void simpleGame()
	{
		Logic sos = new Logic();
		sos.setGameMode("Simple");
		assertEquals(sos.isSimple(), true);
	}
	
	@Test
	void moveEmpty()
	{
		Logic sos = new Logic();
		sos.startGame(5);
		assertEquals(sos.takeTurn(0, 0, 'S'), true);	
	}
	
	@Test
	void moveOccupied()
	{
		Logic sos = new Logic();
		sos.startGame(5);
		sos.takeTurn(0, 0, 'S');
		assertEquals(sos.takeTurn(0, 0, 'S'), false);	
	}

}
