import axios from "axios";

class LoanStatusPage {

    getApprovedLoan(identityNo,birthday) {

        const url = 'api/v1/loans/approved-credit?identityNo=' + identityNo + '&birthday=' + birthday;
        //'http://localhost:8080/api/v1/loans/approved-credit?identityNo=10735264946&birthday=1997-12-20' \

        return axios.get(url);

    }

}

export default new LoanStatusPage();