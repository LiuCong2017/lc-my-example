async function test(){
    return 1;
}
let res = test().then(resolve=>{
    console.log(resolve)
}).catch(err=>{

})
console.log(res)

async function getSome(){
    return Promise.resolve('Hello');
}

async function outSome(){
    return 'outSome'
}

async function testAwait(){
    let getVal = await getSome();
    let outVal = await outSome();
    console.log(`getVal:${getVal} - outVal:${outVal}`)
    console.log('testAwait')
}

testAwait();