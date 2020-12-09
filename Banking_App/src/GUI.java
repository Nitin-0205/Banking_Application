import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI{
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passTxt;
	private static JButton Button;
	static String user;
	static String  pass;

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Banking Application");
		JPanel panel =new JPanel();
		frame.setSize(400,320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.pink);
		panel.setBorder(BorderFactory.createLineBorder(Color.magenta, 2));
		
		userLabel =new JLabel("User ID :");
		userLabel.setFont(new Font("Serif", Font.BOLD, 14));
		userLabel.setBounds(60,50,80,45);
		panel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(140,58,165,24);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password :");
		passwordLabel.setFont(new Font("Serif", Font.BOLD, 15));
		passwordLabel.setBounds(60,100,80,70);
		panel.add(passwordLabel);
		
		passTxt = new JPasswordField("");
		passTxt.setBounds(140,120,165,24);
		panel.add(passTxt);
		
		Button = new JButton("Login");
		Button.setForeground(Color.red);
		Button.setFont(new Font("Serif", Font.BOLD, 17));
		Button.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		Button.setBounds(150, 210, 100, 32);
		panel.add(Button);
		
		
    
		Button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		try {
	    			user = userText.getText();
		            pass = passTxt.getText();
		    		
		    		Class.forName("com.mysql.jdbc.Driver");
		    		Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","");
		    		if(user.equals("") || pass.equals("")) {
		    			
					     JOptionPane.showMessageDialog(null,"Some Field is Empty !!!","Alert",JOptionPane.WARNING_MESSAGE );
		    			
		    		}
		    		else {
		    			
		    			String query ="select * from account_detail where UserId = ? and Password =? ";
			    		
			    		PreparedStatement ps = Conn.prepareStatement(query);					
			    		ps.setString(1,user);
			    		ps.setString(2,pass);
						ResultSet myResult = ps.executeQuery();	
						
						if(myResult.next()) {
				       		frame.dispose();
							Option.main();
						}
						else {
						     userText.setText("");
						     passTxt.setText("");					
						     JOptionPane.showMessageDialog(null,"Incorrect UserName or Password !!!");
						}
		    			
		    		}
		    				
	    		}
	    		catch(Exception e1) {	
	    			
	    			e1.printStackTrace();
	    		}
	    	    
	    	}
	    	
		});
		
		frame.setVisible(true);

	}

}
