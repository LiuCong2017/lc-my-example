import axios from "axios";

const service = axios.create({
    baseURL: '/m',
    timeout: 5000,
    // headers: {
    //     "Content-Type": "application/json; charset=utf-8;"
    // }
})

//请求拦截器
service.interceptors.request.use(
    config => {
        // POST传参序列化
        // if (config.method === "post") {
        //     config.data = qs.stringify(config.data); //将对象序列化，多个对象之间用&拼接
        //     // config.data = config.data; //将对象序列化，多个对象之间用&拼接
        // }
        return config
    },
    error => {
        Promise.reject(error);
    }
)

//响应拦截器
service.interceptors.response.use(
    response => {
        return Promise.resolve(response);
    },
    error => {
        return Promise.reject(error.response);
    }
)

export default service;
