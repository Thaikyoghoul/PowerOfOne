package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX;
	int eventRectDefaultY;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 30;
		eventRect.y = 30;
		eventRect.width = 2;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
		
	}
	
	public void checkEvent()
	{
		if(hit(14, 1, "any")|| hit(15,1,"any")) {
			winGame(gp.dialogueState);
		}
	}
	
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol*gp.tileSize + eventRect.x;
		eventRect.y = eventRow* gp.tileSize + eventRect.y;
		
		if(gp.player.solidArea.intersects(eventRect)) {
			hit = true;
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
	
	public void winGame(int gameState) {
		boolean win = true;
		for(int i = 0 ; i<gp.npc.length; i ++) {
			if(gp.npc[i] != null) {
				win = false;
			}
		}
		
		if(win) {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "You are THE KING!";
		}
		else {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "You must kill all your siblings, only you alone is deserving of \npower.";
		}
	}
	
	
}
