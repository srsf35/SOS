
public class Logic 
{
	private boolean playerOne = true; //Current player can be seen as the boolean question to is it player one's turn?
	private boolean isSimpleGame = true; //Only two game modes, so why not represent it as the question: is the game mode simple?
	private char[][] board; //Char board that keeps track of the current board state.
	
	//Returns if the game is simple or not.
	public boolean isSimple()
	{
		return isSimpleGame;
	}
	
	//Returns if it is currently player one's turn.
	public boolean isPlayerOne()
	{
		return playerOne;
	}
	
	//Sets the game mode to either simple or General.
	public void setGameMode(String gameMode)
	{
		if(gameMode.equals("Simple"))
		{
			isSimpleGame = true;
		}
		else
		{
			isSimpleGame = false;
		}
	}
	
	//Allows the player to populate a square with their chosen move.
	public boolean takeTurn(int indexx, int indexy, char move)
	{
		if(board[indexx][indexy] == '\u0000')
		{
			board[indexx][indexy] = move;
			playerOne = !playerOne;
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	//Starts the game and sets the board to the correct size.
	public void startGame(int boardSize)
	{
		board = new char[boardSize][boardSize];
	}
	
	public void endGame()
	{
		System.out.print(board);
	}
}