import {Component, OnInit, Input} from '@angular/core';
import {SelectInputConfiguration} from "../../selectinput/select.input";

@Component({
    selector: 'default-date-range',
    template: ` <select-input  [serviceurl]="''" 
  [methodType]= "'GET'" [column]="'6'" [label]= "'Default Range'" 
  [widgetConfig]="defaultDateRangeConfig" name="''" 
  (selectedData)="getDefaultDateRangeEvent($event)" 
  [localdata] ="defaultDateRangeData" 
  [(ngModel)]="model" ></select-input>
    `
})
export class DefaultDateRangeComponent implements OnInit {
    @Input() model:any;
    @Input() fromDateName:string;
    @Input() toDateName:string;
    @Input() dateRangeFlag:string;
    
    defaultDateRangeData :any= {"response":{"data":[{"displayField":"Today","valueField":"today","dateFlag":"TODAY"},{"displayField":"Yesterday","valueField":"yesterday","dateFlag":"YESTERDAY"},{"displayField":"Day B4 Yesterday","valueField":"dayB4yes","dateFlag":"DAY_B4_YESTERDAY"},{"displayField":"This Week","valueField":"thisweek","dateFlag":"THIS_WEEK"},{"displayField":"Last Week","valueField":"lastweek","dateFlag":"LAST_WEEK"},{"displayField":"This Month","valueField":"thismonth","dateFlag":"THIS_MONTH"},{"displayField":"Last Month","valueField":"lastmonth","dateFlag":"LAST_MONTH"},{"displayField":"Last Three Month","valueField":"last3month","dateFlag":"LAST_3_MONTH"},{"displayField":"Last Six Month","valueField":"last6month","dateFlag":"LAST_6_MONTH"},{"displayField":"This Year","valueField":"thisyear","dateFlag":"THIS_YEAR"},{"displayField":"Last Year","valueField":"lastyear","dateFlag":"LAST_YEAR"}]}};
    defaultDateRangeConfig : any;

    constructor() {
        this.defaultDateRangeConfig = new SelectInputConfiguration(false,false,false,'Please select default range','displayField','valueField');
    }

    ngOnInit() {

    }

    getDefaultDateRangeEvent(event:any) {

        let fromDateName = this.fromDateName;
        let toDateName = this.toDateName;

    if(event.data){
    this.model[this.dateRangeFlag] = event.data.dateFlag;
    }
    
        switch (event.value) {

            case "today":
                this.model[fromDateName] = this.getTodayFromDate();
                this.model[toDateName] = this.getTodayToDate();
                break;
                
            case "yesterday":
                this.model[fromDateName] = this.getYesterFromDate()
                this.model[toDateName] = this.getYesterToDate();
                break;
                
            case "dayB4yes":
                this.model[fromDateName] = this.getDB4YesterFromDate();
                this.model[toDateName] = this.getDB4YesterToDate();
                break;
            case "thisweek":
                this.model[fromDateName] = this.getStartDayOfWeek();
                this.model[toDateName] = this.getEndDayOfWeek();
                break;
                

            case "lastweek":
                this.model[fromDateName] = this.getStartDayOfLastWeek();
                this.model[toDateName] = this.getEndDayOfLastWeek();
                break;

            case "thismonth":
                this.model[fromDateName] = this.getStartDayofThisMonth();
                this.model[toDateName] = this.getEndDayofThisMonth();
                break;

            case "lastmonth":
                this.model[fromDateName] = this.getStartDayofLastMonth();
                this.model[toDateName] = this.getEndDayofLastMonth();
                break;

            case "last3month":
                this.model[fromDateName] = this.getStartDayofLast3Month();
                this.model[toDateName] = this.getEndDayofLast3Month();
                break;

            case "last6month":
                this.model[fromDateName] = this.getStartDayofLast6Month();
                this.model[toDateName] = this.getEndDayofLast6Month();
                break;

            case "thisyear":
                this.model[fromDateName] = this.getStartDayofThisYear();
                this.model[toDateName] = this.getEndDayofThisYear();
                break;

            case "lastyear":
                this.model[fromDateName] = this.getStartDayofLastYear();
                this.model[toDateName] = this.getEndDayofLastYear();
                break;

        }
       
    }
    setFromHHMM(date:any){
        date.setHours('00');
        date.setMinutes('00');
        date.setSeconds('00');
    }

    setToHHMM(date:any) {
        date.setHours('23');
        date.setMinutes('59');
        date.setSeconds('59');
    }
    getTodayFromDate(){
        let date= new Date();
        this.setFromHHMM(date);
        return date;
    }
    /** used to get Today Date*/
    getTodayFromDate(){
    var date= new Date();
    this.setFromHHMM(date);
        
    return date;
}
    /** used to get Today Date*/
    getTodayToDate(){
    var date= new Date();
    this.setToHHMM(date);
        
      return date;
}
    /** used to get yesterday Date */
    getYesterFromDate(){
    var date=new Date();
    date.setDate(date.getDate()-1);
    this.setFromHHMM(date);
        
    return date;
}
    /** used to get yesterday Date */
    getYesterToDate(){
    var date=new Date();
    date.setDate(date.getDate()-1);
    this.setToHHMM(date);
        
    return date;
}
    /** used to get yesterday Date*/
    getDB4YesterFromDate(){
    var date=new Date();
    date.setDate(date.getDate()-2);
    this.setFromHHMM(date);
        
    return date;
}
    /** used to get yesterday Date*/
    getDB4YesterToDate(){
    var date=new Date();
    date.setDate(date.getDate()-2);
    this.setToHHMM(date);
        
    return date;
}
    /** used to get Start date of current week*/
    getStartDayOfWeek(){
    var date=new Date();
    date.setDate(date.getDate() - date.getDay());
    this.setFromHHMM(date);
        
    return date;
}
    /** used to get End date of current week*/
    getEndDayOfWeek(){
    var date=new Date();
    date.setDate(date.getDate() + (6-date.getDay()));
    this.setToHHMM(date);
        
    return date;
}
    /** used to get Star date of Last week*/
    getStartDayOfLastWeek(){
    var date=new Date();
    //set Last Week Date
    date.setDate(date.getDate() - (date.getDay()+ 1));
    //set first day of last week
    date.setDate(date.getDate() - date.getDay())
    this.setFromHHMM(date);
        
    return date;
}
    /** used to get End date of current week */
    getEndDayOfLastWeek(){
    var date=new Date();
    //set Last Week Date
    date.setDate(date.getDate() - (date.getDay()+ 1));
    //set first day of last week
    date.setDate(date.getDate() + (6-date.getDay()));
    this.setToHHMM(date);
        
    return date;
}
    /** Used to get StartDay of current Month*/
    getStartDayofThisMonth(){
    var date=new Date(new Date().getFullYear(),new Date().getMonth(),1)
    this.setFromHHMM(date);
        
    return date;
}
    /** Used to get end Day of current Month*/
    getEndDayofThisMonth(){
    var date=new Date(new Date().getFullYear(),new Date().getMonth()+1,0);
    this.setToHHMM(date);
        
    return date;
}
    /** Used to get StartDay of Last Month */
    getStartDayofLastMonth(){
    var date=new Date(new Date().getFullYear(),new Date().getMonth()-1,1);
    this.setFromHHMM(date);
        
    return date;
}
    /** Used to get end Day of Last Month*/
    getEndDayofLastMonth(){
    var date=new Date(new Date().getFullYear(),new Date().getMonth(),0);
    this.setToHHMM(date);
        
    return date;
}
    /** Used to get StartDay of Last 3 Month*/
    getStartDayofLast3Month(){
    var today=new Date();
    var year=today.getFullYear();
    var month=today.getMonth();
    var date=today.getDate();
    var date = new Date(year,month-3,date);
    //var date=new Date(new Date().getFullYear(),new Date().getMonth()-3,1);
    this.setFromHHMM(date);
        
    return date;
}
    /** Used to get end Day of Last 3 Month*/
    getEndDayofLast3Month(){
    //var date=new Date(new Date().getFullYear(),new Date().getMonth(),0);
    var date=new Date();
    this.setToHHMM(date);
        
    return date;
}
    /** Used to get StartDay of Last 6 Month*/
    getStartDayofLast6Month(){
    var today=new Date();
    var year=today.getFullYear();
    var month=today.getMonth();
    var date=today.getDate();
    var date = new Date(year,month-6,date);
    //var date=new Date(new Date().getFullYear(),new Date().getMonth()-6,1);
    this.setFromHHMM(date);
        
    return date;
}
    /** Used to get end Day of Last 6 Month*/
    getEndDayofLast6Month(){
    //var date=new Date(new Date().getFullYear(),new Date().getMonth(),0);
    var date=new Date();
    this.setToHHMM(date);
        
    return date;
}
    /** Used to get StartDay of Last 6 Month*/
    getStartDayofThisYear(){
    var date=new Date(new Date().getFullYear(),0,1);
    this.setFromHHMM(date);
        
    return date;
}
    /** Used to get end Day of Last 6 Month*/
    getEndDayofThisYear(){
    var date=new Date(new Date().getFullYear(),12,0);
    this.setToHHMM(date);
        
    return date;
}
    /** Used to get StartDay of Last 6 Month*/
    getStartDayofLastYear(){
    var date=new Date(new Date().getFullYear()-1,0,1);
    this.setFromHHMM(date);
    return date;
}
    /** Used to get end Day of Last 6 Month*/
    getEndDayofLastYear(){
    var date=new Date(new Date().getFullYear()-1,12,0);
    this.setToHHMM(date);
        
    return date;
}

}
