



export class MenuData{
    menuLabel : string;
    leaf  : boolean;
    children  : MenuData [];
    isExpanded : boolean;
    routePath   : string;
    iconUrl : string;

    constructor(){
        this.children = [];
        this.isExpanded = false;
        this.iconUrl = 'fa fa-angle-down rotate-icon';
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