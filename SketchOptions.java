import javax.swing.*;  
import java.awt.event.*;  

public class SketchOptions extends JFrame implements ActionListener
{
  JFrame frame;  
  JButton btn;
  JRadioButton rBtn1, rBtn2, rBtn3, rBtn4, rBtn5;
  public int o_mode;
  SketchOptions()
  {
    frame = new JFrame();  
    // Create the label     
    JLabel label = new JLabel("1 - Give the abbreviation of AWT?", JLabel.CENTER);
    label.setBounds(20,0,200,80);  
    
    // Create the radio buttons
    rBtn1 = new JRadioButton("Line");
    rBtn2 = new JRadioButton("Square");
    rBtn3 = new JRadioButton("Rectangle");  
    rBtn4 = new JRadioButton("Circle");
    rBtn5 = new JRadioButton("Ellipse");
    
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
    
    btn = new JButton("Check");  
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
  }  
  
  public void actionPerformed(ActionEvent e){
    if(rBtn1.isSelected()){  
      JOptionPane.showMessageDialog(this,"Line Mode selected."); 
      o_mode = 0;
    }  
    else if(rBtn2.isSelected()){  
      JOptionPane.showMessageDialog(this,"Square Mode selected.");  
      o_mode = 1;
    }  
    else if(rBtn3.isSelected()){  
	JOptionPane.showMessageDialog(this,"Rectangel Mode selected.");  
	o_mode = 2;
    }  
    else if(rBtn4.isSelected()){  
	JOptionPane.showMessageDialog(this,"Circle Mode selected.");  
	o_mode = 3;
	    }  
    else if(rBtn5.isSelected()){  
	JOptionPane.showMessageDialog(this,"Ellipse Mode selected.");  
	o_mode = 4;
	    }  
  }  
}