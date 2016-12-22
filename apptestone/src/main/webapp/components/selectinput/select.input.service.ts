/**
 * Created by ketan on 7/9/16.
 */

import{Injectable}   from '@angular/core';
import {Http, RequestOptions, Headers} from '@angular/http';
import {AppUrl} from "../constant";

@Injectable()
export class SelectInputService {

    responseData : any;
    callBackFunctionName : any;
    parentRef : any;

    constructor(private _http : Http){

    }

    getData(parentRef : any, callBackFunctionName: any, serviceUrl : string, methodType: string){
        this.parentRef = parentRef;
        this.callBackFunctionName = callBackFunctionName;

        let requestJson = {};
        let headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8'  });
        let options = new RequestOptions({headers : headers,method : methodType});

        if(methodType == "post"){

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

        }else if(methodType == "get"){
            this._http.get(serviceUrl,options).subscribe(
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
    }

    setData (){
        this.parentRef.setData(this.responseData);
    }

}
