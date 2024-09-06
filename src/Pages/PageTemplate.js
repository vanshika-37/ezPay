import Header from "./Header.js";
import { useState, useEffect } from "react";

export default function PageTemplate(props) {
    const [title, setTitle] = useState('');

    useEffect(() => {
        setTitle(props.title);
    }, [props.title]);

    return (
        <div className="home-page-component d-flex flex-column">
            <Header />
            <main className="hero-section d-flex text-center justify-content-center align-items-center flex-grow-1">
                <h1 className="display-3 fw-bold" style={{ margin: "0 auto" }}>Welcome to, {title}</h1>
            </main>
        </div>
    );
}
