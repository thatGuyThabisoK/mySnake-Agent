import java.util.ArrayList;
import java.util.Collections;



public class PathFinder {
	
	ArrayList<String> path = new ArrayList<>();
	String[] next = new String[2]; 
	DrawPlayGround clear = MyAgent.drawMyground;
	
	
	public ArrayList<String> dfs(String[] head,String[] target) {
		
		///////////Implementation////////
		////////////////////////////////
		//Always start with the adjacent node in the front of the head,then go clockwise then proceed from the the last 
		//adjacent node added,repeat until you get to the end Node
		
		Vertex beginNode = new Vertex(Integer.parseInt(head[0]),Integer.parseInt(head[1]));
		Vertex endNode = new  Vertex(Integer.parseInt(target[0]),Integer.parseInt(target[1]));
		boolean condition = false;
		long start = System.currentTimeMillis();
		while(true) {
			
			
			if(condition) {
				break;
			}
			Vertex next = beginNode;
			ArrayList<Vertex> adjacencies = next.getAdjacencies();
			
			for(int index = 0;index < adjacencies.size();++index) {
				
				Vertex current = adjacencies.get(index);
				
				if(clear.walkable(current.headX, current.headY)) {
					current.parent = next;
					next = current;
				}else {
					continue;
				}
				
				if(current.headX == endNode.headY && current.headY == endNode.headY) {
					
					while(current != beginNode) {
			    		path.add(current.headX+","+current.headY);
			    		
			    		current = current.parent;
			    	}
			    	
			    	condition = true;
					break;
					
				}
				
				if(System.currentTimeMillis()-start > 40) {
					
					while(current != beginNode) {
			    		path.add(current.headX+","+current.headY);
			    		
			    		current = current.parent;
			    	}
			    	
			    	condition = true;
					break;
					
				}
				
				
				
			}
			
			
			
		}
		
		return path;
	}
	
	public ArrayList<String> getPath(String[] startNode,String[] endNode) {
		
		
		/*
		 * An over view of the A* path finding algorithm works
		 * 
		 * first you will need to have the starting node and the target node,to search for,
		 * then you will need to get all the vertices adjacent to the current node and the calculate their gCost and hCost.
		 * where the gCost is the distance between the current node and the current adjacent vertex
		 * and the hCost is the distance between the current adjacent node and the target node
		 * then you compare the fCosts of the all the adjacent vertices and move to the vertex with the lowest fCost.
		 * ********the fCost is just the sum of the vertex's gCost and hCost.
		 * *****************Implementation***************
		 * you'll need to have two container,open and closed,where the open is for the vertices with the lowest fCost
		 * and closed is for the vertices that we have already visited(also had the lowest fCost) and cannot visit again.
		 * we start by adding the starting node to the open list,note that the first vertex in the open list must have the smallest fCost,
		 * 
		 * 
		 * 
		 */
		ArrayList<String> openList = new ArrayList<>();
		
		Vertex beginNode = new Vertex(Integer.parseInt(startNode[0]),Integer.parseInt(startNode[1]));
		Vertex targetNode = new Vertex(Integer.parseInt(endNode[0]),Integer.parseInt(endNode[1]));
		ArrayList<Vertex> open = new ArrayList<>();
		ArrayList<String> closed = new ArrayList<>();
		open.add(beginNode);
		openList.add(beginNode.headX+","+beginNode.headY);
		
		
		
		boolean condition = false;
		
		long start = System.currentTimeMillis();
		
		
		while(!open.isEmpty()) {
			Vertex current = open.get(0);
			open.remove(0);
			openList.remove(current.headX+","+current.headY);
			
			closed.add(current.headX+","+current.headY);
			
			
			
			if(condition) {
				break;
			}
			
			
			ArrayList<Vertex> neighbour = current.getAdjacencies();
			
			for(int index = 0;index < neighbour.size();++index) {
				Vertex adjacent  = neighbour.get(index);
				double costTocurrentNode = getDistance(current.headX,current.headY,adjacent.headX,adjacent.headY) + current.gCost; 
				
				if(!clear.walkable(adjacent.headX, adjacent.headY) || closed.contains(adjacent.headX+","+adjacent.headY)) {
					continue;
				}
				if(costTocurrentNode < current.fCost() || !openList.contains(adjacent.headX+","+adjacent.headY)) {
					adjacent.gCost = costTocurrentNode;
					adjacent.hCost = getDistance(adjacent.headX,adjacent.headY,targetNode.headX,targetNode.headY);
					
					adjacent.setParent(current);

					
					if(!openList.contains(adjacent.headX+","+adjacent.headY)) {
				
						open.add(adjacent);
						openList.add(adjacent.headX+","+adjacent.headY);
						Collections.sort(open);;
					}
					
					if(adjacent.headX == targetNode.headX && adjacent.headY == targetNode.headY) {
					
						
				    	while(adjacent != beginNode) {
				    		path.add(adjacent.headX+","+adjacent.headY);
				    		
				    		adjacent = adjacent.parent;
				    	}
				    	
				    	condition = true;
						break;
					}
					
					if(System.currentTimeMillis() - start > 35) {
						condition = true;
						break;
					}
				}
				
			}
			
			
		}
		
		return path;
		
	}

	
	public double getDistance(int startX,int startY,int endX,int endY) {
		
		return Math.abs(startX-endX)+Math.abs(startY-endY);
		
	}
	
	//this piece of code is executed when the apple is rotten and/or the apple is far 
	
	
	public Vertex getNextNode_withLessAdjacentObsticleNodes(String[] Head) {
	
		Vertex myHead = new Vertex(Integer.parseInt(Head[0]),Integer.parseInt(Head[1]));
		
		
		ArrayList<Vertex> adjacent = myHead.getAdjacencies();
		
		while(adjacent.size() >= 1 ) {
			
			Vertex first = adjacent.get(0);
			//check if the current node adjacent to our head is walkable  
			
			first = getFreeNode(first,adjacent);
			
			
			myHead = first;
			break;
			
		}
		
		
		return myHead;
		
	}
	
	private Vertex getFreeNode(Vertex first,ArrayList<Vertex> adjacent) {
		
		first.getAdjacencies_withObsticles();
		
		for(int i = 1;i < adjacent.size();++i) {
			Vertex current = adjacent.get(i);
			current.getAdjacencies_withObsticles();
				
			//this node is walkable
				
			if(first.obsticles.size() > current.obsticles.size()) {
					
					first = current;
			}
			
			
		}	
		
		return first;
	}
	

}
