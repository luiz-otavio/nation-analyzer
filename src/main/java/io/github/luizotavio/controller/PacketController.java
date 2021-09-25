package io.github.luizotavio.controller;

import com.google.common.collect.Table;
import com.google.common.collect.Tables;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class PacketController {

    private final Table<String, Integer, Integer> packetTable = Tables.newCustomTable(new HashMap<>(), WeakHashMap::new);

    public void sumIfPresent(String name, int packet) {
        packetTable.row(name).compute(packet, (key, value) -> value == null ? 1 : value + 1);
    }

    public Map.Entry<Integer, Integer> getHigherEntry(String name) {
        Map<Integer, Integer> map = packetTable.row(name);

        Map.Entry<Integer, Integer> target = null;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (target == null || target.getValue() < entry.getValue()) {
                target = entry;
            }
        }

        return target;
    }

    public Table<String, Integer, Integer> getPacketTable() {
        return packetTable;
    }
}
