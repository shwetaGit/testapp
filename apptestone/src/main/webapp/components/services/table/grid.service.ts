/**
 * Created by pratik on 25/5/16.
 */
/**
 * Created by pratik on 16/5/16.
 */
import{Injectable}   from '@angular/core';
import { Http } from '@angular/http';
import {Page} from "../../grid/paginator.component";
import {Group, GroupSummary} from "../../grid/groupbygrid/group.grid.component";
/**
 * Service class that calls the service to get data of table
 */
@Injectable()
export class GridService{

    // DI for getting HTTP class instance
    constructor(private _http : Http){}

    /**
     * Calls the service and assigns the returned JSON data to caller.data
     * @param parentRef : Refrence of the directive class calling the service
     * @param serviceUrl : URL of the service to be called
     */
    getTableDataService(parentRef : any, serviceUrl : string){
        this._http.get(serviceUrl)
            .subscribe(
                res =>{
                    // On Successfully receiving data
                    parentRef.data = res.json();
                    parentRef.gridHeaderData = parentRef.data.column;
                    // parentRef.gridContentData = parentRef.data.data;
                    parentRef.bufferDumpData = parentRef.data.data;
                    // console.log(parentRef.bufferDumpData.length);

                    parentRef.gridContentData = [];
                    for(let i = 0 ; i < parentRef.maxRowsDefault; i++){
                        parentRef.gridContentData.push(parentRef.bufferDumpData[i]);
                    }

                    parentRef.gridPages.push(new Page(true,1,parentRef.maxRowsDefault,0));

                    parentRef.chopData(parentRef.bufferDumpData);
                    // Need to chop rest of the data.

                },
                err => {
                    console.error('Error Occured on Service. Classname : '+this.constructor.name);
                },
                () => {
                    //on complete?
                }
            );
    }

    /**
     * Service Method that returns the data fetched from the service, Groupes them based on the 3 fields
     * i.e groupBykey && groupByValue && groupByLabel
     * and then Chops the data based
     * on the default maxRows count to display for paginator.
     * @param parentRef
     * @param serviceUrl
     */
    getTableGroupedDataService(parentRef : any,serviceUrl : string){

        console.log(serviceUrl);

        this._http.get(serviceUrl)
            .subscribe(
                res =>{
                    parentRef.buffer = res.json();
                    parentRef.headerData = parentRef.buffer.column;
                    parentRef.gridData   = parentRef.buffer.data;
                    parentRef.groupByKey = parentRef.buffer.groupbyKey;

                    parentRef.summaryData = parentRef.buffer.summary;
                    // console.log(parentRef.summaryData);

                    this.groupGridData(parentRef.groupByKey,parentRef); // Group the data based on the 3 keys. TODO : to add the groupLabel to the template from service.

                },
                err => {
                    console.log('Error Occured on Service. Classname : '+this.constructor.name);
                },
                ()=>{
                    // OnComplete
                }
            )
    }


    groupGridData(groupByKey : string,parentRef : any){
        // console.log('Grp by: ');
        let groupedCollection : any [] = [];
        let group : Group;
        for(let i = 0; i < parentRef.gridData.length ; i++){
            // console.log(this.gridData[i][groupByKey]);
            if(!groupedCollection.includes(parentRef.gridData[i][groupByKey])){
                groupedCollection.push(parentRef.gridData[i][groupByKey]);           // unique group items
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
            gObj.groupSummary = [];

            // gObj.groupSummary = grpSummary;

            // Form Groups of data for Each Group Object
            for(let j = 0; j < parentRef.gridData.length ; j++){
                // console.log(this.gridData[j][groupByKey]);
                // console.log(groupedCollection[i]);

                // console.log(this.gridData[j][groupByKey]==groupedCollection[i]);
                if(parentRef.gridData[j][groupByKey]==groupedCollection[i]){
                    tmp.push(parentRef.gridData[j]);
                }
            }
            gObj.groupedData = tmp;
            // Perform calcuation before pushing to groups
            // console.log('-----');
            // console.log(gObj.groupedData);

            for(let i = 0; i < parentRef.summaryData.length ; i++){
                let grpSummary = new GroupSummary();
                let result = 0;

                grpSummary.dataIndex = parentRef.summaryData[i].dataIndex;
                grpSummary.operation = parentRef.summaryData[i].operation;
                grpSummary.resultSummary = 0;

                let bufferArray = [];

                let bufferDateArray = [];

                // console.log('Index : '+grpSummary.dataIndex);
                // console.log('Opertaion '+grpSummary.operation);
                for(let j = 0 ; j < gObj.groupedData.length; j++){

                    bufferArray.push(gObj.groupedData[j][grpSummary.dataIndex]);

                    bufferDateArray.push(new Date(gObj.groupedData[j].dataIndex));

                    // TODO :  Add a switch case for operation
                    switch(grpSummary.operation){

                        case 'total' :
                            grpSummary.resultSummary += Number(gObj.groupedData[j][grpSummary.dataIndex]);
                            break;

                        case 'average' :
                            grpSummary.resultSummary += Number(gObj.groupedData[j][grpSummary.dataIndex]);
                            grpSummary.resultSummary = (grpSummary.resultSummary / gObj.groupedData.length);
                            // grpSummary.resultSummary = grpSummary.resultSummary.toFixed(3); TODO : Fix decimal places.
                            // console.log('should perform average op');
                            break;

                        case 'min' :
                            // console.log('should perform min');
                            grpSummary.resultSummary = Math.min(...bufferArray);
                            break;

                        case 'max' :
                            // console.log('should perform max');
                            grpSummary.resultSummary = Math.max(...bufferArray);
                            break;

                        case 'datemax' :
                            grpSummary.resultSummary = new Date(Math.max.apply(null,bufferDateArray));
                            break;

                        default :
                            // console.log('Do nothing');
                            break;
                    }



                }

                console.log(grpSummary);
                gObj.groupSummary.push(grpSummary);

            }

            parentRef.groupedData.push(gObj);
            // console.log(parentRef.groupedData);
            // console.log(gObj.groupedData);
            // console.log(gObj.groupedData);
        }

        console.log(parentRef.groupedData);
        parentRef.groupHeader = groupedCollection;

        //Add First Set of Paged Data
        for(let i = 0; i < parentRef.maxRows ; i++){
            parentRef.groupedPaginatorData.push(parentRef.groupedData[i]);
        }

        // console.log('First set : '+parentRef.groupedPaginatorData);

        if(groupedCollection.length <= 0){
            parentRef.isGroupValid = false;
        }

        parentRef.groupPages.push(new Page(true,1,parentRef.maxRows,0));             // Add The first Page of Paginator.
        parentRef.chopData(parentRef.groupedData);                                  // Chop groups into Pages for Pagination Control.
        // this.doGroupPagination(parentRef);  // Start pagination

    }

    /**
     * Performs Pagination Action for group grid. Calculated On Number of Groups and not
     * the group Page.
     * @param parentRef
     */
    doGroupPagination(parentRef : any){
        let totalCountGroup : number = parentRef.groupedData.length;
        // console.log(" Available Groups "+totalCountGroup);
        // console.log("Max Rows"+parentRef.maxRows);

        let firstPage : Page  = new Page(true,1,parentRef.maxRows,0);
        // console.log(firstPage);

        parentRef.chopData(totalCountGroup);
        //Need to divide the total : groupedData[] into chunks with ref to the input provided as maxRows to groupedPaginator Data

    }

}

