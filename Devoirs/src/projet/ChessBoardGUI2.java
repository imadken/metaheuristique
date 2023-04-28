package projet;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ChessBoardGUI2 extends JFrame {
	
	int n=8;

    //private JPanel chessBoard;
    //private JButton[][] chessSquares ;
    
   // private ImageIcon queen_icon =  new ImageIcon("D:/M1 SII/Metaheuristiques/tp/queen2.png");
    private ImageIcon queen_icon =  new ImageIcon(new ImageIcon("D:/M1 SII/Metaheuristiques/tp/queen2.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));

   
    
    ////////////////////////////////////////////
    
    
    public ChessBoardGUI2(int n ,int pop_size ,int max_gen,double rate_cross,double rate_mutation , long temps ,int[] sol) {
        super("N-Queen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       JButton[][] chessSquares = new JButton[n][n];
        
        JPanel controlsPanel = new JPanel(new FlowLayout());
        
        
        
        //JTextField numberField = new JTextField(10);
        SpinnerModel spinnerModelqueen = new SpinnerNumberModel(n, 4, 14, 1); // initial value, min value, max value, step size
        JSpinner numberField = new JSpinner(spinnerModelqueen);
        
        SpinnerModel spinnerModelpop = new SpinnerNumberModel(pop_size, 50, 1000, 50); // initial value, min value, max value, step size
        JSpinner popsizeField = new JSpinner(spinnerModelpop);
  
        SpinnerModel spinnerModelgen = new SpinnerNumberModel(max_gen, 100, 1000000, 100); // initial value, min value, max value, step size
        JSpinner maxgenField = new JSpinner(spinnerModelgen);

        SpinnerModel spinnerModelcross = new SpinnerNumberModel(rate_cross, 0.1, 1.0, 0.1); // initial value, min value, max value, step size
        JSpinner ratecrossField = new JSpinner(spinnerModelcross);
       
        SpinnerModel spinnerModelmuta = new SpinnerNumberModel(rate_mutation, 0.1, 1.0, 0.1); // initial value, min value, max value, step size
        JSpinner ratemutationField = new JSpinner(spinnerModelmuta);
        
        JButton startButton = new JButton("Start");
        
        String[] choices = { "GA", "PSO"};
        JComboBox<String> comboBox = new JComboBox<>(choices);
        
        JLabel numberLabel = new JLabel("Queens");
        
        JLabel popsizeLabel = new JLabel("PopSize");
        JLabel maxgenLabel = new JLabel("MaxGen");
        
        JLabel ratecrossLabel = new JLabel("CrossRate");
        JLabel ratemutaLabel = new JLabel("MutationRate");
        
        JLabel temps_ecoule = new JLabel("temps d'execution: "+temps+" (ms)");

        
        startButton.addActionListener(e -> {

        	String algo = comboBox.getSelectedItem().toString();
        	
        	int N = (int) spinnerModelqueen.getValue();
        	int population_size = (int) spinnerModelpop.getValue();
        	int max_generations = (int) spinnerModelgen.getValue();
        	double crossover_rate = (double) spinnerModelcross.getValue();
        	double mutation_rate = (double) spinnerModelmuta.getValue();
        	
        	long start,end ,total=0;
        	int[] solution=sol;
        	
        	
            switch (algo) {
        	
        	case "GA": { 
        		
        		          
        		start =System.currentTimeMillis();
    	        
        		Genetic_Algo2 GA = new Genetic_Algo2(N ,pop_size ,max_gen ,rate_cross,rate_mutation);
        		solution = GA.solve();
    	        
    	        end =System.currentTimeMillis();
    	       
    	        
    	        total=end-start;
        		
        		break;
        		
        	
        	}
        	
        	case "PSO": {
        		
        	start =System.currentTimeMillis();
	        
        	PSO pso = new PSO(N, pop_size, max_gen,0.9,2.0 ,2.0);
	        solution = pso.solve();
	        
	        end =System.currentTimeMillis();
	        
	      

	        total=end-start;	
        		
        		break;}
        
        	}
            
            System.out.println("solution:  "+Arrays.toString(solution));
            
            
            
            //destroy all frames
        	Frame[] frames = Frame.getFrames();
        	for (Frame frame : frames) {
        	    if (frame instanceof JFrame) {
        	        frame.dispose();
        	    }
        	}
        	
           //new window 
        	new ChessBoardGUI2(N,population_size,max_generations,crossover_rate,mutation_rate,total,solution);
        
        
        });

        controlsPanel.add(numberLabel);    
        controlsPanel.add(numberField);
        
        
        controlsPanel.add(popsizeLabel);    
        controlsPanel.add(popsizeField);
        
        controlsPanel.add(maxgenLabel);    
        controlsPanel.add(maxgenField);
        
        
        controlsPanel.add(comboBox);
        
        controlsPanel.add(startButton);
        
        

        add(controlsPanel, BorderLayout.NORTH);

        JPanel  chessBoard = new JPanel(new GridLayout(n, n));
       
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	
                chessSquares[i][j] = new JButton();
                chessSquares[i][j].setPreferredSize(new Dimension(64, 64));

                if(sol[i]==j) chessSquares[i][j].setIcon(queen_icon);
                
                else {
                if ((i + j) % 2 == 0) {
                	
                    chessSquares[i][j].setBackground(Color.WHITE);
                    //chessSquares[i][j].setIcon(queen_icon);
  
                } else {
                    chessSquares[i][j].setBackground(Color.GRAY);
                    //chessSquares[i][j].setIcon(queen_icon);
                }
                }
                chessBoard.add(chessSquares[i][j]);
            }
        }
        add(chessBoard, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        
        bottomPanel.add(ratecrossLabel);
        bottomPanel.add(ratecrossField);
        
        bottomPanel.add(ratemutaLabel);
        bottomPanel.add(ratemutationField);
   
        bottomPanel.add(temps_ecoule);
        
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        //new ChessBoardGUI2();
    	//int[] t = {15, 6, 8, 14, 1, 18, 16, 9, 0, 4, 17, 7, 2, 12, 5, 13, 10, 3, 11, 19};//15, 6, 8, 14, 1, 18, 16, 9, 0, 4, 17, 7, 2, 12, 5, 13, 10, 3, 11, 19
    	//int[] t= {3, 1, 7, 5, 0, 6, 4, 0};
    	int[] t = {-1,-1,-1,-1};
    	//int[] t = {-1,-1,-1,-1,-1,-1,-1,-1};
    	//int[] t= {0, 0, 0, 0, 2, 6, -1,-1};
        new ChessBoardGUI2(t.length,100,1000,0.1,0.1,0,t);
    }
}
