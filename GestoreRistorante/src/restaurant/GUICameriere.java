package restaurant;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class GUICameriere extends JFrame{
	Menu menu = new Menu("menu.txt");
	
	public GUICameriere() throws IOException{
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
        c.add(p);
        
        JButton exit = new JButton("BACK");
        Dimension size = exit.getPreferredSize();
        
        JPanel p2 = creaMenuBottoni(size); 
        c.add(p2);
        
        //p.setBackground(Color.white);
        //p2.setBackground(Color.black);
                                    
        p.setSize(100, 310);
        p2.setSize(320, 300);
        
        JLabel jl = new JLabel("Piatti");
        JLabel jl2 = new JLabel("Prezzo");
        
        
        exit.setBounds(6, 235, size.width, size.height);
        
        p.setLayout(null);
        p2.setLayout(null);
        //p2 = creaMenuBottoni();
        
        jl.setLocation(125, 0);
        jl.setSize(100, 40);
        
        jl2.setLocation(330, 0);
        jl2.setSize(100, 40);
        
        
        p2.add(jl);
        p2.add(jl2);
        
        
        p.add(exit);
        
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                exitButtonActionPerformed(e);
            }
        });
        
    }
    private void exitButtonActionPerformed(ActionEvent e){
    	GUIRistorante r = new GUIRistorante();
        r.setVisible(true);
        this.dispose();
    }
    
    private JPanel creaMenuBottoni(Dimension size) throws FileNotFoundException{
        JPanel jp = new JPanel();
        jp.setLayout(null);
        int x = 36;
        
        for(int i = 0; i < menu.getItems().length; i++) {
        	String nome = (String) menu.getItems()[i][0];
        	JButton piatto = new JButton(nome);
            piatto.setBounds(6, x, 90, size.height);
            piatto.setLocation(125, x);
            
            JLabel prezzo = new JLabel("€ " + menu.getItemPrice(nome));
            prezzo.setSize(100, 20);
            prezzo.setLocation(330, x);
            
            x+=30;
            
            jp.add(piatto);
            jp.add(prezzo);
        }
        return jp;
    }
}
