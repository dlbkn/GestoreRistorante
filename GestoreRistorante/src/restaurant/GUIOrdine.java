package restaurant;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import application.DynamicJTable;

public class GUIOrdine extends JFrame{
	private OpenOrder openOrder;
	private DefaultTableModel model;
	
	public GUIOrdine(OpenOrder openOrder) throws FileNotFoundException, IOException {
		this.openOrder = openOrder;
		init();
		// TODO Auto-generated constructor stub
	}
	
	private void init() throws FileNotFoundException, IOException{
		setTitle("ORDINE");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = this.getContentPane(); 
        JPanel panel = new JPanel();
        
        String[] colonne = new String[] {
                "piatto", "quantità"
            };
        
        
        
        
        
        JButton exit = new JButton("EXIT");
        c.add(panel);
        exit.setLocation(0,300);
        
        JButton remove = new JButton("REMOVE"); 
        remove.setLocation(6,235);
        JButton clear = new JButton("CLEAR");
        JButton send  = new JButton("SEND");
        
        
        
        DynamicJTable table = new DynamicJTable(openOrder,colonne);
        table.setSize(200,200);
        table.setLocation(50,50);
        
        exit.setBounds(6, 210, 100, 30);
        remove.setBounds(6, 150, 100, 30);
        clear.setBounds(6, 90, 100, 30);
        send.setBounds(6, 30, 100, 30);
        table.setBounds(120, 30, 200, 200);
        
        
        panel.setLayout(null);
        
        panel.add(table);
        panel.add(clear);
        panel.add(remove);
        panel.add(send);
        panel.add(exit);
        
        
        remove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	int riga = table.getSelectedRow();
            	System.out.println(riga);
            	if (riga >= 0) {
                    
                    JPanel panel = new JPanel();
                    JTextField num = new JTextField(3);
                    panel.add(num);
                    
                    Object[] options1 = { "Invio",
                    "Quit" };                                                         
                    int num1 = JOptionPane.showOptionDialog(null,panel, "Quantità da eliminare", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, options1, null);
                    try{
                        Integer.parseInt(num.getText());
                        openOrder.removeItem((String)table.getModel().getValueAt(riga, 0),Integer.parseInt(num.getText()));
                        table.display();
                        
                     } catch (NumberFormatException nfe) {
                    	 JOptionPane.showMessageDialog(null, "Selezionare solo numeri");
                     }  
                
                } else {
                    JOptionPane.showMessageDialog(null, "Nessuna riga selezionata");
                }
            }
        });
        
        clear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	
					openOrder.clear();
					table.display();
				
            }
        });
        
        send.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            
            	JPanel panelSend = new JPanel();
                JTextField num = new JTextField(3);
                panelSend.add(num);
            	Object[] options2 = { "Invio",
                "Quit" };                                                         
                int tavolo = JOptionPane.showOptionDialog(null,panelSend, "Selezionare il tavolo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options2, null);
                
                try{
                    Integer.parseInt(num.getText());
                    openOrder.sendOrder(Integer.parseInt(num.getText()));
					openOrder = new OpenOrder(new Menu("menu.txt"), new OrderHolder(new PaymentHolder()));
                 } catch (NumberFormatException nfe) {
                	 JOptionPane.showMessageDialog(null, "Selezionare solo numeri");
                 } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
            	
            	
            }
        });
        
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	System.out.println(openOrder.getOrderMap());
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
    	GUICameriere r = new GUICameriere(openOrder);
        r.setVisible(true);
        this.dispose();
    }
	

}
