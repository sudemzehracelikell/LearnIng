import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HesapMakinesi 
{
	public static void main (String[] args)
	{  
		JFrame elif = new JFrame("Elifin penceresinden");
		elif.setSize(400, 300);
		elif.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		elif.setLayout(new GridLayout(6, 2, 10, 10));
	
	
	JLabel sayi1Label = new JLabel ("Sayi 1:");
	JTextField sayi1field= new JTextField();
	JLabel sayi2Label = new JLabel ("Sayi 2:");
	JTextField sayi2field= new JTextField();
	JLabel sonucLabel = new JLabel("Sonuc: ");
	JLabel sonucField = new JLabel("0");
	
	
	JButton toplaButton = new JButton("Topla");
    JButton cikarButton = new JButton("Çıkar");
    JButton carpButton=new JButton("carp");
    JButton bolButton=new JButton ("Bol");
    
    JButton temizlemeButton=new JButton("Temzileme");
    
    toplaButton.addActionListener(new ActionListener () {
    	
    	public void actionPerformed(ActionEvent e) {
    		try { 
    			double sayi1=Double.parseDouble(sayi1field.getText());
    			double sayi2=Double.parseDouble(sayi2field.getText());
    			double sonuc = sayi1+sayi2;
    			sonucField.setText(String.valueOf(sonuc));
    		}
    		catch(NumberFormatException ex)
    		{
    			JOptionPane.showMessageDialog(elif,"Lütfen Geçerli gir");
    		}
    		
    	}
    	
    });
    
    cikarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double sayi1 = Double.parseDouble(sayi1field.getText());
                double sayi2 = Double.parseDouble(sayi2field.getText());
                double sonuc = sayi1 - sayi2;
                sonucField.setText(String.valueOf(sonuc));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(elif, "Lütfen geçerli bir sayı girin!");
            }
        }
    });
    
carpButton.addActionListener(new ActionListener () {
    	
    	public void actionPerformed(ActionEvent e) {
    		try { 
    			double sayi1=Double.parseDouble(sayi1field.getText());
    			double sayi2=Double.parseDouble(sayi2field.getText());
    			double sonuc = sayi1*sayi2;
    			sonucField.setText(String.valueOf(sonuc));
    		}
    		catch(NumberFormatException ex)
    		{
    			JOptionPane.showMessageDialog(elif,"Lütfen Geçerli gir");
    		}
    		
    	}
    	
    });

bolButton.addActionListener(new ActionListener () {
	
	public void actionPerformed(ActionEvent e) {
		try { 
			double sayi1=Double.parseDouble(sayi1field.getText());
			double sayi2=Double.parseDouble(sayi2field.getText());
			if(sayi2!=0)
			{
				double sonuc = sayi1/sayi2;
				sonucField.setText(String.valueOf(sonuc));
			}
			else
			{
				JOptionPane.showMessageDialog(elif,"Lütfen Geçerli gir");
			}
			
			
		}
		catch(NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(elif,"Lütfen Geçerli gir");
		}
		
	}
	
});
    
    temizlemeButton.addActionListener(new ActionListener()
    		{
    			public void actionPerformed(ActionEvent e)
    			{
    				sayi1field.setText("");
    				sayi2field.setText("");
    				sonucField.setText("0");
    				
    			}
    		});
    
    elif.add(sayi1Label);
    elif.add(sayi1field);
    elif.add(sayi2Label);
    elif.add(sayi2field);
    elif.add(sonucLabel);
    elif.add(sonucField);
    elif.add(toplaButton);
    elif.add(cikarButton);
    elif.add(carpButton);
    elif.add(temizlemeButton);
    elif.add(bolButton);
    
	elif.setVisible(true);
	}
}
