package yarvaOthello;

public class Main {
	
	  private static final char EMPTY = ' ';

	  private static final char BLACK = 'b';
	
	  private static final char WHITE = 'w';
	 
	  private static final int BOARD_SIZE = 8;
	  
	 
	  private char[][] grid = new char[BOARD_SIZE][BOARD_SIZE]
	    ;
	 
	  private GUI gameBoard = new GUI();
	 
	  
	  public Main()
	  {
	  
	    for (int i=0; i<grid.length; i++)
	    {
	      for (int j=0; j<grid[i].length; j++)
	      {
	        grid[i][j]=EMPTY;
	      }
	    }
	    grid[3][3]=WHITE;
	    grid[3][4]=BLACK;
	    grid[4][3]=BLACK;
	    grid[4][4]=WHITE;
	    
	   
	    updateView();
	  }
	  
	private void updateView()
	  {
	    for (int i=0; i<grid.length; i++)
	    {
	      for (int j=0; j<grid[i].length; j++)
	      {
	        if (grid[i][j]==WHITE)
	        {
	          gameBoard.putPeg("white",i,j);
	         
	        }else if(grid[i][j]==BLACK)
	        {
	          gameBoard.putPeg("black",i,j);
	         
	        }
	      }
	    }

	  }
	
	private char change(char move) {
		if (move == BLACK)
	        move = WHITE;
	    
		else move = BLACK;
		return move;
	}
	
	public void play()
	  {
		int burn=0;
	    char move = WHITE;
	    while (!gameOver())
	    {
	      int[] Coordinates=new int[2];
	      int row = 0;
	      int col = 0;
	      boolean valid = false;
	      boolean noskip = false;
	    
	      for(int i=0; i<8; i++) {
	    	  if(noskip) break;
	    	  for(int j=0; j<8; j++) {
	    		  
	    		  if(validMove(move,i,j,true)) {
	    			  noskip = true;
	    			  break;
	    		  }
	    	  }
	    	  
	      }
	      if(!noskip) {
	    	  move=change(move);
	    	  gameBoard.changeLabel();
	    	  burn++;
	    	  if(burn==2) break;
	      continue;}
	      while (!valid)
	      {
	        Coordinates = gameBoard.getClick(move);
	        if(Coordinates[0]==9 && Coordinates[1]==9)
	        {
	        	AI(move);
	        	
	        	break;
	        }
	        row = Coordinates[0];
	        col = Coordinates[1];
	        valid = validMove(move, row, col, false);
	      }
	      updateView();
	      if(!(Coordinates[0]==9 && Coordinates[1]==9))
	      takeTurn(move, row, col);
	      Coordinates[0]=0;
      	  Coordinates[1]=0;
	      gameBoard.changeLabel();
	      move=change(move);
	      burn=0;
	    }
	    endGame();
	  }
	
	private boolean validMove(char turn, int row, int col, boolean AI)
	  {
	    
	    boolean result=false;
	    char oppCol=BLACK;
	    if (turn==BLACK)
	    {
	      oppCol=WHITE;
	    }

	  
	  if (grid[row][col]==EMPTY)
	  {
	    if (row+1<8 && col+1<8 && grid[row+1][col+1]==oppCol && callDir(turn, row, col, AI)>0)
	    { 
	      result=true;
	    }else if(row+1<8 && grid[row+1][col]==oppCol && callDir(turn, row, col, AI)>0)
	    {
	    	result=true;
	    }else if(col+1<8 && grid[row][col+1]==oppCol && callDir(turn, row, col, AI)>0)
	    {
	    	result=true;
	    }else if (col-1>-1 && grid[row][col-1]==oppCol && callDir(turn, row, col, AI)>0)
	    {
	    	result=true;
	    }else if (row-1>-1 && col-1>-1 && grid[row-1][col-1]==oppCol && callDir(turn, row, col, AI)>0)
	    {
	    	result=true;
	    }else if (row-1>-1 && grid[row-1][col]==oppCol && callDir(turn, row, col, AI)>0)
	    { 
	    	result=true;
	    }else if(row-1>-1 && col+1<8 && grid[row-1][col+1]==oppCol && callDir(turn, row, col, AI)>0)
	    {
	    	result=true;
	    }else if (row+1<8 && col-1>-1 && grid[row+1][col-1]==oppCol && callDir(turn, row, col, AI)>0)
	    {
	    	result = true;
	    }
	  }
	  return result;
	}
	
	
	
	private void endGame()
	  {
	    int countw=0;
	    int countb=0;     
	    for (int i=0; i<grid.length; i++)     
	    { 
	      for (int j=0; j<grid[i].length; j++)       
	      {       
	        if (grid[i][j]==BLACK)       
	        {
	          countb++;       
	        }else if (grid[i][j]==WHITE)       
	        {
	          countw++;       
	        }     
	      }     
	    } if (countb>countw)     
	    {     
	      gameBoard.displayMessage("Black Wins: " + countw + ":" + countb );     
	    }else if (countw>countb)     
	    {       
	      gameBoard.displayMessage("White Wins: " + countw + ":" + countb);     
	    }else     
	    {       
	      gameBoard.displayMessage("Game over. It is a tie game.");     
	    }

	  }
	
	private boolean gameOver()
	  {
	    boolean full=false;
	    int countTot=0;
	    
	    for (int i=0; i<8; i++)
	    {
	      for (int j=0; j<8; j++)
	      {
	        if (grid[i][j]==BLACK || grid[i][j]==WHITE)
	        {
	          countTot++;
	        }
	      }
	    }
	        if (countTot==64)
	        {
	          full=true;
	        }
	    return full;
	  }
	
	

	private int callDir(char turn, int row, int col, boolean AI) {
		int count=direction(row, col, turn, 0, -1, AI)+direction(row, col, turn, 0, 1, AI)+
				   direction(row, col, turn, 1, 0, AI)+direction(row, col, turn, -1, 0, AI)+
				   direction(row, col, turn, 1, 1, AI)+direction(row, col, turn, 1, -1, AI)+
				   direction(row, col, turn, -1, 1, AI)+direction(row, col, turn, -1, -1, AI);
		return count;
	}

	private void takeTurn(char turn, int row, int col) {
	    grid[row][col]=turn;
	   
	    updateView();
	  
	  }
	
	 private int direction(int row, int column, char colour, int colDir, int rowDir, boolean AI)
	  {
		int count=0;
	    int currentRow= row + rowDir;
	    int currentCol = column + colDir;
	    if (currentRow==8 || currentRow<0 || currentCol==8 || currentCol<0)
	    {
	      return count;
	    }
	    while (!(grid[currentRow][currentCol]==EMPTY))
	    {
	      if (grid[currentRow][currentCol]==colour)
	      {
	        while(!(row==currentRow && column==currentCol))
	        {
	          if(grid[currentRow][currentCol]!=colour && AI==false) {
	        	grid[currentRow][currentCol]=colour;
	        	count++;}
	          else if(grid[currentRow][currentCol]!=colour)count++;
	          currentRow=currentRow-rowDir;
	          currentCol=currentCol-colDir;
	        }
	        break;
	      }else
	      {
	    	  
	      currentRow=currentRow + rowDir;
	      currentCol=currentCol + colDir;
	      }
	      if (currentRow<0 || currentCol<0 || currentRow==8 || currentCol==8)
	      { 
	        break;
	      }
	    }
		return count;
	  }
	
	private void AI(char move) {
		int maxCount=0;
		int[] Coords= new int[2];
		Coords[0]=1;
		Coords[1]=1;		
		while(!validMove(move,Coords[0],Coords[1],true)) {
		for(int i=0; i<8; i++) {
	    	  
	    	  for(int j=0; j<8; j++) {
	    		  
	    		 if(callDir(move,i,j,true)>maxCount) {
	    			 if(Coords[0]!=i && Coords[1]!=j) {
	    		  Coords[0]=i;
	    		  Coords[1]=j;
	    		  
	    		  }
	    		  }
	    		 else continue;
	    		 
	    		  }
	    	 
	    	  }
		}
		validMove(move,Coords[0],Coords[1],false);
		takeTurn(move,Coords[0],Coords[1]);
	    	  
	      }
	
	 
public static void main(String[] args) {
	new Main().play();
	
}
}
