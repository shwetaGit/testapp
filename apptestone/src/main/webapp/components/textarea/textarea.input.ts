import {Component, Provider, forwardRef, Provider, forwardRef, Input ,OnInit} from "@angular/core";
import {FORM_DIRECTIVES} from "@angular/forms";
import {ControlValueAccessor, NG_VALUE_ACCESSOR, FORM_DIRECTIVES} from "@angular/forms";
import {BASIC_WIDGETSTYLE_CLASS, TEXTAREA_INPUT_PREFIX} from "../style.constants";

const noop = () => {};

const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {
        useExisting: forwardRef(() => TextAreaInput),
        multi: true
    });

@Component({
    selector : 'textarea-input',
    template : `      
           <div [class]="widgetStyleClassName" [ngClass]="{'hiddendiv': widgetConfig.hidden , '':!widgetConfig.hidden}">
             <div class="md-form">
                <textarea type="text" [(ngModel)]="value" [attr.id]="elementId" class="md-textarea" autocomplete="off" (blur)="onTouched($event)" [required]="widgetConfig.required ? true: null" [readonly]="widgetConfig.readOnly ? true : false" [attr.disabled]="widgetConfig.disabled ? true : null" (change)="validateInput($event)"></textarea>
                <label [ngClass]="{'active': value , '': value}"  [attr.for]="elementId">{{label}}</label>
             </div>
           </div>   

`,
    directives: [FORM_DIRECTIVES],
    providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR]
})

export class TextAreaInput implements OnInit,ControlValueAccessor{

    @Input()    label       :   string;

    @Input()    column       : string;

    @Input()    widgetConfig    :   TextAreaInputConfiguration;

    widgetStyleClassName     : string;

    elementId   :   string;

    constructor(){}


    ngOnInit(){
        this.widgetStyleClassName = BASIC_WIDGETSTYLE_CLASS+this.column;
        this.elementId = TEXTAREA_INPUT_PREFIX+Math.floor(Math.random()*90000) + 10000;
        this.widgetConfig.elementId = this.elementId;
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

}

/**
 * Used to configure Widgets changable properties
 */
export class TextAreaInputConfiguration{
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
