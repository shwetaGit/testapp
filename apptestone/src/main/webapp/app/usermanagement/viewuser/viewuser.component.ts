import { Component, OnInit } from '@angular/core';
import {UserRoleService} from "../../userrole/adduserrolemapping/userrolemapping.service";
import {Router, ActivatedRoute} from "@angular/router";
import {CoreContacts, RootObject, Timezone, GridData, UserDetail} from "./viewuser.model";
import {TextInputConfiguration} from "../../../components/textinput/text.input";
import {DataGridConfiguration} from "../../../components/datagrid/datagrid";
import {RadioGroupInputConfiguration} from "../../../components/radioinput/radio.group.input";
import {SimpleButtonConfiguration} from "../../../components/button/simple.button";

@Component({
    selector: 'view-user',
  templateUrl:'app/usermanagement/viewuser/viewuser.html'
})
export class ViewUserComponent implements OnInit {

    data : Object;
    coreContacts : CoreContacts;
    rootObject : RootObject;
    timezone : Timezone;
    gridData : GridData;
    user : UserDetail;
    displatGridDataObject;radioObject : Object;
    backConfig : SimpleButtonConfiguration ;
    radioGroupConfig : RadioGroupInputConfiguration;
    firstNameConfig;nativeFirstNameConfig;nativeLastNameConfig;nativeMiddleNameConfig;lastNameConfig;middleNameConfig : TextInputConfiguration;
    phoneNoConfig;loginConfig;emailConfig;sessionConfig;ageConfig;timezoneConfig;languageConfig;titleConfig;nativeTitleConfig;dobConfig :TextInputConfiguration;
    dataGridConfig : DataGridConfiguration;

    constructor(private _userRoleService : UserRoleService,private router : Router,activatedRoute: ActivatedRoute) {
        this.rootObject = new RootObject();
        this.coreContacts = new CoreContacts();
        this.timezone = new Timezone();
        this.gridData = new GridData();
        this.user =  new UserDetail();
        this.getUserList(activatedRoute.snapshot.params['userId']);
        this.directiveConfigClassCall();



    }

    directiveConfigClassCall(){
        this.backConfig = new SimpleButtonConfiguration(false,false);
        this.radioGroupConfig =new RadioGroupInputConfiguration('displayField','valueField',false,false,true,'');
        this.firstNameConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.lastNameConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.nativeFirstNameConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.nativeLastNameConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.nativeMiddleNameConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.middleNameConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.phoneNoConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.ageConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.dobConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.titleConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.nativeTitleConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.emailConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.timezoneConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.languageConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.sessionConfig = new TextInputConfiguration(false,false,true,false,'','','');
        this.loginConfig = new TextInputConfiguration(false,false,true,false,'','','');
        var columns = [];
        columns.push({text:'Address Type', dataIndex: 'addressType', hidden: false});
        columns.push({text:'Address1', dataIndex: 'address1', hidden: false});
        columns.push({text:'Postal Code', dataIndex: 'postalCode', hidden: false});
        columns.push({text:'Country', dataIndex: 'country', hidden: false});
        columns.push({text:'State', dataIndex: 'state', hidden: false});
        columns.push({text:'City', dataIndex: 'city', hidden: false});
        this.dataGridConfig = new DataGridConfiguration(columns);
        this.radioObject={response:{data: [{displayField : 'Male' , valueField : '1'},
        {displayField : 'Female' , valueField : '2'}]}};

    }

    getUserList(userId : any){
        let requestJson ={'findKey':userId};
       this._userRoleService.findListOfUser(this,"getUserData","secure/Login/findByUserId","post",requestJson);

    }
    getUserData (data: any) {
        data.response.data.forEach((option)=>{
            this.rootObject = option;
            this.coreContacts = option.coreContacts;
            this.timezone = this.coreContacts.timezone;
            this.user = option.userDetail;
        })
        this.addDataToGrid();

    }
    addDataToGrid(){
        let displatGridDataArray:any[]=[];
        this.coreContacts.address.forEach((option)=>{
            this.gridData.addressType = option.addressTypeId;
            this.gridData.city = option.cityId;
            this.gridData.state = option.stateId;
            this.gridData.country = option.countryId;
            this.gridData.postalCode = option.zipcode;
            this.gridData.address1 = option.address1;
            let gridData = new GridData();
            gridData = JSON.parse(JSON.stringify(this.gridData));
            displatGridDataArray.push(gridData);
            this.displatGridDataObject = ({'response':{'data': displatGridDataArray}});
        })
    }

    ngOnInit() { }

    backToUserManagement(){
        this.router.navigate(['/mainpage/UserManagement']);
    }

}