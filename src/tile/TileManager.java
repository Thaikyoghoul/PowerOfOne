package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[]tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/map/ThroneRoom.txt");
	}
	
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/CastleFloor.jpg"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Carpet.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Throne.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/CastleWall.png"));
			tile[3].collision = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String filepath) {
		try {
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row <gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num; 
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}
		catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		
		
		while(col < gp.maxWorldCol && row<gp.maxWorldRow) {
			int tileNum = mapTileNum[col][row];
			
			int worldX = col * gp.tileSize;
			int worldY = row * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//Stop moving camera at edge
			if(gp.player.screenX>gp.player.worldX) {
				screenX = worldX;
			}
			if(gp.player.screenY>gp.player.worldY) {
				screenY = worldY;
			}
			int rightOffset = gp.screenWidth - gp.player.screenX;
			if(rightOffset > gp.worldWidth - gp.player.worldX) {
				screenX = gp.screenWidth - (gp.worldWidth - worldX);
			}
			int bottomOffset = gp.screenHeight - gp.player.screenY;
			if(bottomOffset > gp.worldHeight - gp.player.worldY) {
				screenY = gp.screenHeight - (gp.worldHeight - worldY);
			}
			
			if(worldX  + gp.tileSize>gp.player.worldX - gp.player.screenX && 
			   worldX  - gp.tileSize< gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize>gp.player.worldY - gp.player.screenY && 
			   worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY    , gp.tileSize, gp.tileSize, null);
			}
			else if(gp.player.screenX>gp.player.worldX ||
					gp.player.screenY>gp.player.worldY ||
					rightOffset > gp.worldWidth - gp.player.worldX ||
					bottomOffset > gp.worldHeight - gp.player.worldY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY    , gp.tileSize, gp.tileSize, null);
			}
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
			}
			
		}
	}
	
}
