package com.tofu.bean.utils.parser;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationParser {

    public static Location string2Location(String rawLocation) {
        String[] partLocation = rawLocation.split("!");

        Location location = new Location(
                Bukkit.getServer().getWorld(partLocation[3]),
                Integer.parseInt(partLocation[0]),
                Integer.parseInt(partLocation[1]),
                Integer.parseInt(partLocation[2])
        );

        return location;
    }

    public static String location2String(Location location) {
        String rawLocation = location.getBlockX() + "!" + location.getBlockY() + "!" +
                location.getBlockZ() + "!" + location.getWorld().getName();

        return rawLocation;
    }

    public static Double string2Double(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException exec) {
            return null;
        }
    }

}
