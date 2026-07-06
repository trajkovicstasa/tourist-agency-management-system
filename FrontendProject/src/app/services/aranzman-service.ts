import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Aranzman } from '../models/aranzman';



@Injectable({
  providedIn: 'root',
})
export class AranzmanService {
  constructor(private httpClient: HttpClient){ }

  public getAllAranzmans(): Observable<Aranzman[]>{
    return this.httpClient.get<Aranzman[]>('http://localhost:8080/aranzmans');
  }

  public createAranzman(aranzman:Aranzman):Observable<Aranzman>{
    return this.httpClient.post<Aranzman>('http://localhost:8080/aranzmans', aranzman)
  }

  public updateAranzman(aranzman:Aranzman):Observable<Aranzman>{
    return this.httpClient.put<Aranzman>(`http://localhost:8080/aranzmans?id=${aranzman.id}`, aranzman)
  }

  public deleteAranzman(id:number):Observable<any>{
    return this.httpClient.delete(`http://localhost:8080/aranzmans?id=${id}`)
  }

}
