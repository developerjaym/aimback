package com.jaymansmann.chat.service;

import com.jaymansmann.chat.db.entity.ConversationMessage;
import com.jaymansmann.chat.db.repository.ConversationMessageRepository;
import com.jaymansmann.chat.dto.ConversationMessageResponseDTO;
import com.jaymansmann.chat.dto.CreateConversationMessageDTO;
import com.jaymansmann.chat.security.UserPrincipal;
import com.jaymansmann.chat.service.auth.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConversationMessageService {
    @Autowired
    private ConversationMessageRepository conversationMessageRepository;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper mapper;

    public ConversationMessageResponseDTO createMessageForConversation(Long conversationId, CreateConversationMessageDTO createConversationMessageDTO, UserPrincipal principal) {
        ConversationMessage conversationMessage = mapper.map(createConversationMessageDTO, ConversationMessage.class);
        conversationMessage.setUser(authService.getUser(principal));
        conversationMessage.setConversation(conversationService.getByIdAndUsersId(conversationId, principal.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        return mapper.map(conversationMessageRepository.save(conversationMessage),
                ConversationMessageResponseDTO.class);
    }

    public List<ConversationMessageResponseDTO> getByConversationId( Long conversationId, UserPrincipal principal) {
        if(!conversationService.existsByIdAndUserId(conversationId, principal.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return conversationMessageRepository.findByConversationId(conversationId).stream().map(message -> mapper.map(message, ConversationMessageResponseDTO.class)).toList();
    }
}
