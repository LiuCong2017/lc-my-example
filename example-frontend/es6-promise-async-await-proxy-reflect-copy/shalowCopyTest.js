var obj = {
    'name': 'Cody',
    'age':30,
    'baby':{
        'name':'objBaby'
    }
}

//shallowCopy = Object.assign(target,source)
function shallowCopy(src){
    var dst = {};
    for (var prop in src){
        if (src.hasOwnProperty(prop)){
            dst[prop] = src[prop];
        }
    }
    return dst
}


/**
 * 第一层是深拷贝,第二层是浅拷贝
 * @type {{name: string, baby: {name: string}, age: number}}
 */
// obj2 = shallowCopy(obj)
obj2 = Object.assign({},obj)

obj2.name = 'Audrey'
obj2.age = 29
obj2.baby.name = 'Ava'

console.log(obj)
console.log(obj2)

