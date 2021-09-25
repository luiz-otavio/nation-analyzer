package io.github.luizotavio.adapter;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Table;
import io.github.luizotavio.controller.PacketController;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ControllerAdapter {

    public static void toWrite(File file, PacketController packetController) throws IOException {
        Table<String, Integer, Integer> table = packetController.getPacketTable();

        YamlConfiguration yamlConfiguration = new YamlConfiguration();

        for (String name : table.rowKeySet()) {
            Map.Entry<Integer, Integer> entry = packetController.getHigherEntry(name);

            if (entry != null) {
                yamlConfiguration.createSection(name, ImmutableMap.builder()
                        .put(entry.getKey(), entry.getValue())
                        .build());
            }
        }

        yamlConfiguration.save(file);
    }

}
