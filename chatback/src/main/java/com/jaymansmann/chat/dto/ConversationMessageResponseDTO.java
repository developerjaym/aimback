package com.jaymansmann.chat.dto;

import com.jaymansmann.chat.dto.auth.UserDTO;
import lombok.Data;

import java.time.Instant;

@Data
public class ConversationMessageResponseDTO {
    private Long id;
    private Instant createdAt;
    private String content;
    private UserDTO user;
}
