import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Hotel } from '../models/hotel';



@Injectable({
  providedIn: 'root',
})
export class HotelService {
  constructor(private httpClient: HttpClient){ }

  public getAllHotels(): Observable<Hotel[]>{
    return this.httpClient.get<Hotel[]>('http://localhost:8080/hotels');
  }

  public createHotel(hotel:Hotel):Observable<any>{
    return this.httpClient.post('http://localhost:8080/hotel', hotel)
  }

  public updateHotel(hotel:Hotel):Observable<any>{
    return this.httpClient.put(`http://localhost:8080/hotel?id=${hotel.id}`, hotel)
  }

  public deleteHotel(id:number):Observable<any>{
    return this.httpClient.delete(`http://localhost:8080/hotel?id=${id}`)
  }

}
