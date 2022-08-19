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

public class IPv6Packet {

    private static final String TAG = IPv6Packet.class.getSimpleName();

    @SuppressWarnings("checkstyle:MagicNumber")
    public static final int MAX_PACKET_LENGTH = 1 << 16; // packet length is stored on 16 bits

    private final ByteBuffer raw;
    private final IPv6Header ipv6Header;
    private final TransportHeader transportHeader;

    public IPv6Packet(ByteBuffer raw) {
        this.raw = raw;
        raw.rewind();

        if (Log.isVerboseEnabled()) {
            Log.v(TAG, "IPv6Packet: " + Binary.buildPacketString(raw));
        }

        ipv6Header = new IPv6Header(raw.duplicate());
        if (!ipv6Header.isSupported()) {
            Log.d(TAG, "Unsupported IPv6 headers");
            transportHeader = null;
            return;
        }
        transportHeader = createTransportHeader();
        raw.limit(ipv6Header.getTotalLength());
    }

    public boolean isValid() {
        return transportHeader != null;
    }

    private TransportHeader createTransportHeader() {
        IPv6Header.Protocol protocol = ipv6Header.getProtocol();
        switch (protocol) {
            case UDP:
                return new UDPHeader(getRawTransport());
            case TCP:
                return new TCPHeader(getRawTransport());
            default:
                throw new AssertionError("Should be unreachable if ipv6Header.isSupported()");
        }
    }

    private ByteBuffer getRawTransport() {
        raw.position(ipv6Header.getHeaderLength());
        return raw.slice();
    }

    public IPv6Header getIpv6Header() {
        return ipv6Header;
    }

    public TransportHeader getTransportHeader() {
        return transportHeader;
    }

    public void swapSourceAndDestination() {
        ipv6Header.swapSourceAndDestination();
        transportHeader.swapSourceAndDestination();
    }

    public ByteBuffer getRaw() {
        raw.rewind();
        return raw.duplicate();
    }

    public int getRawLength() {
        return raw.limit();
    }

    public ByteBuffer getPayload() {
        int headersLength = ipv6Header.getHeaderLength() + transportHeader.getHeaderLength();
        raw.position(headersLength);
        return raw.slice();
    }

    public int getPayloadLength() {
        return raw.limit() - ipv6Header.getHeaderLength() - transportHeader.getHeaderLength();
    }

    public void computeChecksums() {
        ipv6Header.computeChecksum();
        transportHeader.computeChecksum(ipv6Header, getPayload());
    }
}
