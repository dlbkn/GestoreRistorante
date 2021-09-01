package restaurant;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import application.DynamicJTable;

public class GUICassa extends JFrame{
	PaymentHolder payments;
	DynamicJTable table;
	public JScrollPane scroll;
	
	public GUICassa(PaymentHolder payments) throws IOException {
		this.payments = payments;
		this.scroll = this.aggiornaTavoli();
		init();
	}
	private void init() {
		setTitle("CASSA");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JButton exit = new JButton("EXIT");
        
        JPanel p = new JPanel();
        
        p.setSize(420, 310);
        p.setLayout(null);
        
        
        
        Dimension size = exit.getPreferredSize();
        
        exit.setBounds(10, 235, size.width, size.height);
        
        add(p);
        p.add(exit);
        p.add(scroll);
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
		GUIRistorante r = new GUIRistorante();
		r.setVisible(true);
        this.dispose();
    }
	
	private JScrollPane aggiornaTavoli() throws IOException {
    	String[] colonne = new String[] {"Tavolo", "Prezzo"};
 
    	table = new DynamicJTable(payments, colonne);
    	table.setShowGrid(false);
        //table.setEnabled(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(0, 0);
        scroll.setSize(405,150);
        //scroll.setVisible(true);
                
    	return scroll;
    }
}
