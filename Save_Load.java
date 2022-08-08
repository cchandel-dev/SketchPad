import javax.swing.*;  
import java.awt.event.*;
import java.io.*;
public class Save_Load extends JFrame implements ActionListener
{
  JFrame frame;  
  JButton btn;
  JRadioButton rBtn1, rBtn2;
  JTextField address;
  File file;
  boolean s_cmd;
  boolean l_cmd = false;
  Save_Load()
  {
    frame = new JFrame();  
    // Create the label     
    JLabel label = new JLabel("Select Save or Load for a location, and then click Submit.", JLabel.CENTER);
    label.setBounds(20,0,200,80);  
    
    // Create the radio buttons
    rBtn1 = new JRadioButton("Save");
    rBtn2 = new JRadioButton("Load");
    
    // Set the position of the radio buttons
    rBtn1.setBounds(40,60,200,50);  
    rBtn2.setBounds(40,100,200,50);
    
    // Add radio buttons to group
    ButtonGroup bg = new ButtonGroup();  
    bg.add(rBtn1);
    bg.add(rBtn2);
    
    //address text field
    address = new JTextField();
    address.setBounds(30, 200, 200, 30 );
    address.setText("https://");
    
    btn = new JButton("Submit");  
    btn.setBounds(100,300,80,30);  
    btn.addActionListener(this);  

    // Add buttons to frame
    frame.add(label);
    frame.add(address);
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
      JOptionPane.showMessageDialog(this,"Save Mode selected."); 
      file = new File(address.getText());

    }  
    else if(rBtn2.isSelected()){  
      JOptionPane.showMessageDialog(this,"Load Mode selected.");  
     
    } 
 
  }  
}