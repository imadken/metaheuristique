package projet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel labell = new JLabel("");
		frame.getContentPane().add(labell, BorderLayout.WEST);
		
		JButton kafla = new JButton("kafla");
		kafla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				labell.setText("benkouiten etterma");
			
				
			}
		});
		frame.getContentPane().add(kafla, BorderLayout.SOUTH);
	}

}
