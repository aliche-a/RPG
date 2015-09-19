package slash;

import org.newdawn.slick.*;

public class Enemy {
	
	public float enemyPosX;
	public float enemyPosY;
	private float maxHP = 104;
	private float currentHP = 100;
	private float healthPosX;
	private float healthPosY;
	
	//Constructor
	public Enemy(float startX, float startY) {
		enemyPosX = startX;
		enemyPosY = startY;
		healthPosX = startX - 25;
		healthPosY = startY - 30;
	}
	
	//draws enemies on screen
	public void drawEnemy(Graphics g) {
		g.drawRect(enemyPosX, enemyPosY, 50, 50);
		g.setColor(Color.black);
		g.fillRect(healthPosX, healthPosY, maxHP, 24);
		g.setColor(Color.green);
		g.fillRect(healthPosX + 2, healthPosY + 2, currentHP, 20);	
	}
	
	//makes enemies move up
	//moves health bar along with enemies
	public void goUp(int delta) {
		enemyPosY -= delta * .1f;
		healthPosY -= delta * .1f;
		if(enemyPosY <= 30) {			//collision detection on window edges
			enemyPosY += delta * .1f;
			healthPosY += delta * .1f;
		}
	}
	
	//enemies go down
	public void goDown(int delta) {
		enemyPosY += delta * .1f;
		healthPosY += delta * .1f;
		if(enemyPosY >= 533) {
			enemyPosY -= delta * .1f;
			healthPosY -= delta * .1f;
		}
	}
	
	//enemies go left
	public void goLeft(int delta) {
		enemyPosX -= delta * .1f;
		healthPosX -= delta * .1f;
		if(enemyPosX <= 35) {
			enemyPosX += delta * .1f;
			healthPosX += delta * .1f;
		}
	}
	
	//enemies go right
	public void goRight(int delta) {
		enemyPosX += delta * .1f;
		healthPosX += delta * .1f;
		if(enemyPosX >= 880) { 
			enemyPosX -= delta * .1f;
			healthPosX -= delta * .1f;
		}
	}
	
	//get enemy position x
	public float getEnX() {
		return enemyPosX;
	}
	
	//get enemy position y
	public float getEnY() {
		return enemyPosY;
	}
	
	//decrease health
	public void decreaseHP(float damage) {
		currentHP -= damage;
	}
}
