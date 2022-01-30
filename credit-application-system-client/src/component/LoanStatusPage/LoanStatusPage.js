import React from "react";
import LoanStatusService from "../../api/LoanStatusPage/LoanStatusService";
import { Modal2 } from "./Modal2";


class LoanStatusPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {

            identityNo: "",
            birthday: "",
            flag: false,
            creditLimit: ""

        }

    }

    handlerChange = (event) => {
        this.setState({ [event.target.name]: event.target.value })
    }

    handleFormSubmit = (e) => {
        e.preventDefault();

        LoanStatusService.getApprovedLoan(this.state.identityNo, this.state.birthday)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error))
            ;
    }

    handleResponse(response) {
        console.log(response.data)
        this.setState({ flag: true, creditLimit: response.data.creditLimit });
    }

    handleError(error) {
        console.log(error.response)
    }

    render() {
        return (<>
            <div className="container">
                <div className="text-center mt-5">
                    <div className="row col-md-4 offset-md-4">

                        <form className="form-loanstatus" onSubmit={this.handleFormSubmit}>


                            <h1 className="h3 mb-3 font-weight-normal">Learn Loan Status</h1>
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

                            <label htmlFor="inputBirthday" className="sr-only">Birthday</label>
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

                            <input type="submit" className="btn btn-danger btn-block" value="Find Loan" />
                            <p className="mt-5 mb-3 text-muted">© 2022</p>
                        </form>
                    </div>
                </div>
            </div>
            {this.state.flag && <Modal2 showModal={this.state.flag} setShowModal={!this.state.flag}
                creditLimit={this.state.creditLimit}
                toggleModal={(e) => this.setState({ flag: e })}
            />}
        </>



        )
    }


}

export default LoanStatusPage;