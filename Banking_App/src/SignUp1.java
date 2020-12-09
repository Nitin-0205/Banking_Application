import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUp1 {
	private static JLabel UserId ;
	private static JTextField UserTxt;
	private static JLabel pass ;
	private static JTextField passTxt;
	private static JLabel conpass ;
	private static JTextField conpassTxt;
	private static JButton button;
	
	static String User;
	static String Pas;
    static String ConPass;

	

	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sign Up");
		frame.setSize(500,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3, true));
		panel.setBackground(Color.cyan);

		
		UserId = new JLabel("User ID :");
		UserId.setBounds(80,50,100,25);
		UserId.setFont(new Font("Serif", Font.BOLD, 14));
		panel.add(UserId);
		
		UserTxt = new JTextField();
		UserTxt.setBounds(220,50,150,25);
		panel.add(UserTxt);
		
		pass = new JLabel("Password :");
		pass.setBounds(80,110,100,25);
		pass.setFont(new Font("Serif", Font.BOLD, 14));
		panel.add(pass);
		
		passTxt = new JTextField();
		passTxt.setBounds(220,110,150,25);
		panel.add(passTxt);
		
		conpass = new JLabel("Confirm Password :");
		conpass.setBounds(80,170,150,25);
		conpass.setFont(new Font("Serif", Font.BOLD, 14));
		panel.add(conpass);
		
		conpassTxt = new JTextField();
		conpassTxt.setBounds(220,170,150,25);
		panel.add(conpassTxt);
		
		button = new JButton("SUBMIT");
		button.setBounds(200,250,100,35);
		button.setForeground(Color.blue);
		button.setFont(new Font("Serif", Font.BOLD, 14));
		button.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
	    panel.add(button);
	    button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User = UserTxt.getText();
				Pas = passTxt.getText();
				ConPass = conpassTxt.getText();
				

				try {
					Class.forName("com.mysql.jdbc.Driver");	
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","");

					if(User.equals("") || Pas.equals("") || ConPass.equals("") ) {
						
							JOptionPane.showMessageDialog(null,"Some Field is Empty !!!","Alert", JOptionPane.WARNING_MESSAGE);
						
					}
					else if(Pas.equals(ConPass)) {
					
						if(Pas.length() >= 8) {
								
							String query = "INSERT INTO signup(UserId , Password) Values(?,?);";							
							PreparedStatement ps = con.prepareStatement(query);						
							ps.setString(1,User);
							ps.setString(2,ConPass);
							int r = ps.executeUpdate();
							
							if(r == 1) {
									frame.dispose();
									JOptionPane.showMessageDialog(null,"User Id and Password Sucessfull Saved !!!");	
									SignUp_Detail.main();
							}
							else {
									JOptionPane.showMessageDialog(null,"User Id Already Exist !!!","Alert",JOptionPane.WARNING_MESSAGE);
								}													
						}		
						else {
								passTxt.setText("");
								conpassTxt.setText("");
								JOptionPane.showMessageDialog(null,"Password must Be atleast 8 Character");
							}
					    }	
					else {
							passTxt.setText("");
							conpassTxt.setText("");
							JOptionPane.showMessageDialog(null,"Password And Confirm Password Must Be Same");
					    }
						
				} catch (Exception e1) {
					UserTxt.setText("");
					passTxt.setText("");
					conpassTxt.setText("");				
					JOptionPane.showMessageDialog(null,"User Id Already Exist","Alert",JOptionPane.WARNING_MESSAGE);
				}
				
			}
	    	
	    });
		
		frame.add(panel);
		frame.setVisible(true);
		
	}

}
