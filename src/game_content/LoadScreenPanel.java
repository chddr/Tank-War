package game_content;

import resources_classes.ScaledImage;

import javax.swing.*;

import java.awt.*;

import static game_content.GameWindow.*;

public class LoadScreenPanel extends JPanel {

    public LoadScreenPanel(int level){
        setLayout(null);
        setBounds(0,0,windowWidth, windowHeight);
        setBackground(Color.DARK_GRAY);
        if(level==0){
            addWinText();
        } else if(level==-1){
            addDefeatText();
        } else
            addLoadText(level);

    }

    private void addLoadText(int level){
        JLabel text = new JLabel("Stage " + level);
        text.setFont(new Font(fontName,0,80));
        text.setForeground(Color.WHITE);
        text.setBounds(125,75,800,500);
        text.setOpaque(false);
        add(text);

//        JLabel gif = new JLabel(new ImageIcon(ScaledImage.create("resources/sprites/menu/loading_screen/loading.gif",150,150)));
//        gif.setBounds(300,300,200,200);
//        gif.setOpaque(false);
//        add(gif);
    }

    private void addWinText(){
        JLabel text = new JLabel("WIN");
        text.setFont(new Font(fontName,0,80));
        text.setForeground(Color.WHITE);
        text.setBounds(300,75,800,500);
        text.setOpaque(false);
        add(text);
    }

    private void addDefeatText(){
        JLabel text = new JLabel("DEFEAT");
        text.setFont(new Font(fontName,0,80));
        text.setForeground(Color.WHITE);
        text.setBounds(175,75,800,500);
        text.setOpaque(false);
        add(text);
    }
}
