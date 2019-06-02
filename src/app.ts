import express from 'express';
import cors from 'cors';
import { Mongoose } from 'mongoose';
// eslint-disable-next-line import/no-unresolved
import routes from './routes';

class App {
  public express: express.Application;

  public mongoose: Mongoose;

  public constructor() {
    this.express = express();
    this.mongoose = new Mongoose();
    this.middlewares();
    this.database();
    this.routes();
  }

  private middlewares(): void {
    this.express.use(express.json());
    this.express.use(cors());
  }

  private database(): void {
    this.mongoose.connect('mongodb://localhost:27017/instapic', { useNewUrlParser: true });
  }

  private routes(): void {
    this.express.use(routes);
  }
}

export default new App().express;
