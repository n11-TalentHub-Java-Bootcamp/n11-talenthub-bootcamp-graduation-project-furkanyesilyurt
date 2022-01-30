import axios from "axios";

class ApplyForLoanService {

    inquireCredit(identityNo) {

        const url = 'api/v1/loans?identityNo=' + identityNo;

        return axios.get(url);

    }

}

export default new ApplyForLoanService();