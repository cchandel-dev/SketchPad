import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

public class SketchOptions extends JFrame implements ActionListener
{
    JFrame frame;
    JButton btn;
    JRadioButton rBtn1, rBtn2, rBtn3, rBtn4, rBtn5, rBtn6, rBtn7, rBtn8;
    public int o_mode;
    SketchOptions()
    {
        frame = new JFrame();
        // Create the label
        JLabel label = new JLabel("Select which mode you would like to draw in, and then click Submit.", JLabel.CENTER);
        label.setBounds(20,0,200,80);

        // Create the radio buttons
        rBtn1 = new JRadioButton("Line");
        rBtn2 = new JRadioButton("Square");
        rBtn3 = new JRadioButton("Rectangle");
        rBtn4 = new JRadioButton("Circle");
        rBtn5 = new JRadioButton("Ellipse");
        rBtn6 = new JRadioButton("Polygon - Open");
        rBtn7 = new JRadioButton("Polygon - Closed");
        rBtn8 = new JRadioButton("Sketch");

        // Set the position of the radio buttons
        rBtn1.setBounds(40,60,200,50);
        rBtn2.setBounds(40,100,200,50);
        rBtn3.setBounds(40,140,200,50);
        rBtn4.setBounds(40,180,200,50);
        rBtn5.setBounds(40,220,200,50);
        rBtn6.setBounds(40,260,200,50);
        rBtn7.setBounds(40,300,200,50);
        rBtn8.setBounds(40,340,200,50);

        // Add radio buttons to group
        ButtonGroup bg = new ButtonGroup();
        bg.add(rBtn1);
        bg.add(rBtn2);
        bg.add(rBtn3);
        bg.add(rBtn4);
        bg.add(rBtn5);
        bg.add(rBtn6);
        bg.add(rBtn7);
        bg.add(rBtn8);

        btn = new JButton("Submit");
        btn.setBounds(100,400,80,30);
        btn.addActionListener(this);

        // Add buttons to frame
        frame.add(label);
        frame.add(rBtn1);
        frame.add(rBtn2);
        frame.add(rBtn3);
        frame.add(rBtn4);
        frame.add(rBtn5);
        frame.add(rBtn6);
        frame.add(rBtn7);
        frame.add(rBtn8);
        frame.add(btn);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 300;
        int height = 500;
        frame.setBounds(screenSize.width - width * 2, 0, width, height);
        frame.setLayout(null);
        frame.setVisible(true);

        //default is line
        rBtn1.setSelected(true);
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
            JOptionPane.showMessageDialog(this,"Rectangle Mode selected.");
            o_mode = 2;
        }
        else if(rBtn4.isSelected()){
            JOptionPane.showMessageDialog(this,"Circle Mode selected.");
            o_mode = 3;
        }
        else if(rBtn5.isSelected()){
            JOptionPane.showMessageDialog(this,"Ellipse Mode selected.");
            o_mode = 4;

        }else if(rBtn6.isSelected()){
            JOptionPane.showMessageDialog(this,"Open Poly Mode selected.");
            o_mode = 5;

        }else if(rBtn7.isSelected()){
            JOptionPane.showMessageDialog(this,"Close Poly Mode selected.");
            o_mode = 6;

        }else if(rBtn8.isSelected()){
            JOptionPane.showMessageDialog(this,"Sketch Mode selected.");
            o_mode = 7;
        }
    }
}