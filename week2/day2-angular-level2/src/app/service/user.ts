import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface UserInterface {
  id?: number;
  name: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class User {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  getAll(): Observable<UserInterface[]> {
    return this.http.get<UserInterface[]>(this.apiUrl);
  }

  getById(id: number): Observable<UserInterface> {
    return this.http.get<UserInterface>(`${this.apiUrl}/${id}`);
  }

  create(user: UserInterface): Observable<UserInterface> {
    return this.http.post<UserInterface>(this.apiUrl, user);
  }

  update(id: number, user: UserInterface): Observable<UserInterface> {
    return this.http.put<UserInterface>(`${this.apiUrl}/${id}`, user);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
