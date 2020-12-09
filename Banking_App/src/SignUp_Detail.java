import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUp_Detail extends SignUp1{
	private static JLabel Name;
	private static JTextField NameTxt;
	private static JLabel Account;
	private static JTextField AccountTxt;
	private static JLabel ifc;
	private static JTextField ifcTxt;
	private static JLabel Type;
	private static JComboBox<String> TypeT;
	private static JLabel email;
	private static JTextField emailTxt;
	private static JLabel Phone;
	private static JTextField PhoneTxt;
	private static JLabel Add;
	private static JTextField AddTxt;
	private static JButton Submit;
	

	public static void main() {
		
		JFrame frame = new JFrame("Account Detail");
		JPanel panel = new JPanel();
		
		frame.setSize(800,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.green, 2, true));
		panel.setBackground(Color.cyan);

		
		Name = new JLabel("Full Name :");
		Name.setBounds(30,30,100,25);
		Name.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Name);
		
		NameTxt = new JTextField();
		NameTxt.setBounds(150,30,200,25);
		panel.add(NameTxt);
		
		Phone = new JLabel("Phone Number :");
		Phone.setBounds(30,90,150,25);
		Phone.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Phone);
		
		PhoneTxt = new JTextField(10);
		PhoneTxt.setBounds(150,90,100,25);
		panel.add(PhoneTxt);
		
		email = new JLabel("Email Id :");
		email.setBounds(30,150,150,25);
		email.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(email);
		
		emailTxt = new JTextField();
		emailTxt.setBounds(150,150,200,25);
		panel.add(emailTxt);
		
		Add = new JLabel("Address :");	
		Add.setBounds(30,210,150,25);
		Add.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Add);
		
		AddTxt = new JTextField();
		AddTxt.setBounds(150,210,200,30);
		panel.add(AddTxt);
		
		
		Account = new JLabel("Account Number :");
		Account.setBounds(390,30,200,25);
		Account.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Account);
		
		AccountTxt = new JTextField();
		AccountTxt.setBounds(520,30,200,25);
		panel.add(AccountTxt);
		
		ifc = new JLabel("IFSC code :");
		ifc.setBounds(390,90,100,25);
		ifc.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(ifc);
		
		ifcTxt = new JTextField(5);
		ifcTxt.setBounds(520,90,90,25);
		panel.add(ifcTxt);
		
		Type = new JLabel("Account Type :");
		Type.setBounds(390,150,100,25);
		Type.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(Type);		
		
		String[] s = {"Saving","Current"};
		TypeT = new JComboBox<String>(s);	
		TypeT.setBounds(520,150,150,25);
		TypeT.setFont(new Font("Serif", Font.BOLD, 15));
		panel.add(TypeT);
	
				
		Submit = new JButton("SUBMIT");
		Submit.setBounds(350, 290, 100, 35);
		Submit.setForeground(Color.blue);
		Submit.setFont(new Font("Serif", Font.BOLD, 15));
		Submit.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		panel.add(Submit);
		Submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","");
					
					String Nam = NameTxt.getText();
					String pho = PhoneTxt.getText();
					String mail = emailTxt.getText();
					String add = AddTxt.getText();
					String acc = AccountTxt.getText();
					String cod = ifcTxt.getText();
					
					if( Nam.equals("") || pho.equals("") || mail.equals("") || add.equals("") || acc.equals("") || cod.equals("")) {
						JOptionPane.showMessageDialog(null, "Some Field Is Empty !!!", "Alert", JOptionPane.WARNING_MESSAGE);
					}
					else if((acc.length() > 12) || (acc.length() < 12)) {
						AccountTxt.setText("");
						JOptionPane.showMessageDialog(null, "Account Number Must contain 12 Digits !!!");	
						
					}
					else if((pho.length() > 10) || (pho.length() < 10)) {
						AccountTxt.setText("");
						JOptionPane.showMessageDialog(null, "Phone Number Must contain 10 Digits !!!");
						}
					else{
						
					    String query = "INSERT INTO account_detail(UserId , Password , Name ,Account_Number , IFSC , Account_Type , Address , Email ) values(?, ?, ?, ?, ?, ?, ?, ?) ;";
					    PreparedStatement ps = con.prepareStatement(query);
					    
					    String Id = SignUp1.User;
					    String  pass = SignUp1.Pas;					   

      					ps.setString(1,Id);
      					ps.setString(2,pass);
		    			ps.setString(3,Nam.toLowerCase());
		    			ps.setString(4,AccountTxt.getText());
			    		ps.setString(5,ifcTxt.getText());
				    	ps.setString(6,(String) TypeT.getSelectedItem());
				        ps.setString(7,AddTxt.getText());
				        ps.setString(8,emailTxt.getText());
				        int co = ps.executeUpdate();
				        if(co == 1) {
				        	
							JOptionPane.showMessageDialog(null, "SIGN UP Successful  !!!", "Confirm ", JOptionPane.INFORMATION_MESSAGE);		
				        	frame.dispose();
				        }else {
				        	
				        }
					    }
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Invalid Entry !!!", "Alert", JOptionPane.WARNING_MESSAGE);		
					e1.printStackTrace();
				}				
				
			}
			
		});
		

		frame.setVisible(true);
		
	}

}
