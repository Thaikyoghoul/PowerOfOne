package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum <0) {
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum >1) {
					gp.ui.commandNum = 0;
				}
			}
			
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.playMusic(2);
				}
				if(gp.ui.commandNum == 1) {
					System.exit(0);
				}
			}
		}
		
		//PLAY STATE
		else if(gp.gameState == gp.playState) {
			
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_SPACE) {
				
				gp.gameState = gp.pauseState;
			}
			
		}
		
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_SPACE) {
				gp.gameState = gp.playState;
			}
			
		}
		
		//DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_E) {
				gp.gameState = gp.playState;
			}
			
			if(gp.player.inCombat) {
				if(code == KeyEvent.VK_1 && gp.ui.currentAnswer == 1) {
					gp.player.speed ++;
					for(int i = 0; i<gp.npc.length; i++) {
						if(gp.npc[i] != null && gp.npc[i].inCombat == true) {
							gp.npc[i] = null;
							gp.player.inCombat = false;
							gp.gameState = gp.playState;
						}
					}
				}
				
				else if(code == KeyEvent.VK_2 && gp.ui.currentAnswer == 2) {
					gp.player.speed ++;
					for(int i = 0; i<gp.npc.length; i++) {
						if(gp.npc[i] != null && gp.npc[i].inCombat == true) {
							gp.npc[i] = null;
							gp.player.inCombat = false;
							gp.gameState = gp.playState;
						}
					}
				}
				
				else if(code == KeyEvent.VK_3 && gp.ui.currentAnswer == 3) {
					gp.player.speed ++;
					for(int i = 0; i<gp.npc.length; i++) {
						if(gp.npc[i] != null && gp.npc[i].inCombat == true) {
							gp.npc[i] = null;
							gp.player.inCombat = false;
							gp.gameState = gp.playState;
						}
					}
				}
				
				else if(code == KeyEvent.VK_4 && gp.ui.currentAnswer == 4) {
					gp.player.speed ++;
					for(int i = 0; i<gp.npc.length; i++) {
						if(gp.npc[i] != null && gp.npc[i].inCombat == true) {
							gp.npc[i] = null;
							gp.player.inCombat = false;
							gp.gameState = gp.playState;
						}
					}
				}
				
				else if(code == KeyEvent.VK_E) {
					gp.gameState = gp.playState;
					for(int i = 0; i<gp.npc.length; i++) {
						if(gp.npc[i] != null && gp.npc[i].inCombat == true) {
							gp.player.inCombat = false;
							gp.npc[i].inCombat = false;
						}
					}
				}
				else if(code == KeyEvent.VK_1 || code == KeyEvent.VK_2 || code == KeyEvent.VK_3 || code == KeyEvent.VK_4){
					gp.player.life--;
					gp.player.inCombat = false;
					gp.gameState = gp.playState;
				}
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
		
	}

}
