package com.jaymansmann.chat.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateConversationDTO {
    @Size(min = 1, max = 12)
    private List<Long> userIds;
    private String name;
}
