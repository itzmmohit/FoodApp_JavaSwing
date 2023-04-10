import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JPasswordField updpass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 69, 69));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel HomeLabel = new JLabel("");
		HomeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				homepage hobj=new homepage();
				hobj.show();
				dispose();
			}
		});
		HomeLabel.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\home (1).png"));
		HomeLabel.setBounds(995, 14, 48, 49);
		contentPane.add(HomeLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(423, 176, 202, 242);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel PassLabel = new JLabel("Password");
		PassLabel.setForeground(Color.WHITE);
		PassLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		PassLabel.setBounds(65, 95, 67, 22);
		panel.add(PassLabel);
		
		JLabel UserNameLabel = new JLabel("Username");
		UserNameLabel.setForeground(new Color(255, 255, 255));
		UserNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		UserNameLabel.setBounds(65, 52, 67, 22);
		panel.add(UserNameLabel);
		
		updpass = new JPasswordField();
		PassLabel.setLabelFor(updpass);
		updpass.setToolTipText("Password");
		updpass.setBounds(22, 113, 159, 22);
		panel.add(updpass);
		
		JTextPane updusername = new JTextPane();
		UserNameLabel.setLabelFor(updusername);
		updusername.setBackground(new Color(255, 255, 255));
		updusername.setBounds(22, 71, 159, 22);
		panel.add(updusername);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
					System.out.println("Connected from login");
					String username = updusername.getText();
					String pass = updpass.getText();
					Statement stm=con.createStatement();
				    //mysql query to run
				    String sql = "select * from login where username='"+username+"' and password='"+pass+"'";
				    ResultSet rs = stm.executeQuery(sql);
				    
				    Statement stm1=con.createStatement();
				    //mysql query to run for just username to give bit customized message
				    String sql1 = "select * from login where username='"+username+"'";
				    ResultSet rs1 = stm1.executeQuery(sql1);
				    if(username.equals("admin") && pass.equals("admin")) {
			    		Admin_AddFood aobj = new Admin_AddFood();
			    		aobj.show();
			    		dispose();
			    	}else if(rs.next()) {
			    		try {
			    		    Class.forName("com.mysql.jdbc.Driver");
			    		    Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
			    		    Statement stm11=con11.createStatement();

			    		    // MySQL query to add a new column
			    		    String sql11 = "ALTER TABLE login ADD COLUMN log VARCHAR(255)";
			    		    stm11.executeUpdate(sql11);
			    		    stm11.close();

			    		    Statement stm12=con11.createStatement();
			    		    // MySQL query to update column
			    		    String sql12 = "UPDATE login SET log = 'login' WHERE username = '"+username+"'";
			    		    stm12.executeUpdate(sql12);
			    		    stm12.close();

			    		    con11.close(); // Close the connection

			    		} catch(Exception ex) {			
			    			//Checking same user			    			
			    			try {
			    				System.out.println("Last Time Pending");
			    				Class.forName("com.mysql.jdbc.Driver");Connection con111 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
				    			Statement stm111=con111.createStatement();
					    		// MySQL query to add a new column
					    		String sql111 = "select * from login where log='login'";
					    		ResultSet rs111 =stm111.executeQuery(sql111);
					    		if(rs111.next()) {
					    			if(rs111.getString("username").equals(username)) {
					    				Class.forName("com.mysql.jdbc.Driver");Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
						    			Statement stm11=con11.createStatement();
							    		// MySQL query to add a new column
							    		String sql11 = "ALTER TABLE login DROP COLUMN `log`";
							    		stm11.executeUpdate(sql11);
							    		stm11.close();
							    		
							    		Statement stm12=con11.createStatement();
						    		    // MySQL query to add a new column
						    		    String sql12 = "ALTER TABLE login ADD COLUMN log VARCHAR(255)";
						    		    stm12.executeUpdate(sql12);
						    		    stm12.close();
						    			
						    			Statement stm13=con11.createStatement();
						    		    // MySQL query to update column
						    		    String sql13 = "UPDATE login SET log = 'login' WHERE username = '"+username+"'";
						    		    stm13.executeUpdate(sql13);
						    		    stm13.close();
						    		    con11.close();
						    		    System.out.println("Match Check Succesful for Incorrect Closing");		    		    
					    			}
					    			else {
					    				Class.forName("com.mysql.jdbc.Driver");Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
						    			Statement stm11=con11.createStatement();
							    		// MySQL query to add a new column
							    		String sql11 = "ALTER TABLE login DROP COLUMN `log`";
							    		stm11.executeUpdate(sql11);
							    		stm11.close();
							    		
							    		Statement stm12=con11.createStatement();
						    		    // MySQL query to add a new column
						    		    String sql12 = "ALTER TABLE login ADD COLUMN log VARCHAR(255)";
						    		    stm12.executeUpdate(sql12);
						    		    stm12.close();
						    			
						    			Statement stm13=con11.createStatement();
						    		    // MySQL query to update column
						    		    String sql13 = "UPDATE login SET log = 'login' WHERE username = '"+username+"'";
						    		    stm13.executeUpdate(sql13);
						    		    stm13.close();
						    		    
						    		    Statement stm14=con11.createStatement();
						    		    // MySQL query to update column
						    		    String sql14 = "ALTER TABLE foodbase DROP COLUMN `cart`";
						    		    stm14.executeUpdate(sql14);
						    		    stm14.close();
						    		    con11.close();
						    		    System.out.println("Unmatch Check Succesful for Incorrect Closing");
					    			}
					    		}
					    		stm111.close();
			    			}catch(Exception es) {
			    				System.out.println(es.getMessage());
			    			}   
			    			System.out.println(ex.getMessage());
			    		}		    		
				        //if username and password is true than go to homepage
			    		product_home pobj = new product_home(username);
			    		pobj.show();
						pobj.cartupd();
						pobj.srch();
						pobj.product_list();
				        dispose();
				    } else if(rs1.next()) {
				        JOptionPane.showMessageDialog(null, "Incorrect Password..");
				        updpass.setText("");
				    } else {
				        // if username and password is wrong show message
				        JOptionPane.showMessageDialog(null, "User Not Found..");
				        updusername.setText("");
				        updpass.setText("");
				    }
					con.close();		
				}catch( Exception es) {
					System.out.println(es.getMessage());
				}
			}
		});
		btnNewButton.setBounds(63, 149, 78, 21);
		panel.add(btnNewButton);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register robj=new register();
				robj.show();
				dispose();
			}
		});
		btnRegister.setBounds(63, 177, 78, 21);
		panel.add(btnRegister);
		
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updusername.setText("");
				updpass.setText("");
			}
		});
		btnReset.setBounds(63, 206, 78, 21);
		panel.add(btnReset);
		
		JLabel LogibLabel = new JLabel("Login");
		LogibLabel.setForeground(Color.WHITE);
		LogibLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
		LogibLabel.setBounds(63, 5, 78, 32);
		panel.add(LogibLabel);
		
		JLabel Bg = new JLabel("New label");
		Bg.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\Back_Main.png"));
		Bg.setBounds(-12, 0, 1079, 580);
		contentPane.add(Bg);
		
	}
}
