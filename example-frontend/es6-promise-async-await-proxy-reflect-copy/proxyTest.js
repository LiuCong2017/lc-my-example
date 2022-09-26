let obj = {
	name:'objName',
	attr: 'objAttr'
}

const handler = {
	get: (obj,props)=>{
		console.log('get effect')
		return obj[props]
	},

	set: (obj,props,val)=>{
		console.log('set effect')
		return obj[props] = val
	}
}

const proxy = new Proxy(obj,handler)
console.log(proxy.name)
proxy.attr = 'updateAttr'
console.log(obj.attr)