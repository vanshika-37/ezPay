import React from "react";
import './Header.css';
import { NavLink } from "react-router-dom";

export default function Header() {
    return (
        <header className="header">
            <div className="logo">
                <a href="/">
                    <h1><i>EzPay</i></h1>
                </a>
            </div>
            <nav className="nav">
                <ul>
                    <li><NavLink to="/" className={({ isActive }) => isActive ? "active" : ""}>Home</NavLink></li>
                    <li><NavLink to="/balance" className={({ isActive }) => isActive ? "active" : ""}>Balance</NavLink></li>
                    <li><NavLink to="/payment" className={({ isActive }) => isActive ? "active" : ""}>Payment</NavLink></li>
                    <li><NavLink to="/help" className={({ isActive }) => isActive ? "active" : ""}>Help</NavLink></li>
                    <li><NavLink to="/profile" className={({ isActive }) => isActive ? "active" : ""}>Profile</NavLink></li>
                </ul>
            </nav>
            <div className="profile">
                <a href="/">
                    <img src="/profilePicture.jpg" alt="Profile" className="profile-image" />
                </a>
            </div>
        </header>
    );
}