package GUI;


import restaurant.Menu;
import restaurant.OpenOrder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


public class GUIWaiter extends JFrame{
	private Menu menu = new Menu("GestoreRistorante\\resources\\menu");
	private OpenOrder openOrder;
	private JTextField text;
	//private Container c;
	
	
	public GUIWaiter(OpenOrder openOrder) throws FileNotFoundException, IOException{
		this.openOrder = openOrder;
        init();
    }
    
    private void init() throws FileNotFoundException, IOException{
    	
        //Creation of Frame
    	//Creazione del frame
        setTitle("WAITER");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //Container c = this.getContentPane();
        
        // Creation of panel
        //Creazione del panel
        JPanel panel = new JPanel();
        
        //Creation of Button for order, exit
        JButton order = new JButton("ORDER");
        JButton exit = new JButton("BACK");
        Dimension size = exit.getPreferredSize();
        
        //Creation second panel for locate the button
        JPanel menuPanel = createButtonMenu(size); 
        add(menuPanel);
        add(panel);
                   
        //panel size
        panel.setSize(100, 300);
        panel.setLocation(0,300);
        menuPanel.setSize(420, 310);
        
        //Creates new label for the panel
        //Creazione scritte per il panel
        JLabel itemsLabel = new JLabel("Items");
        JLabel priceLabel = new JLabel("Price");
        JLabel amountLabel = new JLabel("Amount");
        
        exit.setBounds(6, 235, size.width, size.height);
        order.setBounds(300, 235, 90, size.height);
        
        panel.setLayout(null);
        menuPanel.setLayout(null);
        //p2 = creaMenuBottoni();
        
        itemsLabel.setLocation(125, 0);
        itemsLabel.setSize(100, 40);
        
        priceLabel.setLocation(330, 0);
        priceLabel.setSize(100, 40);
        
        amountLabel.setLocation(25, 0);
        amountLabel.setSize(100,40);
        
        text = new JTextField(10);
        text.setSize(35, 25);
        text.setLocation(25, 36);
        
        menuPanel.add(itemsLabel);
        menuPanel.add(priceLabel);
        menuPanel.add(amountLabel);
        menuPanel.add(text);
        
        panel.add(exit);
        panel.add(order);
        
        // Action Listener per tornare indietro.
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
        // Action Listener che aggiorna il frame e va all'ordine. 
        order.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	try {
                    orderButtonActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(GUIRestaurant.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        
    }
    
    //Action Performed per tornare indietro
    private void exitButtonActionPerformed(ActionEvent e) throws IOException{
    	/*
    	 * ERRORE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    	 * 
    	 * Creazione di un nuovo GUIRistorante crea un nuovo classe Restaurant
    	 * Quel nuovo classe Restaurant e vuoto allora ordini vengono persi 
    	 * 
    	 */
    	// GUIRistorante r = new GUIRistorante(); //  <--------------
        // r.setVisible(true);
        this.setVisible(false);
    }
    
    //Action Performed per aggiornare il frame e andare al pannello dell'ordine
    private void orderButtonActionPerformed(ActionEvent e) throws FileNotFoundException, IOException{
    	if(!openOrder.empty()) {
    		GUIOrder r = new GUIOrder(openOrder);
	        r.setVisible(true);
	        this.dispose();
    	}else {
    		JOptionPane.showMessageDialog(null, "There are no orders");
    	}
    	
    }
    
    //Metodo per creare i bottoni per l'ordinazione di un determinato piatto
    private JPanel createButtonMenu(Dimension size) throws FileNotFoundException{
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        int x = 36;
        
        // creo una lista di JButton che verrà riempita tramite il for, che a sua volta legge
        // degli Object. Nel for, oltre alla creazione dei bottoni, è presente anche un
        // Action Listener che, se premuto,aggiunge il piatto con l'annessa quantità all'ordine.
        
        ArrayList<JButton> list = new ArrayList<JButton>();
        for(int i = 0; i < menu.getItems().length; i++) {
        	list.add(new JButton((String) menu.getItems()[i][0]));
        	
            list.get(i).setBounds(6, x, 90, size.height);
            list.get(i).setLocation(125, x);
            
            JLabel price = new JLabel("� " + menu.getItemPrice(list.get(i).getText()));
            
            price.setSize(100, 20);
            price.setLocation(330, x);
            
            x+=30;
            
            buttonPanel.add(list.get(i));
            buttonPanel.add(price);
            String name = (String) menu.getItems()[i][0];
            
            
            list.get(i).addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                	String val = text.getText();
                	if(isDigit(val)) {
                		int num = Integer.parseInt(val);
                		openOrder.addItem(name,num); 
                		System.out.println(openOrder.getOrderMap());
                		text.setText("");
                	}else {
                		JOptionPane.showMessageDialog(null, "Use only numbers");
                	}
                	
                }
            });
            
        }
        return buttonPanel;
    }
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
    public boolean isDigit(String valore) {
    	return valore.matches("[0-9.]+");
    }
}
