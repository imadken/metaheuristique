package projet;


import java.util.Arrays;
import java.util.Random;

public class PSO {
	
    int N; // Number of queens
    int swarmSize; // Swarm size
    int maxIterations; // Maximum number of iterations
    double c1; // Cognitive parameter
    double c2; // Social parameter
    double w; 
    double[] globalBestPosition = null;
    double globalBestFitness = Double.POSITIVE_INFINITY;

    
    Particle[] swarm; //particle is a class that contains the structure of an element //check particle.java   
  
     Random random;										 

  
     // Constructor
    public PSO(int N, int swarmSize, int maxIterations, double w,double c1, double c2) {
        this.N = N;
        this.swarmSize = swarmSize;
        this.maxIterations = maxIterations;
        this.w = w;
        this.c1 = c1;
        this.c2 = c2;
        this.random = new Random();
        this.swarm = initializeSwarm();
        
        
    }

    private Particle[] initializeSwarm() {
    	
        Particle[] swarm = new Particle[swarmSize];

        for (int i = 0; i < swarmSize; i++) {
            double[] position = new double[N];
            double[] velocity = new double[N];

            for (int j = 0; j < N; j++) {
                position[j] = Math.round(random.nextDouble() * N) ;
                //velocity[j] = (random.nextDouble() - 0.5) * 2.0 * N;
            }
            
            swarm[i] = new Particle(position, velocity);
        }

        return swarm;
    }

    
    //update position using current position and velocity
    /*private double[] updatePosition(Particle particle) {
        double[] position = particle.getPosition();
        double[] velocity = particle.getVelocity();
        double[] newPosition = new double[N];

        for (int i = 0; i < N; i++) {
           
        	newPosition[i] = Math.round(position[i] + velocity[i]);

            if (newPosition[i] < 0) {
                newPosition[i] = 0;
            } else if (newPosition[i] >= N) {
                newPosition[i] = N - 1;
            }
        }

        return newPosition;
    }*/
    
  //update position using current position and using crossover
    private double[] updatePosition_cross(Particle particle) {
    	
        double[] position = particle.getPosition();
      //  double[] velocity = particle.getVelocity();
        double[] newPosition = new double[N];
        
        double rand = random.nextDouble();
        
        int crossoverPoint = random.nextInt(N);
        
        if((rand <= 1/3) && globalBestPosition != null) {
        	
        	//System.arraycopy(position, 0, newPosition, 0, crossoverPoint);
            //System.arraycopy(globalBestPosition, crossoverPoint, newPosition, crossoverPoint, N - crossoverPoint);
            
        	System.arraycopy(globalBestPosition, 0, newPosition, 0, crossoverPoint);
            System.arraycopy(position, crossoverPoint, newPosition, crossoverPoint, N - crossoverPoint);
 
 
        }
        
        else { 
          	
        	if((rand > 1/3) && (rand <=2/3)) {
        	
        	/*System.arraycopy(position, 0, newPosition, 0, crossoverPoint);
            System.arraycopy(particle.getBestPosition(), crossoverPoint, newPosition, crossoverPoint, N - crossoverPoint);*/
        	
        	System.arraycopy(particle.getBestPosition(), 0, newPosition, 0, crossoverPoint);
            System.arraycopy(position, crossoverPoint, newPosition, crossoverPoint, N - crossoverPoint);}
        	
          	else {
          		
          	double[] rand_position = new double[N];
            //double[] rand_velocity = new double[N];

            for (int j = 0; j < N; j++) {
                rand_position[j] = Math.round(random.nextDouble() * N) ;
               // rand_velocity[j] = (random.nextDouble() - 0.5) * 2.0 * N;
            }
            
            //Particle rand_particle = new Particle(position, velocity);
          		
          		System.arraycopy(rand_position, 0, newPosition, 0, crossoverPoint);
                System.arraycopy(position, crossoverPoint, newPosition, crossoverPoint, N - crossoverPoint);
          	}
        
        }
         
        for (int i = 0; i < N; i++) {
           
        	//newPosition[i] = Math.round(position[i] + velocity[i]);

            if (newPosition[i] < 0) {
                newPosition[i] = 0;
            } else if (newPosition[i] >= N) {
                newPosition[i] = N - 1;
            }
        }

        return newPosition;
    }

    
    //updates the velocity of each particle based on its current position and the global best position.
    
   /* private void updateVelocity(double[] globalBestPosition) {
       
    	for (Particle particle : swarm) {
           
    		double[] position = particle.getPosition();
            double[] velocity = particle.getVelocity();
            double[] newVelocity = new double[N];
            
        
            double r1 = Math.random();//random.nextDouble(2);
            double r2 = Math.random();//random.nextDouble(2);
            
            // Define maxVelocity
            double maxVelocity = (N) ;
            
            
            double personalBestDifference = (double)(Particle.Fitness(particle.getBestPosition())   - Particle.Fitness(position));
            
            double globalBestDifference = (double)(Particle.Fitness(globalBestPosition) - Particle.Fitness(position));

            for (int i = 0; i < N; i++) {
                double cognitiveComponent = c1 * r1 * personalBestDifference * (particle.getBestPosition()[i] - position[i]);
                double socialComponent = c2 * r2 * globalBestDifference * (globalBestPosition[i] - position[i]);
                
                newVelocity[i] = w * velocity[i] + cognitiveComponent + socialComponent;

                if (newVelocity[i] > maxVelocity) {
                    newVelocity[i] = maxVelocity;
                    
                } else if (newVelocity[i] < -maxVelocity) {
                    newVelocity[i] = -maxVelocity;
                }
            }

            particle.setVelocity(newVelocity);
        }
    }*/
    
    public int[] solve() {
      //  double[] globalBestPosition = null;
       // double globalBestFitness = Double.POSITIVE_INFINITY;

        for (int i = 0; i < maxIterations; i++) {
        	
            for (Particle particle : swarm) {
            	
                double[] position = updatePosition_cross(particle);
                particle.setPosition(position);

                double fitness = (double)Particle.Fitness(position);

                if (fitness < particle.getBestFitness()) {
                    particle.setBestFitness(fitness);
                    particle.setBestPosition(position);
                }

                if (fitness < globalBestFitness) {
                    globalBestFitness = fitness;
                    globalBestPosition = position.clone();
                }
            }

           // updateVelocity(globalBestPosition);
        }

        if (globalBestFitness == 0) {
           return convertPositionToSolution(globalBestPosition);
        	
        } else {
            return null;
        }
    }

    //convert double to int
    private int[] convertPositionToSolution(double[] position) {
        int[] solution = new int[N];

        for (int i = 0; i < N; i++) {
            solution[i] = (int) Math.round(position[i]);
        }

        return solution;
    }
    
    
    
    
    public static void main(String[] args) {
    	
        int n = 12; // Set the size of the chessboard
        int numParticles = 200; // Set the number of particles in the swarm
        int maxIterations = 10000; // Set the maximum number of iterations
        
        int x=10;
        //int[] solution = pso.solve();
        
      for(int i=0;i<x;i++) {
    	  
        	PSO pso = new PSO(n, numParticles, maxIterations,0.9,2.0 ,2.0);
        	int[] solution = pso.solve();
        	
        if (solution == null) {
            System.out.println("test "+(i+1)+"  No solution found");
        } else {
            System.out.println("test "+(i+1)+"  Solution found: "+Arrays.toString(solution));
          
        }
      
   }
        }
    
}  
/*
initialize the particles' positions and velocities
initialize the global best position and fitness value

while termination criteria are not met:
    for each particle:
        update the particle's velocity using the following formula:
            velocity[i] = w * velocity[i] + c1 * r1 * (pbest[i] - position[i]) + c2 * r2 * (gbest - position[i])
        update the particle's position using the following formula:
            position[i] = position[i] + velocity[i]
        evaluate the particle's fitness
        update the particle's personal best position and fitness if necessary
        update the global best position and fitness if necessary
    update the value of w*/