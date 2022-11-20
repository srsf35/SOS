import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculationTest {

	@Test
	void testFibonnaci() 
	{
		Calculation testcase = new Calculation();
		int actual = testcase.fibonnacci(10);
		int expected = 55;
		
		assertEquals(expected, actual);
		
	}
}
