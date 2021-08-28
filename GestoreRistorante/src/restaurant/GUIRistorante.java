package restaurant;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GUIRistorante extends JFrame{
	
	public Restaurant res;
	public GUIChef chef;
	public GUICameriere cameriere;
	public GUICuoco cuoco;

	public GUIRistorante() throws IOException{
		this.res = new Restaurant("menu.txt");
        init();
    }
    private void init(){
        setTitle("RISTORANTE");
        setSize (500,300); // imposta le dimensioni
        setLocationRelativeTo(null);// centra il frame
        setResizable(false);
        setVisible (true); // rende visibile f
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        Container c = this.getContentPane();
        JPanel panel = new JPanel();
        c.add(panel);
              
        
        JButton chef = new JButton("CHEF");
        JButton cam = new JButton("CAMERIERE");
        JButton cuoco = new JButton("CUOCO");
        JButton cass = new JButton("CASSIERE");
        
        Dimension size = cam.getPreferredSize();// prende le misure del bottone
        chef.setBounds(25, 25, size.width, size.height);// posizione da left, posizione da top, larghezza del size, altezza del size
        cam.setBounds(160, 25, size.width, size.height);
        cuoco.setBounds(25, 100, size.width, size.height);
        cass.setBounds(160, 100, size.width, size.height);
        panel.setLayout(null);
        panel.add(chef);
        
        chef.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try {
                    chefButtonActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(GUIRistorante.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        panel.add(cam);
        cam.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try {
                    camButtonActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(GUIRistorante.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        panel.add(cuoco);
        cuoco.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try {
                    cuocoButtonActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(GUIRistorante.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        panel.add(cass);
        //panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //panel.add(chef);
        
        
    }
    private void chefButtonActionPerformed(ActionEvent e) throws IOException{
    	//if (newFrameValidation()) {
    		this.chef = new GUIChef(res.getMenu());
    		this.chef.setVisible(true);
    		this.dispose();
    	//}
    }
    private void camButtonActionPerformed(ActionEvent e) throws IOException{
    	//if (newFrameValidation()) {
    		this.cameriere = new GUICameriere(res.getOpenOrder());
    		this.cameriere.setVisible(true);
    	//}
    }
    private void cuocoButtonActionPerformed(ActionEvent e) throws IOException{
    	//if (newFrameValidation()) {
    		//System.out.println(res.getOrderHolder().getOrder(1));
    		this.cuoco = new GUICuoco(res.getOrderHolder());
    		this.cuoco.setVisible(true);
    		this.dispose();
    	//}
    }
    
    private boolean newFrameValidation() {
    	return(this.chef == null && this.cameriere == null);
    }
    
/*
    private boolean newFrameValidation() {
    	return !(this.chef.isVisible() || this.cuoco.isVisible() || this.cameriere.isVisible());
		
    }
*/     
}
