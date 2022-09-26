let hero = {
    name: 'Cody'
}

const handler = {
    set: (target,key,value,receiver)=>{
        const result = Reflect.set(target,key,value,receiver);
        observerArray.forEach(item=>item());
        return result;
    }
}
const proxy = (obj) => new Proxy(obj,handler);
const observerArray = new Set();

const heroProxy = proxy(hero);
observerArray.add(()=>{
    console.log(heroProxy.name)
})

heroProxy.name = 'Alissa'