import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class MyJPanelPendulumWorks extends JPanel implements Runnable, MouseMotionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int lengthOfArmOfPEndulum = 400;
	static int anchorX = 1200 / 2;
    static int anchorY = 800 / 5;
	static int length;
	static int ballX;
	static int ballY;
	static int xChange = 0;
	static int yChange = 0;
	static int[] LeapX = new int [728];
	static int[] LeapY = new int [728];
	static double angle = 1.20;
	static double[] anglePoints = new double [728];
	static boolean flag = true;

	public MyJPanelPendulumWorks() {
		setLayout(null);
		addMouseMotionListener(this); // adding Mouse Listener
		addMouseListener(this);
		length = lengthOfArmOfPEndulum;
	    setDoubleBuffered(true);
	    PendulumWorksHelp.draw();
	    JSlider sliderVertical = new JSlider(JSlider.VERTICAL, 300, 500, 400);
		sliderVertical.setBounds(1130, 100, 30, 500);
		add(sliderVertical);
		
		sliderVertical.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				lengthOfArmOfPEndulum = sliderVertical.getValue();
				length = lengthOfArmOfPEndulum;
				PendulumWorksHelp.draw();
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
	    ballX = anchorX + (int) (Math.sin(angle) * length);
	    ballY = anchorY + (int) (Math.cos(angle) * length);
	    g.fillRect(0, 0, 1200, 20);
	    g.fillRect(anchorX-30, anchorY-140, 60, 140);
	    g.drawLine(anchorX, anchorY, ballX + xChange, ballY + yChange);
	    g.fillOval(anchorX - 4, anchorY - 4, 8, 8);
	    g.fillOval(ballX - 25 , ballY - 25, 50, 50);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1200, 800);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		flag = false;
		int xMouse = e.getX();
			for (int i = 0; i< 728; i++)
				if (xMouse - 600 == LeapX[i]) {
					ballX = LeapX[i]+ anchorX; 
					ballY = LeapY[i]+ anchorY;
					angle = anglePoints[i];
				}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		double angleAccel, angleVelocity = 0, dt = 0.1;
	    while (flag) {
	      angleAccel = -9.81 / length * Math.sin(angle);
	      angleVelocity += angleAccel * dt;
	      angleVelocity *=0.999;
	      angle += angleVelocity * dt;
	      repaint();
	      try {
	        Thread.sleep(5);
	      } catch (InterruptedException ex) {
	      }
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		flag = true;
		repaint ();
		new Thread(this).start();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}

public class PendulumWorks {
	
	public static void main(String[] args) {
		MyJPanelPendulumWorks myp = new MyJPanelPendulumWorks();
		JFrame window = new JFrame();
		window.setVisible(true);
		window.setTitle("My window for PendulumWorks simulation");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(myp);
		window.pack();
		new Thread(myp).start();
	}
}
