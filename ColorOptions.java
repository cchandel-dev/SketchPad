import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;  

public class ColorOptions extends JFrame implements ActionListener
{
  JFrame frame;  
  JButton btn;
  JRadioButton rBtn1, rBtn2, rBtn3, rBtn4, rBtn5;
  public Color c_mode;
  ColorOptions()
  {
    frame = new JFrame();  
    // Create the label     
    JLabel label = new JLabel("Select which color you would like to draw in, and then click Submit.", JLabel.CENTER);
    label.setBounds(20,0,200,80);  
    
    // Create the radio buttons
    rBtn1 = new JRadioButton("Red");
    rBtn2 = new JRadioButton("Blue");
    rBtn3 = new JRadioButton("Green");  
    rBtn4 = new JRadioButton("Yellow");
    rBtn5 = new JRadioButton("Black");
    
    // Set the position of the radio buttons
    rBtn1.setBounds(40,60,200,50);  
    rBtn2.setBounds(40,100,200,50);  
    rBtn3.setBounds(40,140,200,50); 
    rBtn4.setBounds(40,180,200,50);  
    rBtn5.setBounds(40,220,200,50); 
    
    // Add radio buttons to group
    ButtonGroup bg = new ButtonGroup();  
    bg.add(rBtn1);
    bg.add(rBtn2);  
    bg.add(rBtn3);  
    bg.add(rBtn4);  
    bg.add(rBtn5);
    
    btn = new JButton("Submit");  
    btn.setBounds(100,300,80,30);  
    btn.addActionListener(this);  

    // Add buttons to frame
    frame.add(label);
    frame.add(rBtn1);
    frame.add(rBtn2);   
    frame.add(rBtn3);   
    frame.add(rBtn4);   
    frame.add(rBtn5); 
    frame.add(btn); 

    frame.setSize(300,400);  
    frame.setLayout(null);  
    frame.setVisible(true);  
    
    //default is red
    rBtn1.setSelected(true);
    c_mode = Color.RED;
  }  
  
  public void actionPerformed(ActionEvent e){
    if(rBtn1.isSelected()){  
      JOptionPane.showMessageDialog(this,"Red color selected."); 
      c_mode = Color.RED;
    }  
    else if(rBtn2.isSelected()){  
      JOptionPane.showMessageDialog(this,"Blue color selected.");  
      c_mode = Color.BLUE;
    }  
    else if(rBtn3.isSelected()){  
	JOptionPane.showMessageDialog(this,"Green color selected.");  
	c_mode = Color.GREEN;
    }  
    else if(rBtn4.isSelected()){  
	JOptionPane.showMessageDialog(this,"Yellow color selected.");  
	c_mode = Color.YELLOW;
	    }  
    else if(rBtn5.isSelected()){  
	JOptionPane.showMessageDialog(this,"Black color selected.");  
	c_mode =Color.BLACK;
	    }  
  }  
}