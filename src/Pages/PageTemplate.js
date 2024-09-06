import Header from "./Header.js";
import { useState, useEffect } from "react";

/* 
    @author: Vanshika Sood
    @since: 6th September 2024 
    PageTemplate Component is used to do render the content for each page
*/

export default function PageTemplate(props) {
    // useState to manage the 'title' state, which will display the page's title
    const [title, setTitle] = useState('');

    // useEffect hook to update the 'title' whenever props.title changes
    useEffect(() => {
        setTitle(props.title);
    }, [props.title]); // Dependency array ensures that the effect runs only when props.title changes

    return (
        <div className="home-page-component d-flex flex-column">
            <Header />

            {/* Main content area (hero section) */}
            <main className="hero-section d-flex text-center justify-content-center align-items-center flex-grow-1">
                <h1 className="display-3 fw-bold" style={{ margin: "0 auto" }}>Welcome to, {title}</h1>
            </main>
        </div>
    );
}
