package GUI;

import restaurant.PaymentHolder;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

public class GUICash extends JFrame{
	private PaymentHolder payments;
	private DynamicJTable table;
	private JScrollPane scroll;
	private JPanel mainPanel;
	
	public GUICash(PaymentHolder payments) throws IOException {
		this.payments = payments;
		this.scroll = this.reloadPayments();
		init();
	}
	
	private void init() {
		setTitle("CASH");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JButton exit = new JButton("EXIT");
        JButton remove = new JButton("REMOVE"); 
        mainPanel = new JPanel();
        
        mainPanel.setSize(420, 310);
        mainPanel.setLayout(null);
        
        
        
        Dimension size = exit.getPreferredSize();
        
        exit.setBounds(10, 235, size.width, size.height);
        remove.setBounds(110, 235, 90, size.height);
        
        add(mainPanel);
        mainPanel.add(exit);
        mainPanel.add(remove);
        mainPanel.add(scroll);
        
        
        remove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){            	
                try {
					removeButtonActionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }); 
        
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){            	
                try {
					exitButtonActionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }); 
        
	}
	
	private void exitButtonActionPerformed(ActionEvent e) throws FileNotFoundException, IOException{
        this.dispose();
    }
	
	private void removeButtonActionPerformed(ActionEvent e) throws IOException {
		try {
			payments.removePayment(Integer.parseInt(table.getSelectedKey().toString()));
			mainPanel.remove(scroll);
			scroll = reloadPayments();
			JOptionPane.showMessageDialog(null, "Receipt processing");
			mainPanel.add(scroll);
		} catch (Exception e2) {
			System.out.println(e);
		}
	}
	
	private JScrollPane reloadPayments() throws IOException {
    	String[] column = new String[] {"Table", "Price"};
 
    	table = new DynamicJTable(payments, column);
    	table.setShowGrid(false);
        //table.setEnabled(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(0, 0);
        scroll.setSize(405,150);
        //scroll.setVisible(true);
                
    	return scroll;
    }
}
