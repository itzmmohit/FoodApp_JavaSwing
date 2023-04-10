import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
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
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.Label;
import javax.swing.JList;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JTextField SearchBar;
	private JTextField Dfee;
	int quantity[]=new int[8];  // define to store count
	int i;
	private JTextField Stotal;
	private JTextField Taxes;
	private JTextField Total;
	private Panel PaySummary;  //cart global
	private JPanel panel;      //cart global
    private JTextField searchBar;
    private JPanel srchResult;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					product_home frame = new product_home();
					frame.product_hom();
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
		
		Stotal = new JTextField();
		Stotal.setEditable(false);
		Stotal.setText("₹0");
		Stotal.setColumns(10);
		Stotal.setBounds(130, 370, 48, 21);
		PaySummary.add(Stotal);
		
		Taxes = new JTextField();
		Taxes.setText("₹0");
		Taxes.setEditable(false);
		Taxes.setColumns(10);
		Taxes.setBounds(130, 416, 48, 21);
		PaySummary.add(Taxes);
		
		Total = new JTextField();
		Total.setText("₹0");
		Total.setEditable(false);
		Total.setColumns(10);
		Total.setBounds(130, 446, 48, 21);
		PaySummary.add(Total);
		
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
				dobj.show();
				dispose();
			}
		});
		btnNewButton.setBounds(36, 485, 129, 21);
		PaySummary.add(btnNewButton);
		
		try {
			for(int i=0;i<6;i++) {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
				// Database name is Login
				// username of mysql is root
				// Password = 
//				System.out.println("Connected from Product Section");
				
				System.out.println("------- Connected from Cart Section -------");
				
				Statement stm=con1.createStatement();
			    //mysql query to get Products
			    String sql = "SELECT * FROM cart WHERE id = '"+(i+1)+"'";
			    ResultSet rs = stm.executeQuery(sql);
			    
			    
			    
			    if(rs.next()) {
			    	final int index = i;
			    	final String foodName = rs.getString("name");
			    	final String cost=rs.getString("cost");
			    	String q=rs.getString("quantity");
			    	final String img20=rs.getString("img21");
			    	
			    	JLabel img = new JLabel("");
					img.setHorizontalAlignment(SwingConstants.CENTER);
					img.setIcon(new ImageIcon(img20));
					img.setBounds(8, 10+(i*46), 40, 40);
					panel.add(img);
			    	
			    	JLabel name = new JLabel(foodName);
					name.setBounds(54, 10+(i*46), 79, 13);
					panel.add(name);
					
					JLabel Cost = new JLabel("₹"+cost);
					Cost.setHorizontalAlignment(SwingConstants.RIGHT);
					Cost.setBounds(140, 10+(i*46), 34, 13);
					panel.add(Cost);
					
					JLabel Quantity = new JLabel(q);
					Quantity.setHorizontalAlignment(SwingConstants.RIGHT);
					Quantity.setBounds(150, 33+(i*46), 24, 13);
					panel.add(Quantity);
					
	  				JButton Add = new JButton("+");
	  				Add.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent e) {
						 		//--first method call for pay summary panel--
						 		Quantity.setText(q+1);
								try {
									int q = (int) spinner.getValue();
										
										PreparedStatement ps = con.prepareStatement("UPDATE cart SET name = ?, cost = ?, quantity = ?, img21 = ? WHERE name='"+foodName+"'");
							            ps.setString(1, foodName);
							            ps.setString(2, cost);
							            ps.setInt(3, q+1);
							            ps.setString(4, img20);
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
								        pobj.product_hom();
								        dispose();
									
								}catch(Exception es) {
									System.out.println(es.getMessage());
								}
							}
						});
					Add.setBounds(54, 30+(i*46), 45, 21);
					panel.add(Add);
					
					JButton Remove = new JButton("-");
					Remove.setBounds(100, 30+(i*46), 45, 21);
					panel.add(Remove);
			    	
			    }
			    rs.close();
			    stm.close();
			    con1.close();	
			}
			
		}catch(Exception es) {
			System.out.println(es.getMessage());
		}
		
	}

	public void srch() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 728, 427);
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//        setContentPane(contentPane);
//        contentPane.setLayout(null);

        searchBar = new JTextField();
        searchBar.setBounds(226, 22, 258, 25);
        contentPane.add(searchBar);
        searchBar.setColumns(10);

        srchResult = new JPanel();
        srchResult.setBackground(Color.WHITE);
        srchResult.setBounds(226, 50, 258, 206);
        contentPane.add(srchResult);
        srchResult.setLayout(new BoxLayout(srchResult, BoxLayout.Y_AXIS));
        srchResult.setVisible(false);

        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchBar.getText();
                searchText=searchText.trim();
                if(searchText.length()>0) {
                    srchResult.setVisible(true);
                    srchResult.removeAll();
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false", "root", "");

                        Statement stm1 = con.createStatement();
                        String sql1 = "SELECT * FROM foodbase WHERE food LIKE '%" + searchText + "%'";
                        ResultSet rs = stm1.executeQuery(sql1);
                        System.out.println("--Connected from Search Section--");

                        if(rs.next()) {
                       	 while (rs.next()) {
                                String foodName = rs.getString("food");
                                String cost = rs.getString("cost");
                                String img21 = rs.getString("img20");

                                JPanel itemPanel = new JPanel();
                                itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                                itemPanel.setBackground(Color.WHITE);

                                JLabel imgLabel = new JLabel();
                                imgLabel.addMouseListener(new MouseAdapter() {
                       	        	@Override
                       	        	public void mouseClicked(MouseEvent e) {
                       	        		Food_Desc fobj=new Food_Desc();
                       	        		fobj.show();
                       	        		dispose();
                       	        	}
                       	        });
                                imgLabel.setIcon(new ImageIcon(img21));
                                itemPanel.add(imgLabel);

                                JLabel nameLabel = new JLabel(foodName);
                                nameLabel.addMouseListener(new MouseAdapter() {
                       	        	@Override
                       	        	public void mouseClicked(MouseEvent e) {
                       	        		Food_Desc fobj=new Food_Desc();
                       	        		fobj.show();
                       	        		dispose();
                       	        	}
                       	        });
                                nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
                                itemPanel.add(nameLabel);

                                JLabel costLabel = new JLabel("₹" + cost);
                                costLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                                itemPanel.add(costLabel);

                                JSeparator separator = new JSeparator();
                                separator.setBounds(240, 121, 315, 1);
                                srchResult.add(separator);
                                
                                srchResult.add(itemPanel);

                                
                            }
                       }
                       else {
                       	JPanel itemPanel = new JPanel();
                           itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                           itemPanel.setBackground(Color.WHITE);
                       	
                       	JLabel nameLabel = new JLabel("No Search Result to Display");
                           nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
                           itemPanel.add(nameLabel);
                           
                           srchResult.add(itemPanel);

                       }
                    } catch (Exception es) {
                        System.out.println(es.getMessage());
                    }
                    srchResult.revalidate();
                    srchResult.repaint();
                } else {
                    srchResult.setVisible(false);
                }
            }
        });
	}
	
	public product_home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			// Open Connection
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
			// Database name is Login
			// username of mysql is root
			// Password = 
			System.out.println("Connected from P Page");
			
			Statement stm1=con.createStatement();
		    //mysql to create order table for product side
		    String sql1 = "CREATE TABLE cart( id INT NOT NULL AUTO_INCREMENT primary key, name VARCHAR(255) NOT NULL, cost INT NOT NULL, quantity INT NOT NULL, img21 VARCHAR(255) NOT NULL)";
		    stm1.executeUpdate(sql1);
		}catch(Exception es) {
//			System.out.println(es.getMessage());
		}
	}
	
	public void product_hom() {
	
		//-------------------------------------------------------------------------------------
		JPanel Product = new JPanel();
		Product.setBackground(new Color(255, 255, 255));
		Product.setBounds(47, 259, 740, 300);
		contentPane.add(Product);
		Product.setLayout(null);
		{  // For Product Page			
			for(int j=0;j<8;j++) {
				quantity[j]=0;
			}
			try {
				// Open Connection
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
				// Database name is Login
				// username of mysql is root
				// Password = 
				System.out.println("Connected from P Page");
				
				for(i=0;i<100;i++) {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","");
					// Database name is Login
					// username of mysql is root
					// Password = 
//					System.out.println("Connected from Product Section");
					Statement stm=con1.createStatement();
				    //mysql query to get Products
				    String sql = "SELECT * FROM foodbase WHERE id = '"+(i+1)+"'";
				    ResultSet rs = stm.executeQuery(sql);
				    if(rs.next()) {
				    	 final int index = i;
				    	 final String foodName = rs.getString("food");
				    	 final String cost=rs.getString("cost");
				    	 String img=rs.getString("img");
				    	 final String img20=rs.getString("img20");
//						 System.out.println(img);	
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
								
							 SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1); // initial value, min value, max value, step
							 JSpinner spinner = new JSpinner(spinnerModel);
							 spinner.setEnabled(false);
							 spinner.setBounds(139+(i*180), 122, 30, 20);
							 Product.add(spinner);
								
							 JButton remove_1 = new JButton("-");
							 remove_1.setFont(new Font("Tahoma", Font.BOLD, 20));
							 remove_1.addActionListener(new ActionListener() {
							 	public void actionPerformed(ActionEvent e) {
									quantity[index]--;
//									count_1.setText("" + quantity);
									spinner.setValue(quantity[index]);
									try {
										if(quantity[index]==0) {
											int q = (int) spinner.getValue();  // Get the selected quantity from the spinner
										    
											PreparedStatement ps = con.prepareStatement("DELETE from cart WHERE name='"+foodName+"'");
								            // Execute the insert statement
								            int rowsInserted = ps.executeUpdate();
								            
								            if (rowsInserted > 0) {
								                System.out.println("Data Deleted successfully.");
								            } else {
								                System.out.println("Data Deletion failed.");
								            }
								            // Close the prepared statement
								            ps.close();
								            
								            product_home pobj = new product_home();
									        pobj.show();
									        pobj.cartupd();
									        pobj.srch();
									        pobj.product_hom();
									        dispose();
										}
										else {
											int q = (int) spinner.getValue();  // Get the selected quantity from the spinner
										    
											PreparedStatement ps = con.prepareStatement("UPDATE cart SET name = ?, cost = ?, quantity = ?, img21 = ? WHERE name='"+foodName+"'");
								            ps.setString(1, foodName);
								            ps.setString(2, cost);
								            ps.setInt(3, q);
								            ps.setString(4, img20);
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
									        pobj.product_hom();
									        dispose();
										}
									}catch(Exception es) {
										System.out.println(es.getMessage());
									}
								}
							 });
							 	
							 JButton add_1 = new JButton("Buy");
							 add_1.addActionListener(new ActionListener() {
							 	public void actionPerformed(ActionEvent e) {
							 		remove_1.setBounds(85+(index*180), 122, 43, 21);
							 		//--first method call for pay summary panel--
									quantity[index]++;
									add_1.setText("+");
									add_1.setFont(new Font("Tahoma", Font.BOLD, 15));
//									count_1.setText("" + quantity);
									spinner.setValue(quantity[index]);
									try {
										if(quantity[index]==1) {
											//create statement
											int q = (int) spinner.getValue();  // Get the selected quantity from the spinner
										    
											PreparedStatement ps = con.prepareStatement("INSERT INTO cart (name, cost, quantity, img21) VALUES (?, ?, ?, ?)");
								            ps.setString(1, foodName);
								            ps.setString(2, cost);
								            ps.setInt(3, q);
								            ps.setString(4, img20);
								            // Execute the insert statement
								            int rowsInserted = ps.executeUpdate();
								            
								            if (rowsInserted > 0) {
								                System.out.println("Data inserted successfully.");
								            } else {
								                System.out.println("Data insertion failed.");
								            }
								            // Close the prepared statement
								            ps.close();
								            
								            product_home pobj = new product_home();
									        pobj.show();
									        pobj.cartupd();
									        pobj.srch();
									        pobj.product_hom();
									        dispose();
										}
										else {
											int q = (int) spinner.getValue();  // Get the selected quantity from the spinner
										    
											PreparedStatement ps = con.prepareStatement("UPDATE cart SET name = ?, cost = ?, quantity = ?, img21 = ? WHERE name='"+foodName+"'");
								            ps.setString(1, foodName);
								            ps.setString(2, cost);
								            ps.setInt(3, q);
								            ps.setString(4, img20);
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
									        pobj.product_hom();
									        dispose();
										}
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
								
							 SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1); // initial value, min value, max value, step
							 JSpinner spinner = new JSpinner(spinnerModel);
							 spinner.setEnabled(false);
							 spinner.setBounds(139+((i-4)*180), 267, 30, 20);
							 Product.add(spinner);
								
							 JButton remove_1 = new JButton("-");
							 remove_1.setFont(new Font("Tahoma", Font.BOLD, 20));
							 remove_1.addActionListener(new ActionListener() {
							 	public void actionPerformed(ActionEvent e) {
									quantity[index]--;
//									count_1.setText("" + quantity);
									spinner.setValue(quantity[index]);
									try {
										if(quantity[index]==0) {
											int q = (int) spinner.getValue();  // Get the selected quantity from the spinner
										    
											PreparedStatement ps = con.prepareStatement("DELETE from cart WHERE name='"+foodName+"'");
								            // Execute the insert statement
								            int rowsInserted = ps.executeUpdate();
								            
								            if (rowsInserted > 0) {
								                System.out.println("Data Deleted successfully.");
								            } else {
								                System.out.println("Data Deletion failed.");
								            }
								            // Close the prepared statement
								            ps.close();
								            
								            product_home pobj = new product_home();
									        pobj.show();
									        pobj.cartupd();
									        pobj.srch();
									        pobj.product_hom();
									        dispose();
										}
										else {
											int q = (int) spinner.getValue();  // Get the selected quantity from the spinner
										    
											PreparedStatement ps = con.prepareStatement("UPDATE cart SET name = ?, cost = ?, quantity = ?, img21 = ? WHERE name='"+foodName+"'");
								            ps.setString(1, foodName);
								            ps.setString(2, cost);
								            ps.setInt(3, q);
								            ps.setString(4, img20);
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
									        pobj.product_hom();
									        dispose();
										}
									}catch(Exception es) {
										System.out.println(es.getMessage());
									}
								}
							 });
							 	
							 JButton add_1 = new JButton("Buy");
							 add_1.addActionListener(new ActionListener() {
							 	public void actionPerformed(ActionEvent e) {
							 		remove_1.setBounds(85+((index-4)*180), 267, 43, 21);	
							 		//--first method call for pay summary panel--
									quantity[index]++;
									add_1.setText("+");
									add_1.setFont(new Font("Tahoma", Font.BOLD, 15));
//									count_1.setText("" + quantity);
									spinner.setValue(quantity[index]);
									try {
										if(quantity[index]==1) {
											//create statement
											int q = (int) spinner.getValue();  // Get the selected quantity from the spinner
										    
											PreparedStatement ps = con.prepareStatement("INSERT INTO cart (name, cost, quantity, img21) VALUES (?, ?, ?, ?)");
								            ps.setString(1, foodName);
								            ps.setString(2, cost);
								            ps.setInt(3, q);
								            ps.setString(4, img20);
								            // Execute the insert statement
								            int rowsInserted = ps.executeUpdate();
								            
								            if (rowsInserted > 0) {
								                System.out.println("Data inserted successfully.");
								            } else {
								                System.out.println("Data insertion failed.");
								            }
								            // Close the prepared statement
								            ps.close();
								            
								            product_home pobj = new product_home();
									        pobj.show();
									        pobj.cartupd();
									        pobj.srch();
									        pobj.product_hom();
									        dispose();
										}
										else {
											int q = (int) spinner.getValue();  // Get the selected quantity from the spinner
										    
											PreparedStatement ps = con.prepareStatement("UPDATE cart SET name = ?, cost = ?, quantity = ?, img21 = ? WHERE name='"+foodName+"'");
								            ps.setString(1, foodName);
								            ps.setString(2, cost);
								            ps.setInt(3, q);
								            ps.setString(4, img20);
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
									        pobj.product_hom();
									        dispose();
										}
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
