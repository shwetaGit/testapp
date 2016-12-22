
import {
    Component, Input, OnInit, Output, EventEmitter, SimpleChange, forwardRef,
    Provider
}      from    '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';
import {BASIC_WIDGETSTYLE_CLASS,  SELECT_BASE_CLASS} from "../style.constants";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, FORM_DIRECTIVES} from "@angular/forms";
import {SelectInputService} from "./select.input.service";

const noop = () => {};

const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {
        useExisting: forwardRef(() => SelectInput),
        multi: true
    });



@Component({
    selector : 'select-input',
    template : `
                 <div [class]="widgetStyleClassName" [ngClass]="{'hiddendiv': widgetConfig.hidden , '':!widgetConfig.hidden}" >
                    <label  [ngClass]="{'active': value , '': value}" [attr.for]="elementId">{{label}}</label>
                   
                    <select  class="newcustomselect"  (change)="setSelectedValue($event)" [attr.id]="elementId" [(ngModel)]="value" (blur)="onTouched($event)" [attr.disabled]="widgetConfig.disabled ? true : null" [required]="widgetConfig.required ? true: null" >
                        <option value="" disabled selected>Choose your option</option>
                        <option  *ngFor="let row of rows let rowIndex = index " value = "{{row[widgetConfig.valueField]}}" (click)="setRowData(row)" > {{row[widgetConfig.displayField]}}</option>
                    </select>
                                       
                  </div>
`,
    directives: [FORM_DIRECTIVES],
    providers : [HTTP_PROVIDERS,SelectInputService, CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR]
})

export class SelectInput implements OnInit,ControlValueAccessor{

    @Input() label : string;

    @Input() column : string;

    @Input() widgetConfig : SelectInputConfiguration;

    @Output() selectedData : any = new EventEmitter <>();

    @Input() localdata : any;

    @Input() serviceurl : string;

    @Input() methodType : string;

    widgetStyleClassName : string;

    elementId : string;

    selectedRow : any;

    response : any;

    rows : any[];

    materialSelectClass: string;

    constructor(private selectFieldService : SelectInputService){

    }


    ngOnChanges(change : SimpleChange){
        let data = change['localdata'].currentValue;

        if(data && data.response){
            this.setData(change['localdata'].currentValue);
        }
    }


    ngOnInit(){
        this.widgetStyleClassName = BASIC_WIDGETSTYLE_CLASS+this.column;
        this.materialSelectClass = SELECT_BASE_CLASS;
        this.elementId = Math.floor(Math.random()*90000) + 10000;
        this.widgetConfig.elementId = this.elementId;
    }

    ngAfterViewInit(){
        if(this.serviceurl !=null)
            this.loadData();
    }

    loadData(){
        this.selectFieldService.getData(this,"setData",this.serviceurl,"get");
    }

    setData(data){
        this.response = data;
        this.rows = data.response.data;
        this.widgetConfig.rowData = data.response.data;

    }

    setRowData(row : any){
        this.selectedRow = row;
    }

    /**
     * Checks the validity of the input field
     * @param event
     */
    validateInput(event : Event){
        if(document.getElementById(this.elementId).validity.valid){
            this.widgetConfig.validState = true;
            let valueField = this.widgetConfig.valueField;
            this.rows.forEach( (row)=>{
                if(row[valueField] == event.target.value)
                    this.selectedRow = row;
            });
            this.selectedData.emit({value : event.target.value,data : this.selectedRow,rows : this.rows});
        }
        else{
            toastr.error(this.widgetConfig.errorMessage);
            this.widgetConfig.validState = false;
        }
    }

    setSelectedValue(event : any){
	    this.value = event.target.value;
	    this.validateInput(event);
    }

    // --- CUSTOM VALUE ACCESSOR CODE ---

    //The internal data model
    private _value: any = '';

    //Placeholders for the callbacks
    private _onTouchedCallback: (_:any) => void = noop;

    private _onChangeCallback: (_:any) => void = noop;

    //get accessor
    get value(): any { return this._value; };

    //set accessor including call the onchange callback
    set value(v: any) {
        if (v !== this._value) {
            this._value = v;
            this._onChangeCallback(v);
        }
    }

    //Set touched on blur
    onTouched(event : Event){
        this._onTouchedCallback();
    }

    //From ControlValueAccessor interface
    writeValue(value: any) {
        if(value!=null && this.rows!=null){
            let valueField = this.widgetConfig.valueField;
            this.rows.forEach( (row)=>{
                if(row[valueField] == value)
                    this.selectedRow = row;
            });
            this.selectedData.emit({value : value,data : this.selectedRow});
        }
        this._value = value;
    }

    //From ControlValueAccessor interface
    registerOnChange(fn: any) {
        this._onChangeCallback = fn;
    }

    //From ControlValueAccessor interface
    registerOnTouched(fn: any) {
        this._onTouchedCallback = fn;
    }


    // ---  END ---


}

/**
 * Used to configure Widgets changable properties
 * Constructor sequence 1. disbaled 2.Hidden 3.required 4. readOnly 5. minLength 6. maxLength 7.error message
 */

export class SelectInputConfiguration {

    hidden : boolean;
    disabled : boolean;
    required : boolean;
    errorMessage : string;
    displayField : string;
    valueField : string;
    validState : boolean;
    elementId  : string;
    rowData:any;
    constructor(hidden:boolean, disabled:boolean, required:boolean, errorMessage:string, displayField:string, valueField:string) {
        this.hidden = hidden;
        this.disabled = disabled;
        this.required = required;
        this.errorMessage = errorMessage;
        this.displayField = displayField;
        this.valueField = valueField;
        this.rowData = [];
    }

}


/**
 *

 <select-field [(ngModel)]="stateModel.countryId" [serviceurl]="'secure/Country/findAll'" [methodType]="'get'"
 name="stateModel.countryId" [column]="'4'" [label]="'Country'" [widgetConfig]="countryIdConfig" (selectedData)="selectCountryData($event)" [ddata] ="countryIdddata" ></select-field>

 <select-field [(ngModel)]="stateModel.stateIds"
 name="stateModel.stateIds" [column]="'4'" [label]="'States'" [widgetConfig]="stateIdsConfig" (selectedData)="selectStateData($event)" [ddata] ="stateIdddata" ></select-field>

 *
 */
