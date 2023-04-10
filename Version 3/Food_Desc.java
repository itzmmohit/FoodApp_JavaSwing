import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Food_Desc extends JFrame {

	private JPanel contentPane;
	private JTextField Dfee;
	private JTextField Stotal;
	private JTextField Taxes;
	private JTextField Total;
	private Panel PaySummary;  //cart global
	private JPanel panel;      //cart global
    private JTextField searchBar;
    private JPanel srchResult;
	boolean srchBarEnable = true;
	private JPanel Desc;
	private JPanel Base;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Food_Desc frame = new Food_Desc();
					frame.setVisible(true);
					frame.cartupd();
					frame.srch();  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Food_Desc() {   // Only Use if run directly through here
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PaySummary = new Panel();
		PaySummary.setBackground(new Color(255, 255, 255));
		PaySummary.setBounds(830, 28, 199, 529);
		contentPane.add(PaySummary);
		PaySummary.setLayout(null);
		
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
		
		if(srchBarEnable==true) 
		{
			Desc = new JPanel();
			Desc.setBackground(new Color(255, 255, 255));
			Desc.setBounds(42, 126, 770, 431);
			contentPane.add(Desc);
			Desc.setLayout(null);
			
//			JLabel pic = new JLabel();
//			pic.setBackground(new Color(128, 128, 128));
//			pic.setIcon(new ImageIcon(("img")));
//			pic.setBounds(93, 129, 255, 170);
//			Desc.add(pic);
//			
//			JLabel name = new JLabel(("food"));
//			name.setFont(new Font("Tahoma", Font.BOLD, 20));
//			name.setBounds(469, 105, 230, 29);
//			Desc.add(name);
//			
//			JLabel cost = new JLabel("₹"+("cost"));
//			cost.setFont(new Font("Tahoma", Font.BOLD, 20));
//			cost.setBounds(469, 153, 230, 29);
//			Desc.add(cost);
			
	
			try {
				Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false", "root", "");

                Statement stm1 = con.createStatement();
                String sql1 = "SELECT * FROM foodbase WHERE id='1'";
                ResultSet rs = stm1.executeQuery(sql1); 
                if(rs.next()) {
                	JLabel lblNewLabel_1 = new JLabel();
        			lblNewLabel_1.setIcon(new ImageIcon(rs.getString("img")));
        			lblNewLabel_1.setBounds(93, 129, 255, 170);
        			Desc.add(lblNewLabel_1);
        			
        			JLabel img = new JLabel(rs.getString("food"));
        			img.setFont(new Font("Tahoma", Font.BOLD, 20));
        			img.setBounds(469, 105, 230, 29);
        			Desc.add(img);
        			
        			JLabel cost = new JLabel("₹"+rs.getString("cost"));
        			cost.setFont(new Font("Tahoma", Font.BOLD, 20));
        			cost.setBounds(469, 153, 230, 29);
        			Desc.add(cost);
        			
        			JButton additem = new JButton("+");
        			additem.addMouseListener(new MouseAdapter() {
        				@Override
        				public void mouseClicked(MouseEvent e) {
        					try {
        			 			Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
        			 			PreparedStatement ps = con11.prepareStatement("UPDATE foodbase SET `cart` = `cart` + 1 WHERE id = '1'");
        				        // Execute the insert statement
        				        int rowsInserted = ps.executeUpdate();
        				        if (rowsInserted > 0) {
        				            System.out.println("Data Updated successfully.");
        				        } else {
        				            System.out.println("Data Updation failed.");
        				        }
        				        // Close the prepared statement
        				        ps.close();    
        				        Food_Desc fdobj=new Food_Desc();
        				        fdobj.show();
        				        fdobj.srch();
        				        fdobj.cartupd();
        					    dispose();
        						
        					}catch(Exception es) {
        						System.out.println(es.getMessage());
        					}
        				}
        			});
        			additem.setFont(new Font("Tahoma", Font.BOLD, 20));
        			additem.setBounds(504, 201, 97, 29);
        			Desc.add(additem);
        			
        			JButton deleteitem = new JButton("-");
        			deleteitem.addMouseListener(new MouseAdapter() {
        				@Override
        				public void mouseClicked(MouseEvent e) {
        					try {
        			 			Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
        			 			PreparedStatement ps = con11.prepareStatement("UPDATE foodbase SET `cart` = `cart` - 1 WHERE id = '1'");
        				        // Execute the insert statement
        				        int rowsInserted = ps.executeUpdate();
        				        if (rowsInserted > 0) {
        				            System.out.println("Data Updated successfully.");
        				        } else {
        				            System.out.println("Data Updation failed.");
        				        }
        				        // Close the prepared statement
        				        ps.close(); 
        				        
        				        Food_Desc fdobj=new Food_Desc();
        				        fdobj.show();
        				        fdobj.srch();
        				        fdobj.cartupd();
        					    dispose();
        						
        					}catch(Exception es) {
        						System.out.println(es.getMessage());
        					}
        				}
        			});
        			deleteitem.setFont(new Font("Tahoma", Font.BOLD, 25));
        			deleteitem.setBounds(504, 250, 97, 29);
        			Desc.add(deleteitem);
                }
			}catch(Exception es) {
				
			}
		}
	}
	
	public Food_Desc(String foodName) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PaySummary = new Panel();
		PaySummary.setBackground(new Color(255, 255, 255));
		PaySummary.setBounds(830, 28, 199, 529);
		contentPane.add(PaySummary);
		PaySummary.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("LogOut");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");Connection con11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
	    			Statement stm11=con11.createStatement();
		    		// MySQL query to add a new column
		    		String sql11 = "ALTER TABLE login DROP COLUMN `log`";
		    		stm11.executeUpdate(sql11);
		    		stm11.close();
		    		con11.close();
				}catch(Exception ex) {
					login lobj=new login();
					lobj.show();
					dispose();
				}
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
		
		if(srchBarEnable==true) {
			Desc = new JPanel();
			Desc.setBackground(new Color(255, 255, 255));
			Desc.setBounds(42, 126, 770, 431);
			contentPane.add(Desc);
			Desc.setLayout(null);
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false", "root", "");

                Statement stm1 = con.createStatement();
                String sql1 = "SELECT * FROM foodbase WHERE food='"+foodName+"'";
                ResultSet rs = stm1.executeQuery(sql1); 
                if(rs.next()) {
                	JLabel lblNewLabel_1 = new JLabel();
        			lblNewLabel_1.setIcon(new ImageIcon(rs.getString("img")));
        			lblNewLabel_1.setBounds(93, 129, 255, 170);
        			Desc.add(lblNewLabel_1);
        			
        			JLabel img = new JLabel(rs.getString("food"));
        			img.setFont(new Font("Tahoma", Font.BOLD, 20));
        			img.setBounds(469, 105, 230, 29);
        			Desc.add(img);
        			
        			JLabel cost = new JLabel("₹"+rs.getString("cost"));
        			cost.setFont(new Font("Tahoma", Font.BOLD, 20));
        			cost.setBounds(469, 153, 230, 29);
        			Desc.add(cost);
        			
        			JButton additem = new JButton("+");
        			additem.addMouseListener(new MouseAdapter() {
        				@Override
        				public void mouseClicked(MouseEvent e) {
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
        				        Food_Desc fdobj=new Food_Desc(foodName);
        				        fdobj.show();
        				        fdobj.srch();
        				        fdobj.cartupd();
        					    dispose();
        						
        					}catch(Exception es) {
        						System.out.println(es.getMessage());
        					}
        				}
        			});
        			additem.setFont(new Font("Tahoma", Font.BOLD, 20));
        			additem.setBounds(504, 201, 97, 29);
        			Desc.add(additem);
        			
        			JButton deleteitem = new JButton("-");
        			deleteitem.addMouseListener(new MouseAdapter() {
        				@Override
        				public void mouseClicked(MouseEvent e) {
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
        				        
        				        Food_Desc fdobj=new Food_Desc(foodName);
        				        fdobj.show();
        				        fdobj.srch();
        				        fdobj.cartupd();
        					    dispose();
        						
        					}catch(Exception es) {
        						System.out.println(es.getMessage());
        					}
        				}
        			});
        			deleteitem.setFont(new Font("Tahoma", Font.BOLD, 25));
        			deleteitem.setBounds(504, 250, 97, 29);
        			Desc.add(deleteitem);
        			
                }
			}catch(Exception es) {
				
			}
		}
	}
	
	public void cartupd(){
		
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
								        Food_Desc fdobj=new Food_Desc(foodName);
								        fdobj.show();
								        fdobj.srch();
								        fdobj.cartupd();
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
							        
							        Food_Desc fdobj=new Food_Desc(foodName);
							        fdobj.show();
							        fdobj.srch();
							        fdobj.cartupd();
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
	
	public void srch() {
		
		  searchBar = new JTextField();
//	      searchBar.setBounds(226, 22, 258, 25);
	      searchBar.setBounds(250, 22, 350, 25);
	      contentPane.add(searchBar);
	      searchBar.setColumns(10);
	
	      srchResult = new JPanel();
	      srchResult.setBackground(Color.WHITE);
	//      srchResult.setBounds(226, 50, 258, 206);
	      srchResult.setBounds(254, 50, 342, 500);
	      contentPane.add(srchResult);
	      srchResult.setLayout(null);
	      
	      
	      srchResult.setVisible(false);
	      
	      JButton btnNewButton_1 = new JButton("Order Now");
			btnNewButton_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Food_List flobj=new Food_List();
			        flobj.show();
			        flobj.cartupd();
			        flobj.product_list();
				    dispose();
				}
			});
			btnNewButton_1.setBackground(new Color(201, 254, 122));
			btnNewButton_1.setBounds(81, 185, 151, 34);
			contentPane.add(btnNewButton_1);

	      searchBar.addKeyListener(new KeyAdapter() {
	          @Override
	          public void keyReleased(KeyEvent e) {
	              String searchText = searchBar.getText();
	              searchText=searchText.trim();
	              if(searchText.length()>0) {
	            	  Desc.setVisible(false);
	                  srchResult.setVisible(true);
	                  btnNewButton_1.setVisible(false);
	                  srchResult.removeAll();
	                  try {
	                      Class.forName("com.mysql.jdbc.Driver");
	                      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false", "root", "");
	
	                      Statement stm1 = con.createStatement();
	                      String sql1 = "SELECT * FROM foodbase WHERE food LIKE '%" + searchText + "%'";
	                      ResultSet rs = stm1.executeQuery(sql1);
	                      System.out.println("--Connected from Search Section--");
	
	                      if(rs.next()) {
	                    	 int i=0;
	                     	 while (rs.next()) {
	                              String foodName = rs.getString("food");
	                              String cost = rs.getString("cost");
	                              String img21 = rs.getString("img20");
	                     		 
	                     		  Base = new JPanel();
		                  	      Base.setBounds(10, 10+(i*50), 322, 44);
		                  	      Base.setLayout(null);
		                  	      srchResult.add(Base);
		                  	      Base.setBackground(Color.WHITE);
		                  	      
//	                              JPanel itemPanel = new JPanel();
//	                              itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//	                              itemPanel.setBackground(Color.WHITE);
//	
	                              JLabel imgLabel = new JLabel();
	                              imgLabel.addMouseListener(new MouseAdapter() {
	                     	        	@Override
	                     	        	public void mouseClicked(MouseEvent e) {
	                     	        		Food_Desc fobj=new Food_Desc(foodName);
	                     	        		fobj.show();
	                     	        		fobj.cartupd();
	                     	        		fobj.srch();
	                     	        		dispose();
	                     	        	}
	                     	        });
	                              imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
	                              imgLabel.setIcon(new ImageIcon(img21));
	                              imgLabel.setBounds(10, 0, 40, 40);
	                              Base.add(imgLabel);
	
	                              JLabel nameLabel = new JLabel(foodName);
	                              nameLabel.addMouseListener(new MouseAdapter() {
	                     	        	@Override
	                     	        	public void mouseClicked(MouseEvent e) {
	                     	        		Food_Desc fobj=new Food_Desc(foodName);
	                     	        		fobj.show();
	                     	        		fobj.cartupd();
	                     	        		fobj.srch();
	                     	        		dispose();
	                     	        	}
	                     	        });
	                              nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	                              nameLabel.setBounds(60, 0, 200, 40);
	                              Base.add(nameLabel);

	                              JLabel costLabel = new JLabel("₹" + cost);
	                              costLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	                              costLabel.setBounds(260, 0, 50, 40);
	                              Base.add(costLabel);

	                              JSeparator separator = new JSeparator();
	                              separator.setBounds(0, 43, 322, 1);
	                              Base.add(separator);

		                  	      i++;
	                          }
	                     }
	                     else {
	                    	  Base = new JPanel();
	                  	      Base.setBounds(10, 10, 322, 500);
	                  	      Base.setLayout(null);
	                  	      srchResult.add(Base);
	                  	      Base.setBackground(Color.WHITE);
	                     	
	                     	 JLabel nameLabel = new JLabel("No Search Result to Display");
	                         nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	                         nameLabel.setBounds(60, 220, 200, 40);
	                         Base.add(nameLabel); 
	                         
	                         srchResult.add(Base);
	                     }
	                  } catch (Exception es) {
	                      System.out.println(es.getMessage());
	                  }
	                  srchResult.revalidate();
	                  srchResult.repaint();
	              } else {
	                  srchResult.setVisible(false);
	                  Desc.setVisible(true);
	                  btnNewButton_1.setVisible(true);
	              }
	          }
	      });
	}
}
