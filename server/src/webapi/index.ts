import express, { Application } from 'express';
import dotenv from 'dotenv'
import cors from 'cors';
import morgan from "morgan";
import { animeController } from './controllers/anime/animeController';

const PORT = 'port'

class Server {
    public app: Application

    constructor() {
        this.app = express()
        this.config()
        this.routes()
    }

    config() {
        dotenv.config();
        this.app.set(PORT, process.env.PORT);
        this.app.use(morgan('dev'));
        this.app.use(cors());
        this.app.use(express.json());
        this.app.use(express.urlencoded({ extended: false }));
    }

    routes() {
        this.app.use("/animes", animeController.router)
       
    }


    start() {
        const port = this.app.get(PORT)
        this.app.listen(port, () => {
            console.log("App listening on port " + port)
        })
    }

}

const server = new Server()
server.start()