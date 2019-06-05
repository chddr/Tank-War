import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameField extends JPanel implements Runnable, ActionListener {

	/**
	 * Dimensions of a game field (height and width are always equal)
	 */
	private final int FIELD_DIMENSIONS = 256;
	/**
	 * Delay in miliseconds
	 */
	private final int DELAY = 25;

	private Thread animator;


	public GameField() {
		initGameField();
	}

	/**
	 * Initialization method
	 */
	private void initGameField() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(FIELD_DIMENSIONS, FIELD_DIMENSIONS));


	}

	/**
	 * Overriden method. Used to start the Thread when it's added to the JFrame
	 */
	@Override
	public void addNotify() {
		super.addNotify();

		animator = new Thread(this);
		animator.start();
	}

	private void cycle() {
		//TODO

		//Synchronizing drawing because of buffering
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	@Override
	public void run() {

		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (true) {

			cycle();
			repaint();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep < 0) {
				sleep = 2;
			}

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {

				String msg = String.format("Thread interrupted: %s", e.getMessage());

				JOptionPane.showMessageDialog(this, msg, "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			beforeTime = System.currentTimeMillis();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
