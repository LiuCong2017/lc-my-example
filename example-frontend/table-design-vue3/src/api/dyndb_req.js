import axios from 'axios'

const DynamicDBReq = axios.create({
    baseURL:'',
    timeout: 5000
})
DynamicDBReq.interceptors.request.use(
    config => {return config},
    error => (Promise.reject(error))
)
DynamicDBReq.interceptors.response.use(
    response => {return Promise.resolve(response)},
    error => {return Promise.reject(error)}
)

export default DynamicDBReq;
