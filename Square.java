import java.awt.Color;
import java.awt.Graphics;


public class Square{
    int x,y;
    Color color;

    public Square(Color color){
        this.color = color;

    }

    public void drawCanvas(Graphics g, int x, int y){
        g.setColor(color);
        g.fillRect(x, y, 5, 5);
    }


    public void setColor(Color color){
        this.color = color;
    }
}