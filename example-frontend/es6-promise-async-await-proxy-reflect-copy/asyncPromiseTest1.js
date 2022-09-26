function test1(){
    return new Promise(resolve=>{
        setTimeout(()=>{
            resolve('test1Val')
        },5000)
    })
}

// test1().then(res=>{
//     console.log(res)
// })

async function test2(){
    let res = await test1();
    console.log(res)
}

test2();

(() => {
    let promise;
    async function test() {
        promise = new Promise(resolve => resolve(0));
        promise.mark = "hello";
        return promise;
    }

    const gotPromise = test();

    console.log(`is same object?: ${promise === gotPromise}`);  // false
    console.log(`promise.mark: ${promise.mark}`);               // hello
    console.log(`gotPromise.mark: ${gotPromise.mark}`);         // undefined
})();