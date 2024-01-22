import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { SessionService } from '../services/session.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  // ---------------------------------------------------------------------------
  // Constructor

  constructor(private sessionService: SessionService) {}

  // ---------------------------------------------------------------------------
  // Metodos

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const tokenHeader = req.headers.get('Authorization');
    if (tokenHeader) {
      return next.handle(req);
    }

    const tokenSession = this.sessionService.getToken();
    if (!tokenSession) {
      return next.handle(req);
    }

    const handledReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${tokenSession}`),
    });

    return next.handle(handledReq);
  }
}
