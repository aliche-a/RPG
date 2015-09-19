package slash;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayList;
import java.util.Random;

public class Play1 extends BasicGameState {

	Animation samurai, up, left, right, down;
	Image map;
	boolean quit = false;
	int[] duration = {200, 200};
	private float samuraiPosX = 0;
	private float samuraiPosY = 0;
	float shiftX = samuraiPosX + 450;
	float shiftY = samuraiPosY + 350;
	
	//enemy object variables
	float enemyX;
	float enemyY;
	private ArrayList<Enemy> enemies;
	private Enemy enemy;	
	public static int ENEMY_RESPAWN_TIME = 1000;
	int enemyRespawnCountup = 0;
	int eSpawnX;		//random enemy spawn point x
	int eSpawnY;		//random enemy spawn point y
	Random randomGenX;	//random object; will be used to determine coordinates
	Random randomGenY;
	//declare enemy vector to draw onto screen with delay
	
	public Play1(int state) {}
	
	//init()
	//setting up the images to be used in animation
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new Image("res/Background.png");
		Image[] walkUp = {new Image("res/Samurai Back.png"), new Image("res/Samurai Back.png")};
		Image[] walkDown = {new Image("res/Samurai.png"), new Image("res/Samurai.png")};
		Image[] walkLeft = {new Image("res/Samurai Left.png"), new Image("res/Samurai Left.png")};
		Image[] walkRight = {new Image("res/Samurai Right.png"), new Image("res/Samurai Right.png")};
		enemies = new ArrayList<Enemy>(10);		//create enemies to be rendered
		
		up = new Animation(walkUp, duration, false);
		down = new Animation(walkDown, duration, false);
		left = new Animation(walkLeft, duration, false);
		right = new Animation(walkRight, duration, false);
		
		samurai = down;
	}
	
	//render()
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		map.draw(0, 0);
		samurai.draw(samuraiPosX, samuraiPosY);
		g.drawString("X position: " + samuraiPosX + "\nY position " + samuraiPosY, 400, 20);
		
		//as long as there are enemies that are ready to be rendered
		if(enemies != null) {
			for(int k = 0; k < enemies.size(); k++) {
				enemies.get(k).drawEnemy(g);
			}
		}
	}
	
	//update()
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		//create new random object to generate random numbers
		Random randomGenX = new Random();
		Random randomGenY = new Random();
		
		enemyRespawnCountup += delta;

		if (enemyRespawnCountup > ENEMY_RESPAWN_TIME) {
			//if the delay was met
			//generate a random number to determine starting position of next enemy
			eSpawnX = randomGenX.nextInt(371);
			eSpawnY = randomGenY.nextInt(381);
			if(eSpawnX > 100) {		//X spawn point will be somewhere in right side
				eSpawnX += 550;
			}
			if(eSpawnY > 50) {		//Y spawn point somewhere right
				eSpawnY += 150;
			}
			
			enemies.add(new Enemy(eSpawnX, eSpawnY));	
			//add a new enemy object to ArrayList to spawn at randomly generated coordinates
			enemyRespawnCountup = 0;
		}

		//enemy collision detection
		//as long as there are enemies on the screen
		if(enemies != null) {
			for(int j = 0; j < enemies.size(); j++) {	//for every enemy in the vector
				//check its position relative to samurai and move it accordingly
				//enemy is left of samurai
				enemy = enemies.get(j);
				if(enemy.enemyPosX < samuraiPosX) {	//enemy and samurai have not collided
					enemy.goRight(delta);
				}
			
				//enemy is right
				if(enemy.enemyPosX > samuraiPosX) {
					enemy.goLeft(delta);
				}
				
				//enemy is below
				if(enemy.enemyPosY > samuraiPosY) {
					enemy.goUp(delta);
				}
				
				//enemy is above
				if(enemy.enemyPosY < samuraiPosY) {
					enemy.goDown(delta);
				}
			}	
		}
		
		//up
		if(input.isKeyDown(Input.KEY_UP)) {
			samurai = up;
			samuraiPosY -= delta * .5f;
			if(samuraiPosY <= 0) {	//collision detection for screen edge
				samuraiPosY += delta * .5f;
			}
		}
		
		//down
		if(input.isKeyDown(Input.KEY_DOWN)) {
			samurai = down;
			samuraiPosY += delta * .5f;
			if(samuraiPosY >= 533) {
				samuraiPosY -= delta * .5f;
			}			
		}
		
		//left
		if(input.isKeyDown(Input.KEY_LEFT)) {
			samurai = left;
			samuraiPosX -= delta * .5f;
			if(samuraiPosX <= 0) {
				samuraiPosX += delta * .5f;
			}
		}
		
		//right
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			samurai = right;
			samuraiPosX += delta * .5f;
			if(samuraiPosX >= 930) {	//samurai stays on screen
				samuraiPosX -= delta * .5f;
			}
		}
	}
	
	public int getID() {
		return 1;
	}
	
}
