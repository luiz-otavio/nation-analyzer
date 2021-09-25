package io.github.luizotavio;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import io.github.luizotavio.adapter.ControllerAdapter;
import io.github.luizotavio.channel.PacketAnalyzer;
import io.github.luizotavio.controller.PacketController;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class PacketPlugin extends JavaPlugin {

    private PacketController packetController;

    @Override
    public void onLoad() {
        if (!getDataFolder().exists()) getDataFolder().mkdirs();

        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        packetController = new PacketController();

        List<Integer> whitelist = getConfig().getIntegerList("Whitelist");

        PacketType[] packetTypes = PacketType.Play.Client.getInstance().values().stream()
                        .filter(packetType -> whitelist.contains(packetType.getCurrentId()))
                        .toArray(PacketType[]::new);

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAnalyzer(
                this,
                packetController,
                packetTypes
        ));
    }

    @Override
    public void onDisable() {
        File file = new File(getDataFolder(), "packet.log");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            ControllerAdapter.toWrite(file, packetController);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
