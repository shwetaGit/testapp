import {Directive, ComponentFactoryResolver, ViewContainerRef, ComponentRef} from "@angular/core";
import {DataPointComponent} from "./datapoint.component";
@Directive({
    selector:'[dataPointDirective]'
})

export class DataPointDirective{
    constructor(private _viewContainer :ViewContainerRef,private _componentFectoryResolver : ComponentFactoryResolver){

    }


    createDataPointView (dataPointComponent :{new() : DataPointComponent}) :ComponentRef<DataPointComponent>{

        this._viewContainer.clear();

        let compResolverFectory = this._componentFectoryResolver.resolveComponentFactory(dataPointComponent);

        let dataPointComponentRef = this._viewContainer.createComponent(compResolverFectory);

        dataPointComponentRef.instance.close.subscribe(() => {
            dataPointComponentRef.destroy();
        });

        return dataPointComponentRef;

    }
}