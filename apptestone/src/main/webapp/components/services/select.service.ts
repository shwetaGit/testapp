/**
 * Created by pratik on 16/5/16.
 */
import{Injectable}   from '@angular/core';
import { Http } from '@angular/http';
/**
 * Service Class that provides with the data for select element. Requires Instance of the Directive.
 */
@Injectable()
export class SelectService{

    // DI for getting HTTP class instance
    constructor(private _http : Http){}

    /**
     * Calls the service and assigns the returned JSON data to caller.data
     * @param parentRef : Refrence of the directive class calling the service
     * @param serviceUrl : URL of the service to be called
     */
    getSelectOptionData(parentRef : any, serviceUrl : string){
        this._http.get(serviceUrl)
            .subscribe(
                res =>{
                    // On Successfully receiving data
                    parentRef.data = res.json();
                    parentRef.filteredData = parentRef.data;
                },
                err => {
                    //on Error?
                    console.error('Error Occured on Select Service Classname : '+this.constructor.name);
                },
                () => {
                    //on complete?
                    console.log('Completed');
                }
            );
    }


   /* getFileteredOptionData(parentRef : any,serviceUrl : string,value_name : string,label_name : string){
        let data : any[];
        this._http.get(serviceUrl)
            .subscribe(
                res => {
                    // Assign Value and Label based on user
                    console.log(res);
                    data = [];

                }
            )
    }*/
}
