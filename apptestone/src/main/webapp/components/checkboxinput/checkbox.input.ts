import {Component, Input, OnInit, AfterViewInit}      from    '@angular/core';
import {FORM_DIRECTIVES} from "@angular/forms";
import {Component, Provider, forwardRef, Provider, forwardRef, Input} from "@angular/core";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, FORM_DIRECTIVES} from "@angular/forms";
import {BASIC_WIDGETSTYLE_CLASS} from "../style.constants";

const noop = () => {};

const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {
        useExisting: forwardRef(() => CheckBoxInput),
        multi: true
    });

@Component({
    selector : 'checkbox-input',
    template : `              
              <div [class]="widgetStyleClassName" [ngClass]="{'hiddendiv': widgetConfig.hidden , '':!widgetConfig.hidden}">
                <div class="md-form form-group">
                    <input type="checkbox"  [(ngModel)]="value" class="form-control validate" (blur)="onTouched($event)" class="filled-in"  [attr.id]="elementId" [attr.disabled]="widgetConfig.disabled ? true : null" autocomplete="off" [required]="widgetConfig.required ? true: null" [readonly]="widgetConfig.readOnly ? true : false" (change)="validateInput($event)" [checked]="widgetConfig.checked">
                    <label [attr.for]="elementId" >{{label}}</label>
                </div>
              </div>
`,
    directives: [FORM_DIRECTIVES],
    providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR]
})

export class CheckBoxInput implements OnInit,AfterViewInit,ControlValueAccessor{

    @Input()    label       :   string;

    @Input()    column      :  string;

    @Input()    widgetConfig    :   CheckboxInputConfiguration;

    widgetStyleClassName     : string;

    elementId   :   string;

    constructor(){}


    ngOnInit(){
        this.widgetStyleClassName = BASIC_WIDGETSTYLE_CLASS+this.column;
        this.elementId =  Math.floor(Math.random()*90000) + 10000;
        this.widgetConfig.elementId = this.elementId;
    }

    /**
     * Will remove attributes of pattern matching if null
     */
    ngAfterViewInit(){
        //
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


export class CheckboxInputConfiguration{
    hidden : boolean;
    disabled : boolean;
    required : boolean;
    checked : boolean;
    errorMessage : string;
    validState : boolean;
    elementId  : string;

    constructor(hidden :boolean, disabled : boolean,required : boolean,checked : boolean,errorMessage : string){
        this.errorMessage = errorMessage;
        this.hidden = hidden;
        this.disabled = disabled;
        this.required = required;
        this.checked = checked;

    }
}
