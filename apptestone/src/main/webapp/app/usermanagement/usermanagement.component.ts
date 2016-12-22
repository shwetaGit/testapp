
import { Component, OnInit } from '@angular/core';
import {DataGridConfiguration} from "../../components/datagrid/datagrid";
import {UserManagementService} from "./usermanagement.service";

import {SimpleButtonConfiguration} from "../../components/button/simple.button";
import {Router} from "@angular/router";
import {UserData} from "./usermanagement.model";
import {CoreContacts, RootObject, SystemInfo, UserDetail} from "../myprofile/myprofile.model";
import {NumberInputConfiguration} from "../../components/numberinput/number.input";


@Component({

    templateUrl: 'app/usermanagement/usermanagement.html'
})
export class UserManagementComponent implements OnInit {
    coreContacts : CoreContacts;
    rootObject : RootObject;
    systemInfo : SystemInfo;
    user : UserDetail;
    addUserConfig;resendPassConfig;changePassConfig;lockAccountConfig;unlockAccountConfig;sessionConfig :  SimpleButtonConfiguration;
    displatGridDataObject : Object;
    routeToView : string;
    displatGridDataArray : any[]= [];
    UserDataArray : any[]= [];
    checkedUserArray : any;
    userData : UserData;
    userGridConfig  : DataGridConfiguration;
    sessionConfig:  NumberInputConfiguration;
    constructor(private  _UserManagementService : UserManagementService,private router : Router) {
        this.checkedUserArray =[];
        this.routeToView = '';
        this.userData = new UserData();
        this.addUserConfig = new SimpleButtonConfiguration(false,false);
        this.resendPassConfig = new SimpleButtonConfiguration(false,false);
        this.changePassConfig = new SimpleButtonConfiguration(false,false);
        this.lockAccountConfig = new SimpleButtonConfiguration(false,false);
        this.unlockAccountConfig = new SimpleButtonConfiguration(false,false);
        this.sessionConfig = new SimpleButtonConfiguration(false,false);
        this.sessionConfig =  new NumberInputConfiguration(false,false,false,false,'120','','Invalid session Timeout ');


        this.callToGetUserService();
        var columns = [];
       /* columns.push({text:'Title', dataIndex: 'titleId', hidden: false});*/
        columns.push({text:'First Name', dataIndex: 'firstName', hidden: false});
        columns.push({text:'Last Name', dataIndex: 'lastName', hidden: false});
        columns.push({text:'Email Id', dataIndex: 'emailId', hidden: false});
        columns.push({text:'Phone No', dataIndex: 'phoneNumber', hidden: false});
       // columns.push({text:'Locked', dataIndex: 'isLocked', hidden: false});
        this.userGridConfig = new DataGridConfiguration(columns);

    }
    callToGetUserService(){
        let requestJson = {};
        this._UserManagementService.findListOfUser(this,"getUserData","secure/Login/findAll","get",requestJson)
    }


    fetchCheckboxData(event : Event){
        this.checkedUserArray = event;
    }

    fetchRouteData(event : Event){
         this.router.navigate(['/mainpage/ViewUser',{userId:event.userId}]);
    }


    getUserData(data : any){
        this.rootObject =data.response.data;
        this.coreContacts = data.response.data.coreContacts;
        this.systemInfo = data.response.data.systemInfo;
        this.user = data.response.data.userDetail;
        data.response.data.forEach((option)=>{
        let userData = new UserData();
        // this.userData.titleId = option.coreContacts.titleId;
            userData.firstName = option.coreContacts.firstName;
            userData.lastName = option.coreContacts.lastName;
            userData.emailId = option.loginId;
            userData.phoneNumber = option.coreContacts.phoneNumber;
            userData.userId = option.userId;
            userData.isLocked = option.userDetail.isLocked;
            userData.changePasswordNextLogin = option.userDetail.changePasswordNextLogin;
            userData.isChecked =  false;
            this.UserDataArray.push(userData);
            this.displayGridData(userData);
        })
    }
    displayGridData(userData : any){
        let gridData = new UserData();
        gridData = JSON.parse(JSON.stringify(userData));
        this.displatGridDataArray.push(gridData);
        this.displatGridDataObject = ({'response':{'data': this.displatGridDataArray}});
    }

    addUserClick(){
          this.router.navigate(['/mainpage/AddUser']);
    }

    lockClick(){
        let valid : boolean;
        if(this.checkedUserArray.length != 0) {
        let requestJson =[];
        this.checkedUserArray.forEach((option)=>{
        if(!option.isLocked) {
            option.isLocked = true;
            requestJson.push({'userId': option.userId, 'isLocked': option.isLocked});
            valid = true;
            return valid;
        }
        else {
            toastr.error(option.firstName+'is Already locked');
            valid = false;
            return valid;
        }
        })
        if(valid) {
            this._UserManagementService.lockUser(this, "lockServiceResponseUser", "secure/PasswordGenerator/updateUser", "post", requestJson);
        }
        }
        else {
            toastr.error('Please Select Atleast One User');
        }

    }

    lockServiceResponseUser(data : any){
        toastr.success(data.response.message);
        this.checkedUserArray = [];
        document.getElementById("userMangeForm").reset();

    }
    unlockClick(){
        let valid : boolean;
        if(this.checkedUserArray.length != 0) {
        let requestJson =[];
        this.checkedUserArray.forEach((option)=>{
            if(option.isLocked) {
                option.isLocked = false;
                requestJson.push({'userId': option.userId, 'isLocked': option.isLocked});
                valid = true;
                return valid;
            }
            else {
                toastr.error(option.firstName+'is Already Unlocked');
                valid = false;
                return valid;
            }
        })
            if(valid) {
                this._UserManagementService.unlockUser(this, "unlockServiceResponseUser", "secure/PasswordGenerator/updateUser", "post", requestJson);
            }
            }
    else {
    toastr.error('Please Select Atleast One User');
  }

    }
    unlockServiceResponseUser(data : any){
        toastr.success(data.response.message);
        this.checkedUserArray = [];
        document.getElementById("userMangeForm").reset();
    }

    resetResendClick(){
        if(this.checkedUserArray.length != 0) {
        let requestJson =[];
        this.checkedUserArray.forEach((option)=>{
                requestJson.push({'userId': option.userId, 'loginId': option.emailId});
        })
            this._UserManagementService.resetResendUser(this,"resetResendServiceResponseUser","secure/PasswordGenerator/resetPassword","put",requestJson);
        }
        else {
            toastr.error('Please Select Atleast One User');
        }

    }
    resetResendServiceResponseUser(data : any){
        toastr.success("Password Reset Successfully");
        this.checkedUserArray = [];
        document.getElementById("userMangeForm").reset();
    }

    changePassClick(){
        if(this.checkedUserArray.length != 0) {
            let requestJson =[];
            this.checkedUserArray.forEach((option)=> {
                option.changePasswordNextLogin = true;
                requestJson.push({'userId': option.userId, 'changePasswordNextLogin': option.changePasswordNextLogin});
            })
             this._UserManagementService.changePasswordNextLogin(this, "changePasswordNextLoginResponse", "secure/PasswordGenerator/updateUser", "post", requestJson);
            }
            else {
            toastr.error('Please Select Atleast One User');
        }


    }
    changePasswordNextLoginResponse(data : any){
        toastr.success(data.response.message);
        this.checkedUserArray = [];
        document.getElementById("userMangeForm").reset();

    }

    sessionClick(){
        if(this.checkedUserArray.length != 0) {
            document.getElementById("sessionField").style.display = "block";
            let requestJson = [];
            if (this.userData.sessionTimeout != null) {
                this.checkedUserArray.forEach((option)=> {
                    requestJson.push({'userId': option.userId, 'sessionTimeout': this.userData.sessionTimeout});
                })
                this._UserManagementService.sessionTimeOut(this, "getsessionResponse", "secure/PasswordGenerator/updateUser", "post", requestJson);
            }
            else {
                toastr.error('Session Field Can not be Empty');
            }
        }
        else {
            toastr.error('Please Select Atleast One User');
        }

    }
    getsessionResponse(data : any){
        toastr.success(data.response.message);
        this.checkedUserArray = [];
        document.getElementById("userMangeForm").reset();
    }


    ngOnInit() {

    }

}