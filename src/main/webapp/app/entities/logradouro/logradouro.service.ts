import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILogradouro } from 'app/shared/model/logradouro.model';

type EntityResponseType = HttpResponse<ILogradouro>;
type EntityArrayResponseType = HttpResponse<ILogradouro[]>;

@Injectable({ providedIn: 'root' })
export class LogradouroService {
    public resourceUrl = SERVER_API_URL + 'api/logradouros';

    constructor(private http: HttpClient) {}

    create(logradouro: ILogradouro): Observable<EntityResponseType> {
        return this.http.post<ILogradouro>(this.resourceUrl, logradouro, { observe: 'response' });
    }

    update(logradouro: ILogradouro): Observable<EntityResponseType> {
        return this.http.put<ILogradouro>(this.resourceUrl, logradouro, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ILogradouro>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILogradouro[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
