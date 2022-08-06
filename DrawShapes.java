

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class DrawShapes extends JFrame {
    	static ArrayList<Objects> objects_to_draw;
    	public int mode;
    	public static SketchOptions options;
    	public static ColorOptions colors;
    	public static mouse_mode mouse;
    	public static List<String> myList;
    	public static JPanel panel;
    	public static JScrollPane scrollPane;
    	public static JFrame frame;
    	public static int selected;
    	public static Color select_store;
	public DrawShapes() {

		setSize(new Dimension(600, 620));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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
	   if(mouse.m_mode == 1) {  
	     for(int i = 0 ; i <  objects_to_draw.size() ; i++ ) {
		 //find the first object that we can select.
		 if (x0 > objects_to_draw.get(i).xo &&
			 y0 > objects_to_draw.get(i).yo &&
			  x0 < objects_to_draw.get(i).xo + objects_to_draw.get(i).w &&
			  y0 < objects_to_draw.get(i).yo + objects_to_draw.get(i).h) {
		      selected = i;
		  }
	      }
	      Objects temp = objects_to_draw.get(selected);
	      select_store = temp.col;
	      temp.col = Color.PINK;
	      objects_to_draw.set(selected, temp);
	   }
	 }
	 public void mouseReleased(MouseEvent e) { 	   
	     if(mouse.m_mode == 1) {  
			     Objects temp = objects_to_draw.get(selected);
			     temp.x0 = temp.x0 + (x - x0);
			     temp.y0 = temp.y0 + (y - y0);
			     temp.col = select_store;
			     objects_to_draw.set(selected, temp);
			     repaint();
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
	     repaint();
	 }
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





