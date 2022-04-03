package main;

import entity.NPC_Sibling;
import object.OBJ_Sword;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Sword();
		gp.obj[0].worldX = gp.tileSize*15;
		gp.obj[0].worldY = gp.tileSize*9;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_Sibling(gp);
		gp.npc[0].worldX = gp.tileSize*21;
		gp.npc[0].worldY = gp.tileSize*21;
		
		gp.npc[1] = new NPC_Sibling(gp);
		gp.npc[1].worldX = gp.tileSize*5;
		gp.npc[1].worldY = gp.tileSize*3;
		
		gp.npc[2] = new NPC_Sibling(gp);
		gp.npc[2].worldX = gp.tileSize*25;
		gp.npc[2].worldY = gp.tileSize*45;
		
		gp.npc[3] = new NPC_Sibling(gp);
		gp.npc[3].worldX = gp.tileSize*2;
		gp.npc[3].worldY = gp.tileSize*38;
		
		gp.npc[4] = new NPC_Sibling(gp);
		gp.npc[4].worldX = gp.tileSize*15;
		gp.npc[4].worldY = gp.tileSize*25;
	}
	
}
