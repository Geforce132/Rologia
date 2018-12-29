package net.geforcemods.rologia.os.gui.screens.input;

import java.util.ArrayList;

public class InputManager {
	
	private ArrayList<Boolean> decisions = new ArrayList<Boolean>();
	private ArrayList<String> responses = new ArrayList<String>();
	
	public boolean getUserChoice() {
		boolean choice = decisions.get(0);
		decisions.remove(0);
		return choice;
	}
	
	public String getUserResponse() {
		String response = responses.get(0);
		responses.remove(0);
		return response;
	}
	
	public void addChoice(boolean choice) {
		decisions.add(choice);
	}
	
	public void addResponse(String input) {
		responses.add(input);
	}
	
	public boolean hasChoice() {
		return !decisions.isEmpty();
	}
	
	public boolean hasResponse() {
		return !responses.isEmpty();
	}

}
