/**
 * Created by prasad on 27/9/16.
 */
import { Injectable } from '@angular/core';
import {Http, Headers,RequestOptions} from "@angular/http";


@Injectable()
export class ChangePasswordServices {
    responseData:any;
    callBackFunctionName:any;
    parentRef:any;


    constructor(private _http : Http) {
    }

    changePassword(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string, requestJson : any){
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
                    this.changeResponse();
                }
            );
    }
    changeResponse(){
        this.parentRef.changePasswordServiceResponse(this.responseData);
    }


}