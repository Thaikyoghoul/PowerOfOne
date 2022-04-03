package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
	
	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, upstill, downstill,leftstill, rightstill;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea = new Rectangle(0,0 , 64, 64);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	public String[] challenges = new String[10];
	public int[] answers = new int[10];
	public boolean inCombat = false;
	
	public Entity(GamePanel gp) {
		
		this.gp = gp;
	}
	
	//CHARACTER STATUS
	public int maxLife;
	public int life;
	
	public void setAction() {}
	public void speak() {}
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX  + gp.tileSize>gp.player.worldX - gp.player.screenX && 
		   worldX  - gp.tileSize< gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize>gp.player.worldY - gp.player.screenY && 
		   worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
			
			int x = screenX;
			int y = screenY;
			
			if(gp.player.screenX>gp.player.worldX) {
				x = worldX;
			}
			if(gp.player.screenY>gp.player.worldY) {
				y = worldY;
			}
			int rightOffset = gp.screenWidth - screenX;
			if(rightOffset > gp.worldWidth - worldX) {
				x = gp.screenWidth - (gp.worldWidth - worldX);
			}
			int bottomOffset = gp.screenHeight - screenY;
			if(bottomOffset > gp.worldHeight - worldY) {
				y = gp.screenHeight - (gp.worldHeight - worldY);
			}
			
			
			g2.drawImage(downstill, x, y , gp.tileSize, gp.tileSize, null);
			
		}
	}
}
