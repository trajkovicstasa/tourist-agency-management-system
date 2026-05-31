import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TuristickaAgencija } from '../models/turistickaAgencija';


@Injectable({
  providedIn: 'root',
})
export class TuristickaAgencijaService {
  constructor(private httpClient: HttpClient){ }

  public getAllTuristickaAgencijas(): Observable<any>{
    return this.httpClient.get('http://localhost:8080/turistickaagencijas');
  }

  public createTuristickaAgencija(turistickaAgencija:TuristickaAgencija):Observable<any>{
    return this.httpClient.post('http://localhost:8080/turistickaagencija', turistickaAgencija)
  }

  public updateTuristickaAgencija(turistickaAgencija:TuristickaAgencija):Observable<any>{
    return this.httpClient.put(`http://localhost:8080/turistickaagencija?id=${turistickaAgencija.id}`, turistickaAgencija)
  }

  public deleteTuristickaAgencija(id:number):Observable<any>{
    return this.httpClient.delete(`http://localhost:8080/turistickaagencija?id=${id}`)
  }

}
