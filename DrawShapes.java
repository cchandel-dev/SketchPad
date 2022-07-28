

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DrawShapes extends JFrame {
    	static ArrayList<Objects> objects_to_draw;
	public DrawShapes() {

		setSize(new Dimension(320, 320));
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
				    case 0: shapes.add(new Line2D.Double(temp.x0, temp.y0, temp.x, temp.y));break;
				    case 1: shapes.add(new Rectangle(temp.x0, temp.y0, temp.y, temp.y));break;
				    case 2: shapes.add(new Rectangle(temp.x0, temp.y0, temp.x, temp.y));break;
				    case 3: shapes.add(new Ellipse2D.Double(temp.x0, temp.y0, temp.y, temp.y));break;
				    case 4: shapes.add(new Ellipse2D.Double(temp.x0, temp.y0, temp.x, temp.y));break;
				   // case 5: 
				   // case 6:
				    }
				}
				//draw all of the accumulated shapes
				for (int i =0 ; i < shapes.size(); i++) {
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
				new DrawShapes();
				objects_to_draw = new ArrayList<Objects>();
			}
		});
	}
	int x0,y0,x,y;
	public class myMouseHandler extends MouseAdapter {
	 public void mousePressed(MouseEvent e){ x0=e.getX(); y0=e.getY(); }
	 public void mouseReleased(MouseEvent e) { 	    
	     objects_to_draw.add(new Objects(1, x0, y0, x, y));
	     repaint(); }
	}
	public class myMouseMotionHandler extends MouseMotionAdapter {
	 public void mouseMoved(MouseEvent e) {  }
	 public void mouseDragged(MouseEvent e){ 
	     x=e.getX(); 
	     y=e.getY(); 
	 }
	}
	//public void paint(Graphics g) {   }
}





