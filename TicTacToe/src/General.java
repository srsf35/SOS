import java.io.File; // Import file class
import java.io.IOException; //Import exception handler
import java.io.FileWriter; // Import the file writer.

public class General extends Logic 
{
	int player1Score = 0;
	int player2Score = 0;
	public General(boolean recording) 
	{
		isRecording = recording;
	}


	public General() 
	{
		isRecording = false;
	}


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
				score(indexx, indexy, move);
				if(over())
				{
					if(player1Score > player2Score)
					{
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
							myWriter.write("Player one is winner with a score of: " + player1Score + " \n"); 
						}
						myWriter.close();
						
						playerOne = true;
						return win; // Player one is the winner
					}
					else if(player2Score > player1Score)
					{
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
							
							myWriter.write("Player two is winner with a score of: " + player2Score + " \n"); 
						}
						myWriter.close();
						
						playerOne = false;
						return win; // Player two is the winner.
					}
					else
					{
						myWriter.write("Game is a draw \n"); 
						myWriter.close();
						return draw; // Game was a draw
					}
				}
				if(playerOne)
				{
					myWriter.write("Player one moved: (" + indexx + ", " + indexy + ") " + move + "\n");
				}
				else
				{
					myWriter.write("Player two moved: (" + indexx + ", " + indexy + ") " + move + "\n");
				}
				
				myWriter.close();
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
	

	private void score(int indexx, int indexy, char move)
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
						if(board[(indexx + (indexx - x))][indexy + (indexy - y)] == 'S')
						{
							if(playerOne)
							{
								player1Score++;
							}
							else
							{
								player2Score++;
							}
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
						if(playerOne)
						{
							player1Score++;
						}
						else
						{
							player2Score++;
						}
					}
				}
			}
			
		}
		else
		{
			//Check down Column
			if( indexx+1 < board.length)
			{
				if(board[indexx+1][indexy] == 'O')
				{
					if(indexx+2 < board.length)
					{
						if(board[indexx + 2][indexy] == 'S')
						{
							if(playerOne)
							{
								player1Score++;
							}
							else
							{
								player2Score++;
							}
						}
					}
				}
			}
		
				//Check up Column
				if( indexx-1 >= 0)
				{
					if(board[indexx-1][indexy] == 'O')
					{
						if(indexx - 2 >= 0)
						{
							if(board[indexx - 2][indexy] == 'S')
							{
								if(playerOne)
								{
									player1Score++;
								}
								else
								{
									player2Score++;
								}
							}
						}
					}
				}
				
				//Check row back
				if( indexy+1 < board.length)
				{
					if(board[indexx][indexy+1] == 'O')
					{
						if(indexy+2 < board.length)
						{
							if(board[indexx][indexy +2] == 'S')
							{
								if(playerOne)
								{
									player1Score++;
								}
								else
								{
									player2Score++;
								}
							}
						}
					}
				}
				
				//Check row front
				if( indexy-1 >= 0)
				{
					if(board[indexx][indexy - 1] == 'O')
					{
						if(indexy - 2 >= 0)
						{
							if(board[indexx][indexy - 2] == 'S')
							{
								if(playerOne)
								{
									player1Score++;
								}
								else
								{
									player2Score++;
								}
							}
						}
					}
				}
			//Check bottom right diagonal.
			if(indexx-1 >=0 && indexy-1 >= 0)
			{
				if(board[indexx-1][indexy-1] == 'O')
				{
					if(indexx-2 >= 0 && indexy -2 >= 0)
					{
						if(board[indexx-2][indexy-2] == 'S')
						{
							if(playerOne)
							{
								player1Score++;
							}
							else
							{
								player2Score++;
							}
						}
					}
				}
			}
		
			//Top left diagonal.
			if(indexx+1 < board.length && indexy+1 < board.length)
			{
				if(board[indexx+1][indexy+1] == 'O')
				{
					if(indexx+2 < board.length && indexy+2 < board.length)
					{
						if(board[indexx+2][indexy+2] == 'S')
						{
							if(playerOne)
							{
								player1Score++;
							}
							else
							{
								player2Score++;
							}
						}
					}
				}
			}
		
			//Bottom left diagonal
			if(indexx-1 >= 0 && indexy+1 < board.length)
			{
				if(board[indexx-1][indexy+1] == 'O')
				{
					
					if(indexx-2 >= 0 && indexy+2 < board.length)
					{
						if(board[indexx-2][indexy+2] == 'S')
						{
							if(playerOne)
							{
								player1Score++;
							}
							else
							{
								player2Score++;
							}
						}
					}
				}
			}
		
			//Top right diagonal
			if(indexx+1 < board.length && indexy-1 >= 0)
			{
				if(board[indexx+1][indexy-1] == 'O')
				{				
					if(indexy-2 >= 0 && indexx+2 < board.length)
					{
						if(board[indexx+2][indexy-2] == 'S')
						{
							if(playerOne)
							{
								player1Score++;
							}
							else
							{
								player2Score++;
							}
						}
					}
				}
			}
		}	
	}	
}