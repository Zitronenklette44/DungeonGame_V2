package rendering;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Main;
import system.KeyHandler;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;

public class Screen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Point pos;
	public static Dimension size;
	private static Screen frame;

	public static void erstellen() {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				// set Variable for Size and Position
				pos = new Point(0, 0);
				GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
				size = new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
				Main.gvStorage.updateScreenDetails();
				
				try { // creating frame
					frame = new Screen();
					frame.addKeyListener(new KeyHandler());
					frame.setLocation(pos);
					frame.setVisible(true);
					Main.gvStorage.player.pos = Main.gvStorage.centerPos;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void HideScreen() {
		frame.setVisible(false);
	}

	public static void ShowScreen() {
		frame.setVisible(true);
	}

	public static void Repaint() {
		if(frame != null)
			frame.repaint();
	}

	/**
	 * Create the frame.
	 */
	public Screen() {
		setMinimumSize(new Dimension(800, 700));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				try {
					Main.gvStorage.updateScreenDetails();
					size = new Dimension(frame.getWidth(), frame.getHeight());
				} catch (NullPointerException e2) {
					// TODO: handle exception
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(pos.x, pos.y, size.width, size.height);
		contentPane = new JPanel();
		contentPane.setLayout(null);

		CurrentSzene currentSzene = new CurrentSzene(size);
		currentSzene.setBackground(Color.BLACK);
		currentSzene.setFocusable(false);
		currentSzene.setFocusTraversalKeysEnabled(false);
		currentSzene.setBounds(10, 10, 105, 60);
		currentSzene.setVisible(true);
		currentSzene.setBounds(0, 0, size.width, size.height);
		contentPane.add(currentSzene);

		setContentPane(contentPane);
	}

}
