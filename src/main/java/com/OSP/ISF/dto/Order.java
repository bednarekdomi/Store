package com.OSP.ISF.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
}
