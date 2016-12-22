import {Injectable} from '@angular/core';
import {Http, Headers} from "@angular/http";
import { REPORT_DATAPOINT_URL} from "../../../app/constant";

@Injectable()
export class ReportChartService {

    chartComponent:any;
    responseData :any ;
    reportId:string;

    constructor(private _http:Http) {
    }

    getCharts(chartComponent:any,reportId :string){
        this.chartComponent = chartComponent;
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

        if(data && data.charts){
            this.chartComponent.chartData = data.charts;
        }else{
            /*alert("Report Data Points"+body.response.message);*/
            console.log("Report Data Points getCharts() " +body.response.message);
        }
    }
}
