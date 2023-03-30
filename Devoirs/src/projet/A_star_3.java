package projet;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
//import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;




//*************************************possible moves heuristic **********************************


public class A_star_3 {
	
	static int nbr_nodes_gen=0;
	static int nbr_nodes_exp=0;
	
	 public static int[] solve(int n) {
	    	
	    	
		 A_star_3.nbr_nodes_gen=0;
		 A_star_3.nbr_nodes_exp=0;
	    	
	        int[] solution = new int[n];
	        
	        ArrayList<Node> queue = new ArrayList<Node>();
	       // PriorityQueue<Node> queue = new PriorityQueue<Node>( Node.heuristics_sort);
	        queue.add(new Node(n,0));

	        while (!queue.isEmpty()) {
	        	
	        	A_star_3.nbr_nodes_exp++;
	        	
	        	Node state = queue.remove(0);
	            
	        	//System.out.println(state.get_f());
	        	
	            if (state.isComplete() && (state.isValid())) {
	        	//if (state.isComplete()) {
	                //solutions.add(state.queens);
	            	solution= (state.queens);
	            	//System.out.println(state.get_f());
	            	break;
	            } else  {
	            	
	            	if((!state.isComplete()) ) {
	            		
	                List<Node> nextNodes = state.get_children();
	                for (Node nextNode : nextNodes) {
	                	
	                	A_star_3.nbr_nodes_gen++;
	                	
	                	nextNode.set_f(nextNode.heuristique3());
	                	
	                    queue.add(nextNode);
	               }
	                }
	            }
	          //sort descendant  
	          Collections.sort(queue,Node.heuristics_sort);
	        }
	        
	        System.out.println("nombre des noeuds generes: " +A_star_3.nbr_nodes_gen); 
	        System.out.println("nombre des noeuds explores: " +A_star_3.nbr_nodes_exp+"\n");

	        return solution;
	    
	   }
	 
	 
	 
	 
	 
	 

	    public static void main(String[] args) {
	        int n = 8;
	        long start,end;
	        
	        System.out.println("a* 3 \n"); 
	       /*int[] l= {1,-1,-1,-1};
	        
	        Node test=new Node(4,0,l);
	        
	        System.out.println(test.heuristique2()); */
	        
	        start =System.currentTimeMillis();
	        
	        int[] solution = A_star_3.solve(n);
	        
	        end =System.currentTimeMillis();
	        
	        System.out.println("temps de calcul " +(end-start)+" (ms)\n"); 
	        
	        
	       
	            System.out.println("solution:  "+Arrays.toString(solution));
	            
	       
	          
	    }
}