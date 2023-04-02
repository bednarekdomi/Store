package com.OSP.ISF.utils.comparator;

import com.OSP.ISF.dto.Order;

import java.util.Comparator;

public class OrderComparator {
    public static Comparator<Order> getComparator() {
        return Comparator.comparing(Order::getCompleteBy).thenComparing(Order::getPickingTime);
    }
    public static Comparator<Order> getAdditionalComparator() {
        return Comparator.comparing(Order::getValuePerTime).reversed().thenComparing(Order::getCompleteBy)
                .thenComparing(Order::getPickingTime); }
}
