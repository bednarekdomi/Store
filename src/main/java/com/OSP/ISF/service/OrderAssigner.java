package com.OSP.ISF.service;

import com.OSP.ISF.dto.Order;
import com.OSP.ISF.dto.StoreConfig;
import com.OSP.ISF.exception.PickerNotFoundException;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class OrderAssigner {

    private Map<String, List<Order>> assignedOrders = new HashMap<>();

    public Map<String, List<Order>> assignOrdersToPickers(StoreConfig storeConfig, List<Order> orders,
                                                          Comparator<Order> comparator) {
        storeConfig.getPickers().forEach(picker -> assignedOrders.put(picker, new ArrayList<>()));
        orders.sort(comparator);
        orders.forEach(order -> {
            Map.Entry<String, LocalTime> earliestFinishedPicker =
                    getEarliestFinishedPicker(storeConfig.getPickingStartTime());

            if(earliestFinishedPicker.getValue().plus(order.getPickingTime()).isBefore(order.getCompleteBy())) {
                assignedOrders.get(earliestFinishedPicker.getKey()).add(order);
            } else {
                System.out.println("No free picker");
            }
        });
        return assignedOrders;
    }
    private Map.Entry<String, LocalTime> getEarliestFinishedPicker(LocalTime pickingStartTime) {
        Map<String, LocalTime> mapOfFinishedPickers = new HashMap<>();
        assignedOrders.forEach((key, value) -> {
            Duration durationSum = Duration.ZERO;
            for(Duration duration : value.stream().map(Order::getPickingTime).toList()) {
                durationSum = durationSum.plusMinutes(duration.toMinutes());
            }
            mapOfFinishedPickers.put(key, pickingStartTime.plusMinutes(durationSum.toMinutes()));
        });
        Optional<Map.Entry<String, LocalTime>> maxEntry =
                mapOfFinishedPickers.entrySet().stream().min(Map.Entry.comparingByValue());
        return maxEntry.orElseThrow(PickerNotFoundException::new);
    }
}

