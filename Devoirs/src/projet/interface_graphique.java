package projet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.JButton;

public class interface_graphique {

	private JFrame frmNqueen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interface_graphique window = new interface_graphique();
					window.frmNqueen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public interface_graphique() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNqueen = new JFrame();
		frmNqueen.setTitle("N-Queen");
		frmNqueen.setFont(new Font("Maiandra GD", Font.PLAIN, 13));
		frmNqueen.getContentPane().setBackground(SystemColor.controlHighlight);
		frmNqueen.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		frmNqueen.setBackground(new Color(204, 255, 255));
		frmNqueen.setBounds(100, 100, 842, 609);
		frmNqueen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
