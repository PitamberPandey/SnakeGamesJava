package SnakeGamesJava;

import javax.swing.*;

public class Frame extends JFrame{
    Frame(){
        super("Snake game");
        add(new Board());
        pack();
        //setLocation(100,100);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        //setSize(400,400);


    }


    public static void main(String[] args) {
        new Frame();

    }
}



