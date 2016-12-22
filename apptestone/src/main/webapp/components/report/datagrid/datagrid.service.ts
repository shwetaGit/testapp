import {Injectable} from '@angular/core';
import {Http, Headers} from "@angular/http";
import {REPORT_GRID_DATA_URL, REPORT_CONFIGURATION} from "../../../app/constant";

@Injectable()
export class ReportDataGridService {

    dataGridComponent:any;
    responseData :any ;
    reportId:string;
    constructor(private _http:Http) {

    }

    getGridData(dataGridComponent :any ,reportId:string,pagingData:any){

       this.dataGridComponent = dataGridComponent;

        this._http.post(REPORT_GRID_DATA_URL,{id:reportId,start:pagingData.start,page:pagingData.currentPage},{ headers : new Headers({ 'Content-Type': 'application/json;charset=UTF-8' })  })

            .subscribe(response=>{

                this.responseData = response; },error=>{console.error('Error Occured -> Classname : '+this.constructor.name);},
                ()=>{
                    this.sendData();
                });

    }

    sendData (){
       let body = JSON.parse(this.responseData._body);
       let data = body.response.data;

        if(data){
            this.dataGridComponent.gridData = data;
            this.dataGridComponent.pagingData.total = body.response.total;

        }else{
            console.log("Report Data Grid getGridData() " +body.response.message);

        }
    }

}