package com.jaymansmann.chat.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddUsersDTO {
    private List<Long> userIds;
}
