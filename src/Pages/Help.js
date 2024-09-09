import { useEffect, useState } from "react";
import Header from "./Header";
import TicketCard from "./TicketCard";
import "../styles/help.css";
import CreateTicket from "./CreateTicket";
import ChatDialog from "./ChatDialog";

/* 
    @author: Vaishnave JP, Subhashree M
    @since: 6th September 2024 
    Help Component is used to do call the API endpoints for ticket related queries, like fetching, resolving and deleting. It also calls the TicketCard component for rendering each ticket
*/


export default function Help() {

    const base = "http://localhost:8090/api/support"; // Base URL for API requests
    const userId = 5678; // Static user ID (can be dynamic in a real application)

    // State to store tickets raised by a separate user
    const [userTickets, setUserTickets] = useState([]);

    // API call to the backend to fetch tickets for the current user
    const getUserTickets = async() => {
        let response = await fetch(base + `/gettick/user/${userId}`, {"method":"GET"});
        let json = await response.json();
        setUserTickets(json);
    }

    // API call to the backend to resolve or unresolve a ticket based on its current status
    const resolveTicket = async(ticketId,status) => {
        let response;
        let json;
        if(status==="PENDING"){
        response = await fetch(base + `/resolveticket/${ticketId}`, {"method":"PUT"});
        json = await response.json();
        }
        else if (status==="RESOLVED"){
            response = await fetch(base + `/unresolveticket/${ticketId}`, {"method":"PUT"});
            json = await response.json();
        }

        // Update the state with the new ticket status to trigger rerender without manual page reload
        setUserTickets(prevUserTicket => 
            prevUserTicket.map(ticket => 
                ticket.ticketId === ticketId ? {...ticket, status: json.status} : ticket
            )
        )

        console.log("Response from Resolve endpoint :", json);
    }

    // API call to the backend to delete a ticket by its ID
    const deleteTicket = async(ticketId) => {
        let response = await fetch(base + `/delete/${ticketId}`, {"method": "DELETE"});
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
        console.log(userTickets);
    }, [])


    // Function to add a new ticket to the state
    const addNewTicket = (newTicket) => {
        setUserTickets(prevUserTickets => [newTicket, ...prevUserTickets]);
    };

    const [isOpen, setIsOpen] = useState(false);
    const [activeTicket, setActiveTicket] = useState(null);
    const [messages, setMessages] = useState([]);

    const toggleChat = (id) => {
        setIsOpen(!isOpen);
        if(isOpen == false) {
            setActiveTicket(id);
            getMessages(id);
        }
        else {
            setActiveTicket(null);
            setMessages([]);
        }

    };

    const getMessages = async (id) => {
        let response = await fetch(base + `/getchat/${id}`, {"method": "GET"});
        let json = await response.json();
        setMessages(json);
    }

    return(
        <div className="help-page">
            <div className="help-header">
                <Header />
            </div>
            <div className="help-body">
                <div className="help-title">
                    <h2>TICKETS</h2>
                    {/* Button to create a new ticket */}
                    <CreateTicket className="help-create-btn" userId={userId}  onTicketAdded={addNewTicket} />
                </div>

                {/* Ticket cards displaying each user ticket */}
                <div className="help-cards">
                    {
                        userTickets.map((ticket, key) => {
                            return <TicketCard 
                                        ticket = {ticket} // Pass the ticket data
                                        resolveHandler = {resolveTicket} // Pass resolve function
                                        chatHandler = {toggleChat} // Pass resolve function
                                        deleteHandler = {deleteTicket} // Pass delete function
                                    />
                        })
                    }
                </div>
            </div>
            {isOpen && <ChatDialog onClose={toggleChat} ticketId={activeTicket} messages1={messages}/>}
        </div>
    )
}