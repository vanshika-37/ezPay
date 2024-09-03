import Header from "./Header.js";
import { useState, useEffect } from "react";

export default function Home() {

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');


    useEffect(()=>{
        setTitle('EzPay');
        setContent('A Secure and Efficient Digital Payment Solution');
    },[])

    return (
        <div>
        <Header />
        <main className="hero-section">
          <div className="hero-content">
            <h1>Welcome to {title}</h1>
            <p>{content}</p>
          </div>
        </main>
      </div>
    )
}