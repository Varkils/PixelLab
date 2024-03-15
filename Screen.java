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

//screen methods impliments mouse listener for mouse clicks, mouse motion listener for the mouse drag, and action listener for the button
public class Screen extends JPanel implements MouseListener, MouseMotionListener, ActionListener{

    //instance variables
    private JButton clearButton;
    //array of squares to make the canvas
    private Square[][] array;
    //2D array of colors to make the color palette selection
    private Color[][] colors;
    //normal array of colors because it was easier to fill with the colors because I stpeeped inorder to get a gradient type of color palette
    private Color[] colorsArray;
    //color varaible to store the color that should be drawn
    //set to white to start so the canvas is made blank
    private Color color = Color.white;
    private int row, col;
    private BufferedImage background;

    public Screen(){
        setLayout(null);
        setFocusable(true);
        //background image
        //Image by <a href="https://pixabay.com/users/8926-8926/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=1909992">8926</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=1909992">Pixabay</a>
        
        //load the background image
        try {
            background = ImageIO.read(new File("background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //instantiate both arrays. one for the canvas and one for the color palette
        array = new Square[100][100];
        colors = new Color[6][6];

        //for loops to fill the canvas with squares
        for(int row=0;row<array.length;row++){
            for(int col=0;col<array[row].length;col++){
                array[row][col] = new Square(color);
            }
        }

        //creates the clear button
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(600,100,150,50);
        add(clearButton);

        //adds the mouse listeners and motion mouse listener to the JPanel
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    //sets the size of the JPanel
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
    //draws everything
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //draws the background image
        g.drawImage(background, 0, 0, null);

        //for loops to draw the canvas when someone clicks
        for(int row=0;row<array.length;row++){
            for(int col=0;col<array[row].length;col++){
                array[row][col].drawCanvas(g, 50+col*5, 50+row*5);
            }
        }
        //draw the lines for the outlined pixels on the canvas
        g.setColor(new  Color (0,0,0,50));
        for(int i = 0; i<101; i++){
            g.drawLine(50, 50 + i*5, 550, 50 + i*5);
            g.drawLine(50  + i*5, 50, 50 + i*5, 550);

        }
        //draws the pixel when the user cicks on teh canvas
        array[row][col].drawCanvas(g, 50+col*5, 50+row*5);
        
        //draws the color palette
        drawColors(g);
        
    }

    //method to draw the color palette
    public void drawColors(Graphics g){
        //call make colors so the colors array is filled with colors
        makeColors();
        //loop to draw the colors
        for(int row = 0; row<colors.length; row++){
            for(int col = 0; col<colors[row].length; col++){
                g.setColor(colors[row][col]);
                g.fillRect(600 + col*25, 250 + row*25, 25, 25);
            }
        }
    }
    //method to fill the colors array with colors
    public void makeColors(){
        //instantiate normal array of 36 colors
        colorsArray = new Color[36];
        //the way i did the colors was step each value by 51 either up or down in sequance for either r,g,or b.
        for(int i = 0; i<6;i++){
            colorsArray[i] = new Color(255,i*51,0);
        }
        for(int i = 1; i<6;i++){
            colorsArray[i+5] = new Color(255 - i*51,255,0);
        }
        for(int i = 1; i<6;i++){
            colorsArray[i+10] = new Color(0,255,i*51);
        }
        for(int i = 1; i<6;i++){
            colorsArray[i+15] = new Color(0,255 - i*51,255);
        }
        for(int i = 1; i<6;i++){
            colorsArray[i+20] = new Color(i*51,0,255);
        }
        for(int i = 0; i<5;i++){
            colorsArray[i+25] = new Color(255,0,255 - i*51);
        }
        //gray scale for more shades at the botttom
        colorsArray[30] = Color.black;
        colorsArray[31] = new Color(51,51,51);
        colorsArray[32] = new  Color(102,102,102);
        colorsArray[33] = new  Color(153,153,153);
        colorsArray[34] = new  Color(204,204,204);
        colorsArray[35] = Color.white;
        


        //convert normal array to 2D array
        int index = 0;
        for (int row = 0; row < colors.length; row++) {
            for (int col = 0; col < colors[row].length; col++) {
                colors[row][col] = colorsArray[index++];
            }
        }
        
    }


    //method that determines what to do if the mouse is pressed
    public void mousePressed(MouseEvent e) {
        //if the mouse is within the canvas, then it does the math to determine which mixel was clicked. then it sets the color of the pixel
        //the outer bound has to be < instead of <= otherwise it can go out of bounds for the array
    if (e.getX() >= 50 && e.getX() < 550 && e.getY() >= 50 && e.getY() < 550) {
        int clickedRowCanvas = (e.getY() - 50) / 5;
        int clickedColCanvas = (e.getX() - 50) / 5;

            array[clickedRowCanvas][clickedColCanvas].setColor(color);
            repaint();
            //if the mouse is witthin the color palatte, then it determines which color was clicked and sets the color to that color
    } else if (e.getX() >= 600 && e.getX() <= 750 && e.getY() >= 250 && e.getY() <= 400) {
        int clickedRowColors = (e.getY() - 250) / 25;
        int clickedColColors = (e.getX() - 600) / 25;

        color = colors[clickedRowColors][clickedColColors];
        System.out.println(color);
        repaint();
    }
}

    
    //extra methods that are required for the mouse listener
    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    //same code as in mouse pressed for setting the color but this method allows for you to drag the mouse instead of click each square
    //the outer bound has to be < instead of <= otherwise it can go out of bounds for the array
    public void mouseDragged(MouseEvent e) {
        if (e.getX() >= 50 && e.getX() < 550 && e.getY() >= 50 && e.getY() < 550) {
            int clickedRow = (e.getY() - 50) / 5;
            int clickedCol = (e.getX() - 50) / 5;
    
                array[clickedRow][clickedCol].setColor(color);
                repaint();
        }
    }

    //exxtra method that is required for mouse motion listener
    public void mouseMoved(MouseEvent e) {
        
    }

    //method to clear the canvas by setting all the squares to white
    public void clear(){
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                array[row][col].setColor(Color.white);
            }
        }
        repaint();
    }
    //method to detrmine what happens when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            clear();
        }
    }
}
