import axios from 'axios'

const DBrequest = axios.create({
    baseURL: '/d',
    timeout: 50000,
})

DBrequest.interceptors.request.use(
    config=>{
        return config
    },
    error => {
        Promise.reject(error)
    },
)

DBrequest.interceptors.response.use(
    response =>{
        return Promise.resolve(response)
    },
    error => {
        return Promise.reject(error)
    }
)

export default DBrequest;
