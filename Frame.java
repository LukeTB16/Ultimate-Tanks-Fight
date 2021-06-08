package game.UTF;
import java.awt.Color;

import javax.swing.JFrame;

public class Frame extends JFrame{
	Panel panel;
	public Frame(){  // costruttore
		panel = new Panel();
		this.add(panel);
		this.setTitle("Ultimate Tanks Fight");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
