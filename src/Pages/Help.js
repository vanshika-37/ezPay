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

    useEffect(() => {
        getUserTickets();
    }, [])

    useEffect(() => {
        console.log(userTickets);
    }, [userTickets])

    return(
        <div className="help-page">
            <div className="help-header">
                <Header />
            </div>
            <div className="help-body">
                <div className="help-title">
                    <h2>TICKETS</h2>
                    <CreateTicket className="help-create-btn" userId = {userId}/>
                </div>
                <div className="help-cards">
                    {
                        userTickets.map((ticket, key) => {
                            return <TicketCard 
                                        ticket = {ticket}
                                    />
                        })
                    }
                </div>
            </div>
        </div>
    )
}