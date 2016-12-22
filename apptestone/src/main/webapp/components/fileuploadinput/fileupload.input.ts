/**
 * Created by pratik on 16/8/16.
 */
import {Component,  Input, Output, EventEmitter, OnInit}      from    '@angular/core';
import {FORM_DIRECTIVES} from "@angular/forms";
import {Component, Provider, forwardRef, Provider, forwardRef, Input} from "@angular/core";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, FORM_DIRECTIVES} from "@angular/forms";
import {BASIC_WIDGETSTYLE_CLASS, FILEUPLOAD_INPUT_PREFIX} from "../style.constants";

const noop = () => {};

const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {
        useExisting: forwardRef(() => FileUploadInput),
        multi: true
    });

@Component({
    selector : 'fileupload-input',
    template : `              
              <div [class]="widgetStyleClassName" [ngClass]="{'hiddendiv': widgetConfig.hidden , '':!widgetConfig.hidden}">
                <div class="file-field">
                    <div class="btn btn-primary">
                        <span>{{label}}</span>
                        <input type="file" '+ widgetConfig.multiple+ '>         <!-- TODO : FilePicker [(ngModel)]="binding" ?? -->
                    </div>
                    <div class="file-path-wrapper">
                       <input class="file-path validate" type="text" placeholder="Upload your file">
                    </div>
                </div>
              </div>
`,
    directives: [FORM_DIRECTIVES],
    providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR]
})

export class FileUploadInput implements OnInit,ControlValueAccessor{

    @Input()    label       :   string;

    @Input()    regex       :   string;

    @Input()    column       : string;

    @Input()     widgetConfig    :   Object;

    widgetStyleClassName     : string;

    elementId   :   string;

    constructor(){
    }


    ngOnInit(){
        this.widgetStyleClassName = BASIC_WIDGETSTYLE_CLASS+this.column;
        this.elementId = FILEUPLOAD_INPUT_PREFIX+Math.floor(Math.random()*90000) + 10000;
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

export class FileUploadInputConfiguration{
    multiple : string;
    hidden : boolean;
    disabled : boolean;
    constructor(hidden : boolean,disabled : boolean){
        this.multiple = 'multiple';
        this.hidden = hidden;
        this.disabled = disabled;
    }
}

