package restaurant;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

import application.DynamicJTable;

public class GUICuoco extends JFrame{
	
	public OrderHolder orderHolder;
	public JScrollPane scroll;
	public DynamicJTable table;
	public PaymentHolder payments;
	
	
	public GUICuoco(OrderHolder orderHolder) throws IOException {
		this.orderHolder = orderHolder;
		this.scroll = this.aggiornaTavoli();
		this.payments = new PaymentHolder();
		init();
	}

	private void init() {
		setTitle("CUOCO");
        setSize(520, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		     
        // Creation of panel
        //Creazione del panel
        JPanel p = new JPanel();
        
        p.setSize(520, 310);
        p.setLayout(null);
        
        
        JButton exit = new JButton("EXIT");
        JButton invio = new JButton("INVIO");
        
        Dimension size = invio.getPreferredSize();
        
        exit.setBounds(10, 235, size.width, size.height);
        invio.setBounds(430, 235, size.width, size.height);
        
        //DynamicJTable table = new DynamicJTable(orderHolder,colonne);
        //add(scroll);
        scroll.setLocation(0, 0);
        scroll.setSize(505,150);
        scroll.setVisible(true);
        //text.setEditable(false);
        table.setShowGrid(false);
        
        p.add(exit);
        p.add(invio);
        p.add(scroll);
        p.setBackground(Color.white);//Da cambiare/eliminare
        
        add(p);
        
        
        table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() > -1) {
                    // print first column value from selected row
                    System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());                  
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
	
	
	private JScrollPane aggiornaTavoli() throws IOException {
    	String[] colonne = new String[] {"Piatto", "Quantità", "Tavolo"};
    	//piatti = menu.getItems();
    	
    	table = new DynamicJTable(orderHolder, colonne);
    	table.setShowGrid(false);
        //table.setEnabled(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(0, 0);
        scroll.setSize(505,150);
        //scroll.setVisible(true);
                
    	return scroll;
    }
}
