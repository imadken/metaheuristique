package projet;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
//import java.util.Comparator;
import java.util.List;




//*************************************possible moves heuristic **********************************


public class A_star_2 {
	
	static int nbr_nodes_gen=0;
	static int nbr_nodes_exp=0;
	
	 public static int[] solve(int n) {
	    	
	    	
		 A_star_2.nbr_nodes_gen=0;
		 A_star_2.nbr_nodes_exp=0;
	    	
	        int[] solution = new int[n];
	        
	        ArrayList<Node> queue = new ArrayList<Node>();
	        
	        queue.add(new Node(n,0));

	        while (!queue.isEmpty()) {
	        	
	        	A_star_2.nbr_nodes_exp++;
	        	
	        	Node state = queue.remove(0);
	            
	        	
	        	
	            if (state.isComplete() && (state.isValid())) {
	        	
	            	solution= (state.queens);
	            	
	            	break;
	            } else  {
	            	
	            	if((!state.isComplete()) ) {
	            		
	                List<Node> nextNodes = state.get_children();
	                for (Node nextNode : nextNodes) {
	                	
	                	A_star_2.nbr_nodes_gen++;
	                	
	                	nextNode.set_f(nextNode.heuristique2()+nextNode.nextRow);//c(x)==profondeur
	                	
	                    queue.add(nextNode);
	               }
	                }
	            }
	          //sort descendant  
	          Collections.sort(queue,Node.heuristics_sort2);
	        }
	        
	        System.out.println("nombre des noeuds generes: " +A_star_2.nbr_nodes_gen); 
	        System.out.println("nombre des noeuds explores: " +A_star_2.nbr_nodes_exp+"\n");

	        return solution;
	    
	   }
	 
	 
	 
	 
	 
	 

	    public static void main(String[] args) {
	        int n = 7;
	        long start,end;
	        
	        System.out.println("a* 2 \n"); 
	       /*int[] l= {1,-1,-1,-1};
	        
	        Node test=new Node(4,0,l);
	        
	        System.out.println(test.heuristique2()); */
	        
	        start =System.currentTimeMillis();
	        
	        int[] solution = A_star_2.solve(n);
	        
	        end =System.currentTimeMillis();
	        
	        System.out.println("temps de calcul " +(end-start)+" (ms)\n"); 
	        
	        
	        //for (int[] solution : solutions) {
	            System.out.println("solution:  "+Arrays.toString(solution));
	            
	        //}
	       // System.out.println("Total solutions: " + solutions.size());
	            
	        
	          
	    }
}