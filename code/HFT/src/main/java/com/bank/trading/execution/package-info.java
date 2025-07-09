/**
 * This package contains the classes used for the algorithmic trading execution.
 *
 * The heart of the HFTS system are the algorithms and the trading execution services.
 * These services require the most compute and therefore will be scaled more greatly than the other services.
 * All inter system communication is done over UDP for speed, it is okay for a piece of data or two to be lost,
 * especially since that will happen very infrequently.
 *
 * @author Tani Diament
 * @version 1.0
 */

package com.bank.trading.execution;