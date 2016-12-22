/**
 * Created by prasad on 14/9/16.
 */
import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
@Injectable()
export class AddUserService {
    responseData:any;
    callBackFunctionName:any;
    parentRef:any;
    constructor(private _http : Http) {
    }

    findByCountryIdData(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8'  });
        let options = new RequestOptions({headers : headers,method : methodType});
            this._http.post(serviceUrl,requestJson,options).subscribe(
                response=>{
                    this.responseData = response.json();
                },
                error=>{
                },
                ()=>{
                    this.setData();
                }
            );
    }

    setData(){
        this.parentRef.setfindByCountryIdData(this.responseData);
    }

    findByStateIdData(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8'  });
        let options = new RequestOptions({headers : headers,method : methodType});
            this._http.post(serviceUrl,requestJson,options).subscribe(
                response=>{
                    this.responseData = response.json();
                },
                error=>{
                },
                ()=>{
                    this.setCityData();
                }
            );
    }
    setCityData(){
        this.parentRef.setfindByStateIdData(this.responseData);
    }



    checkValidityOfLoginId(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8'  });
        let options = new RequestOptions({headers : headers,method : methodType});
            this._http.post(serviceUrl,requestJson,options).subscribe(
                response=>{
                    this.responseData = response.json();
                },
                error=>{
                },
                ()=>{
                    this.setLoginData();
                }
            );
    }

    setLoginData(){
        this.parentRef.checkValidityLoginData(this.responseData);
    }


    LoginData(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8'  });
        let options = new RequestOptions({headers : headers,method : methodType});
            this._http.post(serviceUrl,requestJson,options).subscribe(
                response=>{
                    this.responseData = response.json();
                },
                error=>{
                },
                ()=>{
                    this.getLoginData();
                }
            );
    }

    getLoginData(){
        this.parentRef.loginDataResponse(this.responseData);
    }


    passworsGenerator(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8'  });
        let options = new RequestOptions({headers : headers,method : methodType});
            this._http.post(serviceUrl,requestJson,options).subscribe(
                response=>{
                    this.responseData = response.json();
                },
                error=>{
                },
                ()=>{
                    this.getpassworsGeneratorData();
                }
            );
    }

    getpassworsGeneratorData(){
        this.parentRef.getpassworsGeneratorDataResponse(this.responseData);
    }

    loginFindAll(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8'  });
        let options = new RequestOptions({headers : headers,method : methodType});

            this._http.get(serviceUrl,options).subscribe(
                response=>{
                    this.responseData = response.json();
                },
                error=>{
                },
                ()=>{
                    this.response();
                }
            );
    }

    response(){
        this.parentRef.loginResponse(this.responseData);
    }


}