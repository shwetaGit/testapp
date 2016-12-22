/**
 * Created by pratik on 27/5/16.
 */
import {Component , ElementRef, Input, Output, EventEmitter ,OnInit ,ViewChildren, QueryList}      from    '@angular/core';
import {GridService} from "../../services/table/grid.service";
import {HTTP_PROVIDERS} from '@angular/http';
import {PaginationDirective, Page} from "../paginator.component";

@Component({
    selector : 'standard-grid',
    template : `
                    <table class="table table-hover">
                          <thead>
                            <tr>
<!--&lt;!&ndash; TODO : Make Column directive&ndash;&gt;    <th (click)="changeSortStyle(tableHeaderItem.dataIndex,headers,icon,i)" [hidden]="tableHeaderItem.hidden" #headers *ngFor="let tableHeaderItem of gridHeaderData; let i = index" [attr.id]="tableHeaderItem.dataIndex">{{tableHeaderItem.text}}&nbsp;&nbsp;<i [class]="sortStyle" [attr.id]="tableHeaderItem.dataIndex" #icon></i></th>                             -->
                                <th (click)="changeSortStyle()" [hidden]="tableHeaderItem.hidden" *ngFor="let tableHeaderItem of gridHeaderData">{{tableHeaderItem.text}}</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr *ngFor="let tableDataItem of gridContentData">
                              <td *ngFor="let tableHeaderItem of gridHeaderData" [style.display]="tableHeaderItem.hidden ? 'none' : 'table-cell'" (click)="cellClick(tableDataItem[tableHeaderItem.dataIndex])">{{tableDataItem[tableHeaderItem.dataIndex]}}</td>
                            </tr>
                          </tbody>                    
                    </table>
                          <center>
                          <grid-pagination [pages]="gridPages" (onContentChange)="loadRows($event)"></grid-pagination>
                          </center>
`,
    providers : [HTTP_PROVIDERS,GridService],
    directives : [PaginationDirective]
})


export class StandardGridComponent implements OnInit{

    @Input('column')    column : string;

    @Input('dataurl')   serviceUrl : string;

    @Input('datalist')  datalist : any;

    @Input('sortable')  sortable : boolean;         // TODO : Make the sortable from service instead of directive.

    @Input('columnItem') gridHeaderData : any[];

    @ViewChildren('icon') private header: QueryList<ElementRef>;

    data : any;

    // gridHeaderData : any[];

    gridContentData : any[];

    bufferDumpData : any[];

    sortStyle : string;                         // Sort Style icon

    maxRowsDefault : number;

    paginationLinks : any[];

    gridPages : Page[];

    constructor(private gridService : GridService){

    }

    check(){
        console.log('check');
    }
    ngOnInit(){
        // Create Service Fetch Data from service and populate table.
        this.maxRowsDefault = 5;           // Default change if user sets!=null TODO : To set from JSON Or Directive Input
        this.gridPages = [];
        this.gridService.getTableDataService(this,this.serviceUrl);
    }

    /**
     * Change the Table Row Content based on Pagination Click Event
     * @param page : Page Object that need to be loaded now.
     */
    loadRows(page : Page){
        console.log(page);
        let previousCount = page.previousRowCount;
        let newCount      = page.rowsToDisplay;
        this.gridContentData = [];              // Empty the View Rows Data

        for(let i = previousCount ; i < newCount ; i++){
            this.gridContentData.push(this.bufferDumpData[i]);
        } // yaay!
    }

    /**
     * Use the total data dump to create the paginator and emit data from loadRows to
     * change the row content.
     * @param dataDump
     */
    chopData(dataDump : any[]){

        let totalRowSize = dataDump.length;
        console.log(totalRowSize/this.maxRowsDefault);

        let result = totalRowSize/this.maxRowsDefault;

        /**
         * Rounding Off
         */
        if(result % 1 !=0){
            // Results in Decimal No need to round off
            console.log('result if '+Math.round(result));
            result = Math.round(result);
        }
        else{
            //Number is whole.
        }

        let lastRowCount = this.maxRowsDefault;
        let newRowCount = this.maxRowsDefault + this.maxRowsDefault;
        for(let i = 2;i <= result; i++){
            this.gridPages.push(new Page(false,i,newRowCount,lastRowCount));
            lastRowCount = newRowCount;
            newRowCount = newRowCount + this.maxRowsDefault;
        }
    }

    changeSortStyle(dataIndex : string,headers : ElementRef,icon : ElementRef,index : any,format : string){

        console.log(dataIndex);

        // console.log(headers);

        // console.log(index);

        console.log(icon);
        // console.log(this.header.toArray()[index].nativeElement.id);
        console.log(this.sortable);
        if(this.sortable){
            switch(this.sortStyle){
                case  'fa fa-sort-asc' :
                    this.sortStyle = 'fa fa-sort-desc';
                    // icon.set = 'fa fa-sort-desc';
                    this.descendingSort(dataIndex);
                    // TODO : Method for sorting() : desc
                    break;

                case 'fa fa-sort-desc' :
                    this.sortStyle = 'fa fa-sort-asc';
                    // icon.class = 'fa fa-sort-desc';
                    // TODO : Method for sorting() : asc
                    this.ascendingSort(dataIndex);
                    break;

                default :
                    this.sortStyle = 'fa fa-sort-asc';
                    // icon.class = 'fa -fa-sort-asc';
                    // TODO : Method for sorting() : asc
                    this.ascendingSort(dataIndex)
                    break;
            }
        }
    }

    ascendingSort(key : string){
        // console.log(this.gridContentData);
        let localDataCopy = this.gridContentData;
        let localSortedCopy = [];
        localSortedCopy = localDataCopy.sort((a,b)=> a[key].localeCompare(b[key]);



    }

    descendingSort(key : string){
        let localDataCopy = this.gridContentData;
        let localSortedCopy = [];
        localSortedCopy = this.gridContentData.sort((a,b)=> b[key].localeCompare(a[key]);
    }

    cellClick(cellData : any){
        console.log(cellData);
        alert('Clicked on Cell Value : '+cellData);
    }

}

