package com.afa.devicer.dispatcher.sagas;

public class SomeCommand {
    private String commandData;

    public SomeCommand(String commandData) {
        this.commandData = commandData;
    }

    public String getCommandData() {
        return commandData;
    }
}

