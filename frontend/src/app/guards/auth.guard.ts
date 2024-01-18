import { inject } from '@angular/core';
import { CanMatchFn, Router } from '@angular/router';

import { SessionService } from '../services/session.service';

export const AuthGuard: CanMatchFn = () => {
  const router: Router = inject(Router);
  const sessionService: SessionService = inject(SessionService);

  const hasToken = !!sessionService.getToken();
  if (!hasToken) {
    return router.navigate(['login']);
  }

  return true;
};
