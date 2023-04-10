import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class homepage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homepage frame = new homepage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public homepage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 222, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton NextButton = new JButton("Shall we move Ahead>>");
		NextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login lobj=new login();
				lobj.show();
				dispose();
			}
		});
		NextButton.setBounds(430, 295, 169, 29);
		contentPane.add(NextButton);
		
		JTextArea WelcomeText = new JTextArea();
		WelcomeText.setBackground(new Color(255, 255, 255));
		WelcomeText.setFont(new Font("Old English Text MT", Font.PLAIN, 30));
		WelcomeText.setText("Welcome to the World of Foods");
		WelcomeText.setBounds(334, 251, 387, 45);
		contentPane.add(WelcomeText);
		
		JTextArea NameLabel = new JTextArea();
		NameLabel.setTabSize(4);
		NameLabel.setText("Personal Project : --\r\n\tMohit Mehta\r\n\t2247267");
		NameLabel.setBackground(new Color(255, 255, 255));
		NameLabel.setBounds(841, 476, 180, 61);
		contentPane.add(NameLabel);
		
		JLabel Bg = new JLabel("");
		Bg.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\Back_Main.png"));
		Bg.setBounds(0, -50, 1205, 704);
		contentPane.add(Bg);
		
	}
}
