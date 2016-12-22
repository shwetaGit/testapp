

export class CoreContacts{
    titleId : string;
    nativeTitle : string;
    nativeLanguageCode : string;
    timezone : Object;
    firstName : string;
    middleName : string;
    lastName : string;
    nativeFirstName : string;
    nativeMiddleName : string;
    nativeLastName : string;
    dateofbirth      : string;
    age             : string;
    genderId          : string;
    emailId         : string;
    phoneNumber         : string;

    address : Array<AddressDetails>;

    constructor(){
        this.address = [];
        this.timezone = {timeZoneId:''};




    }
}
export class UserDetail{
    sessionTimeout  : string;
    allowMultipleLogin : number;
    changePasswordNextLogin : number;
    userAccessCode : number;
    systemInfo : any;

    constructor()
    {
        this.allowMultipleLogin = 0;
        this.changePasswordNextLogin = 1;
        this.userAccessCode = 55005;
        this.systemInfo ={activeStatus:1};
    }
}


export class AddressDetails{
    addressTypeId     : string;
    addressLabel    : string;
    address1        : string;
    address2        : string;
    address3        : string;
    latitude        : number;
    longitude       : number;
    zipcode         : number;
    countryId         : string;
    stateId           : string;
    cityId            : string;



    constructor(){
        this.addressTypeId = '';
        this.addressLabel = '';
        this.address1 = '';
        this.address2 = '';
        this.address3 = '';
        this.latitude = null;
        this.longitude = null;
        this.zipcode = null;
        this.countryId ='';
        this.stateId = '';
        this.cityId = '';
    }



}

export class GridData{
    addressType : string;
    address1 : string;
    postalCode : number;
    country : string;
    state : string;
    city : string;
    action : any;
    constructor(){}

}





