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

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@SuppressWarnings("checkstyle:MagicNumber")
public class IPv6PacketBufferTest {

    private static ByteBuffer createMockPacket() {
        // TODO
        return null;
    }

    private static void writeMockPacketTo(ByteBuffer buffer) {
        // TODO
    }

    private static ReadableByteChannel contentToChannel(ByteBuffer buffer) {
        ByteArrayInputStream bis = new ByteArrayInputStream(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.limit());
        return Channels.newChannel(bis);
    }

    @Test
    public void testParseIPv6PacketBuffer() throws IOException {
        ByteBuffer buffer = createMockPacket();

        IPv6PacketBuffer packetBuffer = new IPv6PacketBuffer();
        // TODO
    }

    @Test
    public void testParseFragmentedIPv6PacketBuffer() throws IOException {
        ByteBuffer buffer = createMockPacket();

        IPv6PacketBuffer packetBuffer = new IPv6PacketBuffer();
        // TODO
    }

    private static ByteBuffer createMockPackets() {
        // TODO
        ByteBuffer buffer = ByteBuffer.allocate(128 * 3);
        for (int i = 0; i < 3; ++i) {
            writeMockPacketTo(buffer);
        }
        buffer.flip();
        return buffer;
    }

    @Test
    public void testMultiPackets() throws IOException {
        ByteBuffer buffer = createMockPackets();
        // TODO
    }

    private static void checkPacketHeaders(IPv6Packet packet) {
        IPv6Header ipv46eader = packet.getIpv6Header();
        // TODO
    }
}
