/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 * 这个类是由java mavlink生成器工具�?它不应该被手动修改�?
 */

package com.MAVLink.Messages;

import com.MAVLink.MAVLinkPacket;

/**
 * Storage for MAVLink Packet and Error statistics
 * mavlink包和错误统计存储
 */
public class MAVLinkStats /* implements Serializable */ {

    public int receivedPacketCount;

    public int crcErrorCount;

    public int lostPacketCount;

    private int lastPacketSeq;

    /**
     * Check the new received packet to see if has lost someone between this and
     * the last packet
     * �?��新的接收的数据包，看看是否已经失去了这之间的人最后一个包
     *
     * @param packet Packet that should be checked
     */
    public void newPacket(MAVLinkPacket packet) {
        advanceLastPacketSequence();
        if (hasLostPackets(packet)) {
            updateLostPacketCount(packet);
        }
        lastPacketSeq = packet.seq;
        receivedPacketCount++;
    }

    private void updateLostPacketCount(MAVLinkPacket packet) {
        int lostPackets;
        if (packet.seq - lastPacketSeq < 0) {
            lostPackets = (packet.seq - lastPacketSeq) + 255;
        } else {
            lostPackets = (packet.seq - lastPacketSeq);
        }
        lostPacketCount += lostPackets;
    }

    private boolean hasLostPackets(MAVLinkPacket packet) {
        return lastPacketSeq > 0 && packet.seq != lastPacketSeq;
    }

    private void advanceLastPacketSequence() {
        // wrap from 255 to 0 if necessary
        lastPacketSeq = (lastPacketSeq + 1) & 0xFF;
    }

    /**
     * Called when a CRC error happens on the parser
     */
    public void crcError() {
        crcErrorCount++;
    }

    /**
     * Resets statistics for this MAVLink.
     * 重置此mavlink统计�?     */
    public void mavlinkResetStats() {
        lastPacketSeq = -1;
        lostPacketCount = 0;
        crcErrorCount = 0;
        receivedPacketCount = 0;
    }

}