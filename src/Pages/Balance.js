import { useState, useEffect } from "react";
import PageTemplate from "./PageTemplate";

export default function Balance() {
    // useState hook to manage the 'title' state. Initially, it's an empty string.
    const [title, setTitle] = useState('');

    // useEffect hook to perform side effects, like fetching data, when the component mounts.
    // Async function to fetch the title from the API.
    useEffect(() => {
        const fetchTitle = async () => {
            // Sending a GET request to the backend API to fetch the balance title.
            const response = await fetch('http://localhost:8090/api/support/balance');
            // Converting the response to text.
            const data = await response.text();
            // Updating the 'title' state with the fetched data.
            setTitle(data);
        };
    
        // Calling the fetchTitle function immediately when the component is mounted.
        fetchTitle();
    }, []); // Empty dependency array to run this effect only once (on page load).

    // Rendering the PageTemplate component and passing the fetched title as a prop.
    return <PageTemplate title={title} />;
}
