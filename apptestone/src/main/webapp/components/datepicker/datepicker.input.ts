/**
 * Created by pratik on 16/8/16.
 */
import {Component, ElementRef, Input, Output, EventEmitter, OnInit, ViewChild}      from    '@angular/core';
import {FORM_DIRECTIVES} from "@angular/forms";
import {Component, Provider, forwardRef, Provider, forwardRef, Input} from "@angular/core";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, FORM_DIRECTIVES} from "@angular/forms";
import {INPUT_MD_FORM} from "../style.constants";

const noop = () => {};

const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {
        useExisting: forwardRef(() => DatePicker),
        multi: true
    });

//TODO : Problem accessing the DatePicker's Value as assigned from other JS.
@Component({
    selector : 'date-picker',
    template : `      
           <div [class]="widgetStyleClassName">
             <div class="md-form">
                <input type="text" [(ngModel)]="value" [attr.id]="elementId" (blur)="onTouched()" class="form-control" #datepicker>
                <label [attr.for]="elementId">{{label}}</label>
             </div>
           </div>   

`,
    directives: [FORM_DIRECTIVES],
    providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR]
})

export class DatePicker implements OnInit,ControlValueAccessor{

    @Input()    label       :   string;

    @Input()      elementId   :   string;

    @Input()    regex       :   string;

    @Input()    column      : string;

    @Input()     widgetConfig    :   Object;

    widgetStyleClassName     : string;

    @ViewChild('datepicker')    datepicker : ElementRef;

    constructor(){}


    ngOnInit(){
        this.widgetStyleClassName = INPUT_MD_FORM+this.column;
        console.warn(this.widgetStyleClassName);
    }

    ngAfterViewInit(){
        try{
            // this.datepicker.pickadate();
        }
        catch (e){
            console.log('Date Widget : Picker js problem');
        }


    }

    setDate(){
        console.log('Date set is');
        console.log(this.datepicker.nativeElement.value);
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
        console.log('Calling set value');
        if (v !== this._value) {
            console.log('Field isnt empty');
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


    // Directive Input's


}

/**
 * Used to configure Widgets changable properties
 * Constructor sequence 1. disbaled 2.Hidden 3.required 4. readOnly 5. minLength 6. maxLength
 */
export class NumberInputConfiguration{
    disabled : boolean;
    hidden : boolean;
    required : boolean;
    readOnly : boolean;
    minLength : string;
    maxLength : string;
    errorMessage : string;

    constructor(disabled : boolean, required : boolean,readOnly : boolean,minLength : string,maxLength : string,errorMessage : string){
        this.disabled = disabled;
        this.required = required;
        this.readOnly = readOnly;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.errorMessage = errorMessage;
    }
}
