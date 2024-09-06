import { useEffect, useState } from "react";
import Header from "./Header";
import TicketCard from "./TicketCard";
import "../styles/help.css";
import CreateTicket from "./CreateTicket";

export default function Help() {

    const base = "http://localhost:8090/api/support";
    const userId = 5678;

    const [userTickets, setUserTickets] = useState([]);

    const getUserTickets = async() => {
        let response = await fetch(base + `/gettick/user/${userId}`, {"method":"GET"});
        let json = await response.json();
        setUserTickets(json);
    }

    const resolveTicket = async(ticketId) => {
        let response = await fetch(base + `/resolveticket/${ticketId}`, {"method":"PUT"});
        let json = await response.json();

        setUserTickets(prevUserTicket => 
            prevUserTicket.map(ticket => 
                ticket.ticketId === ticketId ? {...ticket, status: json.status} : ticket
            )
        )

        console.log("Response from Resolve endpoint :", json);
    }

    const deleteTicket = async(ticketId) => {
        let response = await fetch(base + `/delete/${ticketId}`, {"method": "DELETE"});
        let json = await response.json();
        handleDelete(ticketId);
        console.log("Response from Delete endpoint :", json);
    }

    const handleDelete = (id) => {
        setUserTickets(userTickets.filter(ticket => ticket.ticketId !== id));
    }

    useEffect(() => {
        getUserTickets();
    }, [])

    useEffect(() => {
        console.log(userTickets);
    }, [userTickets])

    const addNewTicket = (newTicket) => {
        setUserTickets(prevUserTickets => [newTicket, ...prevUserTickets]);
    };

    return(
        <div className="help-page">
            <div className="help-header">
                <Header />
            </div>
            <div className="help-body">
                <div className="help-title">
                    <h2>TICKETS</h2>
                    <CreateTicket className="help-create-btn" userId={userId}  onTicketAdded={addNewTicket} />
                </div>
                <div className="help-cards">
                    {
                        userTickets.map((ticket, key) => {
                            return <TicketCard 
                                        ticket = {ticket}
                                        resolveHandler = {resolveTicket}
                                        deleteHandler = {deleteTicket}
                                    />
                        })
                    }
                </div>
            </div>
        </div>
    )
}