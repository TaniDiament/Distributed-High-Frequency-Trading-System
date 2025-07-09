package com.bank.trading.data;

import com.bank.trading.*;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * This class is responsible for the UDP multicast.
 * The UDP multicast is used to stream data to all parts of the system.
 * This is run as a thread on the Lamport service.
 */
public class UDPMulticast implements Runnable {

    private List<InetSocketAddress> subscribers;
    private LinkedBlockingQueue<Event> queue;

    /**
     * Constructor to initialize the UDP multicast with a list of subscribers.
     *
     * @param subscribers The list of subscribers to send the data to.
     * @param queue The queue to get the data from the Lamport Clock.
     */
    public UDPMulticast(List<InetSocketAddress> subscribers, LinkedBlockingQueue<Event> queue) {
        this.subscribers = subscribers;
        this.queue = queue;
    }

    /**
     * Add a new subscriber to the list of subscribers.
     *
     * @param subscriber The address of the new subscriber.
     */
    public void addSubscriber(InetSocketAddress subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Remove a subscriber from the list of subscribers.
     *
     * @param subscriber The address of the subscriber to be removed.
     */
    public void removeSubscriber(InetSocketAddress subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * This method is responsible for running the UDP multicast service.
     * It continuously polls the queue for new events and sends them to all subscribers.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Event event;
            try {
                event = queue.poll(500, TimeUnit.MICROSECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (event == null) {
                continue;
            }
            for (InetSocketAddress subscriber : subscribers) {
                // Send the event to the subscriber over UDP
                // Each iteration makes a Java Virtual Thread to do this in parallel to reduce latency
            }
        }
    }

    public void shutdown() {
        Thread.currentThread().interrupt();
    }
}
