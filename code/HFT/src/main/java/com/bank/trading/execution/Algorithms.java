package com.bank.trading.execution;

import com.bank.trading.Event;
import com.bank.trading.Order;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for the execution of the trading algorithms.
 * Data is streamed from lamport service and is used to execute the trading algorithms.
 * The decision of the system is sent to the execution service.
 * Uses heartbeats to check if the leader is alive.
 * Leader sends data to worker instances based on the tickers.
 * More servers can be added to the cluster by sysadmin if we need more capacity/scale
 */

public class Algorithms implements Runnable {

    private List<Event> data;
    private byte[] algExecutable;
    private byte[] controlsExecutable;
    private long[] heartBeatClock;
    private InetSocketAddress socketAddress;
    private List<InetSocketAddress> peers; // List of peer addresses for the lamport service
    private Map<String, InetSocketAddress> tickerMap; // Map of tickers to their respective socket addresses
    private InetSocketAddress lamportSocketAddress;
    private InetSocketAddress leaderAddress;
    private InetSocketAddress executionAddress;
    private boolean isLeader = false; // Flag to check if this instance is the leader

    /**
     * Constructor to initialize the trading algorithms with the executable and machine id.
     * @param algExecutable The executable for the trading algorithms.
     * @param lamportSocketAddress The socket address for the lamport service.
     * @param socketAddress The socket address for the execution service.
     * @param executionAddress The socket address for the execution service.
     * @param peers List of peer addresses for the lamport service.
     */
    public Algorithms(byte[] algExecutable, InetSocketAddress lamportSocketAddress, InetSocketAddress socketAddress, List<InetSocketAddress> peers, InetSocketAddress executionAddress)  {
        this.algExecutable = algExecutable;
        data = new ArrayList<>();
        this.lamportSocketAddress = lamportSocketAddress;
        this.socketAddress = socketAddress;
        this.executionAddress = executionAddress;
        this.peers = peers;
    }

    @Override
    public void run() {
        //while (true) {
        //    leaderElection();
        //    if (isLeader) {
        //        lead();
        //    } else {
        //        follower();
        //    }
        //    // Check if the leader is alive
        //    // If not, re-elect a new leader
        //    // If new lamport address is received, change it and anticipate
        //    // the new LCS pulling for log data
    }

    /**
     * Cluster leader instance logic.
     */
    private void lead() {
        //Request log of last week of data from the lamport service
        //Calculate how to split up the tickers among the followers
        //Set the map of tickers to their respective socket addresses
        //while (true) {
        //    check if new server was added to the cluster
        //    if so, update the ticker map to inlude the new server
        //    accept data from the lamport service
        //    send data to the worker instances based on the ticker map via sendEventToWorker
        //    check if workers are alive
    }

    /**
     * Cluster follower instance logic.
     */
    private void follower() {
        //while (true) {

        //    make sure leader still alive
        //    accept data event from udp from leader
        //    add to data list via addData
        //    calculate the algorithm on the data event via calculate

    }

    /**
     * Main execution loop for the trading algorithms.
     * This method will be called by a follower instance to execute the algorithm on
     * the given event.
     * @param event The event to be processed by the trading algorithms.
     */
    private void calculate(Event event) {
        // Execute the algorithm on the event
        // Pass through controls executable to make sure it is okay decision
        // if decision is okay, send order to execution service if trade required
    }

    /**
     * This method is called by the follower instance to send the order to the execution service.
     * @param order The order to be executed.
     * @return true if the order was successfully sent, false otherwise.
     */
    private boolean sendToExecutionService(Order order) {
        return true;
    }

    /**
     * Elect leader server for the service
     */
    private void leaderElection() {
        //using zookeeper where most up-to-date wins, highest address breaks tie
        //Each server sends messages to all other on current vote and updates if their vote is superseded
        //once one server has majority, it becomes leader
        //let lamport know who the leader is so system can be updated via UDP
    }

    /**
     * Send heartbeat messages to check if the leader or follower is alive.
     * This is done via direct socket communication.
     */
    private void heartbeat() {
        // Implementation to send heartbeat messages to check if the leader or follower is alive
        // done via direct socket communication
        // pick random peer and send heartbeat with heartbeat clock as gossip
    }

    /**
     * Send event to worker instances.
     * This is only used by the leader instance to distribute the work.
     * @param event The event to be sent to the worker instances.
     */
    private void sendEventToWorker(Event event) {
        //Send event to worker based off the ticker map
    }

    /**
     * Add data to the trading service
     * @param event The event to be added to the trading service.
     */
    public void addData(Event event) {
        data.add(event);
    }

    /**
     * Change the address of the lamport service.
     * @param newLamportSocketAddress the new socket address for the lamport service.
     */
    public void changeLamportAddress(InetSocketAddress newLamportSocketAddress) {
        this.lamportSocketAddress = newLamportSocketAddress;
    }

    /**
     * Change the address of the execution service.
     * @param newExecutionAddress the new socket address for the execution service.
     */
    public void changeExecutionAddress(InetSocketAddress newExecutionAddress) {
        this.executionAddress = newExecutionAddress;
    }

    /**
     * Change the executable for the trading algorithms.
     * @param algExecutable the new executable for the trading algorithms.
     */
    public void changeAlg(byte[] algExecutable) {
        this.algExecutable = algExecutable;
    }

    /**
     * Set the controls for the trading algorithms.
     * @param control the control parameters to be set for the trading algorithms.
     */
    public void setControl(byte[] control) {
        controlsExecutable = control;
    }

    /**
     * Get the executable for the controls.
     * @return the current controls executable.
     */
    public byte[] getControls() {
        return controlsExecutable;
    }

    /**
     * Get the executable for the algorithm.
     * @return the current algorithm executable.
     */
    public byte[] getAlg() {
        return algExecutable;
    }

    /**
     * Add a server to the cluster of peers.
     * Done by sysadmin when we need a new server or to scale to bigger market.
     * @param peer
     */
    public void addPeer(InetSocketAddress peer) {
        peers.add(peer);
    }

}
