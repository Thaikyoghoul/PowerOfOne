package entity;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

	

public class NPC_Sibling extends Entity {
	
	
	
	public NPC_Sibling(GamePanel gp) {
		super(gp);
		
		direction = "down"; 
		speed = 1;
		
		
	
		
		getNPCImage();
		setDialogue();
		setChallenges();
	}
	
	public void getNPCImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
			upstill = ImageIO.read(getClass().getResourceAsStream("/player/player_up_still.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
			downstill = ImageIO.read(getClass().getResourceAsStream("/npc/Sibling.png"));
			
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
	public void setDialogue() {
		
		dialogues[0] = "I was destined for the throne, don't take this \naway from me.";
		dialogues[1] = "Your mother was a hamster.";
		dialogues[2] = "Back off before I hurt you.";
		dialogues[3] = "Ara ara~";
		dialogues[4] = "Mom said it's my turn to be king.";
	}
	
	public void speak() {
		
		gp.player.inCombat = true;
		inCombat = true;
		int num = (int)(Math.random()*6);
		gp.ui.currentDialogue = dialogues[(int)(Math.random()*5)];
		gp.ui.currentChallenge = challenges[num];
		gp.ui.currentAnswer = answers[num];
	}
	
	public void setChallenges() {
		challenges[0] = "Type of government where one person or small group hold all \nthe power: \r\n" + 
				"A. Monarchy 	B. Republic 	C. Dictatorship 	D. Direct democracy";
		
		challenges[1] = "What type of government has a king or queen that holds all \nthe power?\r\n" + 
				"A. Monarchy 	B. Direct democracy 	C. Dictatorship 	D. Republic";
		
		challenges[2] = "Type of government where all voters in a community meet to \nmake laws and decide actions? \r\n" + 
				"A. Direct democracy 	B. Theocracy 	C. Dictatorship 	D. Republic";
		
		challenges[3] = "Type of government has complete control over the lives of \nits citizens:\r\n" + 
				"A. Monarchy 	B. Totalitarian 	C. Absolute 	D. Republic";
				
		challenges[4] = "What type of government elects representatives to make all \ngovernment decisions?\r\n" + 
				"A. Theocracy 	B. Direct democracy 	C. Dictatorship 	D. Republic";
		
		challenges[5] = "Type of government by divine guidance or by officials who \nare regarded as divinely guided?\r\n" + 
				"A. Monarchy 	B. Totalitarian 	C. Theocracy 	D. Absolute ";
		
		answers[0] = 3;
		answers[1] = 1;
		answers[2] = 1;
		answers[3] = 2;
		answers[4] = 4;
		answers[5] = 3;
		
	}







}
