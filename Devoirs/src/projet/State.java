package projet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;



public  class State {
    int[] queens; // positions des reines
    int nextRow; // prochaine ligne pour placer une reine
    public int f;       //fonction d'evaluation

    public State(int n,int h) {
        queens = new int[n];
        Arrays.fill(queens, -1);
        nextRow = 0;
        this.f=h;
    }
    public State(int n,int h,int[] l) {
        queens = new int[n];
       // Arrays.fill(queens, -1);
        queens=l;
        nextRow = 0;
        this.f=h;
    }

    public State(State s,int h) {
        queens = Arrays.copyOf(s.queens, s.queens.length);
        nextRow = s.nextRow;
        this.f=h;
    }

    public boolean isValid() {
    	
    	
    	for (int i = 0; i < queens.length-1; i++) {
    	
        for (int j = i+1; j < queens.length; j++) {
        	
            if (queens[i] == queens[j] || queens[i] - i == queens[j] - j || queens[i] + i == queens[j] + j) {
        	
                return false;
            }
        }
        
    }
    	return true;
    }
    
    
    
    
    
    
    
   /* public boolean Valid_move(int col,int line ) {
    	
        for (int i = 0; i < line; i++) {
            if (queens[i] == col || queens[i] - i == col - line || queens[i] + i == col + line) {
        	//if (queens[i] == col ) {
                return false;
            }
        }
        return true;
    }*/
    
    
     public boolean Valid_move(int col,int line ,int stop ) {
    	
        for (int i = 0; i < stop; i++) {
            if (queens[i] == col || queens[i] - i == col - line || queens[i] + i == col + line) {
        	//if (queens[i] == col ) {
                return false;
            }
        }
        return true;
    }

    public List<State> get_children() {
        List<State> nextStates = new ArrayList<>();
        for (int col = 0; col < queens.length; col++) {
            //if (Valid_move(col)) {
                State nextState = new State(this,0);
                nextState.queens[nextRow] = col;
                nextState.nextRow = nextRow + 1;
                nextStates.add(nextState);
           // }
        }
        return nextStates;
    }

    public boolean isComplete() {
        return nextRow == queens.length;
    }
    
    public void set_f(int h) {
        this.f=h;
    }
    public int get_f() {
        return this.f;
    }
    
    
    
    
    
    public static Comparator<State> heuristics_sort = new Comparator<State>() {
		 
        // Method
        public int compare(State s1, State s2) {
 
            int f1 = s1.f;
            int f2 = s2.f;
 
            // For ascending order
            return f1 - f2;
 
            // For descending order
           // return f2-f1;
        }
    };
    public static Comparator<State> heuristics_sort2 = new Comparator<State>() {
		 
        // Method
        public int compare(State s1, State s2) {
 
            int f1 = s1.f;
            int f2 = s2.f;
 
            // For ascending order
           // return f1 - f2;
 
            // For descending order
           return f2-f1;
        }
    };
    
    
    public  int heuristique1() {//heuristic that counts each queen's conflicts
    	
		 int i=0;
		 
		 int h=0;
		 
		  

	    	while((i<queens.length) && (queens[i]!= -1 ) ) {
	    	
	    	int j=0;	
	    		
	        while ((j<queens.length) && (queens[j]!=-1)  ) {
	        	
	        	if(j!=i) {
	            if (queens[i] == queens[j] || queens[i] - i == queens[j] - j || queens[i] + i == queens[j] + j) {
	        	
	                h++;
	                
	               // System.out.println("conflict between line : "+i+" col:"+queens[i]+" and line "+j+" col "+queens[j]);
	                
	            }
	        	}
	            j++;
	        }
	        
	        i++;
	    }
	    	
	    	return h;
	   }
    
    public  int heuristique2() {//heuristic that counts possible valid moves
    	
		 int i=0;
		 
		 int queens_left=0;	
		 
		 int h=0;
		 
		    while((i<queens.length) && (queens[i]!= -1 ) ) i++;
		    
		  //  System.out.println("i == "+i);
		    
		    
		    
		    if (i!=queens.length) {
		    
		    	int stop=i;
		    	
		    queens_left=queens.length - i;	

	    	while(i<queens.length) {
	    	
	    		
	       for(int j=0 ; j<queens.length ; j++) {
	        	
	        	
	            if (this.Valid_move(j, i,stop)) {
	        	
	                h++;
	              
	        	}
	            
	        }
	        
	        i++;
	    }
		    }
		    
		   // if(queens_left>=h) 
		    	return h;
		    
		    //return 0;
	   }
    
    
   
}
