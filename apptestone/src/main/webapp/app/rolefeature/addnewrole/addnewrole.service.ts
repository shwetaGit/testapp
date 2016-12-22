import { Injectable } from '@angular/core';
import {Http, Headers,RequestOptions} from "@angular/http";
import {AppUrl} from "../../constant";


@Injectable()
export class RoleFeatureServices {

    mainScreenMenuUrl : string;
    responseData:any;
    callBackFunctionName:any;
    parentRef:any;
    menuList : any;
    menuData : any;

    constructor(private _http : Http) {

        this.mainScreenMenuUrl = AppUrl+'secure/RoleFeatureService/findMainScreenMenus';
    }
    getMenus(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8'  });
        let options = new RequestOptions({headers : headers,method : methodType});
           return this._http.post(serviceUrl,requestJson,options)

    }

    saveUser(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
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
                    this.saveData();
                }
            );
    }

    saveData(){
        this.parentRef.SaveResponseData(this.responseData);
    }

}