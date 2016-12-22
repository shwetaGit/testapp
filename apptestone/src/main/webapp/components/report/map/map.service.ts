import {Injectable} from '@angular/core';
import {REPORT_MAP_DATA_URL} from "../../../app/constant";
import {Headers, Http} from "@angular/http";

@Injectable()
export class ReportMapService {

    mapComponent:any;
    responseData :any ;
    constructor(private _http:Http) {
    }
    getMapData(mapComponent :any ,reportId:string){

        this.mapComponent = mapComponent;
        let jsonData = {id:reportId};
        this._http.post(REPORT_MAP_DATA_URL,jsonData,{ headers : new Headers({ 'Content-Type': 'application/json;charset=UTF-8' })  })

            .subscribe(response=>{

                    this.responseData = response; },error=>{console.error('Error Occured -> Classname : '+this.constructor.name);},
                ()=>{
                    this.sendData();
                });

    }

    sendData (){
        let body = JSON.parse(this.responseData._body);
        let data = body.response.data;
        if(data && data.mapdata){
            this.mapComponent.mapData  = data.mapdata;
            this.mapComponent.showMarker();
        }else{
            console.log("Report Map getMapData() " +body.response.message);

        }

    }

}