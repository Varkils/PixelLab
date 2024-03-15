import java.awt.Color;
import java.awt.Graphics;


public class Square{
    //instance variable for color
    Color color;

    //constructor to set the color
    public Square(Color color){
        this.color = color;
    }

    //method to draw the squares
    public void drawCanvas(Graphics g, int x, int y){
        g.setColor(color);
        g.fillRect(x, y, 5, 5);
    }

    //method to set the color
    public void setColor(Color color){
        this.color = color;
    }
}