package com.afa.devicer.dispatcher.sagas;

public class ExampleEvent {
    private String eventData;

    public ExampleEvent(String eventData) {
        this.eventData = eventData;
    }

    public String getEventData() {
        return eventData;
    }
}

