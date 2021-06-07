package restaurant;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import application.DynamicJTable;

public class GUIChef extends JFrame{
	
	Menu menu;
	
	DynamicJTable table;
	JScrollPane scroll;
	JPanel pview = new JPanel();
	
	public GUIChef(Menu menu) throws FileNotFoundException, IOException{
		super();
		this.menu = menu;
		this.scroll = this.aggiornaMenu();
        init();         
    }
	
    private void init() throws FileNotFoundException, IOException{
    	
        setTitle("CHEF");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        
        
        JPanel p = new JPanel();
        
        JButton view = new JButton("MENU'");
        JButton add = new JButton("AGGIUNGI");
        JButton del = new JButton("ELIMINA");
        JButton mod = new JButton("MODIFICA");
        JButton exit = new JButton("BACK");
        
        Container c = this.getContentPane();
        Dimension size = mod.getPreferredSize();
        
        c.add(p);
        c.add(pview);

        
        //pview.setLocation(20,10);
        
        view.setBounds(6, 10, size.width, size.height);
        add.setBounds(6, 50, size.width, size.height);
        del.setBounds(6, 90, size.width, size.height);
        mod.setBounds(6, 130, size.width, size.height);
        exit.setBounds(6, 235, size.width, size.height);
        
        p.setSize(100, 310);
        p.setLayout(null);
        p.add(view);
        p.add(add);
        p.add(mod);
        p.add(del);
        p.add(exit);
        p.setBackground(Color.white);//Da cambiare/eliminare
        
        pview.setSize(420,150);
        pview.setLayout(null);
        pview.setBackground(Color.red);//Da cambiare/eliminare
        pview.add(scroll);
        
        scroll.setLocation(100, 0);
        scroll.setSize(305,150);
        scroll.setVisible(true);
        //text.setEditable(false);
        table.setShowGrid(false);

        
        
        JPanel padd = aggiungiPiatto();
        
        c.add(padd);
        padd.setSize(320, 300);
        padd.setBackground(Color.yellow);//Da cambiare/eliminare
        
        
        //Listener Bottoni        
        view.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //if(scroll.isVisible() && pview.isVisible()){
                    
                    //scroll = aggiornaTabella();
                    pview.remove(scroll);
                    pview.setVisible(false);
                    scroll.setVisible(false);
                //}else{
                	
                    try {
						scroll = aggiornaMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    pview.add(scroll);
                    //scroll.setVisible(true);
                    pview.setVisible(true);
                //}
            }                
        });
        
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	//padd.setVisible(true); 
            	if(padd.isVisible()){
                    padd.setVisible(false);
                    
                }else{
                    padd.setVisible(true);
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
    
    private void exitButtonActionPerformed(ActionEvent e) throws IOException{
    	// GUIRistorante r = new GUIRistorante();
        // r.setVisible(true);
        this.dispose();
    }
    
    private JPanel aggiungiPiatto() {
    	JPanel jp = new JPanel();
        
        JTextField nomePiattoField = new JTextField("Nome Piatto");
        JTextField prezzoPiattoField = new JTextField("Prezzo Piatto");
        
        JButton salva = new JButton("SALVA");
        
        nomePiattoField.setForeground(new Color(153,153,153));
        prezzoPiattoField.setForeground(new Color(153,153,153));
   
        nomePiattoField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(nomePiattoField.getText().equals("Nome Piatto")) {
					nomePiattoField.setText("");
					nomePiattoField.setForeground(Color.BLACK);
				}else if (nomePiattoField.getText().equals("") ){
					nomePiattoField.setForeground(Color.GRAY);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(nomePiattoField.getText().equals("")) {
					nomePiattoField.setText("Nome Piatto");
					nomePiattoField.setForeground(Color.GRAY);
				}else {
					nomePiattoField.setForeground(Color.BLACK);
				}
			}
        	
        });
        
        prezzoPiattoField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(prezzoPiattoField.getText().equals("Prezzo Piatto")) {
					prezzoPiattoField.setText("");
					prezzoPiattoField.setForeground(Color.BLACK);
				}else if (prezzoPiattoField.getText().equals("") ){
					prezzoPiattoField.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(prezzoPiattoField.getText().equals("")) {
					prezzoPiattoField.setText("Prezzo Piatto");
					prezzoPiattoField.setForeground(Color.GRAY);
				}else {
					prezzoPiattoField.setForeground(Color.BLACK);
				}
			}
        	
        });
        
        salva.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	
            	try {
					saveButtonActionPerformed(e, nomePiattoField,  prezzoPiattoField);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        nomePiattoField.setLocation(146, 170);
        nomePiattoField.setSize(100, 20);
        
        prezzoPiattoField.setLocation(146, 200);
        prezzoPiattoField.setSize(100, 20);
        
        salva.setBounds(270, 185, 75, 20);
        
        jp.setLayout(null);
        jp.add(nomePiattoField);
        jp.add(prezzoPiattoField);
        jp.add(salva);
        jp.setVisible(false);
        
        
        return jp;
        
        
    }
    private void saveButtonActionPerformed(ActionEvent e, JTextField nomePiattoField, JTextField prezzoPiattoField) throws IOException{
    	menu.addItem(nomePiattoField.getText(), Double.parseDouble(prezzoPiattoField.getText()));
        System.out.print(nomePiattoField.getText() + Double.parseDouble(prezzoPiattoField.getText()));
        menu.WriteToFile();                
        
        pview.remove(scroll);                           
        scroll = aggiornaMenu();
        pview.add(scroll);
        
        nomePiattoField.setText("Nome Piatto");
        nomePiattoField.setForeground(Color.GRAY);
        
        prezzoPiattoField.setText("Prezzo Piatto");
        prezzoPiattoField.setForeground(Color.GRAY);
    }
  
    
    private JScrollPane aggiornaMenu() throws IOException {
    	String[] colonne = new String[] {"Piatto", "Prezzo (�)"};
    	//piatti = menu.getItems();
    	
    	table = new DynamicJTable(this.menu, colonne);
    	table.setShowGrid(false);
        //table.setEnabled(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(100, 0);
        scroll.setSize(305,150);
        //scroll.setVisible(true);
                
    	return scroll;
    }
}
