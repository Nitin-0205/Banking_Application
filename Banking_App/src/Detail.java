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
import javax.swing.JPanel;

public class Detail extends GUI{
	private static JLabel Name ;
	private static JLabel NameTxt ; 
	private static JLabel AccountNo ; 
	private static JLabel AccountNoTxt ; 
	private static JLabel Ifc ; 
	private static JLabel IfcTxt ;
	private static JLabel Email ; 
	private static JLabel EmailTxt ; 
	private static JLabel Balance ; 
	private static JLabel BalanceTxt ; 
	private static JLabel Type ; 
	private static JLabel TypeTxt ;
	private static JLabel Add ; 
	private static JLabel AddTxt ;
    private static JButton BackButton;


	

	public static void main() {
		JFrame frame = new JFrame("Account Detail");
		JPanel panel = new JPanel();

		frame.setSize(540,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLineBorder(Color.cyan, 2));
		panel.setBackground(Color.pink);

		
		Name = new JLabel("Name :");
		Name.setBounds(60,40,50,25);
		Name.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Name);
		
		NameTxt = new JLabel("");
		NameTxt.setBounds(185,40,100,25);
		NameTxt.setForeground(Color.blue);
		NameTxt.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(NameTxt);
		
		AccountNo = new JLabel("Account Number :");
		AccountNo.setBounds(60,80,140,25);
		AccountNo.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(AccountNo);
		
		AccountNoTxt = new JLabel("");
		AccountNoTxt.setBounds(185,80,200,25);
		AccountNoTxt.setForeground(Color.blue);
		AccountNoTxt.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(AccountNoTxt);
		
		Ifc = new JLabel("IFSC code :");
		Ifc.setBounds(60,120,100,25);
		Ifc.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Ifc);
		
		IfcTxt = new JLabel("");
		IfcTxt.setBounds(185,120,100,25);
		IfcTxt.setForeground(Color.blue);
		IfcTxt.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(IfcTxt);
		
		Email = new JLabel("Email ID :");
		Email.setBounds(60,160,80,25);
		Email.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Email);
		
		EmailTxt = new JLabel("");
		EmailTxt.setBounds(185,160,200,25);
		EmailTxt.setForeground(Color.blue);
		EmailTxt.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(EmailTxt);
		
		Balance = new JLabel("Balance :");
		Balance.setBounds(60,200,80,25);
		Balance.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Balance);
		
		BalanceTxt = new JLabel("");
		BalanceTxt.setBounds(185,200,200,25);
		BalanceTxt.setForeground(Color.blue);
		BalanceTxt.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(BalanceTxt);
		
		Type = new JLabel("Account Type :");
		Type.setBounds(60,240,150,25);
		Type.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Type);
		
		TypeTxt = new JLabel("");
		TypeTxt.setBounds(185,240,150,25);
		TypeTxt.setForeground(Color.blue);
		TypeTxt.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(TypeTxt);
		
		Add = new JLabel("Address :");
		Add.setBounds(60,280,150,25);
		Add.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Add);
		
		AddTxt = new JLabel("");
		AddTxt.setBounds(185,280,500,25);
		AddTxt.setForeground(Color.blue);
		AddTxt.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(AddTxt);
		
		
		try {
			String User = GUI.user;
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","");
			Statement myState = myConn.createStatement();
			String query ="select * from account_detail where UserId = '"+User+"'";
			ResultSet myResult = myState.executeQuery(query);
			while(myResult.next()) {
				
				String name = (myResult.getString("Name"));
				NameTxt.setText(name);
				
				String account = (myResult.getString("Account_Number"));
				AccountNoTxt.setText(account);
				
				String email = (myResult.getString("Email"));
				EmailTxt.setText(email);
				
				String ifc = (myResult.getString("IFSC"));
				IfcTxt.setText(ifc);
				
				String Bal = (myResult.getString("Balance"));
				BalanceTxt.setText(Bal);
				
				String Tp = (myResult.getString("Account_Type"));
				TypeTxt.setText(Tp);
				
				String ad = (myResult.getString("Address"));
				AddTxt.setText(ad);
				
				
				
			}
			
		}
		catch(Exception e) {
			
			e.printStackTrace();			
		}
		
		BackButton = new JButton("BACK");
	    BackButton.setBounds(380, 370, 100, 30);
	    BackButton.setForeground(Color.blue);
	    BackButton.setFont(new Font("Serif", Font.BOLD, 16));
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
