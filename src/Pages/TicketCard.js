import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Modal from 'react-bootstrap/Modal';

export default function TicketCard(props) {

    const [show, setShow] = useState(false);
    const handleModalShow = () => {
        setShow(!show);
    }

    return (
        <div className='ticket-cards'>
            <Card className='card'>
                <Card.Body className='card-body'>
                    <div onClick={handleModalShow}>
                        <Card.Title>
                            #{props.ticket.ticketId}
                        </Card.Title>
                        <Card.Text>
                            <h5>{props.ticket.issueDescription}</h5>
                            <p>Status: {props.ticket.status}</p>
                        </Card.Text>
                    </div>
                    <div className='card-btns'>
                        <Button 
                            variant={props.ticket.status == 'OPEN' ? 'success' : 'primary'} 
                            onClick={() => props.resolveHandler(props.ticket.ticketId)}
                        >
                            {props.ticket.status == 'OPEN' ? 'RESOLVE' : 'UNRESOLVE'}
                        </Button>

                        <Button 
                            variant="danger"
                            onClick={() => props.deleteHandler(props.ticket.ticketId)}
                        >
                            Delete Ticket
                        </Button>
                    </div>
                </Card.Body>
            </Card>


            <Modal show={show} onHide={handleModalShow}>
                <Modal.Header closeButton>
                <Modal.Title>{props.ticket.issueDescription}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>Created Date: {props.ticket.dateCreated}</p>
                    {props.ticket.status == 'OPEN' ? null : <p>Resolved Date: {props.ticket.dateResolved}</p>}
                    <p>Status: {props.ticket.status}</p>
                </Modal.Body>
            </Modal>
        </div>
    )
}
