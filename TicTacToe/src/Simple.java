
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
					return 2;
				}
				else if (over())
				{
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
				
				for(int x = startx; x < endx; x++)
				{
					for(int y = starty; y < endy; y++)
					{
						if(board[x][y] == 'S')
						{
							if(board[(indexx + (indexx - x))][indexy + (indexy - y)] == 'S')
							{
								return true;
							}
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
				
				if(startx >= 0 && starty >= 0)
				{
					if( (board[indexx - 1][indexy - 1] == 'O'))
					{
						
						if(board[indexx - 2][indexy - 2] == 'S')
						{
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
							return true;
						}
					}	
					
					if(starty >= 0)
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
				
				if(endy < board.length)
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