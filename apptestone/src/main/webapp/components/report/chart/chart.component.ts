import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {ReportChartService} from "./chart.service";
import {HTTP_PROVIDERS} from "@angular/http";
import {ReportIndividualChartComponent} from "./individualchart.component";

@Component({
    selector: 'report-chart-component',
    template : `  
 
 <div class="modal fade chartModal" id="chartFullScreenModalWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <!--Content-->
        <div class="modal-content" style="border: 0px solid yellow;" >
            <!--Header-->
             <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            
            <div class="container-fluid centeredCls">
                <div class="col-lg-2">
            
                </div>
                <div class="col-lg-8" style="border: 0px solid red;">
             <div id="chartFullScreenContainer" style="border: 0px solid green;"></div>
                </div>
                <div class="col-lg-2">
            
                </div>
                       
            
            </div>
        </div>
    </div>
    </div>
    <div class = "col-md-12" style="overflow:auto;padding-top:10px;">
<div *ngFor="let ch of chartData" >
      
        <div [ngSwitch]="ch.group">
         <div *ngSwitchCase="true">
         <b>{{ch.chartTitle}}</b>
         <div *ngFor="let grpCh of ch.groupCharts;let idx=index;" >
         
                   <report-individual-chart-component  [chartJson]="grpCh" (fullScreenEmitter)="openChartInFullscreen($event)"></report-individual-chart-component>
         </div>
         </div>
        <div *ngSwitchDefault>
          <report-individual-chart-component  [chartJson]="ch" (fullScreenEmitter)="openChartInFullscreen($event)"></report-individual-chart-component>
        </div>
        </div>
       </div>
    </div>
    `,
    directives:[ReportIndividualChartComponent],
    providers:[HTTP_PROVIDERS,ReportChartService],
    styleUrl:['app/../components/report/report.css']
})
export class ReportChartComponent implements OnInit {

    @Input() reportId : string;
    chartData : any[];
    chartId: any;
  //  @Input() reportComponent:Object;
    constructor(private reportChartService:ReportChartService) {
    }

    ngOnInit() {

    }
    ngAfterViewInit(){
        this.chartId = Math.random();
        new WOW().init();
    }
    loadcomponent(){
        this.reportChartService.getCharts(this,this.reportId);
    }
    openChartInFullscreen1(chartJson:any){
   /* $('#myModal1').on('shown.bs.modal', function () {
   
    $(this).find('.modal-dialog').css({width:'auto',
                               height:'auto', 
                              'max-height':'100%'});
});*/
     $(myModal).modal('show');
    }
  
    openChartInFullscreen(chartJson:any){


        chartJson["renderAt"] = "chartFullScreenContainer";

       /* chartJson.width = 1000;//$('.chartModal').width()-400;
        chartJson.height = 800;//$('.chartModal').height()-400;*/
       
        let reportChart = new FusionCharts( chartJson);
        reportChart.render();
         
       $(chartFullScreenModalWindow).modal('show');
    
       
      /*  $('#chartFullScreenModalWindow').on('shown.bs.modal', function () {
    $(this).find('.modal-dialog').css({width:'auto',
                               height:'auto', 
                              'max-height':'100%'});*/
});
    }

}