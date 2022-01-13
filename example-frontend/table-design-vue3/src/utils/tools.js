// export function debounce(fn, delay) {
//     var timer; // 维护一个 timer
//     return function () {
//         var _this = this; // 取debounce执行作用域的this
//         var args = arguments;
//         if (timer) {
//             clearTimeout(timer);
//         }
//         timer = setTimeout(function () {
//             fn.apply(_this, args); // 用apply指向调用debounce的对象，相当于_this.fn(args);
//         }, delay);
//     };
// }

export function debounce(fn, delay) {
    // 维护一个 timer，用来记录当前执行函数状态
    let timer = null;

    return function() {
        // 通过 ‘this’ 和 ‘arguments’ 获取函数的作用域和变量
        let context = this;
        let args = arguments;
        // 清理掉正在执行的函数，并重新执行
        clearTimeout(timer);
        timer = setTimeout(function() {
            fn.apply(context, args);
        }, delay);
    }
}

// function throttle(func, wait){
//     let previous = 0;
//     return function(){
//         let now = Date.now(), context = this, args = [...arguments];
//         if(now - previous > wait){
//             func.apply(context, args);
//             previous = now; // 闭包，记录本次执行时间戳
//         }
//     }
// }

export function throttle(func, wait){
    let timer = null;
    return function(){
        let context = this, args = [...arguments];
        if(timer) return;
        timer = setTimeout(function(){
            func.apply(context, args);
            timer = null;
        }, wait)
    }
}
