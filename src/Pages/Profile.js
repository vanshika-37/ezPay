import { useState, useEffect } from "react";
import PageTemplate from "./PageTemplate";

export default function Profile() {
    // Initialize 'title' state to store the data fetched from the API, starting as an empty string.
    const [title, setTitle] = useState('');

    // useEffect hook to run a side effect (fetching data) when the component first mounts.
    // Asynchronous function to fetch the profile title from the API.
    useEffect(() => {
        const fetchTitle = async () => {
            try {
                // Send a GET request to the API endpoint to retrieve the profile information.
                const response = await fetch('http://localhost:8090/api/support/profile');
                // Convert the response to text format.
                const data = await response.text();
                // Update the 'title' state with the fetched data.
                setTitle(data);
            } catch (error) {
                console.error("Error fetching profile title:", error);
            }
        };

        // Execute the fetchTitle function when the component is mounted.
        fetchTitle();
    }, []); // The empty dependency array ensures the effect runs only once (on mount).

    // Render the PageTemplate component, passing the fetched title as a prop.
    return <PageTemplate title={title} />;
}
