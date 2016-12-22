/**
 * Created by prasad on 25/5/16.
 */
import{Injectable}   from '@angular/core';
import { Http } from '@angular/http';

/**
 * Service class that returns data for Dynamic checkbox group to build
 */


@Injectable()

export class CheckboxService{
    // DI for getting HTTP class instance
    constructor(private _http : Http){}

    /**
     * Class that call the service url fetched the JSON to generate the checkbox Group buttons
     * @param parentRef -> Component Class Reference
     * @param serviceUrl -> Service URL to call.
     */
    getCheckboxOptionData(parentRef : any ,serviceUrl1 : string){

        this._http.get(serviceUrl1)
            .subscribe(
                res =>{
                    // On Successfully receiving data
                    parentRef.checkboxServiceObject = res.json();

                    parentRef.checkboxGroups = parentRef.checkboxServiceObject.checkboxFields;   // Set the field name
                    parentRef.groupName = parentRef.checkboxServiceObject.checkbox1;       // Set the group name

                },
                err => {
                    //on Error?
                    console.error('Error Occured on Select Service Classname : '+this.constructor.name);
                },
                () => {
                    //on complete?
                    // console.log('Completed');
                }
            );
    }

}
