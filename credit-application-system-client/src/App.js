import React, {useState} from 'react';

import { Route, Routes } from 'react-router-dom';
import './App.css';
import Users from './component/customer/getCustomers/Customers';
import NavBar from './component/navbar/NavBar';
import HomePage from './component/home/HomePage';
import ErrorPage from './component/error/ErrorPage';
import SaveCustomerPage from './component/customer/saveCustomer/SaveCustomer';
import ApplyForLoanPage from './component/ApplyForLoanPage/ApplyForLoanPage';


import styled from 'styled-components';
import { Modal } from './component/ApplyForLoanPage/Modal';
import { GlobalStyle } from './component/ApplyForLoanPage/GlobalStyle';
import LoanStatusPage from './component/LoanStatusPage/LoanStatusPage';


const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 200vh;
`;

const Button = styled.button`
  min-width: 50px;
  padding: 8px 16px;
  border-radius: 4px;
  border: none;
  background: #141414;
  color: #fff;
  font-size: 24px;
  cursor: pointer;
`;

function App() {

  return (
    <div className="App">

      <NavBar></NavBar>
     
      <Routes>
        <Route path="/home" element={<HomePage></HomePage>}></Route>
        <Route path="" element={<HomePage></HomePage>}></Route>
        <Route path="*" element={<ErrorPage></ErrorPage>}></Route>
        <Route path="/new-customer" element={<SaveCustomerPage></SaveCustomerPage>}></Route>
        <Route path="/apply-for-loan" element={<ApplyForLoanPage></ApplyForLoanPage>}></Route>
        <Route path="/loan-status" element={<LoanStatusPage></LoanStatusPage>}></Route>
      </Routes>

    </div>
  );
}

export default App;
