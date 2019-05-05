import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INome } from 'app/shared/model/nome.model';

type EntityResponseType = HttpResponse<INome>;
type EntityArrayResponseType = HttpResponse<INome[]>;

@Injectable({ providedIn: 'root' })
export class NomeService {
    public resourceUrl = SERVER_API_URL + 'api/nomes';

    constructor(private http: HttpClient) {}

    create(nome: INome): Observable<EntityResponseType> {
        return this.http.post<INome>(this.resourceUrl, nome, { observe: 'response' });
    }

    update(nome: INome): Observable<EntityResponseType> {
        return this.http.put<INome>(this.resourceUrl, nome, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<INome>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<INome[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
