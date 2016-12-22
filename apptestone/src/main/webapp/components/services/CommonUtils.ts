/**
 * Created by pratik on 28/9/16.
 */
import {Injectable} from '@angular/core';

@Injectable()
export class CommonUtils {
    timeZoneCookieName : string;
    constructor() {
        this.timeZoneCookieName = 'XA_TID';
    }

    validateAndShowErrorMessages(componentConfigArray) : boolean {

        let invalidComponentsArray = [];
        componentConfigArray.forEach((componentConfig)=> {
            if(!document.getElementById(componentConfig.elementId).validity.valid){
                invalidComponentsArray.push(componentConfig);
            }

        });

        if(invalidComponentsArray.length > 0) {
            var combinedErrorMessage = this.getCombinedErrorMessage(invalidComponentsArray);
            toastr.error(combinedErrorMessage);
            return true;
        }

        return false;
    }
    
    getLocalDataJson(localDataJson : any) : any {
		
		let localDataObject = {};
		localDataObject['response'] = {};
		localDataObject.response['data'] = localDataJson;
		return localDataObject;
    }

    getCombinedErrorMessage(invalidComponentsArray)  : string {

        let combinedErrorMessage = 'Please validate following fields : <br><br>';

        invalidComponentsArray.forEach((invalidCmpConfig)=> {
            if(!invalidCmpConfig.hidden) {
                combinedErrorMessage += (invalidCmpConfig.errorMessage? (': '+invalidCmpConfig.errorMessage) : '') + '<br>';
            }
        });

        return combinedErrorMessage;
    }
    
    setDMYFormattedDate(dateString : string) : string {
        if(dateString!=null) {
            var dateParts = dateString.split("-");
            if(dateParts.length == 3) {
                var day = dateParts[0];
                var month = dateParts[1]-1;
                var year = dateParts[2];

                return new Date(year, month, day);
            } else {
                return undefined;
            }
        } else {
            return undefined;
        }
    }

    getDMYFormattedDate(dateObj : Date) : string{
        let formattedDate : string;
        if(dateObj != null){
            let dd = dateObj.getDate();
            let mm = dateObj.getMonth()+1;
            let yy = dateObj.getFullYear();
            formattedDate = dd+'-'+mm+'-'+yy;
        } else {
            console.log('getDMYFormattedDate() : No date Object found ' + dateObj);
        }
        return formattedDate;
    }

    getDateTimeZone(dateObj : Date) : string{
        let dateTimeZone : string;
        let dmy = this.getDMYFormattedDate(dateObj);

        if(dmy != null) {
            let timeZone : string = this.getCookie(this.timeZoneCookieName);
            if(timeZone != null) {
                let hours : number = dateObj.getHours();
                let mins : number = dateObj.getMinutes();
                let seconds : number = dateObj.getSeconds();

                dateTimeZone = dmy.concat(' '+hours+':'+mins+':'+seconds+' '+timeZone);
            }
        } else {
            console.log('getDateTimeZone() : No date Object found ' + dateObj);
        }
        return dateTimeZone;
    }


    /**
     * util Method to get cookie
     * @param name
     * @returns {any}
     */
    getCookie(name: string) {
        let ca: Array<string> = document.cookie.split(';');
        let caLen: number = ca.length;
        let cookieName = name + "=";
        let c: string;

        for (let i: number = 0; i < caLen; i += 1) {
            c = ca[i].replace(/^\s\+/g, "");
            c = c.trim();
            if (c.indexOf(cookieName) == 0) {
                return c.substring(cookieName.length, c.length);
            }
        }
        return "";
    }
}
