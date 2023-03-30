package projet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_n_reines {
	
	static int nbr_nodes_gen=0;
	static int nbr_nodes_exp=0;
	
	 public static int[] solve(int n) {
	    	
	    	
		 BFS_n_reines.nbr_nodes_gen=0;
		 BFS_n_reines.nbr_nodes_exp=0;
	    	
	        int[] solution = new int[n];
	        
	        Queue<Node> queue = new LinkedList<>();
	        
	        queue.offer(new Node(n,0));

	        while (!queue.isEmpty()) {
	        	
	        	BFS_n_reines.nbr_nodes_exp++;
	        	
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
	                	
	                	
	                	BFS_n_reines.nbr_nodes_gen++;
	                    queue.offer(nextNode);
	                    
	                }
	                }
	            }
	        }
	        
	        System.out.println("nombre des noeuds generes: " +BFS_n_reines.nbr_nodes_gen); 
	        System.out.println("nombre des noeuds explores: " +BFS_n_reines.nbr_nodes_exp+"\n");

	        return solution;
	    
	   }

	    public static void main(String[] args) {
	        int n = 9;
	        long start,end;
	        
	        System.out.println("bfs\n"); 
	        start =System.currentTimeMillis();
	        
	        int[] solution = BFS_n_reines.solve(n);
	        
	        end =System.currentTimeMillis();
	        
	        System.out.println("temps de calcul " +(end-start)+" (ms)\n"); 
	        
	        
	        //for (int[] solution : solutions) {
	            System.out.println("solution:  "+Arrays.toString(solution));
	        //}
	       // System.out.println("Total solutions: " + solutions.size());
	            
	            
	          
	    }
}
