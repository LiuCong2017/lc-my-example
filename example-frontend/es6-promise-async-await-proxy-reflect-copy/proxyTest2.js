// let targetProxy = {
//     name:'',
//     age:''
// }
// const proxyHandler = {
//     get:()=>{
//
//     },
//     set:()=>{
//
//     }
// }
//
// let proxy = new Proxy(targetProxy,proxyHandler)

let x = {
    name:'Cody',
    val:'Single'
}
// let p = new Proxy(x,{
//     get(target, p, receiver) {
//
//     },
//     set(target, p, value, receiver) {
//         console.log('set')
//         return Reflect.set(value);
//     }
// })

let proto = Reflect.getPrototypeOf(x)
console.log(proto)

let attr = Reflect.getOwnPropertyDescriptor(x,'name')
console.log(attr)

let bool = 98
let flag = Object.isExtensible(bool)

console.log(flag)
