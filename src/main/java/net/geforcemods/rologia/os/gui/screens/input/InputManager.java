package net.geforcemods.rologia.os.gui.screens.input;

import java.util.ArrayList;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;
import net.geforcemods.rologia.os.gui.screens.Screen;

public class InputManager {
	
	private RologiaOS OS;

	private ArrayList<Boolean> decisions = new ArrayList<Boolean>();
	private ArrayList<String> responses = new ArrayList<String>();
	
	public InputManager(RologiaOS os) {
		OS = os;
	}

	public void handleMouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		boolean clickedOnComponent = false;
		Screen screen = OS.getCurrentScreen();

		int size = OS.getCurrentScreen().getComponents().size();
		for(int i = 0; i < size; i++){
			if(OS.isScreenBeingSwapped()) return;

			ScreenComponent component = screen.getComponents().get(i);

			if(component.isVisible() && component.isMouseHoveringOver(screen.getMousePosition())) {				
				if(component.mouseClick(screen.getMousePosition(), mouseButtonClicked)) {
					if(screen.getFocusedComponent() == component)
						screen.setFocusedComponent(null);
					else
						screen.setFocusedComponent(component);
				}

				screen.onComponentClicked(component, screen.getMousePosition(), mouseButtonClicked);
				clickedOnComponent = true;
			}
		}

		if(!clickedOnComponent)
			screen.setFocusedComponent(null);
	}

	public void handleKeyTyped(char key, int keyCode) {
		Screen screen = OS.getCurrentScreen();

		int size = screen.getComponents().size();
		for(int i = 0; i < size; i++){
			if(OS.isScreenBeingSwapped()) return;

			if(screen.hasFocusedComponent() && screen.getFocusedComponent().acceptsKeyboardInput())
				screen.getComponents().get(i).keyTyped(key, keyCode);
		}
	}

	public void requestDecision(Screen returnScreen, String prompt) {
		OS.setScreen(new InputYesNoScreen(OS, OS.getCurrentScreen().getPosition(), returnScreen, prompt));
	}

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
