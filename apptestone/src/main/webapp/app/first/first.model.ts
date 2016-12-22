

export class First{
    _email : string;
    _password : string;

    constructor(){
        this.email = '';
        this.password = '';


    }
    get email():string {
        return this._email;
    }
    set email(email:string) {
        this._email = email;
    }
    get password():string {
        return this._password;
    }
    set password(password:string) {
        this._password = password;
    }
}