import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Bank_Transfer extends GUI {
	private static JLabel fromAcc;
	private static JLabel fromAccTxt;
	private static JLabel bal;
	private static JLabel balTxt;
	private static JLabel toAcc ;
	private static JTextField toAccTxt ;
	private static JLabel contoAcc;
	private static JTextField contoAccTxt;
	private static JLabel Amt ;
	private static JTextField AmtTxt ;
	private static JButton ProceedButton;
	private static JButton BackButton;


	public static void main() {
		JFrame frame = new JFrame("Bank Transfer");
		frame.setSize(500,450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setBorder(BorderFactory.createLineBorder(Color.orange, 2));
		frame.add(panel);
		panel.setLayout(null);
		
		
		fromAcc = new JLabel("From Account :");
		fromAcc.setBounds(50,30,150 , 25);
		fromAcc.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(fromAcc);
		
		fromAccTxt = new JLabel("");
		fromAccTxt.setBounds(190,30,200 , 25);
		fromAccTxt.setForeground(Color.blue);
		fromAccTxt.setFont(new Font("Serif", Font.BOLD, 15));

		panel.add(fromAccTxt);
		
		bal = new JLabel("Available Balance :");
		bal.setBounds(50,80,130 , 25);
		bal.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(bal);
		
		balTxt = new JLabel("");
		balTxt.setBounds(190,80,200, 25);
		balTxt.setForeground(Color.blue);
		panel.add(balTxt);
		
		toAcc = new JLabel("To Account :");
		toAcc.setBounds(50,140,100 , 25);
		toAcc.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(toAcc);
		
		toAccTxt = new JTextField("");
		toAccTxt.setBounds(180,140,200 , 25);
		panel.add(toAccTxt);
		
		contoAcc = new JLabel("Confirm Account :");
		contoAcc.setBounds(50,200,150 , 25);
		contoAcc.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(contoAcc);
		
		contoAccTxt = new JTextField("");
		contoAccTxt.setBounds(180,200,200 , 25);
		panel.add(contoAccTxt);
		
		Amt = new JLabel("Amount :");
		Amt.setBounds(50,260,150 , 25);
		Amt.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Amt);
		
		AmtTxt = new JTextField("");
		AmtTxt.setBounds(180,260,150 , 25);
		panel.add(AmtTxt);
		
		String Use = GUI.user;
	    
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root",""); 
		    String query = "SELECT  Account_Number , Balance  FROM account_detail WHERE UserId  = '"+Use+"';";        
		    Statement ps = con.createStatement(); 
		    ResultSet rs = ps.executeQuery(query); 
		    while (rs.next()) {
		    	String facc = rs.getString("Account_Number");
		    	String avlbal =  Double.toString(rs.getDouble("Balance"));		    	
			    fromAccTxt.setText(facc);
			    balTxt.setText(avlbal);
	
                  
		    }    
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
   	    
		ProceedButton = new JButton("PROCEED");
		ProceedButton.setBounds(285, 350, 90, 30);
		ProceedButton.setForeground(Color.red);
	    panel.add(ProceedButton);
	    
		ProceedButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		try {
	    			 String acc = toAccTxt.getText();
	    		     String conacc = contoAccTxt.getText();
	    		     Double amount = Double.parseDouble(AmtTxt.getText());
	    			
	    			 Class.forName("com.mysql.jdbc.Driver");
	    		     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root",""); 

	    		     if(acc.equals("") || conacc.equals("") || amount.equals("")) {
	    		    	 JOptionPane.showMessageDialog(null, "Some Field Is Empty !!!","Alert",JOptionPane.WARNING_MESSAGE);
	    		     }
	    		     else if(!acc.equals(conacc)) {
	    		    	 JOptionPane.showMessageDialog(null, "To Account And ConFirm Must Same !!!","Alert",JOptionPane.WARNING_MESSAGE);   	 
	    		     }else if (acc.length() !=12) {
	    		    	 JOptionPane.showMessageDialog(null, "Account Number Must Contain 12 Digits !!! ","Alert",JOptionPane.WARNING_MESSAGE);   	  
	    		     }else {
		    		     String query = "SELECT  Account_Number , Balance FROM account_detail WHERE Account_Number = ?  ;";
		    		     PreparedStatement ps = con.prepareStatement(query); 
		    		     ps.setString(1,acc);
		    			 ResultSet rs = ps.executeQuery();	
		    			 
							if(rs.next()) {
								Double ReceverBal  = rs.getDouble("Balance");
							    String qury = "SELECT Balance  FROM account_detail WHERE UserId = '"+Use+"';";     
							    Statement stmt = con.createStatement();    
							    ResultSet result = stmt.executeQuery(qury); 
							    
							    if(result.next()) {
							    	Double SenderBal = result.getDouble("Balance");
							    	
							    	if( SenderBal >= amount) {
							    		int dialogBut = JOptionPane.YES_NO_OPTION ;
							    		int Result = JOptionPane.showConfirmDialog(null, "Do You Really Want To Proceed !!!","Alert",dialogBut);
							    		
							    		if(Result == JOptionPane.YES_OPTION) {
							    			
							    			Double TotWith = SenderBal-amount;
							        		String qy ="update account_detail set Balance = ? where UserId = '"+Use+"';";   
							        		PreparedStatement pstm =con.prepareStatement(qy);
							        		pstm.setDouble(1, TotWith);
							        		int r = pstm.executeUpdate();
							        		
							        		if(r == 1) {
							        			Double TotDep = ReceverBal+amount;
								        		String q ="update account_detail set Balance = ? where Account_Number = ?;";
								        		PreparedStatement pstatem =con.prepareStatement(q);
								        		pstatem.setDouble(1, TotDep);
								        		pstatem.setString(2,acc);
								        		int Finresult = pstatem.executeUpdate();
								        		
								        		if(Finresult == 1) {
									    			JOptionPane.showMessageDialog(null, " Amount Successful Transfer !!!","Confirm",JOptionPane.INFORMATION_MESSAGE);
									    			frame.dispose();
									    			Bank_Transfer.main();
								        			
								        		}
							        			
							        		}
							        		
						    							    			
							    		}
					                  
							    }else {
					    			JOptionPane.showMessageDialog(null, "Insufficient Amount !!!","Alert",JOptionPane.WARNING_MESSAGE);		    				 
							    	
							    	} 
							    }
								
								
								
								
		    			 }else {
				    			JOptionPane.showMessageDialog(null, "Account Number Not Exist !!!","Alert",JOptionPane.WARNING_MESSAGE);		    				 

		    			 }
		    			 
	    		     }
	    		     	    		        
	    		}
	    		catch (Exception e1) {
	    			   		    	    
	    			JOptionPane.showMessageDialog(null, "Invalid Entry !!!","Alert",JOptionPane.WARNING_MESSAGE);
	    			e1.printStackTrace();

	    		}
	    	    		
	    	} 
	    });
		
	    BackButton = new JButton("BACK");
	    BackButton.setBounds(380, 350, 70, 30);
	    BackButton.setForeground(Color.blue);
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
