/**
 * Created by pratik on 24/5/16.
 */
import{Injectable}   from '@angular/core';
import { Http } from '@angular/http';

/**
 * Service class that returns data for Dynamic Radio group to build
  */


@Injectable()

export class RadioService{
    // DI for getting HTTP class instance
    constructor(private _http : Http){}

    /**
     * Class that call the service url fetched the JSON to generate the Radio Group buttons
     * @param parentRef -> Component Class Reference
     * @param serviceUrl -> Service URL to call.
     */
    getRadioOptionData(parentRef : any ,serviceUrl : string){

        this._http.get(serviceUrl)
            .subscribe(
                res =>{
                    // On Successfully receiving data
                    parentRef.radioServiceObject = res.json();

                    parentRef.radioGroups = parentRef.radioServiceObject.fields;   // Set the field name
                    parentRef.groupName = parentRef.radioServiceObject.radio;       // Set the group name

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
