package projet;


import java.util.*;




public class Genetic_Algo2 {
	
   static int Size_Population; //= 200;
   static int MAX_GEN; //= 10000;
   static double TAUX_MUTATION; //= 0.6;
   static double TAUX_CROSS; //= 0.6;
  //static int k=5;

    
    int Problem_size;

    Random random;
    
    public Genetic_Algo2(int Problem , int Size_Population ,int MAX_GEN  ,double TAUX_CROSS ,double TAUX_MUTATION  ) {
        this.Problem_size = Problem;
        
        Genetic_Algo2.Size_Population = Size_Population;
        Genetic_Algo2.MAX_GEN = MAX_GEN;
        Genetic_Algo2.TAUX_MUTATION =TAUX_MUTATION;
        Genetic_Algo2.TAUX_CROSS=TAUX_CROSS;
        
        this.random = new Random();
    }
    
    
    // Generer une population initial
    
    int[][] initializePopulation() {
    	
        int[][] population = new int[Size_Population][Problem_size];
        
        for (int i = 0; i < Size_Population; i++) {
        	
            for (int j = 0; j < Problem_size; j++) {
            	
                population[i][j] = random.nextInt(Problem_size);  // [0,2[
            }
        }
        return population;
    }
    
    public  int Fitness(int[] queens) {//counts each queen's conflicts
    	
		 int i=0;
		 int h=0;
	
	    	while((i<Problem_size) && (queens[i]!= -1 ) ) {
	    	
	    	int j=0;	
	    		
	        while ((j<Problem_size) && (queens[j]!=-1)) {
	        	
	        	if(j!=i) {
	        		
	            if (queens[i] == queens[j] || queens[i] - i == queens[j] - j || queens[i] + i == queens[j] + j) {
	        
	                h++;      
	            }}
	        	
	            j++;
	        }
	        
	        i++;
	    }
	    return h;
	   }
    
  


    private int[][] crossover(int[][] population) {
    	
    	//calcule de nombrre de fils pour la crossover a partir du taux de crossover
    	int percentage = (int)Math.round((Size_Population)*TAUX_CROSS);
    	if (percentage%2 ==1) percentage++;//si impaire
    	
    	int[][] selected_for_crossover = new int[percentage][Problem_size];
    	
    	//selectionner les elements aleatoirement
    	for(int i=0 ; i<percentage ; i++) {
    		selected_for_crossover[i] = population[random.nextInt(Size_Population)];
    	}
    	
    	
        int[][] Children = new int[percentage][Problem_size];
      
   
     for(int i=0 ; i<percentage ; i+=2) {
    	 
            int parent1Idx = i;
            int parent2Idx = i+1;
            
            int crossoverPoint = random.nextInt(Problem_size);
            
            //child 1
            System.arraycopy(selected_for_crossover[parent1Idx], 0, Children[parent1Idx], 0, crossoverPoint);
            System.arraycopy(selected_for_crossover[parent2Idx], crossoverPoint, Children[parent1Idx], crossoverPoint, Problem_size - crossoverPoint);
            //child 2 
            System.arraycopy(selected_for_crossover[parent2Idx], 0, Children[parent2Idx], 0, crossoverPoint);
            System.arraycopy(selected_for_crossover[parent1Idx], crossoverPoint, Children[parent2Idx], crossoverPoint, Problem_size - crossoverPoint);
                 
     }
  
        return Children;
    }

    
    private int[][]  Mutation(int[][] children) {
    	
    	//calcule de nombrre de fils pour la mutation a partir du taux de mutation
    	int len_children = children.length;
    	
        int percentage = (int)Math.round((len_children)*TAUX_MUTATION);
    	if (percentage%2 ==1) percentage++;
    	
    	int[][] selected_for_mutation = new int[percentage][Problem_size];
    	
    	
    	
    	for (int i =0; i<percentage ; i++) {
    		
    		selected_for_mutation[i]= children[random.nextInt(len_children)];
    		
    	}
    	
    	
    	for (int i = 0; i < percentage; i++) {
    		 
    	  //for (int j = 0; j < percentage; j++) {
            	
                //if (random.nextDouble() > TAUX_MUTATION) {
                	
                	//selected_for_mutation[i][j] = random.nextInt(Problem_size);; // replace
    		  
    		 selected_for_mutation[i][random.nextInt(Problem_size)] = random.nextInt(Problem_size);
    		  
                  // }
                
    	  //} 
        }
    	
    	//merge all children (children from crossover + children from mutation)
        int[][] Mergedbrothers = new int[len_children + percentage][Problem_size];
        
        System.arraycopy(children, 0, Mergedbrothers, 0, len_children);
        System.arraycopy(selected_for_mutation, 0, Mergedbrothers, len_children , percentage);
    	
        
        return Mergedbrothers;
    }

        //merge initial population with children and prepare them to select the top elements
        private int[][] MergeFamily_elitist(int[][] population, int[][] Children) {
    	
        int[][] MergedFamily = new int[Size_Population + Children.length][Problem_size];

        System.arraycopy(Children, 0, MergedFamily, 0 , Children.length);
        System.arraycopy(population, 0, MergedFamily,  Children.length, Size_Population);
        
        Arrays.sort(MergedFamily, Comparator.comparingInt(this::Fitness));

        return MergedFamily;
    }
        
      //merge initial population with children and prepare them to select all children and complete the rest with the best parents
    private int[][] MergeFamily_trunction(int[][] population, int[][] Children) {
    	
        int[][] MergedFamily = new int[Size_Population + Children.length][Problem_size];
        
        Arrays.sort(population, Comparator.comparingInt(this::Fitness));
        
        System.arraycopy(Children, 0, MergedFamily, 0 , Children.length);
        System.arraycopy(population, 0, MergedFamily,  Children.length, Size_Population);

        return MergedFamily;
    }

    // Selectionner les meilleurs
    private int[][] Selection(int[][] MergedFamily) {
  
        int[][] selectedPopulation = new int[Size_Population][Problem_size];
  
        System.arraycopy(MergedFamily, 0, selectedPopulation, 0, Size_Population);
       
        return selectedPopulation;
        
    }

    
    public int[] solve() {
    	
    	
    	
    	//population initial
        int[][] population = initializePopulation();
        
        //trier la population par fitness 
        Arrays.sort(population, Comparator.comparingInt(this::Fitness));
        
        //Optimum Globale       
        int[] GlobalBestSolution = population[0];
        
        for (int generation = 0; generation < MAX_GEN; generation++) {
        	
        	//if(Size_Population < 0) break;
        	
        	//Crossover
            int[][] Children = crossover(population);
            
            //Mutation
            Mutation(Children);
            
            //Combiner les fils
            int[][] MergedFamily = MergeFamily_elitist(population, Children);
            
            //Selectionner
            int[][] selectedPopulation = Selection(MergedFamily);
            
            population = selectedPopulation;
            
            
            int[] LocalBestSolution = population[0];
            
            if (Fitness(LocalBestSolution) < Fitness(GlobalBestSolution)) GlobalBestSolution = LocalBestSolution;
            
            if (Fitness(GlobalBestSolution)==0) break;
        }
        
        return GlobalBestSolution ;
    }
    
    
    

public static void main(String[] args) {
	
     
    int Problem = 14;
    
    int Size_Population =100;//= 200;
    int MAX_GEN = 10000  ; //= 10000;
    double TAUX_MUTATION  = 0.5; //= 0.8;
    double TAUX_CROSS  =  0.5; //= 0.6;
    
    
    int iter =10;
    
    Genetic_Algo2 solver = new Genetic_Algo2(Problem ,Size_Population,MAX_GEN,TAUX_CROSS,TAUX_MUTATION );
    
    
    for(int i=0;i<iter;i++) {
    
    int[] solution = solver.solve();
    
    System.out.println();
    //System.out.println("Input Problem size: "+Problem);
    
    System.out.println("Iter "+i+" The solution is : "+ Arrays.toString(solution)+"  Fitness : "+solver.Fitness(solution));
   
    
    }
}
    
    
    
}