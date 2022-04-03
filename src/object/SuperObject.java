package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0,0,64,64);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0; 
	
	public void draw(Graphics2D g2, GamePanel gp) {
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
			
			if(worldX  + gp.tileSize>gp.player.worldX - gp.player.screenX && 
				worldX  - gp.tileSize< gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize>gp.player.worldY - gp.player.screenY && 
				worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
				g2.drawImage(image, x, y    , gp.tileSize, gp.tileSize, null);
			}
			else if(gp.player.screenX>gp.player.worldX ||
				gp.player.screenY>gp.player.worldY ||
				rightOffset > gp.worldWidth - gp.player.worldX ||
				bottomOffset > gp.worldHeight - gp.player.worldY) {
				g2.drawImage(image, x, y    , gp.tileSize, gp.tileSize, null);
					}
		}
		
	}
}
