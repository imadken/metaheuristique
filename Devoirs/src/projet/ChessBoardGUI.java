package projet;
import javax.swing.*;
import java.awt.*;

public class ChessBoardGUI extends JFrame {
	
	int n=8;

    private JPanel chessBoard;
    private JButton[][] chessSquares = new JButton[n][n];
    
    private ImageIcon queen_icon =  new ImageIcon("D:/M1 SII/Metaheuristiques/tp/queen.png");

    public ChessBoardGUI() {
        super("N-Queen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

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
        	
        	/*Frame[] frames = Frame.getFrames();
        	for (Frame frame : frames) {
        	    if (frame instanceof JFrame) {
        	        frame.dispose();
        	    }
        	}*/
        	
        	
            int number = (int)numberField.getValue();
            temps_ecoule.setText("temps d'execution: "+number);
            // your code to generate and explore nodes goes here
            int nodesGenerated = 10; // for example
            int nodesExplored = 5; // for example
            nodesGeneratedLabel.setText("Nodes generated: " + nodesGenerated);
            nodesExploredLabel.setText("Nodes explored: " + nodesExplored);
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
                   // chessSquares[i][j].setIcon(queen_icon);
                    
                    
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

    public static void main(String[] args) {
        new ChessBoardGUI();
    }
}
