package com.zyuzin.maxim.DTO;

import lombok.Data;

@Data
public class OperationDTO {
    private Long amount;
    private Long senderId;
    private Long receiverId;
}
