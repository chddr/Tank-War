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

public class GameFieldPanel extends JPanel {

    private AudioClip music = GameSound.getBattleMusicInstance();
    private GameWindow gameWindow;
    private GameField gameField;
    private JLabel numberOfRespawns;
    private JLabel numberEnemyTanksLabel;
    private int enemyTanksDestroyed;
    private Level level;
    private boolean musicMute;
    private boolean musicStop;
    private Image mutedImage = ScaledImage.create("resources/sprites/menu/buttons_icon/mute_button.png",50,50);
    private Image unmutedImage = ScaledImage.create("resources/sprites/menu/buttons_icon/unmute_button.png",50,50);

    //Level.values()[Level.Two.ordinal()+1]
    public GameFieldPanel(GameWindow gameWindow, Level level){
        this.gameWindow = gameWindow;
        this.level = level;
        setBounds(0,0,windowWidth,windowHeight);
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        addGameField();
        addMuteButton();
        addExitToMenuButton();
        addLifeIcon();
        addFlagIconAndInfo();
        addEnemyTankLabelAndText();
        checkMusicPlaying();

    }

    private void checkMusicPlaying(){
        if(!musicStop){
            Timer timer = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!music.isPlaying() && !musicMute && !musicStop){
                        music = GameSound.getBattleMusicInstance();
                        music.play();
                    }
                    checkMusicPlaying();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void addEnemyTankLabelAndText(){
        Image heart = ScaledImage.create("resources/sprites/menu/enemy_tank_icon.png",75,75);
        JLabel label = new JLabel(new ImageIcon(heart));
        label.setBounds(720, 0, 75, 75);
        add(label);

        numberEnemyTanksLabel = new JLabel(20-enemyTanksDestroyed+"x");
        numberEnemyTanksLabel.setFont(new Font(fontName,0,30));
        numberEnemyTanksLabel.setForeground(Color.WHITE);
        numberEnemyTanksLabel.setBounds(625, 0, 100, 100);
        add(numberEnemyTanksLabel);
    }



    private void addLifeIcon(){
        Image heart = ScaledImage.create("resources/sprites/menu/heart_icon.png",75,75);
        JLabel label = new JLabel(new ImageIcon(heart));
        label.setBounds(720, 200, 75, 75);
        add(label);

        numberOfRespawns = new JLabel(gameWindow.getRespawns()+"x");
        numberOfRespawns.setFont(new Font(fontName,0,40));
        numberOfRespawns.setForeground(Color.WHITE);
        numberOfRespawns.setBounds(635, 200, 100, 100);
        add(numberOfRespawns);
    }

    private void addFlagIconAndInfo(){
        Image flagImage = ScaledImage.create("resources/sprites/menu/flag_icon.png",100,100);
        JLabel flag = new JLabel(new ImageIcon(flagImage));
        flag.setBounds(710, 380, 100, 100);
        add(flag);
        JLabel number = new JLabel(level.ordinal()+1+"x");
        number.setFont(new Font(fontName,0,40));
        number.setForeground(Color.WHITE);
        number.setBounds(635, 400, 100, 100);
        add(number);
    }


    private void addGameField(){
        gameField = new GameField(level, this);
        gameField.setBounds(0,0,624,624);
        add(gameField);
        music.play();
    }

    private void addMuteButton(){

        JButton muteButton = new JButton(new ImageIcon(unmutedImage));
        muteButton.setBounds(640,540,50,50);
        muteButton.setBackground(Color.DARK_GRAY);
        muteButton.setBorderPainted(false);
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (musicMute){
                    muteButton.setIcon(new ImageIcon(unmutedImage));
                    music.play();
                    musicMute = false;
                    requestFocusField();
                } else {
                    muteButton.setIcon(new ImageIcon(mutedImage));
                    //Jazz music stops.jpg
                    music.stop();
                    musicMute=true;
                    requestFocusField();
                }
            }
        });
        add(muteButton);
    }

    private void addExitToMenuButton(){
        JButton exitButton = new JButton(new ImageIcon(ScaledImage.create("resources/sprites/menu/buttons_icon/exit_button.png",50,50)));
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setBorderPainted(false);
        exitButton.setBounds(730,540,50,50);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                musicStop=true;
//                music.stop();
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
        musicStop = true;
        music.stop();
        if (level.ordinal()+1==Level.values().length){
            gameWon();
            return;
        }

        gameWindow.remove(this);
        LoadScreenPanel loadScreenPanel = new LoadScreenPanel(level.ordinal()+2);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.remove(loadScreenPanel);
                GameFieldPanel gameFieldPanel = new GameFieldPanel(gameWindow, Level.values()[level.ordinal()+1]);
                gameWindow.add(gameFieldPanel);
                gameWindow.repaint();
                gameFieldPanel.requestFocusField();
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
    private void gameWon(){
        gameEnd(0);
    }

    private void gameEnd(int gameResult){
        gameWindow.remove(this);
        musicStop=true;
        music.stop();
        LoadScreenPanel loadScreenPanel = new LoadScreenPanel(gameResult);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.remove(loadScreenPanel);
                gameWindow.add(new MenuPanel(gameWindow));
                gameWindow.setRespawns(3);
                gameWindow.repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();
        gameWindow.add(loadScreenPanel);
        gameWindow.repaint();

    }

    public void playerTankDestroyed(){
        gameWindow.playerTankDestroyed();
        int respawns = gameWindow.getRespawns();
        if(respawns!=-1){
            numberOfRespawns.setText(respawns+"x");
        }
//        else {
//            Timer timer = new Timer(3000, new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    gameLost();
//                }
//            });
//            timer.setRepeats(false);
//            timer.start();
//        }

    }

    public void enemyTankDestroyed(){
        enemyTanksDestroyed++;
        numberEnemyTanksLabel.setText(20-enemyTanksDestroyed+"x");
        if (enemyTanksDestroyed==20){
            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    roundWon();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public int getRespawns(){
        return gameWindow.getRespawns();
    }


}