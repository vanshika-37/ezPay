import Header from "./Header.js";
import { useState, useEffect } from "react";

export default function Balance() {

    const [title, setTitle] = useState('');

    useEffect(() => {
        setTitle('Balance');
    }, [])
    return (
        <div >
            <Header />
            <div className="component-page">
                <h1>Welcome to, {title}</h1>
            </div>
        </div>
    )
}