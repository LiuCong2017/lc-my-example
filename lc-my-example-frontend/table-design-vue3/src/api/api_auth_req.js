import axios from "axios";
import {getToken} from "@/utils/auth";

const request = axios.create({
    baseURL:'/m',
    timeout:5000,
    headers: {
        "Content-Type": "application/x-www-form-urlencoded; charset=utf-8;"
    }
})

//请求拦截器
request.interceptors.request.use(
    config =>{
        if (getToken()){
            // 让每个请求携带自定义token
            config.headers['sld-token'] = getToken();
        }
        return config;
    },
    error => {
        Promise.reject(error);
    }
)

//响应拦截器
request.interceptors.response.use(
    response=>{
        return Promise.resolve(response);
    },
    error => {
        return Promise.reject(error.response);
    }
)

export default request;
