import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";

@Injectable()
export class TreeService {
    treecomponent:any;
    response:any;
    templateConfig:any;
    constructor(private http : Http) {

    }

    callService(treecomponent:any,templateConfig:string,params:any){

            this.treecomponent = treecomponent;
            this.templateConfig = templateConfig;

/**,params,
 { headers : new Headers({ 'Content-Type': 'application/json;charset=UTF-8' })  })
 */

        let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
        let options = new RequestOptions({headers : headers, body : {}, method : templateConfig.requestMethodType });

        this.http.request(templateConfig.url, options).subscribe(response=>{
                
                this.response = response;
            },error=>{
                
                console.error('Error Occured -> Classname : '+this.constructor.name);
            },
            ()=>{
                this.setTreeData();
            });
    }
    setTreeData (){
        let resp = JSON.parse(this.response._body).response;

        if(resp.data){

            this.treecomponent.servicedata = [resp.data];

            this.treecomponent.loadTreeComponent();
        }
    }



}
