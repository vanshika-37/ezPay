import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Modal from 'react-bootstrap/Modal';

/**
 * TicketCard component displays individual support tickets as cards.
 * It shows basic ticket information like ID, issue description, and status.
 * The component also includes buttons for resolving/unresolving and deleting tickets.
 * A modal is shown when the ticket card is clicked, displaying detailed ticket info.
 * 
 * 
 * @author: Vaishnave JP
 * @since: 6th September 2024
 */


export default function TicketCard(props) {
    // State variable to control the visibility of the modal
    const [show, setShow] = useState(false);

    /**
     * Toggles the modal visibility on card click.
     */
    const handleModalShow = () => {
        setShow(!show); // Toggle the modal visibility
    }

    return (
        <div className='ticket-cards'>
            <Card className='card'>
                <Card.Body className='card-body'>
                    <div onClick={handleModalShow}>
                        <Card.Title>
                            #{props.ticket.ticketId}
                        </Card.Title>
                        <Card.Text className='text-truncate'>
                            <h5>{props.ticket.issueDescription}</h5>
                            <p>Status: {props.ticket.status}</p>
                        </Card.Text>
                    </div>
                    <div className='card-btns'>

                        {/* Button to resolve or unresolve the ticket based on its current status */}
                        <Button
                            variant={props.ticket.status === 'OPEN' || props.ticket.status === 'PENDING' ? 'success' : 'warning'}
                            onClick={() => props.resolveHandler(props.ticket.ticketId, props.ticket.status)}
                        >
                            {props.ticket.status === 'OPEN' || props.ticket.status === 'PENDING' ? 'Resolve' : 'Unresolve'}
                        </Button>
                        
                        {/* Button to show the chat */}
                        <Button
                            variant="primary"
                            onClick={() => props.chatHandler(props.ticket.ticketId)}
                        >
                            Chat
                        </Button>

                        {/* Button to delete the ticket */}
                        <Button
                            variant="danger"
                            onClick={() => props.deleteHandler(props.ticket.ticketId)}
                        >
                            Delete
                        </Button>
                    </div>
                </Card.Body>
            </Card>


            <Modal show={show} onHide={handleModalShow}>
                <Modal.Header closeButton>
                    <Modal.Title>{props.ticket.issueDescription}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p><b>Created Date:</b> {props.ticket.dateCreated}</p>  {/* Shows when the ticket was created */}
                    {props.ticket.status === 'OPEN' || props.ticket.status === 'PENDING' ? null : <p><b>Resolved Date:</b> {props.ticket.dateResolved}</p>} {/* Shows when the ticket was resolved, if applicable */}
                    <p><b>Status:</b> {props.ticket.status}</p>
                </Modal.Body>
            </Modal>
        </div>
    )
}