package com.jaymansmann.chat.db.repository;

import com.jaymansmann.chat.db.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    boolean existsByIdAndUsersId(Long conversationId, Long userId);

    Optional<Conversation> findByIdAndUsersId(Long conversationId, Long userId);

    List<Conversation> findAllByUsersId(Long id);
}
