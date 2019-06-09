package game_content;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameWindow extends JFrame {

	private final int windowWidth = 800;
	private final int windowHeight = 700;
	private String fontName = "cootuecursessquare16x16";

	public GameWindow() {

		initUI();
	}

	/**
	 * This method initialises the UI of the app.
	 */
	private void initUI() {

//		setLayout(null);
		setSize(windowWidth,windowHeight);
		createFont();

		MenuPanel menuPanel = new MenuPanel();
		add(menuPanel);


//		menuPanel.setVisible(false);

		GameField field = new GameField();

		setResizable(false);
//		add(field);
//		pack();

		setTitle("Tank War");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void createFont(){
		try{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/mainFont.ttf")));
		} catch (Exception e){
			System.out.println("font adding failed");
		}

	}

	class MenuPanel extends JPanel{

//		private Image backgroundImage;

		public MenuPanel(){
			setBounds(0,0,windowWidth,windowHeight);
			setLayout(null);

			addText();
			addPlayButton();
			addBackground();

		}

		private void addBackground(){
			Image backgroundImage =
					Toolkit.getDefaultToolkit().createImage("resources/sprites/menu/background2.gif");
			Image scaledImage = backgroundImage.getScaledInstance(windowWidth,windowHeight-30,Image.SCALE_DEFAULT);
			JLabel label = new JLabel(new ImageIcon(scaledImage));
			label.setBounds(0, 0, windowWidth, windowHeight - 30);
			add(label);

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
			playButton.setFont(new Font(fontName,1,60));
			playButton.setForeground(Color.BLACK);
			playButton.setBackground(new Color(172,17,21));
			playButton.setBounds(250,500,300,80);
			playButton.setBorderPainted(false);
			playButton.setFocusPainted(false);
			playButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GameWindow.this.remove(MenuPanel.this);
					GameField gameField = new GameField();
					gameField.setBounds(10,10,650,650);
					GameWindow.this.add(gameField);
					GameWindow.this.repaint();
					gameField.requestFocus();
				}
			});
			add(playButton);
		}


//		class BackgroundGifPanel extends JPanel{
//
//			private Image backgroundImage =
//					Toolkit.getDefaultToolkit().createImage("C:\\Users\\Ein Erste\\Desktop\\Пикчи\\Лучше обои\\270916.png");
////					Toolkit.getDefaultToolkit().createImage("resources/sprites/menu/background2.gif");
//			private int counter;
//
//
//			@Override
//			public void paint(Graphics g) {
////				counter++;
////				if (counter>5050)
////					return;
////				super.paint(g);
//				if (backgroundImage!=null){
//					g.drawImage(backgroundImage, 0, 0, windowWidth, windowHeight - 30, this);
//					System.out.println(1);
//
//				}
//			}
//		}
	}

	public static void main(String[] args) {
//		JFrame ex = new GameWindow();
//		ex.setVisible(true);
		EventQueue.invokeLater(() -> {
			JFrame ex = new GameWindow();
			ex.setVisible(true);
		});
	}
}