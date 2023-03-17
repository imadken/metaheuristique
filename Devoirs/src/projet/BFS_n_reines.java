package projet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_n_reines {
	
	
	
	 public static int[] solve(int n) {
	    	
	    	
	    	int nbr_nodes_gen=0;
	    	int nbr_nodes_exp=0;
	    	
	        int[] solution = new int[n];
	        
	        Queue<Node> queue = new LinkedList<>();
	        
	        queue.offer(new Node(n,0));

	        while (!queue.isEmpty()) {
	        	
	        	nbr_nodes_exp++;
	        	
	        	Node state = queue.poll();
	            
	        	//if (state.isComplete() && (state.heuristique1()==0)) {
	        	if (state.isComplete() && (state.isValid())) {
	            	
	                //solutions.add(state.queens);
	            	solution= (state.queens);
	            	break;
	            } else  {
	            	
	            	//if((!state.isComplete()) && (state.heuristique1()==0)) {
	            	if((!state.isComplete())) {
	                List<Node> nextNodes = state.get_children();
	                for (Node nextNode : nextNodes) {
	                	
	                	
	                	nbr_nodes_gen++;
	                    queue.offer(nextNode);
	                    
	                }
	                }
	            }
	        }
	        
	        System.out.println("nombre des noeuds generes: " +nbr_nodes_gen); 
	        System.out.println("nombre des noeuds explores: " +nbr_nodes_exp+"\n");

	        return solution;
	    
	   }
	
	/* public static int[] solve(int n) {
	    	
	    	
	    	int nbr_nodes_gen=0;
	    	int nbr_nodes_exp=0;
	    	
	        int[] solution = new int[n];
	        
	        Queue<Node> queue = new LinkedList<>();
	        
	        queue.offer(new Node(n,0));

	        while (!queue.isEmpty()) {
	        	
	        	nbr_nodes_exp++;
	        	
	        	Node state = queue.poll();
	            
	            if (state.isComplete() && state.isValid()) {
	            	
	                //solutions.add(state.queens);
	            	solution= (state.queens);
	            	break;
	            } else  {
	            	
	            	if(!state.isComplete()) {
	            	
	                List<Node> nextNodes = state.get_children();
	                for (Node nextNode : nextNodes) {
	                	
	                	nbr_nodes_gen++;
	                	
	                    queue.offer(nextNode);
	                }}
	            }
	        }
	        
	        System.out.println("nombre des noeuds generes: " +nbr_nodes_gen); 
	        System.out.println("nombre des noeuds explores: " +nbr_nodes_exp+"\n");

	        return solution;
	    
	   }*/
	    
	    

	    public static void main(String[] args) {
	        int n = 6;
	        long start,end;
	        
	        System.out.println("bfs\n"); 
	        start =System.currentTimeMillis();
	        
	        int[] solution = BFS_n_reines.solve(n);
	        
	        end =System.currentTimeMillis();
	        
	        System.out.println("temps de calcul " +(end-start)/1000F+" (s)\n"); 
	        
	        
	        //for (int[] solution : solutions) {
	            System.out.println("solution:  "+Arrays.toString(solution));
	        //}
	       // System.out.println("Total solutions: " + solutions.size());
	            
	            
	          
	    }
}
