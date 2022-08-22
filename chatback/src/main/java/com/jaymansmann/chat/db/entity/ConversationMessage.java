package com.jaymansmann.chat.db.entity;

import com.jaymansmann.chat.db.entity.auth.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ConversationMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Conversation conversation;
    @CreatedDate
    private Instant createdAt;
    private String content;

}
