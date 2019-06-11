package game_content;

import javafx.scene.media.AudioClip;
import map_tools.Level;
import resources_classes.GameSound;
import resources_classes.ScaledImage;

import javax.swing.*;
import java.awt.*;

import static game_content.GameWindow.*;

public class MenuPanel extends JPanel {

    private AudioClip music;
    private JComboBox levelsBox;
    private JLabel labelBackground;
    private GameWindow gameWindow;
    public MenuPanel(GameWindow gameWindow){
        this.gameWindow = gameWindow;

        setBounds(0,0,windowWidth,windowHeight);
        setLayout(null);
        addText();
        addPlayButton();
        addLevelsComboBoc();
        addBackground();
        playMusic();

    }

    private void addBackground(){

        Image backgroundImage = ScaledImage.create("resources/sprites/menu/background2.gif",windowWidth,windowHeight-30);
        labelBackground = new JLabel(new ImageIcon(backgroundImage));
        labelBackground.setBounds(0, 0, windowWidth, windowHeight - 30);
        add(labelBackground);

    }

    private void playMusic(){
        music = GameSound.getMenuMusicInstance();
        music.play();
    }


    private void addText(){
        JLabel gameName = new JLabel("<html><div style='text-align: center;'>Tank<br>War</div></html>");
        gameName.setFont(new Font(fontName,1,100));
        gameName.setForeground(new Color(172,17,21));
        gameName.setBounds(200,-100,600,500);
        add(gameName);
    }

    private void addPlayButton(){
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font(fontName,1,50));
        playButton.setForeground(Color.BLACK);
        playButton.setBackground(new Color(172,17,21));
        playButton.setBounds(250,400,300,80);
        playButton.setBorderPainted(false);
        playButton.setVerticalAlignment(SwingConstants.BOTTOM);
        playButton.setFocusPainted(false);
        playButton.addActionListener(e -> {
            gameWindow.remove(MenuPanel.this);
            music.stop();
            GameFieldPanel gameFieldPanel = new GameFieldPanel(gameWindow,(Level) levelsBox.getSelectedItem());
            gameWindow.add(gameFieldPanel);
            gameWindow.repaint();
            gameFieldPanel.requestFocusField();
        });
        add(playButton);
    }

    private void addLevelsComboBoc(){
        levelsBox = new JComboBox();
        levelsBox.setRenderer(new CustomComboBoxCellRenderer());
        levelsBox.setFont(new Font(fontName,0,50));
        levelsBox.setForeground(Color.BLACK);
        levelsBox.setBackground(new Color(172,17,21));
        levelsBox.setBounds(250,500,300,80);
        levelsBox.setToolTipText("Choose desired level");
        levelsBox.setMaximumRowCount(2);
        for (Level level : Level.values()) {
            levelsBox.addItem(level);
        }
        add(levelsBox);
    }

    class CustomComboBoxCellRenderer extends JLabel implements ListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

            JLabel label = new JLabel(){
                public Dimension getPreferredSize(){
                    return new Dimension(300, 80);
                }
            };
            label.setText(String.valueOf(value));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            label.setFont(new Font(fontName,0,50));
            label.setForeground(Color.BLACK);

            return label;
        }
    }


}