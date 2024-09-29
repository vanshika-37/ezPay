import React from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import { NavLink } from "react-router-dom";

/**
    @author: Vanshika Sood
    @since: 6th September 2024 
    Header component to reuse in other pages. This consists of links to other pages.
**/

export default function Header() {
    return (
        <div className="header">
            <Navbar expand="lg" variant="dark">
                <Container>
                    <Navbar.Brand href="/">
                        <h1><i><b>EzPay</b></i></h1>
                    </Navbar.Brand>
                    {/* Button to toggle navbar visibility on smaller screens */}
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    {/* Collapsible navbar content (links) */}
                    <Navbar.Collapse className="justify-content-end" id="basic-navbar-nav">
                        <Nav className="header-navbar-items">
                            <Nav.Link as={NavLink} to="/" className={({ isActive }) => isActive ? "active" : ""}>Home</Nav.Link>
                            <Nav.Link as={NavLink} to="/balance" className={({ isActive }) => isActive ? "active" : ""}>Balance</Nav.Link>
                            <Nav.Link as={NavLink} to="/payment" className={({ isActive }) => isActive ? "active" : ""}>Payment</Nav.Link>
                            <Nav.Link as={NavLink} to="/help" className={({ isActive }) => isActive ? "active" : ""}>Help</Nav.Link>
                            <Nav.Link as={NavLink} to="/profile" className={({ isActive }) => isActive ? "active" : ""}>Profile</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </div>
    );
}
