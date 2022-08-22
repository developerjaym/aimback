package com.jaymansmann.chat.dto;

import com.jaymansmann.chat.dto.auth.UserDTO;
import lombok.Data;

import java.util.List;

@Data
public class ConversationResponseDTO {
    private Long id;
    private List<UserDTO> users;
    private String name;
}
