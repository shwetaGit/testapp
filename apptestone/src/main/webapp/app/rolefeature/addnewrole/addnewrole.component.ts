

import { Component, OnInit } from '@angular/core';
import { RoleFeatureServices} from "./addnewrole.service";
import {MenuData} from "../../mainpage/sidemenu/menutree.model";
import {TextInputConfiguration} from "../../../components/textinput/text.input";
import {RoleConfiguration, RoleList, RoleMenuBridge, MappedFeature} from "./addnewrole.model";
import {RadioGroupInputConfiguration} from "../../../components/radioinput/radio.group.input";
import {SimpleButtonConfiguration} from "../../../components/button/simple.button";
import {ResetButtonConfiguration} from "../../../components/button/reset.button";
import {CommonUtils} from "../../../components/services/CommonUtils";

@Component({

    selector: 'add-new-role',
    templateUrl: 'app/rolefeature/addnewrole/addnewrole.html'
})
export class AddNewRoleComponent implements OnInit {
    mappedFeatureList : any;
    roleMenuBrigeArray : any;
    dragData  : any;
    roleconfig : RoleConfiguration;
    menu : MenuData;
    menuBody : any;
    menuList : any;
    roleListArray : any;
    SaveConfig : SimpleButtonConfiguration;
    ResetConfig : ResetButtonConfiguration;
    deviceConfig : RadioGroupInputConfiguration;
    roleNameConfig;roleDescriptionConfig : TextInputConfiguration;
    deviceType : Object;
    constructor(private _roleFeatureService : RoleFeatureServices,private _commonsValidatorService : CommonUtils) {

        this.SaveConfig = new SimpleButtonConfiguration(false,false);
        this.ResetConfig = new ResetButtonConfiguration(false,false);
        this.mappedFeatureList = [];
        this.roleMenuBrigeArray = [];
        this.dragData = [];
        this.roleListArray = [];
        this.roleconfig =new RoleConfiguration();
        this.deviceConfig =  new  RadioGroupInputConfiguration('displayField','valueField',false,false,true,'select device mode ');
        this.roleNameConfig = new TextInputConfiguration(false,false,false,true,'','','Invalid Role Name');
        this.roleDescriptionConfig = new TextInputConfiguration(false,false,false,true,'','','Invalid Description');
        this.menuList = [];
        this.deviceType = {
            response : {
                data : [
                    {displayField : 'Web' , valueField : '1'},
                    {displayField : 'Mobile' , valueField : '2'}
                ]
            }
        };
        this.getDeviceType(1);
    }
    resetForm(){
        document.getElementById("roleForm").reset();
    }

    selectAllRole(data : any){
        if(data) {
            this.menuList.forEach((option)=> {
                option.isSelected = true;
                option.isExecute = true;
                if (!option.leaf) {
                    this.childSelected(option.children,data);
                }
            });
        }
        else{
            this.menuList.forEach((option)=> {
                option.isSelected = false;
                option.isExecute = false;
                if (!option.leaf) {
                    this.childSelected(option.children,data);
                }
            });
        }
    }
    childSelected(child,data){
        if(data) {
            child.forEach((option)=> {
                option.isSelected = true;
                option.isExecute = true;
                if (!option.leaf) {
                    this.childSelected(option.children,data);
                }
            });
        }
        else {
            child.forEach((option)=> {
                option.isSelected = false;
                option.isExecute = false;
                if (!option.leaf) {
                    this.childSelected(option.children,data);
                }
            });
        }
    }
    SaveRole(){
        this.setRoleConfigData(this.menuList);
        let requestJson = {'roleDescription':this.roleconfig.roleName,'roleName':this.roleconfig.roleDescription,'roleMenuBridge':this.roleMenuBrigeArray};
        let componentArray = [ this.roleNameConfig,this.roleDescriptionConfig ];
        if(this._commonsValidatorService.validateAndShowErrorMessages(componentArray)) {
            return;
        }
        else {
            if(this.roleMenuBrigeArray.length>0) {
                 this._roleFeatureService.saveUser(this,"SaveResponseData","secure/Roles","post",requestJson);
            }
                else {
                toastr.error('Please select Role Feature');
            }
        }

    }

    SaveResponseData(data : any){
        toastr.success('Role Added Successfully');
            /*TODO : if required*/
      //  this.getRoleList();
    }

    setRoleConfigData(data : any){
        data.forEach((option)=> {
            if(option.isSelected) {
                let role = new RoleMenuBridge();
                role.menuId = option.menuId;
                role.isRead = option.isRead;
                role.isWrite = option.isWrite;
                role.isExecute = option.isExecute;
                if (!option.leaf) {
                    this.addChildConfigData(option.children, role);
                }
                this.roleMenuBrigeArray.push(role);
            }
        });
    }
    addChildConfigData(child,parent) {
        child.forEach((option)=>{
            let role = new RoleMenuBridge();
            role.menuId = option.menuId;
            role.isRead = option.isRead;
            role.isWrite = option.isWrite;
            role.isExecute = option.isExecute;
            this.roleMenuBrigeArray.push(role);
            if (!option.leaf) {
                this.addChildConfigData(option.children, role);
            }
        })
    }
    getDeviceType(value : any){
        let requestJson = {};
        requestJson['findKey'] = parseInt(value);
        this._roleFeatureService.getMenus(this,"setMenusData","secure/MenuService/findMenusByDeviceType","post",requestJson).subscribe(
            response=>{
                let res = response.json();
                this.menuBody= JSON.parse(res.response.data);
            },
            error=>{
            },
            ()=>{
                this.mainMenuSorting(this.menuBody);
                this._roleFeatureService.menuData =  this.menuList;
            }
        );

    }

    mainMenuSorting(menuBody) {
        this.menuList = [];
        menuBody.forEach((option)=> {
            let parent = new MappedFeature();
            parent.menuLabel = option.menuName;
            parent.routePath = option.menuAction.path;
            parent.leaf = option.leaf;
            parent.menuId = option.menuId;
            if(!parent.leaf){
                this.addChild(option.children,parent);
            }
            this.menuList.push(parent);
        });
    }
    addChild(child,parent){
        for(let i = 0 ;i< child.length;i++){
            let parentChildren = new MappedFeature();
            parentChildren.menuLabel = child[i].menuName;
            parentChildren.leaf = child[i].leaf;
            parentChildren.routePath = child[i].menuAction;
            parentChildren.menuId = child[i].menuId;
            parent.children.push(parentChildren);
            if(!parentChildren.leaf){
                this.addChild(child[i].children, parentChildren);
            }
        }
    }
    ngOnInit() { }


}