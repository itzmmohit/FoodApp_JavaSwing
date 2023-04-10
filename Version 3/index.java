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
import javax.swing.JButton;
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
    private JPanel Base;

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
	                     		 
	                              System.out.println(foodName);
	                              
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
}