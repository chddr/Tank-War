import java.awt.EventQueue;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

	public GameWindow() {

		initUI();
	}

	/**
	 * This method initialises the UI of the app.
	 */
	private void initUI() {

		add(new GameField());

		setResizable(false);
		pack();

		setTitle("Tank War");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			JFrame ex = new GameWindow();
			ex.setVisible(true);
		});
	}
}