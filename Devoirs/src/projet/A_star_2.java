package projet;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
//import java.util.Comparator;
import java.util.List;




//*************************************possible moves heuristic **********************************


public class A_star_2 {
	
	 public static int[] solve(int n) {
	    	
	    	
	    	int nbr_nodes_gen=0;
	    	int nbr_nodes_exp=0;
	    	
	        int[] solution = new int[n];
	        
	        ArrayList<Node> queue = new ArrayList<Node>();
	        
	        queue.add(new Node(n,0));

	        while (!queue.isEmpty()) {
	        	
	        	nbr_nodes_exp++;
	        	
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
	                	
	                	nbr_nodes_gen++;
	                	
	                	nextNode.set_f(nextNode.heuristique2()+nextNode.nextRow);//c(x)==profondeur
	                	
	                    queue.add(nextNode);
	               }
	                }
	            }
	          //sort descendant  
	          Collections.sort(queue,Node.heuristics_sort2);
	        }
	        
	        System.out.println("nombre des noeuds generes: " +nbr_nodes_gen); 
	        System.out.println("nombre des noeuds explores: " +nbr_nodes_exp+"\n");

	        return solution;
	    
	   }
	 
	 
	 
	 
	 
	 

	    public static void main(String[] args) {
	        int n = 6;
	        long start,end;
	        
	        System.out.println("a* 2 \n"); 
	       /*int[] l= {1,-1,-1,-1};
	        
	        Node test=new Node(4,0,l);
	        
	        System.out.println(test.heuristique2()); */
	        
	        start =System.currentTimeMillis();
	        
	        int[] solution = A_star_2.solve(n);
	        
	        end =System.currentTimeMillis();
	        
	        System.out.println("temps de calcul " +(end-start)/1000F+" (s)\n"); 
	        
	        
	        //for (int[] solution : solutions) {
	            System.out.println("solution:  "+Arrays.toString(solution));
	            
	        //}
	       // System.out.println("Total solutions: " + solutions.size());
	            
	        
	          
	    }
}