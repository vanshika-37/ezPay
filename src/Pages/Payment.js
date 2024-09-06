import { useState, useEffect } from "react";
import PageTemplate from "./PageTemplate";

export default function Payment() {
    // Initialize 'title' state to store the data fetched from the API, starting as an empty string.
    const [title, setTitle] = useState('');

    // useEffect hook to run a side effect (fetching data) when the component is mounted.
    // Asynchronous function to fetch the payment title from the API.
    useEffect(() => {
        const fetchTitle = async () => {
            try {
                // Send a GET request to the API endpoint to retrieve the payment information.
                const response = await fetch('http://localhost:8090/api/support/payment');
                // Convert the response to text format.
                const data = await response.text();
                // Update the 'title' state with the fetched data.
                setTitle(data);
            } catch (error) {
                console.error("Error fetching payment title:", error);
            }
        };

        // Execute the fetchTitle function immediately when the component is mounted.
        fetchTitle();
    }, []); // Empty dependency array means the effect will only run once on page load.

    // Render the PageTemplate component, passing the fetched title as a prop.
    return <PageTemplate title={title} />;
}
