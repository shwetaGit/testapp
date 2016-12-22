/**
 * Created by pratik on 16/5/16.
 */
import {Directive, Renderer, ElementRef, Self, forwardRef, Provider} from '@angular/core';
import {NG_VALUE_ACCESSOR, ControlValueAccessor} from '@angular/common';

const CHECKBOX_VALUE_ACCESSOR = new Provider(
    NG_VALUE_ACCESSOR, {useExisting: forwardRef(() => CheckboxControlValueAccessor), multi: true});

/**
 * Custom Directive for ngModel data binding on checkbox
 */
@Directive({
    selector:
        'a-checkbox[ngControl],a-checkbox[ngFormControl],a-checkbox[ngModel]',
    host: {
        '(change)': 'onChange($event.target.checked)',
        '(blur)': 'onTouched()'
    },
    providers: [CHECKBOX_VALUE_ACCESSOR]
})
export class CheckboxControlValueAccessor implements ControlValueAccessor {
    onChange = (_: any) => {};
    onTouched = () => {};

    constructor(private _renderer: Renderer, private _elementRef: ElementRef) {}

    writeValue(value: any): void {
        this._renderer.setElementProperty(this._elementRef.nativeElement, 'checked', value);
    }
    registerOnChange(fn: (_: any) => {}): void { this.onChange = fn; }
    registerOnTouched(fn: () => {}): void { this.onTouched = fn; }
}
