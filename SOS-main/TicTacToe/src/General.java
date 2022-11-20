
public class General extends Logic 
{
	int player1Score = 0;
	int player2Score = 0;
	public int takeTurn(int indexx, int indexy, char move)
	{
		//Check if the square is empty.
		if(board[indexx][indexy] == '\u0000')
		{
			board[indexx][indexy] = move;
			score(indexx, indexy, move);
			if(over())
			{
				if(player1Score > player2Score)
				{
					playerOne = true;
					return 2; // Player one is the winner
				}
				else if(player2Score > player1Score)
				{
					playerOne = false;
					return 2; // Player two is the winner.
				}
				else
				{
					return 3; // Game was a draw
				}
			}
			playerOne = !playerOne;
			
			return 1;
		}
		else // If not empty, try again.
		{
			return 0;
		}
	}
	

	private void score(int indexx, int indexy, char move)
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
								return;
							}
							else
							{
								player2Score++;
								return;
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
									return;
								}
								else
								{
									player2Score++;
									return;
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
									return;
								}
								else
								{
									player2Score++;
									return;
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
									return;
								}
								else
								{
									player2Score++;
									return;
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
							return;
						}
						else
						{
							player2Score++;
							return;
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
							return;
						}
						else
						{
							player2Score++;
							return;
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
							return;
						}
						else
						{
							player2Score++;
							return;
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
							return;
						}
						else
						{
							player2Score++;
							return;
						}
					}
				}
			}
		}
	}	
}