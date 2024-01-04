package Clock;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JFrame;

public class Program {

	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(new JClock(150));
            frame.pack();
            
            Timer timer = new Timer(1000, (ActionListener) new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.repaint();
                }
            });

            // Start the timer
            timer.start();
            
        });
	}

	
	
}
