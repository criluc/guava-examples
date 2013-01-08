package com.tfnico.examples.guava.eventbus;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class EventBusTest {

    @Test
    public void shouldReceiveEvent() throws Exception {

        // given
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        // when
        eventBus.post(new OurTestEvent(200));

        // then
        assertEquals(listener.getLastMessage(), 200);
    }

    @Test
    public void shouldReceiveMultipleEvents() throws Exception {
     
        // given
        EventBus eventBus = new EventBus("test");
        MultipleListener multiListener = new MultipleListener();
     
        eventBus.register(multiListener);
     
        // when
        eventBus.post(new Integer(100));
        eventBus.post(new Long(800));
     
        // then
        assertEquals(multiListener.getLastInteger(), Integer.valueOf(100));
        assertEquals(multiListener.getLastLong(), Long.valueOf(800));
    }

    // we can test that though the event bus post event in different thread, the
    // listener can receive the event
    @Test
    public void multiThreadTest() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        EventListener listener = new EventListener();
        BusThread busThread = new BusThread(listener);

        service.submit(busThread);
        Thread.sleep(500); // give busThread to run
        assertEquals(listener.getLastMessage(), 100);
    }
}
