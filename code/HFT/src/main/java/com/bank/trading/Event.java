package com.bank.trading;

/**
 * Object used system-wide to represent an event.
 * All events go through the Lamport clock to be timestamped.
 */
public class Event {

    /**
     * The type of the event.
     */
    public enum Type {
        ORDER, MARKET_DATA, REFERENCE_DATA, ALGORITHM_CHANGE, CONTROL_CHANGE, EXECUTION
    }

    private Type type;
    private String message;
    private String ticker;
    private Long timestamp;
    private Order order;
    private byte[] binaryData;// The binary data for alg or control change events

    /**
     * Constructor for an event.
     * @param type The type of the event.
     * @param message The message associated with the event.
     */
    public Event(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    /**
     * Constructor for an event with a timestamp.
     * Used for constructing events from the Lamport clock or over the network.
     * @param binary The binary data to make the event.
     */
    public Event(byte[] binary) {
        String binaryString = new String(binary);
        String[] parts = binaryString.split(",");
        this.type = Type.valueOf(parts[0]);
        this.message = parts[1];
        this.timestamp = Long.parseLong(parts[2]);
        this.order = new Order(parts[3].getBytes());
        this.binaryData = parts[4].getBytes();
    }

    /**
     * Returns the type of the event.
     * @return the type of the event.
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns the message associated with the event.
     * @return the message of the event.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the timestamp of the event.
     * This is set by the Lamport clock when the event is created.
     * @return the timestamp of the event.
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the event.
     * This should only be set by the Lamport clock.
     * @param timestamp the timestamp of the event.
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the order associated with the event.
     * This is only set for ORDER events.
     * @return the order associated with the event, or null if not an ORDER event.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the order associated with the event.
     * This should only be set for ORDER events.
     * @param order the order to associate with the event.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Set the binary data for the event.
     * @param data
     */
    public void setBinaryData(byte[] data) {
        // Set the binary data for the event
    }

    /**
     * Get the ticker associated with the event.
     * @return the ticker associated with the event.
     */
    public String getTicker() {
        return ticker;
    }
    /**
     * Set the ticker associated with the event.
     * @param ticker the ticker to associate with the event.
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * Get the binary data for the event.
     * @return the binary data for the event.
     */
    public byte[] getBinaryData() {
        return binaryData;
    }

    /**
     * Convert the event to a binary format.
     * This is used for sending the event over the network.
     * @return the binary representation of the event.
     */
    public byte[] toBinary() {
        String binaryString = type.toString() + "," + message + "," + timestamp + "," + order.toString() + "," + new String(binaryData);
        return binaryString.getBytes();
    }
}
