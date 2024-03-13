import java.awt.Color;
import java.awt.Graphics;


public class Square{
    int x,y;
    Color color;

    public Square(Color color){
        this.color = color;

    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, 4, 4);
    }

    public void setColor(Color color){
        this.color = color;
    }
}