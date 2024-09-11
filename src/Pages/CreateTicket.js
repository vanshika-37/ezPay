import React, { useState } from 'react';
import { Button, Modal, Form, Alert } from 'react-bootstrap';
import { BASE_URL } from '../constants/app.constants';

/**
 * A component for creating support tickets. It renders a modal where users can enter the issue description
 * and submit a ticket. The component also handles form validation and alerts the user about success or failure.
 * 
 * @author: Vanshika Sood
 * @since: 6th September 2024
 */
const CreateTicket = ({ userId, onTicketAdded }) => {
    // State variables
    const [show, setShow] = useState(false); // Controls the visibility of the modal
    const [issueDescription, setIssueDescription] = useState(''); // Holds the entered issue description
    const [alertMessage, setAlertMessage] = useState(''); // Displays success or error messages
    const [validated, setValidated] = useState(false); // Controls form validation

    // This defines the max length for the issue description
    const maxDescriptionLength = 255;

    /**
     * Closes the modal and resets form validation and alert messages.
     */
    const handleClose = () => {
        setShow(false);
        setAlertMessage('');
        setValidated(false); // Reset validation state
    };

    /**
     * Opens the modal for creating a ticket.
     */
    const handleShow = () => setShow(true);

    /**
     * Handles form submission, creates a new ticket, and sends it to the backend API.
     * Validates the form input before making the API request.
     * 
     * @param {Object} e - Event object from form submission.
     */
    const handleSubmit = async (e) => {
        e.preventDefault(); // Prevents default form submission
        const form = e.currentTarget;

        // Check if the form is valid
        if (form.checkValidity() === false) {
            e.stopPropagation();
            setValidated(true); // Mark form as validated if input is invalid
            return;
        }

        setValidated(true); // Set form as validated if input is valid

        // Create a new ticket object
        const newTicket = {
            userId: userId,
            issueDescription: issueDescription,
            status: 'OPEN',
            dateCreated: new Date(),
            dateResolved: null,
        };

        try {
            // Send a POST request to the backend to create a new ticket
            const response = await fetch(BASE_URL + '/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newTicket), // Send ticket data as JSON
            });

            if (response.ok) {
                // If the request is successful, clear form and display success message
                const createdTicket = await response.json();
                setAlertMessage('Ticket created successfully');
                setIssueDescription(''); // Reset the issue description input
                handleClose(); // Close the modal
                onTicketAdded(createdTicket); // Notify parent component about the new ticket
            } else {
                // Display an error message if the request failed
                setAlertMessage('Failed to create ticket');
            }
        } catch (error) {
            // Catch any errors and display an appropriate error message
            console.error('There was an error in creating the ticket!', error);
            setAlertMessage('There was an error in creating the ticket!');
        }
    };

    return (
        <>
            {/* Button to open the modal */}
            <Button variant="primary" onClick={handleShow} className='create-ticket-btn'>
                Create Ticket
            </Button>

            {/* Modal for creating a support ticket */}
            <Modal show={show} onHide={handleClose} size="lg" centered>
                <Modal.Header closeButton>
                    <Modal.Title>Create a Support Ticket</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {/* Alert message displayed when ticket is created or if there is an error */}
                    {alertMessage && (
                        <Alert variant={alertMessage === 'Ticket created successfully' ? 'success' : 'danger'}>
                            {alertMessage}
                        </Alert>
                    )}
                    {/* Form for entering the issue description */}
                    <Form noValidate validated={validated} onSubmit={handleSubmit}>
                        {/* User ID field (disabled) */}
                        <Form.Group controlId="formUserId">
                            <Form.Label>User ID</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Enter your user ID"
                                value={userId}
                                disabled
                            />
                        </Form.Group>
                        {/* Issue description input */}
                        <Form.Group className='mt-3'>
                            <Form.Label htmlFor="inputIssueDescription">Issue Description</Form.Label>
                            <Form.Control as="textarea" rows={3}
                                placeholder="Enter the issue description"
                                id='inputIssueDescription'
                                value={issueDescription}
                                onChange={(e) => setIssueDescription(e.target.value)}
                                maxLength={maxDescriptionLength}
                                required
                            />
                            <Form.Text className='text-muted'>
                                {issueDescription.length}/{maxDescriptionLength} characters remaining
                            </Form.Text>
                            <Form.Control.Feedback type="invalid">
                                Please provide a valid description of the issue.
                            </Form.Control.Feedback>
                        </Form.Group>
                        {/* Submit button */}
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
