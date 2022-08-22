package com.jaymansmann.chat.service;

import com.jaymansmann.chat.db.entity.Conversation;
import com.jaymansmann.chat.db.entity.auth.User;
import com.jaymansmann.chat.db.repository.ConversationRepository;
import com.jaymansmann.chat.dto.AddUsersDTO;
import com.jaymansmann.chat.dto.ConversationResponseDTO;
import com.jaymansmann.chat.dto.CreateConversationDTO;
import com.jaymansmann.chat.security.UserPrincipal;
import com.jaymansmann.chat.service.auth.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {
    @Autowired
    private AuthService authService;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private ModelMapper mapper;

    public Optional<Conversation> getByIdAndUsersId(Long conversationId, Long userId) {
        return conversationRepository.findByIdAndUsersId(conversationId, userId);
    }

    public boolean existsByIdAndUserId(Long conversationId, Long userId) {
        return conversationRepository.existsByIdAndUsersId(conversationId, userId);
    }

    public List<ConversationResponseDTO> getForUser(UserPrincipal principal) {
        return conversationRepository.findAllByUsersId(principal.getId()).stream().map(conversation -> mapper.map(conversation, ConversationResponseDTO.class)).toList();
    }

    public ConversationResponseDTO create(CreateConversationDTO createConversationDTO, UserPrincipal principal) {
        User user = authService.getUser(principal);
        Conversation conversation = mapper.map(createConversationDTO, Conversation.class);
        List<Long> usersList = new ArrayList<>(createConversationDTO.getUserIds());
        usersList.add(user.getId());
        System.out.println(usersList);
        conversation.setUsers(authService.getUsersByIds(usersList));
        return mapper.map(conversationRepository.save(conversation), ConversationResponseDTO.class);
    }

    @Transactional
    public ConversationResponseDTO addUsers(Long conversationId, AddUsersDTO addUsersDTO, UserPrincipal principal) {
        Conversation conversation =
                conversationRepository.findByIdAndUsersId(conversationId, principal.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        conversation.getUsers().addAll(authService.getUsersByIds(addUsersDTO.getUserIds()));
        return mapper.map(conversation, ConversationResponseDTO.class);
    }
}
