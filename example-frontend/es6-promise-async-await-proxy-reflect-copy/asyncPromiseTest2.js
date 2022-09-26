/**
 * 当执行async函数时，遇到await声明，
 * 会先将await后面的内容按照'平常的执行规则'执行完，
 * 执行完后立马跳出async函数，去执行主线程其他内容，
 * 等到主线程执行完再回到await处继续执行后面的内容
 * @returns {Promise<void>}
 */
async function async1(){
    console.log('async1 start')
    /**
     * 注意await并没有使async2阻塞
     * 而是在等待右侧表达式的值返回
     */
    let val = await async2() //await执行结束才能执行后面的代码
    console.log(val) //await执行完，其后面的代码不再阻塞，所以执行
    console.log('async1 end')
}

async function async2(){
    console.log('async2')
    return 'async2_val'
}

async1()
console.log('script start')