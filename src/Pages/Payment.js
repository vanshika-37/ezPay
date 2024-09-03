import Header from "./Header.js";
import { useState, useEffect } from "react";

export default function Payment() {

    const [title, setTitle] = useState('');

    useEffect(() => {
        setTitle('Payment');
    }, [])
    return (
        <div >
            <Header />
            <main className="hero-section">
                <div className="hero-content">
                    <h1>Welcome to, {title}</h1>
                </div>
            </main>
        </div>
    )
}