package game_content;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class GameWindow extends JFrame {

	//Window size
	public static final int windowWidth = 800;
	public static final int windowHeight = 660;
	//Font name
	public static final String fontName = "cootuecursessquare16x16";
	//Number of respawns
	private int respawns = 3;

	public GameWindow() {
		initUI();
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
		MenuPanel menuPanel = new MenuPanel(this);
		add(menuPanel);

		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * Minus one respawn
	 */
	public void playerTankDestroyed(){
		respawns--;
	}

	/**
	 * Plus one respawn
	 */
	public void playerRespawnGained(){
		respawns++;
	}

	public int getRespawns(){
		return respawns;
	}

	public void setRespawns(int respawns){
		this.respawns=respawns;
	}


	/**
	 * Add font to windows environment
	 */
	private void createFont(){
		try{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/mainFont.ttf")));
		} catch (Exception e){
			System.out.println("font adding failed");
		}

	}

	/**
	 * Set window icon
	 */
	private void setWindowIcon(){
		Image iconImage =
				Toolkit.getDefaultToolkit().createImage("resources/sprites/window/icon.png");
		setIconImage(iconImage);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			JFrame ex = new GameWindow();
			ex.setVisible(true);
		});
	}
}