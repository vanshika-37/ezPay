import React, { useState } from 'react';
import { Button, Modal, Form, Alert } from 'react-bootstrap';

const CreateTicket = ({ userId, onTicketAdded }) => {
    const [show, setShow] = useState(false);
    const [issueDescription, setIssueDescription] = useState('');
    const [alertMessage, setAlertMessage] = useState('');
    const [validated, setValidated] = useState(false);

    const handleClose = () => {
        setShow(false);
        setAlertMessage('');
        setValidated(false); 
    };

    const handleShow = () => setShow(true);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
            setValidated(true);
            return;
        }

        setValidated(true);

        const newTicket = {
            userId: userId,
            issueDescription: issueDescription,
            status: 'PENDING',
            dateCreated: new Date(),
            dateResolved: null,
        };

        try {
            const response = await fetch('http://localhost:8090/api/support/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newTicket),
            });

            if (response.ok) {
                const createdTicket = await response.json();
                setAlertMessage('Ticket created successfully');
                setIssueDescription('');
                handleClose();
                onTicketAdded(createdTicket); 
            } else {
                setAlertMessage('Failed to create ticket');
            }
        } catch (error) {
            console.error('There was an error in creating the ticket!', error);
            setAlertMessage('There was an error in creating the ticket!');
        }
    };

    return (
        <>
            <Button variant="primary" onClick={handleShow}>
                Create Ticket
            </Button>

            <Modal show={show} onHide={handleClose} size="lg" centered>
                <Modal.Header closeButton>
                    <Modal.Title>Create a Support Ticket</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {alertMessage && (
                        <Alert variant={alertMessage === 'Ticket created successfully' ? 'success' : 'danger'}>
                            {alertMessage}
                        </Alert>
                    )}
                    <Form noValidate validated={validated} onSubmit={handleSubmit}>
                        <Form.Group controlId="formUserId">
                            <Form.Label>User ID</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Enter your user ID"
                                value={userId}
                                disabled
                            />
                        </Form.Group>
                        <Form.Group className='mt-3'>
                            <Form.Label htmlFor="inputIssueDescription">Issue Description</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Enter the issue description"
                                id='inputIssueDescription'
                                value={issueDescription}
                                onChange={(e) => setIssueDescription(e.target.value)}
                                required
                            />
                            <Form.Control.Feedback type="invalid">
                                Please provide a valid description of the issue.
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Button variant="primary" type="submit" className='mt-3'>
                            Submit Ticket
                        </Button>
                    </Form>
                </Modal.Body>
            </Modal>
        </>
    );
};

export default CreateTicket;
