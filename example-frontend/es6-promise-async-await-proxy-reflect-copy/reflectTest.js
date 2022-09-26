// import {expandObj} from "./common-tools";

let obj = {
    name:'',
    age:''
}

Reflect.defineProperty(obj,'gender',{
    value:'m',
    writable:true,
    configurable:true,
    enumerable:true
})

console.log(obj)


const expandObj = (_obj,_prop,_val)=>{
    if(Reflect.isExtensible(_obj)){
        Reflect.defineProperty(_obj,_prop,{
            value: _val,
            writable:true,
            configurable:true,
            enumerable:true
        });
    }
}

expandObj(obj,'salary',3000);

obj.salary = 5500
console.log(obj)