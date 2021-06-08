package game.UTF;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Tank{
	int life = 100;
	int damage;
	int id;
	int tank_x;
	int tank_y;
	int tank_height;
	int tank_width;
	Color color;
	int speed;
	Image tank1_up_img, tank2_up_img, tank1_left_img, tank2_left_img, tank1_right_img, tank2_right_img, tank1_down_img,tank2_down_img, tank_expl;
	ImageIcon b,c,d,e,f,g,h,l,m;
	Bullet z;
	String dir;
	static ArrayList<Bullet> bullets;
	boolean tank_died = false;
	int level_speed = 20;
	int b_count = 0;
	
	public Tank(int id, int tank_x, int tank_y, int tank_width, int tank_height, int damage, int speed, Color color, String dir, int life) {
		this.life = life;
		this.damage = damage;
		this.id = id;
		this.tank_x = tank_x;
		this.tank_y = tank_y;
		this.tank_width = tank_width;
		this.tank_height = tank_height;
		this.color = color;
		this.speed = speed;
		this.dir = dir;
		b = new ImageIcon("images/tank1/tank1_up.png");
		c = new ImageIcon("images/tank2/tank2_up.png");
		d = new ImageIcon("images/tank1/tank1_left.png");
		e = new ImageIcon("images/tank2/tank2_left.png");
		f = new ImageIcon("images/tank1/tank1_right.png");
		g = new ImageIcon("images/tank2/tank2_right.png");
		h = new ImageIcon("images/tank1/tank1_down.png");
		l = new ImageIcon("images/tank2/tank2_down.png");
		m = new ImageIcon("images/tank_expl.gif");
		tank_expl = m.getImage();
		tank1_up_img = b.getImage();
		tank2_up_img = c.getImage();
		tank1_left_img = d.getImage();
		tank2_left_img = e.getImage();
		tank1_right_img = f.getImage();
		tank2_right_img = g.getImage();
		tank1_down_img = h.getImage();
		tank2_down_img = l.getImage();
		bullets = new ArrayList<>();
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public static ArrayList<Bullet> getBullets() {
		return bullets;
	}

	// Controllo della collisione del proiettile verso il tank avversario
	public void checkBulletCollision(Tank t) {
		if(new Rectangle(z.getX(), z.getY(), 4, 4)
				.intersects(new Rectangle(t.getTank_x(), t.getTank_y(), t.getTank_width(), t.getTank_height())))
				{
					z.visible = false;
					t.life = t.life - 5;
					System.out.println("TANK COLPITO !");
				}
	}
	
	// Posizione da cui viene sparato il proiettile
	public void fire(Tank t) {
		int xDir = 0;
		int yDir = 0;
		if(t.getDir() == "up" || t.getDir() == "u") {
			xDir = 24;
			yDir = -10;
		}
		if(t.getDir() == "down" || t.getDir() == "d") {
			xDir = 24;
			yDir = 58;
		}
		if(t.getDir() == "left" || t.getDir() == "l") {
			xDir = -10;
			yDir = 24;
		}
		if(t.getDir() == "right" || t.getDir() == "r") {
			xDir = 58;
			yDir = 24;
		}
		z = new Bullet(t.tank_x+xDir, t.tank_y+yDir , t.damage, level_speed, t.dir);
		bullets.add(z);
		b_count++;
		z.b_dead = false;
		System.out.println("Proiettile sparato !");
		checkBulletCollision(t);
	}
	
	// Controllo se il carro è stato distrutto
	public void checkLife() {
		if(this.life <= 0) {
			tank_died = true;
		}
		
	}

	// Gestione della grafica dei movimenti del primo tank
	public void paint1(Graphics2D g2d) {
		if(this.getDir() == "left") {
			g2d.drawImage(this.tank1_left_img, this.getTank_x(), this.getTank_y(), null);
		}
		else if (this.getDir() == "right") {
			g2d.drawImage(this.tank1_right_img, this.getTank_x(), this.getTank_y(), null);
		}
		else if(this.getDir() == "up") {
			g2d.drawImage(this.tank1_up_img, this.getTank_x(), this.getTank_y(), null);
		}
		else if(this.getDir() == "down") {
			g2d.drawImage(this.tank1_down_img, this.getTank_x(), this.getTank_y(), null);
		}
	}
	
	// Gestione della grafica dei movimenti del secondo tank
	public void paint2(Graphics2D g2d) {
		if(this.getDir() == "l") {
			g2d.drawImage(this.tank2_left_img, this.getTank_x(), this.getTank_y(), null);
		}
		else if (this.getDir() == "r") {
			g2d.drawImage(this.tank2_right_img, this.getTank_x(), this.getTank_y(), null);
		}
		else if(this.getDir() == "u") {
			g2d.drawImage(this.tank2_up_img, this.getTank_x(), this.getTank_y(), null);
		}
		else if(this.getDir() == "d") {
			g2d.drawImage(this.tank2_down_img, this.getTank_x(), this.getTank_y(), null);
		}
	}
	
	// Animazione dell'esplosione del tank quando viene distrutto
	public void drawExplosion(Graphics2D g) {
		g.drawImage(this.tank_expl, this.getTank_x(), this.getTank_y(), null);
	}
	public void checkCollision() {
		if(this.getTank_x() <= 0) {  
			this.setTank_x(0);
		}
		
		if(this.getTank_y() <= 0) {  
			this.setTank_y(0);
		}

		if(this.getTank_x() >= (Panel.SCREEN_WIDTH-Panel.TANK_WIDTH)) {  
			this.setTank_x((Panel.SCREEN_WIDTH-Panel.TANK_WIDTH)-10);
		}
		
		if(this.getTank_y() >= (Panel.SCREEN_HEIGHT-Panel.TANK_HEIGHT)) {  
			this.setTank_y((Panel.SCREEN_HEIGHT-Panel.TANK_HEIGHT)-10);
		}
		
	}
	
	// Controllo della collisione del tank con un altro tank
	public void checkTankCollision(Tank t) {
		if(new Rectangle(this.getTank_x(), this.getTank_y(), this.tank_width, this.tank_height)
				.intersects(new Rectangle(t.getTank_x(), t.getTank_y(), t.getTank_width(), t.getTank_height())))
				{
					this.setTank_x((Panel.SCREEN_WIDTH-this.getTank_width())-(Panel.SCREEN_WIDTH-t.getTank_width()));
					this.setTank_y((Panel.SCREEN_HEIGHT-t.getTank_height())-(Panel.SCREEN_HEIGHT-t.getTank_height()));
					this.life = this.life - 50;
					//System.exit(0);  -> comando per chiusura finestra
					//tank_died = true;
				}
	}
	
	
	
	public  int getLIFE() {
		return life;
	}
	public  void setLIFE(int lIFE) {
		life = lIFE;
	}
	public int getDamage() {
		return damage;
	}


	public void setDamage(int damage) {
		this.damage = damage;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getTank_x() {
		return tank_x;
	}


	public void setTank_x(int tank_x) {
		this.tank_x = tank_x;
	}


	public int getTank_y() {
		return tank_y;
	}


	public void setTank_y(int tank_y) {
		this.tank_y = tank_y;
	}


	public int getTank_height() {
		return tank_height;
	}


	public void setTank_height(int tank_height) {
		this.tank_height = tank_height;
	}


	public int getTank_width() {
		return tank_width;
	}


	public void setTank_width(int tank_width) {
		this.tank_width = tank_width;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public Image getTank1_up_img() {
		return tank1_up_img;
	}

	public void setTank1_up_img(Image tank1_up_img) {
		this.tank1_up_img = tank1_up_img;
	}

	public Image getTank2_up_img() {
		return tank2_up_img;
	}

	public void setTank2_up_img(Image tank2_up_img) {
		this.tank2_up_img = tank2_up_img;
	}

	public Image getTank1_left_img() {
		return tank1_left_img;
	}

	public void setTank1_left_img(Image tank1_left_img) {
		this.tank1_left_img = tank1_left_img;
	}

	public Image getTank2_left_img() {
		return tank2_left_img;
	}

	public void setTank2_left_img(Image tank2_left_img) {
		this.tank2_left_img = tank2_left_img;
	}

	public Image getTank1_right_img() {
		return tank1_right_img;
	}

	public void setTank1_right_img(Image tank1_right_img) {
		this.tank1_right_img = tank1_right_img;
	}

	public Image getTank2_right_img() {
		return tank2_right_img;
	}

	public void setTank2_right_img(Image tank2_right_img) {
		this.tank2_right_img = tank2_right_img;
	}

	public Image getTank1_down_img() {
		return tank1_down_img;
	}

	public void setTank1_down_img(Image tank1_down_img) {
		this.tank1_down_img = tank1_down_img;
	}

	public Image getTank2_down_img() {
		return tank2_down_img;
	}

	public void setTank2_down_img(Image tank2_down_img) {
		this.tank2_down_img = tank2_down_img;
	}

	
	

}
