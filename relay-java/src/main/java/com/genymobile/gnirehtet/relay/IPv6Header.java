/*
 * Copyright (C) 2017 Genymobile
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.genymobile.gnirehtet.relay;

import java.nio.ByteBuffer;

@SuppressWarnings("checkstyle:MagicNumber")
public class IPv6Header {
	 public enum Protocol {
        TCP(6), UDP(17), OTHER(-1);

        private final int number;

        Protocol(int number) {
            this.number = number;
        }

        int getNumber() {
            return number;
        }

        static Protocol fromNumber(int number) {
            if (number == TCP.number) {
                return TCP;
            }
            if (number == UDP.number) {
                return UDP;
            }
            return OTHER;
        }
    }
	private ByteBuffer raw;
    private byte version;
    private int headerLength;
    private int totalLength;
    private Protocol protocol;
    private int source;
    private int destination;
	public IPv6Header(ByteBuffer raw) {
		// TODO
	}
	
	public boolean isSupported() {
		// TODO
        return version == 6 && protocol != Protocol.OTHER;
    }
	
    public Protocol getProtocol() {
    	// TODO
        return null;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        // TODO 
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public void setSource(int source) {
        // TODO
    }

    public void setDestination(int destination) {
        // TODO
    }

    public void swapSourceAndDestination() {
        // TODO
    }

    public ByteBuffer getRaw() {
        // TODO
        return null;
    }

    public IPv6Header copyTo(ByteBuffer target) {
        // TODO 
        return null;
    }

    public IPv6Header copy() {
        return new IPv6Header(Binary.copy(raw));
    }

    public void computeChecksum() {
        // TODO
    }

    private void setChecksum(short checksum) {
        // TODO
    }

    public short getChecksum() {
    	// TODO
        return 0;
    }

    /**
     * Read the packet IP version, assuming that an IP packets is stored at absolute position 0.
     *
     * @param buffer the buffer
     * @return the IP version, or {@code -1} if not available
     */
    public static int readVersion(ByteBuffer buffer) {
        // TODO
        return 0;
    }

    /**
     * Read the packet length, assuming thatan IP packet is stored at absolute position 0.
     *
     * @param buffer the buffer
     * @return the packet length, or {@code -1} if not available
     */
    public static int readLength(ByteBuffer buffer) {
        // TODO
        return 0;
    }

}