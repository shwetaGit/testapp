import {Component,  Input, Output, EventEmitter, OnInit}      from    '@angular/core';
import {FORM_DIRECTIVES} from "@angular/forms";
import {Component, Provider, forwardRef, Provider, forwardRef, Input} from "@angular/core";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, FORM_DIRECTIVES} from "@angular/forms";
import {BASIC_WIDGETSTYLE_CLASS, INPUT_RANGE_SELECTOR, RANGE_INPUT_PREFIX} from "../style.constants";

const noop = () => {};

const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {
        useExisting: forwardRef(() => RangeInput),
        multi: true
    });

@Component({
    selector : 'range-input',
    template : `              
              <div [class]="widgetStyleClassName" [ngClass]="{'hiddendiv': widgetConfig.hidden , '':!widgetConfig.hidden}">
                    <label [attr.for]="elementId">{{label}}</label>
                    <input type="range"  [(ngModel)]="value" class="form-control validate" (blur)="onTouched($event)"  [attr.id]="elementId" [attr.min]="minRange" [attr.disabled]="widgetConfig.disabled ? true : null"  [attr.max]="maxRange" [attr.step]="rangeStep">
              </div>
`,
    directives: [FORM_DIRECTIVES],
    providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR]
})


export class RangeInput implements OnInit,ControlValueAccessor{

    @Input()    label       :   string;

    @Input()    minRange       :   string;

    @Input()    maxRange       :   string;      // TODO : from object or static ?

    @Input()    rangeStep       :   string;

    @Input()    column       : string;

    @Input()     widgetConfig    :   Object;

    widgetStyleClassName     : string;

    elementId   :   string;

    constructor(){}


    ngOnInit(){
        this.widgetStyleClassName = INPUT_RANGE_SELECTOR+this.column;
        this.elementId = RANGE_INPUT_PREFIX+Math.floor(Math.random()*90000) + 10000;
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


    // Directive Input's


}

/**
 * Used to configure Widgets changable properties
 *
 */
export class RangeInputConfiguration{
    hidden        : boolean;
    disabled      : boolean;
    minRange      : string;
    maxRange      : string;
    rangeStep     : string;

    constructor(hidden : boolean,disabled : boolean,minRange : string,maxRange : string,rangeStep : string){
        this.hidden = hidden;
        this.disabled = disabled;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.rangeStep = rangeStep;
    }
}
