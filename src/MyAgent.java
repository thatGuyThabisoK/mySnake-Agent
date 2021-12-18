import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MyAgent extends za.ac.wits.snake.MyAgent {

	
	static DrawPlayGround drawMyground;
	
	Helper helperClass = new Helper();
	
    public static void main(String args[]) throws IOException {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }

    @Override
    public void run() {
    	double health = 5.0;
    	Movement move = new Movement();
    	String prev_apple = null;
    	
    	String[] corners  = {"0,0","49,0","49,49","0,49"};
    	
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);
            drawMyground = new DrawPlayGround(Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
            
            
            while (true) {
                String line = br.readLine();
                if (line.contains("Game Over"))break;
               
                

                String apple1 = line;
                //do stuff with apples
                String[] appleCoordinates = apple1.split(" ");
                
                
                
                // read in obstacles and do something with them!
                int nObstacles = 3;
                for (int obstacle = 0; obstacle < nObstacles; obstacle++) {
                    String obs = br.readLine();
                    /// do something with obs
                    
                    drawMyground.drawSnake(obs, 1);
                    
                }
                
                
            
                String[] mySnake = null;
                PathFinder path = new PathFinder();
                
                int mySnakeNum = Integer.parseInt(br.readLine());
                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();
                    
                  //draw other agent snakes on our play ground
                    String[] snakes = snakeLine.split(" ");
                    
                    if(snakes[0].equalsIgnoreCase("alive")) {
                    	
                    	
                    	if (i == mySnakeNum) {
                    		
                    		drawMyground.drawMySnake(snakeLine);
                        	mySnake = snakeLine.split(" ");
                        	
                        	
                            //hey! That's me :)
                        }else {
                        	drawMyground.drawPlayers(snakeLine);
                        }
                    	
                    	
                    }
                    

                    
                    
                    
                    
                    //do stuff with other snakes
                }
                 
               
                //finished reading, calculate move:
                
                if(!mySnake.equals(null)) {
                	
                	String[] head = mySnake[3].split(",");
                	String[] tail = mySnake[mySnake.length-1].split(",");
                	
                	health = getCurrentHealth(prev_apple,appleCoordinates,health);
                	
                	if(helperClass.Manhattan_distance(head, appleCoordinates)  < 40 && helperClass.aroundApple(appleCoordinates, drawMyground) && health > 0 && drawMyground.walkable(Integer.parseInt(appleCoordinates[1]), Integer.parseInt(appleCoordinates[1]))) {
                		
                		ArrayList<String> myPath = path.getPath(head, appleCoordinates);
                		
                		if(myPath.size() >= 1) {
                			System.out.println(move.makeMove(myPath.get(myPath.size()-1).split(","), head));
                		}else {
                			
                			
                			myPath = path.getPath(head, getAdjacencies(Integer.parseInt(tail[0]),Integer.parseInt(tail[1]),drawMyground).split(","));
                			
                			if(myPath.size() >= 1) {
                				System.out.println(move.makeMove(myPath.get(myPath.size()-1).split(","), head));
                			}else {
                				
                				Vertex nextEdge = path.getNextNode_withLessAdjacentObsticleNodes(head);
                				System.out.println(move.makeMove((nextEdge.headX+","+nextEdge.headY).split(","), head));
                				
                				
                			}
                			
                			
                			
                			
                		}	
                		
                	}else {
                		ArrayList<String> myPath = path.getPath(head, getAdjacencies(Integer.parseInt(tail[0]),Integer.parseInt(tail[1]),drawMyground).split(","));
            			
            			if(myPath.size() >= 1) {
            				System.out.println(move.makeMove(myPath.get(myPath.size()-1).split(","), head));
            			}else {
            				
            				Vertex nextEdge = path.getNextNode_withLessAdjacentObsticleNodes(head);
            				System.out.println(move.makeMove((nextEdge.headX+","+nextEdge.headY).split(","), head));
            				
            				
            			}
                	}
                	
                	
                	
                	
                	
                	
                	
                	
                }
                prev_apple = appleCoordinates[0]+","+appleCoordinates[1];
                
                //drawMyground.drawGround();
                
                
                
                //now that we calculated a move we reset our play ground so that we can draw the next state of the game
                drawMyground.reset();
                
                
            }
        } catch (IOException e) {
        	 e.printStackTrace();
        } 
        
        
    }
    
    
    private double getCurrentHealth(String prev_apple,String[] appleCoordinates,double health) {
    	
    	if(!(prev_apple == null)) {
        	
        	if(prev_apple.equals(appleCoordinates[0]+","+appleCoordinates[1])) {
        		
        		health -= 0.1;

        	}else {
        		health = 5;
        	}
        	
        }
  
    	return  health;
    	
    }
    
    //this to get a node adjacent to the tail
    
    public String getAdjacencies(int headX,int headY,DrawPlayGround getObs) {
    	 
    	
		
    	if(headX-1 >= 0 && getObs.walkable(headX-1, headY)) {
			
			return String.valueOf(headX-1)+","+String.valueOf(headY);
			
    	}else if(headX+1 <= getObs.row-1 && getObs.walkable(headX+1, headY)) {
			
			
			return String.valueOf(headX+1)+","+String.valueOf(headY);
			
    	}else if(headY-1 >= 0 && getObs.walkable(headX, headY-1)) {
				
				return String.valueOf(headX)+","+String.valueOf(headY-1);
			
		}else{
			return String.valueOf(headX)+","+String.valueOf(headY+1);
		}
	}    

    
    
    //0 for UP;1 for DOWN; 2 for WEST; 3 for East; 4 for LEFT; 5 for STRAIGHT; 6 for RIGHT
    
    
}