/**
 * Created by ketan on 7/9/16.
 */

import {
    Component, ElementRef, Input, Output, EventEmitter, OnInit, ViewChildren, QueryList,
    SimpleChange
}      from    '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';
import {INPUT_MD_FORM} from "../style.constants";
import {DataGridService} from "./datagrid.service"

@Component({
    selector: 'data-grid',
    template: `
                        <div [class]="widgetStyleClassName">
                        <table width="100%">
                            <tr>
                                <td>
                                     <b>{{title}}</b> 
                                </td>
                                <td width="25%" align="right" [hidden]="clientSidePaging ? false : true">
                                      <ul class="pagination pg-bluegrey">
                                        <li class="page-item">
                                          <a class="page-link"   aria-label="Previous" (click)="prev()">
                                            <i class="fa fa-backward" aria-hidden="true"></i>
                                          </a>
                                        </li>
                                        <li class="page-item">
                                          <a class="page-link"  aria-label="Next" (click)="next()">
                                            <i class="fa fa-forward" aria-hidden="true"></i>
                                          </a>
                                        </li>
                                        <li class="page-item">
                                          <a class="page-link"  aria-label="Next" (click)="reload()">
                                            <i class="fa fa-refresh" aria-hidden="true"></i>
                                          </a>
                                        </li>
                                        
                                      </ul>
                                </td>
                            </tr>
                        </table>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th *ngFor="let columns of widgetConfig.columns" [hidden]="columns.hidden">
                                            {{columns.text}}
                                        </th>
                                    </tr>
                                </thead>
                                 <tr *ngFor="let row of rows let rowIndex = index " (click)="rowClick(rowIndex)">
                                   <td *ngFor="let columns of widgetConfig.columns" [hidden] ="columns.hidden" >
                                            {{row[columns.dataIndex]}}
                                    </td>
                                </tr>
                            </table>
                        </div> 
    `,

    providers: [HTTP_PROVIDERS,DataGridService],
    directives: []
})


export class DataGrid implements OnInit {

    @Input() serviceurl : string;

    @Input() methodType : string;

    @Input() column : string;

    @Input() title : string;

    @Input() widgetConfig : Object;

    @Input() clientSidePaging : boolean;

    @Input() pageSize : number;

    @Input() localdata : any;

    @Output() rowData : any = new EventEmitter <>();

    //@Input() callReload : boolean;

    widgetStyleClassName : string;

    pageNumber : number;

    maxPage : number;

    rows : any[];

    response : any;

    constructor(private dataGridService : DataGridService){

    }

    ngOnChanges(change : SimpleChange){
        let data = change['localdata'].currentValue;
        if(data && data.response){
            this.setData(change['localdata'].currentValue);
        }
    }


    ngOnInit(){
        this.widgetStyleClassName = INPUT_MD_FORM+this.column;
    }

    ngAfterViewInit(){
        this.loadData();
    }

    reload(){
        this.loadData();
    }

    loadData(){
        this.dataGridService.getData(this,"setData()",this.serviceurl, this.methodType);
    }

    setData(responseData: any){
        this.response =  responseData.response;
        this.pageNumber = 1;

        if(this.response.data.length > (1 * this.pageSize))
        {
            this.maxPage = Math.floor((this.response.data.length/this.pageSize));

            if((this.response.data.length%this.pageSize)>0)
            {
                this.maxPage ++;
            }
        }
        this.rows = this.response.data;
        this.renderData();
    }

    renderData(){
        if(this.maxPage > 1){
            var rowsTemp = this.response.data;
            var newRows = [];
            var startIndex = 1;
            var endIndex = this.pageSize;
            if(this.pageNumber>1){
                startIndex  = (this.pageNumber-1) * this.pageSize;
                endIndex = startIndex + this.pageSize;
            }

            while(startIndex<=endIndex){
                if(rowsTemp[startIndex]){
                    newRows.push(rowsTemp[startIndex]);
                }
                startIndex++;
            }

            this.rows = newRows;

        }else{
            this.rows = this.response.data;
        }

    }

    rowClick(rowIndex: number){
        this.rowData.emit(this.rows[rowIndex]);
    }

    next(){
        debugger;
        if(this.pageNumber<this.maxPage){
            this.pageNumber++;
        }
        this.renderData();
    }

    prev(){
        debugger;
        if(this.pageNumber>1){
            this.pageNumber--;
        }else{
            this.pageNumber = 1;
        }
        this.renderData();
    }
}

export class DataGridConfiguration {
    columns : any[] ;
    rowData : any;
    constructor(columns:any[]) {
        this.columns = columns;
        console.log(this.columns);
    }
}