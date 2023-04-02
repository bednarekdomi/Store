package com.OSP.ISF.service;

import com.OSP.ISF.dto.Order;
import com.OSP.ISF.dto.OrderList;
import com.OSP.ISF.dto.StoreConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static StoreConfig readStoreConfig(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File storeFile = new File(filePath);
        StoreConfig storeConfig = null;
        try {
            storeConfig = mapper.readValue(storeFile, StoreConfig.class);
        } catch (IOException e) {
            System.out.println("Error while reading configuration file: " + e.getMessage());
        }
        return storeConfig;
    }

    public static OrderList readOrders(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File ordersFile = new File(filePath);
        OrderList orderList = new OrderList();
        List<Order>orders = new ArrayList<>();
        try {
            Order[] orderArray = mapper.readValue(ordersFile, Order[].class);
            for (Order order : orderArray) {
                orders.add(order);
                orderList.setOrders(orders);
            }
        } catch (IOException e) {
            System.out.println("Error while reading configuration file: " + e.getMessage());
        }
        return orderList;
    }
}
