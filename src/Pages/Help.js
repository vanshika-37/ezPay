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
import { Button } from "react-bootstrap";


export default function Help() {

    // Get the userId from the context
    const { userId } = useContext(UserContext);
    // State to store tickets raised by a separate user
    const [userTickets, setUserTickets] = useState([]);
    // state to open or close the modal chat
    const [isOpen, setIsOpen] = useState(false);
    // state to get the current selected ticket id
    const [activeTicket, setActiveTicket] = useState(null);

    const [showDropdown, setShowDropdown] = useState(false);
    const filterOptions = ['Resolved', 'Unresolved'];
    const [selectedFilters, setSelectedFilters] = useState([]);
    const [filteredData, setFilteredData] = useState(userTickets);

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

        // console.log("Response from Resolve endpoint :", json);
    }

    // API call to the backend to delete a ticket by its ID
    const deleteTicket = async (ticketId) => {
        let response = await fetch(BASE_URL + `/delete/${ticketId}`, { "method": "DELETE" });
        let json = await response.json();
        handleDelete(ticketId);
        // console.log("Response from Delete endpoint :", json);
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


    // Toggle the dropdown visibility
    const toggleDropdown = () => {
        setShowDropdown(!showDropdown);
    };


    // Handle checkbox changes by updating the selectedFilters state to inclue or exclude the selected option
    const handleCheckboxChange = (option) => {
        setSelectedFilters((prev) =>
        prev.includes(option)
            ? prev.filter((item) => item !== option)
            : [...prev, option]
        );   
    };

    // Update filtered data whenever selectedFilters or userTickets changes
    useEffect(() => {
        const filterData = () => {
            const newFilteredData = userTickets.filter((item) => {
                    // If no filters are selected, return all tickets
                    if (selectedFilters.length === 0) {
                        return true;
                    }

                    // Filter based on the selected status filters ('Resolved' or 'Unresolved')
                    if (selectedFilters.includes('Resolved') && item.status === 'RESOLVED') {
                        return true;
                    }

                    if (selectedFilters.includes('Unresolved') && item.status === 'OPEN') {
                        return true;
                    }

                    return false;
                }
            );
            setFilteredData(newFilteredData);
        };

        filterData();
    }, [selectedFilters, userTickets]); // Triggers whenever selectedFilters or userTickets changes


    return (
        <div className="help-page">
            <div className="help-header">
                <Header />
            </div>
            <div className="help-body">
                <div className="help-title">
                    <h2>TICKETS</h2>

                    {/* Dropdown to filter tickets */}
                    <div style={{display: "flex", gap: "10px", justifyContent: "center"}}>
                        <div className="help-filter-add" style={{position: "relative", display: "inline-block"}}>
                            <Button variant="primary" className="dropdown-btn" onClick={toggleDropdown}>
                                Filter Tickets
                            </Button>
                            {showDropdown && (
                                <div style={{position: "absolute", border: "1px solid #ccc", backgroundColor: "white", zIndex: 1000, boxShadow: "0px 4px 8px rgba(0, 0, 0, 0.1)", padding:"10px"}}>
                                    {filterOptions.map((option) => (
                                        <div key={option} className="dropdown-item" style={{display: "flex", alignItems: "center"}}>
                                            {/* Checkbox for each option */}
                                            <input
                                                type="checkbox"
                                                checked={selectedFilters.includes(option)}
                                                onChange={() => handleCheckboxChange(option)}
                                            />
                                            <label>{option}</label>
                                        </div>
                                    ))}
                                </div>
                            )}
                        </div>
                        {/* Button to create a new ticket */}
                        <CreateTicket className="help-create-btn" userId={userId} onTicketAdded={addNewTicket} />
                    </div>
                </div>

                {/* Ticket cards displaying each user ticket */}
                <div className="help-cards">
                    {
                        // If no filtered data, display 'No Tickets Found'
                        filteredData.length === 0 
                        ? 
                            <div style={{display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", textAlign: "center"}}>
                                <h4>No Tickets Found. </h4> 
                                <h4>Create a new ticket to get started.</h4>
                            </div>
                        :
                            filteredData.map((ticket, key) => {
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