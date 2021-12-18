
public class Helper {
	
	
	public String generate(String[] head,String[] apple,int radius) {
		
		double x_Coordinates = Math.pow(radius, 2) - Math.pow(Integer.parseInt(head[1]), 2);
		int x =  (int) Math.floor(Math.sqrt(x_Coordinates));
		
		
		if(Manhattan_distance((head[1]+","+x).split(","),apple) <= radius) {
			return x+","+head[1];
		}else {
			
			double y_Coordinates = Math.pow(radius, 2) - Math.pow(Integer.parseInt(head[0]), 2);
			
			int y =  (int) Math.floor(Math.sqrt(y_Coordinates));
			
			return head[0]+","+y;
			
		}

	}
	
	
	
	public boolean aroundApple(String[] target,DrawPlayGround getObs) {
    	int targetX = Integer.parseInt(target[0]);
    	int targetY = Integer.parseInt(target[1]);
    	
    	Vertex end = new Vertex(targetX, targetY);
    	
    	end.getAdjacencies_withObsticles();;
    	
    	
    	if(end.obsticles.size() >= 3 ) {
    		return false;
    	}else {
    		return true;
    	}
    	
    	
    	
    }
	
	
	

	public double Manhattan_distance(String head[],String[] apple) {
    	
    	
    	int headX = Integer.parseInt(head[0]);
    	int headY = Integer.parseInt(head[1]);
    	
    	int appleX = Integer.parseInt(apple[0]);
    	int appleY = Integer.parseInt(apple[1]);
    	
    	
    	
    	
    	return Math.abs(headX-appleX)+Math.abs(headY-appleY);
    }
	
}
