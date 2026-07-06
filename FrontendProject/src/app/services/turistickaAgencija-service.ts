import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TuristickaAgencija } from '../models/turistickaAgencija';


@Injectable({
  providedIn: 'root',
})
export class TuristickaAgencijaService {
  constructor(private httpClient: HttpClient){ }

  public getAllTuristickaAgencijas(): Observable<TuristickaAgencija[]>{
    return this.httpClient.get<TuristickaAgencija[]>('http://localhost:8080/turistickaagencijas');
  }

  public createTuristickaAgencija(turistickaAgencija:TuristickaAgencija):Observable<TuristickaAgencija>{
    return this.httpClient.post<TuristickaAgencija>('http://localhost:8080/turistickaagencijas', turistickaAgencija)
  }

  public updateTuristickaAgencija(turistickaAgencija:TuristickaAgencija):Observable<TuristickaAgencija>{
    return this.httpClient.put<TuristickaAgencija>(`http://localhost:8080/turistickaagencijas?id=${turistickaAgencija.id}`, turistickaAgencija)
  }

  public deleteTuristickaAgencija(id:number):Observable<any>{
    return this.httpClient.delete(`http://localhost:8080/turistickaagencijas?id=${id}`)
  }

}
