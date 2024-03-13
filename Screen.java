import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Screen extends JPanel implements MouseListener, MouseMotionListener, ActionListener{

    private JButton clearButton;
    private Square[][] array;
    private Color color = Color.white;
    private int row, col;

    public Screen(){
        setLayout(null);
        setFocusable(true);
        

        array = new Square[100][100];

        for(int row=0;row<array.length;row++){
            for(int col=0;col<array[row].length;col++){
                array[row][col] = new Square(color);
            }
        }

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(640,100,100,40);
        add(clearButton);

        addMouseListener(this);
        addMouseMotionListener(this);
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

        for(int row=0;row<array.length;row++){
            for(int col=0;col<array[row].length;col++){
                array[row][col].draw(g, 50+col*5, 50+row*5);
            }
        }


        array[row][col].draw(g, 50+col*5, 50+row*5);
        
        g.setColor(Color.red);
        g.fillRect(601,251,49,49);

        g.setColor(Color.orange);
        g.fillRect(651,251,49,49);

        g.setColor(Color.yellow);
        g.fillRect(701,251,49,49);

        g.setColor(Color.green);
        g.fillRect(601,301,49,49);

        g.setColor(Color.blue);
        g.fillRect(651,301,49,49);

        g.setColor(new Color(0,128,255));
        g.fillRect(701,301,49,49);

        g.setColor(new Color(255,51,153));
        g.fillRect(601,351,49,49);

        g.setColor(Color.black);;
        g.fillRect(651,351,49,49);

        g.setColor(Color.white);;
        g.fillRect(701,351,49,49);
        
    }



    public void mousePressed(MouseEvent e) {
    if (e.getX() >= 50 && e.getX() < 550 && e.getY() >= 50 && e.getY() < 550) {
        int clickedRow = (e.getY() - 50) / 5;
        int clickedCol = (e.getX() - 50) / 5;

        // Check if the clicked coordinates are within the array bounds
        
            // Update the color of the clicked cell
            array[clickedRow][clickedCol].setColor(color);
            repaint();
    } else if (e.getX() >= 600 && e.getX() <= 750 && e.getY() >= 250 && e.getY() <= 400) {
        // Update color based on the color palette
        if (e.getX() >= 600 && e.getX() <= 650 && e.getY() >= 250 && e.getY() <= 300) {
            color = Color.red;
        } else if (e.getX() >= 650 && e.getX() <= 700 && e.getY() >= 250 && e.getY() <= 300) {
            color = Color.orange;
        } else if (e.getX() >= 700 && e.getX() <= 750 && e.getY() >= 250 && e.getY() <= 300) {
            color = Color.yellow;
        } else if (e.getX() >= 600 && e.getX() <= 650 && e.getY() >= 300 && e.getY() <= 350) {
            color = Color.green;
        } else if (e.getX() >= 650 && e.getX() <= 700 && e.getY() >= 300 && e.getY() <= 350) {
            color = Color.blue;
        } else if (e.getX() >= 700 && e.getX() <= 750 && e.getY() >= 300 && e.getY() <= 350) {
            color = new Color(0, 128, 255);
        } else if (e.getX() >= 600 && e.getX() <= 650 && e.getY() >= 350 && e.getY() <= 400) {
            color = new Color(255, 51, 153);
        } else if (e.getX() >= 650 && e.getX() <= 700 && e.getY() >= 350 && e.getY() <= 400) {
            color = Color.black;
        } else if (e.getX() >= 700 && e.getX() <= 750 && e.getY() >= 350 && e.getY() <= 400) {
            color = Color.white;
        }
        System.out.println(color);
        repaint();
    }
}

    

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}


    public void clear(){
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                array[row][col].setColor(Color.white);
            }
        }
        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            clear();
        }
    }


    public void mouseDragged(MouseEvent e) {
        if (e.getX() >= 50 && e.getX() < 550 && e.getY() >= 50 && e.getY() < 550) {
            int clickedRow = (e.getY() - 50) / 5;
            int clickedCol = (e.getX() - 50) / 5;
    
                array[clickedRow][clickedCol].setColor(color);
                repaint();
        }
    }


    public void mouseMoved(MouseEvent e) {
        
    }
}
