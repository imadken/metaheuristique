package projet;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ChessBoardGUI extends JFrame {
	
	int n=8;

    private JPanel chessBoard;
    private JButton[][] chessSquares ;
    
   // private ImageIcon queen_icon =  new ImageIcon("D:/M1 SII/Metaheuristiques/tp/queen2.png");
    private ImageIcon queen_icon =  new ImageIcon(new ImageIcon("D:/M1 SII/Metaheuristiques/tp/queen2.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));

    public ChessBoardGUI() {
    	
    	
        super("N-Queen");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        chessSquares = new JButton[n][n];
        
        JPanel controlsPanel = new JPanel(new FlowLayout());
        
        JLabel numberLabel = new JLabel("Enter a number: ");
        
        //JTextField numberField = new JTextField(10);
        SpinnerModel spinnerModel = new SpinnerNumberModel(4, 4, 8, 1); // initial value, min value, max value, step size
        JSpinner numberField = new JSpinner(spinnerModel);

        
        
        JButton startButton = new JButton("Start");
        
        String[] choices = { "DFS", "BFS", "A* -1-", "A* -2-" };
        
        JComboBox<String> comboBox = new JComboBox<>(choices);
        
        JLabel nodesGeneratedLabel = new JLabel("Noeuds generes: ");
        JLabel nodesExploredLabel = new JLabel("Noeuds explores: ");
        JLabel temps_ecoule = new JLabel("temps d'execution: ");

        startButton.addActionListener(e -> {
            String algo = comboBox.getSelectedItem().toString();
        	
        	int N = (int) spinnerModel.getValue();
        	
        	
        	//{ "DFS", "BFS", "A* -1-", "A* -2-" };
        	
        	switch (algo) {
        	
        	case "DFS": System.out.println("dfs \n"); break;
        	
        	case "BFS":System.out.println("bfs \n");break;
        	
        	case "A* -1-":System.out.println("a* \n");break;
        	
        	case "A* -2-":System.out.println("a* \n");break;
        	
        	case "A* -3-":System.out.println("a* \n");break;
        	
           
        	
        	
        	}
        	/*Frame[] frames = Frame.getFrames();
        	for (Frame frame : frames) {
        	    if (frame instanceof JFrame) {
        	        frame.dispose();
        	    }
        	}*/
        	
        	
            /*int number = (int)numberField.getValue();
            temps_ecoule.setText("temps d'execution: "+number);
            // your code to generate and explore nodes goes here
            int nodesGenerated = 10; // for example
            int nodesExplored = 5; // for example
            nodesGeneratedLabel.setText("Nodes generated: " + nodesGenerated);
            nodesExploredLabel.setText("Nodes explored: " + nodesExplored);*/
        });

        controlsPanel.add(numberLabel);
        controlsPanel.add(numberField);
        controlsPanel.add(startButton);
        controlsPanel.add(comboBox);
        controlsPanel.add(nodesGeneratedLabel);
        controlsPanel.add(nodesExploredLabel);

        add(controlsPanel, BorderLayout.NORTH);

        chessBoard = new JPanel(new GridLayout(n, n));
       
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessSquares[i][j] = new JButton();
                chessSquares[i][j].setPreferredSize(new Dimension(64, 64));
                if ((i + j) % 2 == 0) {
                    chessSquares[i][j].setBackground(Color.WHITE);
                    //chessSquares[i][j].setIcon(queen_icon);
                    
                    
                } else {
                    chessSquares[i][j].setBackground(Color.GRAY);
                }
                chessBoard.add(chessSquares[i][j]);
            }
        }
        add(chessBoard, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(temps_ecoule);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
    
    ////////////////////////////////////////////
    
    
    public ChessBoardGUI(int n ,int nbr_gen ,int nbr_exp, long temps ,int[] sol) {
        super("N-Queen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        chessSquares = new JButton[n][n];
        
        JPanel controlsPanel = new JPanel(new FlowLayout());
        
        JLabel numberLabel = new JLabel("Entrer un nombre: ");
        
        //JTextField numberField = new JTextField(10);
        SpinnerModel spinnerModel = new SpinnerNumberModel(4, 4, 10, 1); // initial value, min value, max value, step size
        JSpinner numberField = new JSpinner(spinnerModel);

        
        
        JButton startButton = new JButton("Start");
        
        String[] choices = { "DFS", "BFS", "A* -1-", "A* -2-" ,"A* -3-" };
        
        JComboBox<String> comboBox = new JComboBox<>(choices);
        
        JLabel nodesGeneratedLabel = new JLabel("Noeuds generes: "+nbr_gen);
        JLabel nodesExploredLabel = new JLabel("Noeuds explores: "+nbr_exp);
        JLabel temps_ecoule = new JLabel("temps d'execution: "+temps+" (ms)");

        startButton.addActionListener(e -> {
        	
        	
        	String algo = comboBox.getSelectedItem().toString();
        	
        	int N = (int) spinnerModel.getValue();
        	
        	
        	//{ "DFS", "BFS", "A* -1-", "A* -2-" };
        	
        	//startButton.disable();
        	
        	int gen=0,exp=0;
        	
        	long start,end ,total=0;
        	int[] solution=sol;
        	
        	
            switch (algo) {
        	
        	case "DFS": { 
        		          
        		start =System.currentTimeMillis();
    	        
    	        solution = DFS_n_reines.solve(N);
    	        
    	        end =System.currentTimeMillis();
    	        
    	        gen=DFS_n_reines.nbr_nodes_gen;
    	        exp=DFS_n_reines.nbr_nodes_exp;
    	        total=end-start;
        		
        		break;
        		
        	
        	}
        	
        	case "BFS": {
        		
        	start =System.currentTimeMillis();
	        
	        solution = BFS_n_reines.solve(N);
	        
	        end =System.currentTimeMillis();
	        
	        gen=BFS_n_reines.nbr_nodes_gen;
	        exp=BFS_n_reines.nbr_nodes_exp;
	        total=end-start;	
        		
        		break;}
        	
        	case "A* -1-":{
        		
        		start =System.currentTimeMillis();
    	        
    	        solution = A_star_1.solve(N);
    	        
    	        end =System.currentTimeMillis();
    	        
    	        gen=A_star_1.nbr_nodes_gen;
    	        exp=A_star_1.nbr_nodes_exp;
    	        total=end-start;
    	        
        		break;}
        	
        	case "A* -2-":{ 
                start =System.currentTimeMillis();
    	        
    	        solution = A_star_2.solve(N);
    	        
    	        end =System.currentTimeMillis();
    	        
    	        gen=A_star_2.nbr_nodes_gen;
    	        exp=A_star_2.nbr_nodes_exp;
    	        total=end-start;
        		
        		break;}
        	
        	case "A* -3-":{
        		
                start =System.currentTimeMillis();
    	        
    	        solution = A_star_3.solve(N);
    	        
    	        end =System.currentTimeMillis();
    	        
    	        gen=A_star_3.nbr_nodes_gen;
    	        exp=A_star_3.nbr_nodes_exp;
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
        	new ChessBoardGUI(N,gen,exp,total,solution);
        
        
        
        
        
        });

        controlsPanel.add(numberLabel);
        controlsPanel.add(numberField);
        controlsPanel.add(startButton);
        controlsPanel.add(comboBox);
        controlsPanel.add(nodesGeneratedLabel);
        controlsPanel.add(nodesExploredLabel);

        add(controlsPanel, BorderLayout.NORTH);

        chessBoard = new JPanel(new GridLayout(n, n));
       
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
        bottomPanel.add(temps_ecoule);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        //new ChessBoardGUI();
    	
    	//int[] t= {-1,-1,-1,-1,-1,-1,-1,-1};
    	int[] t= {0, 4, 7, 5, 2, 6, -1,-1};
        new ChessBoardGUI(8,0,0,0,t);
    }
}
