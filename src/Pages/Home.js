import React, { useState, useEffect } from "react";
import Header from "./Header.js";
import { Container, Row, Col, Image } from "react-bootstrap";

export default function Home() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  useEffect(() => {
    setTitle('EzPay');
    setContent('A Secure and Efficient Digital Payment Solution');
  }, []);

  return (
    <div>
      <div className="home-page-component d-flex flex-column justify-content-center">
        <Header />
        <main className="hero-section d-flex">
          <Container >
            <Row className="align-items-center justify-content-center">
              <Col md={7} className="text-center text-md-start">
                <h1>Welcome to, {title}</h1>
                <p>{content}</p>
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


