package restaurant;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


// GUI for waiter, it permit you to make an order selecting the dish and the quantity.
// GUI per il cameriere, ti permette di creare un ordine e di selezionare il piatto e la quantità.

public class GUICameriere extends JFrame{
	public Menu menu = new Menu("menu.txt");
	public OpenOrder openOrder;
	public JTextField text;
	public Container c;
	
	
	public GUICameriere(OpenOrder openOrder) throws FileNotFoundException, IOException{
		this.openOrder = openOrder;
        init();
    }
    
    private void init() throws FileNotFoundException, IOException{
    	
        //Creation of Frame
    	//Creazione del frame
        setTitle("CAMERIERE");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        Container c = this.getContentPane();
        
        // Creation of panel
        //Creazione del panel
        JPanel p = new JPanel();
        
        //Creation of Button for order, exit
        JButton ordine = new JButton("ORDINE");
        JButton exit = new JButton("BACK");
        Dimension size = exit.getPreferredSize();
        
        //Creation second panel for locate the button
        JPanel p2 = creaMenuBottoni(size); 
        c.add(p2);
        c.add(p);
                   
        //panel size
        p.setSize(100, 300);
        p.setLocation(0,300);
        p2.setSize(420, 310);
        
        //Creates new label for the panel
        //Creazione scritte per il panel
        JLabel jl = new JLabel("Piatti");
        JLabel jl2 = new JLabel("Prezzo");
        JLabel jl3 = new JLabel("Quantità");
        
        exit.setBounds(6, 235, size.width, size.height);
        ordine.setBounds(325, 235, size.width, size.height);
        
        p.setLayout(null);
        p2.setLayout(null);
        //p2 = creaMenuBottoni();
        
        jl.setLocation(125, 0);
        jl.setSize(100, 40);
        
        jl2.setLocation(330, 0);
        jl2.setSize(100, 40);
        
        jl3.setLocation(25, 0);
        jl3.setSize(100,40);
        
        text = new JTextField(10);
        text.setSize(30, 30);
        text.setLocation(25, 36);
        
        p2.add(jl);
        p2.add(jl2);
        p2.add(jl3);
        p2.add(text);
        
        p.add(exit);
        p.add(ordine);
        
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
        ordine.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	try {
                    ordineButtonActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(GUIRistorante.class.getName()).log(Level.SEVERE, null, ex);
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
        this.dispose();
    }
    
    //Action Performed per aggiornare il frame e andare al pannello dell'ordine
    private void ordineButtonActionPerformed(ActionEvent e) throws FileNotFoundException, IOException{
    	GUIOrdine r = new GUIOrdine(openOrder);
        r.setVisible(true);
        this.dispose();
    }
    
    //Metodo per creare i bottoni per l'ordinazione di un determinato piatto
    private JPanel creaMenuBottoni(Dimension size) throws FileNotFoundException{
        JPanel jp = new JPanel();
        jp.setLayout(null);
        int x = 36;
        
        // creo una lista di JButton che verrà riempita tramite il for, che a sua volta legge
        // degli Object. Nel for, oltre alla creazione dei bottoni, è presente anche un
        // Action Listener che, se premuto,aggiunge il piatto con l'annessa quantità all'ordine.
        
        ArrayList<JButton> lista = new ArrayList<JButton>();
        for(int i = 0; i < menu.getItems().length; i++) {
        	lista.add(new JButton((String) menu.getItems()[i][0]));
        	
            lista.get(i).setBounds(6, x, 90, size.height);
            lista.get(i).setLocation(125, x);
            
            JLabel prezzo = new JLabel("� " + menu.getItemPrice(lista.get(i).getText()));
            
            prezzo.setSize(100, 20);
            prezzo.setLocation(330, x);
            
            x+=30;
            
            jp.add(lista.get(i));
            jp.add(prezzo);
            String nome = (String) menu.getItems()[i][0];
            
            
            lista.get(i).addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                	String val = text.getText();
                	int num = Integer.parseInt(val);
                	openOrder.addItem(nome,num); 
                	System.out.println(openOrder.getOrderMap());
                	text.setText("");
                }
            });
            
        }
        return jp;
    }
}
