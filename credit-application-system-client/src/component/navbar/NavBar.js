import React from "react";
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap/';
import './NavBar.css'

class NavBar extends React.Component {

    render() {
        return <>
            <Navbar className="navbar" expand="lg" >
                <Container>
                    <Navbar.Brand href="home">Green Bank</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="home">HOME</Nav.Link>
                            {/* <Nav.Link href="list-customer">LIST CUSTOMERS</Nav.Link> */}
                            <Nav.Link href="new-customer">NEW CUSTOMER</Nav.Link>
                            <NavDropdown title="LOAN" id="basic-nav-credit">
                                <NavDropdown.Item href="apply-for-loan">Apply For Loan</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item href="loan-status">Learn Loan Status</NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    }
}

export default NavBar;