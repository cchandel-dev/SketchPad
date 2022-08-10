import java.awt.Color;
import java.util.ArrayList;

public class Objects implements java.io.Serializable{
    public int x0, y0, x, y;
    public int xo, yo, w, h; // For representing the top left corner coordinates and dimensions of the shape.
    public Color col;
    public int type; // line = 0 , square = 1, rectangle = 2, circle = 3, ellipse = 4, polygon = 5, scribble = 6
    public  ArrayList<point>points; //used for type 5 and type 6
    int[] xPts, yPts;
    //x0 & y0 are the top-left corner
    //x & y are allegedly the widths
    public Objects(){};
    public Objects(int type, int x0, int y0, int x, int y, Color col, int[] xPoints, int[] yPoints) {
        this.x0 = x0;
        this.y0 = y0;
        this.x = x;
        this.y = y;
        this.type = type;
        this.col = col;

        if(xPoints != null){
           this.xPts = xPoints;
           this.yPts = yPoints;
        }
    }
    public class point{
        int x, y;
    }
}