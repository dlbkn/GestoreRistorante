package restaurant;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GUIRistorante extends JFrame{

	public GUIRistorante(){
        init();
    }
    private void init(){
        setTitle("RISTORANTE");
        setSize (300,200); // imposta le dimensioni
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
        panel.add(cass);
        //panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //panel.add(chef);
        
        
    }
    private void chefButtonActionPerformed(ActionEvent e) throws IOException{
        GUIChef ch = new GUIChef();
        ch.setVisible(true);
        this.dispose();
    }
    private void camButtonActionPerformed(ActionEvent e) throws IOException{
    	GUICameriere cam = new GUICameriere(new OpenOrder(new Menu("menu.txt"), new OrderHolder(new PaymentHolder())));
        cam.setVisible(true);
        this.dispose();
    }
}
