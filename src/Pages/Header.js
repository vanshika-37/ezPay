import React from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import { NavLink } from "react-router-dom";

export default function Header() {
    return (
        <div className="header">
            <Navbar expand="lg">
                <Container>
                    <Navbar.Brand href="/">
                        <h1><i><b>EzPay</b></i></h1>
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
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
