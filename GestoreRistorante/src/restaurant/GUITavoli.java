package restaurant;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import application.DynamicJTable;

public class GUITavoli extends JFrame{
	public DynamicJTable table;
	public JScrollPane scroll;
	public OrderHolder orderHolder;
	public int i;
	public JPanel piatti;
	
	public GUITavoli(OrderHolder orderHolder, int i) throws IOException {
		this.orderHolder = orderHolder;
		this.i = i;
		this.scroll = this.aggiornaTavoli();
		init();
	}
	
	private void init() {
		setTitle("Table "+ i);
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        piatti = new JPanel();
        piatti.setLayout(null);
        
        JButton exit = new JButton("EXIT");
        JButton remove = new JButton("REMOVE");
        
        Dimension size = exit.getPreferredSize();
        exit.setBounds(10, 235, 70, size.height);
        remove.setBounds(110, 235, 90, size.height);
        
        scroll.setLocation(0, 0);
        scroll.setSize(405,150);
        scroll.setVisible(true);
        table.setShowGrid(false);
        
        add(piatti);
        piatti.add(scroll);
        piatti.add(exit);
        piatti.add(remove);
        
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
	
	
	private JScrollPane aggiornaTavoli() throws IOException {
    	String[] colonne = new String[] {"Piatto", "Quantità"};
 
    	table = new DynamicJTable(orderHolder.getOrder(i), colonne);
    	table.setShowGrid(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(0, 0);
        scroll.setSize(405,150);
                
    	return scroll;
    }
	
	private void exitButtonActionPerformed(ActionEvent e) throws FileNotFoundException, IOException{
    	GUICuoco r = new GUICuoco(orderHolder);
        r.setVisible(true);
        this.dispose();
    }
	
	private void removeButtonActionPerformed(ActionEvent e) throws IOException {
		try {
			orderHolder.serveItem(i, table.getSelectedKey().toString());
			piatti.remove(scroll);
			scroll = aggiornaTavoli();
			piatti.add(scroll);
		} catch (Exception e2) {
			GUICuoco r = new GUICuoco(orderHolder);
	        r.setVisible(true);
			this.dispose();
			
		}
		
		
		
	}
}
