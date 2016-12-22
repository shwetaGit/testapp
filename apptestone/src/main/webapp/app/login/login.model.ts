

export class loginCredentials{
    _loginId : string;
    _password    : string;
    _latitude : string;
    _longitude : string;

    constructor(loginId : string, password : string, latitude : string,longitude : string){
        this.loginId = loginId;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    get loginId():string {
        return this._loginId;
    }
    set loginId(loginId:string) {
        this._loginId = loginId;
    }

    get password():string {
        return this._password;
    }
    set password(paasword:string) {
        this._password = paasword;
    }

    get latitude():string {
        return this._latitude;
    }
    set latitude(latitude:string) {
        this._latitude = latitude;
    }
    get longitude():string {
        return this._longitude;
    }
    set longitude(longitude:string) {
        this._longitude = longitude;
    }

}