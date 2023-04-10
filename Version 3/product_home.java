import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 
import java.math.BigDecimal;

class MySpinnerModel implements SpinnerModel {  // For Spinner purpose
    private int value = 0;
    private int minimum = 0;
    private int maximum = 100;
    private int stepSize = 1;
    private ChangeListener changeListener;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        if (value instanceof Integer) {
            int newValue = (Integer) value;
            if (newValue >= minimum && newValue <= maximum) {
                this.value = newValue;
                if (changeListener != null) {
                    changeListener.stateChanged(new ChangeEvent(this));
                }
            }
        }
    }

    public Object getNextValue() {
        int newValue = value + stepSize;
        if (newValue <= maximum) {
            return newValue;
        } else {
            return null;
        }
    }

    public Object getPreviousValue() {
        int newValue = value - stepSize;
        if (newValue >= minimum) {
            return newValue;
        } else {
            return null;
        }
    }

    public void addChangeListener(ChangeListener l) {
        changeListener = l;
    }

    public void removeChangeListener(ChangeListener l) {
        changeListener = null;
    }
}


public class product_home extends JFrame {

	private JPanel contentPane;
	private JTextField Dfee;
	private int i;
	private int quantity;  // for temp count purpose
	private JTextField Stotal;
	private JTextField Taxes;
	private JTextField Total;
	private Panel PaySummary;  //cart global
	private JPanel panel;      //cart global
    private JTextField searchBar;
    private JPanel srchResult;
    private JPanel Base;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					product_home frame = new product_home();
					frame.product_list();
					frame.cartupd();
					frame.srch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
			for(int i=0;i<8;i++) {
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
								        product_home pobj = new product_home();
									    pobj.show();
									    pobj.cartupd();
									    pobj.srch();
									    pobj.product_list();
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
							        
							        product_home pobj = new product_home();
								    pobj.show();
								    pobj.cartupd();
								    pobj.srch();
								    pobj.product_list();
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
	      srchResult.setBounds(254, 50, 342, 206);
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
			
			JLabel ThemeBG = new JLabel("");
			ThemeBG.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\ThemeBG.jpg"));
			ThemeBG.setBounds(47, 67, 740, 173);
			contentPane.add(ThemeBG);

	      searchBar.addKeyListener(new KeyAdapter() {
	          @Override
	          public void keyReleased(KeyEvent e) {
	              String searchText = searchBar.getText();
	              searchText=searchText.trim();
	              if(searchText.length()>0) {
	                  srchResult.setVisible(true);
	                  ThemeBG.setVisible(false);
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
	                  	      Base.setBounds(10, 10, 322, 200);
	                  	      Base.setLayout(null);
	                  	      srchResult.add(Base);
	                  	      Base.setBackground(Color.WHITE);
	                     	
	                     	 JLabel nameLabel = new JLabel("No Search Result to Display");
	                         nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	                         nameLabel.setBounds(60, 73, 200, 40);
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
	                  ThemeBG.setVisible(true);
	                  btnNewButton_1.setVisible(true);
	              }
	          }
	      });
	}
	
	public product_home() {
		System.out.println("___Loop___");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
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
		btnNewButton_2.setBounds(910, 10, 85, 21);
		contentPane.add(btnNewButton_2);
		
		

		//------Trial Purpose-------
		
//		JPanel Product = new JPanel();
//		Product.setBackground(new Color(255, 255, 255));
//		Product.setBounds(47, 259, 740, 300);
//		contentPane.add(Product);
//		Product.setLayout(null);
//		
//		searchBar = new JTextField();
//	    searchBar.setBounds(226, 22, 258, 25);
//	    contentPane.add(searchBar);
//	    searchBar.setColumns(10);
//		
//		PaySummary = new Panel();
//		PaySummary.setBackground(new Color(255, 255, 255));
//		PaySummary.setBounds(830, 28, 199, 529);
//		contentPane.add(PaySummary);
//		PaySummary.setLayout(null);
		
		//--------------------------
		
		try {
			// Open Connection
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
			System.out.println("Order Table Created");
			Statement stm1=con.createStatement();
		    //mysql to create order table for product side
		    String sql1 = "ALTER TABLE foodbase ADD COLUMN `cart` INT DEFAULT 0";
		    stm1.executeUpdate(sql1);
		    stm1.close();
		    con.close();
		}catch(Exception es) {
		}
	}

	public product_home(String username) {
		System.out.println("___First Timee___");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
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
		btnNewButton_2.setBounds(910, 10, 85, 21);
		contentPane.add(btnNewButton_2);
		
		try {
			// Open Connection
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
			System.out.println("Order Table Created");
			Statement stm1=con.createStatement();
		    //mysql to create order table for product side
		    String sql1 = "ALTER TABLE foodbase ADD COLUMN `cart` INT DEFAULT 0";
		    stm1.executeUpdate(sql1);
		    stm1.close();
		    con.close();
		}catch(Exception es) {
			try {    
				try {
					Class.forName("com.mysql.jdbc.Driver");
	                Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false", "root", "");
	
	                Statement stm11 = con1.createStatement();
	                String sql11 = "SELECT COUNT(*) as count FROM foodbase WHERE cart > 0";
	                ResultSet rs = stm11.executeQuery(sql11); 
	                
	                if(rs.next()) {
	                	if(rs.getInt("count")>0) {
	                		ImageIcon icon = new ImageIcon("");
	        				UIManager.put("OptionPane.minimumSize", new Dimension(400, 100));
	        			    int res1 = JOptionPane.showConfirmDialog(null, "Wanna Continue From where Left?", "File", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
	        			    if(res1 == 0) {
	        			    	System.out.println("_________Continue_________");
	        			    } else {
	        			        System.out.println("_________Fresh_________");
	        			     // Open Connection
	        					Class.forName("com.mysql.jdbc.Driver");
	        					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
	        					
	        					Statement stm1=con.createStatement();
	        				    //mysql to create order table for product side
	        				    String sql1 = "ALTER TABLE foodbase DROP COLUMN `cart`";
	        				    stm1.executeUpdate(sql1);
	        				    stm1.close();
	        					
	        					Statement stm2=con.createStatement();
	        				    //mysql to create order table for product side
	        				    String sql2 = "ALTER TABLE foodbase ADD COLUMN `cart` INT DEFAULT 0";
	        				    stm2.executeUpdate(sql2);
	        				    stm2.close();
	        				    con.close();
	        				    System.out.println("Order Table Created");
	        			    }
	                	}else {
	                		System.out.println("Order Table Not present");
	                	}
	                }
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}catch(Exception ex) {	
				System.out.println(ex.getMessage());
			}
		}
	}
	
	public void product_list() {
		JPanel Product = new JPanel();
		Product.setBackground(new Color(255, 255, 255));
		Product.setBounds(47, 259, 740, 300);
		contentPane.add(Product);
		Product.setLayout(null);
		{  // For Product Page			
			try {
				// Open Connection
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
				System.out.println("Connected from P Page");
				
				for(i=0;i<8;i++) {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");					
//					System.out.println("Connected from Product Section");
					Statement stm=con1.createStatement();
				    //mysql query to get Products
				    String sql = "SELECT * FROM foodbase WHERE id = '"+(i+1)+"'";
				    ResultSet rs = stm.executeQuery(sql);
				    if(rs.next()) {
				    	 final int index = i;
				    	 final String foodName = rs.getString("food");
				    	 final String cost=rs.getString("cost");
				    	 final String img=rs.getString("img");
				    	 final String img20=rs.getString("img20");
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
						 if(i<4) {
							 JLabel lblNewLabel_1 = new JLabel("");
							 lblNewLabel_1.setIcon(new ImageIcon(img));
							 lblNewLabel_1.setBounds(26+(i*180), 15, 140, 88);
							 Product.add(lblNewLabel_1);
								
							 JLabel lblNewLabel_2 = new JLabel(foodName);
							 lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
							 lblNewLabel_2.setBounds(26+(i*180), 106, 102, 13);
							 Product.add(lblNewLabel_2);
								
							 JLabel lblNewLabel_2_1 = new JLabel("₹"+cost);
							 lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
							 lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
							 lblNewLabel_2_1.setBounds(121+(i*180), 106, 45, 13);
							 Product.add(lblNewLabel_2_1);
								
//								JLabel count_1 = new JLabel("");
//								count_1.setEnabled(false);
//								count_1.setHorizontalAlignment(SwingConstants.CENTER);
//								count_1.setBounds(89, 178, 19, 13);
//								Product.add(count_1);
								
							 SpinnerModel spinnerModel = new SpinnerNumberModel(quan, 0, 100, 1); // initial value, min value, max value, step
							 JSpinner spinner = new JSpinner(spinnerModel);
							 spinner.setEnabled(false);
							 spinner.setBounds(139+(i*180), 122, 30, 20);
							 Product.add(spinner);
								
							 JButton remove_1 = new JButton("-");
							 remove_1.setFont(new Font("Tahoma", Font.BOLD, 20));
							 remove_1.addActionListener(new ActionListener() {
							 	public void actionPerformed(ActionEvent e) {
									try {
//										int q = ((int) spinner.getValue())-1;  // Get the selected quantity from the spinner
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
								        product_home pobj = new product_home();
									    pobj.show();
									    pobj.cartupd();
									    pobj.srch();
									    pobj.product_list();
									    dispose();
									}catch(Exception es) {
										System.out.println(es.getMessage());
									}
								}
							 });
	
							 JButton add_1 = new JButton("Buy");
							 if(quan>0) {
//								System.out.println(foodName+" "+quan);
							 	remove_1.setBounds(85+(index*180), 122, 43, 21);
								add_1.setText("+");
								add_1.setFont(new Font("Tahoma", Font.BOLD, 15));
						 	 }
							 add_1.addActionListener(new ActionListener() {
							 	public void actionPerformed(ActionEvent e) {
									try {
//										int q = ((int) spinner.getValue())+1;  // Get the selected quantity from the spinner
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
								        product_home pobj = new product_home();
									    pobj.show();
									    pobj.cartupd();
									    pobj.srch();
									    pobj.product_list();
									    dispose();
									}catch(Exception es) {
										System.out.println(es.getMessage());
									}
								}
							});
							Product.add(remove_1);
							add_1.setBounds(25+(index*180), 122, 57, 21);
							Product.add(add_1);
						 }
						 else {
							 JLabel lblNewLabel_1 = new JLabel("");
							 lblNewLabel_1.setIcon(new ImageIcon(img));
							 lblNewLabel_1.setBounds(26+((i-4)*180), 162, 140, 88);
							 Product.add(lblNewLabel_1);
								
							 JLabel lblNewLabel_2 = new JLabel(foodName);
							 lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
							 lblNewLabel_2.setBounds(26+((i-4)*180), 252, 102, 13);
							 Product.add(lblNewLabel_2);
								
							 JLabel lblNewLabel_2_1 = new JLabel("₹"+cost);
							 lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
							 lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
							 lblNewLabel_2_1.setBounds(121+((i-4)*180), 252, 45, 13);
							 Product.add(lblNewLabel_2_1);
								
//								JLabel count_1 = new JLabel("");
//								count_1.setEnabled(false);
//								count_1.setHorizontalAlignment(SwingConstants.CENTER);
//								count_1.setBounds(89, 178, 19, 13);
//								Product.add(count_1);
								
							 SpinnerModel spinnerModel = new SpinnerNumberModel(quan, 0, 100, 1); // initial value, min value, max value, step
							 JSpinner spinner = new JSpinner(spinnerModel);
							 spinner.setEnabled(false);
							 spinner.setBounds(139+((i-4)*180), 267, 30, 20);
							 Product.add(spinner);
								
							 JButton remove_1 = new JButton("-");
							 remove_1.setFont(new Font("Tahoma", Font.BOLD, 20));
							 remove_1.addActionListener(new ActionListener() {
								 public void actionPerformed(ActionEvent e) {
										try {
//											int q = ((int) spinner.getValue())-1;  // Get the selected quantity from the spinner
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
									        product_home pobj = new product_home();
										    pobj.show();
										    pobj.cartupd();
										    pobj.srch();
										    pobj.product_list();
										    dispose();
										}catch(Exception es) {
											System.out.println(es.getMessage());
										}
									}
								 });
							 	
							 JButton add_1 = new JButton("Buy");
							 if(quan>0) {
//								System.out.println(foodName+" "+quan);
							 	remove_1.setBounds(85+((index-4)*180), 267, 43, 21);
								add_1.setText("+");
								add_1.setFont(new Font("Tahoma", Font.BOLD, 15));
						 	 }
							 add_1.addActionListener(new ActionListener() {
							     public void actionPerformed(ActionEvent e) {
									 try {
//										int q = ((int) spinner.getValue())+1;  // Get the selected quantity from the spinner
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
										product_home pobj = new product_home();
										pobj.show();
										pobj.cartupd();
										pobj.srch();
										pobj.product_list();
										dispose();
									}catch(Exception es) {
										System.out.println(es.getMessage());
									}
								}
							});
							Product.add(remove_1);
							add_1.setBounds(25+((index-4)*180), 267, 57, 21);
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
	}
}
