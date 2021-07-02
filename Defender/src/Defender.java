import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.Scanner; 
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

//=====================================================================================================================================================
//=====================================================================================================================================================

public class Defender extends JFrame implements ActionListener{
	//=====================================================================================================================================================
  	//=====================================================================================================================================================
	
	Timer myTimer;
	GamePanel game;
	
	//=====================================================================================================================================================
  	//=====================================================================================================================================================
	
    public Defender() throws IOException{
		super("Defender");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,720);
		
		game = new GamePanel();
		add(game);

		setResizable(false);
		setVisible(true);
		myTimer = new Timer(10, this);	 // trigger every 10 ms
		myTimer.start();
		
		
		
    }

    //=====================================================================================================================================================
  	//=====================================================================================================================================================
	
    public void actionPerformed(ActionEvent evt) {
		if(game!= null){
			game.repaint();
			game.collide();
			game.dead();
			game.update();
		}	
	}

	//=====================================================================================================================================================
  	//=====================================================================================================================================================

    public static void main(String[] arguments) throws IOException {
    	new Defender();	
    }
    
    //=====================================================================================================================================================
  	//=====================================================================================================================================================
}

//=====================================================================================================================================================
//=====================================================================================================================================================

class GamePanel extends JPanel implements MouseListener,MouseMotionListener {
	//=====================================================================================================================================================
  	//=====================================================================================================================================================
	
	
	private Font enemiesAlive = new Font("Hel",Font.BOLD,200);
	private Font enemyHealth = new Font("Hel",Font.BOLD,25);
	private Font wallHealth = new Font("Hel",Font.BOLD,50);
	private Font golds = new Font("Hel",Font.BOLD,25);

	private Image bowPic,arrowPic;
	private Image goldIcon,crystalIcon;
	private Image floor;
	private Image background;
	
	private static int defaultArrowDelay = 500;
	private static int enemySpawnDelay = 1000;
	private static int time = 0;
	private static int intensity = 0;
	
	private int counter = 100;
	private int timeDec = 3;
	private int arrowCount = 0;
	private int enemyCount = 0;
	private int eProjectileCount = 0;
	private int projectileCount, projectileCount2 = 0;
	private int mousex, mousey;
	private int level = 1;
	private int waves = 1;
	private int waveInc = 0;
	private int wallHPInc = 0;
	private int gold = 0;
	private int crystal = 0;
	private int s = 0;
	private int bowX = 0;
	
	private double bowAngle, arrowAngle;

	private boolean mousepressed = false;

	private ArrayList<Enemy> enemies;
	private Enemy enemy;
	private ArrayList<EnemyProjectile> eProjectiles;
	private EnemyProjectile eProjectile;
	private Enemy test;
		
	private ArrayList<Arrow> arrows;
	private Arrow arrow;
	
	private ArrayList<Tower> projectiles;
	private Tower projectile;
	private ArrayList<Tower> projectiles2;
	private Tower projectile2;

	private Wall wall;
	
	private Random rand = new Random();
	Arrows q;    

	static Reader read;
	static Writer write;
		
	ArrayList<Image> wolf = new ArrayList<Image>();
	Image wolf1 = new ImageIcon("Wolf\\Wolf1.png").getImage().getScaledInstance(50 * 2, 30 * 2, 0); //48 30
	Image wolf2 = new ImageIcon("Wolf\\Wolf2.png").getImage().getScaledInstance(50 * 2, 30 * 2, 0); //49 40
	Image wolf3 = new ImageIcon("Wolf\\Wolf3.png").getImage().getScaledInstance(50 * 2, 30 * 2, 0); //50 27
	Image wolf4 = new ImageIcon("Wolf\\Wolf4.png").getImage().getScaledInstance(50 * 2, 30 * 2, 0); //48 32
	Image wolf5 = new ImageIcon("Wolf\\Wolf5.png").getImage().getScaledInstance(50 * 2, 30 * 2, 0); //46 30
	int wolfIndex = 0;
	
	ArrayList<Image> snake = new ArrayList<Image>();
	Image snake1 = new ImageIcon("Snake\\Snake1.png").getImage().getScaledInstance(45 * 2, 50 * 2, 0); //59 81
	Image snake2 = new ImageIcon("Snake\\Snake2.png").getImage().getScaledInstance(45 * 2, 50 * 2, 0); //56 73
	Image snake3 = new ImageIcon("Snake\\Snake3.png").getImage().getScaledInstance(45 * 2, 50 * 2, 0); //55 59
	Image snake4 = new ImageIcon("Snake\\Snake4.png").getImage().getScaledInstance(45 * 2, 50 * 2, 0); //60 53
	Image snake5 = new ImageIcon("Snake\\Snake5.png").getImage().getScaledInstance(45 * 2, 50 * 2, 0); //63 66
	int snakeIndex = 0;
	
	ArrayList<Image> croc = new ArrayList<Image>();
	Image croc1 = new ImageIcon("Crocodile\\Crocodile1.png").getImage().getScaledInstance(60 * 2, 50 * 2, 0); //64 46
	Image croc2 = new ImageIcon("Crocodile\\Crocodile2.png").getImage().getScaledInstance(60 * 2, 50 * 2, 0); //54 47
	Image croc3 = new ImageIcon("Crocodile\\Crocodile3.png").getImage().getScaledInstance(60 * 2, 50 * 2, 0); //52 48
	Image croc4 = new ImageIcon("Crocodile\\Crocodile4.png").getImage().getScaledInstance(60 * 2, 50 * 2, 0); //52 46
	int crocIndex = 0;
	
	ArrayList<Image> dragon = new ArrayList<Image>();
	Image dragon1 = new ImageIcon("Dragon\\Dragon1.png").getImage().getScaledInstance(180, 90, 0); //181 93
	Image dragon2 = new ImageIcon("Dragon\\Dragon2.png").getImage().getScaledInstance(180, 90, 0);; //181 80
	Image dragon3 = new ImageIcon("Dragon\\Dragon3.png").getImage().getScaledInstance(180, 90, 0);; //174 135
	int dragonIndex = 0;
	
	ArrayList<Image> dinosaur = new ArrayList<Image>();
	Image dinosaur1 = new ImageIcon("Dinosaur\\Dinosaur1.png").getImage().getScaledInstance(60 * 2, 40 * 2, 0); //60 41
	Image dinosaur2 = new ImageIcon("Dinosaur\\Dinosaur2.png").getImage().getScaledInstance(60 * 2, 40 * 2, 0); //57 39
	Image dinosaur3 = new ImageIcon("Dinosaur\\Dinosaur3.png").getImage().getScaledInstance(60 * 2, 40 * 2, 0); //57 40
	Image dinosaur4 = new ImageIcon("Dinosaur\\Dinosaur4.png").getImage().getScaledInstance(60 * 2, 40 * 2, 0); //57 40
	Image dinosaur5 = new ImageIcon("Dinosaur\\Dinosaur5.png").getImage().getScaledInstance(60 * 2, 40 * 2, 0); //55 43
	Image dinosaur6 = new ImageIcon("Dinosaur\\Dinosaur6.png").getImage().getScaledInstance(60 * 2, 40 * 2, 0); //56 40
	int dinosaurIndex = 0;
	
	ArrayList<Image> fireball = new ArrayList<Image>();
	Image fireball1 = new ImageIcon("Fireball\\Fireball1.png").getImage();
	Image fireball2 = new ImageIcon("Fireball\\Fireball2.png").getImage();
	Image fireball3 = new ImageIcon("Fireball\\Fireball3.png").getImage();
	Image fireball4 = new ImageIcon("Fireball\\Fireball4.png").getImage();
	Image fireball5 = new ImageIcon("Fireball\\Fireball5.png").getImage();
	Image fireball6 = new ImageIcon("Fireball\\Fireball6.png").getImage();
	int fireballIndex = 0;
	//=====================================================================================================================================================
  	//=====================================================================================================================================================
	
	public GamePanel() throws IOException{
		addMouseMotionListener(this);
		addMouseListener(this);
		setSize(1280,720);
		
		wolf.add(wolf1);
		wolf.add(wolf2);
		wolf.add(wolf3);
		wolf.add(wolf4);
		wolf.add(wolf5);
		
		snake.add(snake1);
		snake.add(snake2);
		snake.add(snake3);
		snake.add(snake4);
		snake.add(snake5);
		
		croc.add(croc1);
		croc.add(croc2);
		croc.add(croc3);
		croc.add(croc4);
		
		dragon.add(dragon1);
		dragon.add(dragon2);
		dragon.add(dragon3);
		
		dinosaur.add(dinosaur1);
		dinosaur.add(dinosaur2);
		dinosaur.add(dinosaur3);
		dinosaur.add(dinosaur4);
		dinosaur.add(dinosaur5);
		dinosaur.add(dinosaur6);
		
		fireball.add(fireball1);
		fireball.add(fireball2);
		fireball.add(fireball3);
		fireball.add(fireball4);
		fireball.add(fireball5);
		fireball.add(fireball6);
				
		bowPic = new ImageIcon("src\\Sprites\\Bow and Arrow\\Bow.png").getImage().getScaledInstance(60, 200, 0);
		arrowPic = new ImageIcon("src\\Sprites\\Bow and Arrow\\Arrow.png").getImage().getScaledInstance(90, 15, 0);
		
		goldIcon = new ImageIcon("src\\Sprites\\Icons\\Gold Icon.jpg").getImage().getScaledInstance(25, 25, 0);
		crystalIcon = new ImageIcon("src\\Sprites\\Icons\\Crystal Icon.jpg").getImage().getScaledInstance(25, 25, 0);
		
		floor = new ImageIcon("Floor.jpg").getImage();
		background = new ImageIcon("Background2.jpg").getImage().getScaledInstance(getWidth(), getHeight(), 0);
		
		enemies = new ArrayList<Enemy>();
		arrows = new ArrayList<Arrow>();
		eProjectiles = new ArrayList<EnemyProjectile>();
		
		projectiles = new ArrayList<Tower>();
		projectiles2 = new ArrayList<Tower>();
		
		wall = new Wall(0, 0, 250, getHeight(),1000000);
		//test = new Enemy(getWidth() + 50, 500,50,50,5,200,0,20, false, false, 500, 10);
		
		read = new Reader();
		write = new Writer();
	}
	
	public void save(int n1, int n2, int n3, int n4, String path) throws IOException{
		write.write(n1,n2,n3,n4, path);
	}
	
	public ArrayList<Integer> load(String path) throws IOException{
		return read.reader(path);
	}
	
	//=====================================================================================================================================================
  	//=====================================================================================================================================================
	
	public void update() {
		for (int i = 0; i < enemyCount; i++) {
			enemies.get(i).move();
		}
		
		for (int i = 0; i < arrowCount; i++) {
			arrows.get(i).move();
		}
		
		for (int i = 0; i < eProjectiles.size(); i++) {
			eProjectiles.get(i).shoot();
		}
		
		for (int i = 0; i < projectileCount; i++) {
			projectiles.get(i).move(enemy.getX(), enemy.getY());
		}
		
		for (int i = 0; i < projectileCount2; i++) {
			projectiles2.get(i).move(enemy.getX(), enemy.getY());
		}
		
		//test.move();
	}

	public void towerProjectileSpawn() {
		projectile = new Tower(getWidth()/10 + 25, getHeight()/7 + 25,25,25, 6);
		projectiles.add(projectile);
			
		projectile2 = new Tower(getWidth()/10 + 25, (getHeight() - 200) + 25,25,25, 6);
		projectiles2.add(projectile2);
	      	
	    projectileCount++;
	    projectileCount2++;
	}
	
    public void spawnEnemy() {
    	int g = rand.nextInt(6) + 0;
    	//g = 0;
    	//if (g == 0) {
    	ArrayList<Integer> melee = new ArrayList<Integer>();
    	ArrayList<Integer> projectile = new ArrayList<Integer>();
    	melee.add(0);
    	melee.add(1);
    	melee.add(2);
    	projectile.add(3);
    	projectile.add(4);
  	 	   	
    	int randomMelee = rand.nextInt(melee.size());
  	 	int randomProjectile = rand.nextInt(projectile.size());
  	 	
  	 	int meleeEnemy = melee.get(randomMelee);
  	 	int projectileEnemy = projectile.get(randomProjectile);

    	try {			
	  	 	if (g == 0) { //Singular melee enemy at random y position
	  	 		int y = rand.nextInt(getHeight() - 200) + 50;
	  	 		enemy = new Enemy(getWidth() + 100, y, 100, 100, load("enemy.txt").get(0), load("enemy.txt").get(1),load("enemy.txt").get(1),meleeEnemy,load("enemy.txt").get(2),false,false,500,load("enemy.txt").get(3));
	  	 		enemies.add(enemy);
	  	 		enemyCount++;
	  	 	}
	  	 	
	  	 	else if (g == 1) { //Singular projectile enemy at random y position
	  	 		int y = rand.nextInt(getHeight() - 200) + 50;
	  	 		enemy = new Enemy(getWidth() + 100, y, 100, 100, load("enemy.txt").get(0), load("enemy.txt").get(1),load("enemy.txt").get(1),projectileEnemy,load("enemy.txt").get(2),false,false,500,load("enemy.txt").get(3));
	  	 		enemies.add(enemy);
	  	 		enemyCount++;
	  	 	}
	  	 	
	  	 	else if (g == 2) {
	  	 		int numE = rand.nextInt(7) + 3;

	  	 		for (int i = 0; i < 5; i++) {
	  	 			int y = 50 * i;
	  		 		enemy = new Enemy(getWidth() + 100, y + 200, 100, 100, load("enemy.txt").get(0), load("enemy.txt").get(1),load("enemy.txt").get(1),projectileEnemy,load("enemy.txt").get(2),false,false,500,load("enemy.txt").get(3));
	  	  	 		enemies.add(enemy);
	  	  	 		enemyCount++;
	  	 		}
	  	 	}
	  	 	
	  	 	
    	}
    	
    	catch (IOException e) {
    		System.out.println("ERROR");
    	}
  	 	


    }
    
    public void spawnEnemyProjectile() {
    	for (int i = 0; i < enemyCount; i++) {
	    	eProjectile = new EnemyProjectile(enemies.get(i).getX() - 25, enemies.get(i).getY() + 10, 20,20,5);
	    	
	    	if (enemies.get(i).getType() >= 3 && enemies.get(i).at850x()) {
	    		enemies.get(i).fire(enemies.get(i).getFireRate());
	    		
	    		if (enemies.get(i).getReset() < 0) {
	    			eProjectiles.add(eProjectile);
		    		eProjectileCount++;
		    		enemies.get(i).setAttacked(false);
		    		enemies.get(i).setReset(enemies.get(i).getAttackTimer());
	    		}
	    		
	    		else {
	    			enemies.get(i).setAttacked(true);
	    		}
	    	}
    	}
    }
    
    public void collide() {
    	for (int a = 0; a < arrows.size(); a ++) {
    		for (int e = enemies.size() - 1; e >= 0; e--) {
    			if (enemies.get(e).collides((int)arrows.get(a).getX(),(int)arrows.get(a).getY(),arrows.get(a).getW(),arrows.get(a).getH())) {
        			enemies.get(e).HpLost(50);
        			arrows.remove(arrows.get(a));
        			arrowCount--;
        			break;
        		}
    		}
    	}
    	
		for (int a = 0; a < projectiles.size(); a ++) {
			if (enemy.collides((int)projectiles.get(a).getX(),(int)projectiles.get(a).getY(),projectiles.get(a).getW(),projectiles.get(a).getH())) {
				projectiles.remove(projectiles.get(a));
				projectileCount--;
				enemy.HpLost(4);
			}
		}
		
		for (int a = 0; a < projectiles2.size(); a ++) {
			if (enemy.collides((int)projectiles2.get(a).getX(),(int)projectiles2.get(a).getY(),projectiles2.get(a).getW(),projectiles2.get(a).getH())) {
				projectiles2.remove(projectiles2.get(a));
				projectileCount2--;
				enemy.HpLost(4);
			}
		}
		
		for (int a = 0; a < projectiles.size(); a ++) {
			for (int e = 0; e < enemies.size(); e++) {
				if (enemies.get(e).collides((int)projectiles.get(a).getX(),(int)projectiles.get(a).getY(),projectiles.get(a).getW(),projectiles.get(a).getH())) {
					enemies.get(e).HpLost(2);
				}
			}
		}
		
		for (int a = 0; a < projectiles2.size(); a ++) {
			for (int e = 0; e < enemies.size(); e++) {
				if (enemies.get(e).collides((int)projectiles2.get(a).getX(),(int)projectiles2.get(a).getY(),projectiles2.get(a).getW(),projectiles2.get(a).getH())) {
					enemies.get(e).HpLost(2);
				}
			}
		}
    }
    
    public void enemyDamage() {
    	for (int i = 0; i < enemyCount; i++) {
			if (enemies.get(i).collides(wall.getX(),wall.getY(),wall.getW(),wall.getH())) {
				
				enemies.get(i).fire(enemies.get(i).getFireRate());
	    		
	    		if (enemies.get(i).getReset() < 0) {
	    			wall.HpLost(enemies.get(i).getDamage());
	    			enemies.get(i).setAttacked(false);
		    		enemies.get(i).setReset(enemies.get(i).getAttackTimer());
	    		}
	    		
	    		else {
	    			enemies.get(i).setAttacked(true);
	    		}
    		}
		}
    	
    	for (int i = 0; i < eProjectiles.size(); i++) {
			if (eProjectiles.get(i).collides(wall.getX(),wall.getY(),wall.getW(),wall.getH())) {
    			wall.HpLost(20);
    			eProjectiles.remove(eProjectiles.get(i));
    			eProjectileCount--;
    		}
		}
    }
    
    public void dead() {
    	for (int i = 0; i < enemies.size(); i++) {
    		if (enemies.get(i).getHp() <= 0) {
    			enemies.get(i).setDead(true);
    			enemies.remove(enemies.get(i));
    			enemyCount--;
    			int rGold = rand.nextInt(8) + 5;
    			gold += rGold;
    		}
    	}
    }
    
    public void sprite() {
  	 	 if (wolfIndex >= 4) {
   	 		 wolfIndex = 0;
   	 	 }
   	 	   	 	 
   	 	 if (snakeIndex >= 4) {
   	 		 snakeIndex = 0;
   	 	 }
   	 	 
   	 	 if (crocIndex >= 3) {
   	 		 crocIndex = 0;
   	 	 }
   	 	 
   	 	 if (dragonIndex >= 2) {
   	 		dragonIndex = 0;
   	 	 }
   	 	 
   	 	 if (dinosaurIndex >= 5) {
   	 		dinosaurIndex = 0;
   	 	 }
   	 	 
   	 	 if (fireballIndex >= 5) {
   	 		fireballIndex = 0;
   	 	 }
   	 	 
   		 if (counter % 5 == 0) {
   			wolfIndex++;
   	 	 }
   	 	 
   	 	 if (counter % 10 == 0) {
    	 	dinosaurIndex++;
    	 	snakeIndex++;
    	 	crocIndex++;
   	 	 }
   	 	 
   	 	 if (counter % 30 == 0) {
   	 		dragonIndex++;
   	 		fireballIndex++;
   	 	 }
    }

	//=====================================================================================================================================================
  	//=====================================================================================================================================================
	
    public void paintComponent(Graphics g){
    	 //=====================================================================================================================================================
      	 //=====================================================================================================================================================
    	
    	 g.setColor(Color.WHITE);
   	 	 g.fillRect(0, 0, getWidth(), getHeight());
   	 	 //g.drawImage(background, 250, 0, this);
   	 	 Graphics2D g2d = (Graphics2D) g;

   	 	 //=====================================================================================================================================================
       	 //=====================================================================================================================================================
   	 	 g.setColor(Color.BLACK);
   	 	 g.fillRect(0,684,getWidth(),1);
   	 	 //g.fillRect(test.getX(), test.getY(), 50, 50);
   	 	 
   	 	 //g.drawImage(floor, 0, 0, this);
		 //=====================================================================================================================================================
       	 //=====================================================================================================================================================
   	 	    	 	 
   	 	 intensity-=100;
   	 	 
   	 	 if (intensity < 0 && wall.getHp() >= 0 && waves > 0) {
   	 		spawnEnemy();
   	 	
   	 		intensity = enemySpawnDelay;
   	 	 }
   	 	 
   	 	 if (intensity == 0) {
   	 		waves -= 1;
   	 	 }
   	 	 
   	 	 if (enemyCount == 0 && waves == 0 && wall.getHp() > 0) {
   	 		 System.out.println("LEVEL " + level + " FINISHED");
   	 		 level++;
   	 		 crystal += 2;
   	 		 waves = 6 + waveInc;
   	 		 waveInc += 3;
   	 		 intensity += 2;
   	 		 wallHPInc += 100;
   	 		 wall.setHp(1000 + wallHPInc);
   	 	 }
   	 	 
   	 	 spawnEnemyProjectile();
   	 	 enemyDamage();
   	 	 sprite();
		 //=====================================================================================================================================================
       	 //=====================================================================================================================================================
   	 	    	 	 
    	 if (counter % 200 == 0 && enemyCount > 0 && !enemy.dead()) {
    		//towerProjectileSpawn();
    	 }
    	 
		 //=====================================================================================================================================================
       	 //=====================================================================================================================================================
    	 
		 if (counter % 100 == 0) {
   	 		 timeDec--;
   	 	 }
		
		 if (timeDec > 0) {
			 g.setFont(enemiesAlive);
			 g.setColor(Color.BLACK);
			 //g.drawString(Integer.toString(timeDec), (getWidth()/2) - 50, (getHeight()/2) - 50);
		 }
		 
		 //=====================================================================================================================================================
       	 //=====================================================================================================================================================
		 
		 for (int i = 0; i < enemyCount; i++) {
			if (enemies.get(i).getType() == 0) {
	  	 	   g.drawImage(wolf.get(wolfIndex),enemies.get(i).getX(), enemies.get(i).getY(),this);

			}
			
			else if (enemies.get(i).getType() == 1) {
		  	 	 g.drawImage(snake.get(snakeIndex),enemies.get(i).getX(), enemies.get(i).getY(),this);
			}
			
			else if (enemies.get(i).getType() == 2) {
		  	 	 g.drawImage(croc.get(crocIndex),enemies.get(i).getX(), enemies.get(i).getY(),this);
			}
			
			else if (enemies.get(i).getType() == 3) {
	  	        g.drawImage(dragon.get(dragonIndex),enemies.get(i).getX(), enemies.get(i).getY(),this);
	  	        
			}
			 
			else if (enemies.get(i).getType() == 4) {
				g.drawImage(dinosaur.get(dinosaurIndex),enemies.get(i).getX(), enemies.get(i).getY(),this);
			}  
			
			g.setColor(Color.BLACK);
			g.fillRect(enemies.get(i).getX(), enemies.get(i).getY(), enemies.get(i).getW(), 5);
			g.setColor(Color.RED);
			g.fillRect(enemies.get(i).getX(), enemies.get(i).getY(), (int)(enemies.get(i).getW() * enemies.get(i).percentHP()), 5);
		 }
      
		 //=====================================================================================================================================================
       	 //=====================================================================================================================================================
		 
		 for (int i = 0; i < eProjectileCount; i++) {
			 g.drawImage(fireball.get(fireballIndex), eProjectiles.get(i).getPx(), eProjectiles.get(i).getPy(), this);
		 }
		 		 
		 //=====================================================================================================================================================
       	 //=====================================================================================================================================================

		 //if (wall.getHp() > 0) {
			 g.setFont(enemiesAlive);
			 g.setColor(Color.BLACK);
			 g.drawString(Integer.toString(enemyCount), (getWidth()/2) - 50, (getHeight()/2) - 50);
		 //}
		 
		 //=====================================================================================================================================================
	     //=====================================================================================================================================================
		 
		 g.setFont(golds);
		 g.drawString(Integer.toString(gold), 500, 30);
		 g.drawString(Integer.toString(crystal), 800, 30);
		 g.drawImage(crystalIcon, 400, 30, this);
		 //g.drawString(Integer.toString(enemyCount),1100, 30);
		 g.fillRect(1100,30,150,2);
		 g.setColor(Color.CYAN);
		 g.fillRect(1230 - s,22,20,20);
		 
		 //=====================================================================================================================================================
       	 //=====================================================================================================================================================
		 
		 g.setColor(Color.GRAY);  //Wall
   	 	 //g.fillRect(wall.getX(),wall.getY(),wall.getW(),wall.getH());
   	 	 
   	 	 g.setFont(wallHealth);
   	 	 g.setColor(Color.BLACK);
		 g.drawString(Integer.toString(wall.getHp()), 10, getHeight() - 50);
		 
		 g.setColor(new Color(102,0,204)); //Tower
    	 g.fillOval(getWidth()/10, (getHeight()/2 - 100) - 100, 75, 75);
    	 g.fillOval(getWidth()/10, (getHeight()/2 - 100) + 220, 75, 75);
    	 
		 //=====================================================================================================================================================
       	 //=====================================================================================================================================================
		 
    	 for (int i = 0; i < projectiles.size(); i++) {
    		 g.setColor(new Color(0,204,204));
    		 g.fillOval((int)projectiles.get(i).getX(), (int)projectiles.get(i).getY(), projectiles.get(i).getW(), projectiles.get(i).getH());
    	 }
    	 
         
    	 for (int i = 0; i < projectiles2.size(); i++) {
    		 g.setColor(new Color(0,204,204));
    		 g.fillOval((int)projectiles2.get(i).getX(), (int)projectiles2.get(i).getY(), projectiles2.get(i).getW(), projectiles2.get(i).getH());
    	 }
    	 
		 //=====================================================================================================================================================
       	 //=====================================================================================================================================================
   	 	 
         for (int i = 0; i < arrowCount; i++) { //Spawning bow arrows
        	 g.setColor(Color.BLACK);
        	 //g.drawImage(arrowPic, (int)arrows.get(i).getX(), (int)arrows.get(i).getY(), this);
        	 
        	 AffineTransform a = AffineTransform.getRotateInstance((int)arrows.get(i).getX(), (int)arrows.get(i).getY());
        	 a.setToTranslation((int)arrows.get(i).getX(), (int)arrows.get(i).getY());
             a.rotate(arrows.get(i).getAng(), 45, 7);
             g2d.drawImage(arrowPic, a, null);
         }
         
         AffineTransform bow = AffineTransform.getRotateInstance(bowAngle);
         bow.setToTranslation(0, getHeight()/2 - 100);
         bow.rotate(bowAngle, 30, 100);
         g2d.drawImage(bowPic, bow, null);
         
         time -= 100;
         if (time <0 && mousepressed) {
	         double angle = Math.atan2(mousey - getHeight()/2, mousex - 38);
	 		 arrow = new Arrow(-10, getHeight()/2 - 5,90,15,15,angle);
	         arrows.add(arrow);
	 		 arrowCount++;
	 		 time = defaultArrowDelay;
         }
                 
   	 	 counter++;
   	 	 
   	 	 
   	 	 try {
   	 		 g.setColor(Color.BLACK);
   	 		 g.setFont(golds);
   	 		 
   	 		 g.drawString(Integer.toString(load("Enemy.txt").get(0)), getWidth()/2, getHeight()/2);
   	 		 g.drawString(Integer.toString(load("Enemy.txt").get(1)), getWidth()/2, getHeight()/2 + 50);
   	 		 g.drawString(Integer.toString(load("Enemy.txt").get(2)), getWidth()/2, getHeight()/2 + 100);
   	 		 g.drawString(Integer.toString(load("Enemy.txt").get(3)), getWidth()/2, getHeight()/2 + 150);
   	 	 }
   	 	 
   	 	 catch (IOException e) {
   	 		 
   	 	 }
         //=====================================================================================================================================================
       	 //=====================================================================================================================================================
    }
    
    //=====================================================================================================================================================
  	//=====================================================================================================================================================
    
    public void mouseDragged(MouseEvent e){
    	mousex = e.getX();
		mousey = e.getY();
		
		bowAngle = Math.atan2(mousey - getHeight()/2 - 50, mousex - 30);
    } 
    
    public void mouseMoved(MouseEvent e){
    	mousex = e.getX();
    	mousey = e.getY();
    	
    	bowAngle = Math.atan2(mousey - getHeight()/2 - 50, mousex - 30);
    }
    
    public void mousePressed(MouseEvent e){
		if (e.getButton() == 1) {
			mousepressed = true;
			arrowAngle = Math.atan2(mousey - getHeight()/2 - 50, mousex - 30);
			
			try {
				if (level % 2 == 0) {
					save(load("Enemy.txt").get(0), load("Enemy.txt").get(1), load("Enemy.txt").get(2), load("Enemy.txt").get(3), "Enemy.txt");
				}
			}
			catch (IOException b) {
				
			}
    	}
    }
    
    public void mouseReleased(MouseEvent e) {
    	mousepressed = false;
    }
    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e){}
    
    //=====================================================================================================================================================
  	//=====================================================================================================================================================
    
}