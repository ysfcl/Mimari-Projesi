package paket;

import javax.swing.*;
import java.awt.*;

class BitPanel extends JPanel{

	private JLabel[] bitLabels;

    public BitPanel() {
        setLayout(new FlowLayout());
    }

    public void showBits(String bits, int errorPos, int correctedPos) {
        
    	removeAll();
        bitLabels = new JLabel[bits.length()];

        for (int i = 0; i < bits.length(); i++) {
            
        	bitLabels[i] = new JLabel(String.valueOf(bits.charAt(i)));
            bitLabels[i].setOpaque(true);
            bitLabels[i].setFont(new Font("Monospaced", Font.BOLD, 16));
            bitLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            bitLabels[i].setPreferredSize(new Dimension(30, 30));

            
            if (i + 1 == errorPos) {
                bitLabels[i].setBackground(Color.RED);
            } 
            
            else if (i + 1 == correctedPos) {
                bitLabels[i].setBackground(Color.GREEN);
            }
            
            else {
                bitLabels[i].setBackground(Color.WHITE);
            }

            add(bitLabels[i]);
        }

        revalidate();
        repaint();
    
    }
	
	
}
