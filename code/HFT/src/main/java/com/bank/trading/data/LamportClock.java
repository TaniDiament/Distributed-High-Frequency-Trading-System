package com.bank.trading.data;

import com.bank.trading.Event;
import com.bank.trading.web.WebInterface;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Lamport clock service to keep track of the logical time of the system.
 * All events that take place on any component of the HFTS are streamed
 * to the LCS which timestamps them with a long starting from 1.
 * The LCS immediately writes it to an in memory log file and adds to queue which
 * will be used by the UDP multicast and Replay services which run on this server as well as the webInterface.
 * The LCS is responsible for sending the most recent log data to the BackupData
 * This service is run as a cluster with one active leader, the others on standby in case of failure.
 * Uses heartbeats to check if the leader is alive via the event stream.
 */

public class LamportClock implements Runnable {

    private long clockNumber = 1;
    private InetSocketAddress LamportAddress;
    private UDPMulticast ourCast;
    private ReplayService replayService;
    private File logfile;
    private boolean leader = false;
    private List<InetSocketAddress> peers;
    private Map<String, InetSocketAddress> subscribers;
    private WebInterface webInterface;
    private LinkedBlockingQueue<Event> queue;
    private byte[] currentAlgExecutable;
    private byte[] currentControlsExecutable;

    /**
     * This method is responsible for running the Lamport clock service.
     * This also starts the UDP multicast and Replay services.
     */
    @Override
    public void run() {
        //Leader election
        //if leader:
        //start the UDP multicast service, Replay service and web interface
        //accept incoming requests from all the services
        //if event is received, call submitEvent
        //if request is received for replay forward to replay service
        //if address change request is received, call updateSubscriber
        //if follower:
        //keep receiving log from leader, if there is a leader failure because there
        // are no more messages start election.
        //We treat the messages from the leader as a heartbeat if after 5 sec no messages then
        //start election
    }

    /**
     * Constructor to initialize the Lamport clock service.
     * @param logfile the file to write the log data.
     * @param address the address of this service.
     * @param peers the list of peers in the cluster.
     * @param subscribers the list of subscribers to this service and their names.
     */
    public LamportClock(File logfile, InetSocketAddress address, List<InetSocketAddress> peers, Map<String, InetSocketAddress> subscribers) {
        this.logfile = logfile;
        this.LamportAddress = address;
        this.subscribers = subscribers;
        this.peers = peers;
        queue = new LinkedBlockingQueue<>();
        ourCast = new UDPMulticast(subscribers.values().stream().toList(), queue);
        replayService = new ReplayService(logfile, LamportAddress);
        //webInterface = new WebInterface(address, LamportAddress, subscribers as services map);
    }

    /**
     * add or update a subscriber to the Lamport clock service.
     * @param name the name of the subscriber.
     * @param subscriber the address of the subscriber to add.
     */
    public void updateSubscriber(String name, InetSocketAddress subscriber) {
        subscribers.put(name, subscriber);
    }

    /**
     * remove subscriber from the Lamport clock service.
     * @param subscriber the address of the subscriber to remove.
     */
    public void removeSubscriber(InetSocketAddress subscriber) {
        for (Map.Entry<String, InetSocketAddress> entry : subscribers.entrySet()) {
            if (entry.getValue().equals(subscriber)) {
                subscribers.remove(entry.getKey());
                break;
            }
        }
    }

    /**
     * Used to get the address of a service.
     * @param serviceName
     * @return the address of the service.
     */
    public InetSocketAddress getServiceAddress(String serviceName) {
        return subscribers.get(serviceName);
    }

    /**
     * Get the alg executable that the system is currently using.
     * @return the alg executable.
     */
    public byte[] getCurrentAlgExecutable() {
        return currentAlgExecutable;
    }

    /**
     * Set the alg executable that the system should use.
     * @param currentAlgExecutable the alg executable to set.
     */
    private void setCurrentAlgExecutable(byte[] currentAlgExecutable) {
        this.currentAlgExecutable = currentAlgExecutable;
    }

    /**
     * Get the controls executable that the system is currently using.
     * @return the controls executable.
     */
    public byte[] getCurrentControlsExecutable() {
        return currentControlsExecutable;
    }

    /**
     * Set the controls executable that the system should use.
     * @param currentControlsExecutable the controls executable to set.
     */
    private void setCurrentControlsExecutable(byte[] currentControlsExecutable) {
        this.currentControlsExecutable = currentControlsExecutable;
    }

    /**
     * Elect leader server for the service
     * This is called on boot and in the case of failure of the current leader.
     */
    private void leaderElection() {
        //Election happens between us and the peers list
        //using zookeeper where the most up-to-date wins, highest address ties
        //Each server sends messages to all other on current vote and updates if their vote is superseded
        //once one server has majority, it becomes leader
        //let system know who the leader is so system can be updated via UDP
    }

    /**
     * Submit and event to the service.
     * This will add the event to the log with the current clock number.
     * This will trigger the multicast service to send the event to all subscribers.
     * @param event the event to submit.
     */
    private void submitEvent(Event event) {
        event.setTimestamp(clockNumber);
        clockNumber++;
        //add event to log file
        queue.add(event);
    }

    /**
     * Add a server to the cluster of peers.
     * Done by sysadmin when we need a new server.
     * @param peer
     */
    public void addPeer(InetSocketAddress peer) {
        peers.add(peer);
    }
}
