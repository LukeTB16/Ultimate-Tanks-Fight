package game.UTF;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bullet{
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	int damage;
	int speed;
	Image img_b_up, img_b_down, img_b_left, img_b_right;;
	ImageIcon bullet_up, bullet_left, bullet_right, bullet_down;
	boolean visible;
	String dir;
	boolean arr;
	boolean b_dead = false;
	
	public Bullet(int x, int y, int damage, int speed, String dir) {
		this.x = x ;
		this.y = y;
		this.damage = damage;
		this.speed = speed;
		this.dir = dir;
		bullet_up = new ImageIcon("images/bullet/bullet_up.png");
		bullet_left = new ImageIcon("images/bullet/bullet_left.png");
		bullet_right = new ImageIcon("images/bullet/bullet_right.png");
		bullet_down = new ImageIcon("images/bullet/bullet_down.png");
		img_b_up = bullet_up.getImage();
		img_b_down = bullet_down.getImage();
		img_b_left = bullet_left.getImage();
		img_b_right = bullet_right.getImage();
		visible = true;
	}
	
	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Image getImg_b_up() {
		return img_b_up;
	}
	public void setImg_b_up(Image img_b_up) {
		this.img_b_up = img_b_up;
	}
	public Image getImg_b_down() {
		return img_b_down;
	}
	public void setImg_b_down(Image img_b_down) {
		this.img_b_down = img_b_down;
	}
	public Image getImg_b_left() {
		return img_b_left;
	}
	public void setImg_b_left(Image img_b_left) {
		this.img_b_left = img_b_left;
	}
	public Image getImg_b_right() {
		return img_b_right;
	}
	public void setImg_b_right(Image img_b_right) {
		this.img_b_right = img_b_right;
	}
	
	// Controllo collisione del tank con i bordi della finestra e controllo se il proiettile
	// tocca i bordi della finestra
	public void checkCollision(Tank t) {
		if (this.x > 600) {
			visible = false;
			b_dead = true;
		}
		if (this.y > 600){
			visible = false;
			b_dead = true;
		}
		if(this.x <= 0) {
			visible = false;
			b_dead = true;
		}
		if(this.y <= 0) {
			visible = false;
			b_dead = true;
		}
		if(b_dead == true) {
			System.out.println("Proiettile arrivato a destinazione !");
		}
		
	}
	
	// Direzione del fuoco
	public void move(Tank t) {
		checkCollision(t);
			if(this.getDir() == "up" ||  this.getDir() == "u") {
				this.y = this.y - this.speed;
			}
			else if(this.getDir() == "down" || this.getDir() == "d") {
				this.y = this.y + this.speed;
			}
			else if(this.getDir() == "left" || this.getDir() == "l") {
				this.x = this.x - this.speed;
			}
			else if(this.getDir() == "right" || this.getDir() == "r") {
				this.x = this.x + this.speed;
			}
		
	}
}
