/**
 * Created by pratik on 1/6/16.
 */
import {Component , ElementRef, Input, Output, EventEmitter ,OnInit ,ViewChildren, QueryList}      from    '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';
import {GridService} from "../../services/table/grid.service";
import {PaginationDirective, Page} from "../paginator.component";


@Component({
    selector : 'group-grid',
    template : `
                       
                        <div class="container">
                            <table class="table table-sm table-hover z-depth-1">
                                <thead class="thead-inverse">
                                <tr>
                                    <th *ngFor="let head of headerData" [hidden]="head.hide" (click)="sortData(head.dataIndex,groupedPaginatorData,$event)"><i [ngClass]="{'fa fa-sort': (checkSortIcon(head.dataIndex)==0),'fa fa-sort-asc': (checkSortIcon(head.dataIndex)==-1),'fa fa-sort-desc' : (checkSortIcon(head.dataIndex)==1)}"></i>{{head.text}}</th>
                                </tr>
                                </thead>
                                    <!--<template *ngFor="let item of groupedPaginatorData">-->
                                    <template ngFor let-item="$implicit" [ngForOf]="groupedPaginatorData" let-i="index">
                                           <tr data-toggle="collapse" [attr.data-target]="item.viewId" class="table-active clickable animated fadeIn">
                                            <td class="thead-default" (click)="expand(item)" colspan="4"><i class="fa fa-{{item.icon}}-square" aria-hidden="true" [ngClass]="update(item)">&nbsp;&nbsp;&nbsp;<b>{{item.headerText}}</b></i></td>
                                           </tr>
                        
                                             <tbody [attr.id]="item.groupId" class="collapse">
                                                <tr *ngFor="let data of item.groupedData">
                                                    <td>{{data.description}}</td>
                                                    <td *ngFor="let head of headerData" [hidden]="head.hidden">{{data[head.dataIndex]}}</td>   <!-- TODO adding their grouping dynamic  && ISSUE Desciption missing-->
                                                </tr>
                                                
                                                <tr>
                                                   <td><center><b>Summary({{item.groupedData.length}})</b></center></td>
                                                    <td *ngFor="let summary of item.groupSummary"><b>{{summary.resultSummary}}</b></td>
                                                </tr>
                                                
                                            </tbody>
                                    </template>
                                  </table>
                          <center>
                          <grid-pagination [pages]="groupPages" (onContentChange)="loadRows($event)"></grid-pagination>
                          </center>
                        </div>
                         
`,
    providers : [HTTP_PROVIDERS,GridService],
    directives : [PaginationDirective]
})


export class GroupGridComponent implements OnInit{

    @Input() url : string;

    @Input() data : any[];

    @Input() maxRows : number;

    sortedIcon : string;

    selectedHeaderText : string;

    headerData : any [];
    gridData : any [];
    summaryData : any[];

    groupByKey : string;
    groupByValue : string;
    isGroupValid : boolean = true;

    buffer : any;

    groupedData : any [];
    groupHeader : any [];

    groupedData : Group[] = [];  // Original Grouped Data
    groupedPaginatorData : Group[] = [];
    groupPages : Page[] = [];

    sortStyle : string;

    ngOnInit(){
        // Service Calls should go here.
        console.log(this.url);
        console.log(this.maxRows);
        this.gridService.getTableGroupedDataService(this,this.url);
    }

    constructor(private gridService : GridService){}

    test(){
        console.log(this.groupedData);
    }

    /**
     * Method that changes the sort icon
     */
    checkSortIcon(dataIndex : string) : number{
        if(this.sortedIcon == '' || this.sortedIcon == null){
            return 0;
        }

        if(this.sortedIcon == dataIndex){

        }
        else{
            return 0;
        }
    }

    /**
     * Change the Table Row Content based on Pagination Click Event
     * @param page : Page Object that need to be loaded now.
     */
    loadRows(page : Page){
        console.log(page);
        let previousCount = page.previousRowCount;
        let newCount      = page.rowsToDisplay;
        this.groupedPaginatorData = [];              // Empty the View Rows Data

        for(let i = previousCount ; i < newCount ; i++){
            this.groupedPaginatorData.push(this.groupedData[i]);
        }

        this.setDefaultGroupStyle();
    }

    setDefaultGroupStyle(){
        for(let i = 0; i < this.groupedPaginatorData.length; i++){
            let item = this.groupedPaginatorData[i];

            item.expandStatus = false;
            item.icon   = 'plus';
        }
    }


    /**
     * Method that groupes the grid raw data based on the key provided.
     * @param groupByKey
     */
    groupGridData(groupByKey : string){
        console.log('Grp by: ');
        let groupedCollection : any [] = [];
        let group : Group;
        for(let i = 0; i < this.gridData.length ; i++){
            if(!groupedCollection.includes(this.gridData[i][groupByKey])){
                groupedCollection.push(this.gridData[i][groupByKey]);           // unique group items
            }
            //

        }
        // console.log(groupedCollection);
        for(let i = 0 ; i < groupedCollection.length ; i++){
            let gObj :Group = new Group();
            let tmp : any[] = [];
            gObj.headerText = groupedCollection[i];
            gObj.viewId = '#gp'+i;
            gObj.groupId = 'gp'+i;
            console.log('---');
            for(let j = 0; j <this.gridData.length ; j++){
                if(this.gridData[j][groupByKey]==groupedCollection[i]){
                    tmp.push(this.gridData[j]);
                }
            }
            gObj.groupedData = tmp;
            this.groupedData.push(gObj);
            // console.log(this.groupedData);
            console.log(gObj.groupedData);
        }

        // console.log(groupedCollection);
        this.groupHeader = groupedCollection;

        if(groupedCollection.length <= 0){
            this.isGroupValid = false;
        }
    }

    /**
     * Takes param as the complete data, and then divides the
     * total grouped data based on the maximum rows and creates
     * pages for the Paginator.
     * @param groupDataDump
     */
    chopData(groupDataDump : any[]){
        let totalGroupCount : number = groupDataDump.length;
        let page : Page = new Page(true,1,this.maxRows,0);
        console.log('Total Groups : '+totalGroupCount+' And Max Rows '+this.maxRows);
        console.log(totalGroupCount/this.maxRows);

        let totalPages = totalGroupCount/this.maxRows;
        console.log('Total Pages : '+totalPages);
        /**
         * Rounding Off
         */
        if(totalPages % 1 !=0){
            // Results in Decimal No need to round off
            console.log('Rounded off result if '+Math.round(totalPages));
            totalPages = Math.round(totalPages);
        }
        else{
            //Number is whole.
            console.log('Whole Result is : '+totalPages);
        }

        let lastRowCount = this.maxRows;
        let newRowCount = this.maxRows + this.maxRows;
        for(let i = 2;i <= totalPages; i++){
            this.groupPages.push(new Page(false,i,newRowCount,lastRowCount));
            lastRowCount = newRowCount;
            newRowCount = newRowCount + this.maxRows;
        }

        console.log(this.groupPages);
    }

    /**
     * Sorting the grouped grid data. Sorts only the single group of data
     * at header click $event and changes the icon based on sort op.
     */
    sortData(sortIndex : any, dataToSort : Group[] , event : any){            //TODO : adding sort styling icon to the clicked table header
        // console.log('Sort '+sortIndex);
        // console.log(dataToSort);
        // console.log('Paginator');
        // console.log(this.groupedPaginatorData);
        // Traverse down the dataToSort -> Group Object using the sortIndex key and sort(dataToSort)
        switch(this.sortStyle){

            default :
                // console.log('Assign ascending sort as default sort to ' +sortIndex);
                this.sortStyle = 'asc';
                this.sortGroupedData(this.sortStyle,sortIndex,dataToSort);
                break;

            case  'asc' :
                // console.log('Do desc sort to '+sortIndex);
                this.sortStyle = 'desc';
                this.sortGroupedData(this.sortStyle,sortIndex,dataToSort);
                break;

            case 'desc' :
                // console.log('Do Asecing sort to '+sortIndex);
                this.sortStyle = 'asc';
                this.sortGroupedData(this.sortStyle,sortIndex,dataToSort);
                break;
        }
    }


    /**
     * Performs actual sorting on the grouped data.
     * @param dataToSort
     */
    sortGroupedData(sortOperation : string,sortKey,dataToSort : Group[]){

        switch(sortOperation){

            case 'asc' :            // Doing a ascending sort
                for(let i = 0; i < dataToSort.length ; i++){
                    let localCopytoSort = dataToSort[i].groupedData;
                    dataToSort[i].groupedData.sort((a,b) => a[sortKey].localeCompare(b[sortKey]);
                }
                break;

            case 'desc' :
                for(let i = 0; i < dataToSort.length ; i++){
                    let localCopytoSort = dataToSort[i].groupedData;
                    dataToSort[i].groupedData.sort((a,b) => b[sortKey].localeCompare(a[sortKey]);
                }
                break;

        }
    }

    /**
     * Icon switch
     */
    expand(groupItem : Group){
        groupItem.expandStatus = true;
        this.selectedHeaderText = groupItem.headerText;
        console.log('Click')
        console.log(groupItem.expandStatus);

        if(groupItem.expandStatus===true && groupItem.icon==='minus'){
            groupItem.expandStatus=false;

            console.log("After minus   =    "+groupItem.expandStatus);
        }
        if(groupItem.expandStatus===false){
            groupItem.icon='plus';
        }
    }

    update(groupItem : Group){

        console.log("called");
        console.log(groupItem.headerText);
        if(this.selectedHeaderText===groupItem.headerText && groupItem.expandStatus===true){

            groupItem.icon='minus';
            console.log("with in update =    "+groupItem.expandStatus);
            console.log("true");
        }


    }


}

/**
 * Model class for Grid Groupings.
 */
export class Group{
    public headerText : string;
    public groupedData : any[];
    public viewId : string;
    public groupId : string;
    public expandStatus : boolean;
    public icon : string = 'plus';
    public groupSummary : GroupSummary [];
}

export class GroupSummary{
    public dataIndex : string;
    public operation : string;
    public resultSummary : number;
}

