package com.bank.trading.data;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * Replay service to catch booted up service or lagging service
 * to the current state of the system.
 * This service is run as a Thread on the Lamport Clock server.
 */

public class ReplayService implements Runnable {

    /**
     * This method is responsible for running the Replay service main exection loop.
     */
    @Override
    public void run() {
        //while(true) {
        //    //receive request from the Lamport clock to catch a certain subscriber up-to-date
        //    //call getLog with the start time and subscriber address
    }

    private File logFile;
    private InetSocketAddress address;

    /**
     * Constructor to initialize the replay service with the log file.
     *
     * @param logFile The file containing the logged events.
     */
    public ReplayService(File logFile, InetSocketAddress address) {
        this.logFile = logFile;
        this.address = address;
    }

    /**
     * Get the log data starting from a specific time for a given subscriber.
     * @param startTime
     * @param subscriber
     * @return byte array of the log data in event log format.
     */
    public byte[] getLog(long startTime, URI subscriber) {
        //check the earliest time that the log file has
        //if the start time is less than the earliest time
        //    // retrieve the old data from the BackupData service
        // Start sending the log data to the subscriber until he is caught up to current time
        return null;
    }
}
