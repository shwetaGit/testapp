/**
 * Created by pratik on 16/8/16.
 */
import {Component, ElementRef, Input, Output, EventEmitter, OnInit, ViewChild}      from    '@angular/core';
import {FORM_DIRECTIVES} from "@angular/forms";
import {Component, Provider, forwardRef, Provider, forwardRef, Input} from "@angular/core";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, FORM_DIRECTIVES} from "@angular/forms";
import {BASIC_WIDGETSTYLE_CLASS} from "../style.constants";

const noop = () => {};

const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {
        useExisting: forwardRef(() => ToggleInput),
        multi: true
    });

@Component({
    selector : 'toggle-input',
    template : `              
              <div [class]="widgetStyleClassName" [ngClass]="{'hiddendiv': widgetConfig.hidden , '':!widgetConfig.hidden}">
                   <div class="switch">
                    <label>
                      Off
                      <input type="checkbox" [(ngModel)]="value" (blur)="onTouched()" [attr.disabled]="widgetConfig.disabled ? true : null" [checked]="widgetConfig.checked">
                      <span class="lever"></span>
                      On
                    </label>
                   </div>
              </div>
`,
    directives: [FORM_DIRECTIVES],
    providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR]
})

export class ToggleInput implements OnInit,ControlValueAccessor{

    @Input()    label       :   string;

    @Input()    regex       :   string;

    @Input()    column       : string;

    @Input()     widgetConfig    :   Object;

    widgetStyleClassName     : string;

    elementId   :   string;

    constructor(){}


    ngOnInit(){
        this.widgetStyleClassName = BASIC_WIDGETSTYLE_CLASS+this.column;
    }

    ngAfterViewInit(){

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


    // Directive Input's


}

export class ToggleInputConfiguration{
    hidden : boolean;
    disabled : boolean;
    checked : boolean;
    required : boolean;

    constructor(hidden : boolean,disabled : boolean,checked : boolean,required : boolean){
        this.hidden = hidden;
        this.disabled = disabled;
        this.checked = checked;
        this.required = required;

    }
}

