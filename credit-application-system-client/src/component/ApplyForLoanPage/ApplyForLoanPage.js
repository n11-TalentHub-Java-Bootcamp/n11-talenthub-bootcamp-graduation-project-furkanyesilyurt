import React from "react";
import ApplyForLoanService from "../../api/Loan/ApplyForLoanService";
import App from "../../App";
import { Modal } from "./Modal";


class ApplyForLoanPage extends React.Component {


  constructor(props) {
    super(props);
    this.state = {

      identityNo: "",
      flag: false,
      status: "",
      limit: ""
    }
  }

  handlerChange = (event) => {
    this.setState({ [event.target.name]: event.target.value })
  }

  handleFormSubmit = (e) => {
    e.preventDefault();

    ApplyForLoanService.inquireCredit(this.state.identityNo)
      .then(response => this.handleResponse(response))
      .catch(error => this.handleError(error))
      ;
  }

  handleResponse(response) {
    console.log(response.data);
    // App.openModal();    
    this.setState({ flag: true, limit: response.data.creditLimit, status: response.data.responseStatus == "APPROVAL" ? " APPROVAL" : " REJECTION" });
  }

  handlePopup() {

  }

  handleError(error) {
    console.log(error.response)
  }

  render() {
    return (<>
      <div className="container">
        <div className="text-center mt-5">
          <div className="row col-md-4 offset-md-4">

            <form className="form-signin" onSubmit={this.handleFormSubmit}>


              <h1 className="h3 mb-3 font-weight-normal">Apply For A Loan</h1>

              <label htmlFor="inputIdentityNo" className="sr-only">Identity No</label>
              <input
                type="text"
                id="inputIdentityNo"
                className="form-control"
                placeholder="Identity No"
                required=""
                autoFocus=""
                value={this.state.identityNo}
                name="identityNo"
                onChange={this.handlerChange}
              />

              <br></br>
              <input type="submit" className="btn btn-danger btn-block" value="Inquire" />
              <p className="mt-5 mb-3 text-muted">© 2022</p>
            </form>
          </div>
        </div>
      </div>
      {this.state.flag && <Modal showModal={this.state.flag} setShowModal={!this.state.flag}
        status={this.state.status}
        limit={this.state.limit}
        toggleModal={(e) => this.setState({ flag: e })}
      />}
    </>



    )
  }


}



export default ApplyForLoanPage;