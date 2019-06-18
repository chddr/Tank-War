package game_content;

import javafx.scene.media.AudioClip;
import map_tools.Level;
import resources_classes.GameSound;
import resources_classes.ScaledImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game_content.GameWindow.*;

public class MenuPanel extends JPanel {

    //Music
    private AudioClip music;
    //Level chooser
    private JComboBox levelsBox;
    //Background gif
    private JLabel labelBackground;
    //Music boolean
    private boolean musicStop;
    //Parent component
    private GameWindow gameWindow;
    public MenuPanel(GameWindow gameWindow){

        this.gameWindow = gameWindow;
        setBounds(0,0,windowWidth,windowHeight);
        setLayout(null);
        addText();
        addPlayButton();
        addLevelsComboBox();
        addBackground();
        playMusic();
        checkMusicPlaying();

    }

    /**
     * Creates background gif
     */
    private void addBackground(){

        Image backgroundImage = ScaledImage.create("resources/sprites/menu/background2.gif",windowWidth,windowHeight-30);
        labelBackground = new JLabel(new ImageIcon(backgroundImage));
        labelBackground.setBounds(0, 0, windowWidth, windowHeight - 30);
        add(labelBackground);

    }

    /**
     * Play music
     */
    private void playMusic() {
        music = GameSound.nextMenuMusic();
        music.play();

    }

    /**
     * Controls music playing endless
     */
    private void checkMusicPlaying(){
        if(!musicStop){
            Timer timer = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!music.isPlaying() && !musicStop){
                        music = GameSound.nextMenuMusic();
                        music.play();
                    }
                    checkMusicPlaying();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    /**
     * Creates JLabel with text "Tank War"
     */
    private void addText(){
        JLabel gameName = new JLabel("<html><div style='text-align: center;'>Tank<br>War</div></html>");
        gameName.setFont(new Font(fontName,1,100));
        gameName.setForeground(new Color(172,17,21));
        gameName.setBounds(200,-100,600,500);
        add(gameName);
    }

    /**
     * Creates play button, which starts the game
     */
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
            musicStop=true;
            music.stop();
            Level level = (Level)levelsBox.getSelectedItem();
            LoadScreenPanel loadScreenPanel = new LoadScreenPanel(level.ordinal()+1);

            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameWindow.remove(loadScreenPanel);
                    GameFieldPanel gameFieldPanel = new GameFieldPanel(gameWindow,level);
                    gameWindow.add(gameFieldPanel);
                    gameWindow.repaint();
                    gameFieldPanel.requestFocusField();
                }
            });
            timer.setRepeats(false);
            timer.start();
            gameWindow.add(loadScreenPanel);
            gameWindow.repaint();
        });
        add(playButton);
    }

    /**
     * Creates JComboBox which contains levels
     */
    private void addLevelsComboBox(){
        levelsBox = new JComboBox();
        levelsBox.setRenderer(new CustomComboBoxCellRenderer());
        levelsBox.setFont(new Font(fontName,0,37));
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

    //Help class
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
                    return new Dimension(300, 60);
                }
            };
            label.setText(String.valueOf(value));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            label.setFont(new Font(fontName,0,37));
            label.setForeground(Color.BLACK);

            return label;
        }
    }


}