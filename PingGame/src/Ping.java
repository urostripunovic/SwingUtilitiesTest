import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Ping {
	private static final int WIDTH = 300, HEIGHT = 200, BALL_SIZE = 10;

	private static void animate(JLabel ball) {
		try {
			int x = WIDTH / 2, y = HEIGHT / 2;
			int dx = 5, dy = 5;
			//ball.setBounds(WIDTH / 2, HEIGHT / 2, BALL_SIZE, BALL_SIZE);
			SwingUtilities.invokeLater(() -> ball.setBounds(WIDTH / 2, HEIGHT / 2, BALL_SIZE, BALL_SIZE));
			while (true) {
				if (x + dx + BALL_SIZE >= WIDTH || x + dx < 0) {
					dx = -dx;
				}
				if (y + dy + BALL_SIZE >= HEIGHT || y + dy < 0) {
					dy = -dy;
				}
				int newx = x + dx;
				int newy = y + dy;
				//ball.setLocation(newx, newy);
				SwingUtilities.invokeLater(() -> ball.setLocation(newx, newy));
				Thread.sleep(25);
				x = newx;
				y = newy;
			}
		} catch (InterruptedException e) {
			throw new Error(e);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Ping");
			JPanel canvas = new JPanel(null);
			canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			JLabel ball = new JLabel("O");
			canvas.add(ball);
			frame.add(canvas);
			frame.pack();
			frame.setVisible(true);

			Thread t = new Thread(() -> animate(ball));
			t.start();  
		});

	}
}