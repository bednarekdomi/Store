package com.OSP.ISF.service;

import com.OSP.ISF.dto.OrderList;
import com.OSP.ISF.dto.StoreConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataReader {
    public static StoreConfig readStoreConfig(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
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
        File ordersFile = new File(filePath);
        OrderList orders = null;

        try {
            orders = mapper.readValue(ordersFile, OrderList.class);
        } catch (IOException e) {
            System.out.println("Error while reading configuration file: " + e.getMessage());
        }
        return orders;
    }
}
