import React, { Component, createRef } from 'react';
import '../styles/ChatDialog.css'; // CSS for translucent background


/*
    @author: Subhashree M
    @since: 6th September 2024 
*/

class ChatDialog extends Component {
  constructor(props) {
    super(props);
    this.state = {
      messages: [],
      inputMessage: '',
    };
    this.chatContentRef = createRef(); // Create a ref for the chat content
  }

  componentDidUpdate(prevProps) {
    // Update the state with new messages from props if they change
    if (prevProps.messages1 !== this.props.messages1) {
      this.setState({ messages: this.props.messages1 }, this.scrollToBottom);
      this.scrollToBottom();
    }
  }

  // Scroll to the bottom of the chat content
  scrollToBottom = () => {
    if (this.chatContentRef.current) {
      this.chatContentRef.current.scrollTop = this.chatContentRef.current.scrollHeight;
    }
  };

  // Method to handle sending a message
  sendMessage = async () => {
    const { inputMessage, messages } = this.state;
    const { ticketId } = this.props;
    
    if (inputMessage.trim() === '') return;

    const userMessage = { message: inputMessage };

    // Send user message to the backend
    try {
      const response = await fetch(`http://localhost:8090/api/support/sendusermessage/${ticketId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ "message": inputMessage }),
      });
      const responseData = await response.json();

      // Update the state with the new user and bot messages
      this.setState({
        messages: [...messages, ...responseData],
        inputMessage: '',
      });

      this.scrollToBottom();
      
    } catch (error) {
      console.error('Error sending message:', error);
    }
  };

  // Method to handle input change
  handleInputChange = (e) => {
    this.setState({ inputMessage: e.target.value });
  };

  render() {
    const { messages, inputMessage } = this.state;
    const { onClose, ticketId } = this.props;

    return (
      <div className="chat-overlay">
        <div className="chat-box">
          <div className="chat-header">
            <h4>Support Chat #{ticketId}</h4>
            <button onClick={onClose}>Close</button>
          </div>
          <div className="chat-content" ref={this.chatContentRef}>
            {messages.map((msg, index) => (
              <div key={index} className={msg.sender === 'User' ? 'user-msg' : 'bot-msg'}>
                <span>{msg.message}</span>
              </div>
            ))}
          </div>
          <div className="chat-input">
            <input
              type="text"
              value={inputMessage}
              onChange={this.handleInputChange}
              placeholder="Type a message..."
            />
            <button onClick={this.sendMessage}>Send</button>
          </div>
        </div>
      </div>
    );
  }
}

export default ChatDialog;
