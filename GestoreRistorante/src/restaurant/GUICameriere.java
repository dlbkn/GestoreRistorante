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



public class GUICameriere extends JFrame{
	public Menu menu = new Menu("menu.txt");
	public OpenOrder openOrder = new OpenOrder(menu,new OrderHolder(new PaymentHolder()));
	public JTextField text;
	public Container c;
	
	
	public GUICameriere(OpenOrder openOrder) throws FileNotFoundException, IOException{
		this.openOrder = openOrder;
        init();
    }
    
    private void init() throws FileNotFoundException, IOException{
        //FRAME
        setTitle("CAMERIERE");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = this.getContentPane(); 
        JPanel p = new JPanel();
        
        JButton ordine = new JButton("ORDINE");
        JButton exit = new JButton("BACK");
        Dimension size = exit.getPreferredSize();
        
        
        JPanel p2 = creaMenuBottoni(size); 
        c.add(p2);
        c.add(p);
        
        p.setBackground(Color.white);
        //p2.setBackground(Color.black);
                                    
        p.setSize(100, 300);
        p.setLocation(0,300);
        p2.setSize(420, 310);
        
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
        
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                exitButtonActionPerformed(e);
            }
        });
        
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
    private void exitButtonActionPerformed(ActionEvent e){
    	GUIRistorante r = new GUIRistorante();
        r.setVisible(true);
        this.dispose();
    }
    
    private void ordineButtonActionPerformed(ActionEvent e) throws FileNotFoundException, IOException{
    	GUIOrdine r = new GUIOrdine(openOrder);
        r.setVisible(true);
        this.dispose();
    }
    
    private JPanel creaMenuBottoni(Dimension size) throws FileNotFoundException{
        JPanel jp = new JPanel();
        jp.setLayout(null);
        int x = 36;
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
    
   
   public HashMap<String,Integer> returnOrder(){
	   return openOrder.getOrderMap();
   }
}
