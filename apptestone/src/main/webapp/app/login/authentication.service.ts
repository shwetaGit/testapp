
import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
import {AppUrl} from "../constant";
import {loginCredentials} from "./login.model";


@Injectable()
export class AuthenticationService {
    loginAuthenticationUrl : string;
    logoutAuthenticationUrl : string;


    constructor(private _http : Http) {
        this.loginAuthenticationUrl = AppUrl+'secure/Authentication/authenticate';
        this.logoutAuthenticationUrl = AppUrl+'secure/Logout';
    }

    loginServiceAuthenticateUser(loginModel : loginCredentials){
        let body = { 'loginId' : loginModel.loginId, 'password' : loginModel.password, latitude : loginModel.latitude, longitude : loginModel.longitude };;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({headers : headers,method : 'post'});

        try {
           return this._http.post(this.loginAuthenticationUrl, body, options)

        }
        catch (e){
            console.log('invalid auth');
        }

    }

    logOutServiceAuthenticateUser(){

        return this._http.post(this.logoutAuthenticationUrl,null,{ jsonData : {},headers : new Headers({ 'Content-Type': 'application/json;charset=UTF-8' })});

    }

}
