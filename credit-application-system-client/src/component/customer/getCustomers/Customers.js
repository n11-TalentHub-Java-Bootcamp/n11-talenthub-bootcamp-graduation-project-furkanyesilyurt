import React from "react";
import UserList from "./CustomerList";

class Users extends React.Component{

    state = {
        userList: []
    }

    constructor(){
        super();
    }

    componentDidMount(){
        fetch("http://localhost:8080/api/v1/customers")
            .then((response) => response.json())
            .then(userList =>  {
                this.setState({userList: userList})
            });
            
    }

    render(){

        return <>
            <UserList userList = {this.state.userList}/>
        </>
    }
}

export default Users;