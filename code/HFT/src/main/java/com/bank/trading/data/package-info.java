/**
 * This is the package for all the data related services of the system.
 *
 * The backbone of the HFTS are the data services that provide the necessary data for the system to run.
 * These services must have high availability and reliability. We also need a service to gather market data and reference data.
 * We also need a service that can store data long term for compliance, research or testing purposes.
 *
 * This package also includes the Lamport Clock for system synchronization.
 * The lamport clock also houses the UDP multicast and that Replay service.
 *
 * @author Tani Diament
 * @version 1.0
 */
package com.bank.trading.data;