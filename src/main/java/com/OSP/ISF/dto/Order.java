package com.OSP.ISF.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private String orderId;
    private BigDecimal orderValue;
    private Duration pickingTime;
    private LocalTime completeBy;

    public double valuePerTime(BigDecimal orderValue, Duration pickingTime){
        double minutes = pickingTime.toMinutes();
        return BigDecimal.valueOf(minutes).divide(orderValue, 2, RoundingMode.HALF_UP).doubleValue();
    }
}
