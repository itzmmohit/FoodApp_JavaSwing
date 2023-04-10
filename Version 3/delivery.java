import java.awt.EventQueue;
import java.awt.Panel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

public class delivery extends JFrame {

	private JPanel contentPane;
	private JTextField Street1;
	private JTextField Street2;
	private JTextField City;
	private JTextField State;
	private JTextField zip;
	private JTextField Country;
	boolean listenersEnabled = true;
	boolean listenersEnabled1 = true;
	boolean user = false;
	boolean address = true;
	private JTextField Dfee;
	private JTextField Stotal;
	private JTextField Taxes;
	private JTextField Total;
	private Panel PaySummary;  //cart global
	private JPanel panel;      //cart global
	private JTextField ID;
	private JTextField Name;
	private JTextField no;
	private JTextField email;
	int i; //for message box resizing

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					delivery frame = new delivery();
					frame.cartupd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public delivery() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JButton btnNewButton = new JButton("Drop");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
		
//		Panel PaySummary = new Panel();
//		PaySummary.setBackground(new Color(255, 255, 255));
//		PaySummary.setBounds(830, 28, 199, 529);
//		contentPane.add(PaySummary);
//		PaySummary.setLayout(null);
		
		

		JPanel UserDetails = new JPanel();
		// Add MouseListener to UserDetails button
		UserDetails.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseExited(MouseEvent e) {
		        if (listenersEnabled) {
		            UserDetails.setBackground(new Color(255, 255, 255));
		        }
		    }
		});
		// Add MouseMotionListener to UserDetails button
		UserDetails.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseMoved(MouseEvent e) {		
		        if (listenersEnabled) {
		            UserDetails.setBackground(new Color(201, 254, 122));
		        }
		    }
		});
		UserDetails.setBackground(new Color(255, 255, 255));
		UserDetails.setBounds(31, 53, 258, 469);
		contentPane.add(UserDetails);
		UserDetails.setLayout(null);
		
		JLabel UserLabel = new JLabel("User Info");
		UserLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		UserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UserLabel.setBounds(10, 218, 212, 32);
		UserDetails.add(UserLabel);
		
		JLabel NameLb = new JLabel("Name       :");
		NameLb.setFont(new Font("Tahoma", Font.PLAIN, 12));
		NameLb.setBounds(15, 290, 70, 24);
		UserDetails.add(NameLb);
		
		JLabel MobileLb = new JLabel("Mobile No :");
		MobileLb.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MobileLb.setBounds(15, 320, 70, 24);
		UserDetails.add(MobileLb);
		
		JLabel lblEmailId = new JLabel("Email ID   :");
		lblEmailId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmailId.setBounds(15, 350, 70, 24);
		UserDetails.add(lblEmailId);
		
		JLabel lblUserId = new JLabel("User ID  :");
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserId.setBounds(15, 260, 70, 24);
		UserDetails.add(lblUserId);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
//			System.out.println("------- Connected from Delivery User Section -------");		
			Statement stm=con1.createStatement();
		    //mysql query to get Products
		    String sql = "SELECT * FROM login WHERE log = 'login'";
		    ResultSet rs = stm.executeQuery(sql);
		    
		    if(rs.next()) {				
				ID = new JTextField(rs.getString("username"));
				ID.setEnabled(false);
				ID.setColumns(10);
				ID.setBounds(95, 260, 153, 19);
				ID.setFont(new Font("Tahoma", Font.BOLD, 10));
				UserDetails.add(ID);
				
				Name = new JTextField(rs.getString("name"));
				Name.setEnabled(true);
				Name.setColumns(10);
				Name.setBounds(95, 294, 153, 19);
				Name.setFont(new Font("Tahoma", Font.BOLD, 10));
				UserDetails.add(Name);
				
				no = new JTextField(rs.getString("phone"));
				no.setEnabled(true);
				no.setColumns(10);
				no.setBounds(95, 324, 153, 19);
				no.setFont(new Font("Tahoma", Font.BOLD, 10));
				UserDetails.add(no);
				
				email = new JTextField(rs.getString("email"));
				email.setEnabled(true);
				email.setColumns(10);
				email.setBounds(95, 354, 153, 19);
				UserDetails.add(email);
//				btnNewButton.setBounds(498, 10, 85, 21);
//				contentPane.add(btnNewButton);
		    }
		    
		}catch(Exception ex) {
			System.out.println(ex);
		}
		
		JButton btnNewButton_1 = new JButton("Verify Details");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listenersEnabled = false;
				user=true;
				UserDetails.setBackground(new Color(201, 254, 122));
			}
		});
		btnNewButton_1.setBounds(65, 419, 126, 21);
		UserDetails.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\businessman.png"));
		lblNewLabel.setBounds(65, 70, 126, 127);
		UserDetails.add(lblNewLabel);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		JLabel Date = new JLabel(dtf.format(now));
		Date.setHorizontalAlignment(SwingConstants.CENTER);
		Date.setFont(new Font("Tahoma", Font.BOLD, 10));
		Date.setBounds(40, 390, 168, 13);
		UserDetails.add(Date);
		
		JPanel DeliveryDetails = new JPanel();
		DeliveryDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(listenersEnabled1) {
					DeliveryDetails.setBackground(new Color(255, 255, 255));
				}
			}
		});
		DeliveryDetails.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(listenersEnabled1) {
					DeliveryDetails.setBackground(new Color(255,114,118));
				}
			}
		});
		DeliveryDetails.setBackground(new Color(255, 255, 255));
		DeliveryDetails.setBounds(347, 53, 423, 469);
		contentPane.add(DeliveryDetails);
		DeliveryDetails.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\parcel.png"));
		lblNewLabel_1.setBounds(159, 70, 117, 135);
		DeliveryDetails.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Delivery Details");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2.setBounds(101, 218, 229, 39);
		DeliveryDetails.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Street Line 1 :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(19, 285, 87, 17);
		DeliveryDetails.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Street Line 2 :");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(19, 312, 87, 17);
		DeliveryDetails.add(lblNewLabel_3_1);
		
		Street1 = new JTextField();
		Street1.setBounds(114, 285, 286, 19);
		DeliveryDetails.add(Street1);
		Street1.setColumns(10);
		
		Street2 = new JTextField();
		Street2.setColumns(10);
		Street2.setBounds(114, 312, 286, 19);
		DeliveryDetails.add(Street2);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("City :");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1_1.setBounds(19, 339, 87, 17);
		DeliveryDetails.add(lblNewLabel_3_1_1);
		
		City = new JTextField();
		City.setColumns(10);
		City.setBounds(56, 339, 142, 19);
		DeliveryDetails.add(City);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("State :");
		lblNewLabel_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1_1_1.setBounds(208, 341, 107, 17);
		DeliveryDetails.add(lblNewLabel_3_1_1_1);
		
		State = new JTextField();
		State.setColumns(10);
		State.setBounds(258, 339, 142, 19);
		DeliveryDetails.add(State);
		
		JLabel lblNewLabel_3_1_1_2 = new JLabel("Pincode  :");
		lblNewLabel_3_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1_1_2.setBounds(19, 369, 87, 17);
		DeliveryDetails.add(lblNewLabel_3_1_1_2);
		
		JLabel lblNewLabel_3_1_1_2_1 = new JLabel("Country  :");
		lblNewLabel_3_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1_1_2_1.setBounds(208, 372, 87, 17);
		DeliveryDetails.add(lblNewLabel_3_1_1_2_1);
		
		zip = new JTextField();
		zip.setColumns(10);
		zip.setBounds(81, 369, 117, 19);
		DeliveryDetails.add(zip);
		
		Country = new JTextField();
		Country.setColumns(10);
		Country.setBounds(268, 368, 132, 19);
		DeliveryDetails.add(Country);
		
		JButton btnNewButton_2 = new JButton("Finalize Order");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Street1.getText()!="NULL" && Street2.getText()!="NULL" && City.getText()!="NULL" && State.getText()!="NULL" && zip.getText()!="NULL" && Country.getText()!="NULL" && Street1.getText().length()>0 && Street2.getText().length()>0 && City.getText().length()>0 && State.getText().length()>0 && zip.getText().length()>0 && Country.getText().length()>0) {
					listenersEnabled1=false;
					address=true;
					DeliveryDetails.setBackground(new Color(201, 254, 122));
				}else {
					JOptionPane.showMessageDialog(null, "Delivery Details Not Filled");    
				}
			}
		});
		btnNewButton_2.setBounds(159, 405, 117, 21);
		DeliveryDetails.add(btnNewButton_2);
	}
	
	public void cartupd(){
		PaySummary = new Panel();
		PaySummary.setBackground(new Color(255, 255, 255));
		PaySummary.setBounds(830, 28, 199, 529);
		contentPane.add(PaySummary);
		PaySummary.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(11, 36, 178, 324);
		PaySummary.add(panel);
		panel.setLayout(null);
		
		Dfee = new JTextField();
		Dfee.setEnabled(false);
		Dfee.setEditable(false);
		Dfee.setText("₹25.50");
		Dfee.setBounds(130, 393, 48, 21);
		PaySummary.add(Dfee);
		Dfee.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Total");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(25, 446, 39, 21);
		PaySummary.add(lblNewLabel);
		
		JLabel lblTaxes = new JLabel("Taxes");
		lblTaxes.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTaxes.setBounds(25, 415, 39, 21);
		PaySummary.add(lblTaxes);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 440, 160, 1);
		PaySummary.add(separator);
		
		JLabel lblDeliveryFee = new JLabel("Delivery Fee");
		lblDeliveryFee.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDeliveryFee.setBounds(25, 393, 66, 21);
		PaySummary.add(lblDeliveryFee);
		
		JLabel lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSubTotal.setBounds(25, 372, 57, 21);
		PaySummary.add(lblSubTotal);
		
		JLabel lblMyOrder = new JLabel("My Order");
		lblMyOrder.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMyOrder.setBounds(11, 12, 105, 21);
		PaySummary.add(lblMyOrder);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 365, 160, 1);
		PaySummary.add(separator_2);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false", "root", "");

            Statement stm1 = con.createStatement();
            String sql1 = "SELECT COUNT(*) AS fcount FROM foodbase WHERE cart > 0";
            ResultSet rs = stm1.executeQuery(sql1);
            if(rs.next()) {
            	int totalFoodCount = rs.getInt("fcount");
            	i=totalFoodCount;
//            	System.out.println(i);
            }
            
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		ImageIcon icon = new ImageIcon("");
		JButton btnNewButton = new JButton("Checkout\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user==true && address==true) {
					UIManager.put("OptionPane.minimumSize", new Dimension(400, 120*(i)));
				    int res = JOptionPane.showConfirmDialog(null, panel, "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
				      if(res == 0) {
				    	  try {
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
								System.out.println("Connected from Delivery Page");
								
								Statement stm1=con.createStatement();
							    //mysql to create order table for product side
							    String sql1 = "ALTER TABLE foodbase DROP COLUMN `cart`";
							    stm1.executeUpdate(sql1);
							    stm1.close();
							    System.out.println("Cart Deleted");
							    
							    Statement stm2=con.createStatement();
							    //mysql to create order table for product side
							    String sql2 = "ALTER TABLE login DROP COLUMN `log`";
							    stm2.executeUpdate(sql2);
							    stm2.close();
							    System.out.println("Log Deleted");
							    
							    con.close();
							    
							}catch(Exception es) {
								System.out.println(es.getMessage());
							}
				    	  
				    	  UIManager.put("OptionPane.minimumSize", new Dimension(400, 100));
				    	  JOptionPane.showMessageDialog(null, "Order Placed"); 
				    	  login lobj=new login();
				    	  lobj.show();
				    	  dispose();
				      } else if (res == 1) {
				    	    UIManager.put("OptionPane.minimumSize", new Dimension(400, 100));
						    int res1 = JOptionPane.showConfirmDialog(null, "Sure? You want to exit?", "File", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
						      if(res1 == 0) {
						    	  UIManager.put("OptionPane.minimumSize", new Dimension(400, 100));
						    	  JOptionPane.showMessageDialog(null, "Thanks For Patience!!!"); 
						    	  login lobj=new login();
						    	  lobj.show();
						    	  dispose();
						      } else {
						         System.out.println("Pressed CANCEL");
						      }
				         System.out.println("Pressed NO");
				      } else {
				         System.out.println("Pressed CANCEL");
				      }
				}else {
					JOptionPane.showMessageDialog(null, "Verify First!!!");
				}
				
				
			}
		});
		btnNewButton.setBounds(36, 485, 129, 21);
		PaySummary.add(btnNewButton);
		int j=0;
		try {
			for(int i=0;i<10;i++) {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
//				System.out.println("------- Connected from Cart Section -------");
				
				Statement stm=con1.createStatement();
			    //mysql query to get Products
			    String sql = "SELECT * FROM foodbase WHERE id = '"+(i+1)+"'";
			    ResultSet rs = stm.executeQuery(sql);
			    
			    
			    if(rs.next()) {
			    	final int index = i;
			    	final String foodName = rs.getString("food");
			    	final String cost=rs.getString("cost");
			    	final int cost1=rs.getInt("cost");
			    	final String q=rs.getString("cart");
			    	final int q1=rs.getInt("cart");
			    	final String img20=rs.getString("img20");
//			    	System.out.println(j);
			    	if(q1>0) {
			    		JLabel img = new JLabel("");
						img.setHorizontalAlignment(SwingConstants.CENTER);
						img.setIcon(new ImageIcon(img20));
						img.setBounds(8, 10+(j*46), 40, 40);
						panel.add(img);
				    	
				    	JLabel name = new JLabel(foodName);
						name.setBounds(54, 10+(j*46), 79, 13);
						panel.add(name);
						
						JLabel Cost = new JLabel("₹"+(cost1*q1));
						Cost.setHorizontalAlignment(SwingConstants.RIGHT);
						Cost.setBounds(140, 10+(j*46), 34, 13);
						panel.add(Cost);
						
						JLabel Quantity = new JLabel(q);
						Quantity.setHorizontalAlignment(SwingConstants.RIGHT);
						Quantity.setBounds(150, 33+(j*46), 24, 13);
						panel.add(Quantity);
						
		  				JButton Add = new JButton("+");
		  				Add.addActionListener(new ActionListener() {
							 	public void actionPerformed(ActionEvent e) {
							 		try {
							 			Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
							 			PreparedStatement ps = con11.prepareStatement("UPDATE foodbase SET `cart` = `cart` + 1 WHERE food = '"+foodName+"'");
								        // Execute the insert statement
								        int rowsInserted = ps.executeUpdate();
								        if (rowsInserted > 0) {
								            System.out.println("Data Updated successfully.");
								        } else {
								            System.out.println("Data Updation failed.");
								        }
								        // Close the prepared statement
								        ps.close();    
								        delivery dobj=new delivery();
								        dobj.show();
								        dobj.cartupd();
									    dispose();
										
									}catch(Exception es) {
										System.out.println(es.getMessage());
									}
								}
							});
						Add.setBounds(54, 30+(j*46), 45, 21);
						panel.add(Add);
						
						JButton Remove = new JButton("-");
						Remove.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent e) {
						 		try {
						 			Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
						 			PreparedStatement ps = con11.prepareStatement("UPDATE foodbase SET `cart` = `cart` - 1 WHERE food = '"+foodName+"'");
							        // Execute the insert statement
							        int rowsInserted = ps.executeUpdate();
							        if (rowsInserted > 0) {
							            System.out.println("Data Updated successfully.");
							        } else {
							            System.out.println("Data Updation failed.");
							        }
							        // Close the prepared statement
							        ps.close(); 
							        
							        delivery dobj=new delivery();
							        dobj.show();
							        dobj.cartupd();
								    dispose();
									
								}catch(Exception es) {
									System.out.println(es.getMessage());
								}
							}
						});
						Remove.setBounds(100, 30+(j*46), 45, 21);
						panel.add(Remove);	
						++j;
			    	}	
			    }
			    rs.close();
			    stm.close();
			    con1.close();
			    
			    try { //to calculate cost
					Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
//					System.out.println("------- Connected from Cart Section -------");
					
					Statement stm11=con11.createStatement();
				    //mysql query to get Products
				    String sql11 = "SELECT * FROM foodbase";
				    ResultSet rs1 = stm11.executeQuery(sql11);
					double calc=0.00;
					double tax=0.00;
					double total=0.00;
			        while(rs1.next()) {
			        	calc=calc+(rs1.getInt("cost")*rs1.getInt("cart"));
			        }
					
					Stotal = new JTextField();
					Stotal.setEditable(false);
					Stotal.setText("₹"+calc);
					Stotal.setColumns(10);
					Stotal.setBounds(130, 370, 48, 21);
					PaySummary.add(Stotal);
					
					tax= (calc*0.18);
			        // Create a BigDecimal object from the double value
			        BigDecimal bigDecimal = new BigDecimal(tax);
					 // Round to 2 decimal places using setScale() method
			        BigDecimal roundedBigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			        // Get the rounded result as a double value
			        double roundedDouble = roundedBigDecimal.doubleValue();
			        
			        total= (calc+(calc*0.18)+25.50);
			        // Create a BigDecimal object from the double value
			        BigDecimal bigDecimal1 = new BigDecimal(total);
					 // Round to 2 decimal places using setScale() method
			        BigDecimal roundedBigDecimal1 = bigDecimal1.setScale(2, BigDecimal.ROUND_HALF_UP);
			        // Get the rounded result as a double value
			        double roundedDouble1 = roundedBigDecimal1.doubleValue();
					
					Taxes = new JTextField();
					Taxes.setText("₹"+roundedDouble);
					Taxes.setEditable(false);
					Taxes.setColumns(10);
					Taxes.setBounds(130, 416, 48, 21);
					PaySummary.add(Taxes);
					if(calc>0) {
						Total = new JTextField();
						Total.setText("₹"+total);
						Total.setEditable(false);
						Total.setColumns(10);
						Total.setBounds(130, 446, 48, 21);
						PaySummary.add(Total);
					}else {
						Total = new JTextField();
						Total.setText("₹0");
						Total.setEditable(false);
						Total.setColumns(10);
						Total.setBounds(130, 446, 48, 21);
						PaySummary.add(Total);
					}
					con11.close();
				}catch(Exception es) {
					System.out.println(es.getMessage());
				}
			    
			}
			
		}catch(Exception es) {
			System.out.println(es.getMessage());
		}
		
	}
}
