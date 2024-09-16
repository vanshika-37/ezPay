/** 
    To make an api call to get all the messages for a given ticket raised by the current user and display it, along with the option to send new queries.
    @author: Subhashree M, Vaishnave JP
    @since: 6th September 2024 
**/

import React, { useRef, useState, useEffect } from 'react';
import { Button, Modal } from 'react-bootstrap';
import { BASE_URL } from '../constants/app.constants';
import '../styles/ChatDialog.css';

export default function ChatPage({ isOpen, onClose, ticketId }) {

    // const base = "http://localhost:8090/api/support";
    const [inputMessage, setInputMessage] = useState("");
    const [myMessages, setMyMessages] = useState([]);
    const chatContentRef = useRef(null);

    // API call to backend to get messages for a particular ticket of current user
    const getMessages = async (id) => {
        let response = await fetch(BASE_URL + `/getchat/${id}`, { "method": "GET" });
        let json = await response.json();

        setMyMessages(json);
        scrollToBottom();
    }

    // Call API to get messages in DB when page is loaded
    useEffect(() => {
        getMessages(ticketId);
    }, []);

    // Ensure scroll happens after messages update
    useEffect(() => {
        scrollToBottom();
    }, [myMessages]);

    // send button is diabled if input is empty
    const isSendButtonDisabled = !inputMessage.trim();

    // update input message
    const handleInputChange = (e) => {
        setInputMessage(e.target.value);
    }

    // enter button to send message to backend
    const handleKeyDown = (e) => {
        if (e.key === 'Enter' && !e.shiftKey) {
            e.preventDefault(); // Prevent newline in textarea
            sendMessage();
        }
    };

    // Scroll to the bottom of the chat content
    const scrollToBottom = () => {
        if (chatContentRef.current) {
            chatContentRef.current.scrollTop = chatContentRef.current.scrollHeight;
        }
    };

    // Method to handle sending a message
    const sendMessage = async () => {

        if (inputMessage.trim() === '') return;

        // Send user message to the backend
        try {
            const response = await fetch(BASE_URL + `/sendusermessage/${ticketId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ "message": inputMessage }),
            });
            const responseData = await response.json();

            // Update the state with the new user and bot messages
            setMyMessages((prevMessages) => [...prevMessages, ...responseData]);
            setInputMessage('');

            scrollToBottom();

        } catch (error) {
            console.error('Error sending message:', error);
        }
    };

    return (
        <div>
            <Modal show={isOpen} onHide={onClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Support Chat #{ticketId}</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                    
                            <div className="chat-content" ref={chatContentRef}>
                            {
                                myMessages.length === 0 ?
                                    <h6 style={{ "textAlign": "center" }}>
                                        <i>No Messages Yet</i>
                                    </h6>
                            :
                                <>{myMessages.map((msg, index) => (
                                    <div key={index} className={msg.sender === 1 ? 'user-msg' : 'bot-msg'}>
                                        <span>{msg.message}</span>
                                    </div>
                                ))}</>
                            }
                            </div>
                    

                    <div className="chat-input">
                        <textarea
                            type="text"
                            value={inputMessage}
                            onChange={handleInputChange}
                            onKeyDown={handleKeyDown}
                            placeholder="Type a message..."
                        />
                        <Button
                            onClick={sendMessage}
                            style={{ border: "none", width: "100px", height: "40px", textAlign: "cente" }}
                            variant={inputMessage.length == 0 ? 'secondary' : 'primary'}
                            disabled={isSendButtonDisabled}
                        >
                            Send
                        </Button>
                    </div>
                </Modal.Body>
            </Modal>
        </div>
    )
}
