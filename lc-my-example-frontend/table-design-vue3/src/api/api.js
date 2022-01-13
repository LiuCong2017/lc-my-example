import service from "./api_req";
import request from "@/api/api_auth_req";
import DBrequest from "@/api/db_req";
import DynamicDBReq from '@/api/dyndb_req'

export const login = params =>{
    //后台以RequestBody方式接收参数须通过此方式传参
    return service.post(`/api/login/doLogin`,{
        username: params.username,
        password: params.password
    })
}

export const getUserInfo = (username)=>{
    // return request.get('/api/user/getUserInfo?username='+username)
    return service.get('/api/user/getUserInfo?username='+username)
}

export const getLogout = ()=>{
    return service.get('/api/login/logout')
}

export const addUser = (param)=>{
    return service.post('/api/user/add',param);
}


//获取数据源连接
export const getConnect = (param)=>{
    return DBrequest.post('/api/db/connect',param)
}

//获取所有数据库
export const getAllDb = (params)=>{
    return DBrequest.post('/api/db/getAllDbs',params)
}

//获取所有表
export const getAllTable = (params) =>{
    return DBrequest.post('/api/db/getAllTables',params)
}

//获取所有字段
export const getAllColumns = (params)=>{
    return DBrequest.post('/api/db/getAllColumns',params)
}

//获取所有视图
export const getAllViews = (params) => {
    return DBrequest.post('/api/db/getAllViews',params)
}

//使用动态源
export const getDynamicDataSource = ()=>{
    return DynamicDBReq.post();
}

//获取树数据
export const getDbTreeData = (params)=>{
    return DBrequest.post('/api/db/getTreeData',params)
}
