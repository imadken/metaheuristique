package projet;

import java.util.Arrays;
import java.util.Random;

public class StandardPSO {
    private int population_size;
    private int N; // Number of queens
    private int[] globalBest; // Best solution found by any particle
    private int globalBestFitness; // Fitness value of global best solution
    private int[][] positions; // Position matrix for all particles
    private int[][] velocities; // Velocity matrix for all particles
    private int[] personalBestFitness; // Personal best fitness for each particle
    private int[][] personalBestPositions; // Personal best position for each particle
    private Random random; // Random number generator
    private double w; // Inertia weight
    private double c1; // Cognitive learning factor
    private double c2; // Social learning factor
    private double maxVelocity; // Maximum velocity magnitude
    private int maxIterations; // Maximum number of iterations
    
    public StandardPSO(int population_size,int N, double w, double c1, double c2, int maxIterations) {
        this.population_size =  population_size;
    	this.N = N;
        this.globalBest = new int[N];
        this.globalBestFitness = Integer.MAX_VALUE;
        this.positions = new int[population_size][N];
        this.velocities = new int[population_size][N];
        this.personalBestFitness = new int[population_size];
        Arrays.fill(personalBestFitness, Integer.MAX_VALUE);
        this.personalBestPositions = new int[population_size][N];
        this.random = new Random();
        this.w = w;
        this.c1 = c1;
        this.c2 = c2;
        this.maxVelocity = (N - 1) / 2.0;
        this.maxIterations = maxIterations;
    }
 
    
    public int[] solve() {
        // Initialize particle positions and velocities
        for (int i = 0; i < population_size; i++) {
            for (int j = 0; j < N; j++) {
                positions[i][j] = random.nextInt();
                velocities[i][j] = (int) ((random.nextDouble() - 0.5) * 2.0 * maxVelocity);
            }
            //shuffle(positions[i]);
            evaluateFitness(positions[i]);
            System.arraycopy(positions[i], 0, personalBestPositions[i], 0, N);
            personalBestFitness[i] = globalBestFitness;
        }

        // Main PSO loop
        for (int iteration = 1; iteration <= maxIterations; iteration++) {
            // Update global best solution
            for (int i = 0; i < N; i++) {
                if (personalBestFitness[i] < globalBestFitness) {
                    System.arraycopy(personalBestPositions[i], 0, globalBest, 0, N);
                    globalBestFitness = personalBestFitness[i];
                }
            }

            // Update particle velocities and positions
            for (int i = 0; i < population_size; i++) {
                for (int j = 0; j < N; j++) {
                    double rand1 = random.nextDouble();
                    double rand2 = random.nextDouble();
                    velocities[i][j] = (int) (w * velocities[i][j]
                                             + c1 * rand1 * (personalBestPositions[i][j] - positions[i][j])
                                             + c2 * rand2 * (globalBest[j] - positions[i][j]));
                    if (velocities[i][j] > maxVelocity) {
                        velocities[i][j] = (int) maxVelocity;
                    } else if (velocities[i][j] < -maxVelocity) {
                        velocities[i][j] = (int) -maxVelocity;
                    }
                    positions[i][j] += velocities[i][j];
                    if (positions[i][j] < 0) {
                        positions[i][j] = 0;
                    } else if (positions[i][j] >= N) {
                        positions[i][j] = N - 1;
                    }
                }
                evaluateFitness(positions[i]);
                if (personalBestFitness[i] > globalBestFitness) {
                    System.arraycopy(globalBest, 0, personalBestPositions[i], 0, N);
                    personalBestFitness[i] = globalBestFitness;
                }
            }
        }
        return globalBest;
    }

    private void shuffle(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private int evaluateFitness(int[] position) {
        int conflicts = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (position[i] == position[j]) { // same column
                    conflicts++;
                } else if (Math.abs(position[i] - position[j]) == j - i) { // diagonal conflict
                    conflicts++;
                }
            }
        }
        return conflicts;
    }

    public static void main(String[] args) {
    	int population_size = 100;
    	int N = 4; // Board size
        double w = 1; // Inertia weight
        double c1 = 1.49445; // Cognitive learning factor
        double c2 = 1.49445; // Social learning factor
        int maxIterations = 1000; // Maximum number of iterations
        
        StandardPSO pso = new StandardPSO(population_size,N, w, c1, c2, maxIterations);
        int[] solution = pso.solve();
        
        System.out.println("Solution found:"+Arrays.toString(solution));
        
        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (solution[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }*/
    }

    
}
