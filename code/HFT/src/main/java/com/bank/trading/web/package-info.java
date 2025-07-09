/**
 * This package contains the web interface for the trading system.
 *
 * The Employee Web Interface(EWI) is the portal for bank employees to interact with the HFTS.
 * It runs an HTTP server for the client side website to interact with the system.
 * The EWI allows for changing of the controls and algos, viewing current system data and data from the backup data service,
 * and allows for manual trading.
 * The LCS keeps a map of services to address so the EWI can get the address of a service it wants to interact with e.g. to execute a trade.
 * Given the fact that the EWI is mainly used for data consumption it is run on one of the cores of the Lamport server.
 * This service also allows for data access for governing bodies like the SEC.
 *
 * @author Tani Diament
 * @version 1.0
 */

package com.bank.trading.web;