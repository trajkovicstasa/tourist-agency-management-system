import { Routes } from '@angular/router';
import { DestinacijaComponent } from './components/main/destinacija/destinacija';
import { TuristickaAgencijaComponent } from './components/main/turisticka-agencija/turisticka-agencija';
import { HotelComponent } from './components/main/hotel/hotel';
import { AranzmanComponent } from './components/main/aranzman/aranzman';
import { AuthorComponent } from './components/utility/author/author';
import { AboutComponent } from './components/utility/about/about';
import { HomeComponent } from './components/utility/home/home';


export const routes: Routes = [
    {path:'destinacija', component:DestinacijaComponent},
    {path:'turisticka-agencija', component:TuristickaAgencijaComponent},
    {path:'hotel', component:HotelComponent},
    {path:'aranzman', component:AranzmanComponent},
    {path:'author', component:AuthorComponent},
    {path:'about', component:AboutComponent},
    {path:'', component:HomeComponent, pathMatch:'full'},

];
