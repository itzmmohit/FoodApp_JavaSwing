import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class index extends JFrame {

    private JPanel contentPane;
    private JTextField searchBar;
    private JPanel srchResult;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    index frame = new index();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public index() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 728, 427);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        searchBar = new JTextField();
        searchBar.setBounds(226, 22, 258, 25);
        contentPane.add(searchBar);
        searchBar.setColumns(10);

        srchResult = new JPanel();
        srchResult.setBackground(Color.WHITE);
        srchResult.setBounds(226, 50, 258, 206);
        contentPane.add(srchResult);
        srchResult.setVisible(true);

        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchBar.getText();
                searchText=searchText.trim();
                if(searchText.length()>0) {
                    srchResult.setVisible(true);
                    srchResult.removeAll();
                    try {
                    	int j=-1;
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false", "root", "");

                        Statement stm1 = con.createStatement();
                        String sql1 = "SELECT * FROM foodbase WHERE food LIKE '%" + searchText + "%'";
                        ResultSet rs = stm1.executeQuery(sql1);
                        System.out.println("--Connected from Search Section--");
                        
                        for(int i=0;i<8;i++) {
                        	if(rs.next()) {
                            	
//                           	 while (rs.next()) 
                        		{
                           		 	j=7+(50*i);
                           	        String foodName = rs.getString("food");
                           	        String cost = rs.getString("cost");
                           	        String img21 = rs.getString("img20");
                           	        
                           	        System.out.println(j);
                           	        
                           	        JPanel itemPanel = new JPanel();
                           	        itemPanel.setBounds(0, 0, 258, 206);
                           	        itemPanel.setBackground(new Color(255, 255, 255));
                           	        itemPanel.setLayout(null);
                           	
                           	        JLabel imgLabel = new JLabel();
                           	        imgLabel.setIcon(new ImageIcon(img21));
                           	        imgLabel.addMouseListener(new MouseAdapter() {
                           	        	@Override
                           	        	public void mouseClicked(MouseEvent e) {
                           	        		Food_Desc fobj=new Food_Desc();
                           	        		fobj.show();
                           	        		dispose();
                           	        	}
                           	        });
                           	        imgLabel.setBounds(5, j, 40, 40);
                           	        imgLabel.setText("");
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
                           	        srchResult.setLayout(null);
                           	        nameLabel.setBounds(52, j, 149, 40);
                           	        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                           	        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
                           	        itemPanel.add(nameLabel);
                           	
                           	        JLabel costLabel = new JLabel("₹" + cost);
                           	        costLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
                           	        costLabel.setBounds(211, j, 37, 40);
                           	        costLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                           	        itemPanel.add(costLabel);
//                           	       
//                           	        JSeparator separator = new JSeparator();
//                           	        separator.setBounds(9, j, 243, 1);
//                           	        itemPanel.add(separator);
                           	        
                           	        srchResult.add(itemPanel);
                           	        
                                }
                           }
                           else {
                           	JPanel itemPanel = new JPanel();
                               itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                               itemPanel.setBackground(Color.gray);
                           	
                           	JLabel nameLabel = new JLabel("No Search Result to Display");
                               nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
                               itemPanel.add(nameLabel);
                               
                               srchResult.add(itemPanel);

                           }
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
}
        
//        String foodName = ("food");
//        String cost = ("169");
//        String img21 = ("jpg");
//
//        JPanel itemPanel = new JPanel();
//        itemPanel.setBounds(0, 0, 258, 206);
//        itemPanel.setBackground(new Color(255, 255, 255));
//        itemPanel.setLayout(null);
//
//        JLabel imgLabel = new JLabel();
//        imgLabel.setIcon(new ImageIcon("C:\\Users\\mmmeh\\OneDrive\\Desktop\\3rd Trimester\\Java Img\\Fooditems\\burger20.jpg"));
//        imgLabel.addMouseListener(new MouseAdapter() {
//        	@Override
//        	public void mouseClicked(MouseEvent e) {
//        	}
//        });
//        imgLabel.setBounds(5, 7, 40, 40);
//        imgLabel.setText("");
//        itemPanel.add(imgLabel);
//
//        JLabel nameLabel = new JLabel(foodName);
//        nameLabel.addMouseListener(new MouseAdapter() {
//        	@Override
//        	public void mouseClicked(MouseEvent e) {
//        	}
//        });
//        srchResult.setLayout(null);
//        nameLabel.setBounds(52, 7, 149, 40);
//        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
//        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
//        itemPanel.add(nameLabel);
//
//        JLabel costLabel = new JLabel("₹" + cost);
//        costLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
//        costLabel.setBounds(211, 7, 37, 40);
//        costLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//        itemPanel.add(costLabel);
//       
//        
//      
//        
//        JSeparator separator = new JSeparator();
//        separator.setBounds(9, 51, 243, 1);
//        itemPanel.add(separator);
//        
//        srchResult.add(itemPanel);
//        
//        
//        
//    }
//}
