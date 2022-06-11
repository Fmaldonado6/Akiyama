import { EpisodeScrapper } from './../../scrapper/episodeScrapper';
import { BaseController } from "../baseController";
import { Request, Response } from "express";
import { animeDataSource } from '../../../network/animeDataSource';
import { AnimeScrapper } from '../../scrapper/animeScrapper';
import { episodeDataSource } from '../../../network/episodeDataSource';
class AnimeController extends BaseController {

    constructor() {
        super()
        this.config()
    }

    config() {
        this.router.get("/latest", (req, res) => this.getLatestAnimes(req, res))
        this.router.get("/episodes", (req, res) => this.getLatestEpisodes(req, res))
        this.router.get("/episodes/:episodeId", (req, res) => this.getEpisodeServers(req, res))
        this.router.get("/specials", (req, res) => this.getLatestSpecials(req, res))
        this.router.get("/ovas", (req, res) => this.getLatestOvas(req, res))
        this.router.get("/movies", (req, res) => this.getLatestMovies(req, res))
        this.router.get("/search/:search", (req, res) => this.getSearch(req, res))
        this.router.get("/:animeId", (req, res) => this.getAnimeInfo(req, res))
    }

    async getLatestAnimes(req: Request, res: Response) {
        try {

            const response = await animeDataSource.getAnimeResponse()

            const scrapper = new AnimeScrapper(response);
            const animes = scrapper.getAnimesByResponse();

            res.status(200).send(animes)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

    async getLatestEpisodes(req: Request, res: Response) {
        try {

            const response = await episodeDataSource.getLatestEpisodes();
            const scrapper = new EpisodeScrapper(response)
            const latestEpisodes = scrapper.getLatestEpisodesFromResponse();
            res.status(200).send(latestEpisodes)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }


    async getLatestMovies(req: Request, res: Response) {
        try {
            const response = await animeDataSource.getAnimeResponse("movie")

            const scrapper = new AnimeScrapper(response);
            const animes = scrapper.getAnimesByResponse();

            res.status(200).send(animes)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

    async getLatestSpecials(req: Request, res: Response) {
        try {
            const response = await animeDataSource.getAnimeResponse("special")

            const scrapper = new AnimeScrapper(response);
            const animes = scrapper.getAnimesByResponse();

            res.status(200).send(animes)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

    async getLatestOvas(req: Request, res: Response) {
        try {
            const response = await animeDataSource.getAnimeResponse("ova")

            const scrapper = new AnimeScrapper(response);
            const animes = scrapper.getAnimesByResponse();

            res.status(200).send(animes)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

    async getEpisodeServers(req: Request, res: Response) {
        try {
            const episodeId = req.params.episodeId;
            const response = await episodeDataSource.getEpisodeServers(episodeId)
            const scrapper = new EpisodeScrapper(response)
            const servers = scrapper.getEpisodeServersFromResponse()
            res.status(200).json(servers)
        } catch (error) {

        }
    }

    async getAnimeInfo(req: Request, res: Response) {
        try {
            const animeId = req.params.animeId
            const response = await animeDataSource.getAnimeInfo(animeId);
            const scrapper = new AnimeScrapper(response);
            const animeInfo = scrapper.getAnimeInfo();
            animeInfo.id = animeId;
            res.status(200).json(animeInfo)


        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }


    async getSearch(req: Request, res: Response) {
        try {
            const search = req.params.search

            const response = await animeDataSource.getSearchResults(search)

            const scrapper = new AnimeScrapper(response);
            const animes = scrapper.getAnimesByResponse();
            res.status(200).json(animes)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }
}

export const animeController = new AnimeController()