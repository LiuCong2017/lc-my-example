class Parse{
    constructor() {
        var source = Object;
        var target = [];
    }

    get souce(){
        return this.source
    }
    set source(source){
        this.source = source
    }

    get target(){
        return this.target
    }
    set target(target){
        this.target = target
    }

    static defaultTarget(){
        return new Parse().target;
    }

}
