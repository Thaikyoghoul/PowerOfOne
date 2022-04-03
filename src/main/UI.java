package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import object.OBJ_Heart;
import object.SuperObject;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, purisaB;
	BufferedImage hp;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public String currentDialogue = "";
	public int commandNum = 0;;
	public String currentChallenge = "";
	public int currentAnswer = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		}
		catch(FontFormatException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//CREATE HUD OBJECT
		SuperObject heart = new OBJ_Heart();
		hp = heart.image;
		
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		
		
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		//PLAYSTATE
		else if(gp.gameState == gp.playState) {
			
			//draw player life
			drawPlayerLife();
			
		}
		
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		//DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		
		//GAME OVER
		else if(gp.gameState == gp.overState) {
			drawGameOverScreen();
		}
		
		
		//MESSAGE
		if(messageOn == true) {
			
			g2.setFont(g2.getFont().deriveFont(20F));
			g2.drawString(message, gp.tileSize*8, gp.tileSize*7);
			 
			messageCounter++;
			
			if(messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
			}
		
		}
				
	}
	
	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		while(i<gp.player.life) {
			g2.drawImage(hp, x, y, null);
			i++;
			x+= gp.tileSize;
		}
		
	}
	
	public void drawTitleScreen() {
		
		//Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "POWER OF ONE";
		int x;
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		int y = gp.tileSize*3;
		
		//SHADOW
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		
		text = "NEW GAME";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "QUIT";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}

	}
	
	public void drawPauseScreen() {
		String text = "PAUSED";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64F));
		int x;
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		
		//WINDOW
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*5;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y+= 38;
		}
			y += 90;
		
		if(gp.player.inCombat) {
			
			
			for(String line: currentChallenge.split("\n")) {
				g2.drawString(line, x-20, y);
				y+= 38;
			}
		}
		
	}
	
	public void drawGameOverScreen() {
		String text = "GAME OVER";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		int x;
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		int y = gp.screenHeight/2;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		g2.drawString(text, x, y);
	}
	
	public void drawSubWindow(int x, int y , int width, int height) {
		Color c = new Color(0,0,0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x +5, y+5, width-10, height-10, 25, 25);
		
	}
	
	
}
