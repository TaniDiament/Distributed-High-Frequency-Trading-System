package com.bank.trading.execution;

import com.bank.trading.Event;
import com.bank.trading.Order;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

/**
 * Called by person or Algo to submit trade to market
 * This class will start a smart order router to route the order to the market on
 * another Thread in this server.
 * The service is bound by the controls executable that is passed to it.
 * This prevents extreme failures in the system and loss of money.
 * The service uses Zookeper algorithm for leader election.
 * The leader distributes the work to the other instances based of tickers.
 * Uses heartbeats to check if the leader is alive and leader will know if followers are alive.
 * More servers can be added to the cluster by sysadmin if we need more capacity/scale
 */
public class ExecutionService implements Runnable {

    private InetSocketAddress address; // Address for the execution service
    private InetSocketAddress leader;
    private InetSocketAddress LamportAddress; // Address for the Lamport clock service
    private byte[] controlsExecutable;
    private long[] heartBeatClock;
    private List<InetSocketAddress> peers; // List of peer addresses for the execution service
    private Map<String, InetSocketAddress> tickerMap;
    private Map<Order.Exchange, InetSocketAddress> exchangeAddresses; // Map of exchange addresses
    private List<Event> log; // Log for the execution service
    boolean isLeader = false; // Flag to check if this instance is the leader


    /**
     * Constructor for the execution service.
     * @param address The address for the execution service.
     * @param LamportAddress The address for the Lamport clock service.
     * @param controlsExecutable The controls executable for the execution service.
     * @param peers List of peer addresses for the execution service.
     */
    public ExecutionService(InetSocketAddress address, InetSocketAddress LamportAddress,
                           byte[] controlsExecutable, List<InetSocketAddress> peers, Map<Order.Exchange, InetSocketAddress> exchangeAddresses) {
        this.address = address;
        this.LamportAddress = LamportAddress;
        this.controlsExecutable = controlsExecutable;
        this.peers = peers;
        this.exchangeAddresses = exchangeAddresses;
    }

    /**
     * Main execution loop for the execution service.
     */
    @Override
    public void run() {
        //run execution service
//        while (true) {
//            run leader election if failure
//            take any new info from lamport and add to log and change address if needed
//            if (isLeader) {
//                leader();
//            } else {
//                follower();
//            }

//            // Check if the leader is alive
//            // If not, start a new leader election
//            // This is done via heartbeat messages
        //    // If new lamport address is received, change it and anticipate
        //    // the new LCS pulling for log data
//        }
    }

    /**
     * This method is the main logic for the leader of the execution service.
     * Accept incoming connections from lamport for orders and send to workers.
     */
    private void leader() {
        // while (true) {
        //     check if new server was added to the cluster
        //     if so, update the ticker map to include the new server
        //      accept events from lamport add to log
        //     accept incoming connections for orders
        //     send orders to worker instances
        //     send heartbeat messages to check all workers are alive
    }

    /**
     * This method is the main logic for the follower of the execution service.
     * Accept incoming connections from leader for orders and execute.
     */
    private void follower() {
        // while (true) {
        //      accept events from lamport add to log
        //     accept incoming connections for orders
        //     execute orders and send complete order event to the lamport clock
        //     send heartbeat messages to tell leader you are alive
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
     * Send order to worker instances.
     * This is only used by the leader instance to distribute the work.
     * @param order the order to be sent to the worker instances.
     */
    private void sendOrderToWorker(Order order) {
        // send the order to the next worker instances using round-robin scheduling
        // We will know if order is executed from lamport clock
    }

    /**
     * Submit a trade order to the market.
     * This method will route then execute the order.
     * @param order the order to be submitted.
     */
    public boolean submitTrade(Order order) {
        // Route the order to the market
        // call executeOrder to execute the order(s)
        // If the order is executed successfully, return true
        return false;
    }

    /**
     * Set new controls executable for the execution service.
     * @param controlsExecutable the new controls executable to be set.
     */
    public void setControlsExecutable(byte[] controlsExecutable) {
        this.controlsExecutable = controlsExecutable;
    }

    /**
     * Get the current controls executable.
     * @return byte array of the controls executable.
     */
    public byte[] getControlsExecutable() {
        return controlsExecutable;
    }

    /**
     * Process the order and route it to the market.
     * Does this via call to Smart Order Router
     * @param order the order to be processed.
     */
    private List<Order> routeOrder(Order order) {
        // Send the order to the smart order router
        // The smart order router will route the order to the market
        // based on the routing algorithm
        return null;
    }

    /**
     * Execute the order in the market.
     * This method will execute the order based on the routing.
     * @param order the order to be executed.
     * @return true if the order was executed successfully, false otherwise.
     */
    private boolean executeOrder(Order order) {
        // Validate the order with the controls executable
        // Connect to the market via TCP socket
        // Send the order to the market
        // Wait for the response from the market
        // If the order is executed successfully, return true
        return true;
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
     * Change the current Lamport address.
     * @return the Lamport address.
     */
    public void changeLamportAddress(InetSocketAddress newLamportAddress) {
        this.LamportAddress = newLamportAddress;
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
