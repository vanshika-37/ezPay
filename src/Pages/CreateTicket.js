import React, { useState } from 'react';
import { Button, Modal, Form } from 'react-bootstrap';

const CreateTicket = ({ userId }) => {
  const [show, setShow] = useState(false);
  const [issueDescription, setIssueDescription] = useState('');

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const newTicket = {
      userId: userId,
      issueDescription: issueDescription,
      status: 'PENDING',
      dateCreated: new Date(),
      dateResolved: null,
    };

    try {
      const response = await fetch('http://localhost:8080/api/support/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newTicket),
      });

      if (response.ok) {
        alert('Ticket created successfully');
        handleClose();
      } else {
        alert('Failed to create ticket');
        console.error('Error:', response.statusText);
      }
    } catch (error) {
      console.error('There was an error creating the ticket!', error);
      alert('Failed to create ticket');
    }
  };

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Create Ticket
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Create a Support Ticket</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formUserId">
              <Form.Label>User ID</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter your user ID"
                value={userId}
                disabled
              />
            </Form.Group>
            <Form.Group controlId="formIssueDescription">
              <Form.Label>Issue Description</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter the issue description"
                value={issueDescription}
                onChange={(e) => setIssueDescription(e.target.value)}
                required
              />
            </Form.Group>
            <Button variant="primary" type="submit" className='mt-2'>
              Submit
            </Button>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
};

export default CreateTicket;
