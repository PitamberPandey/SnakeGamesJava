package SnakeGamesJava;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyAdapter;

public class Board extends JPanel  implements ActionListener {
    private Image apple;
    private Image dot;
    private Image head;
    private int dots;
    private final int All_DOTS=900;
    private final int DOTS_SIZE=10;
    private final int RANDOM_POSITION=29;
    private final int x[]=new int[900];
    private final int y[]=new int[900];
    private int apple_x;
    private int apple_y;
    private Timer timer;
    private boolean rightdirection=true;
    private boolean leftdirection=false;
            ;private boolean updirection=false;

    private boolean downdirection=false;
    private boolean ingame=true;


    public Board() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(300,300));
        initGame();
        loadImage();
    }

    public void loadImage() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("SnakeGamesJava/icons/apple.png"));
        apple = i1.getImage();
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("SnakeGamesJava/icons/dot.png"));
        dot = i2.getImage();
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("SnakeGamesJava/icons/head.png"));
        head = i3.getImage();
    }

    public void initGame() {
        dots = 3;
        for( int i=0;i<dots;i++){
            y[i]=50;
            x[i]=50-i*DOTS_SIZE;
        }
        locateApple();
        timer =new Timer(140,this);
        timer.start();

    }
    public  void locateApple(){
        int r=(int)(Math.random()*RANDOM_POSITION);
        apple_x=r*DOTS_SIZE;
         r=(int)(Math.random()*RANDOM_POSITION);
        apple_y=r*DOTS_SIZE;




    }
    public void paint(Graphics g){
        super.paint(g);
        draw(g);

    }
    public void draw(Graphics g){
        if (ingame) {
            g.drawImage(apple, apple_x, apple_y, this);
            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                } else {
                    g.drawImage(dot, x[i], y[i], this);
                }

            }
            Toolkit.getDefaultToolkit().sync();
        }
        else{
            gameover(g);
        }
    }
    public void gameover(Graphics g){
        String msg="Game Over !!!";
        Font font =new Font("SAN SERIF",Font.BOLD,16);
        g.setColor(Color.WHITE);
        g.setFont(font);

                FontMetrics metrics=getFontMetrics(font);
g.drawString(msg,(300-metrics.stringWidth(msg))/2,300/2);
    }

    public void move(){
        for (int i=dots;i>0;i-- ){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if (leftdirection){
           x[0]=x[0]-DOTS_SIZE;
       }
        if (rightdirection){
            x[0]=x[0]+DOTS_SIZE;
        }
        if (updirection){
            y[0]=y[0]-DOTS_SIZE;
        }
        if (downdirection){
            y[0]=y[0]+DOTS_SIZE;
        }
        //x[0]+=DOTS_SIZE;
        //y[0]+=DOTS_SIZE;
    }
    public void checkapple(){
        if((x[0]==apple_x) && (y[0]==apple_y)){
            dots++;
            locateApple();

        }
    }
    public void checkcollision(){
        for (int i=dots; i>0; i--){
          if ((i>4)&&(x[0]==x[i])&&(y[0]==y[i])) {
              ingame=false;
          }
        }
        if (x[0]>=400){
            ingame=false;
        }
        if (y[0]>=400){
            ingame=false;
        }
        if (x[0]<0){
            ingame=false;
        }
        if (y[0]<0){
            ingame=false;
        }
        if (!ingame){
            timer.stop();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
       if (ingame){
           move();
        checkapple();
        checkcollision();
       }
        repaint();
    }
    public class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();
            if(key==KeyEvent.VK_LEFT &&(!rightdirection)){
                leftdirection=true;
                updirection=false;
                downdirection=false;
            }
            if(key==KeyEvent.VK_RIGHT &&(!leftdirection)){
                rightdirection=true;
                updirection=false;
                downdirection=false;
            }
            if(key==KeyEvent.VK_DOWN &&(!updirection)){
                downdirection=true;
                leftdirection=false;
             rightdirection=false;
            }
            if(key==KeyEvent.VK_UP &&(!downdirection)){
                updirection=true;
                rightdirection=false;
                leftdirection=false;
            }




        }
    }

    }


