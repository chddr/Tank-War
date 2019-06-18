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
        addLoadText(level);

    }

    private void addLoadText(int level){
        JLabel text = new JLabel("Stage " + level);
        text.setFont(new Font(fontName,0,80));
        text.setForeground(Color.WHITE);
        text.setBounds(125,75,800,500);
        text.setOpaque(false);
        add(text);

//        JLabel gif = new JLabel(new ImageIcon(ScaledImage.create("resources/sprites/menu/loading_screen/loading.gif",100,100)));
//        gif.setBounds(300,400,200,200);
//        gif.setOpaque(false);
//        add(gif);
    }

}
