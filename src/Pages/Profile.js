import { useContext, useEffect, useState } from "react";
import { UserContext } from "../context/UserContext";
import { Button, Form, Alert, Container, Row, Col } from 'react-bootstrap';
import { BASE_URL } from "../constants/app.constants.js";
import Header from "./Header";
import '../styles/Profile.css';

/**
    @author: Vanshika Sood
    @since: 6th September 2024 
    Profile Component is used to do fetch content for the Profile page
    It allows the user to update their User ID i.e. switch to a different user ID
**/


export default function Profile() {
    const { userId, setUserId } = useContext(UserContext); // Get userId and setter from context
    const [newUserId, setNewUserId] = useState(userId);    // Local state for form input
    const [alertMessage, setAlertMessage] = useState('');  // Local state for alert message if userId is updated
    const [alertVariant, setAlertVariant] = useState('');  // Variant of the alert ('success' or 'danger')
    const [title, setTitle] = useState(''); // State variable to store the title of the page

    useEffect(() => {
        const fetchTitle = async () => {
            const response = await fetch(BASE_URL + '/profile');
            const data = await response.text();
            setTitle(data);
        };
        fetchTitle();
    }, []);

    // Input change handler
    const handleUserIdChange = (e) => {
        setNewUserId(e.target.value); // Update local state
    };

    // Function to update the user ID
    const updateUserId = () => {
        if (isNaN(newUserId) || newUserId.trim() === '' || parseInt(newUserId) <= 0) {
            // If userId is not a valid number or negative, set an error message
            setAlertMessage('Invalid User ID. Please enter a valid positive non-zero number.');
            setAlertVariant('danger');
        } else {
            try {
                // Simulating success/failure
                setUserId(newUserId); // Update the global state with the new userId
                setAlertMessage('User ID updated successfully!');
                setAlertVariant('success');
                // Auto-hide alert after 3 seconds
                setTimeout(() => setAlertMessage(''), 3000);
            } catch (error) {
                // If an error occurs
                setAlertMessage('Failed to update User ID. Please try again.');
                setAlertVariant('danger');
            }
        }
    };

    return (
        <div className="profile-page">
            <Header />
            <Container className="mt-5">
                <Row className="justify-content-center">
                    <Col xs={12} md={6} lg={4}>
                        <div className="profile-container p-4 bg-white rounded">
                            <h1 className="text-center fw-bold mb-4">
                                Welcome to Your {title}, User {userId}
                            </h1>

                            <Form>
                                <Form.Group className="mb-4">
                                    <Form.Label className="fw-semibold">Switch to User ID:</Form.Label>
                                    <Form.Control
                                        type="number"
                                        value={newUserId}
                                        onChange={handleUserIdChange}
                                        isInvalid={alertVariant === 'danger'} // Highlight input in case of error
                                        className="py-2"
                                    />
                                </Form.Group>
                                <Button
                                    onClick={updateUserId}
                                    variant="primary"
                                    block
                                    className="w-100 py-2"
                                    style={{ fontSize: '1.1rem' }}
                                >
                                    Update User ID
                                </Button>
                            </Form>

                            {/* Alert message shown after update attempt */}
                            {alertMessage && (
                                <Alert variant={alertVariant} className="mt-3" dismissible>
                                    {alertMessage}
                                </Alert>
                            )}
                        </div>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}
