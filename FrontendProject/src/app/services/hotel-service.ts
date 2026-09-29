import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Hotel } from '../models/hotel';



@Injectable({ // kaze Angularu da je klasa ispod servis kojim moze da upravlja Dependency Injection sistem
  // @ oznacava dekorator - Angularu daje dodatne informacije o klasi
  providedIn: 'root', // znaci ovaj servis je dostupan na nivou cele apl
})
export class HotelService {
  constructor(private httpClient: HttpClient){ }

  public getAllHotels(): Observable<Hotel[]>{
    return this.httpClient.get<Hotel[]>('http://localhost:8080/hotels');
  }

  public createHotel(hotel:Hotel):Observable<Hotel>{
    return this.httpClient.post<Hotel>('http://localhost:8080/hotels', hotel)
  }

  public updateHotel(hotel:Hotel):Observable<Hotel>{
    return this.httpClient.put<Hotel>(`http://localhost:8080/hotels?id=${hotel.id}`, hotel)
  }

  public deleteHotel(id:number):Observable<any>{
    return this.httpClient.delete(`http://localhost:8080/hotels?id=${id}`)
  }

}
