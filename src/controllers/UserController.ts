import { Request, Response } from 'express';

// eslint-disable-next-line import/no-unresolved
import User from '../schemas/User';

class UserController {
  // eslint-disable-next-line class-methods-use-this
  public async index(req: Request, res: Response): Promise<Response> {
    const users = await User.find();

    return res.json(users);
  }

  // eslint-disable-next-line class-methods-use-this
  public async store(req: Request, res: Response): Promise<Response> {
    const user = await User.create(req.body);

    return res.json(user);
  }
}

export default new UserController();
