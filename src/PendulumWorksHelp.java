
public class PendulumWorksHelp {
	public static void draw() {
		int j=0;
		for (double i = 1.20; i >= -1.20; i-= 0.0033) {
			MyJPanelPendulumWorks.LeapX[j] = (int) (Math.sin(i) * MyJPanelPendulumWorks.length);
			MyJPanelPendulumWorks.LeapY[j] = (int) (Math.cos(i) * MyJPanelPendulumWorks.length);
			MyJPanelPendulumWorks.anglePoints[j] = i;
			j++;
		}
	}
}
