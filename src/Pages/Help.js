/** 
 *  Help Component calls the API endpoints for ticket related queries - fetching, resolving and deleting. 
 *  It also calls the TicketCard component for rendering each ticket.
 *  It display the chat modal if the user clicks on the show button.
 *  It opens the add ticket modal when the create ticket button is clicked.
    @author: Vaishnave JP
    @since: 6th September 2024 
**/

import { useEffect, useState, useContext } from "react";
import { UserContext } from "../context/UserContext";
import Header from "./Header";
import TicketCard from "./TicketCard";
import "../styles/help.css";
import CreateTicket from "./CreateTicket";
import ChatPage from "./ChatPage";
import { BASE_URL } from "../constants/app.constants";


export default function Help() {

    // Get the userId from the context
    const { userId } = useContext(UserContext);
    // State to store tickets raised by a separate user
    const [userTickets, setUserTickets] = useState([]);
    // state to open or close the modal chat
    const [isOpen, setIsOpen] = useState(false);
    // state to get the current selected ticket id
    const [activeTicket, setActiveTicket] = useState(null);

    // API call to the backend to fetch tickets for the current user
    const getUserTickets = async () => {
        let response = await fetch(BASE_URL + `/gettick/user/${userId}`, { "method": "GET" });
        let json = await response.json();
        setUserTickets(json);
    }

    // API call to the backend to resolve or unresolve a ticket based on its current status
    const resolveTicket = async (ticketId, status) => {
        let response;
        let json;
        if (status === "OPEN" || status === "PENDING") {
            response = await fetch(BASE_URL + `/resolveticket/${ticketId}`, { "method": "PUT" });
            json = await response.json();
        }
        else if (status === "RESOLVED") {
            response = await fetch(BASE_URL + `/unresolveticket/${ticketId}`, { "method": "PUT" });
            json = await response.json();
        }

        // Update the state with the new ticket status to trigger rerender without manual page reload
        setUserTickets(prevUserTicket =>
            prevUserTicket.map(ticket =>
                ticket.ticketId === ticketId ? { ...ticket, status: json.status, dateResolved: json.dateResolved } : ticket
            )
        )

        console.log("Response from Resolve endpoint :", json);
    }

    // API call to the backend to delete a ticket by its ID
    const deleteTicket = async (ticketId) => {
        let response = await fetch(BASE_URL + `/delete/${ticketId}`, { "method": "DELETE" });
        let json = await response.json();
        handleDelete(ticketId);
        console.log("Response from Delete endpoint :", json);
    }

    // Function to filter out the deleted ticket from the state
    const handleDelete = (id) => {
        setUserTickets(userTickets.filter(ticket => ticket.ticketId !== id));
    }

    // useEffect to fetch tickets when the component is first rendered
    useEffect(() => {
        getUserTickets();
    }, []); 


    // Function to add a new ticket to the state
    const addNewTicket = (newTicket) => {
        setUserTickets(prevUserTickets => [newTicket, ...prevUserTickets]);
    };

    // open and close the chat modal
    const toggleChat = (id) => {
        setIsOpen(!isOpen);
        if (isOpen == false) {
            setActiveTicket(id);
        }
        else {
            setActiveTicket(null);
        }
    };

    return (
        <div className="help-page">
            <div className="help-header">
                <Header />
            </div>
            <div className="help-body">
                <div className="help-title">
                    <h2>TICKETS</h2>
                    {/* Button to create a new ticket */}
                    <CreateTicket className="help-create-btn" userId={userId} onTicketAdded={addNewTicket} />
                </div>

                {/* Ticket cards displaying each user ticket */}
                <div className="help-cards">
                    {
                        userTickets.map((ticket, key) => {
                            return <TicketCard
                                ticket={ticket} // Pass the ticket data
                                resolveHandler={resolveTicket} // Pass resolve function
                                chatHandler={toggleChat} // Pass resolve function
                                deleteHandler={deleteTicket} // Pass delete function
                            />
                        })
                    }
                </div>
            </div>
            {/* open the chat modal if selected */}
            {isOpen &&
                <ChatPage
                    isOpen={isOpen}
                    onClose={toggleChat}
                    ticketId={activeTicket}
                />
            }
        </div>
    )
}