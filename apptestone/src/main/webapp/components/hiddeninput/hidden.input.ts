import {Component, Provider, forwardRef, Provider, forwardRef, Input, OnInit, AfterViewInit} from "@angular/core";
import {FORM_DIRECTIVES} from "@angular/forms";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, FORM_DIRECTIVES} from "@angular/forms";
import {BASIC_WIDGETSTYLE_CLASS, HIDDEN_INPUT_PREFIX} from "../style.constants";

const noop = () => {};

const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {
        useExisting: forwardRef(() => HiddenInput),
        multi: true
    });

@Component({
    selector : 'hidden-input',
    template : `              
                 <input type="hidden"  [(ngModel)]="value" class="form-control validate" (blur)="onTouched()"  [attr.id]="elementId" autocomplete="off">
`,
    directives: [FORM_DIRECTIVES],
    providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR]
})

export class HiddenInput implements OnInit,AfterViewInit,ControlValueAccessor{

    @Input()    column       : string;

    @Input()    hidden       : boolean;

    widgetStyleClassName     : string;

    elementId   :   string;

    constructor(){}


    ngOnInit(){
        this.widgetStyleClassName = BASIC_WIDGETSTYLE_CLASS+this.column;
        this.elementId = HIDDEN_INPUT_PREFIX+Math.floor(Math.random()*90000) + 10000;
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

