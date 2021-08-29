package com.tofu.bean.utils;

import java.text.DecimalFormat;

public class UtilsBean {

    public static Double doublerFormatP2(Double value) {
        DecimalFormat df = new DecimalFormat("0.00");

        return Double.parseDouble(df.format(value));
    }
}
