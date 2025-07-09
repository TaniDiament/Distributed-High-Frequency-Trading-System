package com.bank.trading.data;

import java.net.InetSocketAddress;
import java.net.URI;


/**
 * Backup data service that receives log data form the Lamport clock service
 * and then backs it up in SQL database.
 * Also retrieves the backup data from the long term storage.
 */
public class BackupData implements Runnable {

    private URI storageAddress;
    private InetSocketAddress LamportAddress;
    private InetSocketAddress address;

    /**
     * Constructor to initialize the backup data service.
     * @param storage the URI of the long term storage database.
     * @param address the address of the backup data service.
     * @param LamportAddress the address of the Lamport clock service.
     */
    public BackupData(URI storage, InetSocketAddress address, InetSocketAddress LamportAddress) {
        // Backup data to storage
        this.storageAddress = storage;
        this.address = address;
        this.LamportAddress = LamportAddress;
    }

    /**
     * Set the address of the SQL Database.
     * @param storageAddress the URI of the long term storage database.
     */
    public void setStorageAddress(URI storageAddress) {
        this.storageAddress = storageAddress;
    }

    /**
     * Backup log data to long term database storage.
     * Convert from event logs to SQL format
     * Send the data to the database at storageAddress.
     * @param logs the byte array of the event logs to back up.
     */
    public void backupData(byte[] logs) {
        // Backup data
        // Convert logs to Events
        // Send to long term storage via SQL query to storageAddress
    }

    /**
     * Retrieve backup data from long term storage.
     * @param startTime the start time of the data to retrieve.
     * @param endTime the end time of the data to retrieve.
     * @return byte array of the backup data in event log format.
     */
    public byte[] getBackupData(Long startTime, Long endTime) {
        // Retrieve backup data from storageAddress with SQL query
        // SELECT * FROM events WHERE time >= startTime AND time <= endTime
        // Convert to byte array of event logs
        // Return the byte array of the event logs
        return null;
    }
    /**
     * Run the backup data service keep connection open to receive data
     * and send to long term storage
     * Message includes method calling and parameters
     * Also accept incoming requests to retrieve backup data.
     */
    @Override
    public void run() {
        // UDP socket to receive data from Lamport clock service
        // message
        //loop:
        // while (true) {
        //     // Accept incoming requests to retrieve backup data via TCP
        //     // Send the data to the client via getBackupData
        //     // Receive data from Lamport clock service via UDP in batches
        //     // Send data to long term storage
    }
}
