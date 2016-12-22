import { Component, OnInit } from '@angular/core';
import {AddUserService} from "./adduser.service";
import {Router} from "@angular/router";
import {AddressDetails, CoreContacts, GridData, UserDetail} from "./adduser.model";
import {TextInputConfiguration} from "../../../components/textinput/text.input";
import {EmailInputConfiguration} from "../../../components/emailinput/email.input";
import {DateInputConfiguration} from "../../../components/dateinput/date.input";
import {NumberInputConfiguration} from "../../../components/numberinput/number.input";
import {RadioGroupInputConfiguration} from "../../../components/radioinput/radio.group.input";
import {DataGridConfiguration} from "../../../components/datagrid/datagrid";
import {SelectInputConfiguration} from "../../../components/selectinput/select.input";
import {SimpleButtonConfiguration} from "../../../components/button/simple.button";
import {ResetButtonConfiguration} from "../../../components/button/reset.button";
import {CommonUtils} from "../../../components/services/CommonUtils";
import {Router} from "@angular/router";

@Component({

    selector: 'add-user',
    templateUrl: 'app/usermanagement/adduser/adduser.html'
})
export class AddUserComponent implements OnInit {
    gridData : GridData;
    displatGridDataArray : any[];
    displatGridDataObject : Object;
    user : UserDetail;
    loginJson : any;
    userData : CoreContacts;
    address : AddressDetails;
    radioObject : any;
    firstNameConfig;nativeFirstNameConfig;nativeLastNameConfig;nativeMiddleNameConfig;lastNameConfig;middleNameConfig;addressLabelConfig;address1Config;address2Config;address3Config : TextInputConfiguration;
    emailConfig : EmailInputConfiguration;
    dateConfig : DateInputConfiguration;
    radioGroupConfig : RadioGroupInputConfiguration;
    addAddressConfig;SaveConfig;backConfig : SimpleButtonConfiguration;
    ResetConfig : ResetButtonConfiguration;
    phoneNoConfig;latitudeConfig;longitudeConfig;postalCodeConfig;sessionConfig;ageConfig : NumberInputConfiguration;
    countryConfig;stateIdsConfig ;cityIdsConfig;addressTypeConfig;timezoneConfig;languageConfig;titleConfig;nativeTitleConfig:  SelectInputConfiguration;
    countryIdddata;stateIdddata;cityIddData;addressTypeddata ;timezoneddata;languageData; titledata;nativeTitledata;checkboxConfig: any;
    checkBoxData : any = [];
    radioArray : any[];
    dataGridConfig : DataGridConfiguration;

    constructor(private _AddUserService :  AddUserService, private _commonsValidatorService : CommonUtils,private router : Router) {
        this.userData = new CoreContacts();
        this.radioArray;this.radioObject;this.displatGridDataArray = [];
        this.cityIddData;this.stateIdddata;this.countryIdddata;this.addressTypeddata;this.timezoneddata;this.languageData;this.titledata;this.nativeTitledata = [];
        this.address = new AddressDetails();
        this.gridData = new  GridData();
        this.user = new  UserDetail();
        this.directiveConfigClassCall();
    }

    ngOnInit() { }

    directiveConfigClassCall(){
        this.addAddressConfig = new SimpleButtonConfiguration(false,false);
        this.SaveConfig = new SimpleButtonConfiguration(false,false);
        this.backConfig = new SimpleButtonConfiguration(false,false);
        this.ResetConfig = new ResetButtonConfiguration(false,false);
        this.countryConfig = new SelectInputConfiguration(false,false,true,'Please select country','countryName','countryId');
        this.stateIdsConfig = new SelectInputConfiguration(false,false,true,'Please select state',"stateName","stateId");
        this.cityIdsConfig = new SelectInputConfiguration(false,false,true,'Please select city',"cityName","cityId");
        this.addressTypeConfig = new SelectInputConfiguration(false,false,true,'Please select Address Type',"addressType","addressTypeId");
        this.timezoneConfig = new SelectInputConfiguration(false,false,true,'Please select Timezone',"timeZoneLabel","timeZoneId");
        this.languageConfig = new SelectInputConfiguration(false,false,true,'Please select language',"language","languageId");
        this.titleConfig = new SelectInputConfiguration(false,false,true,'Please select title',"titles","titleId");
        this.nativeTitleConfig = new SelectInputConfiguration(false,false,false,'Please select native title',"titles","titleId");
        this.radioGroupConfig =new RadioGroupInputConfiguration('gender','genderId',false,false,true,'Please select gender ');

        this.firstNameConfig = new TextInputConfiguration(false,false,false,true,'','','Invalid First Name');
        this.nativeFirstNameConfig = new TextInputConfiguration(false,false,false,false,'','','Invalid Native First Name');
        this.nativeLastNameConfig = new TextInputConfiguration(false,false,false,false,'','','Invalid Native Last Name');
        this.nativeMiddleNameConfig = new TextInputConfiguration(false,false,false,false,'','','Invalid Native Middle Name');
        this.lastNameConfig = new TextInputConfiguration(false,false,false,true,'','','Invalid Last Name');
        this.middleNameConfig = new TextInputConfiguration(false,false,false,false,'','','Invalid Middle Name');
        this.emailConfig = new EmailInputConfiguration(false,false,false,true,'','','Enter Email address');
        this.dateConfig = new DateInputConfiguration(false,false,false,false,'Date Required');
        this.phoneNoConfig = new TextInputConfiguration(false,false,false,true,'','','Invalid Phone NO ');
        this.postalCodeConfig =  new TextInputConfiguration(false,false,false,true,'','','Invalid postal Code ');
        this.latitudeConfig =  new NumberInputConfiguration(false,false,false,false,'','','Invalid latitude ');
        this.longitudeConfig =  new NumberInputConfiguration(false,false,false,false,'','','Invalid longitude ');
        this.sessionConfig =  new NumberInputConfiguration(false,false,false,true,'120','','Invalid session Timeout ');

        this.addressLabelConfig = new TextInputConfiguration(false,false,false,false,'','','Invalid Address Label');
        this.address1Config = new TextInputConfiguration(false,false,false,true,'','','Invalid Address 1');
        this.address2Config = new TextInputConfiguration(false,false,false,false,'','','Invalid Address 2');
        this.address3Config = new TextInputConfiguration(false,false,false,false,'','','Invalid Address 3');
        this.ageConfig =  new NumberInputConfiguration(false,false,true,false,'2','3','Invalid age ');


        var columns = [];
        columns.push({text:'Address Type', dataIndex: 'addressType', hidden: false});
        columns.push({text:'Address1', dataIndex: 'address1', hidden: false});
        columns.push({text:'Postal Code', dataIndex: 'postalCode', hidden: false});
        columns.push({text:'Country', dataIndex: 'country', hidden: false});
        columns.push({text:'State', dataIndex: 'state', hidden: false});
        columns.push({text:'City', dataIndex: 'city', hidden: false});
        columns.push({text:'Action', dataIndex: '', hidden: false});

        this.dataGridConfig = new DataGridConfiguration(columns);

    }

    addAddress(){
       let addressDetails = new AddressDetails();
        if(!this.displayGridData()) {
            addressDetails = JSON.parse(JSON.stringify(this.address));
            this.userData.address.push(addressDetails);
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
            this.displatGridDataObject = ({'response': {'data': this.displatGridDataArray}});
        }
    }

    resetForm(){
        document.getElementById("userForm").reset();
    }

    getDate(dateString : any){
        let today = new Date();
        let birthDate = new Date(dateString);
        let age = today.getFullYear() - birthDate.getFullYear();
        let m = today.getMonth() - birthDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }
        if(age>18 || age==18) {
            this.userData.dateofbirth = this._commonsValidatorService.getDateTimeZone(dateString);
            this.userData.age = age;
        }
        else {
            this.userData.dateofbirth = '';
            toastr.error('Age Should be greater than or equal to 18');
        }
    }

    selectAddressType(data : any){
        this.gridData.addressType = data.data.addressType;
    }
    radioSelect(data : any){
        this.userData.genderId = data.target.value;
    }

    selectCountryData(data : any){
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

    saveUserData(){
        let requestJson = {};
        let componentArray = [ this.firstNameConfig,this.emailConfig,this.phoneNoConfig,this.sessionConfig,this.lastNameConfig,this.titleConfig,this.timezoneConfig];
        if(this._commonsValidatorService.validateAndShowErrorMessages(componentArray)) {
            return true;
        }
        else {
            requestJson['findKey'] = this.userData.emailId;
           this._AddUserService.checkValidityOfLoginId(this, "checkValidityLoginData", "secure/PasswordGenerator/checkValidityOfLoginId", "post", requestJson);
        }
    }
    checkValidityLoginData (data : any){
        toastr.success(data.response.message);
        if(data.response.success!=false){
            let requestJson = {};
            requestJson['coreContacts'] = this.userData;
            requestJson['loginId'] =this.userData.emailId;
            requestJson['userDetail'] = this.user;
           this._AddUserService.LoginData(this,"loginDataResponse","secure/Login","post",requestJson);
        }
    }
    loginDataResponse(data : any){
        toastr.success(data.response.message);
        let requestJson = {};
        requestJson['findKey'] = data.response.data.loginPk;
        this._AddUserService.passworsGenerator(this,"getpassworsGeneratorDataResponse","secure/PasswordGenerator/generatePassword","post",requestJson);
    }
    getpassworsGeneratorDataResponse(data : any){
        toastr.success(data.response.message);
        let requestJson = {};
        this._AddUserService.loginFindAll(this,"loginResponse","secure/Login/findAll","get",requestJson);
    }

    loginResponse (data : any){
        toastr.success('User Added Successfully');
        document.getElementById("userForm").reset();

    }
    backToUserManagement(){
        this.router.navigate(['/mainpage/UserManagement']);
    }
}