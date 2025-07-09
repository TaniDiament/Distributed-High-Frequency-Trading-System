package com.bank.trading.web;

import com.bank.trading.Event;
import com.bank.trading.Order;

import java.net.InetSocketAddress;
import java.util.Map;
import com.sun.net.httpserver.HttpServer;

/**
 * This class is responsible for the web interface.
 * The web interface is used to interact with the trading system
 * and view system-wide data.
 * Will use netty http server to access
 * This is run on the same server as the Lamport clock service.
 */
public class WebInterface implements Runnable{
    private InetSocketAddress socketAddress;
    private InetSocketAddress http;
    private Map<String, InetSocketAddress> services;
    private HttpServer ourServer;
    private boolean running;

    /**
     * This method is responsible for starting the web interface HTTP server.
     * It will run the netty http server to handle requests.
     * Every method has HTTp endpoint.
     */
    @Override
    public void run() {
        //run netty http server - this is a simple http server in java replace with netty for production
        // separate endpoint for each method

//        ourServer = HttpServer.create(http, 10);
//        ourServer.createContext("/execute", new HttpHandler() { passes in order
            // execute the order
//        ourServer.createContext("/setAlg", new HttpHandler() {    passes in alg executable
//
//        ourServer.createContext("/getAlg", new HttpHandler() {
//
//        ourServer.createContext("/setControl", new HttpHandler() { passes in control parameters
//
//        ourServer.createContext("/getControl", new HttpHandler() {
//
//        ourServer.createContext("/getData", new HttpHandler() { get data method passes in start and end time
//
//        ourServer.createContext("/shutDown", new HttpHandler() { stop server
//
//
//        ourServer.start();
//       while (running) {
//            //keep the server running
//            //listen for any updates from the Lamport clock service if addresses change
//        }
//        ourServer.stop(0);
    }

    /**
     * Constructor to initialize the web interface with an address.
     * @param address the user for the web interface.
     */
    public WebInterface(InetSocketAddress address, InetSocketAddress http, Map<String, InetSocketAddress> services) {
        this.socketAddress = address;
        this.services = services;
        this.http = http;
        this.running = true;
    }

    /**
     * This method is responsible for executing a manual order.
     * @param order the order to be executed.
     * @return true if the order was executed successfully, false otherwise.
     */
    public boolean execute(Order order) {
        //get address of the Trade Execution service form the map
        //make a new UDP socket to the address
        //send message over UDP to Trade Execution service
        //to execute the order
        //wait for the response from the Trade Execution service
        //return true if the order was executed successfully, false otherwise.
        return true;
    }

    /**
     * This method is responsible for changing the algorithm executable.
     * @param algExecutable the new algorithm executable to be set.
     */
    public boolean setAlg(byte[] algExecutable) {
        Event event = new Event(Event.Type.ALGORITHM_CHANGE, "Algorithm executable changed");
        event.setBinaryData(algExecutable);
        //send event to the Lamport clock service
        //wait for time to change
        //validate by calling getAlgorithms
        //if changed return true
        //else return false
        return true;
    }

    /**
     * This method is responsible for setting the control parameters for the trading algorithms.
     * @param controls the control parameters to be set.
     */
    public boolean setControl(byte[] controls) {
        Event event = new Event(Event.Type.CONTROL_CHANGE, "Control parameters changed");
        event.setBinaryData(controls);
        //send event to the Lamport clock service
        //wait for time to change
        //validate by calling getControls
        //if changed return true
        //else return false
        return true;
    }

    /**
     * This method is responsible for getting the current controls.
     * @return the current controls.
     */
    public byte[] getControls() {
        //get the current controls from the Lamport clock service
        //return the controls
        return null;
    }

    /**
     * This method is responsible for getting the current algorithms.
     * @return the current algorithms.
     */
    public byte[] getAlgorithms() {
        //Open UDP socket to the Lamport clock service
        //get the current algorithms from the Lamport clock service
        //return the algorithms
        return null;
    }


    /**
     * This method is responsible for getting the data for a specific machine.
     * @param start the start timestamp for the data.
     * @param end the end timestamp for the data.
     * @return the data for the specified machine and time range.
     */
    public byte[] getData(Long start, Long end) {
        InetSocketAddress address = services.get("BDBS");
        //Open TCP socket to the address
        //send message to the BDBS service
        //get the data from the BDBS service
        //return the data
        return null;
    }
}
