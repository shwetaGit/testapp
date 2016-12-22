import {Component, OnInit, Input} from '@angular/core';
import {ReportDataGridService} from "./datagrid.service";
import {HTTP_PROVIDERS} from "@angular/http";
import {Router} from "@angular/router";

@Component({
    selector: 'report-datagrid-component',

    template:`

<div>
<header>
<nav style="float: right;overflow:hidden;">

    <ul class="pagination">
    
    <li class="page-item disabled"><a class="page-link"> {{pagingToolbarStatus()}} </a></li>
 
      <!--Arrow left-->
        <li class="page-item {{isFirstPage ()==true?'disabled':''}}">
            <a class="page-link" aria-label="First" disabled (click) = "moveToFirst()">
                
                <i class="fa fa-fast-backward" aria-hidden="true"></i>
            </a>
        </li>
        
        <!--Arrow left-->
        <li class="page-item {{isFirstPage ()==true?'disabled':''}}">
            <a class="page-link" aria-label="Previous" (click) = "movePrev()">
                <i class="fa fa-backward" aria-hidden="true"></i>
            </a>
        </li>
        <li class="page-item disabled"><a class="page-link"> <b>{{pageNoStatus()}} </b></a></li>
 
        
        <!--Arrow right-->
        <li class="page-item {{isLastPage ()==true?'disabled':''}}">
            <a class="page-link" aria-label="Next" (click) = "moveNext()">
                  <i class="fa fa-forward" aria-hidden="true"></i>
            </a>
        </li>
        
          <!--Arrow right-->
        <li class="page-item {{isLastPage ()==true?'disabled':''}}">
            <a class="page-link" aria-label="Last" (click) = "moveToLast()">
                 <i class="fa fa-fast-forward" aria-hidden="true"></i>
            </a>
        </li>
         <li class="page-item {{pagingData.total==0?'disabled':''}}">
            <a class="page-link" aria-disabled="true" aria-label="Refresh" (click) = "refresh()">
                <i class="fa fa-refresh" aria-hidden="true"></i>
            </a>
        </li>
    </ul>
</nav>
</header>
   <table id="report-grid-table" class="col-md-12 table table-hover table-responsive" style="overflow:auto;">
               <thead  style="position:relative;background:white;">
                    <tr >
                        <th>#</th>
                        <th *ngFor="let item of gridColumns" class="{{item.hidden?'col-hidden':''}}" style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">{{item.header}}</th>
                    </tr>
               </thead>
               <tbody style="overflow:auto;">
              <tr *ngFor="let record of gridData; let idx = index;">
                             
                            <th scope="row">{{pagingData.start+idx}}</th>
                              <td *ngFor="let col of gridColumns" class="{{col.hidden?'col-hidden':''}} {{highLightColumn(record,col)}} {{highLightRow(record)}} " style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                             
                               <div [ngSwitch]="col.hasDrillDown == true">
                              
                               <div *ngSwitchCase="true"> 
                               
                               <a style="color:blue;" (click)="onDrillDownClick(record,col)">
                                    <u>{{record[col.dataIndex]}}</u></a>
                               
                               </div>
                              
                               <div *ngSwitchDefault>
                                        {{record[col.dataIndex]}}
                               </div>
                              
                              </div>
                              </td>
               </tr>
                   </tbody>
        </table>  
  
  </div> `,

    styleUrls:['app/../components/report/report.css'],
    providers:[ReportDataGridService,HTTP_PROVIDERS]


})
export class ReportDataGridComponent implements OnInit {

    @Input() gridColumns :any = [];
    @Input() rowHighLight : any = [];
    @Input() reportId : string;
    gridData : any = [];

    pagingData : any = {
        currentPage : 1,
        totalPages :0,
        start:0,
        limit:20,
        total:0
    }

    constructor(private reportDataGridService : ReportDataGridService,private router : Router) {
    }

    ngOnInit() {

    }
    ngAfterViewInit(){

    }

    loadcomponent(){
        this.pagingData.currentPage = 1;
        this.pagingData.start = 1;
        this.pagingData.total = 0;

        this.reportDataGridService.getGridData(this,this.reportId,this.pagingData);
    }

    refresh(){
        this.reportDataGridService.getGridData(this,this.reportId,this.pagingData);
    }
    pagingToolbarStatus(){
        if(this.pagingData.total == 0){
            return "No Records available.";
        }else{
            let fromRecord = this.pagingData.start;
            let toRecord = this.pagingData.total == 0 ? 0:this.pagingData.start+ this.pagingData.limit-1;
            if(toRecord>this.pagingData.total){
                toRecord = toRecord -(toRecord -this.pagingData.total);
            }

            return "Showing "+ fromRecord +" - " +toRecord+ " of total "+this.pagingData.total+" records.";

        }
    }
    pageNoStatus(){

        if(this.pagingData.total==0){
            return "";
        }else{
            let calPageNo = this.pagingData.total/this.pagingData.limit;
            let roundPageNo = Math.round(calPageNo);

           // let totalPages = (Number(calPageNo) === calPageNo && calPageNo % 1 !== 0 )?roundPageNo+1 : roundPageNo;
            let totalPages = (calPageNo-roundPageNo)>0 ? roundPageNo+1:roundPageNo;

            this.pagingData.totalPages = totalPages;

            return this.pagingData.currentPage + "/"+totalPages;
        }
    }
    moveToFirst(){
        this.pagingData.currentPage = 1;
        this.pagingData.start = 1;
        this.pagingData.total = 0;

        this.reportDataGridService.getGridData(this,this.reportId,this.pagingData);


    }
    moveToLast(){
        let secondFromLastPage = this.pagingData.totalPages-1;

        this.pagingData.currentPage = secondFromLastPage;
        this.pagingData.start =((secondFromLastPage)*this.pagingData.limit+1)-this.pagingData.limit;
        this.moveNext();
        this.pagingData.currentPage = secondFromLastPage+1;
    }

    moveNext(){
        if(!this.isLastPage()) {

            this.pagingData.currentPage = this.pagingData.currentPage + 1;
            this.pagingData.start = this.pagingData.start + this.pagingData.limit;

            this.reportDataGridService.getGridData(this, this.reportId, this.pagingData);
        }
    }

    movePrev(){
        if(!this.isFirstPage()) {

            this.pagingData.currentPage =  this.pagingData.currentPage-1;
            this.pagingData.start =  this.pagingData.start - this.pagingData.limit;

        }

        this.reportDataGridService.getGridData(this,this.reportId,this.pagingData);

    }

    isFirstPage (){

        return this.pagingData.start == 1;
    }
    isLastPage () {
        let toRecord = this.pagingData.total == 0 ? 0:this.pagingData.start+ this.pagingData.limit-1;
        if(toRecord>this.pagingData.total){
            toRecord = toRecord -(toRecord -this.pagingData.total);
        }
        return toRecord==this.pagingData.total;
    }

    highLightRow(record){

        let rowArray = this.rowHighLight;
        for (let i = 0; i < rowArray.length; i++) {

            let attrValue = record[rowArray[i].name];
            if(attrValue) {

                //If row highlight is applied on date field
                if (rowArray[i].dateFlag != undefined && rowArray[i].dateFlag == true) {
                    if (record.data.dateLongValue[rowArray[i].name] >= new Date(rowArray[i].from).getTime()
                        && record.data.dateLongValue[rowArray[i].name] <= new Date(rowArray[i].to).getTime()) {
                        return rowArray[i].styleCss;
                    }
                }
                //If row highlight is applied on string and integer fields
                else {
                    if (rowArray[i].parameterType == "range") {
                        if (rowArray[i].to != "") {
                            if (attrValue >= parseInt(rowArray[i].from) && attrValue <= parseInt(rowArray[i].to))
                                return rowArray[i].styleCss;
                        } else {
                            if (attrValue >= parseInt(rowArray[i].from))
                                return rowArray[i].styleCss;
                        }
                    } else if (rowArray[i].parameterType == "like") {
                        var resArr = attrValue.match(new RegExp(rowArray[i].like, "gi"));
                        if (resArr != null && resArr.length > 0) {
                            return rowArray[i].styleCss;
                        }
                    }
                    else {
                        if (attrValue.toString().toUpperCase() == rowArray[i].equalTo)
                            return rowArray[i].styleCss;
                    }
                }
            }

        }
    }
    highLightColumn(record:any,col:any){
        let tdCls = "";
        if(col.colHighlight) {
            let colArr = col.colHighlight;

            for (var i = 0; i < colArr.length; i++) {
                let value = record[colArr[i].name];
                if(value){

                if (colArr[i].parameterType == "range") {
                    if (colArr[i].to != "") {
                        if (value >= parseInt(colArr[i].from) && value <= parseInt(colArr[i].to))
                            tdCls = colArr[i].styleCss;
                    } else {
                        if (value >= parseInt(colArr[i].from))
                            tdCls = colArr[i].styleCss;
                    }
                } else if (colArr[i].parameterType == "like") {
                    var resArr = record[(colArr[i].name)].match(new RegExp(colArr[i].like, "gi"));
                    if (resArr != null && resArr.length > 0) {
                        tdCls = colArr[i].styleCss;
                    }
                }
                else {
                    if (value.toString().toUpperCase() == colArr[i].equalTo)
                        tdCls = colArr[i].styleCss;
                }
            }

        }
        }

        return tdCls;

    }
    onDrillDownClick(record,column){

        if(column.drillDownReports.length>0){
            
            let reportToDrill = column.drillDownReports[0];

            let queryCriteriaParams = {};
          
            queryCriteriaParams [reportToDrill.srcField]=record [reportToDrill.srcField];
            let fullRoute = '/mainpage/'+reportToDrill.reportRoute;
            this.router.navigate([fullRoute,{queryCriteriaParams:JSON.stringify(queryCriteriaParams)}]);

        }

    }

}