import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

@Component({
    selector: 'report-individual-chart-component',
    template:`
    <div class="col-lg-6">
<div class="card">
    
    <div class="card-block">
    
          <!--  data-toggle="modal" data-target="#chartFullScreenModal" -->
       <!-- <h4 class="card-title"><b>{{chartJson.chartTitle}}</b></h4>
           --> <span style="float: right;"><a class ="open-AddBookDialog" idNo = "0929" (click)="onFullScreenClick()"><i class="fa fa-arrows"></i></a></span>

    <div class="view overlay hm-white-slight">
       <div  class="card" id="chartContainer{{chartId}}" class="wow fadeInUp"></div>   
        <a>
            <div class="mask"></div>
        </a>
    </div>
     </div>
    
</div>

</div>

`,
    directives:[]
})
export class ReportIndividualChartComponent implements OnInit {

    @Input() chartJson: any;
   // @Input() reportComponent:Object;
   // @Input() chartComponent:Object;
    @Output()  fullScreenEmitter :any = new EventEmitter(<any>);
    chartId : any;


    constructor() {

    }

    ngOnInit(){
        this.chartId = Math.random();
        new WOW().init();
    }


    ngAfterViewInit(){

        if(this.chartJson){

            this.chartJson["renderAt"] = "chartContainer"+this.chartId;
            let reportChart = new FusionCharts( this.chartJson);
            reportChart.setChartAttribute("width","100%");
            reportChart.setChartAttribute("height","100%");
            reportChart.render();
        }

    }

    onFullScreenClick(){
    
        this.fullScreenEmitter.emit(this.chartJson);
        //this.reportComponent.reportChartFullScreenComponent.openChartInFullscreen(this.chartJson);
    //this.chartComponent.openChartInFullscreen(this.chartJson);
    }



}