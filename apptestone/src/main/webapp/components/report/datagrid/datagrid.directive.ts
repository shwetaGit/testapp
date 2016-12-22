import {Directive, ComponentFactoryResolver, ViewContainerRef, ComponentRef} from "@angular/core";
import {DataGridComponent} from "./datagrid.component";
@Directive({
    selector:'[dataGridDirective]'
})

export class DataGridDirective{
    constructor(private _viewContainer :ViewContainerRef,private _componentFectoryResolver : ComponentFactoryResolver){

    }


    createDataGridView (dataGridComponent :{new() : DataGridComponent}) :ComponentRef<DataGridComponent>{

        this._viewContainer.clear();
        let compResolverFectory = this._componentFectoryResolver.resolveComponentFactory(dataGridComponent);

        let dataGridComponentRef = this._viewContainer.createComponent(compResolverFectory);

        dataGridComponentRef.instance.close.subscribe(() => {
            dataGridComponentRef.destroy();
        });

        return dataGridComponentRef;

    }
}