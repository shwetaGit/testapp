import {Injectable} from '@angular/core';
import {Http, Headers} from "@angular/http";
import { REPORT_DATAPOINT_URL} from "../../../app/constant";

@Injectable()
export class ReportDataPointService {

    dataPointComponent:any;
    responseData :any ;
    reportId:string;

    constructor(private _http:Http) {
    }

    getDataPoints(dataPointComponent:any,reportId :string){
        this.dataPointComponent = dataPointComponent;
        let jsonData = {id:reportId};

        this._http.post(REPORT_DATAPOINT_URL,jsonData,{headers : new Headers({ 'Content-Type': 'application/json;charset=UTF-8' })  })

            .subscribe(response=>{
                    this.responseData = response; },error=>{console.error('Error Occured -> Classname : '+this.constructor.name);},
                ()=>{
                    this.sendData();
                });
    }
    sendData (){
        let body = JSON.parse(this.responseData._body);
        let data = body.response.data;
        if(data && data.datapoints){
            this.dataPointComponent.dataPointData = data.datapoints;
        }else{
            console.log("Report Data Points getDataPoints() " +body.response.message);
        }
    }
}
