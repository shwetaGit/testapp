/**
 * Created by pratik on 26/5/16.
 */
import {Component, Input, Output ,Component , EventEmitter, OnInit}  from   '@angular/core';

@Component({
    selector : 'grid-pagination',
    template : `
               <nav>
                <ul class="pagination">
                            <li class="page-item"><a class="page-link waves-effect" (click)="navigateBack()"><span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span></a></li>
                            
                            <li class="page-item" [class.active]="page.isActive" *ngFor="let page of pages"><a class="page-link" (click)="selectPage(page,$event)">{{page.pageNumber}}</a></li>
                            
                            <li class="page-item"><a (click)="navigateForward()" class="page-link waves-effect"><span aria-hidden="true">&raquo;</span>
        <span class="sr-only">Next</span></a></li>
                 </ul>
              </nav>

`
})

export class PaginationDirective implements OnInit{

    @Input() pages : Page[];

    @Output()  onContentChange : EventEmitter<any> = new EventEmitter();


    firstPage : Page;                   // First Page

    currentPage : Page;                 // Current Page selection of user

    lastPage : Page;                    // Last page of Pages

    asyncFlag : boolean;

    constructor(){

    }

    ngOnInit(){
/*        console.log(this.pages);
        this.firstPage = this.pages[0];

        this.currentPage = this.pages[0];
        this.lastPage = this.pages[this.pages.length-1];*/
        this.asyncFlag = true;

    }

    /**
     * Selects the page, change the icon and emit to number of rows to change.
     * @param page
     * @param event
     */
    selectPage(page : Page,event : any){
        this.currentPage = page;
        let nextPageNumber = page.pageNumber;
        let loadPage = this.pages[nextPageNumber-1];
        this.setActiveIcon(loadPage);       // Change Circle Icon
        this.onContentChange.emit(loadPage);
    }

    /**
     *  Take the page object and sets its style class to active and all others to non active.
     * @param loadPage : Page Object
     */
    setActiveIcon(loadPage : Page){
        // Set the current page active to true and others to false
        for(let i = 0; i < this.pages.length ; i++){

            //If match found to loadPage- set isActive true else set all to false
            if(this.pages[i].pageNumber == loadPage.pageNumber){
                this.pages[i].isActive = true;
            }
            else
                this.pages[i].isActive = false;
        }
    }

    /**
     * Method to navigate using the chvron Right Icon
     */
    navigateForward(){
        //Async problem
        console.log('Navigate Forward');
        console.log(this.pages);
        this.hasCallFinished();

        // Check if current is last ? No action : Move Forward
        console.log(this.currentPage)
        console.log(this.lastPage);
       if(this.currentPage.pageNumber != this.lastPage.pageNumber){
           console.log('Forward')
           console.log(this.pages[this.currentPage.pageNumber]);
           this.selectPage(this.pages[this.currentPage.pageNumber],null);
       }

    }

    /**
     * method to navigate using the chevron Left icon
     */
    navigateBack(){
        this.hasCallFinished();
        //Check if current is first ? : No Action : Move Backward
        if(this.currentPage.pageNumber != this.firstPage.pageNumber){
            let tempIndex = this.currentPage.pageNumber -1;
            this.currentPage = this.pages[tempIndex-1];
            this.setActiveIcon(this.currentPage);
            console.log('backward');
            console.log(this.currentPage);
            this.onContentChange.emit(this.currentPage);
        }
    }

    hasCallFinished(){
       if(this.asyncFlag){
           this.firstPage = this.pages[0];

           this.currentPage = this.pages[0];
           this.lastPage = this.pages[this.pages.length-1];
       }
        this.asyncFlag = false;
    }

}

/**
 * Class representing the Pagination
 */
export class Page{
    public isActive : boolean;
    public pageNumber : number;
    public rowsToDisplay : number;
    public previousRowCount : number;

    constructor(isActive : boolean, pageNumber : number,rowsToDisplay : number,previousRowCount : number){
        this.isActive = isActive;
        this.pageNumber = pageNumber;
        this.rowsToDisplay = rowsToDisplay;
        this.previousRowCount = previousRowCount;
    }
}
