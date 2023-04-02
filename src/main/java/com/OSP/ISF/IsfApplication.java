package com.OSP.ISF;

import com.OSP.ISF.dto.Order;
import com.OSP.ISF.dto.OrderList;
import com.OSP.ISF.dto.StoreConfig;
import com.OSP.ISF.service.DataReader;
import com.OSP.ISF.service.OrderAssigner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class IsfApplication {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Paths to configuration files are not specified");
            return;
        }

        String storeFilePath = args[0];
        String ordersFilePath = args[1];

        StoreConfig storeConfig = DataReader.readStoreConfig(storeFilePath);
        OrderList orderList = DataReader.readOrders(ordersFilePath);

        List<String> pickers = new ArrayList<>();
        pickers.add("Picker1");
        pickers.add("Picker2");
        pickers.add("Picker3");
        pickers.add("Picker4");
        pickers.add("Picker5");

        StoreConfig storeConfig = new StoreConfig(pickers, LocalTime.of(14, 00), LocalTime.of(18, 00));
        Order orderOne = new Order("order-1", BigDecimal.valueOf(52.40), Duration.ofMinutes(15), LocalTime.of(15, 00));
        Order orderTwo = new Order("order-2", BigDecimal.valueOf(50.40), Duration.ofMinutes(20), LocalTime.of(16, 00));
        Order orderThree = new Order("order-3", BigDecimal.valueOf(50.40), Duration.ofMinutes(30), LocalTime.of(17, 00));
        Order orderFour= new Order("order-4", BigDecimal.valueOf(50.40), Duration.ofMinutes(20), LocalTime.of(17, 00));
        Order orderFive = new Order("order-5", BigDecimal.valueOf(50.40), Duration.ofMinutes(20), LocalTime.of(17, 00));
        Order orderSix = new Order("order-6", BigDecimal.valueOf(50.40), Duration.ofMinutes(20), LocalTime.of(17, 00));
        List<Order> orders = new ArrayList<>();
        orders.add(orderOne);
        orders.add(orderTwo);
        orders.add(orderThree);
        orders.add(orderFour);
        orders.add(orderFive);
        orders.add(orderSix);

        OrderAssigner orderAssigner = new OrderAssigner();
        Map<String, List<Order>> testMap = orderAssigner.assignOrdersToPickers(storeConfig, orders);
    }
}
