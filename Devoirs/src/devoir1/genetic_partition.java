package devoir1;

import java.util.*;


public class genetic_partition {
	
   static int Size_Population = 2000;
   static int MAX_GEN = 100000;
   static double TAUX_MUTATION = 0.3;
  //static int k=5;

    int[] Problem;
    int list_size;

    Random random;
    
    public genetic_partition(int[] Problem) {
        this.Problem = Problem;
        this.list_size = Problem.length;
        this.random = new Random();
    }
    
    
    // Generer une population initial
    
    int[][] initializePopulation() {
    	
        int[][] population = new int[Size_Population][list_size];
        
        for (int i = 0; i < Size_Population; i++) {
        	
            for (int j = 0; j < list_size; j++) {
            	
                population[i][j] = random.nextInt(2);  // [0,2[
            }
        }
        return population;
    }
    
 
    private int Fitness(int[] solution) {
        int sum1 = 0;
        int sum2 = 0;
        
        for (int i = 0; i < list_size; i++) {
            if (solution[i] == 0) {
                sum1 += Problem[i];
            } else {
                sum2 += Problem[i];
            }
        }
        return Math.abs(sum1 - sum2);
    }


    private int[][] crossover(int[][] population) {
    	
        int[][] Children = new int[4][list_size];
      
   // Arrays.sort(population, Comparator.comparingInt(this::Fitness));

            int parent1Idx = 0;
            int parent2Idx = 1;
            int crossoverPoint = random.nextInt(list_size);
            
            //child 1
            System.arraycopy(population[parent1Idx], 0, Children[0], 0, crossoverPoint);
            System.arraycopy(population[parent2Idx], crossoverPoint, Children[0], crossoverPoint, list_size - crossoverPoint);
            //child 2 
            System.arraycopy(population[parent2Idx], 0, Children[1], 0, crossoverPoint);
            System.arraycopy(population[parent1Idx], crossoverPoint, Children[1], crossoverPoint, list_size - crossoverPoint);
           
            //child 3 and 4 to be Mutationd later
            System.arraycopy(Children[0],0, Children[2], 0, list_size);
            System.arraycopy(Children[0],0, Children[2], 0, list_size);
            
           /* System.arraycopy(population[parent1Idx], 0, Children[2], 0, crossoverPoint);
            System.arraycopy(population[parent2Idx], crossoverPoint, Children[2], crossoverPoint, list_size - crossoverPoint);
            
            System.arraycopy(population[parent2Idx], 0, Children[3], 0, crossoverPoint);
            System.arraycopy(population[parent1Idx], crossoverPoint, Children[3], crossoverPoint, list_size - crossoverPoint);*/
            
        //}
        return Children;
    }

    
    private void Mutation(int[][] children) {
    	
    	  for (int j = 0; j < list_size; j++) {
            	
                if (random.nextDouble() < TAUX_MUTATION) {
                	
                	children[2][j] = 1 - children[2][j]; // inverser
                	children[3][j] = 1 - children[3][j]; // inverser

                                                        }
        }
    }

    

    // fusionner la population avec les nouveaux elements
    private int[][] MergeFamily(int[][] population, int[][] Children) {
    	
        int[][] MergedFamily = new int[Size_Population + 4][list_size];
        
        System.arraycopy(population, 0, MergedFamily, 0, Size_Population);
        System.arraycopy(Children, 0, MergedFamily, Size_Population , 4);
        
        return MergedFamily;
    }

    // Selectionner les meilleurs
    private int[][] Selection(int[][] MergedFamily) {
    	
        Arrays.sort(MergedFamily, Comparator.comparingInt(this::Fitness));
        
        int[][] selectedPopulation = new int[Size_Population][list_size];
  
        System.arraycopy(MergedFamily, 0, selectedPopulation, 0, Size_Population);
        
       // Size_Population =- k;
        
        return selectedPopulation;
        
    }

    
    public int[] solve() {
    	
    	
    	
    	//population initial
        int[][] population = initializePopulation();
        
        Arrays.sort(population, Comparator.comparingInt(this::Fitness));
        
       
        int[] GlobalBestSolution = population[0];
        
        for (int generation = 0; generation < MAX_GEN; generation++) {
        	//if(Size_Population < 0)break;
        	
            int[][] Children = crossover(population);
            
            Mutation(Children);
            
            
            int[][] MergedFamily = MergeFamily(population, Children);
            
            int[][] selectedPopulation = Selection(MergedFamily);
            
            population = selectedPopulation;
            
            
            int[] LocalBestSolution = population[0];
            
            if (Fitness(LocalBestSolution) < Fitness(GlobalBestSolution)) GlobalBestSolution = LocalBestSolution;
            
        }
        return GlobalBestSolution ;
    }
    
public static void main(String[] args) {
	
    //int[] Problem = {1 , 6 , 9 , 7 ,2 ,1 ,3 ,3 , 9 , 41 ,3, 6 ,5,7,89,3,4,6,2,4};
  //int [] Problem= {3, 4, 3, 1, 3, 2, 3, 2, 1};
  //int [] Problem= {2, 10, 3, 8, 5, 7, 9, 5, 3, 2};
  //int [] Problem= {484, 114, 205, 288, 506, 503, 201, 127, 410};
  //int [] Problem= {23, 31,  29,  44,  53,  38,  63, 85, 89, 82};
  int [] Problem= {771, 121, 281, 854, 885, 734,  486, 1003, 83, 62};
  //int [] Problem= {70, 73, 77, 80, 82, 87, 90, 94, 98, 106, 110, 113, 115, 118, 120};
  //int [] Problem= {382745, 799601, 909247, 729069, 467902,  44328,  34610, 698150, 823460, 903959, 853665, 551830, 610856, 670702, 488960, 951111, 323046, 446298, 931161,  31385, 496951, 264724, 224916, 169684};
  //int[] Problem = {1 , 6 , 9};
    
    genetic_partition solver = new genetic_partition(Problem);
    
    int[] solution = solver.solve();
    
    System.out.println("Input Problem: " + Arrays.toString(Problem));
    System.out.println("Partition 1: ");
    
    int s1=0;
    int s2=0;
    
    for (int i = 0; i < Problem.length; i++) {
        if (solution[i] == 0) {
            System.out.print(Problem[i] + " ");
            s1+=Problem[i];
        }
    }
    System.out.println();
    System.out.println("Partition 2: ");
    for (int i = 0; i < Problem.length; i++) {
        if (solution[i] == 1) {
            System.out.print(Problem[i] + " ");
        s2+=Problem[i];
        }
    }
    
    System.out.println("\n\n The minmum sum diffrenece is : "+ Math.abs(s1-s2));
}

}

// Find the solution with the minimum fitness value
/* private int[] findBestSolution(int[][] population) {
	
    int[] bestSolution = population[0];
    
    int bestFitness = Fitness(population[0]);
    
    for (int i = 1; i < Size_Population; i++) {
    	
        int fitness = Fitness(population[i]);
        
        if (fitness < bestFitness) {
        	
            bestSolution = population[i];
            bestFitness = fitness;
        }
    }
    return bestSolution;
}*/
