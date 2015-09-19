package slash;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.*;
import org.newdawn.slick.*;

public class Game1 extends StateBasedGame {
	
	public static final String name = "Hack and Slash";
	//public static final int menu = 0;
	public static final int play = 1;
	
	//Constructor
	public Game1(String name) {
		super(name);
		this.addState(new Play1(play));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(play).init(gc, this);
		this.enterState(play);
	}
	
	//main
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game1(name));
			appgc.setDisplayMode(980, 600, false);
			appgc.start();
		}catch(SlickException e) {
			e.printStackTrace();
		}
	}

}
