package projet;





import java.util.*;

public class DFS_n_reines {


    public static int[] solve(int n) {
    	
    	
    	int nbr_nodes_gen=0;//generes
    	int nbr_nodes_exp=0;//explores
    	
        int[] solutions = new int[n];
        
        Stack<Node> stack = new Stack<Node>();
       // Queue<Node> queue = new LinkedList<>();
        
        stack.push(new Node(n,0));

        while (!stack.isEmpty()) {
        	
        	nbr_nodes_exp++;
        	
        	Node state = stack.pop();
            
           if (state.isComplete() && (state.isValid())) {
        	//if (state.isComplete() &&) {
                //solutions.add(state.queens);
            	solutions= (state.queens);
            	break;
            } else  {
            	
            	//if((!state.isComplete()) && (state.heuristique1()==0)) {
            	if((!state.isComplete())) {
            		
                List<Node> nextNodes = state.get_children();
                
                for (Node nextNode : nextNodes) {
                	
                	nbr_nodes_gen++;
                	
                	stack.push(nextNode);
                }
                }
            }
        }
        
        System.out.println("nombre des noeuds generes: " +nbr_nodes_gen); 
        System.out.println("nombre des noeuds explores: " +nbr_nodes_exp+"\n");

        return solutions;
    
   }
    
    /*public static List<int[]> solve(int n) {
    	
        List<int[]> solutions = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(n,0));

        while (!queue.isEmpty()) {
            Node state = queue.poll();

            if (state.isComplete()) {
                solutions.add(state.queens);
            } else {
                List<Node> nextNodes = state.get_children();
                for (Node nextNode : nextNodes) {
                    queue.offer(nextNode);
                }
            }
        }

        return solutions;
    }*/

    public static void main(String[] args) {
        int n = 6;
        long start,end;
        System.out.println("dfs\n"); 
        
        start =System.currentTimeMillis();
        
        int[] solution = DFS_n_reines.solve(n);
        
        end =System.currentTimeMillis();
        
        System.out.println("temps de calcul " +(end-start)/1000F+" (s)\n"); 
        
        
        //for (int[] solution : solutions) {
            System.out.println("solution:  "+Arrays.toString(solution));
        //}
       // System.out.println("Total solutions: " + solutions.size());
            
           
           
    }
}
/*
 * dfs
 * nombre des noeuds generes: 15330
nombre des noeuds explores: 15316

temps de calcul 0.016 (s)

solution:  [4, 2, 0, 5, 3, 1]
-------
bfs

nombre des noeuds generes: 55986
nombre des noeuds explores: 22092

temps de calcul 0.025 (s)

solution:  [1, 3, 5, 0, 2, 4]
---------
a* 2 

nombre des noeuds generes: 53970
nombre des noeuds explores: 21666

temps de calcul 2.303 (s)

solution:  [1, 3, 5, 0, 2, 4]

a* 

nombre des noeuds generes: 894
nombre des noeuds explores: 150

temps de calcul 0.007 (s)

solution:  [1, 3, 5, 0, 2, 4]



*/
