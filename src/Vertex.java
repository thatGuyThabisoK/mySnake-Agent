import java.util.ArrayList;
public class Vertex implements Comparable<Object> {


	int headX,headY;
	Vertex parent;
	
	double gCost,hCost;
	
	DrawPlayGround getObs = MyAgent.drawMyground;
	
	
	ArrayList<Integer> obsticles = new ArrayList<>();
	ArrayList<Vertex> neighbours = new ArrayList<>();
	
	
	int[][] ground = getObs.playGround;
	
	public Vertex(int startX,int startY) {
		
		headX = startX;
		headY = startY;
		
	}
	
	public double fCost() {
		return gCost+hCost;
	}
	
	public String getVertexString() {
		return String.valueOf(headX)+","+String.valueOf(headY);
	}
	
	public void setParent(Vertex getParent) {
		this.parent = getParent;
	}
	
	public void getAdjacencies_withObsticles() {
    	// c 
    	int row = ground.length-1;
    	int column = ground[0].length-1;
		
		if(headX-1 >= 0  && !getObs.walkable(headX-1, headY)) {
			obsticles.add(1);
			
		} 
		if(headX+1 <= row  && !getObs.walkable(headX+1, headY)) {
			obsticles.add(1);
			
		} 
		if(headY-1 >= 0  && !getObs.walkable(headX, headY-1)) {
			obsticles.add(1);
			
		} 
		if(headY+1 <= column  && !getObs.walkable(headX, headY+1)){
			obsticles.add(1);	
		}
		
	}
	
	
	
	
	
	
    public ArrayList<Vertex> getAdjacencies() {
    	// c 
    	int row = ground.length-1;
    	int column = ground[0].length-1;
		
    	if(headX-1 >= 0 && getObs.walkable(headX-1, headY)) {
			neighbours.add(new Vertex(headX-1,headY));
    	} 
    	if(headX+1 <= row && getObs.walkable(headX+1, headY)) {
			neighbours.add(new Vertex(headX+1,headY));
    	} 
		if(headY-1 >= 0 && getObs.walkable(headX, headY-1)) {
				neighbours.add(new Vertex(headX,headY-1));
			
		} 
		if(headY+1 <= column && getObs.walkable(headX, headY+1)){
				neighbours.add(new Vertex(headX,headY+1));
		}
		
		
		return neighbours;
	}

	@Override
	public int compareTo(Object toCompare) {
		
		int compareFCost = (int) ((Vertex) toCompare).fCost();
		
		// TODO Auto-generated method stub
		return  ((int) this.fCost())-compareFCost;
	}
	

}
