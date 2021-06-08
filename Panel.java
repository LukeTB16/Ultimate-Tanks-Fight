package game.UTF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Panel extends JPanel implements ActionListener{  
	
	private static final long serialVersionUID = 1L;
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int TANK_WIDTH = 50;
	static final int TANK_HEIGHT = 50;
	static final Dimension SCREEN_SIZE = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
	static final int DELAY = 75;
	Tank t1;
	Tank t2;
	ImageIcon a;
	Image back_img;
	Graphics graphics;
	Bullet b;
	Score score;
	String dir;
	int count = 0;
	Timer time;
	Thread animator;
	
	
	public Panel(){
		newTanks();
		score = new Score(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new Listener());  // richiamo la inner class
		this.setPreferredSize(SCREEN_SIZE);
		a = new ImageIcon("images/background.jpg");
		back_img = a.getImage();
		time = new Timer(5, this);
		time.start();

	}  
	// Funzione principale per la stampa degli oggetti nella finestra
	public void paint(Graphics g) { 
		animator = new Thread();
		animator.start();
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(back_img, 0, 0, null);
		t1.paint1(g2d);
		t2.paint2(g2d);
		t1.checkLife();
		t2.checkLife();
		//System.out.println(t1.life);
		if(t1.tank_died == true && t2.tank_died == false) {
			t1.drawExplosion(g2d);
			System.out.println("Player 2 WIN !");
		}
		else if(t2.tank_died == true && t1.tank_died == false) {
			t2.drawExplosion(g2d);
			System.out.println("Player 1 WIN !");
		}
		else if(t1.tank_died == true && t2.tank_died == true) {
			t1.drawExplosion(g2d);
			t2.drawExplosion(g2d);
			System.out.println("PAREGGIO !");
		}
		
		// Gestione del disegno dei proiettili per il primo tank
		ArrayList<Bullet> bullets1 = Tank.getBullets();
		for(int i = 0; i < bullets1.size(); i++) {
			Bullet b1 = (Bullet) bullets1.get(i);
			if(t1.getDir() == "left" && b1.getDir() == "left") {
				g2d.drawImage(b1.getImg_b_left(), b1.getX(), b1.getY(), null);
			}
			else if(t1.getDir() == "up" && b1.getDir() == "up") {
				g2d.drawImage(b1.getImg_b_up(), b1.getX(), b1.getY(), null);
			}
			else if(t1.getDir() == "down" && b1.getDir() == "down") {
				g2d.drawImage(b1.getImg_b_down(),b1.getX(), b1.getY(), null);
			}
			else if(t1.getDir() == "right" && b1.getDir() == "right") {
				g2d.drawImage(b1.getImg_b_right(), b1.getX(), b1.getY(), null);
			}
		}
		// Gestione del disegno dei proiettili per il secondo tank
		ArrayList<Bullet> bullets2 = Tank.getBullets();
		for(int w = 0; w < bullets2.size(); w++) {
			Bullet b2 = (Bullet) bullets2.get(w);
			if(t2.getDir() == "l" && b2.getDir() == "l") {
				g2d.drawImage(b2.getImg_b_left(), b2.getX(), b2.getY(), null);
			}
			else if(t2.getDir() == "u" && b2.getDir() == "u") {
				g2d.drawImage(b2.getImg_b_up(), b2.getX(), b2.getY(), null);
			}
			else if(t2.getDir() == "d" && b2.getDir() == "d") {
				g2d.drawImage(b2.getImg_b_down(),b2.getX(), b2.getY(), null);
			}
			else if(t2.getDir() == "r" && b2.getDir() == "r") {
				g2d.drawImage(b2.getImg_b_right(), b2.getX(), b2.getY(), null);
			}
		}
		 
		
		
	}
		
	// Creazione di nuovi tank
	public void newTanks() {
		t1 = new Tank(1, 280, 500, TANK_WIDTH, TANK_HEIGHT, 50, 20,  Color.green, dir, 100);
		t2 = new Tank(2, 280, 50, TANK_WIDTH, TANK_HEIGHT, 50, 20, Color.red, dir, 50);
	}
	
	// Assegnazione dei tasti ai movimenti dei due tank usando la classe apposita
	public class Listener extends KeyAdapter{  // inner class
		@Override
		public void keyPressed(KeyEvent e) {
			
			// Movimento del tank
			if(t1.tank_died == false) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					t1.setTank_y(t1.getTank_y()-t1.speed);
					t1.setDir("up");
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					t1.setTank_y(t1.getTank_y()+t1.speed);
					t1.setDir("down");
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					t1.setTank_x(t1.getTank_x()-t1.speed);
					t1.setDir("left");
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					t1.setTank_x(t1.getTank_x()+t1.speed);
					t1.setDir("right");
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					t1.fire(t1);
				}
			}
			if(t2.tank_died == false) {
				if(e.getKeyCode() == KeyEvent.VK_W) {
					t2.setTank_y(t2.getTank_y()-t2.speed);
					t2.setDir("u");
				}
				if(e.getKeyCode() == KeyEvent.VK_S) {
					t2.setTank_y(t2.getTank_y()+t2.speed);
					t2.setDir("d");
				}
				if(e.getKeyCode() == KeyEvent.VK_A) {
					t2.setTank_x(t2.getTank_x()-t2.speed);
					t2.setDir("l");
				}
				if(e.getKeyCode() == KeyEvent.VK_D) {
					t2.setTank_x(t2.getTank_x()+t2.speed);
					t2.setDir("r");
				}
				if(e.getKeyCode() == KeyEvent.VK_F) {
					t2.fire(t2);
				}
			}
			
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		t1.checkCollision();
		t2.checkCollision();
		t1.checkTankCollision(t2);
		t2.checkTankCollision(t1);
		ArrayList<Bullet> bullets1 = Tank.getBullets();
		for(int i = 0; i < bullets1.size(); i++) {
			Bullet b1 = bullets1.get(i);
			if(b1.isVisible() == true) {
				b1.move(t1);
			}
			else {
				bullets1.remove(i);
			}
		}
		ArrayList<Bullet> bullets2 = Tank.getBullets();
		for(int w = 0; w < bullets2.size(); w++) {
			Bullet b2 = bullets2.get(w);
			if(b2.isVisible() == true) {
				b2.move(t2);
			}
			else {
				bullets2.remove(w);
			}
		}
		repaint();
		
	}

}
