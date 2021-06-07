package restaurant;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.DynamicJTable;

public class GUICuoco extends JFrame{
	
	public OrderHolder orderHolder;
	
	public GUICuoco(OrderHolder orderHolder) {
		this.orderHolder = orderHolder;
		init();
	}

	private void init() {
		setTitle("CUOCO");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        Container c = this.getContentPane();
        
        // Creation of panel
        //Creazione del panel
        JPanel p = new JPanel();
        c.add(p);
        
        JButton exit = new JButton("EXIT");
        exit.setLocation(getMousePosition());
        
        JButton invio = new JButton("INVIO");
        
        String[] colonne = new String[] {
                "piatto"
            };
        DynamicJTable table = new DynamicJTable(orderHolder,colonne);
        
        p.add(exit);
        p.add(invio);
        p.add(table);
        
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
        this.dispose();}
	
}
