package com.OSP.ISF;

import com.OSP.ISF.dto.Order;
import com.OSP.ISF.dto.OrderList;
import com.OSP.ISF.dto.StoreConfig;
import com.OSP.ISF.service.DataReader;
import com.OSP.ISF.service.OrderAssigner;
import com.OSP.ISF.utils.comparator.OrderComparator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class IsfApplication {
    public static final String OUTPUT_MESSAGE_PATTERN = " %s, %s, %s";

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println("Paths to configuration files are not specified");
            return;
        }

        String storeFilePath = args[0];
        String ordersFilePath = args[1];

        StoreConfig storeConfig = DataReader.readStoreConfig(storeFilePath);
        OrderList orderList = DataReader.readOrders(ordersFilePath);

        OrderAssigner orderAssigner = new OrderAssigner();

        Map<String, List<Order>> ordersPickersMap = orderAssigner.assignOrdersToPickers(storeConfig, orderList.getOrders(), OrderComparator.getComparator());

        IsfApplication.printValues(ordersPickersMap, storeConfig.getPickingStartTime());
    }

    private static void printValues(Map<String, List<Order>> map, LocalTime startPickingTime) throws IOException {
        map.forEach((key, value) -> {
            LocalTime startTime = startPickingTime;
            for (Order order : value) {
                System.out.println(String.format(OUTPUT_MESSAGE_PATTERN, key, order.getOrderId(), startTime));
                startTime = startTime.plus(order.getPickingTime());
            }
        });
    }
}
