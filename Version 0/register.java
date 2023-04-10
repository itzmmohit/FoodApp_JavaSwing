import java.sql.Connection;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class register extends JFrame {

	private JPanel contentPane;
	private JPasswordField updpass;
	private JPasswordField updconpass;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 69, 69));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				homepage hobj=new homepage();
				hobj.show();
				dispose();
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\home (1).png"));
		lblNewLabel_1.setBounds(995, 25, 48, 49);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(358, 141, 304, 286);
		contentPane.add(panel);
		panel.setLayout(null);
		
		updpass = new JPasswordField();
		updpass.setToolTipText("Password");
		updpass.setBounds(138, 150, 109, 19);
		panel.add(updpass);
		
		JTextPane updusername = new JTextPane();
		updusername.setBackground(new Color(255, 255, 255));
		updusername.setBounds(138, 127, 109, 20);
		panel.add(updusername);
		
		JTextPane updemail = new JTextPane();
		updemail.setBackground(Color.WHITE);
		updemail.setBounds(138, 103, 109, 20);
		panel.add(updemail);
		
		JTextPane updmobile = new JTextPane();
		updmobile.setBackground(Color.WHITE);
		updmobile.setBounds(138, 79, 109, 20);
		panel.add(updmobile);
		
		JTextPane updname = new JTextPane();
		updname.setBackground(Color.WHITE);
		updname.setBounds(138, 55, 109, 20);
		panel.add(updname);
		
		updconpass = new JPasswordField();
		updconpass.setToolTipText("Password");
		updconpass.setBounds(138, 185, 109, 19);
		panel.add(updconpass);
		
		JTextArea txtrConfirmPassword = new JTextArea();
		txtrConfirmPassword.setText("Confirm\r\nPassword");
		txtrConfirmPassword.setForeground(Color.WHITE);
		txtrConfirmPassword.setBackground(Color.BLACK);
		txtrConfirmPassword.setBounds(48, 172, 67, 40);
		panel.add(txtrConfirmPassword);
		
		JLabel RegisterLabel = new JLabel("Register");
		RegisterLabel.setForeground(Color.WHITE);
		RegisterLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		RegisterLabel.setBounds(109, 10, 78, 22);
		panel.add(RegisterLabel);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setLabelFor(updpass);
		PasswordLabel.setForeground(Color.WHITE);
		PasswordLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		PasswordLabel.setBounds(49, 150, 67, 22);
		panel.add(PasswordLabel);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setLabelFor(updusername);
		UsernameLabel.setForeground(Color.WHITE);
		UsernameLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		UsernameLabel.setBounds(49, 125, 67, 22);
		panel.add(UsernameLabel);
		
		JLabel EmailLabel = new JLabel("Email");
		EmailLabel.setLabelFor(updemail);
		EmailLabel.setForeground(Color.WHITE);
		EmailLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		EmailLabel.setBounds(49, 100, 67, 22);
		panel.add(EmailLabel);
		
		JLabel MobileLabel = new JLabel("Mobile");
		MobileLabel.setLabelFor(updmobile);
		MobileLabel.setForeground(Color.WHITE);
		MobileLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		MobileLabel.setBounds(49, 75, 67, 22);
		panel.add(MobileLabel);
		
		JLabel NameLabel = new JLabel("Name");
		NameLabel.setLabelFor(updname);
		NameLabel.setForeground(new Color(255, 255, 255));
		NameLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		NameLabel.setBounds(49, 53, 67, 22);
		panel.add(NameLabel);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Open Connection
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
					// Database name is Login
					// username of mysql is root
					// Password = 
					System.out.println("Connected from register");
					String name = updname.getText();
					String mob = updmobile.getText();
					String email = updemail.getText();
					String username = updusername.getText();
					String pass = updpass.getText();
					String conpass = updconpass.getText();
					System.out.println(pass + " " +conpass);
					if(pass.equals(conpass)) {
						Statement stm=con.createStatement();
						//mysql query to run
						String sql = "INSERT INTO login (name, phone, email, username, password, confirm_password) VALUES ('"+name+"','"+mob+"','"+email+"','"+username+"','"+pass+"','"+conpass+"')";
						int rowsAffected = stm.executeUpdate(sql);
						
						login lobj=new login();
						lobj.show();
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "password doesn't match confirm password..");
						updpass.setText("");
						updconpass.setText("");
					}
					
					
				}catch(Exception es) {
					System.out.println(es.getMessage());
				}
			}
		});
		btnRegister.setBounds(69, 241, 78, 21);
		panel.add(btnRegister);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updname.setText("");
				updmobile.setText("");
				updemail.setText("");
				updusername.setText("");
				updpass.setText("");
				updconpass.setText("");
			}
		});
		btnReset.setBounds(157, 241, 78, 21);
		panel.add(btnReset);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\Back_Main.png"));
		lblNewLabel.setBounds(0, 0, 1079, 580);
		contentPane.add(lblNewLabel);
		
	}
}
