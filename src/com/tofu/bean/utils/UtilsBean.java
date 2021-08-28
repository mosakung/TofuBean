package com.tofu.bean.utils;

import org.bukkit.Location;

import java.text.DecimalFormat;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class UtilsBean {

    public Double calDistanceXYZ(Location l1, Location l2) {
        Double diffP2X = pow(l1.getBlockX() - l2.getBlockX(), 2);
        Double diffP2Y = pow(l1.getBlockY() - l2.getBlockY(), 2);
        Double diffP2Z = pow(l1.getBlockZ() - l2.getBlockZ(), 2);

        return sqrt(diffP2X + diffP2Y + diffP2Z);
    }

    public Double doublerFormatP2(Double value) {
        DecimalFormat df = new DecimalFormat("0.00");

        return Double.parseDouble(df.format(value));
    }
}
