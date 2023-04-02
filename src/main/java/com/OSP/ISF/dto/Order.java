package com.OSP.ISF.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class Order {
    private String orderId;
    private BigDecimal orderValue;
    private Duration pickingTime;
    private LocalTime completeBy;
    private double valuePerTime;

    public Order(String orderId, BigDecimal orderValue, Duration pickingTime, LocalTime completeBy) {
        double minutes = pickingTime.toMinutes();
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.pickingTime = pickingTime;
        this.completeBy = completeBy;
        this.valuePerTime = this.orderValue.divide(BigDecimal.valueOf(minutes), 2, RoundingMode.HALF_UP).doubleValue();
    }
}
