

import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";


@Injectable()
export class UserManagementService {
    responseData:any;
    callBackFunctionName:any;
    parentRef:any;

    constructor(private _http : Http) {
    }

    findListOfUser(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
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
                    this.setUserData();
                }
            );
    }

    setUserData(){
        this.parentRef.getUserData(this.responseData);
    }

    lockUser(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
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
                    this.lockResponse();
                }
            );

    }
    lockResponse(){
        this.parentRef.lockServiceResponseUser(this.responseData);
    }




    unlockUser(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
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
                this.unlockResponse();
            }
        );

    }

    unlockResponse(){
        this.parentRef.unlockServiceResponseUser(this.responseData);
    }


    resetResendUser(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json'  });
        let options = new RequestOptions({headers : headers,method : methodType});

        this._http.post(serviceUrl,requestJson,options).subscribe(
            response=>{
                this.responseData = response.json();
            },
            error=>{
            },
            ()=>{
                this.resetResendResponse();
            }
        );

    }

    resetResendResponse(){
        this.parentRef.resetResendServiceResponseUser(this.responseData);
    }


    changePasswordNextLogin(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json'  });
        let options = new RequestOptions({headers : headers,method : methodType});
        this._http.post(serviceUrl,requestJson,options).subscribe(
            response=>{
                this.responseData = response.json();
            },
            error=>{
            },
            ()=>{
                this.Response();
            }
        );

    }
    Response(){
        this.parentRef.changePasswordNextLoginResponse(this.responseData);
    }


    sessionTimeOut(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json'  });
        let options = new RequestOptions({headers : headers,method : methodType});
        this._http.post(serviceUrl,requestJson,options).subscribe(
            response=>{
                this.responseData = response.json();
            },
            error=>{
            },
            ()=>{
                this.sessionResponse();
            }
        );

    }

    sessionResponse(){
        this.parentRef.getsessionResponse(this.responseData);
    }




}


