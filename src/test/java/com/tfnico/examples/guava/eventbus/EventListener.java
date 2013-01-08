package com.tfnico.examples.guava.eventbus;

import com.google.common.eventbus.Subscribe;

public class EventListener {
    
    public int lastMessage = 0;
 
    @Subscribe
    public void listen(OurTestEvent event) {
        lastMessage = event.getMessage();
    }
 
    public int getLastMessage() {
        return lastMessage;
    }
}
