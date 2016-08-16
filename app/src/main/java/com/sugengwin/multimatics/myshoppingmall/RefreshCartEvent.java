package com.sugengwin.multimatics.myshoppingmall;

/**
 * Created by Multimatics on 22/07/2016.
 */
public class RefreshCartEvent {
    private String eventMessage;

    private String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }
}
