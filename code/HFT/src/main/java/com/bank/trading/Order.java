package com.bank.trading;

/**
 * Represents an order to market. This order is Sent from the Algo to the Smart Order Router to the Execution Service.
 */
public class Order {

    /**
     * Represents the type of the order.
     * MARKET_BUY: Buy at market price.
     * MARKET_SELL: Sell at market price.
     * LIMIT_BUY: Buy at a specified limit price.
     * LIMIT_SELL: Sell at a specified limit price.
     * STOP_BUY: Buy when the stock reaches a certain price.
     * STOP_SELL: Sell when the stock reaches a certain price.
     */
    public enum OrderType {
        MARKET_BUY, MARKET_SELL, LIMIT_BUY, LIMIT_SELL, STOP_BUY, STOP_SELL
    }

    /**
     * Represents the status of the order.
     * OPEN: The order is open and has not been executed yet.
     * FILLED: The order has been completely executed.
     * CANCELLED: The order has been cancelled.
     */
    public enum OrderStatus {
        OPEN, FILLED, CANCELLED
    }

    /**
     * Represents the stock exchanges where the order can be executed.
     */
    public enum Exchange {
        NYSE, NASDAQ, LSE, TSE, SSE, NSE, BSE, HKEX, SGX, ASX, JSE, TSX,
        BMV, FWB, BME, KRX, TWSE, BIVA, BVC, BVL, BYMA, CSE, EGX, IDX, ISX,
        MSE, PSE, SET, TASE, Tadawul, USE, ZSE
    }

    private final Long orderId;
    private final String symbol;
    private final OrderType orderType;
    private OrderStatus orderStatus;
    private long timeToExecute;
    private Exchange exchange;
    private final Double price;
    private final Integer quantity;

    /**
     * Constructor to initialize the order with the required parameters.
     *
     * @param orderId The unique identifier for the order.
     * @param symbol The stock symbol for the order.
     * @param orderType The type of the order (e.g., MARKET_BUY, LIMIT_SELL).
     * @param orderStatus The initial status of the order (e.g., OPEN).
     * @param price The price at which to execute the order.
     * @param quantity The quantity of stocks to be traded.
     */
    public Order(Long orderId, String symbol, OrderType orderType, OrderStatus orderStatus, Double price, Integer quantity) {
        this.orderId = orderId;
        this.symbol = symbol;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.price = price;
        this.quantity = quantity;
    }

    public Order(byte[] order){
        String orderString = new String(order);
        String[] orderParts = orderString.split(",");
        this.orderId = Long.parseLong(orderParts[0]);
        this.symbol = orderParts[1];
        this.orderType = OrderType.valueOf(orderParts[2]);
        this.orderStatus = OrderStatus.valueOf(orderParts[3]);
        this.price = Double.parseDouble(orderParts[4]);
        this.quantity = Integer.parseInt(orderParts[5]);
        this.exchange = Exchange.valueOf(orderParts[6]);
        this.timeToExecute = Long.parseLong(orderParts[7]);
    }

    /**
     * Returns the unique identifier for the order.
     *
     * @return The order ID.
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Returns the stock symbol for the order.
     *
     * @return The stock symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the type of the order.
     *
     * @return The order type (e.g., MARKET_BUY, LIMIT_SELL).
     */
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * Returns the current status of the order.
     *
     * @return The order status (e.g., OPEN, FILLED, CANCELLED).
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Returns the exchange on which the order is to be executed.
     *
     * @return The exchange (e.g., NYSE, NASDAQ).
     */
    public Exchange getExchange() {
        return exchange;
    }

    /**
     * Returns the price at which the order is to be executed.
     * @return The price for the order.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Returns the quantity of stocks to be traded.
     *
     * @return The quantity of stocks.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the exchange on which the order is to be executed.
     * @param exchange The exchange to set for the order (e.g., NYSE, NASDAQ).
     */
    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    /**
     * Sets the order status of the order.
     * @param orderStatus The new status for the order (e.g., OPEN, FILLED, CANCELLED).
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Gets the time to execute the order.
     * @return The Unix timestamp of when the order should be executed.
     */
    public long getTimeToExecute() {
        return timeToExecute;
    }

    /**
     * Sets the time to execute the order.
     * This should be set to a Unix timestamp indicating when the order should be executed.
     *
     * @param timeToExecute The Unix timestamp for execution.
     */
    public void setTimeToExecute(Long timeToExecute) {
        this.timeToExecute = timeToExecute;
    }

    /**
     * Converts the order to a byte array for transmission.
     * This is a simple string representation of the order.
     *
     * @return The byte array representation of the order.
     */
    public byte[] toByteArray() {
        String orderString = orderId + "," + symbol + "," + orderType + "," + orderStatus + "," + price + "," + quantity + "," + exchange + "," + timeToExecute;
        return orderString.getBytes();
    }
}
