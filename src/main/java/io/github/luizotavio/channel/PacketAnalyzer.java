package io.github.luizotavio.channel;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import io.github.luizotavio.PacketPlugin;
import io.github.luizotavio.controller.PacketController;

public class PacketAnalyzer extends PacketAdapter {

    private final PacketController packetController;

    public PacketAnalyzer(PacketPlugin packetPlugin, PacketController packetController, PacketType... packetTypes) {
        super(packetPlugin, ListenerPriority.LOWEST, packetTypes);

        this.packetController = packetController;
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        packetController.sumIfPresent(event.getPlayer().getName(), event.getPacketID());
    }
}
