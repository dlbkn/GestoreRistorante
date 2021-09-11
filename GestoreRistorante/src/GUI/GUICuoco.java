package GUI;

import restaurant.OrderHolder;
import restaurant.PaymentHolder;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;

import application.DynamicJTable;

public class GUICuoco extends JFrame{
	
	public OrderHolder orderHolder;
	public JScrollPane scroll;
	public DynamicJTable table;
	public PaymentHolder payments;
	
	
	public GUICuoco(OrderHolder orderHolder) throws IOException {
		this.orderHolder = orderHolder;
		this.scroll = this.aggiornaTavoli();
		init();
	}

	private void init() {
		setTitle("CUOCO");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		     
        // Creation of panel
        //Creazione del panel
        JPanel p = new JPanel();
             
        p.setSize(420, 310);
        p.setLayout(null);
       
        JButton exit = new JButton("EXIT");
        
        Dimension size = exit.getPreferredSize();
        
        exit.setBounds(10, 235, size.width, size.height);
                
        //DynamicJTable table = new DynamicJTable(orderHolder,colonne);
        //add(scroll);
        scroll.setLocation(0, 0);
        scroll.setSize(405,150);
        scroll.setVisible(true);
        //text.setEditable(false);
        table.setShowGrid(false);
       
        DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
        rendar.setHorizontalAlignment(JLabel.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(rendar); 
        
        p.add(exit);
        p.add(scroll);
        p.setBackground(Color.white);//Da cambiare/eliminare

        add(p);
 
        table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
            	int i = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            	
            	tableSelectActionPerformed(event, orderHolder, i);
            	
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
		//GUIRistorante r = new GUIRistorante();
		//r.setVisible(true);
        this.dispose();
        }
	
	
	private JScrollPane aggiornaTavoli() throws IOException {
    	String[] colonne = new String[] {"Tavolo"};
 
    	table = new DynamicJTable(orderHolder, colonne);
    	table.setShowGrid(false);
        //table.setEnabled(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(0, 0);
        scroll.setSize(405,150);
        //scroll.setVisible(true);
                
    	return scroll;
    }

	private void tableSelectActionPerformed(ListSelectionEvent e, OrderHolder orderHolder, int i) {
		try {
			GUITavoli t = new GUITavoli(orderHolder, i);
			t.setVisible(true);
			
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		this.dispose();
	}
}
