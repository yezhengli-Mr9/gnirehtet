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

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class IPv6PacketBuffer {

    private final ByteBuffer buffer = ByteBuffer.allocate(IPv6Packet.MAX_PACKET_LENGTH);

    public int readFrom(ReadableByteChannel channel) throws IOException {
        return channel.read(buffer);
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private int getAvailablePacketLength() {
        int length = IPv6Header.readLength(buffer);
        assert length == -1 || IPv6Header.readVersion(buffer) == 6 : "This function must not be called when the packet is not IPv6";
        if (length == -1) {
            // no packet
            return 0;
        }
        if (length > buffer.remaining()) {
            // no full packet available
            return 0;
        }
        return length;
    }

    public IPv6Packet asIPv6Packet() {
        // TODO
        return null;
    }

    public void next() {
        buffer.compact();
    }
}
