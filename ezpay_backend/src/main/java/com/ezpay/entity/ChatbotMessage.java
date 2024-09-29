package com.ezpay.entity;

import java.util.Date;
import jakarta.persistence.*;

/**
 * Represents a message exchanged in a chatbot conversation, either from the user or the chatbot.
 * It is linked to a support ticket, and the message can be tracked with a timestamp and optional context.
 * 
 * @author Subhashree M
 * @since 4th September, 2024
 */

@Entity  // Marks this class as a JPA entity that maps to a database table.
@Table(name = "chatbot_messages")  // Specifies the table name for this entity in the database.
@Access(AccessType.FIELD)  // Specifies that field-level access is used for persistence.
public class ChatbotMessage {

    @Id  // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_seq")  // Specifies sequence-based primary key generation.
    @SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq", allocationSize = 1)  // Defines the sequence generator for the message ID.
    private Long messageId;  // Unique identifier for each message.

    @ManyToOne  // Defines a many-to-one relationship with the Ticket entity.
    @JoinColumn(name = "ticket_id", nullable = false)  // Specifies the foreign key column in the database and ensures it's not null.
    private Ticket ticket;  // The support ticket to which this message is linked.

    @Column(nullable = false)  // Indicates that this column cannot be null.
    private int sender;  // Represents the sender of the message (0 for User, 1 for Chatbot).

    @Column(nullable = false)  // Ensures this column is not null.
    private String message;  // The message content, either from the user or the chatbot.

    @Column(nullable = false)  // Timestamp column, cannot be null.
    private Date timestamp;  // The time when the message was sent.

    @Column  // Optional field for chatbot context in the conversation.
    private String context;  // Context for the chatbot conversation, can store information for follow-up responses.

    // Getters and Setters

    /**
     * Returns the unique message ID.
     * 
     * @return The message ID.
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * Sets the message ID.
     * 
     * @param messageId The unique identifier for the message.
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * Returns the support ticket associated with this message.
     * 
     * @return The ticket object.
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * Sets the support ticket for this message.
     * 
     * @param supportTicket The associated support ticket.
     */
    public void setTicket(Ticket supportTicket) {
        this.ticket = supportTicket;
    }

    /**
     * Returns the sender of the message.
     * 
     * @return The sender (0 for User, 1 for Chatbot).
     */
    public int getSender() {
        return sender;
    }

    /**
     * Sets the sender of the message.
     * 
     * @param sender The sender (0 for User, 1 for Chatbot).
     */
    public void setSender(int sender) {
        this.sender = sender;
    }

    /**
     * Returns the message content.
     * 
     * @return The message text.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message content.
     * 
     * @param message The content of the message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the timestamp of when the message was sent.
     * 
     * @return The timestamp.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp for the message.
     * 
     * @param timestamp The time when the message was sent.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the context for the chatbot conversation.
     * 
     * @return The context string.
     */
    public String getContext() {
        return context;
    }

    /**
     * Sets the context for the chatbot conversation.
     * 
     * @param context The conversation context.
     */
    public void setContext(String context) {
        this.context = context;
    }
}
