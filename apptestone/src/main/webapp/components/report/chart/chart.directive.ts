import {Directive, ComponentFactoryResolver, ViewContainerRef, ComponentRef} from "@angular/core";
import {DataPointComponent} from "./datapoint.component";
import {ChartComponent} from "./chart.component";
@Directive({
    selector:'[chartDirective]'
})

export class ChartDirective{
    constructor(private _viewContainer :ViewContainerRef,private _componentFectoryResolver : ComponentFactoryResolver){

    }


    createChartView (chartComponent :{new() : ChartComponent}) :ComponentRef<ChartComponent>{

        this._viewContainer.clear();
        let compResolverFectory = this._componentFectoryResolver.resolveComponentFactory(chartComponent);

        let chartComponentRef = this._viewContainer.createComponent(compResolverFectory);

        chartComponentRef.instance.close.subscribe(() => {
            chartComponentRef.destroy();
        });

        return chartComponentRef;

    }
}