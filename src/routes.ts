import { Router } from 'express';
// eslint-disable-next-line import/no-unresolved
import UserController from './controllers/UserController';

const routes = Router();

routes.get('/users', UserController.index);
routes.post('/users', UserController.store);

export default routes;
