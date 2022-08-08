

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.*;

public class DrawShapes extends JFrame {
    	static ArrayList<Objects> objects_to_draw;
    	public int mode;
    	public static SketchOptions options;
    	public static ColorOptions colors;
    	public static Save_Load save_load;
    	public static mouse_mode mouse;
    	public static List<String> myList;
    	public static JPanel panel;
    	public static JScrollPane scrollPane;
    	public static JFrame frame;
    	public static int selected;
    	public static Color select_store;
    	  //JFrame frame;  
    	  JButton btn;
    	  JRadioButton rBtn1, rBtn2;
    	  JTextField address;
    	  File file;
    	  FileOutputStream fileOut;
    	  boolean s_cmd;
    	  boolean l_cmd = false;
	public DrawShapes() {
		setSize(new Dimension(600, 620));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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
		    btn.addActionListener(new ActionListener() {
		            @SuppressWarnings("unchecked")
			    @Override
		            public void actionPerformed(ActionEvent e){
		        	    if(rBtn1.isSelected()){  
		        	      file = new File(address.getText());
		        	      try {
					fileOut = new FileOutputStream(file);
    		        	      	ObjectOutputStream out = new ObjectOutputStream(fileOut);
    		        	      	out.writeObject(objects_to_draw);
    		        	      	out.close();
    		        	      	fileOut.close();
		        	      } catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
		        	      }
		        	     
		        	    }  
		        	    else if(rBtn2.isSelected()){  
		        		     try {
		        		         FileInputStream fileIn = new FileInputStream(file);
		        		         ObjectInputStream in = new ObjectInputStream(fileIn);
		        		         objects_to_draw = (ArrayList<Objects>) in.readObject();
		        		         in.close();
		        		         fileIn.close();
		        		         repaint();
		        		      } catch (IOException i) {
		        		         i.printStackTrace();
		        		         return;
		        		      } catch (ClassNotFoundException c) {
		        		         System.out.println("Employee class not found");
		        		         c.printStackTrace();
		        		         return;
		        		      }
		        		      
		        	    } 
		            }  
		        });
		    // Add buttons to frame
		    add(label);
		    add(address);
		    add(rBtn1);
		    add(rBtn2);  
		    add(btn); 
		    
		    //default is line
		    rBtn1.setSelected(true);
		addMouseListener( new myMouseHandler());
		addMouseMotionListener( new myMouseMotionHandler());
		JPanel p = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				ArrayList <Shape> shapes = new ArrayList <Shape>();
				
				//take all of the objects and add them to a shapes array
				for (int i = 0; i < objects_to_draw.size(); i++) {
				    Objects temp = objects_to_draw.get(i);
					switch(temp.type) {
						case 0:
							shapes.add(new Line2D.Double(temp.x0, temp.y0, temp.x, temp.y));
							break;
						case 1:
							mySquare square = new mySquare(temp.x0, temp.y0, temp.x, temp.y);
							shapes.add(square.shape);
							break;
						case 2:
							myRect rect = new myRect(temp.x0, temp.y0, temp.x, temp.y);
							shapes.add(rect.shape);
							break;
						case 3:
							myCircle circle = new myCircle(temp.x0, temp.y0, temp.x, temp.y);
							shapes.add(circle.shape);
							break;
						case 4:
							myEllipse ellipse = new myEllipse(temp.x0, temp.y0, temp.x, temp.y);
							shapes.add(ellipse.shape);
							break;
						// case 5:
						// case 6:
					}
				}
				g2.setColor(colors.c_mode);
			if (mouse.m_mode==0) {
				switch(options.o_mode) {
					case 0: g2.draw(new Line2D.Double(x0, y0, x, y));break;
					case 1:
						mySquare square = new mySquare(x0, y0, x, y);
						g2.draw(square.shape);
						break;
					case 2:
						myRect rect = new myRect(x0, y0, x, y);
						g2.draw(rect.shape);
						break;
					case 3:
						myCircle circle = new myCircle(x0, y0, x, y);
						g2.draw(circle.shape);
						break;
					case 4:
						myEllipse ellipse = new myEllipse(x0, y0, x, y);
						g2.draw(ellipse.shape);
						break;
				}
			}
				//draw all of the accumulated shapes
				for (int i = 0 ; i < shapes.size(); i++) {
				    g2.setColor(objects_to_draw.get(i).col);
				    g2.draw(shapes.get(i));
				}
				
			}
		};
		setTitle("Sketch Pad");
		this.getContentPane().add(p);
	}
	
	public static void main(String arg[]) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				DrawShapes sketch = new DrawShapes();
				options = new SketchOptions();
				//save_load = new Save_Load();
				colors = new ColorOptions();
				mouse = new mouse_mode();
				objects_to_draw = new ArrayList<Objects>();
			}
		});
		
		      panel = new JPanel(new BorderLayout());
		      myList = new ArrayList<>(10);
		      scrollPane = new JScrollPane();
		      frame = new JFrame("Object List");

	}
	int x0,y0,x,y;
	public class myMouseHandler extends MouseAdapter {
	 public void mousePressed(MouseEvent e){ 
	   x0=e.getX(); y0=e.getY();

	   if(mouse.m_mode != 0 && e.getButton() == MouseEvent.BUTTON1) {  
	     for(int i = 0 ; i <  objects_to_draw.size() ; i++ ) {
		 //find the first object that we can select.
		 if (x0 > objects_to_draw.get(i).xo &&
			 y0 > objects_to_draw.get(i).yo &&
			  x0 < objects_to_draw.get(i).xo + objects_to_draw.get(i).w &&
			  y0 < objects_to_draw.get(i).yo + objects_to_draw.get(i).h) {
		      selected = i;
		  }
	      }
	   }
	     if(mouse.m_mode ==1 ) {
	      Objects temp = objects_to_draw.get(selected);
	      select_store = temp.col;
	      temp.col = Color.PINK;
	      objects_to_draw.set(selected, temp);
	     }
	     else if (mouse.m_mode == 2) {
		 objects_to_draw.remove(selected);
	     }
	     else if(mouse.m_mode == 3 && e.getButton() == MouseEvent.BUTTON3) {
		     //right click to paste
		      Objects temp = objects_to_draw.get(selected);
		      Objects mod = new Objects(temp.type, x0, y0, temp.x, temp.y, colors.c_mode);
		      objects_to_draw.add(mod);
		      
	     }
	 }
	 public void mouseReleased(MouseEvent e) { 	   
	     if(mouse.m_mode == 1) {  
			     Objects temp = objects_to_draw.get(selected);
			     temp.x0 = temp.x0 + (x - x0);
			     temp.y0 = temp.y0 + (y - y0);
			 	 temp.x = temp.x + (x - x0);
			 	 temp.y = temp.y + (y - y0);
			     temp.col = select_store;
			     objects_to_draw.set(selected, temp);
			     
	     }
	  else if (mouse.m_mode==0){
	     objects_to_draw.add(new Objects(options.o_mode, x0, y0, x, y, colors.c_mode));
	     myList.add("Shape Drawn " + options.o_mode);
	      final JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));   
	      scrollPane.setViewportView(list);
	      list.setLayoutOrientation(JList.VERTICAL);
	      panel.add(scrollPane);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.add(panel);
	      frame.setSize(500, 250);
	      frame.setLocationRelativeTo(null);
	      frame.setVisible(true);
	     
	  	}
	     repaint();  
	 }
	}
	public class myMouseMotionHandler extends MouseMotionAdapter {
	 public void mouseMoved(MouseEvent e) {  }
	 public void mouseDragged(MouseEvent e){ 
	     x=e.getX(); 
	     y=e.getY();
	     repaint();
	 }
	}
	// Inserting classes here for better drawing of shapes.
	public class rectangularShape extends Objects{
		public int getOrig(int a, int b, int segLength){
			if (a > b) {return a - segLength;}
			else {return a;}
		}
		public int calcDist(int a, int b){
			return Math.abs(a - b);
		}
		public void setDims(int x0, int y0, int x, int y){
			int width = calcDist(x0, x);
			int height = calcDist(y0, y);
			xo = getOrig(x0, x, width);
			yo = getOrig(y0, y, height);
			w = width;
			h = height;
		}
	}
	public class myRect extends rectangularShape{
		Rectangle shape;
		public myRect(){}
		public myRect(int x0, int y0, int x, int y){
			setDims(x0, y0, x, y);
			shape = new Rectangle(xo, yo, w, h);
		}
	}
	public class myEllipse extends rectangularShape{
		Ellipse2D.Double shape;
		public myEllipse(){}
		public myEllipse(int x0, int y0, int x, int y){
			setDims(x0, y0, x, y);
			shape = new Ellipse2D.Double(xo, yo, w, h);
		}
	}
	public class specialCase extends rectangularShape{
		@Override
		public void setDims(int x0, int y0, int x, int y){
			int width = calcDist(x0, x);
			int height = calcDist(y0, y);
			int l = Math.min(width, height);
			xo = getOrig(x0, x, l);
			yo = getOrig(y0, y, l);
			w = l;
			h = l;
		}
	}
	public class mySquare extends specialCase{
		Rectangle shape;
		public mySquare(){}
		public mySquare(int x0, int y0, int x, int y){
			setDims(x0, y0, x, y);
			shape = new Rectangle(xo, yo, w, h);
		}
	}
	public class myCircle extends specialCase {
		Ellipse2D.Double shape;
		public myCircle(){}
		public myCircle(int x0, int y0, int x, int y){
			setDims(x0, y0, x, y);
			shape = new Ellipse2D.Double(xo, yo, w, h);
		}
	}
}





