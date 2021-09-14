package GUI;

import restaurant.Menu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import application.DynamicJTable;

public class GUIChef extends JFrame{
	
	private Menu menu;
	
	private DynamicJTable table;
	private JScrollPane scroll;
	private JPanel menuPanel = new JPanel();
	
	public GUIChef(Menu menu) throws FileNotFoundException, IOException{
		super();
		this.menu = menu;
		this.scroll = this.reloadMenu();
        init();         
    }
	
    private void init() throws FileNotFoundException, IOException{
    	
        setTitle("CHEF");
        setSize(520, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel buttonPanel = new JPanel();
        
        JButton add = new JButton("NEW");
        JButton delete = new JButton("DELETE");
        JButton edit = new JButton("EDIT");
        JButton exit = new JButton("BACK");
        

        Dimension size = delete.getPreferredSize();
        
        add.setBounds(6, 10, size.width, size.height);
        delete.setBounds(6, 70, size.width, size.height);
        edit.setBounds(6, 130, size.width, size.height);
        exit.setBounds(6, 235, size.width, size.height);
        
        buttonPanel.setSize(100, 520);
        buttonPanel.setLayout(null);
        buttonPanel.add(add);
        buttonPanel.add(edit);
        buttonPanel.add(delete);
        buttonPanel.add(exit);
        buttonPanel.setBackground(Color.white);//Da cambiare/eliminare
        
        menuPanel.setSize(520,150);
        menuPanel.setLayout(null);
        menuPanel.setBackground(Color.red);//Da cambiare/eliminare
        menuPanel.add(scroll);
        
        scroll.setLocation(100, 0);
        scroll.setSize(405,150);
        scroll.setVisible(true);
        //text.setEditable(false);
        table.setShowGrid(false);
        
        JPanel addPanel = addItem();
        //JPanel pdel = rimuoviPiatto();
        JPanel editPanel = editItem();
       
        //c.add(padd);
        
        //c.add(pdel);
         
        add(buttonPanel);
        add(menuPanel);
        add(addPanel);
        //add(pdel);
        add(editPanel);
        
        
        addPanel.setSize(420, 400);
        addPanel.setBackground(Color.yellow);//Da cambiare/eliminare
        
        //pdel.setSize(420, 400);
        //pdel.setBackground(Color.black);//Da cambiare/eliminare
        
        editPanel.setSize(420, 400);
        editPanel.setBackground(Color.green);//Da cambiare/eliminare
        
        //Listener Bottoni  
        
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	//padd.setVisible(true); 
            	if(addPanel.isVisible()){
                    addPanel.setVisible(false);
                    
                }else{
                	//pdel.setVisible(false);
                	editPanel.setVisible(false);
                    addPanel.setVisible(true);
                }
            }                
        });
        
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	/*
            	if(pdel.isVisible()){
                    pdel.setVisible(false);
                    
                }else{
                	padd.setVisible(false);
                	pedit.setVisible(false);
                    pdel.setVisible(true);
                
                }*/
            	if(table.getSelectedRow() != -1) {
            		int reply = JOptionPane.showConfirmDialog(null, "Delete this item?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
	            	if (reply == JOptionPane.YES_OPTION) {
	            		try {
	    					removeButtonActionPerformed(e);
	    					JOptionPane.showMessageDialog(null, "Item deleted");
	    				} catch (IOException e1) {
	    					// TODO Auto-generated catch block
	    					JOptionPane.showMessageDialog(null, "Select item");
	    				}
	            	} else {
	            	    JOptionPane.showMessageDialog(null, "Item not deleted");
	            	    //System.exit(0);
	            	}
            	}else {
            		JOptionPane.showMessageDialog(null, "Select a row");
            	}
            	
            	
            	
            }                
        });
        
        edit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	
            	if(editPanel.isVisible()){
                    editPanel.setVisible(false);
                    
                }else{
                	addPanel.setVisible(false);
                	//pdel.setVisible(false);
                    editPanel.setVisible(true);
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
    
    
    
    private JPanel addItem() {
    	JPanel addPanel = new JPanel();
        
        JTextField itemNameField = new JTextField("Item name");
        JTextField itemPriceField = new JTextField("Item price");
        
        JButton save = new JButton("ADD");
        
        itemNameField.setForeground(new Color(153,153,153));
        itemPriceField.setForeground(new Color(153,153,153));
   
        itemNameField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(itemNameField.getText().equals("Item name")) {
					itemNameField.setText("");
					itemNameField.setForeground(Color.BLACK);
				}else if (itemNameField.getText().equals("") ){
					itemNameField.setForeground(Color.GRAY);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(itemNameField.getText().equals("")) {
					itemNameField.setText("Item name");
					itemNameField.setForeground(Color.GRAY);
				}else {
					itemNameField.setForeground(Color.BLACK);
				}
			}
        	
        });
        
        itemPriceField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(itemPriceField.getText().equals("Item price")) {
					itemPriceField.setText("");
					itemPriceField.setForeground(Color.BLACK);
				}else if (itemPriceField.getText().equals("") ){
					itemPriceField.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(itemPriceField.getText().equals("")) {
					itemPriceField.setText("Item price");
					itemPriceField.setForeground(Color.GRAY);
				}else {
					itemPriceField.setForeground(Color.BLACK);
				}
			}
        	
        });
        
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	
            	try {
					saveButtonActionPerformed(e, itemNameField,  itemPriceField);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        itemNameField.setLocation(146, 185);
        itemNameField.setSize(100, 20);
        
        itemPriceField.setLocation(146, 215);
        itemPriceField.setSize(100, 20);
        
        save.setBounds(270, 200, 75, 20);
        
        addPanel.setLayout(null);
        addPanel.add(itemNameField);
        addPanel.add(itemPriceField);
        addPanel.add(save); 
        addPanel.setVisible(false);
               
        return addPanel;
    }
    /*
    private JPanel rimuoviPiatto() {
    	JPanel jpr = new JPanel();
    	JButton rimuovi = new JButton("RIMUOVI");
    	/*
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
        	
        //});
    	
    	rimuovi.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	
            	try {
					removeButtonActionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
    	
    	//piattoRemover.setLocation(146, 200);
    	//piattoRemover.setSize(120, 20);
    	rimuovi.setBounds(270, 200, 90, 20);
    	
    	jpr.setLayout(null);
        //jpr.add(piattoRemover);
        jpr.add(rimuovi);
        jpr.setVisible(false);
    	
    	return jpr;
    }
    */
    
    private JPanel editItem() {
    	JPanel editPanel = new JPanel();
    	JButton edit = new JButton("EDIT");
    	
    	JTextField item = new JTextField("Item");
    	item.setForeground(new Color(153,153,153));
    	
    	JTextField newPrice = new JTextField("New price");
    	newPrice.setForeground(new Color(153,153,153));
    	
    	item.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(item.getText().equals("Item")) {
					item.setText("");
					item.setForeground(Color.BLACK);
				}else if (item.getText().equals("") ){
					item.setForeground(Color.GRAY);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(item.getText().equals("")) {
					item.setText("Item");
					item.setForeground(Color.GRAY);
				}else {
					item.setForeground(Color.BLACK);
				}
			}
        	
        });
    	
    	newPrice.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(newPrice.getText().equals("New price")) {
					newPrice.setText("");
					newPrice.setForeground(Color.BLACK);
				}else if (newPrice.getText().equals("") ){
					newPrice.setForeground(Color.GRAY);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(newPrice.getText().equals("")) {
					newPrice.setText("New price");
					newPrice.setForeground(Color.GRAY);
				}else {
					newPrice.setForeground(Color.BLACK);
				}
			}
        	
        });
    	
    	edit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	
            	try {
					editButtonActionPerformed(e, item, newPrice);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "The item doesn't exist");
					
					item.setText("Item");
		    		item.setForeground(Color.GRAY);
		            
		            newPrice.setText("New price");
		    		newPrice.setForeground(Color.GRAY);
				}
            }
        });
    	
    	item.setLocation(146, 185);
    	newPrice.setLocation(146, 215);
    	
    	item.setSize(100, 20);

    	newPrice.setSize(100, 20);
    	edit.setBounds(270, 200, 75, 20);
    	
    	editPanel.setLayout(null);
        editPanel.add(item);

        editPanel.add(newPrice);
        editPanel.add(edit);
        editPanel.setVisible(false);
    	
    	return editPanel;
    }
    
    private void saveButtonActionPerformed(ActionEvent e, JTextField itemNameField, JTextField itemPriceField) throws IOException{
    	
    	if(!isAlpha(itemNameField.getText())) {
    		
    		JOptionPane.showMessageDialog(null, "Use only letters");
    		
    		itemNameField.setText("Item name");
	        itemNameField.setForeground(Color.GRAY);
	        
	        itemPriceField.setText("Item price");
	        itemPriceField.setForeground(Color.GRAY);
	        
    	}else if (!isDigit(itemPriceField.getText())){
    
    		JOptionPane.showMessageDialog(null, "Use only numbers and comma");
    		
    		//nomePiattoField.setText("Nome Piatto");
	        //nomePiattoField.setForeground(Color.GRAY);
	        
	        itemPriceField.setText("Item price");
	        itemPriceField.setForeground(Color.GRAY);
	        
    	}else {
    	
    		menu.addItem(itemNameField.getText(), Double.parseDouble(itemPriceField.getText()));
	        System.out.print(itemNameField.getText() + Double.parseDouble(itemPriceField.getText()));
	        menu.WriteToFile();                
	        
	        menuPanel.remove(scroll);            
	        scroll = reloadMenu();
	        menuPanel.add(scroll);
	        
	        itemNameField.setText("Item name");
	        itemNameField.setForeground(Color.GRAY);
	        
	        itemPriceField.setText("Item price");
	        itemPriceField.setForeground(Color.GRAY);
    	}    	
    }
  
    private void removeButtonActionPerformed(ActionEvent e) throws IOException {
    	menu.removeItem(table.getSelectedKey().toString());
    	menu.WriteToFile();
    	menuPanel.remove(scroll);            
        scroll = reloadMenu();
        menuPanel.add(scroll);
        
        //piattoRemover.setText("Piatto da rimuovere");
		//piattoRemover.setForeground(Color.GRAY);
    }
    
    private void editButtonActionPerformed(ActionEvent e, JTextField item, JTextField newPrice) throws IOException {
    	
    	if(!isAlpha(item.getText())) {
    		
    		JOptionPane.showMessageDialog(null, "Use only letters");
    		
    		item.setText("Item");
    		item.setForeground(Color.GRAY);
            
            newPrice.setText("New price");
    		newPrice.setForeground(Color.GRAY);
	        
    	}else if (!isDigit(newPrice.getText())){
    
    		JOptionPane.showMessageDialog(null, "Use only numbers and comma");
    		
    		item.setText("Item");
    		item.setForeground(Color.GRAY);
            
            newPrice.setText("New price");
    		newPrice.setForeground(Color.GRAY);
	        
    	}else {
    
    		menu.replaceItem(item.getText(), Double.parseDouble(newPrice.getText()));
	    	menu.WriteToFile();
	    	menuPanel.remove(scroll);            
	        scroll = reloadMenu();
	        menuPanel.add(scroll);
	    	
	        item.setText("Item");
			item.setForeground(Color.GRAY);
	        
	        newPrice.setText("New price");
			newPrice.setForeground(Color.GRAY);
    	}
    }
    
    private void exitButtonActionPerformed(ActionEvent e) throws IOException{
    	GUIRestaurant r = new GUIRestaurant();
        r.setVisible(true);
        this.dispose();
    }
    
    private JScrollPane reloadMenu() throws IOException {
    	String[] column = new String[] {"Item", "Price (€)"};
    	//piatti = menu.getItems();
    	
    	table = new DynamicJTable(this.menu, column);
    	table.setShowGrid(false);
        //table.setEnabled(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(100, 0);
        scroll.setSize(405,150);
        //scroll.setVisible(true);
                
    	return scroll;
    }
    
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
    
    public boolean isDigit(String value) {
    	return value.matches("[0-9.]+");
    }
}
