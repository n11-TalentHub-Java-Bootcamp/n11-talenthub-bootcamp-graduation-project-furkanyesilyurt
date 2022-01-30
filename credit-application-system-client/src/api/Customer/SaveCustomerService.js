import axios from "axios";

class SaveCustomerService{

    saveCustomer(identityNo, firstname, lastname, monthlyIncome, phone, birthday, guarantee){

        const data = {
             identityNo: identityNo,
             firstname: firstname,
             lastname: lastname,
             monthlyIncome: monthlyIncome,
             phone: phone,
             birthday: birthday,
             guarantee: guarantee
        }

        const url = 'api/v1/customers';

        return axios.post(url, data);
    }

}

export default new SaveCustomerService();