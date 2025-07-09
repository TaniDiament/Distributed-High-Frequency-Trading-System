/**
 * This is the java package for the high frequency trading system.
 * The High-Frequency Trading System(HFTS) is designed to make split second decisions
 * based on current market data and conditions to optimize profit.
 * To gain a competitive advantage we need to be able to quickly scale our system based on market volatility and trading volume.
 *
 * Our HFTS needs to import data from the market, in our case streamed from Bloomberg.
 * It must have the ability to execute trades in markets around the globe (NYSE, TYO, LSE…).
 * It requires a dashboard for bank employees to view real time data and the ability to update algorithms and manually place trades.
 * The system also needs to have kill switches and controls that keep the system from spiraling out of control in case of errors.
 * All of this has to happen on a distributed computing platform with ability to dynamically scale and be fault-tolerant.
 *
 * This package contains the classes for the event and order objects that are used system-wide
 * @author Tani Diament
 * @version 1.0
 */

package com.bank.trading;