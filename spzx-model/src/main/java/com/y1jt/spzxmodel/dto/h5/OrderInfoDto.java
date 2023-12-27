package com.y1jt.spzxmodel.dto.h5;

import com.y1jt.spzxmodel.entity.order.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderInfoDto {

    //送货地址id
    private Long userAddressId;

    //运费
    private BigDecimal feightFee;

    //备注
    private String remark;

    //订单明细
    private List<OrderItem> orderItemList;
}
