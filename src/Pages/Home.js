import React, { useState, useEffect } from "react";
import Header from "./Header.js";
import { Container, Row, Col, Image } from "react-bootstrap";

export default function Home() {

  // useState to manage 'title' and 'content' state variables
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  useEffect(() => {
    // Function to fetch the home page title from the API
    const fetchTitle = async () => {
      const response = await fetch('http://localhost:8090/api/support/home');
      const data = await response.text();
      setTitle(data);
  };

  fetchTitle();

    // Setting static content for the home page
    setContent('A Secure and Efficient Digital Payment Solution');
  }, []);

  return (
    <div>
      <div className="home-page-component d-flex flex-column">
        <Header />
        {/* Main hero section of the home page */}
        <main className="hero-section d-flex pt-5 align-items-center">
          <Container >
            {/* Bootstrap row to organize content */}
            <Row className="align-items-center justify-content-center">
              {/* Column for the text, responsive layout */}
              <Col sm={12} md={7} className="text-center text-md-start">
                <h1  className="display-3 fw-bold" >Welcome to, {title}</h1>
                <p className="lead fs-1 mt-4">{content}</p>
              </Col>
              <Col className="text-center">
                <Image src="/heroIllustration.svg" alt="EzPay Illustration" fluid className="home-illustration" />
              </Col>
            </Row>
          </Container>
        </main >
      </div>
    </div>
  );
}


