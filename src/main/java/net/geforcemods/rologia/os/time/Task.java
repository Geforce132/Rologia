package net.geforcemods.rologia.os.time;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.screens.Screen;

public abstract class Task {
	
	private RologiaOS os;
	
	protected int sleepCounter = getTickDelay();
	
	public Task(RologiaOS rologia) {
		os = rologia;
	}
	
	public void decreaseSleepCounter() {
		sleepCounter--;
		
		if(sleepCounter <= 0)
		{
			execute(os, os.getCurrentScreen());
			
			if(shouldRepeat())
				sleepCounter = getTickDelay();
		}
	}
	
	public abstract void execute(RologiaOS os, Screen currentScreen);
	
	public abstract boolean shouldRepeat();
	
	public abstract int getTickDelay();

}
