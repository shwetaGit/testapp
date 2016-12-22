
import { Component, OnInit } from '@angular/core';
import {CheckBoxGroupConfiguration} from "../../../components/checkboxgroup/checkbox.group.input";
import {UserRoleService} from "./userrolemapping.service";
import {UserData, RoleData} from "./userrolemapping.model";
import {SimpleButtonConfiguration} from "../../../components/button/simple.button";
import {ResetButtonConfiguration} from "../../../components/button/reset.button";

@Component({

    templateUrl: 'app/userrole/adduserrolemapping/userrolemapping.html'
})
export class UserRoleMappingComponent implements OnInit {
    checkboxConfig;userConfig : CheckBoxGroupConfiguration;
    saveButtonConfig : SimpleButtonConfiguration;
    checkBoxData :any[]= [];
    userDataArray : any[] =[];
    selectedRole : any[]=[];
    selectedUser : any[]=[];
    jsonData : any[] = [];
    roleData : RoleData;
    data : Object;
    ResetConfig : ResetButtonConfiguration;
    check : boolean;

    constructor(private _userRoleService : UserRoleService) {
        this.roleData = new  RoleData();
        this.ResetConfig = new ResetButtonConfiguration(false,false);
        this.checkboxConfig = new CheckBoxGroupConfiguration('roleName','roleId',false,false,false,'Select Role ');
        this.userConfig = new CheckBoxGroupConfiguration('userName','userId',false,false,false,'Select User ');
        this.saveButtonConfig = new SimpleButtonConfiguration(false,false);
        this.getUserList();
    }
    ngOnInit() { }
    getUserList(){
        let requestJson ={};
        this._userRoleService.findListOfUser(this,"getUserData","secure/Login/FindUnMappedUser","post",requestJson);

    }
    getUserData (data: any) {
        data.response.data.forEach((option)=> {
            let userObject = new UserData();
            userObject.userId = option.userDetail.userId;
            let name;
            if (option.coreContacts.middleName != '') {
                name = option.coreContacts.firstName + ' ' + option.coreContacts.middleName + ' ' + option.coreContacts.lastName;
            }
            else {
                name = option.coreContacts.firstName + ' ' + option.coreContacts.lastName;
            }
            userObject.userName = name;
            this.userDataArray.push(userObject);
            this.data = {
                response: {
                    data: this.userDataArray
                }
            };

        });
    }
    selectUserData(data : any){
        this.selectedUser = [];
        data.forEach((option)=>{
            this.selectedUser.push(option.userId);

        })
    }
    selectCountryData(data : any){
        this.selectedRole = [];
        data.forEach((option)=>{
           this.selectedRole.push(option.roleId);
        })
    }
    saveMappingData(){
        this.jsonData = [];
        if(this.selectedRole.length>0 && this.selectedUser.length>0) {
            this.selectedUser.forEach((useroption)=> {
                this.selectedRole.forEach((roleoption)=> {
                    this.jsonData.push({'userId': useroption, 'roleId': roleoption});
                })
            })
             this._userRoleService.saveRoleMappedData(this, "getSaveMappedRoleResponse", "secure/UserRoleBridge", "post", this.jsonData);
        }
        else {
            toastr.error('Must Select Atleast one Role and one User');
        }
    }
    getSaveMappedRoleResponse(data : any){
        toastr.success(data.response.message);
        document.getElementById("roleMapping").reset();
    }

    resetForm(){
        document.getElementById("roleMapping").reset();
    }

}

