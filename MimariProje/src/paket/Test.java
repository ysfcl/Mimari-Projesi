package paket;

import javax.swing.SwingUtilities;

public class Test {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new HammingSimulatorGUI());
	}
}
