import javax.swing.*;  
import java.awt.event.*;  

public class mouse_mode extends JFrame implements ActionListener
{
  JFrame frame;  
  JButton btn;
  JRadioButton rBtn1, rBtn2;
  public int m_mode;
  mouse_mode()
  {
    frame = new JFrame();  
    // Create the label     
    JLabel label = new JLabel("Select which mode your should be in, and then click Submit.", JLabel.CENTER);
    label.setBounds(20,0,200,80);  
    
    // Create the radio buttons
    rBtn1 = new JRadioButton("Draw");
    rBtn2 = new JRadioButton("Select");
    
    // Set the position of the radio buttons
    rBtn1.setBounds(40,60,200,50);  
    rBtn2.setBounds(40,100,200,50);  
    
    // Add radio buttons to group
    ButtonGroup bg = new ButtonGroup();  
    bg.add(rBtn1);
    bg.add(rBtn2);
    
    btn = new JButton("Submit");  
    btn.setBounds(100,300,80,30);  
    btn.addActionListener(this);  

    // Add buttons to frame
    frame.add(label);
    frame.add(rBtn1);
    frame.add(rBtn2);   
    frame.add(btn); 

    frame.setSize(300,400);  
    frame.setLayout(null);  
    frame.setVisible(true); 
    
    //default is line
    rBtn1.setSelected(true);
  }  
  
  public void actionPerformed(ActionEvent e){
    if(rBtn1.isSelected()){  
      JOptionPane.showMessageDialog(this,"Draw Mode selected."); 
      m_mode = 0;
    }  
    else if(rBtn2.isSelected()){  
      JOptionPane.showMessageDialog(this,"Select Mode selected.");  
      m_mode = 1;
    }  
  }  
}