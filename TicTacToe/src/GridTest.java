import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GridTest {

	@Test
	void testNegative() {
		Board test = new Board();
		assertThrows(IllegalArgumentException.class,
				() -> {
					test.adjustGrid(-5);
				});
	}
	@Test
	void testZero()
	{
		Board test = new Board();
		assertThrows(IllegalArgumentException.class,
				() -> {
					test.adjustGrid(0);
				});
	}
	@Test
	void testNumNonNum()
	{
		Board test = new Board();
		assertThrows(IllegalArgumentException.class,
				() -> {
					test.adjustGrid(Integer.parseInt("Twenty"));
				});
	}
}
