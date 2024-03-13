import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Screen extends JPanel implements MouseListener,ActionListener{

    private JButton clearButton;
    private Square[][] array;
    private int x,y;

    public Screen(){
        setLayout(null);
        setFocusable(true);
        

        array = new Square[100][100];

        for(int row=0;row<array.length;row++){
            for(int col=0;col<array[row].length;col++){
                array[row][col] = new Square(Color.white);
            }
        }

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(640,100,100,40);
        add(clearButton);

        addMouseListener(this);
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
                array[row][col].draw(g);
            }
        }

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
        
        if(e.getX() >= 50 && e.getX() <= 550 && e.getY()>= 50 && e.getY() <= 550){
        
        }

        repaint();
    }
    

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}


    public void clear(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {

        }
    }
}
