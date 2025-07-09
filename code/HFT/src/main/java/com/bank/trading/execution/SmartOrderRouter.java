package com.bank.trading.execution;

import com.bank.trading.Order;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Defines a SmartOrderRouter that is responsible for routing orders
 * decide which exchange is the best to execute the order on and how
 * fast or slow to execute to reduce market slip and to hide out strategy from our competitors
 * This runs on the ExecutionService server.
 */
public class SmartOrderRouter implements Runnable {
    private InetSocketAddress socketAddress;
    private InetSocketAddress LamportAddress;

    /**
     * Constructor to initialize the SmartOrderRouter with a socket address.
     *
     * @param address The socket address of server.
     */
    public SmartOrderRouter(InetSocketAddress address, InetSocketAddress LamportAddress) {
        this.socketAddress = address;
        this.LamportAddress = LamportAddress;
    }

    /**
     * Starts the smart order router.
     * Takes in the order and calculates the best way to route the order.
     */
    @Override
    public void run() {
        //while true {
        // receive next UDP message
        // get order from message
        // send to getRoutedOrder
        // send routed order to execution service
        // }
    }

    /**
     * Routes the order to the best exchange based on the current market conditions.
     *
     * @param order The order to be routed.
     */
    private List<Order> getRoutedOrder(Order order) {
        //calculate the best exchange to route the order to
        //base on the current market conditions to avoid slippage
        //return list of orders to be executed on the exchange
        //can be multiple if we have to split the order
        return null;
    }
}
