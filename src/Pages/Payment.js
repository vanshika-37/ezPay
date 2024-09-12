import { useEffect, useState } from "react";
import PageTemplate from "./PageTemplate";
import { BASE_URL } from "../constants/app.constants";

/**
    @author: Vanshika Sood
    @since: 6th September 2024 
    Payment Component is used to do fetch content for the Payment page
**/

export default function Payment() {
    // Initialize 'title' state to store the data fetched from the API, starting as an empty string.
    const [title, setTitle] = useState('');

    // useEffect hook to run a side effect (fetching data) when the component is mounted.
    // Asynchronous function to fetch the payment title from the API.
    useEffect(() => {
        const fetchTitle = async () => {
            try {
                // Send a GET request to the API endpoint to retrieve the payment information.
                const response = await fetch(BASE_URL + '/payment');
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
