
public class Movement {
	
	 public int makeMove(String[] myPath,String[] head) {
	
	    	int move = 1;
	    	
	    	if(myPath.length != 0) {
	    		
	    		int headX = Integer.parseInt(head[0]);
		    	int headY = Integer.parseInt(head[1]);
		    	
		    	int nextX = Integer.parseInt(myPath[0]);
		    	int nextY = Integer.parseInt(myPath[1]);
		    	
		    	//0 for UP;1 for DOWN; 2 for WEST; 3 for East; 4 for LEFT; 5 for STRAIGHT; 6 for RIGHT
		    	
		    	
		    	
		    	if(headX == nextX || headY == nextY) {
		    		
		    		if(headX == nextX) {
		    			if(headY+1 == nextY) {
		    				move = 1;
		    			}else {
		    				move = 0;
		    			}
		    		}else {
		    			
		    			if(headX-1 == nextX) {
		    				move = 2;
		    			}else {
		    				move = 3;
		    			}
		    			
		    		}	
		    			
		    	}
		    	
		    	return move;
	    	}else {
	    		return 0;
	    		
	    	} 	
	 
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 


}
