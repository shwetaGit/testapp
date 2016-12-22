/**
 * Created by pratik on 16/9/16.
 */
import {Component, Input, OnInit, AfterViewInit}      from    '@angular/core';
import {FORM_DIRECTIVES} from "@angular/forms";
import {Component, Provider, forwardRef, Provider, forwardRef, Input} from "@angular/core";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, FORM_DIRECTIVES} from "@angular/forms";
import {BASIC_WIDGETSTYLE_CLASS} from "../style.constants";

const noop = () => {};

const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {
        useExisting: forwardRef(() => DateTimeInput),
        multi: true
    });

@Component({
    selector : 'date-time-input',
    template : `              
              <div [class]="widgetStyleClassName" [ngClass]="{'hiddendiv': widgetConfig.hidden , '':!widgetConfig.hidden}">
                <div class="md-form form-group">
                    <input type="datetime-local"  [(ngModel)]="value" class="form-control validate" (blur)="onTouched()"  [attr.id]="elementId" [attr.disabled]="widgetConfig.disabled ? true : null" autocomplete="off"  [required]="widgetConfig.required ? true: null" [readonly]="widgetConfig.readOnly ? true : false" (change)="validateInput($event)">
                    <label class="active" [attr.for]="elementId" >{{label}}</label>
                </div>
              </div>
`,
    directives: [FORM_DIRECTIVES],
    providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR]
})

export class DateTimeInput implements OnInit,AfterViewInit,ControlValueAccessor{

    @Input()    label       :   string;

    @Input()    elementId   :   string;

    @Input()    column       : string;

    @Input()    widgetConfig    :   DateTimeInputConfiguration;

    widgetStyleClassName     : string;

    constructor(){}


    ngOnInit(){
        this.widgetStyleClassName = BASIC_WIDGETSTYLE_CLASS+this.column;
    }

    /**
     * Will remove attributes of pattern matching if null
     */
    ngAfterViewInit(){

    }

    /**
     * Checks the validity of the input field
     * @param event
     */
    validateInput(event : Event){
        if(document.getElementById(this.elementId).validity.valid){
            this.widgetConfig.validState = true;
        }
        else{
            // toastr.warning(this.widgetConfig.errorMessage);
            this.widgetConfig.validState = false;
        }
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
    onTouched(){
        this._onTouchedCallback();
    }

    //From ControlValueAccessor interface
    writeValue(value: any) {
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
 */
export class DateTimeInputConfiguration{
    hidden : boolean;
    disabled : boolean;
    readOnly : boolean;
    required : boolean;
    errorMessage : string;
    validState : boolean;

    constructor(hidden :boolean, disabled : boolean, readOnly : boolean,required : boolean,errorMessage : string){
        this.hidden = hidden;
        this.disabled = disabled;
        this.readOnly = readOnly;
        this.required = required;
        this.errorMessage = errorMessage;
    }
}


/**
 *   Usage , Date is represented in yyyy/mm/dd, can be binded to a String. i.e Modal Class Will have type string
 *               <date-time-input [label]="'Pick'" [elementId]="'mydatepicker'" [column]="'6'" [widgetConfig]="dateTimeConfig"   [(ngModel)]="userdate" name="userdate"></date-time-input>
 **/

