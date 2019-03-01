package net.geforcemods.rologia.os.apps.events;

public class AppEventReceiveMessage extends AppEvent {
	
	public String sender;
	public String type;
	public String message;

	public AppEventReceiveMessage(String sender, String type, String message) {
		this.sender = sender;
		this.type = type;
		this.message = message;
	}

	@Override
	public AppEventType getEventType() {
		return AppEventType.RECEIVE_MESSAGES;
	}

}
