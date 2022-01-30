import React from "react";

class UserList extends React.Component{

    constructor(props){
        super(props);
    }

    render(){
        return(
            <>
                {
                    this.props.userList.map(user =>{
                        return <h2>{user.firstname}</h2>
                                                                   
                    })
                }
            </>
        );
    }
}

export default UserList;