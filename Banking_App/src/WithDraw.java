
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;


public class WithDraw extends GUI {
	private static JLabel currentBal;
	private static JLabel currentBalTxt;
	private static JLabel amtLabel;
	private static JTextField amtBalTxt;
    private static JButton WithButton;
	private static JButton BackButton;

	
 
	public static void main() {
		JFrame frame = new JFrame("WithDraw");
		JPanel panel = new JPanel();
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.pink);
		panel.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
		

		currentBal = new JLabel("Current Balance :");	
	    currentBal.setBounds(40, 30, 120, 25);
		currentBal.setFont(new Font("Serif", Font.BOLD, 14));
		panel.add(currentBal);
		
		currentBalTxt = new JLabel("");
	    currentBalTxt.setBounds(160, 30, 130, 25);
	    currentBalTxt.setForeground(Color.blue);
	    panel.add(currentBalTxt);
	    
	    amtLabel = new JLabel("Enter Amount :");
	    amtLabel.setBounds(40, 80 , 120,25);
		amtLabel.setFont(new Font("Serif", Font.BOLD, 14));
	    panel.add(amtLabel);
	    
	    amtBalTxt = new JTextField("");
	    amtBalTxt.setBounds(160, 80, 150, 25);
	    panel.add(amtBalTxt);
	    
	    WithButton = new JButton("Withdraw");
	    WithButton.setBounds(140, 200, 100, 30);
		WithButton.setForeground(Color.red);
		WithButton.setFont(new Font("Serif", Font.BOLD, 14));
	    panel.add(WithButton);
	    
   	    String User = GUI.user;
   		try {
   			Class.forName("com.mysql.jdbc.Driver");
        	Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","");
    		Statement myState = myConn.createStatement();
       		ResultSet myResult = myState.executeQuery("select Balance from account_detail where UserId ='"+User+"';");
            while(myResult.next()) {  
               Double a = myResult.getDouble("Balance");
               currentBalTxt.setText(Double.toString(a));
                
			   }
   			
   		}catch(Exception e2) {
			e2.printStackTrace();
   			
   		}
	    	
	    WithButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent b ) {
	    		Double amt,Bal;
				Double Total;  
	    		try {
	    			
	    			amt = Double.parseDouble(amtBalTxt.getText());
	    			Bal = Double.parseDouble(currentBalTxt.getText());
	    			
	    		    Class.forName("com.mysql.jdbc.Driver");
		        	Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","");		
			    		
		        	if(Bal >= amt) {
	    				
		        		Total =Bal-amt;
		        		String query ="update account_detail set Balance = ? where UserId ='"+User+"';";      
		        		PreparedStatement ps =(PreparedStatement) myConn.prepareStatement(query);
		        		ps.setDouble(1,Total);
		        		ps.execute();
		        		frame.dispose();
		                WithDraw.main();
	    				
		    			JOptionPane.showMessageDialog(null, "Amount Successfully WithDrawed From Your Account!!!");
		    			
	    			}
	    			else {
		    			JOptionPane.showMessageDialog(null, "Insufficient Balance !!!");
		    			amtBalTxt.setText("");
		    			
	    			}
	    			
	    		}
	    		catch(Exception e){
	    			
	    			JOptionPane.showMessageDialog(null, "Please Enter Valid Amount !!!");
	    			
	    		}
	    			 
	      } 
	    });
	    
	    BackButton = new JButton("BACK");
	    BackButton.setBounds(260, 200, 90, 30);
	    BackButton.setForeground(Color.blue);
		BackButton.setFont(new Font("Serif", Font.BOLD, 14));
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
