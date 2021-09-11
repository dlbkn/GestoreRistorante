package GUI;

import restaurant.OpenOrder;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


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
		
		// Creazione del Frame
		setTitle("ORDINE");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Container c = this.getContentPane(); 
        JPanel panel = new JPanel();
        
        String[] colonne = new String[] {
                "Piatto", "Quantit�"
            };
        
        JButton exit = new JButton("EXIT");
        add(panel);
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
        
      //Manda l'ordine al Cuoco, prima però selezionando il tavolo che ha fatto l'ordine
        send.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	if(!openOrder.empty()) {
            		//creazione panel per immettere il numero del tavolo
            		JPanel panelSend = new JPanel();
	                JTextField num = new JTextField(3);
	                panelSend.add(num);
	            	Object[] options = { "Invio",
	                "Quit" };  
	                int result = JOptionPane.showOptionDialog(null, panelSend, "Selezionare il tavolo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
	                        null, options, null);
	                
	                if(result == JOptionPane.OK_OPTION) {
						try{
							Integer.parseInt(num.getText());
							openOrder.sendOrder(Integer.parseInt(num.getText()));
							exitButtonActionPerformed(e);
						}catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Selezionare solo numeri");
						}catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Inserire quantit�");
						}  
	                }
	                //prova a convertire la stringa in intero
	                
            	}
            		
            }
        });
        
        // Rimuove una determinata quantità di un piatto ordinato
        remove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	if(!openOrder.empty()) {
            		int riga = table.getSelectedRow();

	            	//Controllo se è stata selezionata una riga dalla tabella, se non è stata
	            	//selezionata allora darà una schermata di errore.
	            	if (riga >= 0) {
	            		
	            		//creazione panel per immettere la quantità da togliere
	                    JPanel panel = new JPanel();
	                    JTextField num = new JTextField(3);
	                    panel.add(num);
	                    
	                    Object[] options = { "Invio",
	                    "Quit" };                                                         
	                    int result = JOptionPane.showOptionDialog(null,panel, "Quantit� da eliminare", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
	                            null, options, null);
	                    //prova a convertire la stringa in intero
	                    if(result == JOptionPane.OK_OPTION) {
	                    	try{
		                        Integer.parseInt(num.getText());
		                        openOrder.removeItem((String)table.getModel().getValueAt(riga, 0),Integer.parseInt(num.getText()));
		                        table.display();
		                    }catch (NumberFormatException nfe) {
		                    	JOptionPane.showMessageDialog(null, "Selezionare solo numeri");
		                    }
	                    }
	                     
	                }else{                	
	                    JOptionPane.showMessageDialog(null, "Nessuna riga selezionata");
	                }
            	}else {
            		try {
    					exitButtonActionPerformed(e);
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
            	}
            }
        });
        
        //Cancella l'intero ordine
        clear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
				openOrder.clear();
				table.display();
				if(openOrder.empty()) {
            		try {
    					exitButtonActionPerformed(e);
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
				}
            }
        });
        
        
        //Torna alla schermata del cameriere
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
	//Action Performed per tornare alla schermata del cameriere
	private void exitButtonActionPerformed(ActionEvent e) throws FileNotFoundException, IOException{
    	GUICameriere r = new GUICameriere(openOrder);
        r.setVisible(true);
        this.dispose();
    }
	

}
