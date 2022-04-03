package entity;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 12;
		solidArea.y = 20;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		solidArea.width = 44;
		solidArea.height = 44;
		
		
		
		setDefaultValues();
		getPlayerImage();
	}
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
			upstill = ImageIO.read(getClass().getResourceAsStream("/player/player_up_still.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
			downstill = ImageIO.read(getClass().getResourceAsStream("/player/player_down_still.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
			leftstill = ImageIO.read(getClass().getResourceAsStream("/player/player_left_still.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
			rightstill = ImageIO.read(getClass().getResourceAsStream("/player/player_right_still.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize*15;
		worldY = gp.tileSize*12;
		speed = 4;
		direction = "down";
		
		//PLAYER STATUS
		maxLife = 3;
		life = maxLife;
	}
	
	public void update() {
		
			if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
				
			}
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
			interactNPC(npcIndex);
			
			//CHECK EVENT
			gp.eHandler.checkEvent();
			
			//walking animation
			spriteCounter++;
			if(spriteCounter > 20) {
				if(spriteNum ==1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
			//IF COLLIDING, CANNOT MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			//CHECK LIFE
			if(life <= 0) {
				gp.gameState = gp.overState;
			}
			
		}
	
		
			
	}
	
	public void pickUpObject(int index) {
		if(index != 999) {
			String objectName = gp.obj[index].name;
			
			switch(objectName) {
			case"Sword": 
				speed += 2;
				gp.obj[index] = null;
				gp.ui.showMessage("Power Boost! Speed Increased!");
				break;
			}
		}
	}
	
	public void interactNPC(int index) {
		if(index !=999) {
			
			
			gp.gameState = gp.dialogueState;
			gp.npc[index].speak();
			
		}
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(keyH.upPressed == false) {
				image = upstill;
				break;
			}
			if(spriteNum == 1)
				image = up1;
			if(spriteNum == 2)
				image = up2;
			break;
		case "down":
			if(keyH.downPressed == false) {
				image = downstill;
				break;
			}
			if(spriteNum == 1)
				image = down1;
			if(spriteNum == 2)
				image = down2;
			
			break;
		case "left":
			if(keyH.leftPressed == false) {
				image = leftstill;
				break;
			}
			if(spriteNum == 1)
				image = left1;
			if(spriteNum == 2)
				image = left2;
			break;
		case "right":
			if(keyH.rightPressed == false) {
				image = rightstill;
				break;
			}
			if(spriteNum == 1)
				image = right1;
			if(spriteNum == 2)
				image = right2;
			break;
		}
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
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
		
		
	}
}
