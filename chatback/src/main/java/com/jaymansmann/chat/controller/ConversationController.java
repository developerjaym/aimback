package com.jaymansmann.chat.controller;

import com.jaymansmann.chat.dto.AddUsersDTO;
import com.jaymansmann.chat.dto.ConversationResponseDTO;
import com.jaymansmann.chat.dto.CreateConversationDTO;
import com.jaymansmann.chat.security.UserPrincipal;
import com.jaymansmann.chat.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conversations")
public class ConversationController {


    @Autowired
    private ConversationService conversationService;

    @GetMapping
    public List<ConversationResponseDTO> getAllByUserId(
                                                        Authentication authentication) {
        return conversationService.getForUser((UserPrincipal) authentication.getPrincipal());

    }

    @PostMapping
    public ConversationResponseDTO create(@Valid @RequestBody CreateConversationDTO createConversationDTO,
                                         Authentication authentication) {
        return conversationService.create(createConversationDTO, (UserPrincipal) authentication.getPrincipal());
    }

    @PutMapping("/{conversationId}/users")
    public ConversationResponseDTO addUsers(@PathVariable Long conversationId,
                                            @Valid @RequestBody AddUsersDTO addUsersDTO,
                                            Authentication authentication) {
        return conversationService.addUsers(conversationId, addUsersDTO, (UserPrincipal) authentication.getPrincipal());
    }
}
