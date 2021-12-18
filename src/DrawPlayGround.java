

public class DrawPlayGround {

	public int row,column;
	int[][] playGround;
	
	
	public DrawPlayGround(int row,int column) {
		
		this.row = row;
		this.column = column;
		playGround = new int[row][column];
		
	}
	
	

	public boolean walkable(int x,int y) {
		return playGround[y][x] == 0;
	}
	
	
	
	public void drawMySnake(String description) {
		
		/*
		 * we get input of string in the format alive/dead 20 1 12,0 10,0 10,6 10,8
		 * but we want the string to begin at the first (x,y) coordinates
		 */
		
		
		String snake = description.substring(description.indexOf(" ")+1);
	    for(int i = 0;i < 2;++i) {
	    	snake = snake.substring(snake.indexOf(" ")+1);
	    }
	    	
	    drawSnake(snake,3);
		
		
		
		
    	
	}
	
	
	public void drawGround() {
		for(int[] row : playGround) {
			for(int i = 0;i < row.length;++i){
				if(i != row.length-1) {
					System.err.print(row[i]+" ");
				}else {
					System.err.println(row[i]);
				}
			}
		}
	}
	
	
	
	public void drawPlayers(String description) {
		
		/*
		 * we get input of string in the format alive/dead 20 1 12,0 10,0 10,6 10,8
		 * but we want the string to begin at the first (x,y) coordinates
		 */
		
		
		String snake = description.substring(description.indexOf(" ")+1);
	    for(int i = 0;i < 2;++i) {
	    	snake = snake.substring(snake.indexOf(" ")+1);
	    }
	    
	    String[] head = snake.split(" ")[0].split(",");
	    
	    int headX = Integer.parseInt(head[0]);
	    int headY = Integer.parseInt(head[1]);
	    
	    if(headX-1 >= 0  && walkable(headX-1, headY)) {
			playGround[headY][headX-1]  = 2;
		} 
		if(headX+1 < row  && walkable(headX+1, headY)) {
			
			playGround[headY][headX+1] =  2;
			
		} 
		if(headY-1 >= 0 && walkable(headX, headY-1)) {
			playGround[headY-1][headX] =  2;
			
		} 
		if(headY+1 < column && walkable(headX, headY+1)){
			playGround[headY+1][headX] =  2;

		}
	    
	    
	    
	    
	    	
	    drawSnake(snake,1);
		
		
		
		
    	
	}
	
	public void drawSnake(String snakeDesc,int snakeId) {
		
		String[] snakeCoordinates = snakeDesc.split(" ");
    	int sizeOfCoordinates = snakeCoordinates.length;
    	for(int index = 0;index < sizeOfCoordinates;++ index) {
    		
    		String current = snakeCoordinates[index];
    		
    		if(index != sizeOfCoordinates-1) {
    			String next = snakeCoordinates[index+1];
    			drawLine(playGround,current,next,snakeId);
    		}
    
    	}
    	
    }
	
	
    private void drawLine(int[][] playGround, String currents, String next, int i) {
		
		String[] current = currents.split(",");
		String[] nextPoint = next.split(",");
		
		if(current[0].equals(nextPoint[0])) {
			//compare x values
			int x = Integer.parseInt(current[0]);
			int y0 = Integer.parseInt(current[1]);
			int y1 = Integer.parseInt(nextPoint[1]);
			while(y0 != y1) {
				playGround[y0][x] = i;
				if(y0 > y1) {
					y0 -=1;
				}else if(y0 < y1) {
					y0 += 1;
				}
				
			}
			
			playGround[y0][x] = i;
			
		}else if(current[1].equals(nextPoint[1])) {
			//compare y values
			int y = Integer.parseInt(current[1]);
			int x0 = Integer.parseInt(current[0]);
			int x1 = Integer.parseInt(nextPoint[0]);
			while(x0 != x1) {
				playGround[y][x0] = i;
				if(x0 > x1) {
					x0 -=1;
				}else if(x0 < x1) {
					x0 += 1;
				}
				
			}
			
			playGround[y][x0] = i;
			
		}
    }
	
    public void reset() {
		
    	playGround = new int[row][column];
		
	}
    

}
