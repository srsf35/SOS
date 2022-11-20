
public class Simple extends Logic 
{
	//Allows the player to populate a square with their chosen move.
		public int takeTurn(int indexx, int indexy, char move)
		{
			//Check if the square is empty.
			if(board[indexx][indexy] == '\u0000')
			{
				board[indexx][indexy] = move;
				
				if(winner(indexx, indexy, move))
				{
					isOver = true;
					return 2;
				}
				else if (over())
				{
					isOver = true;
					return 3;
				}
				playerOne = !playerOne;
				
				return 1;
			}
			else // If not empty, try again.
			{
				return 0;
			}
		}
		
		public boolean winner(int indexx, int indexy, char move)
		{
			int startx = indexx - 1;
			int endx = indexx + 1;
			int starty = indexy - 1;
			int endy = indexy + 1;
			
			if(move == 'O')
			{
				//Check row
				if( (indexy - 1 >= 0) && (indexy + 1 < board.length))
				{
					//Row checking
					if(board[indexx][indexy - 1] == 'S' && board[indexx][indexy + 1] == 'S')
					{
						isOver = true;
						return true;
					}
					
					//Check diagonals.
					if( (indexx - 1 >= 0) && (indexx + 1 < board.length))
					{
						if(board[indexx - 1][indexy - 1] == 'S' && board[indexx + 1][indexy + 1] == 'S')
						{
							isOver = true;
							
							return true;
						}
					}
				}
				else if( (indexx - 1 >= 0) && (indexx + 1 < board.length))
				{
					if(board[indexx - 1][indexy] == 'S' && board[indexx+1][indexy] == 'S')
					{
						isOver = true;
						return true;
					}
				}
				
			}
			else
			{
				startx = indexx - 2;
				endx = indexx + 2;
				starty = indexy - 2;
				endy = indexy + 2;
				
				if(startx >= 0 && starty >= 0)
				{
					if( (board[indexx - 1][indexy - 1] == 'O'))
					{
						
						if(board[indexx - 2][indexy - 2] == 'S')
						{
							isOver = true;
							return true;
						}
					}
				}
				
				if(startx >= 0)
				{
					
					if(board[indexx - 1][indexy] == 'O')
					{
						if(board[indexx - 2][indexy] == 'S')
						{
							isOver = true;
							return true;
						}
					}
				}
				
				if(starty >= 0)
				{
					
					if(board[indexx][indexy - 1] == 'O')
					{
						if(board[indexx][indexy - 2] == 'S')
						{
							isOver = true;
							return true;
						}
					}
				}
				
				if(endx < board.length && endy < board.length)
				{
					if(board[indexx + 1][indexy + 1] == 'O')
					{
						if(board[indexx + 2][indexy + 2] == 'S')
						{
							isOver = true;
							return true;
						}
					}
				}
				
				if(endx < board.length)
				{
					if(board[indexx + 1][indexy] == 'O')
					{
						if(board[indexx + 2][indexy] == 'S')
						{
							isOver = true;
							return true;
						}
					}	
					
					if(starty >= 0)
					{
						if(board[indexx + 1][indexy - 1] == 'O')
						{
							if(board[indexx + 2][indexy - 2] == 'S')
							{
								isOver = true;
								return true;
							}
						}
					}
				}
				
				if(endy < board.length)
				{
					if(board[indexx][indexy + 1] == 'O')
					{
						if(board[indexx][indexy + 2] == 'S')
						{
							isOver = true;
							return true;
						}
					}	
					
					if(startx >= 0)
					{
						if(board[indexx - 1][indexy + 1] == 'O')
						{
							if(board[indexx - 2][indexy + 2] == 'S')
							{
								isOver = true;
								return true;
							}
						}
					}
				}
		}
			return false;
		}
}