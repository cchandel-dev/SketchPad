

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.*;

public class DrawShapes extends JFrame {
    	static ArrayList<Objects> objects_to_draw = new ArrayList<Objects>() ;
    	public int mode;
    	public static SketchOptions options;
    	public static ColorOptions colors;
    	public static Save_Load save_load;
    	public static mouse_mode mouse;
    	public static List<String> myList;
    	public static JPanel panel;
    	public static JScrollPane scrollPane;
        public static ArrayList<Shape> shapes;
    	public static JFrame frame;
    	public static int selected;
    	public static Color select_store;
        public static ArrayList<Integer> xPoints, yPoints;
        public static int max = 1000, display_pointer = 0, last_pointer;
        public static String Checkpoint_path= "C:\\Users\\12267\\Jave Test\\Checkpoints";
    	  //JFrame frame;  
    	  JButton btn, undo, redo;
    	  JRadioButton rBtn1, rBtn2;
    	  JTextField address;
    	  File file;
    	  FileOutputStream fileOut;
    	  boolean s_cmd;
    	  boolean l_cmd = false;
    	public void Save(File file) {
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
    	public void Load(File file) {
    	 try {
    	     	System.out.println("file being read is called: "+file.getName());
	         FileInputStream fileIn = new FileInputStream(file);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         System.out.println("before reading object:" + objects_to_draw.size());
	         objects_to_draw.clear();
	         objects_to_draw = (ArrayList<Objects>) in.readObject();
	        System.out.println("after reading object:" + objects_to_draw.size());
	         in.close();
	         fileIn.close();
	         repaint();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Class not found");
	         c.printStackTrace();
	         return;
	      }     
    	}
    	public void Checkpoint() {
    	    File Checkpoint = new File(Checkpoint_path);
    	    File[] file_array = Checkpoint.listFiles();
    	    last_pointer = file_array.length;
    	    display_pointer++;
    	    if (display_pointer != last_pointer) {
    		delete_files(display_pointer);
    	    }
    	    System.out.println("Checkpoint number " + display_pointer);
    	    if(file_array.length < max && objects_to_draw != null) {
    	    //code below in Checkpoint method is copied from -> https://www.geeksforgeeks.org/how-to-rename-all-files-of-a-folder-using-java/
    		Save(new File(Checkpoint_path + "\\" + display_pointer + ".txt"));
      	    }
    	    else {
    		JOptionPane.showMessageDialog(this,"Max Checkpoints made"); 
    	    }
    	}
    	public void undo_act() {
    	    
    	    if(display_pointer >= 1) {
    		Load(new File(Checkpoint_path + "\\" + display_pointer + ".txt"));
    		display_pointer--;
    	    }
    	    else {
    		JOptionPane.showMessageDialog(this,"Can't Undo any further"); 
    	    }
    	}
    	public void redo_act() {
    	   // last_pointer = file.
    	    File Checkpoint = new File(Checkpoint_path);
    	    File[] file_array = Checkpoint.listFiles();
    	    last_pointer = file_array.length;
    	    if(display_pointer <  last_pointer - 1) {
    		display_pointer++;
    		Load(new File(Checkpoint_path + "\\" + display_pointer + ".txt"));
    	    }
    	    else {
    		JOptionPane.showMessageDialog(this,"Can't Redo any further"); 
    	    }
    	}
    	public void delete_files(int start) {
   	    File Checkpoint = new File(Checkpoint_path);
    	    File[] file_array = Checkpoint.listFiles();
    	    for(int i = start; i < file_array.length; i++) {
    		file_array[i].delete();
    	    }
       	}
	public DrawShapes() {
		setSize(new Dimension(800, 820));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//delete all files in the folder
		delete_files(0);
		Checkpoint();
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
		    address.setText(""+display_pointer);
		   
		    undo = new JButton("Undo");
		    undo.setBounds(100,400,80,30); 
		    undo.addActionListener(new ActionListener() {
		            @SuppressWarnings("unchecked")
			    @Override
		            public void actionPerformed(ActionEvent e){  
		        	undo_act();
		            }  
		        });
		    redo = new JButton("Redo");
		    redo.setBounds(100,500,80,30);
		    redo.addActionListener(new ActionListener() {
		            @SuppressWarnings("unchecked")
			    @Override
		            public void actionPerformed(ActionEvent e){  
		        	redo_act();
		            }  
		        });
		    
		    btn = new JButton("Submit");  
		    btn.setBounds(100,300,80,30);  
		    btn.addActionListener(new ActionListener() {
		            @SuppressWarnings("unchecked")
			    @Override
		            public void actionPerformed(ActionEvent e){  
		        	    file = new File(address.getText());
		        	    if(rBtn1.isSelected()){
		        	      Save(file);
		        	    }  
		        	    else if(rBtn2.isSelected()){  
		        		Load(file);
		        	    } 
		            }  
		        });
		    // Add buttons to frame
		    add(label);
		    add(address);
		    add(rBtn1);
		    add(rBtn2);  
		    add(btn); 
		    add(undo);
		    add(redo);
		    
		    //default is line
		    rBtn1.setSelected(true);
		addMouseListener( new myMouseHandler());
		addMouseMotionListener( new myMouseMotionHandler());
		JPanel p = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
		                Graphics2D g2 = (Graphics2D) g;
		                shapes = new ArrayList <Shape>();
		                address.setText(""+display_pointer);
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
		                        case 5:
		                            myPoly polyOpen = new myPoly(temp.xPts, temp.yPts, temp.xPts.length);
		                            shapes.add(polyOpen.shape);break;
		                        case 6:
		                            myPoly polyClosed = new myPoly(temp.xPts, temp.yPts, temp.xPts.length);
		                            shapes.add(polyClosed.shape);break;
		                        case 7:
		                            myPoly sketch = new myPoly(temp.xPts, temp.yPts, temp.xPts.length);
		                            shapes.add(sketch.shape);break;
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

		                        case 5:
		                        case 6:
		                            g2.draw(new Line2D.Double(prevX, prevY, x, y));
		                            g2.drawPolyline(xPoints.stream().mapToInt(Integer::intValue).toArray(), yPoints.stream().mapToInt(Integer::intValue).toArray(),
		                                    xPoints.size());
		                            break;

		                        case 7:
		                            for(int i = 0; i < xPoints.size(); i++)
		                                g2.drawLine(xPoints.get(i), yPoints.get(i), xPoints.get(i), yPoints.get(i));break;
		                    }
		                }
		                //draw all of the accumulated shapes
		                for (int i = 0 ; i < shapes.size(); i++) {
		                    g2.setColor(objects_to_draw.get(i).col);

		                    if(objects_to_draw.get(i).type < 5){
		                        g2.draw(shapes.get(i));

		                    }else if(objects_to_draw.get(i).type == 5){//polygon(s), freeSketch (TBD)
		                        g2.drawPolyline(objects_to_draw.get(i).xPts, objects_to_draw.get(i).yPts, objects_to_draw.get(i).xPts.length);

		                    }else if(objects_to_draw.get(i).type == 6){
		                        g2.drawPolygon(objects_to_draw.get(i).xPts, objects_to_draw.get(i).yPts, objects_to_draw.get(i).xPts.length);
		                    }else{
		                        for(int j = 0; j < objects_to_draw.get(i).xPts.length; j++)
		                            g2.drawLine(objects_to_draw.get(i).xPts[j], objects_to_draw.get(i).yPts[j],
		                                    objects_to_draw.get(i).xPts[j], objects_to_draw.get(i).yPts[j]);
		                    }
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
		                xPoints = new ArrayList<Integer>();
		                yPoints = new ArrayList<Integer>();
			}
		});
	}
	    int x0,y0,x,y, prevX, prevY, selectX, selectY;
	    boolean bFound;
	    public class myMouseHandler extends MouseAdapter {
	        public void mousePressed(MouseEvent e){
	            x0=e.getX(); y0=e.getY();
	            if(mouse.m_mode != 0 && e.getButton() == MouseEvent.BUTTON1) {
	                for (int i = 0; i < objects_to_draw.size(); i++) {
	                    //find the first object that we can click
	                    if (shapes.get(i).contains(x0, y0)) {
	                        selected = i;
	                        selectX = x0;
	                        selectY = y0;
	                        bFound = true;
	                        break;
	                    } else {
	                        bFound = false;
	                    }
	                }

	                Objects temp = objects_to_draw.get(selected);
	                select_store = temp.col;
	                temp.col = Color.PINK;
	                objects_to_draw.set(selected, temp);

	            }else if(options.o_mode > 4 && xPoints.isEmpty()){
	                prevX = x0;
	                prevY = y0;
	                xPoints.add(prevX);
	                yPoints.add(prevY);

	            }else if(options.o_mode > 4 && x0 == prevX && y0 == prevY){
	                objects_to_draw.add(new Objects(options.o_mode, x0, y0, x-x0, y-y0, colors.c_mode,
	                        xPoints.stream().mapToInt(Integer::intValue).toArray(),
	                        yPoints.stream().mapToInt(Integer::intValue).toArray()));
	                xPoints.clear();
	                yPoints.clear();
	                System.out.println("I am closing a polygon!");
	                Checkpoint();
	                repaint();
	            }
	        }
		public void mouseReleased(MouseEvent e) {
	          if(mouse.m_mode==3 &&  e.getButton() == MouseEvent.BUTTON3) {
	        	Objects temp = objects_to_draw.get(selected);
	        	if (temp.type < 5) {
	              	int width = Math.abs(temp.x - temp.x0);
	              	int height = Math.abs(temp.y - temp.y0);
	        	Objects mod = new Objects(temp.type, x0 , y0 , width+x0,  height+y0, temp.col, temp.xPts, temp.yPts);
	        	objects_to_draw.add(mod);
	        	}
	        	else {//for type 5,6 & 7
	        	    	int[] x_points = new int[temp.xPts.length];
	        	    	int[] y_points = new int[temp.yPts.length];
	                        for(int i = 0; i < temp.xPts.length; i++){
	                            //update all the points of temp
	                            x_points[i] = temp.xPts[i] - (selectX - x0);
	                            y_points[i] = temp.yPts[i] - (selectY - y0);	                            
	                        }
	                        Objects mod = new Objects(temp.type, 0 , 0 , 0, 0, temp.col, x_points, y_points);
		        	objects_to_draw.add(mod);
		        	
	        	}
	        	Checkpoint();
	        	repaint();
	           }
		    else if(mouse.m_mode == 2) {
	        	objects_to_draw.remove(selected);
	        	Checkpoint();
	        	repaint();
	            }
	            else if(mouse.m_mode == 1) {

	                if (bFound) {
	                    Objects temp = objects_to_draw.get(selected);

	                    if(temp.type > 4){

	                        for(int i = 0; i < temp.xPts.length; i++){
	                            //update all the points of temp
	                            temp.xPts[i] = temp.xPts[i] + (x - x0);
	                            temp.yPts[i] = temp.yPts[i] + (y - y0);
	                            temp.col = select_store;
	                            objects_to_draw.set(selected, temp);
	                        }
	                    }
	                    else{
	                        temp.x0 = temp.x0 + (x - x0);
	                        temp.y0 = temp.y0 + (y - y0);
	                        temp.x = temp.x + (x - x0);
	                        temp.y = temp.y + (y - y0);
	                        temp.col = select_store;
	                        objects_to_draw.set(selected, temp);
	                       
	                    }
	                }
	                Checkpoint();
	                repaint();
	            }
	            else if (mouse.m_mode==0){
	                if(options.o_mode < 5) {///option 0,1,2,3,4
	                    objects_to_draw.add(new Objects(options.o_mode, x0, y0, x, y, colors.c_mode, null, null));
	                    Checkpoint();
	                    repaint();
	                }else if(!xPoints.isEmpty() && options.o_mode != 7){//option 6 and 5
	                    xPoints.add(x);
	                    yPoints.add(y);
	                    prevX = x;
	                    prevY = y;
	                    System.out.println("I am Painting a Polygon!");
	                    Checkpoint();
	                    repaint();

	                }else if(!xPoints.isEmpty()){//option 7
	                    objects_to_draw.add(new Objects(options.o_mode, x0, y0, x-x0, y-y0, colors.c_mode,
	                            xPoints.stream().mapToInt(Integer::intValue).toArray(),
	                            yPoints.stream().mapToInt(Integer::intValue).toArray()));
	                    xPoints.clear();
	                    yPoints.clear();
	                    System.out.println("I am Painting a Scribble!");
	                    Checkpoint();
	                    repaint();
	                }
	            }
	        }
	        
	    }
	    public class myMouseMotionHandler extends MouseMotionAdapter {
	        public void mouseMoved(MouseEvent e) {  }
	        public void mouseDragged(MouseEvent e){
	            x=e.getX();
	            y=e.getY();
	            if(options.o_mode == 7){
	                xPoints.add(x);
	                yPoints.add(y);
	                repaint();
	            }else
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

	    public class myPoly {
	        Polygon shape;
	        public myPoly(int[] xPoints, int[] yPoints, int numPoints){
	            shape = new Polygon(xPoints, yPoints, numPoints);
	        }
	    }
	
}





