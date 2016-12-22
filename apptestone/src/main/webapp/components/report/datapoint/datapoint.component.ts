import {Component, OnInit, Input} from '@angular/core';
import {HTTP_PROVIDERS} from "@angular/http";
import {ReportDataPointService} from "./datapoint.service";

@Component({
    selector: 'report-datapoint-component',
    template:
      `  <div style="margin:0px 15px 0px 15px;">
           
    <span  *ngFor="let dp of dataPointData;">


        <div> <b>{{dp.label}}</b> </div>
        <span class="datapoint-panel" *ngFor="let val of dp.value;let inx = index;">
            <span>
          
            <button type="button" class="btn {{dataPointStyle[inx>9?inx%10:inx]}}"  > 
                    <b>{{val.statistic}}</b><br>{{val.description}}
            </button>
            
            </span>
            
        </span>
       
    </span>
 
    </div>
    `,
    providers:[HTTP_PROVIDERS,ReportDataPointService],
    styleUrls:['app/../components/report/report.css']
})
export class ReportDataPointComponent implements OnInit {
   // @Input() reportComponent:Object;
    @Input() dataPointData : any[];
    @Input() reportId : string;

    dataPointStyle :any = ["dp1","dp2","dp3","dp4","dp5","dp6","dp7","dp8","dp9","dp10"];
    currentDataPointIdx :number = 0;
    
    constructor(private reportDataPointService:ReportDataPointService) {
    }

    ngOnInit() {

    }
    ngAfterViewInit(){

    }
    loadcomponent(){
        this.reportDataPointService.getDataPoints(this,this.reportId);
    }
    

}