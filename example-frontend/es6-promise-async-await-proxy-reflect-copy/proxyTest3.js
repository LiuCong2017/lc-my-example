let validator = {
    set: (_obj,_propKey,_val)=>{
        if (!Number.isInteger(_val)){
            throw new Error(`The ${_propKey} is not a integer number`)
        }
        _obj[_propKey] = _val
    }
}

// let p = new Proxy({},validator);
// p.age = 'fwaoefa'
//
// console.log(p.age)

let p = new Proxy();