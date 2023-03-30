package projet;





import java.util.*;

public class DFS_n_reines {
	static int nbr_nodes_gen;//generes
	static int nbr_nodes_exp;//explores
	
    public static int[] solve(int n) {

    	DFS_n_reines.nbr_nodes_gen=0;//generes
    	DFS_n_reines.nbr_nodes_exp=0;//explores
    	
        int[] solutions = new int[n];
        
        Stack<Node> stack = new Stack<Node>();
       
        
        stack.push(new Node(n,0));

        while (!stack.isEmpty()) {
        	
        	DFS_n_reines.nbr_nodes_exp++;
        	
        	Node state = stack.pop();
            
           if (state.isComplete() && (state.isValid())) {
        	
            	solutions= (state.queens);
            	break;
            } else  {

            	if((!state.isComplete())) {
            		
                List<Node> nextNodes = state.get_children();
                
                for (Node nextNode : nextNodes) {
                	
                	DFS_n_reines.nbr_nodes_gen++;
                	
                	stack.push(nextNode);
                }
                }
            }
        }
        
        System.out.println("nombre des noeuds generes: " +DFS_n_reines.nbr_nodes_gen); 
        System.out.println("nombre des noeuds explores: " +DFS_n_reines.nbr_nodes_exp+"\n");

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
        
        System.out.println("temps de calcul " +(end-start)+" (ms)\n"); 
        
        
        //for (int[] solution : solutions) {
            System.out.println("solution:  "+Arrays.toString(solution));
        //}
       // System.out.println("Total solutions: " + solutions.size());
            
    	/*long start,end;    
       for (int i = 4; i<=6 ;i++) {
    	   System.out.println(i);
    	   start =System.currentTimeMillis();
           
    	   System.out.println("solution:  "+Arrays.toString(DFS_n_reines.solve(i)));
           
           end =System.currentTimeMillis();
           
           System.out.println("temps de calcul " +(end-start)+" (ms)\n"); 
           
           
           //for (int[] solution : solutions) {
          start=0;
          end=0;
       }*/     
            
           
           
    }
}
/*dfs 11

nombre des noeuds generes: 1981694645
nombre des noeuds explores: 1981694591

temps de calcul 626.582 (s)

solution:  [10, 8, 6, 4, 2, 0, 9, 7, 5, 3, 1]
*/
