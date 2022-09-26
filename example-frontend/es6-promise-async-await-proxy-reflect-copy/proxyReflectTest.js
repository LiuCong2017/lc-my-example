let obj = new Proxy({},{
   get:(target,key,receiver)=>{
       console.log(`getting ${key}`)
       return Reflect.get(target,key,receiver);
   },
    set(target, key, value, receiver) {
        console.log(`setting ${key}, value ${value}`)
        return Reflect.set(target,key,value,receiver);
    },
})

obj.count = 1;

let newObj = Object.create(obj);
newObj.count = 3;
// ++obj.count;
++newObj.count;
console.log(obj.count)
