import Header from "./Header.js";
import { useState, useEffect } from "react";

export default function Profile(){

    const [title, setTitle] = useState('');

    useEffect(()=>{
        setTitle('Profile');
    },[])
    return (
        <div className="component-page">
        <Header/>
        <h1>Welcome to, {title}</h1>
        </div>
    )
}