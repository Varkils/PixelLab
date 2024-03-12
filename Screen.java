import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Screen extends JPanel implements MouseListener,ActionListener{

    private JButton clearButton;
    private int[][] array;
    public Screen(){
        setLayout(null);
        setFocusable(true);

        array =  new  int[100][100];

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(700,100,50,20);
        add(clearButton);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i<101; i++){
            g.drawLine(50, 50 + i*5, 550, 50 + i*5);
            g.drawLine(50  + i*5, 50, 50 + i*5, 550);

        }

        for(int i = 0; i<4; i++){
            g.drawLine(600, 250 + i*50, 750, 250 + i*50);
            g.drawLine(600 + i*50, 250, 600 + i*50, 400);
        }
    }



    public void mousePressed(MouseEvent e) {
        if (e.getX() >= 50 && e.getX() <= 550 && e.getY() >= 50 && e.getY() <= 550) {
            
        }
        
        repaint();
    }
    

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {

        }
    }
}
