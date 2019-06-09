package game_content;

import java.awt.*;
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

		setLayout(null);
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

		private Image backgroundImage;

		public MenuPanel(){
			setBounds(0,0,windowWidth,windowHeight);
			setLayout(null);

			backgroundImage = Toolkit.getDefaultToolkit().createImage("resources/sprites/menu/background2.gif");
			addText();
		}


		private void addText(){
			JLabel gameName = new JLabel("<html><div style='text-align: center;'>Tank<br>War</div></html>");
			gameName.setFont(new Font(fontName,1,70));
			gameName.setForeground(Color.RED);
			gameName.setBackground(Color.RED);
			gameName.setBounds(250,100,300,300);
			System.out.println(1);
			add(gameName);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if (backgroundImage!=null){
				g.drawImage(backgroundImage, 0, 0, windowWidth, windowHeight - 30, this);
			}
		}
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			JFrame ex = new GameWindow();
			ex.setVisible(true);
		});
	}
}