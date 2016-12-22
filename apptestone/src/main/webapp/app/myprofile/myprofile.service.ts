
import { Injectable } from '@angular/core';
import {Http, Headers,RequestOptions} from "@angular/http";
@Injectable()
export class MyProfileServices {
    responseData:any;
    callBackFunctionName:any;
    parentRef:any;
    constructor(private _http : Http) {
    }

    findLogedUser(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
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
                    this.saveData();
                }
            );
    }
    saveData(){
        this.parentRef.saveLogedUser(this.responseData);
    }

    saveUser(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;
        let headers = new Headers({ 'Content-Type': 'application/json'  });
        let options = new RequestOptions({headers : headers,method : methodType});
            this._http.put(serviceUrl, requestJson, options).subscribe(
                response=>{
                    this.responseData = response.json();
                },
                error=>{
                },
                ()=>{
                    this.saveuserData();
                }
            );
    }

    saveuserData(){
        this.parentRef.saveServiceResponse(this.responseData);
    }


}