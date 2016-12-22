/**
 * Created by prasad on 27/9/16.
 */
import { Component, OnInit } from '@angular/core';
import {MyProfileServices} from "./myprofile.service";
import {SimpleButtonConfiguration} from "../../components/button/simple.button";
import {EmailInputConfiguration} from "../../components/emailinput/email.input";
import {DateInputConfiguration} from "../../components/dateinput/date.input";
import {RadioGroupInputConfiguration} from "../../components/radioinput/radio.group.input";
import {ResetButtonConfiguration} from "../../components/button/reset.button";
import {NumberInputConfiguration} from "../../components/numberinput/number.input";
import {TextInputConfiguration} from "../../components/textinput/text.input";
import {SelectInputConfiguration} from "../../components/selectinput/select.input";
import {DataGridConfiguration} from "../../components/datagrid/datagrid";
import {
    CoreContacts, GridData, RootObject, Timezone, SystemInfo, Address, PassRecovery,
    PasswordRecoveryGridData, UserDetail
} from "./myprofile.model";
import {TextAreaInputConfiguration} from "../../components/textarea/textarea.input";
import {AddUserService} from "../usermanagement/adduser/adduser.service";
import {CommonUtils} from "../../components/services/CommonUtils";

@Component({
    templateUrl: 'app/myprofile/myprofile.html'
})
export class MyProfileComponent implements OnInit {

    coreContacts : CoreContacts;
    rootObject : RootObject;
    timezone : Timezone;
    gridData : GridData;
    systemInfo : SystemInfo;
    user : UserDetail;
    address : Address;
    passwordRecovery : PassRecovery;
    passwordRecoveryGridData :  PasswordRecoveryGridData;
    radioObject : Object;
    loginIdConfig;firstNameConfig;nativeFirstNameConfig;nativeLastNameConfig;nativeMiddleNameConfig;lastNameConfig;middleNameConfig;addressLabelConfig;address1Config;address2Config;address3Config : TextInputConfiguration;
    emailConfig : EmailInputConfiguration;
    dateConfig : DateInputConfiguration;
    radioGroupConfig : RadioGroupInputConfiguration;
    addAddressConfig;SaveConfig;QAConfig : SimpleButtonConfiguration;
    ResetConfig : ResetButtonConfiguration;
    dataGridConfig ; securityQandAnsConfig : DataGridConfiguration;
    securityAnsConfig : TextAreaInputConfiguration;
    phoneNoConfig;latitudeConfig;longitudeConfig;postalCodeConfig;sessionConfig;ageConfig : NumberInputConfiguration;
    securityQConfig;countryConfig;stateIdsConfig ;cityIdsConfig;addressTypeConfig;timezoneConfig;languageConfig;titleConfig;nativeTitleConfig:  SelectInputConfiguration;
    countryIdddata;stateIdddata;cityIddData;addressTypeddata ;timezoneddata;languageData; titledata;nativeTitledata;checkboxConfig: any;
    displatGridDataArray : any[];
    securityQData : any[]=[];
    displatGridDataObject : Object;
    securityQandAnsDataObject : Object;
    securityQandAnsDataArray : any[];
    mydate : Object;

    constructor(private _myProfileServices : MyProfileServices,private _AddUserService :  AddUserService,private _commonsValidatorService : CommonUtils) {
        this.securityQandAnsDataArray = [];
        this.displatGridDataArray = [];
        this.rootObject = new RootObject();
        this.coreContacts = new CoreContacts();
        this.timezone = new Timezone();
        this.gridData = new GridData();
        this.user =  new UserDetail();
        this.address = new Address();
        this.systemInfo = new SystemInfo();
        this.passwordRecovery = new PassRecovery();
        this.passwordRecoveryGridData = new PasswordRecoveryGridData();

      //  this.radioArray;this.radioObject;this.displatGridDataArray = [];
        this.cityIddData;this.stateIdddata;this.countryIdddata;this.addressTypeddata;this.timezoneddata;this.languageData;this.titledata;this.nativeTitledata = [];
        this.callLaogedUserService();
       this.directiveConfigClassCall();
    }

    ngOnInit() { }

    directiveConfigClassCall(){
        this.addAddressConfig = new SimpleButtonConfiguration(false,false);
        this.QAConfig = new SimpleButtonConfiguration(false,false);
        this.SaveConfig = new SimpleButtonConfiguration(false,false);
        this.ResetConfig = new ResetButtonConfiguration(false,false);
        this.countryConfig = new SelectInputConfiguration(false,false,true,'Please select country','countryName','countryId');
        this.stateIdsConfig = new SelectInputConfiguration(false,false,true,'Please select state',"stateName","stateId");
        this.cityIdsConfig = new SelectInputConfiguration(false,false,true,'Please select city',"cityName","cityId");
        this.addressTypeConfig = new SelectInputConfiguration(false,false,true,'select Address Type',"addressType","addressTypeId");
        this.timezoneConfig = new SelectInputConfiguration(false,false,true,'select Timezone',"timeZoneLabel","timeZoneId");
        this.languageConfig = new SelectInputConfiguration(false,false,true,'select language',"language","languageId");
        this.titleConfig = new SelectInputConfiguration(false,false,true,'select title',"titles","titleId");
        this.nativeTitleConfig = new SelectInputConfiguration(false,false,false,'select title',"titles","titleId");
        this.radioGroupConfig =new RadioGroupInputConfiguration('gender','genderId',false,false,true,'select gender ');

        this.firstNameConfig = new TextInputConfiguration(false,false,false,true,'','','Invalid First Name');
        this.nativeFirstNameConfig = new TextInputConfiguration(false,false,false,false,'','','Invalid Native First Name');
        this.nativeLastNameConfig = new TextInputConfiguration(false,false,false,false,'','','Invalid Native Last Name');
        this.nativeMiddleNameConfig = new TextInputConfiguration(false,false,false,false,'','','Invalid Native Middle Name');
        this.lastNameConfig = new TextInputConfiguration(false,false,false,true,'','','Invalid Last Name');
        this.middleNameConfig = new TextInputConfiguration(false,false,false,false,'','','Invalid Middle Name');
        this.emailConfig = new EmailInputConfiguration(false,false,false,true,'','','Enter Email address');
        this.dateConfig = new DateInputConfiguration(false,false,false,false,'Date Required');
        this.phoneNoConfig = new TextInputConfiguration(false,false,false,true,'10','10','Invalid Phone NO ');
        this.latitudeConfig =  new NumberInputConfiguration(false,false,false,false,'','','Invalid latitude ');
        this.longitudeConfig =  new NumberInputConfiguration(false,false,false,false,'','','Invalid longitude ');
        this.postalCodeConfig =  new TextInputConfiguration(false,false,false,true,'','','Invalid postal Code ');
        this.sessionConfig =  new NumberInputConfiguration(false,false,false,true,'120','','Invalid seesion Timeout ');

        this.addressLabelConfig = new TextInputConfiguration(false,false,false,false,'','','Invalid Address Label');
        this.address1Config = new TextInputConfiguration(false,false,false,true,'','','Invalid Address 1');
        this.address2Config = new TextInputConfiguration(false,false,false,false,'','','Invalid Address 2');
        this.address3Config = new TextInputConfiguration(false,false,false,false,'','','Invalid Address 3');
        this.ageConfig =  new NumberInputConfiguration(false,false,true,false,'2','3','Invalid age ');

        this.loginIdConfig = new TextInputConfiguration(false,false,true,false,'','','login Id');

        this.securityQConfig = new SelectInputConfiguration(false,false,false,'Please select security Question',"questionDetails","questionId");
        this.securityAnsConfig = new TextAreaInputConfiguration(false,false,false,false,'','','Plz fill Security Answer');


        let columns = [];
        columns.push({text:'Address Type', dataIndex: 'addressType', hidden: false});
        columns.push({text:'Address1', dataIndex: 'address1', hidden: false});
        columns.push({text:'Postal Code', dataIndex: 'postalCode', hidden: false});
        columns.push({text:'Country', dataIndex: 'country', hidden: false});
        columns.push({text:'State', dataIndex: 'state', hidden: false});
        columns.push({text:'City', dataIndex: 'city', hidden: false});
        columns.push({text:'Action', dataIndex: '', hidden: false});
        this.dataGridConfig = new DataGridConfiguration(columns);

        let securityColumn = [];
        securityColumn.push({text:'securityQuestion', dataIndex: 'securityQuestion', hidden: false});
        securityColumn.push({text:'securityAnswer', dataIndex: 'securityAnswer', hidden: false});

        this.securityQandAnsConfig = new DataGridConfiguration(securityColumn);

    }
    selectNativetTitle(data : any){
    }

    callLaogedUserService(){
        let requestJson ={};
        this._myProfileServices.findLogedUser(this,"saveLogedUser","secure/PasswordGenerator/findLoggedInUser","get",requestJson);
    }
    saveLogedUser (logedUserData : any){

        debugger;
            this.rootObject =logedUserData.response.data;
            this.coreContacts = logedUserData.response.data.coreContacts;
          //  this.mydate = logedUserData.response.data.coreContacts.dateofbirth;
            this.mydate = this.setDate(logedUserData.response.data.coreContacts.dateofbirth);
            this.timezone = logedUserData.response.data.coreContacts.timezone;
            this.systemInfo = logedUserData.response.data.systemInfo;
            this.user = logedUserData.response.data.userDetail;

           /* this.addDataToGrid();         // TODO if we get display name from DB.
            this.addDataToPasswordGrid();*/

    }

    setDate(stringDate){
        let date : string;
        let time : string;
        let finalString:string;
        let stringArray : any[] =[];
        let demoString = stringDate.split(" ");
        for(var i =0; i < 2; i++){
            stringArray.push(demoString[i]);
             console.log(stringArray);
        }
            date = stringArray[0];
          //  time = stringArray[1];

        return finalString = date;

    }

    addDataToGrid(){
        this.coreContacts.address.forEach((option)=>{
            this.gridData.addressType = option.addressTypeId;
            this.gridData.city = option.cityId;
            this.gridData.state = option.stateId;
            this.gridData.country = option.countryId;
            this.gridData.postalCode = option.zipcode;
            this.gridData.address1 = option.address1;
            let gridData = new GridData();
            gridData = JSON.parse(JSON.stringify(this.gridData));
            this.displatGridDataArray.push(gridData);
            this.displatGridDataObject = ({'response':{'data': this.displatGridDataArray}});
        })
    }

    addDataToPasswordGrid(){
        this.user.passRecovery.forEach((option)=>{
            this.passwordRecoveryGridData.securityAnswer = option.answer;
            this.passwordRecoveryGridData.securityQuestion = option.questionId;
            let passsGrid = new PasswordRecoveryGridData();
            passsGrid = JSON.parse(JSON.stringify(this.passwordRecoveryGridData));
            this.securityQandAnsDataArray.push(passsGrid);
            this.securityQandAnsDataObject = ({'response':{'data': this.securityQandAnsDataArray}});

        })
    }





    getDate(dateString : any){
        //this.coreContacts.dateofbirth = dateString;
        let today = new Date();
        let birthDate = new Date(dateString);
        let age = today.getFullYear() - birthDate.getFullYear();
        let m = today.getMonth() - birthDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }
        if(age>18 || age==18) {
            this.coreContacts.dateofbirth = this._commonsValidatorService.getDateTimeZone(dateString);
            this.coreContacts.age = age;
        }
        else {
            this.coreContacts.dateofbirth = '';
            toastr.error('Age Should be greater than or equal to 18');
        }
    }

    selectSecurityQue(data : any){
        console.log(data.data.question);
        this.passwordRecoveryGridData.securityQuestion = data.data.question;
        console.log(this.passwordRecoveryGridData.securityQuestion);
    }

    addQAndAns(){
        let passwordRecovery = new PassRecovery();
        this.passwordRecoveryGridData.securityAnswer = this.passwordRecovery.answer;
        let gridData = new PasswordRecoveryGridData();
        gridData = JSON.parse(JSON.stringify(this.passwordRecoveryGridData));
        this.securityQandAnsDataArray.push(gridData);
        this.securityQandAnsDataObject = ({'response':{'data': this.securityQandAnsDataArray}});
      //  this.passwordRecovery.user = this.user.userId;
       // this.passwordRecovery.primaryDisplay=this.passwordRecovery.questionId+','+this.passwordRecovery.answer;
        passwordRecovery = JSON.parse(JSON.stringify(this.passwordRecovery));
        this.user.passRecovery.push(passwordRecovery);
        toastr.success('Security Question Added Successfully');
        document.getElementById("securityQ").reset();

    }


    selectAddressType(data : any){
        this.gridData.addressType = data.data.addressType;

    }
    selectCountryData(data : any){
        console.log(data);
        this.gridData.country = data.data.countryName;
        let requestJson = {};
        requestJson['findKey'] =data.value;
        this._AddUserService.findByCountryIdData(this,"setfindByCountryIdData","secure/State/findByCountryId","post",requestJson);

    }

    setfindByCountryIdData (data){
        this.stateIdddata = data;

    }

    selectStateData(data : any){
        this.gridData.state = data.data.stateName;
        if(data != null) {
            debugger;
            let requestJson = {};
            requestJson['findKey'] = data.value;
            this._AddUserService.findByStateIdData(this,"setfindByStateIdData","secure/City/findByStateId","post",requestJson);
        }
    }

    setfindByStateIdData (data){
        this.cityIddData = data;
    }
    selectCityData(data : any){
        this.gridData.city = data.data.cityName;
    }

    addAddress(){
        let addressDetails = new Address();
        if(!this.displayGridData()) {
            addressDetails = JSON.parse(JSON.stringify(this.address));
            this.coreContacts.address.push(addressDetails);
            toastr.success('Address Added Successfully');
            document.getElementById("addressForm").reset();
        }
    }
    displayGridData(){
        this.gridData.postalCode = this.address.zipcode;
        this.gridData.address1 = this.address.address1;
        let gridData = new GridData();
        gridData = JSON.parse(JSON.stringify(this.gridData));

        let componentArray = [this.postalCodeConfig, this.countryConfig,this.stateIdsConfig,this.cityIdsConfig,this.addressTypeConfig, this.address1Config];

        if(this._commonsValidatorService.validateAndShowErrorMessages(componentArray)) {
            return true;
        }
        else {
            this.displatGridDataArray.push(gridData);
            this.displatGridDataObject = ({'response':{'data': this.displatGridDataArray}});
        }
    }

    saveUserData(){
        let requestJson = {};
        requestJson['coreContacts'] = this.coreContacts;
       /* requestJson.coreContacts.dateofbirth = "29-7-1991 18:30:00 GMT";*/
        requestJson['loginId'] =this.rootObject.loginId;
        requestJson['versionId'] =this.rootObject.versionId;
        requestJson['loginPk'] =this.rootObject.loginPk;
        requestJson['primaryKey'] =this.rootObject.primaryKey;
        requestJson['failedLoginAttempts'] =this.rootObject.failedLoginAttempts;
        requestJson['serverAuthText'] = this.rootObject.serverAuthText;
        requestJson['serverAuthImage'] = this.rootObject.serverAuthImage;
        requestJson['userDetail'] = this.user;
        requestJson['systemInfo']={activeStatus:1};
        requestJson['primaryDisplay']="";
        if(this.user.passRecovery.length >=3) {
         this._myProfileServices.saveUser(this, "saveServiceResponse", "secure/Login", "put", requestJson);
        }else{
            toastr.error('3 Security Questions Required');
        }
    }
    saveServiceResponse(data : any){
            if(!data.response.success){
                toastr.success('Updating Login Request Succeeded');
            }
    }

}