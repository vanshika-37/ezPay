package com.ezpay.entity;

import java.util.Date;
import jakarta.persistence.*;

/**
 * @author Subhashree M
 * @since 4th September, 2024
 */

@Entity
@Table(name = "chatbot_messages")
@Access(AccessType.FIELD)
public class ChatbotMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_seq")
	@SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq", allocationSize = 1)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Column(nullable = false)
    private int sender;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Date timestamp;

    @Column
    private String context; // For chatbot conversation context

    // Getters and Setters
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket supportTicket) {
        this.ticket = supportTicket;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
