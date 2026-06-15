import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Aranzman } from '../models/aranzman';



@Injectable({
  providedIn: 'root',
})
export class AranzmanService {
  constructor(private httpClient: HttpClient){ }

  public getAllAranzmans(): Observable<any>{
    return this.httpClient.get('http://localhost:8080/aranzmans');
  }

  public getAranzmansByHotelId(hotelId: number): Observable<any> {
  return this.httpClient.get(`http://localhost:8080/aranzmans/hotel?id=${hotelId}`);
}

  public createAranzman(aranzman:Aranzman):Observable<any>{
    return this.httpClient.post('http://localhost:8080/aranzman', aranzman)
  }

  public updateAranzman(aranzman:Aranzman):Observable<any>{
    return this.httpClient.put(`http://localhost:8080/aranzman?id=${aranzman.id}`, aranzman)
  }

  public deleteAranzman(id:number):Observable<any>{
    return this.httpClient.delete(`http://localhost:8080/aranzman?id=${id}`)
  }

}
