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
        setSize(520, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel p = new JPanel();
        
        JButton add = new JButton("AGGIUNGI");
        JButton del = new JButton("ELIMINA");
        JButton mod = new JButton("MODIFICA");
        JButton exit = new JButton("BACK");
        

        Dimension size = mod.getPreferredSize();
        
        add.setBounds(6, 10, size.width, size.height);
        del.setBounds(6, 70, size.width, size.height);
        mod.setBounds(6, 130, size.width, size.height);
        exit.setBounds(6, 235, size.width, size.height);
        
        p.setSize(100, 520);
        p.setLayout(null);
        p.add(add);
        p.add(mod);
        p.add(del);
        p.add(exit);
        p.setBackground(Color.white);//Da cambiare/eliminare
        
        pview.setSize(520,150);
        pview.setLayout(null);
        pview.setBackground(Color.red);//Da cambiare/eliminare
        pview.add(scroll);
        
        scroll.setLocation(100, 0);
        scroll.setSize(405,150);
        scroll.setVisible(true);
        //text.setEditable(false);
        table.setShowGrid(false);
        
        JPanel padd = aggiungiPiatto();
        JPanel pdel = rimuoviPiatto();
        JPanel pedit = modificaPiatto();
       
        //c.add(padd);
        
        //c.add(pdel);
         
        add(p);
        add(pview);
        add(padd);
        add(pdel);
        add(pedit);
        
        
        padd.setSize(420, 400);
        padd.setBackground(Color.yellow);//Da cambiare/eliminare
        
        pdel.setSize(420, 400);
        pdel.setBackground(Color.black);//Da cambiare/eliminare
        
        pedit.setSize(420, 400);
        pedit.setBackground(Color.green);//Da cambiare/eliminare
        
        //Listener Bottoni  
        
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	//padd.setVisible(true); 
            	if(padd.isVisible()){
                    padd.setVisible(false);
                    
                }else{
                	pdel.setVisible(false);
                	pedit.setVisible(false);
                    padd.setVisible(true);
                }
            }                
        });
        
        del.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	
            	if(pdel.isVisible()){
                    pdel.setVisible(false);
                    
                }else{
                	padd.setVisible(false);
                	pedit.setVisible(false);
                    pdel.setVisible(true);
                }
            }                
        });
        
        mod.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	
            	if(pedit.isVisible()){
                    pedit.setVisible(false);
                    
                }else{
                	padd.setVisible(false);
                	pdel.setVisible(false);
                    pedit.setVisible(true);
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
        
        nomePiattoField.setLocation(146, 185);
        nomePiattoField.setSize(100, 20);
        
        prezzoPiattoField.setLocation(146, 215);
        prezzoPiattoField.setSize(100, 20);
        
        salva.setBounds(270, 200, 75, 20);
        
        jp.setLayout(null);
        jp.add(nomePiattoField);
        jp.add(prezzoPiattoField);
        jp.add(salva); 
        jp.setVisible(false);
               
        return jp;
    }
    
    private JPanel rimuoviPiatto() {
    	JPanel jpr = new JPanel();
    	JButton rimuovi = new JButton("RIMUOVI");
    	
    	JTextField piattoRemover = new JTextField("Piatto da rimuovere");
    	piattoRemover.setForeground(new Color(153,153,153));
    	
    	piattoRemover.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(piattoRemover.getText().equals("Piatto da rimuovere")) {
					piattoRemover.setText("");
					piattoRemover.setForeground(Color.BLACK);
				}else if (piattoRemover.getText().equals("") ){
					piattoRemover.setForeground(Color.GRAY);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(piattoRemover.getText().equals("")) {
					piattoRemover.setText("Piatto da rimuovere");
					piattoRemover.setForeground(Color.GRAY);
				}else {
					piattoRemover.setForeground(Color.BLACK);
				}
			}
        	
        });
    	
    	rimuovi.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	
            	try {
					removeButtonActionPerformed(e, piattoRemover);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
    	
    	piattoRemover.setLocation(146, 200);
    	piattoRemover.setSize(120, 20);
    	rimuovi.setBounds(270, 200, 90, 20);
    	
    	jpr.setLayout(null);
        jpr.add(piattoRemover);
        jpr.add(rimuovi);
        jpr.setVisible(false);
    	
    	return jpr;
    }
    
    private JPanel modificaPiatto() {
    	JPanel jpm = new JPanel();
    	JButton edit = new JButton("EDIT");
    	
    	JTextField piatto = new JTextField("Piatto");
    	piatto.setForeground(new Color(153,153,153));
    	
    	JTextField nuovoPrezzo = new JTextField("Nuovo prezzo");
    	nuovoPrezzo.setForeground(new Color(153,153,153));
    	
    	piatto.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(piatto.getText().equals("Piatto")) {
					piatto.setText("");
					piatto.setForeground(Color.BLACK);
				}else if (piatto.getText().equals("") ){
					piatto.setForeground(Color.GRAY);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(piatto.getText().equals("")) {
					piatto.setText("Piatto");
					piatto.setForeground(Color.GRAY);
				}else {
					piatto.setForeground(Color.BLACK);
				}
			}
        	
        });
    	
    	nuovoPrezzo.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(nuovoPrezzo.getText().equals("Nuovo prezzo")) {
					nuovoPrezzo.setText("");
					nuovoPrezzo.setForeground(Color.BLACK);
				}else if (nuovoPrezzo.getText().equals("") ){
					nuovoPrezzo.setForeground(Color.GRAY);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(nuovoPrezzo.getText().equals("")) {
					nuovoPrezzo.setText("Nuovo prezzo");
					nuovoPrezzo.setForeground(Color.GRAY);
				}else {
					nuovoPrezzo.setForeground(Color.BLACK);
				}
			}
        	
        });
    	
    	edit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	
            	try {
					editButtonActionPerformed(e, piatto, nuovoPrezzo);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
    	
    	
    	piatto.setLocation(146, 185);
    	nuovoPrezzo.setLocation(146, 215);
    	
    	piatto.setSize(100, 20);

    	nuovoPrezzo.setSize(100, 20);
    	edit.setBounds(270, 200, 75, 20);
    	
    	jpm.setLayout(null);
        jpm.add(piatto);

        jpm.add(nuovoPrezzo);
        jpm.add(edit);
        jpm.setVisible(false);
    	
    	return jpm;
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
  
    private void removeButtonActionPerformed(ActionEvent e, JTextField piattoRemover) throws IOException {
    	menu.removeItem(piattoRemover.getText());
    	menu.WriteToFile();
    	pview.remove(scroll);            
        scroll = aggiornaMenu();
        pview.add(scroll);
        
        piattoRemover.setText("Piatto da rimuovere");
		piattoRemover.setForeground(Color.GRAY);
    }
    
    private void editButtonActionPerformed(ActionEvent e, JTextField piatto, JTextField nuovoPrezzo) throws IOException {
    	menu.replaceItem(piatto.getText(), Double.parseDouble(nuovoPrezzo.getText()));
    	menu.WriteToFile();
    	pview.remove(scroll);            
        scroll = aggiornaMenu();
        pview.add(scroll);
    	
        piatto.setText("Piatto");
		piatto.setForeground(Color.GRAY);
        
        nuovoPrezzo.setText("Nuovo prezzo");
		nuovoPrezzo.setForeground(Color.GRAY);
    }
    
    private JScrollPane aggiornaMenu() throws IOException {
    	String[] colonne = new String[] {"Piatto", "Prezzo ($)"};
    	//piatti = menu.getItems();
    	
    	table = new DynamicJTable(this.menu, colonne);
    	table.setShowGrid(false);
        //table.setEnabled(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(100, 0);
        scroll.setSize(405,150);
        //scroll.setVisible(true);
                
    	return scroll;
    }
}
