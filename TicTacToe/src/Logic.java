
public class Logic 
{
	protected boolean playerOne = true; //Current player can be seen as the boolean question to is it player one's turn?
	protected char[][] board; //Char board that keeps track of the current board state.
	
	//Returns if it is currently player one's turn.
	public boolean isPlayerOne()
	{
		return playerOne;
	}
	
	//Allows the player to populate a square with their chosen move.
	public int takeTurn(int indexx, int indexy, char move)
	{
		//Check if the square is empty.
		if(board[indexx][indexy] == '\u0000')
		{
			board[indexx][indexy] = move;
			playerOne = !playerOne;
			return 1; // Turn was taken correctly.
		}
		else // If not empty, try again.
		{
			return 0;
		}

	}
	
	//If the board state is full and play cannot continue.
	protected boolean over()
	{
		boolean full = true;
		
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board.length; y++)
			{
				if(board[x][y] == '\u0000')
				{
					return full = false;
				}
			}
		}
		return full;
	}
	
	//Starts the game and sets the board to the correct size.
	public void startGame(int boardSize)
	{
		board = new char[boardSize][boardSize];
	}
}