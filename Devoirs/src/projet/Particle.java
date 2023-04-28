package projet;

public class Particle {
    private double[] position;
    private double[] velocity;
    private double bestFitness;
    private double[] bestPosition;

    public Particle(double[] position, double[] velocity) {
        this.position = position;
        this.velocity = velocity;
        this.bestPosition = position;
        this.bestFitness = Double.POSITIVE_INFINITY;
    }
    
    public static int Fitness(double[] position) {//counts each queen's conflicts
    	
		 int i=0;
		 int h=0;
	
	    	while((i<position.length) && (position[i]!= -1 ) ) {
	    	
	    	int j=0;	
	    		
	        while ((j<position.length) && (position[j]!=-1)) {
	        	
	        	if(j!=i) {
	        		
	            if ((int)position[i] == (int)position[j] || (int)position[i] - i == (int)position[j] - j || (int)position[i] + i == (int)position[j] + j) {
	        
	                h++;      
	            }}
	        	
	            j++;
	        }
	        
	        i++;
	    }
	    return h;
	   }
    
    
    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public double getBestFitness() {
        return bestFitness;
    }

    public void setBestFitness(double bestFitness) {
        this.bestFitness = bestFitness;
    }

    public double[] getBestPosition() {
        return bestPosition;
    }

    public void setBestPosition(double[] bestPosition) {
        this.bestPosition = bestPosition;
    }
}
