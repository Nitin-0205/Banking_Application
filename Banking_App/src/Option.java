import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Option {
	private static JButton Check;
	private static JButton pin;
	private static JButton Deposit;
	private static JButton with;
	private static JButton Transfer;


	public static void main() {
		
		JFrame frame = new JFrame("Option");
		JPanel panel = new JPanel();
		frame.setSize(970,420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.pink);
		panel.setBorder(BorderFactory.createLineBorder(Color.cyan,2));
		
		Check = new JButton("CHECK  ACCOUNT  DETAIL");
		Check.setForeground(Color.blue);
	    Check.setFont(new Font("Serif", Font.BOLD, 16));
		Check.setBounds(175 , 60, 600 ,45);
		Check.setBorder(BorderFactory.createLineBorder(Color.blue,1));		
		panel.add(Check);
		Check.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e1 ) {
	    		frame.setVisible(false);
	    		Detail.main();
	    			 
	    	}
		});
		
		pin = new JButton("CHANGE  PASSWORD");
		pin.setForeground(Color.red);
	    pin.setFont(new Font("Serif", Font.BOLD, 16));
		pin.setBounds(50 , 150, 400 ,45);
		pin.setBorder(BorderFactory.createLineBorder(Color.blue,1));
		panel.add(pin);
		
		pin.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e2 ) {
	    		frame.setVisible(false);
	    		ChangePassword.main();
	    			 
	    	}
		});
		
		Deposit = new JButton("DEPOSITE");
		Deposit.setBounds(500 , 150, 400 ,45);
		Deposit.setForeground(Color.red);
	    Deposit.setFont(new Font("Serif", Font.BOLD, 16));
		Deposit.setBorder(BorderFactory.createLineBorder(Color.blue,1));
		panel.add(Deposit);
		
		Deposit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e3 ) {
	       		frame.dispose();
	    		Deposite.main();
	    			 
	    	}
		});
		
		with = new JButton("WITHDRAW ");
		with.setBounds(50 , 240, 400 ,45);
		with.setForeground(Color.red);
	    with.setFont(new Font("Serif", Font.BOLD, 16));
		with.setBorder(BorderFactory.createLineBorder(Color.blue,1));
		panel.add(with);
		
		with.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e4 ) {
	    		frame.setVisible(false);
	    		WithDraw.main();
	    			 
	    	}
		});
		
		Transfer = new JButton("BANK  TRANSFER");
		Transfer.setForeground(Color.red);
	    Transfer.setFont(new Font("Serif", Font.BOLD, 16));
		Transfer.setBounds(500 , 240, 400 ,45);
		Transfer.setBorder(BorderFactory.createLineBorder(Color.blue,1));
		panel.add(Transfer);
		Transfer.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e5 ) {
	    		frame.setVisible(false);
	    		Bank_Transfer.main();
	    			 
	    	}
		});
				
		frame.setVisible(true);

	}
	

}
