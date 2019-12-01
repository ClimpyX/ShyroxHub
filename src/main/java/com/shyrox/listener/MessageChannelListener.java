package com.shyrox.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import com.shyrox.ShyroxHub;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MessageChannelListener implements PluginMessageListener {


    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord") && !channel.equals("ReturnCommand")) {
            return;
        }

        try {
            if (message.length == 0) {
                return;
            }

            ByteArrayDataInput in = ByteStreams.newDataInput(message);
            String subchannel = in.readUTF();
            if (subchannel.equals("PlayerCount")) {
                String server = in.readUTF();
                int playerCount = in.readInt();

                ShyroxHub.getInstance().playerCount.remove(server);
                ShyroxHub.getInstance().playerCount.put(server, playerCount);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void sendToServer(Player player, String targetServer) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(targetServer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        player.sendPluginMessage(ShyroxHub.getInstance(), "BungeeCord", b.toByteArray());
    }


    public Integer getOnlineCount(String serverName) {
        if (serverName == null) {
            serverName = "ALL";
            int online = 0;

            for (Integer next : ShyroxHub.getPlayerCount().values()) {
                if (next > 0) {
                    online += next;
                }
            }

            return online;
        } else {
            ShyroxHub.getPlayerCount().putIfAbsent(serverName, Integer.valueOf(-1));
            return ((Integer)ShyroxHub.getPlayerCount().get(serverName)).intValue();
        }
    }

    public void getCount(Player player, String serverName) {
        if (serverName == null) {
            serverName = "ALL";
        }

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(serverName);
    }
}
