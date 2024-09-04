import Header from "./Header.js";
import { useState, useEffect } from "react";

export default function Help() {

    const [title, setTitle] = useState('');

    useEffect(() => {
        setTitle('Help');
    }, [])
    return (
        <div>
            <div className="home-page-component d-flex flex-column justify-content-center">
                <Header />
                <main className="hero-section d-flex text-center">
                    <h1 className="display-3 fw-bold" style={{ "margin": "0 auto" }}>Welcome to, {title}</h1>
                </main >
            </div>
        </div>
    )
}