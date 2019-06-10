package game_content;


import javafx.scene.media.AudioClip;
import map_tools.Level;
import resources_classes.GameSound;
import resources_classes.ScaledImage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class GameWindow extends JFrame {

	private final int windowWidth = 800;
	private final int windowHeight = 660;
	private final String fontName = "cootuecursessquare16x16";
	private AudioClip music;

	public GameWindow() {

		initUI();
		playMusic();
	}

	private void playMusic(){
		music = GameSound.getMenuMusicInstance();
		music.play();
	}

	/**
	 * This method initialises the UI of the app.
	 */
	private void initUI() {

		setTitle("Tank War");
		setWindowIcon();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setSize(windowWidth,windowHeight);

		createFont();
		MenuPanel menuPanel = new MenuPanel();
		add(menuPanel);

		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void createFont(){
		try{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/mainFont.ttf")));
		} catch (Exception e){
			System.out.println("font adding failed");
		}

	}

	private void setWindowIcon(){
		Image iconImage =
				Toolkit.getDefaultToolkit().createImage("resources/sprites/window/icon.png");
		setIconImage(iconImage);
	}

	class MenuPanel extends JPanel{

		private JComboBox levelsBox;
		private JLabel labelBackground;
		public MenuPanel(){
			setBounds(0,0,windowWidth,windowHeight);
			setLayout(null);
			addText();
			addPlayButton();
			addLevelsComboBoc();
			addBackground();

		}

		private void addBackground(){

			Image backgroundImage = ScaledImage.create("resources/sprites/menu/background2.gif",windowWidth,windowHeight-30);
			labelBackground = new JLabel(new ImageIcon(backgroundImage));
			labelBackground.setBounds(0, 0, windowWidth, windowHeight - 30);
			add(labelBackground);

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
				GameWindow.this.remove(MenuPanel.this);
				music.stop();
				GameFieldPanel gameFieldPanel = new GameFieldPanel((Level) levelsBox.getSelectedItem());
				GameWindow.this.add(gameFieldPanel);
				GameWindow.this.repaint();
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


	class GameFieldPanel extends JPanel{

		private GameField gameField;
		private Level level;
		private boolean mutedBoolean;
		private Image mutedImage = ScaledImage.create("resources/sprites/menu/buttons_icon/mute_button.png",50,50);
		private Image unmutedImage = ScaledImage.create("resources/sprites/menu/buttons_icon/unmute_button.png",50,50);

		//Level.values()[Level.Two.ordinal()+1]
		public GameFieldPanel(Level level){
			setBounds(0,0,windowWidth,windowHeight);
			setLayout(null);
			this.level = level;
			addGameField();
			addMuteButton();
		}

		private void addGameField(){
			gameField = new GameField(level, this);
			gameField.setBounds(0,0,624,624);
			add(gameField);
			gameField.musicPlay();
		}

		private void addMuteButton(){

			JButton muteButton = new JButton(new ImageIcon(unmutedImage));
			muteButton.setBounds(700,500,50,50);
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

		public void requestFocusField(){
			gameField.requestFocus();
		}

		public void gameWon(){

		}

		public void gameLost(){

		}

		public void tankHpLost(){

		}



	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			JFrame ex = new GameWindow();
			ex.setVisible(true);
		});
	}
}