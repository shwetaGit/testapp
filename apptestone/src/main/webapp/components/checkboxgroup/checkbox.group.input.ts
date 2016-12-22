/**
 * Created by patrick on 15/9/16.
 */
import {Component, OnInit, Input, AfterViewInit, SimpleChanges, Output, EventEmitter} from '@angular/core';
import {BASIC_WIDGETSTYLE_CLASS} from "../style.constants";
import {CheckBoxGroupService} from "./checkbox.group.service";



@Component({
    selector: 'checkbox-group',
    template: `

    <div [class]="widgetStyleClassName">
     <h6>{{label}}</h6> 
           <span  *ngFor="let row of rows let rowIndex = index">
           <div [class]="widgetStyleClassName">
                <span>
                    <input type="checkbox" class="filled-in {{groupClassId}}" id="{{groupClassId}}-{{rowIndex}}{{row[widgetConfig.valueField]}}{{row[widgetConfig.displayField]}}" [attr.value] ="row[widgetConfig.valueField]" (change)="setSelectedValue(row)">
                    <label [attr.for]="groupClassId+'-'+rowIndex+''+row[widgetConfig.valueField]+row[widgetConfig.displayField]">{{row[widgetConfig.displayField]}}</label>
                </span>
           </div>
           </span>
     </div>

`,providers:[CheckBoxGroupService]
})

export class CheckBoxGroup implements OnInit,AfterViewInit {

    @Input()    column       : string;
    
    @Input()    label       :   string;

    @Input()    widgetConfig : CheckBoxGroupConfiguration;

    @Input()    methodType   : string;

    @Input()    serviceurl   : string;

    @Input()    localdata    : any;

    @Input()    preSelected  : any[];

    @Output()   selectedData : any = new EventEmitter<>();


    groupClassId             : string;

    widgetStyleClassName     : string;

    selectedCheckBoxes       : any[];



    response : any;

    rows : any[];

    constructor(private checkboxService : CheckBoxGroupService){
        this.groupClassId = Math.floor(Math.random()*90000) + 10000;
        this.selectedCheckBoxes = [];
    }

    ngOnInit() {
        this.widgetStyleClassName = BASIC_WIDGETSTYLE_CLASS+this.column;
    }

    ngOnChanges(change : SimpleChanges){
        let data = change['localdata'].currentValue;

        if(data && data.response){
            this.setData(change['localdata'].currentValue);
        }

        if(change['preSelected']){
            let defaultselected = change['preSelected'].currentValue;

            if(defaultselected != null)
                this.setDefaultSelected();
        }

    }

    ngAfterViewInit(){
        if(this.serviceurl !=null)
            this.loadData();
        if(this.preSelected != null)
            this.setDefaultSelected();
    }

    setData(responseData: any){
        this.response =  responseData.response;
        this.rows = this.response.data;
    }

    loadData(){
        this.checkboxService.getData(this,"setData",this.serviceurl,"get");
    }


    setSelectedValue(row : any) {
        // Need to maintain an array of pushed checks
        let checkboxes = document.getElementsByClassName('filled-in ' + this.groupClassId);
        this.selectedCheckBoxes = [];
        if (checkboxes != null) {
            for (let i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked)
                    this.selectedCheckBoxes.push({[this.widgetConfig.valueField] : checkboxes[i].value})
            }
        }
        this.selectedData.emit(this.selectedCheckBoxes);

    }


    setDefaultSelected(){
        console.log('Checked');
        let checkboxes = document.getElementsByClassName('filled-in ' + this.groupClassId);
        this.preSelected.forEach( (value)=>{
            for (let i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].value == value){
                    console.log(checkboxes[i]);
                    checkboxes[i].setAttribute('checked',true);
                }

            }
        })
    }




}

export class CheckBoxGroupConfiguration{
    displayField : string;
    valueField : string;
    hidden : boolean;
    disabled : boolean;
    required : boolean;
    errorMessage : string;

    constructor(displayField:string, valueField:string, hidden:boolean, disabled:boolean, required:boolean, errorMessage:string) {
        this.displayField = displayField;
        this.valueField = valueField;
        this.hidden = hidden;
        this.disabled = disabled;
        this.required = required;
        this.errorMessage = errorMessage;
    }
}

/**
 * Usage
 *  <checkbox-group [serviceurl]="'secure/Title/findAll'" [methodType]="'get'" [column]="'6'" [widgetConfig]="checkboxConfig" [localData]="checkBoxData" [preSelected]="checkBoxData"></checkbox-group>-->
 **/
