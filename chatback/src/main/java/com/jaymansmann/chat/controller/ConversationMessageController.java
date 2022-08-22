package com.jaymansmann.chat.controller;

import com.jaymansmann.chat.dto.ConversationMessageResponseDTO;
import com.jaymansmann.chat.dto.CreateConversationMessageDTO;
import com.jaymansmann.chat.security.UserPrincipal;
import com.jaymansmann.chat.service.ConversationMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conversations/{conversationId}/messages")
public class ConversationMessageController {
    @Autowired
    private ConversationMessageService conversationMessageService;

    @GetMapping
    public List<ConversationMessageResponseDTO> getMessagesByConversationId(
                                                                                    @PathVariable Long conversationId,
                                                                                    Authentication authentication) {
        return conversationMessageService.getByConversationId(conversationId,
                (UserPrincipal) authentication.getPrincipal());

    }

    @PostMapping
    public ConversationMessageResponseDTO createByUserIdAndConversationId(
                                                                          @PathVariable Long conversationId,
                                                                          @RequestBody @Valid CreateConversationMessageDTO createConversationMessageDTO,
                                                                          Authentication authentication) {

        return conversationMessageService.createMessageForConversation(conversationId,
                createConversationMessageDTO, (UserPrincipal) authentication.getPrincipal());
    }
}
