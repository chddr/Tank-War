package game_content;

import map_tools.Level;
import resources_classes.ScaledImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game_content.GameWindow.windowHeight;
import static game_content.GameWindow.windowWidth;

public class GameFieldPanel extends JPanel {

    private GameWindow gameWindow;
    private GameField gameField;
    private Level level;
    private boolean mutedBoolean;
    private Image mutedImage = ScaledImage.create("resources/sprites/menu/buttons_icon/mute_button.png",50,50);
    private Image unmutedImage = ScaledImage.create("resources/sprites/menu/buttons_icon/unmute_button.png",50,50);

    //Level.values()[Level.Two.ordinal()+1]
    public GameFieldPanel(GameWindow gameWindow, Level level){
        this.gameWindow = gameWindow;
        setBounds(0,0,windowWidth,windowHeight);
        setLayout(null);
        this.level = level;
        addGameField();
        addMuteButton();
        addExitToMenuButton();
    }

    private void addGameField(){
        gameField = new GameField(level, this);
        gameField.setBounds(0,0,624,624);
        add(gameField);
        gameField.musicPlay();
    }

    private void addMuteButton(){

        JButton muteButton = new JButton(new ImageIcon(unmutedImage));
        muteButton.setBounds(640,540,50,50);
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mutedBoolean){
                    muteButton.setIcon(new ImageIcon(unmutedImage));
                    gameField.musicPlay();
                    mutedBoolean = false;
                    requestFocusField();
                } else {
                    muteButton.setIcon(new ImageIcon(mutedImage));
                    //Jazz music stops.jpg
                    gameField.musicStop();
                    mutedBoolean=true;
                    requestFocusField();
                }
            }
        });
        add(muteButton);
    }

    private void addExitToMenuButton(){
        JButton exitButton = new JButton(new ImageIcon(ScaledImage.create("resources/sprites/menu/buttons_icon/exit_button.png",50,50)));
        exitButton.setBounds(730,540,50,50);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                gameField.musicStop();
//                gameWindow.remove(GameFieldPanel.this);
//                gameWindow.add(new MenuPanel(gameWindow));
//                gameWindow.repaint();
                roundWon();
            }
        });
        add(exitButton);
    }

    public void requestFocusField(){
        gameField.requestFocus();
    }

    public void roundWon(){
        if (level.ordinal()+1==Level.values().length){
            gameWon();
            return;
        }

        gameWindow.remove(this);
        gameField.musicStop();
        LoadScreenPanel loadScreenPanel = new LoadScreenPanel(level.ordinal()+2);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.remove(loadScreenPanel);
                gameWindow.add(new GameFieldPanel(gameWindow, Level.values()[level.ordinal()+1]));
                gameWindow.repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();
        gameWindow.add(loadScreenPanel);
        gameWindow.repaint();
    }

    //LoadScreen parameter -1 means defeat screen
    public void gameLost(){
        gameEnd(-1);
    }

    //LoadScreen parameter 0 means win screen
    public void gameWon(){
        gameEnd(0);
    }

    private void gameEnd(int gameResult){
        gameWindow.remove(this);
        gameField.musicStop();
        LoadScreenPanel loadScreenPanel = new LoadScreenPanel(gameResult);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.remove(loadScreenPanel);
                gameWindow.add(new MenuPanel(gameWindow));
                gameWindow.repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();
        gameWindow.add(loadScreenPanel);
        gameWindow.repaint();

    }

    public void tankHpLost(){

    }



}