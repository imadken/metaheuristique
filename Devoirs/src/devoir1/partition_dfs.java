package devoir1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

import projet.Node;

import java.lang.Math.*;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class



public class partition_dfs {

    //Min et Max represente les extremites de l'intervalle des valeurs entieres
    int Min=0;
    int Max=100;
    int h;
    int somme_cible=0;
   

    static int n; //taille de la liste


    static ArrayList<Integer> tab = new ArrayList<Integer>(); //tab et le vecteur qui va contenir l'instanciation

     int [] sol; // si sol[i] =1 alors l'element tab[i] est  dans S1 sinon S2

    int prof;

    //constructeur
    public  partition_dfs(int n){

    	partition_dfs.n=n;
        prof=0;
        sol=new int[n];

        Arrays.fill(this.sol, 0);
        for(int i =0 ;i<n;i++) {
        	partition_dfs.tab.add( Min + (int)(Math.random() * ((Max - Min) + 1)));
        }
        
      for (int i=0;i<partition_dfs.tab.size();i++) somme_cible += partition_dfs.tab.get(i);
      
      somme_cible /= 2;
      
    }
    
    
    public  partition_dfs( int [] l){

    	partition_dfs.n=l.length;
        prof=0;
        sol=new int[n];

        Arrays.fill(this.sol, 0);
        
        for(int i =0 ;i<n;i++) {
        	
        partition_dfs.tab.add(l[i]);
        
        }
       
    }
    

    //constructeur
    public  partition_dfs(int p, int[] T, int i){

        prof=p+1; //update profondeur

        sol=(int[])T.clone(); //creates a new array that is a copy of the original array. (garder une version de T dans sol)
        sol[p]=i;

    }

    public boolean finalsolution() {
        return (this.prof == partition_dfs.n); //attribut staic appel with name class
    }

    public void devchild(Stack <partition_dfs> s) {
    	partition_dfs temp;

        temp = new partition_dfs(this.prof, this.sol, 1);
        s.push(temp);
        //System.out.println("\n");
          //temp.get_solution();
       temp = new partition_dfs(this.prof, this.sol, 2);
        s.push(temp);
        //System.out.println("\n");
        //temp.get_solution();
    }
    
    //develop childs for a*
    
    public void devchild_A_star(ArrayList <partition_dfs> s) {
    	partition_dfs temp;

        temp = new partition_dfs(this.prof, this.sol, 1);
        
        temp.set_h(temp.heuristic());
        
        s.add(temp);
        //System.out.println("\n");
          //temp.get_solution();
       temp = new partition_dfs(this.prof, this.sol, 2);
       
       temp.set_h(temp.heuristic()); 
       
       s.add(temp);
        //System.out.println("\n");
        //temp.get_solution();
    }
    
    



    public void DFS(partition_dfs InitNoeud) {System.out.println("DFS");
        //initialisation des variables
        Stack<partition_dfs> OUVERT = new Stack<partition_dfs>();
        ArrayList<partition_dfs> FERMER = new ArrayList<partition_dfs>();
        ArrayList<partition_dfs> solutions = new ArrayList<partition_dfs>();

        //FileWriter fichier = new FileWriter("8.txt");
       
        int exp=0;
        int gen=0;
       
        
        partition_dfs temp, optimal;
        
        int min;
        int first_sol=0;
        int first=0;
        int nbr_sol=0;
        
        long start,end ;//pour calculer le temps
        

        optimal = null;
        min = Integer.MAX_VALUE;

        OUVERT.push(InitNoeud);
        
        start =System.currentTimeMillis();

        while(!OUVERT.isEmpty()) {
        	
            temp = OUVERT.pop();
            exp++;
            
            //temp.get_solution();
            FERMER.add(temp);
            //System.out.println("\n");
           // System.out.println(temp.finalsolution());

            if(temp.finalsolution() && partition_dfs.verification(temp.sol)) {
                //System.out.println("******");
            	
            	first=1;
            	
            	solutions.add(temp);
            	
                if (temp.evaluation() < min) {
                    min = temp.evaluation();
                    optimal = temp;
                    
                   // nbr_sol++;
                    
                }
            }
            else if (!temp.finalsolution()){
               // System.out.println("++++++++");
            	
            	if(first==0) first_sol++;
                
            	temp.devchild(OUVERT);
                
            	gen += 2;
            }

        }
        
        end =System.currentTimeMillis();
        
        //System.out.println("\ngen=  "+gen);
       // System.out.println("\nexp=  "+exp);
        System.out.println("\ninstanciation :\n ");
        optimal.get_instanciation();
        
        
        //afficher la solution
        //System.out.println("\n\nSolutions optimal obtenue par le DFS : ");
        
        for(int i=0;i<solutions.size();i++) {
        	
        	if(solutions.get(i).evaluation()==min) {
        		nbr_sol++;
        		//System.out.print("\n-");solutions.get(i).get_solution();System.out.print("\n");
        	}
           	
        }
        
      //afficher l'instanciation
        
        //il faut mentionner que si nbr_sol==n alors on a n/2 solutions distinctes 
        
        System.out.println("\n\n nbr noeuds generes avant la premiere solution : "+first_sol);
        
        System.out.println("\n nbr de solution optimales  "+nbr_sol +" ("+(nbr_sol/2)+" solutions distinctes)");
        
        
        
        
        
        System.out.println("\ntemps de calcule : "+((end-start))+" ms");
        
        
        //afficher l'instanciation
       // System.out.println("\ninstanciation : ");
        //optimal.get_instanciation();
        
        //afficher la solution
       // System.out.println("\n\nSolution optimal obtenue par le DFS : ");
       // optimal.get_solution();
        
        //afficher la difference minimale
        System.out.println("\n\nEvaluation , la difference minimale est: ");
        System.out.println(optimal.evaluation());
        
        
        
        
        
       System.out.println("\n\ntoutes les solutions: "+solutions.size());
        
       /*for(int i=0;i<solutions.size();i++) {
    	   
    	   System.out.print("\n-  ");
    	   
    	   solutions.get(i).get_solution();
    	   
    	   System.out.print("\n  ");
       
       }*/
        
    }


     int heuristic() {
    	
    	int s1=0;
    	int s2=0;
    	
    	for(int i=0;i<this.sol.length;i++) {
    		
    		if (this.sol[i]==1) { s1+=partition_dfs.tab.get(i); continue;}
    		
    		if (this.sol[i]==2) { s2+=partition_dfs.tab.get(i);}
    		
    	}
    	
    	
    	
    	return somme_cible - Math.abs(s1-s2);}
     
     

    public void A_star(partition_dfs InitNoeud) {
    	
    	 
    	System.out.println("A*");
    	
    	
        //initialisation des variables
    	ArrayList<partition_dfs> OUVERT = new ArrayList<partition_dfs>();
        
        //ArrayList<partition_dfs> FERMER = new ArrayList<partition_dfs>();
        
        ArrayList<partition_dfs> solutions = new ArrayList<partition_dfs>();

        //FileWriter fichier = new FileWriter("8.txt");
       
       
        
        partition_dfs temp, optimal;
        
        int min;
        int first_sol=0;
        int first=0;
        int nbr_sol=0;
        int exp=0;
        int gen=0;
        
        long start,end ;//pour calculer le temps
        

        optimal = null;
        min = Integer.MAX_VALUE;

        OUVERT.add(InitNoeud);
        
        start =System.currentTimeMillis();

        while(!OUVERT.isEmpty()) {
        	
            temp = OUVERT.remove(0);
            
            exp++;
            
            //temp.get_solution();
           // FERMER.add(temp);
            //System.out.println("\n");
           // System.out.println(temp.finalsolution());

            if(temp.finalsolution() && partition_dfs.verification(temp.sol)) {
                //System.out.println("******");
            	
            	first=1;
            	
            	solutions.add(temp);
            	
                if (temp.evaluation() < min) {
                    min = temp.evaluation();
                    optimal = temp;
                    
                   // nbr_sol++;
                    
                }
            }
            else if (!temp.finalsolution()){
               // System.out.println("++++++++");
            	
            	if(first==0) first_sol++;
                
            	temp.devchild_A_star(OUVERT);
            	
            gen+=2;
            }

            //sort
            Collections.sort(OUVERT,heuristic_sort);
            }
        
        end =System.currentTimeMillis();
        
        //System.out.println("\ngen=  "+gen);
        //System.out.println("\nexp=  "+exp);
        
        System.out.println("\ninstanciation :\n ");
        optimal.get_instanciation();
        
        
        //afficher la solution
        //System.out.println("\n\nSolutions optimal obtenue par le DFS : ");
        
        for(int i=0;i<solutions.size();i++) {
        	
        	if(solutions.get(i).evaluation()==min) {
        		nbr_sol++;
        		//System.out.print("\n-");solutions.get(i).get_solution();System.out.print("\n");
        	}
           	
        }
        
      //afficher l'instanciation
        
        //il faut mentionner que si nbr_sol==n alors on a n/2 solutions distinctes 
        
        System.out.println("\n\n nbr noeuds generes avant la premiere solution : "+first_sol);
        
        System.out.println("\n nbr de solution optimales  "+nbr_sol +" ("+(nbr_sol/2)+" solutions distinctes)");
        
        
        
        
        
        System.out.println("\ntemps de calcule : "+((end-start))+" ms");
        
        
        //afficher l'instanciation
       // System.out.println("\ninstanciation : ");
        //optimal.get_instanciation();
        
        //afficher la solution
       // System.out.println("\n\nSolution optimal obtenue par le DFS : ");
       // optimal.get_solution();
        
        //afficher la difference minimale
        System.out.println("\n\nEvaluation , la difference minimale est: ");
        System.out.println(optimal.evaluation());
        
        
        
        
        
       System.out.println("\n\ntoutes les solutions: "+solutions.size());
        
       /*for(int i=0;i<solutions.size();i++) {
    	   
    	   System.out.print("\n-  ");
    	   
    	   solutions.get(i).get_solution();
    	   
    	   System.out.print("\n  ");
       
       }*/
        
    }


    //la methode qui genere une solution en mettant des valeurs aleatoire (1 ou 2) dans chaque case du vecteur sol
    void solution() {

        for(int i=0 ; i<partition_dfs.n ; i++) {

            this.sol[i]= 1 + (int)(Math.random() * ((2 - 1) + 1));

        }

        //this.sol[0]=9;

    }

    //la methode qui calcule la difference entre la somme du S1 et S2
    int evaluation() {


        int s1=0;
        int s2=0;
        //System.out.println("1111111");

        for(int i=0 ;i<partition_dfs.n ; i++) {
           // System.out.println("1111111222222***"+sol[i]);

            if (this.sol[i]==1) s1 += partition_dfs.tab.get(i);

            else s2 += partition_dfs.tab.get(i);

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
            else return false;

            if (contientUn && contientDeux) {
                return true;
            }
        }

        return false;
    }



    //getters

    void get_instanciation() {

        for(int i=0 ; i< partition_dfs.n;i++) {
            System.out.print(partition_dfs.tab.get(i)+" ");
        }
    }


    void get_solution() {

        for(int i=0 ;i<partition_dfs.n ; i++) {

            System.out.print(this.sol[i]+"  ");
        }

    }


    int get_n() {return partition_dfs.n;}

    void set_h(int h) {this.h=h;}

    
    public static Comparator<partition_dfs> heuristic_sort = new Comparator<partition_dfs>() {
		 
        // Method
        public int compare(partition_dfs s1, partition_dfs s2) {
 
            int f1 = s1.h;
            int f2 = s2.h;
 
            // For ascending order
            return f1 - f2;
 
            // For descending order
           // return f2-f1;
        }
    };
    public static Comparator<Integer> heuristic_sort_desc = new Comparator<Integer>() {
		 
        // Method
        public int compare(Integer s1, Integer s2) {
 
            int f1 = s1;
            int f2 = s2;
 
            // For ascending order
            //return f1 - f2;
 
            // For descending order
            return f2-f1;
        }
    };
    
    
    
    
    
    
    
    public static void main(String[] args)
    {
    	//partition_dfs test = new partition_dfs(5);
    	//int [] t= {1,2,3,4,6};
    	//int [] l= {31, 10, 20, 19,  4,  3,  6};
    	//int [] l= {25, 35, 45,  5, 25,  3,  2,  2};
    	//int [] l= {3, 4, 3, 1, 3, 2, 3, 2, 1};
    	//int [] l= {2, 10, 3, 8, 5, 7, 9, 5, 3, 2};
    	//int [] l= {484, 114, 205, 288, 506, 503, 201, 127, 410};
    	//int [] l= {23, 31,  29,  44,  53,  38,  63, 85, 89, 82};
    	//int [] l= {771, 121, 281, 854, 885, 734,  486, 1003, 83, 62};
    	int [] l= {70, 73, 77, 80, 82, 87, 90, 94, 98, 106, 110, 113, 115, 118, 120};
    	//int [] l= {382745, 799601, 909247, 729069, 467902,  44328,  34610, 698150, 823460, 903959, 853665, 551830, 610856, 670702, 488960, 951111, 323046, 446298, 931161,  31385, 496951, 264724, 224916, 169684};
    			
    	partition_dfs test = new partition_dfs(l);
    	//partition_dfs testt = new partition_dfs(t);
    	//System.out.print(testt.heuristic());
    	
    	
        test.DFS(test);
        
      System.out.println("\n\n--------------\n");
      
      // Collections.sort(partition_dfs.tab,heuristic_sort_desc);

        test.A_star(test);
        
    }
}

