

export class RoleConfiguration{
    roleName : string;
    roleDescription : string;
    roleMenuBridge : Array<RoleMenuBridge>;
    constructor(){
        this.roleMenuBridge = [];
    }
}

export class RoleList{
    roleName : string;
    roleId : string;
    constructor(){
    }
}

export class RoleMenuBridge{
    isExecute : boolean;
    isRead : boolean;
    isWrite : boolean;
    menuId : string;
    constructor(){
        this.isExecute = true;
        this.isRead = true;
        this.isWrite = true;
    }

}

export class MappedFeature{
    isSelected : boolean;
    menuLabel : string;
    menuId : string;
    leaf  : boolean;
    children  : MappedFeature [];
    isExpanded : boolean;
    routePath   : string;
    iconUrl : string;
    isExecute : boolean;
    isRead : boolean;
    isWrite : boolean;

    constructor(){
        this.isSelected = false;
        this.children = [];
        this.isExpanded = false;
        this.iconUrl = 'fa fa-angle-down rotate-icon';
        this.isExecute = false;
        this.isRead = true;
        this.isWrite = true;
    }

    toggle(){
        this.isExpanded = !this.isExpanded;
        if(this.isExpanded){
            this.iconUrl = 'fa fa-angle-down rotate-icon rotate-element';
        }
        else {
            this.iconUrl = 'fa fa-angle-down rotate-icon';
        }

    }

}
