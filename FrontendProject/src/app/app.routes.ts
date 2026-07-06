import { Routes } from '@angular/router';
import { DestinacijaComponent } from './components/main/destinacija-component/destinacija-component';
import { TuristickaAgencijaComponent } from './components/main/turisticka-agencija-component/turisticka-agencija-component';
import { HotelComponent } from './components/main/hotel-component/hotel-component';
import { AranzmanComponent } from './components/main/aranzman-component/aranzman-component';
import { AuthorComponent } from './components/utility/author-component/author-component';
import { AboutComponent } from './components/utility/about-component/about-component';
import { HomeComponent } from './components/utility/home-component/home-component';
import { Component } from '@angular/core';


export const routes: Routes = [
    {path:'destinacija', component:DestinacijaComponent},
    {path:'turisticka-agencija', component:TuristickaAgencijaComponent},
    {path:'hotel', component:HotelComponent},
    {path:'aranzman', component:AranzmanComponent},
    {path:'author', component:AuthorComponent},
    {path:'about', component:AboutComponent},
    {path:'home', component: HomeComponent},
    {path:'', component: HomeComponent, pathMatch:'full'}];