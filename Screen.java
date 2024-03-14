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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Screen extends JPanel implements MouseListener, MouseMotionListener, ActionListener{

    private JButton clearButton;
    private Square[][] array;
    private Color[][] colors;
    private Color[] colorsArray;
    private Color color = Color.white;
    private int row, col;
    private BufferedImage background;

    public Screen(){
        setLayout(null);
        setFocusable(true);
        //Image by <a href="https://pixabay.com/users/8926-8926/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=1909992">8926</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=1909992">Pixabay</a>
        
        try {
            background = ImageIO.read(new File("background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        array = new Square[100][100];
        colors = new Color[6][6];

        for(int row=0;row<array.length;row++){
            for(int col=0;col<array[row].length;col++){
                array[row][col] = new Square(color);
            }
        }

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(600,100,150,50);
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
        g.drawImage(background, 0, 0, null);



        for(int row=0;row<array.length;row++){
            for(int col=0;col<array[row].length;col++){
                array[row][col].drawCanvas(g, 50+col*5, 50+row*5);
            }
        }

        g.setColor(Color.black);
        for(int i = 0; i<101; i++){
            g.drawLine(50, 50 + i*5, 550, 50 + i*5);
            g.drawLine(50  + i*5, 50, 50 + i*5, 550);

        }

        for(int i = 0; i<7; i++){
            g.drawLine(600, 250 + i*25, 750, 250 + i*25);
            g.drawLine(600 + i*25, 250, 600 + i*25, 400);
        }



        array[row][col].drawCanvas(g, 50+col*5, 50+row*5);
        
        drawColors(g);
        
    }

    public void drawColors(Graphics g){
        makeColors();
        for(int row = 0; row<colors.length; row++){
            for(int col = 0; col<colors[row].length; col++){
                g.setColor(colors[row][col]);
                g.fillRect(600 + col*25, 250 + row*25, 25, 25);
            }
        }
    }
    public void makeColors(){
        colorsArray = new Color[36];
        for(int i = 0; i<6;i++){
            colorsArray[i] = new Color(255,i*51,0);
        }
        for(int i = 0; i<6;i++){
            colorsArray[i+6] = new Color(255 - i*51,255,0);
        }
        for(int i = 0; i<6;i++){
            colorsArray[i+12] = new Color(0,255,i*51);
        }
        for(int i = 0; i<6;i++){
            colorsArray[i+18] = new Color(0,255 - i*51,255);
        }
        for(int i = 0; i<6;i++){
            colorsArray[i+24] = new Color(i*51,0,255);
        }
        for(int i = 0; i<4;i++){
            colorsArray[i+30] = new Color(255,0,255 - i*51);
        }
        colorsArray[34] = Color.black;
        colorsArray[35] = Color.white;
        



        int index = 0;
        for (int row = 0; row < colors.length; row++) {
            for (int col = 0; col < colors[row].length; col++) {
                colors[row][col] = colorsArray[index++];
            }
        }
        
    }



    public void mousePressed(MouseEvent e) {
    if (e.getX() >= 50 && e.getX() < 550 && e.getY() >= 50 && e.getY() < 550) {
        int clickedRowCanvas = (e.getY() - 50) / 5;
        int clickedColCanvas = (e.getX() - 50) / 5;

            array[clickedRowCanvas][clickedColCanvas].setColor(color);
            repaint();
    } else if (e.getX() >= 600 && e.getX() <= 750 && e.getY() >= 250 && e.getY() <= 400) {
        int clickedRowColors = (e.getY() - 250) / 25;
        int clickedColColors = (e.getX() - 600) / 25;

        color = colors[clickedRowColors][clickedColColors];
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
