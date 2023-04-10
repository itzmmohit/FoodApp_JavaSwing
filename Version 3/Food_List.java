import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Food_List extends JFrame {

	private JPanel contentPane;
	private JTextField Dfee;
	private JTextField Stotal;
	private JTextField Taxes;
	private JTextField Total;
	private Panel PaySummary;  //cart global
	private JPanel panel;      //cart global
	private int i;
	private int quantity;  // for temp count purpose
	private JPanel Product;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Food_List frame = new Food_List();
					frame.cartupd();
					frame.product_list();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Food_List() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("LogOut");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				try {
//					Class.forName("com.mysql.jdbc.Driver");Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
//	    			Statement stm11=con11.createStatement();
//		    		// MySQL query to add a new column
//		    		String sql11 = "ALTER TABLE login DROP COLUMN `log`";
//		    		stm11.executeUpdate(sql11);
//		    		stm11.close();
//		    		con11.close();
//				}catch(Exception ex) {
//					login lobj=new login();
//					lobj.show();
//					dispose();
//				}
				
				login lobj=new login();
				lobj.show();
				dispose();
			}
		});
		btnNewButton_2.setBounds(690, 29, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				product_home pobj = new product_home();
			    pobj.show();
			    pobj.cartupd();
			    pobj.srch();
			    pobj.product_list();
			    dispose();
			}
		});
		Back.setToolTipText("Previous Page");
		Back.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\home (1).png"));
		Back.setBounds(43, 10, 40, 40);
		contentPane.add(Back);
	}

	public void product_list() {
		Product = new JPanel();
		Product.setBackground(new Color(255, 255, 255));
		Product.setBounds(36, 94, 766, 463);
		Product.setLayout(null);
		
		JScrollPane Prod = new JScrollPane(Product);
		Prod.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Prod.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Prod.setBackground(new Color(255, 255, 255));
		Prod.setBounds(36, 94, 766, 463);
		contentPane.add(Prod);
		
		{  // For Product Page			
			try {
				// Open Connection
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
				System.out.println("Connected from P Page");
				
				int tempy=-1,tempx=-1;
				for(i=0;i<20;i++) {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");					
//					System.out.println("Connected from Product Section");
					Statement stm=con1.createStatement();
				    //mysql query to get Products
				    String sql = "SELECT * FROM foodbase WHERE id = '"+(i+1)+"'";
				    ResultSet rs = stm.executeQuery(sql);
				    if(rs.next()) {
				    	 final String foodName = rs.getString("food");
				    	 final String cost=rs.getString("cost");
				    	 final String img=rs.getString("img");
				    	 final int quan=rs.getInt("cart");
				    	 
				    	 try {
				    		 Class.forName("com.mysql.jdbc.Driver");
							 Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
//							 System.out.println("Connected from Product Section");
							 Statement stm11=con11.createStatement();
							 //mysql query to get Products
					         String sql11 = "SELECT `order`FROM foodbase WHERE food = '"+foodName+"'";
							 ResultSet rs11 = stm11.executeQuery(sql11);
							 if(rs11.next()) {
								 quantity=rs11.getInt("order");
//								 System.out.println(quantity);
							 }
				    	 }catch(Exception es) {
				    		 
				    	 }
						 {
							 if(i%4==0){
								 tempx=0;
								 tempy++;
							 }
							 else
								 tempx++;
							 
							 JLabel lblNewLabel_1 = new JLabel("");
							 lblNewLabel_1.setIcon(new ImageIcon(img));
							 lblNewLabel_1.setBounds(20+(tempx*195),20+(tempy*130), 140, 88);
							 Product.add(lblNewLabel_1);
								
							 JLabel lblNewLabel_2 = new JLabel(foodName);
							 lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
							 lblNewLabel_2.setBounds(20+(tempx*195), 108+(tempy*130), 102, 13);
							 Product.add(lblNewLabel_2);
								
							 JLabel lblNewLabel_2_1 = new JLabel("₹"+cost);
							 lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
							 lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
							 lblNewLabel_2_1.setBounds(115+(tempx*195), 109+(tempy*130), 45, 13);
							 Product.add(lblNewLabel_2_1);
							 
							 SpinnerModel spinnerModel = new SpinnerNumberModel(quan, 0, 100, 1); // initial value, min value, max value, step
							 JSpinner spinner = new JSpinner(spinnerModel);
							 spinner.setEnabled(false);
							 spinner.setBounds(132+(tempx*195), 122+(tempy*130), 30, 20);
							 Product.add(spinner);
								
							 JButton remove_1 = new JButton("-");
							 remove_1.setFont(new Font("Tahoma", Font.BOLD, 20));
							 remove_1.addActionListener(new ActionListener() {
							 	public void actionPerformed(ActionEvent e) {
									try {
										PreparedStatement ps = con.prepareStatement("UPDATE foodbase SET `cart` = `cart` - 1 WHERE food = '"+foodName+"'");
								        // Execute the insert statement
								        int rowsInserted = ps.executeUpdate();
								        if (rowsInserted > 0) {
								            System.out.println("Data Updated successfully.");
								        } else {
								            System.out.println("Data Updation failed.");
								        }
								        // Close the prepared statement
								        ps.close();   
								        Food_List flobj=new Food_List();
								        flobj.show();
								        flobj.cartupd();
								        flobj.product_list();
									    dispose();
									}catch(Exception es) {
										System.out.println(es.getMessage());
									}
								}
							 });
	
							 JButton add_1 = new JButton("Buy");
							 if(quan>0) {
//								System.out.println(foodName+" "+quan);
							 	remove_1.setBounds(80+(tempx*195), 122+(tempy*130), 43, 21);
								add_1.setText("+");
								add_1.setFont(new Font("Tahoma", Font.BOLD, 15));
						 	 }
							 add_1.addActionListener(new ActionListener() {
							 	public void actionPerformed(ActionEvent e) {
									try {
										PreparedStatement ps = con.prepareStatement("UPDATE foodbase SET `cart` = `cart` + 1 WHERE food = '"+foodName+"'");
								        // Execute the insert statement
								        int rowsInserted = ps.executeUpdate();
								        if (rowsInserted > 0) {
								            System.out.println("Data Updated successfully.");
								        } else {
								            System.out.println("Data Updation failed.");
								        }
								        // Close the prepared statement
								        ps.close();    
								        Food_List flobj=new Food_List();
								        flobj.show();
								        flobj.cartupd();
								        flobj.product_list();
									    dispose();
									}catch(Exception es) {
										System.out.println(es.getMessage());
									}
								}
							});
							Product.add(remove_1);
							add_1.setBounds(20+(tempx*195), 122+(tempy*130), 57, 21);
							Product.add(add_1);
						 }
						
				    }
				    rs.close();
				    stm.close();
				    con1.close();
				}				
			}catch(Exception es) {
				System.out.println(es.getMessage());
			}
		}
		Product.setPreferredSize(new Dimension(766, 700));
		Product.revalidate();
		Product.repaint();
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
		
		JButton btnNewButton = new JButton("Checkout\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delivery dobj=new delivery();
				dobj.cartupd();
				dobj.show();
				dispose();
			}
		});
		btnNewButton.setBounds(36, 485, 129, 21);
		PaySummary.add(btnNewButton);
		int j=0;
		try {
			for(int i=0;i<20;i++) {
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
								        Food_List flobj=new Food_List();
								        flobj.show();
								        flobj.cartupd();
								        flobj.product_list();
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
							        Food_List flobj=new Food_List();
							        flobj.show();
							        flobj.cartupd();
							        flobj.product_list();
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
