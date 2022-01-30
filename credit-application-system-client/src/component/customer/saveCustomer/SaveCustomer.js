import React from "react";
import SaveCustomerService from "../../../api/Customer/SaveCustomerService";


class SaveCustomerPage extends React.Component {

  constructor(props) {
    super(props);
    this.state = {

      identityNo: "",
      firstname: "",
      lastname: "",
      monthlyIncome: "",
      phone: "",
      birthday: "",
      guarantee: ""

    }
  }

  handlerChange = (event) => {
    this.setState({ [event.target.name]: event.target.value })
  }

  handleFormSubmit = (e) => {
    e.preventDefault();

    SaveCustomerService.saveCustomer(this.state.identityNo, this.state.firstname, this.state.lastname, this.state.monthlyIncome, this.state.phone, this.state.birthday, this.state.guarantee)
      .then(response => this.handleResponse(response))
      .catch(error => this.handleError(error))
      ;

      
  }

  handleResponse(response) {
    console.log(response.data)
    
  }

  handleError(error) {
    console.log(error.response)
  }

  clearForm(){
    document.getElementById("form-signup").reset();
  }

  render() {
    return (<>
      <div className="container">
        <div className="text-center mt-5">
          <div className="row col-md-4 offset-md-4">

            <form className="form-signup" onSubmit={this.handleFormSubmit}>


              <h1 className="h3 mb-3 font-weight-normal">Customer Registration</h1>

              <br></br>

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

              <label htmlFor="inputFirstname" className="sr-only">Firstname</label>
              <input
                type="text"
                id="inputFirstname"
                className="form-control"
                placeholder="Firstname"
                required=""
                autoFocus=""
                value={this.state.firstname}
                name="firstname"
                onChange={this.handlerChange}
              />
              <br></br>

              <label htmlFor="inputLastname" className="sr-only">Lastname</label>
              <input
                type="text"
                id="inputLastname"
                className="form-control"
                placeholder="Lastname"
                required=""
                autoFocus=""
                value={this.state.lastname}
                name="lastname"
                onChange={this.handlerChange}
              />
              <br></br>

              <label htmlFor="inputMonthlyIncome" className="sr-only">Monthly Income (e.g. 8500.0)</label>
              <input
                type="text"
                id="inputMonthlyIncome"
                className="form-control"
                placeholder="Monthly Income"
                required=""
                autoFocus=""
                value={this.state.monthlyIncome}
                name="monthlyIncome"
                onChange={this.handlerChange}
              />
              <br></br>

              <label htmlFor="inputPhone" className="sr-only">Phone Number</label>
              <input
                type="text"
                id="inputPhone"
                className="form-control"
                placeholder="Phone"
                required=""
                autoFocus=""
                value={this.state.phone}
                name="phone"
                onChange={this.handlerChange}
              />
              <br></br>

              <label htmlFor="inputBirthday" className="sr-only">Birthday (e.g. 1995-02-28)</label>
              <input
                type="text"
                id="inputBirthday"
                className="form-control"
                placeholder="Birthday"
                required=""
                autoFocus=""
                value={this.state.birthday}
                name="birthday"
                onChange={this.handlerChange}
              />
              <br></br>

              <label htmlFor="inputGuarantee" className="sr-only">Guarantee (e.g. 10000.0)</label>
              <input
                type="text"
                id="inputGuarantee"
                className="form-control"
                placeholder="Guarantee"
                required=""
                autoFocus=""
                value={this.state.guarantee}
                name="guarantee"
                onChange={this.handlerChange}
              />
              <br></br>

            
              <input type="submit" className="btn btn-danger btn-block" value="Sign Up" />
              <p className="mt-5 mb-3 text-muted">© 2022</p>
            </form>
          </div>
        </div>
      </div>
    </>



    )
  }
}

export default SaveCustomerPage;