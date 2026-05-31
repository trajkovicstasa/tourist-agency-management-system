import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Destinacija } from '../models/destinacija';


@Injectable({
  providedIn: 'root',
})
export class DestinacijaService {
  constructor(private httpClient: HttpClient){ }

  public getAllDestinacijas(): Observable<any>{
    return this.httpClient.get('http://localhost:8080/destinacijas');
  }

  public createDestinacija(destinacija:Destinacija):Observable<any>{
    return this.httpClient.post('http://localhost:8080/destinacija', destinacija)
  }

  public updateDestinacija(destinacija:Destinacija):Observable<any>{
    return this.httpClient.put(`http://localhost:8080/destinacija?id=${destinacija.id}`, destinacija)
  }

  public deleteDestinacija(id:number):Observable<any>{
    return this.httpClient.delete(`http://localhost:8080/destinacija?id=${id}`)
  }

}
