/**
 * Accomplish the compare value either or not to empty value
 * @param obj
 * @returns {boolean}
 */
export function isEmpty(obj) {
  return obj === undefined || obj === null || Number.isNaN(obj) || '' === obj.toString();
}

/**
 * 格式化字符串日期
 * @param str
 * @returns {string}
 */
export function formatTime(str){
  let oDate = new Date(str),
      oYear = oDate.getFullYear(),
      oMonth = oDate.getMonth() + 1,
      oDay = oDate.getDate();//最后拼接时间
  return oYear + '-' + ((parseInt(oMonth) < 10) ? ('0' + oMonth) : oMonth) + '-' + ((parseInt(oDay) < 10) ? ('0' + oDay) : oDay);
}

/**
 * 判断对象是否为空
 */
export function isEmptyObj(obj){
  return Object.keys(obj).length === 0 && obj.constructor === Object
}

/**
 * 判断对象是否包含某属性
 */
export function hasPropertyInObj(_obj,_prop){
  let handler = {
    has: (obj,prop) => {
      return prop in obj;
    }
  }
  let proxy = new Proxy(_obj,handler);
  return Reflect.has(proxy,_prop);
  // return _prop in proxy;
}

/**
 * 对象属性扩展
 */
export function expandObj(_obj,_prop,_val){
  if(Reflect.isExtensible(_obj)){
    Reflect.defineProperty(_obj,_prop,{
      value: _val,
      writable:true,
      configurable:true,
      enumerable:true
    });
  }
}
