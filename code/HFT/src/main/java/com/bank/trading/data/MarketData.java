package com.bank.trading.data;

import com.bank.trading.Event;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;

/**
 * This service takes market data from bloomberg and streams to lamport service.
 * This service is run as a cluster with one active leader, the others on standby in case of failure.
 * This part of the system does not need to scale as it is just streaming data from bloomberg.
 */
public class MarketData implements Runnable{
    private InetSocketAddress address;
    private URI bloombergURI;
    private InetSocketAddress lamportAddress;
    private File log;
    private InetSocketAddress leaderAddress;
    private List<InetSocketAddress> peers; // List of peer addresses for the market data service

    /**
     * Constructor to initialize the market data service.
     * @param address the address of this service.
     * @param bloombergURI the URI of the bloomberg service.
     * @param LamportAddress the address of the lamport service.
     * @param peers the list of peer addresses for the market data service.
     */
    public MarketData(InetSocketAddress address, URI bloombergURI, InetSocketAddress LamportAddress, List<InetSocketAddress> peers) {
        this.address = address;
        this.bloombergURI = bloombergURI;
        this.lamportAddress = LamportAddress;
        this.log = new File("marketdata.log"); // log file for market data
        this.peers = peers; // Initialize the list of peer addresses
    }

    /**
     * This method is responsible for streaming data from bloomberg to lamport service.
     */
    @Override
    public void run() {
        //while (true) {
        // check if there is a leader/alive
        // if not, elect a new leader via leaderElection()
        // if we are the leader, call lead()
        // else watch for lamport address changes
        // if we are not the leader, listen for heartbeats from the leader
        // if we do not hear from the leader, we will elect a new leader
        //    // If new lamoprt address is received, change it and anticipate
        //    // the new LCS pulling for log data
        // }
    }

    /**
     * Elect leader server for the service
     */
    private void leaderElection() {
        //using zookeeper where the highest address wins
        //Each server in the peer list sends messages to all other on current vote and updates if their vote is superseded
        //once one server has majority, it becomes leader
        //let lamport know who the leader is so system can be updated via UDP
    }

    /**
     * Main logic of service
     */
    private void lead(){
        //while (true) {
        // get market data from bloomberg via bloombergURI udp messages
        // call addMarketData() to add the data to the system
        // also accept from lamport service to update the lamport address
        // send heartbeat messages to the peers
        // }
    }

    /**
     * Change the lamport address to a new one.
     * This is called when the lamport leader changes.
     * @param address the new lamport address.
     */
    public void changeLamportAddress(InetSocketAddress address) {
        this.lamportAddress = address;
    }

    /**
     * Add market data to the system.
     * @param marketData the market data to be added.
     */
    public void addMarketData(String marketData) {
        //Write the market data to the log file
        // Event event = new Event(MARKET_DATA, marketData);
        // event.setTimestamp(System.currentTimeMillis());
        // send the market data to the lamport service via sendMarketData(event)
    }

    /**
     * Send market data to the lamport service.
     * This will be called to send the latest market data.
     * @param marketData the market data event to be sent.
     */
    public void sendMarketData(Event marketData) {
        // byte[] data = marketData.toBinary();
        // send the data to the lamport service via UDP
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
