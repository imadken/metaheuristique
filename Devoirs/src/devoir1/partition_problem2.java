package devoir1;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math.*;
 
public class partition_problem2 {
    
	//Min et Max represente les extremites de l'intervalle des valeurs entieres 
	int Min=0;
	int Max=100;
	
	int n; //taille de la liste
	
	
	ArrayList<Integer> tab = new ArrayList<Integer>(); //tab et le vecteur qui va contenir l'instanciation
    
	static int [] sol; // si sol[i] =1 alors l'element tab[i] est  dans S1 sinon S2
	
	
    //constructeur
	public  partition_problem2(int n){
		
		this.n=n;
		
		sol=new int[n];
		
		for(int i =0 ;i<n;i++) {
			this.tab.add( Min + (int)(Math.random() * ((Max - Min) + 1)));
	    }
		
		//generer une solution
	    solution();
	}
	
	//la methode qui genere une solution en mettant des valeurs aleatoire (1 ou 2) dans chaque case du vecteur sol
	void solution() {
		
		for(int i=0 ; i<this.n ; i++) {
			
			this.sol[i]= 1 + (int)(Math.random() * ((2 - 1) + 1));
			
		}
		
	}
	
	//la methode qui calcule la difference entre la somme du S1 et S2
	int evaluation() {
		
		int s1=0;
		int s2=0;
		
		for(int i=0 ;i<this.n ; i++) {
			
			if (sol[i]==1) s1 += this.tab.get(i);
			
			else s2 += this.tab.get(i);
			
		}
		
		return Math.abs( s1 - s2 );
		
	}
	
	//a count function to verify if we have 2 vectors
	static boolean verification(int sol[]) {
	    boolean contientUn = false;
	    boolean contientDeux = false;
	    
	    for (int valeur : sol) {
	        if (valeur == 1) {
	            contientUn = true;
	        } else if (valeur == 2) {
	            contientDeux = true;
	        }
	        
	        if (contientUn && contientDeux) {
	            return true;
	        }
	    }
	    
	    return false;
	}	
	
	
/*	static boolean verification(int sol[]) {
		boolean allEqual1 = Arrays.stream(sol).distinct().count() == 1;
		boolean allEqual2 = Arrays.stream(sol).distinct().count() == 2;
		
		if (allEqual1 || allEqual2) return true;
		else return false;
	}*/
	
	
	//getters
	
	void get_instanciation() {
		
		   for(int i=0 ; i< this.n;i++) {
		        System.out.print(this.tab.get(i)+" ");
		    }
	}
	
	
	void get_solution() {
		
           for(int i=0 ;i<this.n ; i++) {
			
			System.out.print(this.sol[i]+"  ");
		}
		
	}
	
	
	int get_n() {return this.n;}


 
    public static void main(String[] args)
    {
       
 
        //creer une instanciation
        partition_problem2 instance = new partition_problem2(20);
        
        //afficher l'instaniciation
        System.out.println("instanciation : ");
        
        instance.get_instanciation();
    
        //afficher la solution
        System.out.println("\n\nSolution : ");
        
        instance.get_solution();
        
        //verification
		boolean dis = verification(sol);
		if (dis) {
			
		//afficher la difference
			System.out.println("\n\nEvaluation , la difference est: ");
			System.out.println(instance.evaluation()); 
			
		}
		else {
			System.out.println("\n\n partition error !!");
		}
       

    }
}












/* // Partition set `S` into two subsets, `S1` and `S2`, such that the
// difference between the sum of elements in `S1` and the sum
// of elements in `S2` is minimized
public static int findMinAbsDiff(int[] S, int n, int S1, int S2,
                            Map<String, Integer> lookup)
{
    // Base case: if the list becomes empty, return the absolute
    // difference between both sets
    if (n < 0) {
        return Math.abs(S1 - S2);
    }

    // Construct a unique map key from dynamic elements of the input.
    // Note that we can uniquely identify the subproblem with `n` and `S1` only,
    // as `S2` is nothing but `S-S1`, where `S` is the sum of all elements
    String key = n + "|" + S1;

    // If the subproblem is seen for the first time, solve it and
    // store its result in a map
    if (!lookup.containsKey(key))
    {
        // Case 1. Include the current item in subset `S1` and recur
        // for the remaining items `n-1`
        int inc = findMinAbsDiff(S, n - 1, S1 + S[n], S2, lookup);

        // Case 2. Exclude the current item from subset `S1` and recur for
        // the remaining items `n-1`
        int exc = findMinAbsDiff(S, n - 1, S1, S2 + S[n], lookup);

        lookup.put(key, Integer.min(inc, exc));
    }

    return lookup.get(key);
}*/