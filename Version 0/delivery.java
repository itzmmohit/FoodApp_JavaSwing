import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class delivery extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					delivery frame = new delivery();
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
	public delivery() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Drop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
					// Database name is Login
					// username of mysql is root
					// Password = 
					System.out.println("Connected from Delivery Page");
					
					Statement stm1=con.createStatement();
				    //mysql to create order table for product side
				    String sql1 = "drop table cart";
				    stm1.executeUpdate(sql1);
				    stm1.close();
				    
				    JOptionPane.showMessageDialog(null, "Table Deleted..");
				    product_home pobj = new product_home();
			        pobj.show();
			        pobj.cartupd();
			        pobj.product_hom();
			        dispose();
				    
				}catch(Exception es) {
					System.out.println(es.getMessage());
				}
			}
		});
		btnNewButton.setBounds(457, 252, 85, 21);
		contentPane.add(btnNewButton);
	}
}
