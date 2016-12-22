
import { Routes, RouterModule } from '@angular/router';
import {ModuleWithProviders} from "@angular/core";
import {MainPageComponent} from "./mainpage.component";


const mainPageRoutes: Routes = [
    {
        path: 'mainpage',
        component: MainPageComponent,
        children : [
            {
                path : ''
            },
            { path: 'first', loadChildren: './app/first/first.module#FirstModule' }
           
        ]
    }


];

export const mainPageRouting: ModuleWithProviders = RouterModule.forRoot(mainPageRoutes);







