package com.tfnico.examples.guava.eventbus;

import com.google.common.eventbus.EventBus;

public class BusThread implements Runnable{
    private final EventBus bus;

    public BusThread(EventListener listener) {
        this.bus = new EventBus();
        bus.register(listener);
    }

    public void run() {
        bus.post(new OurTestEvent(100));
    }


}
