import { useState, useEffect } from "react";
import PageTemplate from "./PageTemplate";

export default function Balance() {
    const [title, setTitle] = useState('');

    useEffect(() => {
        const fetchTitle = async () => {
            const response = await fetch('http://localhost:8090/api/support/balance');
            const data = await response.text();
            setTitle(data);
        };
    
        fetchTitle();
    }, []);
    

    return <PageTemplate title={title} />;
}
