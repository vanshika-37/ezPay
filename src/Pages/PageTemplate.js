import Header from "./Header.js";
import { useEffect, useState, useContext } from "react";
import { UserContext } from "../context/UserContext";

/**
    @author: Vanshika Sood
    @since: 6th September 2024 
    PageTemplate Component is used to do render the content for each page
**/

export default function PageTemplate(props) {
    // useState to manage the 'title' state, which will display the page's title
    const [title, setTitle] = useState('');

    // Get the userId from the context
    const { userId } = useContext(UserContext);

    // useEffect hook to update the 'title' whenever props.title changes
    useEffect(() => {
        setTitle(props.title);
    }, [props.title]); // Dependency array ensures that the effect runs only when props.title changes

    return (
        <div className="home-page-component d-flex flex-column">
            <Header />

            {/* Main content area (hero section) */}
            <main className="hero-section d-flex flex-column text-center justify-content-center align-items-center flex-grow-1">
                <h1 className="display-3 fw-bold" style={{ margin: "0 auto" }}>Welcome to, {title}</h1>
                <p className="lead fs-1 mt-4">User ID: {userId}</p>
            </main>
        </div>
    );
}
