import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

public class ChangePassword extends GUI {
	private static JLabel PrevPass;
	private static JTextField PrevPassTxt;
	private static JLabel NewPass;
	private static JTextField NewPassTxt;
	private static JLabel conNewPass;
	private static JPasswordField conNewPassTxt;
	private static JButton 	BackButton;
	private static JButton 	Submit;



	public static void main() {
		JFrame frame = new JFrame();
		JPanel panel =new JPanel();
		frame.setSize(500,380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
        panel.setLayout(null);
        panel.setBackground(Color.pink);
		panel.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		
		PrevPass =new JLabel("Enter  Previous  Password :");
		PrevPass.setBounds(50,50,180,25);
		PrevPass.setFont(new Font("Serif", Font.BOLD, 14));
		panel.add(PrevPass);
		
		PrevPassTxt= new JTextField(20);
		PrevPassTxt.setBounds(250,50,150,25);
		panel.add(PrevPassTxt);
		
		NewPass =new JLabel("Enter  New  Password :");
		NewPass.setBounds(50,120,180,24);
		NewPass.setFont(new Font("Serif", Font.BOLD, 14));
		panel.add(NewPass);
		
		NewPassTxt= new JTextField(20);
		NewPassTxt.setBounds(250,120,150,24);
		panel.add(NewPassTxt);	
		
		conNewPass =new JLabel("Confirm  New  Password :");
		conNewPass.setBounds(50,190,180,24);
		conNewPass.setFont(new Font("Serif", Font.BOLD, 14));
		panel.add(conNewPass);
		
		conNewPassTxt= new JPasswordField(20);
		conNewPassTxt.setBounds(250,190,150,24);
		panel.add(conNewPassTxt);
		
		Submit = new JButton("SUBMIT");
		Submit.setBounds(280, 280, 90, 30);
		Submit.setForeground(Color.red);
		Submit.setFont(new Font("Serif", Font.BOLD, 13));
	    panel.add(Submit);

	    
	    Submit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		
	    		try {
	    			String Prevpass = PrevPassTxt.getText();
		    		String Newpass = NewPassTxt.getText();
		    		String Conpass = conNewPassTxt.getText();
		    		
	    			Class.forName("com.mysql.jdbc.Driver");
	        		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","");
	    			String userPass = GUI.pass;
	    			
	    			if (Prevpass.equals("") || Newpass.equals("") || Conpass.equals("")){
						JOptionPane.showMessageDialog(null,"Some Field is Empty !!!","Alert",JOptionPane.WARNING_MESSAGE);
						PrevPassTxt.setText("");
						NewPassTxt.setText("");
						conNewPassTxt.setText("");
    			    }
	    			else if(Prevpass.equals(userPass)) {
	    				
	    				 if(Newpass.equals(Conpass)) {
	    					 
	    					 if(Newpass.length() >= 8) {
	    						 
	    						 int co = JOptionPane.showConfirmDialog(null,"Do You Really Want To Update Your Password !!!","Confirm",JOptionPane.YES_NO_OPTION);
	    						 if(co == JOptionPane.YES_OPTION) {
	    							 
	    							 String query ="update account_detail set Password ='"+Newpass+"'where Password ='"+Prevpass+"';";
			    					 PreparedStatement myState =(PreparedStatement) myConn.prepareStatement(query);        		
			    					 myState.executeUpdate();
			    					 JOptionPane.showMessageDialog(null,"Password Update Successfull !!!");
			    					 frame.dispose();
	    							 
	    						 }else {
	    							 ChangePassword.main();
	    							 
	    						 }
		    					 
	    					 }
	    					 else {
		    					JOptionPane.showMessageDialog(null,"Password Must Contain Atleast 8 Characters !!!","Alert",JOptionPane.WARNING_MESSAGE);
		 						NewPassTxt.setText("");
		 						conNewPassTxt.setText("");
								
							 }
	    				 }
	    				 else {
		    					JOptionPane.showMessageDialog(null,"New password And Confirm Password Must Same  !!!","Alert",JOptionPane.WARNING_MESSAGE);
		 						NewPassTxt.setText("");
		 						conNewPassTxt.setText("");
			
	    				 }	 
	    			}
	    			else {JOptionPane.showMessageDialog(null,"Incorrect Password !!!","Alert",JOptionPane.WARNING_MESSAGE);
					PrevPassTxt.setText("");
					NewPassTxt.setText("");
					conNewPassTxt.setText("");}
	    				    				
	    		}
	    		catch(Exception e1)
	    		{
	    			System.out.println(e1);

	    		}
	    			    		
	    	} 
	    });
	    
		BackButton = new JButton("BACK");
	    BackButton.setBounds(380, 280, 80, 30);
	    BackButton.setForeground(Color.blue);
		BackButton.setFont(new Font("Serif", Font.BOLD, 13));
	    panel.add(BackButton);
	    
	    BackButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	       		     frame.dispose();
	    			 Option.main();
	    		} 
	    });
		
		frame.setVisible(true);

	}
}
