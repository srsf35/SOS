import java.io.File; // Import file class
import java.io.IOException; //Import exception handler
import java.io.FileWriter; // Import the file writer.

public class Simple extends Logic 
{
	
	
	
	public Simple(boolean recording) 
	{
		isRecording = recording;
	}

		public Simple() 
		{
			isRecording = false;
		}

		//Allows the player to populate a square with their chosen move.
		public int takeTurn(int indexx, int indexy, char move)
		{	
			try 
			{
				//The possible outcomes of a turn that need to be handled by the board.
				final int tryAgain = 0;
				final int nextTurn = 1;
				final int win = 2;
				final int draw = 3;
				
				
				FileWriter myWriter = new FileWriter("recording.txt", true);
				//Check if the square is empty.
				if(board[indexx][indexy] == '\u0000')
				{
					board[indexx][indexy] = move;
					
					if(winner(indexx, indexy, move))
					{
						if(isRecording)
						{
							if(playerOne)
							{
								myWriter.write("Player one moved: (" + indexx + ", " + indexy + ") " + move + "\n");
								myWriter.write("Player one is winner. \n"); 
							}
							else
							{
								myWriter.write("Player two moved: (" + indexx + ", " + indexy + ") " + move + "\n");
								myWriter.write("Player two is winner. \n");
							}
							
						}
						myWriter.close();
						return win;
					}
					else if (over())
					{
						if(isRecording)
						{
							myWriter.write("Game was a draw. \n");
						}
						myWriter.close();
						return draw;
					}
					
					if(isRecording)
					{
						if(playerOne)
						{
							myWriter.write("Player one moved: (" + indexx + ", " + indexy + ") " + move + "\n");
						}
						else
						{
							myWriter.write("Player two moved: (" + indexx + ", " + indexy + ") " + move + "\n");
						}

					myWriter.close();
					}
					playerOne = !playerOne;
					return nextTurn;
				}
				else // If not empty, try again.
				{
					myWriter.close();
					return tryAgain;
				}
			} 
			catch (IOException e) 
			{
			    
			}
			
			return 0;
			
		}
		
		public boolean winner(int indexx, int indexy, char move)
		{
			int startx = indexx - 1;
			int endx = indexx + 1;
			int starty = indexy - 1;
			int endy = indexy + 1;
			
			if(move == 'O')
			{
				//Check to make sure nothing is out of bounds.
				if(startx < 0)
				{
					startx = 0;
				}
				if(starty < 0)
				{
					starty = 0;
				}	
				if(endx > board.length)
				{
					endx = (board.length - 1);
				}
				if(endy > board.length)
				{
					endy = (board.length - 1);
				}
				
				for(int x = startx; x < endx; x++) // Will check if an O in the middle completes an SOS for every case except diagonal from top right to top left.
				{
					for(int y = starty; y < endy; y++)
					{
						if(board[x][y] == 'S')
						{
							if( (indexx + (indexx - x)) < board.length && indexy + (indexy - y) < board.length && board[(indexx + (indexx - x))][indexy + (indexy - y)] == 'S')
							{
								return true;
							}
						}
					}
				}
				
				if(indexx > 0 && indexy > 0) // Checks from the bottom left to the top right, with the top right being the last move and an S.
				{
					if( (indexx + 1) < board.length && (indexy - 1) >= 0 && board[indexx + 1][indexy - 1] == 'S')
					{
						if( (indexx - 1) >= 0 && (indexy + 1) < board.length && board[indexx - 1][indexy + 1] == 'S')
						{
							return true;
						}
					}
				}
				
			}
			else
			{
				startx = indexx - 2;
				endx = indexx + 2;
				starty = indexy - 2;
				endy = indexy + 2;
				
				if(startx >= 0 && starty >= 0) // Diagonal top left to bottom right, with bottom right being the last move and an S.
				{
					if( (board[indexx - 1][indexy - 1] == 'O'))
					{
						
						if(board[indexx - 2][indexy - 2] == 'S')
						{
							return true;
						}
					}
				}
				
				if(startx >= 0) // Checks from top to bottom, with bottom being the last move and an S.
				{
					
					if(board[indexx - 1][indexy] == 'O')
					{
						if(board[indexx - 2][indexy] == 'S')
						{
							return true;
						}
					}
				}
				
				if(starty >= 0) // Checks from left to right, with right being the last move and an S.
				{
					
					if(board[indexx][indexy - 1] == 'O')
					{
						if(board[indexx][indexy - 2] == 'S')
						{
							return true;
						}
					}
				}
				
				if(endx < board.length && endy < board.length) // Checks from bottom right to top left, with the top left being the last move and an S.
				{
					if(board[indexx + 1][indexy + 1] == 'O')
					{
						if(board[indexx + 2][indexy + 2] == 'S')
						{
							return true;
						}
					}
				}
				
				if(endx < board.length) // Checks from bottom to top, with the top being the last move and an S.
				{
					if(board[indexx + 1][indexy] == 'O')
					{
						if(board[indexx + 2][indexy] == 'S')
						{
							return true;
						}
					}	
					
					if(starty >= 0) // Checks from the bottom left to the top right, with the top right being the last move and an S.
					{
						if(board[indexx + 1][indexy - 1] == 'O')
						{
							if(board[indexx + 2][indexy - 2] == 'S')
							{
								return true;
							}
						}
					}
				}
				
				if(endy < board.length) // Checks from the right to the left, with the left being the last move and an S
				{
					if(board[indexx][indexy + 1] == 'O')
					{
						if(board[indexx][indexy + 2] == 'S')
						{
							return true;
						}
					}	
					
					if(startx >= 0)
					{
						if(board[indexx - 1][indexy + 1] == 'O')
						{
							if(board[indexx - 2][indexy + 2] == 'S')
							{
								return true;
							}
						}
					}
				}
		}
			return false;
		}
}