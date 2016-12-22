
    export class Address {
        addressLabel: string;
        address1: string;
        address2: string;
        address3: string;
        zipcode: string;
        latitude: string;
        longitude: string;
        primaryKey: string;
        addressId: string;
        addressTypeId: string;
        countryId: string;
        stateId: string;
        cityId: string;
        versionId: number;
        systemInfo:any;
        primaryDisplay: string;
        constructor(){
            this.systemInfo = new SystemInfo();
            this.systemInfo= {activeStatus: 1};
        }

    }

    export class Timezone {
        utcdifference: number;
        gmtLabel: string;
        timeZoneLabel: string;
        country: string;
        cities: string;
        primaryKey: string;
        timeZoneId: string;
        versionId: number;
        systemInfo: SystemInfo;
        primaryDisplay: string;
        constructor(){
            this.systemInfo = new SystemInfo();
            this.systemInfo.activeStatus = 1;
            this.systemInfo.txnAccessCode = 51000;
        }

    }

    export class SystemInfo {
        activeStatus: number;
        txnAccessCode?: number;
    }

    export class CoreContacts {
        firstName: string;
        middleName: string;
        lastName: string;
        nativeTitle: string;
        nativeFirstName: string;
        nativeMiddleName: string;
        nativeLastName: string;
        dateofbirth: string;
        age: string;
        approximateDOB?: any;
        emailId: string;
        phoneNumber: string;
        primaryKey: string;
        contactId: string;
        titleId: string;
        nativeLanguageCode: string;
        genderId: string;
        communicationData: any[];
        address: Address[];
        timezone: Timezone;
        versionId: number;
        systemInfo: SystemInfo;
        primaryDisplay: string;
        constructor(){
            this.address = [];
            this.systemInfo = new SystemInfo();
            this.systemInfo.activeStatus = 1;
            this.systemInfo.txnAccessCode = 51000;
        }
    }

    export class PassRecovery {
        answer: string;
        primaryKey: string;
        passRecoveryId: string;
        questionId: string;
        user: string;
        versionId: number;
        primaryDisplay: string;
        constructor(){

        }
    }

    export class UserDetail {
        userId: string;
        userAccessCode: number;
        multiFactorAuthEnabled?: any;
        genTempOneTimePassword?: any;
        allowMultipleLogin: number;
        isLocked?: any;
        isDeleted?: any;
        changePasswordNextLogin: number;
        passwordExpiryDate?: any;
        passwordAlgo: string;
        lastPasswordChangeDate?: any;
        sessionTimeout: number;
        primaryKey: string;
        userAccessLevelId?: any;
        userAccessDomainId?: any;
        passRecovery: PassRecovery[];
        versionId: number;
        systemInfo: SystemInfo;
        primaryDisplay: string;

        constructor(){
            this.allowMultipleLogin = 0;
            this.passRecovery = [];
            this.userAccessCode = 55005;
            this.systemInfo = new SystemInfo();
            this.systemInfo.activeStatus = 1;
        }


    }


    export class RootObject {
        loginId: string;
        serverAuthImage?: any;
        serverAuthText?: any;
        failedLoginAttempts?: any;
        primaryKey: string;
        loginPk: string;
        coreContacts: CoreContacts;
        user: UserDetail;
        versionId: number;
        systemInfo: SystemInfo;
        primaryDisplay: string;
        constructor(){
            this.systemInfo = new SystemInfo();
            this.systemInfo.activeStatus = 1;
        }

    }


    export class GridData{
        addressType : string;
        address1 : string;
        postalCode : string;
        country : string;
        state : string;
        city : string;
        action : string;

        constructor(){}

    }
    export class PasswordRecoveryGridData{
        securityQuestion: string;
        securityAnswer : string;
        constructor(){}

    }


