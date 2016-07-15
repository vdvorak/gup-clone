package ua.com.itproekt.gup.server.api.rest.dialogues;

import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.PrivateMessage;

public class SocketResponse {

    private PrivateMessage content;
    private Dialogue dialogue;

    public SocketResponse(PrivateMessage content) {
        this.content = content;
    }

    public SocketResponse(Dialogue dialogue) {
        this.dialogue = dialogue;
    }

    public PrivateMessage getContent() {
        return content;
    }
    public Dialogue getDialogue() {
        return dialogue;
    }

}
