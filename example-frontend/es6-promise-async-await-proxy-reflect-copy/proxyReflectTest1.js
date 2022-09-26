const hasPropertyInObj = (_obj,_prop)=>{
    return Reflect.has(new Proxy(_obj,{}),_prop);
}

let obj = {name:''}

// let flag = hasPropertyInObj(obj,'name')
let flag = Reflect.has(obj,'name')
console.log(flag)