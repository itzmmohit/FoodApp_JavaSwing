import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin_AddFood extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Price;
	private JTextField Image;
	private JTextField Image_Pay;
	private JLabel lblNewLabel;
	private JLabel lblCost;
	private JLabel lblImageLink;
	private JLabel lblImagePay;
	private JLabel HomeLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_AddFood frame = new Admin_AddFood();
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
	public Admin_AddFood() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		HomeLabel = new JLabel("");
		HomeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login lobj=new login();
				lobj.show();
				dispose();
			}
		});
		HomeLabel.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\home (1).png"));
		HomeLabel.setBounds(995, 10, 48, 49);
		contentPane.add(HomeLabel);
		
		lblImagePay = new JLabel("Image Pay");
		lblImagePay.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblImagePay.setBackground(Color.CYAN);
		lblImagePay.setBounds(161, 370, 77, 13);
		contentPane.add(lblImagePay);
		
		lblImageLink = new JLabel("Image Link");
		lblImageLink.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblImageLink.setBackground(Color.CYAN);
		lblImageLink.setBounds(161, 304, 77, 13);
		contentPane.add(lblImageLink);
		
		lblCost = new JLabel("Cost");
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCost.setBackground(Color.CYAN);
		lblCost.setBounds(161, 233, 77, 13);
		contentPane.add(lblCost);
		
		lblNewLabel = new JLabel("Food Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(161, 172, 77, 13);
		contentPane.add(lblNewLabel);
		
		Image_Pay = new JTextField("C:\\\\Users\\\\mmmeh\\\\OneDrive\\\\Desktop\\\\3rd Trimester\\\\Java Img\\\\");
		lblImagePay.setLabelFor(Image_Pay);
		Image_Pay.setColumns(10);
		Image_Pay.setBounds(273, 362, 578, 30);
		contentPane.add(Image_Pay);
		
		Image = new JTextField("C:\\\\Users\\\\mmmeh\\\\OneDrive\\\\Desktop\\\\3rd Trimester\\\\Java Img\\\\");
		lblImageLink.setLabelFor(Image);
		Image.setColumns(10);
		Image.setBounds(273, 296, 578, 30);
		contentPane.add(Image);
		
		Price = new JTextField();
		lblCost.setLabelFor(Price);
		Price.setColumns(10);
		Price.setBounds(273, 225, 578, 30);
		contentPane.add(Price);
		
		Name = new JTextField();
		lblNewLabel.setLabelFor(Name);
		Name.setBounds(273, 164, 578, 30);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JButton Add = new JButton("Add Product");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Open Connection
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
					// Database name is Login
					// username of mysql is root
					// Password = 
					
					
					System.out.println("Connected from Add Product");
					String fname=Name.getText();
					String p=Price.getText();
					String im=Image.getText();
					String impy=Image_Pay.getText();
					Statement stm=con.createStatement();
					//mysql query to run
					if(fname!="" && p!="" && im!="" && impy!="") {
						String sql = "INSERT INTO foodbase (food, cost, img, img20) VALUES ('"+fname+"','"+p+"','"+im+"','"+impy+"')";
						int rowsAffected = stm.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Product Added Successfully..");
						Name.setText("");
						Price.setText("");
						Image.setText("");
						Image_Pay.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid Values..");
						Name.setText("");
						Price.setText("");
						Image.setText("");
						Image_Pay.setText("");
					}
					
				}catch(Exception es) {
					System.out.println(es.getMessage());
				}
			}
		});
		Add.setBounds(483, 434, 108, 30);
		contentPane.add(Add);
		
		JLabel Bg = new JLabel("");
		Bg.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\Back_Main.png"));
		Bg.setBounds(0, 0, 1067, 597);
		contentPane.add(Bg);
	}
}
