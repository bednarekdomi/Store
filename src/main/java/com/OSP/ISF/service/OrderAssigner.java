package com.OSP.ISF.service;

import com.OSP.ISF.dto.Order;
import com.OSP.ISF.dto.StoreConfig;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAssigner {

    public Map<String, List<Order>> assignOrdersToPickers(StoreConfig storeConfig, List<Order> orders) {
        Map<String, List<Order>> assignedOrders = new HashMap<>();
        List<String> pickers = storeConfig.getPickers();
        int orderIndex = 0;
        LocalTime workStartTime = storeConfig.getPickingStartTime();

        while (workStartTime.isBefore(storeConfig.getPickingEndTime()) && orderIndex < orders.size()) {
            for (int i = 0; i < pickers.size() && orderIndex < orders.size(); i++) {
                String pickerId = pickers.get(i);
                List<Order> pickerOrders = assignedOrders.getOrDefault(pickerId, new ArrayList<>());
                Order order = orders.get(orderIndex);
                if (order.getCompleteBy().isBefore(workStartTime.plus(order.getPickingTime()))) {
                    continue;
                }
                pickerOrders.add(order);
                assignedOrders.put(pickerId, pickerOrders);
                System.out.println("Picker ID: " + pickerId + ", Order Index: " + orderIndex + ", Picking Start Time: " + workStartTime);
                orderIndex++;
                workStartTime = workStartTime.plus(order.getPickingTime());
            }
        }

        return assignedOrders;
    }
}
