import {Component, Input, OnInit, AfterViewInit}      from    '@angular/core';
import {FORM_DIRECTIVES} from "@angular/forms";
import {Component, Provider, forwardRef, Provider, forwardRef, Input} from "@angular/core";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, FORM_DIRECTIVES} from "@angular/forms";
import {BASIC_WIDGETSTYLE_CLASS, TEXT_INPUT_PREFIX} from "../style.constants";

const noop = () => {};

const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {
        useExisting: forwardRef(() => TextInput),
        multi: true
    });

@Component({
    selector : 'text-input',
    template : `              
              <div [class]="widgetStyleClassName" [ngClass]="{'hiddendiv': widgetConfig.hidden , '':!widgetConfig.hidden}">
                <div class="md-form form-group">
                    <input type="text"  [(ngModel)]="value" class="form-control validate" (blur)="onTouched($event)"  [attr.id]="elementId" [attr.disabled]="widgetConfig.disabled ? true : null" autocomplete="off" [attr.maxlength]="widgetConfig.maxLength" [attr.minlength]="widgetConfig.minLength" [required]="widgetConfig.required ? true: null" [readonly]="widgetConfig.readOnly ? true : false" [pattern]="regex" (change)="validateInput($event)">
                    <label [ngClass]="{'active': value , '': value}" [attr.for]="elementId" >{{label}}</label>
                </div>
              </div>
`,
    directives: [FORM_DIRECTIVES],
    providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR]
})

export class TextInput implements OnInit,AfterViewInit,ControlValueAccessor{

    @Input()    label       :   string;

    @Input()    regex       :   string;

    @Input()    column       : string;

    @Input()    widgetConfig    :   TextInputConfiguration;

    widgetStyleClassName     : string;

    elementId   :   string;

    constructor(){}


    ngOnInit(){
        this.widgetStyleClassName = BASIC_WIDGETSTYLE_CLASS+this.column;
        this.elementId = TEXT_INPUT_PREFIX+Math.floor(Math.random()*90000) + 10000;
        this.widgetConfig.elementId = this.elementId;
    }

    /**
     * Will remove attributes of pattern matching if null
     */
    ngAfterViewInit(){
        if(this.regex == null || this.regex == ''){
            document.getElementById(this.elementId).removeAttribute("pattern");
        }
        if (this.widgetConfig.minLength == '' || this.widgetConfig.minLength == null){
            document.getElementById(this.elementId).removeAttribute("minlength");
        }
        if(this.widgetConfig.maxLength == '' || this.widgetConfig.maxLength == null){
            document.getElementById(this.elementId).removeAttribute("maxlength");
        }

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
            toastr.error(this.widgetConfig.errorMessage);
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
    onTouched(event : Event){
    	this.validateInput(event);
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
export class TextInputConfiguration{
    hidden : boolean;
    disabled : boolean;
    readOnly : boolean;
    required : boolean;
    minLength : string;
    maxLength : string;
    errorMessage : string;
    validState : boolean;
    elementId  : string;

    constructor(hidden :boolean, disabled : boolean, readOnly : boolean,required : boolean,minLength : string,maxLength : string,errorMessage : string){
        this.hidden = hidden;
        this.disabled = disabled;
        this.readOnly = readOnly;
        this.required = required;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.errorMessage = errorMessage;
    }
}

/**
 * Usage
 * <text-input [label]="'Address 1'" [elementId]="'address1'"[column]="'6'" [widgetConfig]="address1Config"  [(ngModel)]="address.address1" name="address1"></text-input>
 * 
 */
