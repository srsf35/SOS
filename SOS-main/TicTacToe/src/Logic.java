import java.util.Random;

public class Logic 
{
	protected boolean playerOne = true; //Current player can be seen as the boolean question to is it player one's turn?
	protected boolean isHuman1 = true; //Is player one an AI or human?
	protected boolean isHuman2 = true; //Is player two an AI or human?
	protected char[][] board; //Char board that keeps track of the current board state.
	protected boolean isOver = false;
	
	//Returns if it is currently player one's turn.
	public boolean isPlayerOne()
	{
		return playerOne;
	}
	
	public char returnPosition(int x, int y)
	{
		return board[x][y];
	}
	
	public void setPlayer1(boolean humanOrAI)
	{
		isHuman1 = humanOrAI;
	}
	
	public void setPlayer2(boolean humanOrAI)
	{
		isHuman2 = humanOrAI;
	}
	
	public boolean playerOneHuman()
	{
		return isHuman1;
	}
	
	public boolean playerTwoHuman()
	{
		return isHuman2;
	}
	
	public int[] AITurn()
	{
		Random ran = new Random();
		int helper = ran.nextInt(2);
		int[] data = new int[3];
		char move;
		int x, y;
		if(helper == 1)
		{
			move = 'S';
		}
		else
		{
			move = 'O';
		}
		
		do
		{
			x = (ran.nextInt(board.length));
			y = ran.nextInt(board.length );
			helper = takeTurn(x, y, move);
		}while(helper == 0 && !isOver);
		
		data[0] = x;
		data[1] = y;
		data[2] = helper;
		return data;
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
		isOver = true;
		return full;
	}
	
	//Starts the game and sets the board to the correct size.
	public void startGame(int boardSize)
	{
		isOver = false;
		board = new char[boardSize][boardSize];
	}
}