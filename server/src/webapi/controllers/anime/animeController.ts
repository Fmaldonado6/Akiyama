import { EpisodeScrapper } from './../../scrapper/episodeScrapper';
import { OvasResponse, SpecialsReponse, AnimeInfo, MoviesResponse, SearchResponse, ServersResponse } from './../../../core/domain/models';
import { BaseController } from "../baseController";
import axios from 'axios'
import { Request, Response } from "express";
import { AnimeResponse, EpisodesResponse } from "../../../core/domain/models";
import { animeDataSource } from '../../../network/animeDataSource';
import { AnimeScrapper } from '../../scrapper/animeScrapper';
import { episodeDataSource } from '../../../network/episodeDataSource';
class AnimeController extends BaseController {

    BASE_URL = "https://aruppi.jeluchu.xyz/apis/animeflv/v1"

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

            const response = await animeDataSource.getLatestAnimeResponse()

            const scrapper = new AnimeScrapper(response);
            const animes = scrapper.getLatestAnimesByResponse();

            res.status(200).send(animes)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

    async getLatestEpisodes(req: Request, res: Response) {
        try {
            const animes = await axios.get<EpisodesResponse>(`${this.BASE_URL}/LatestEpisodesAdded`)

            res.status(200).json(animes.data.episodes)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }


    async getLatestMovies(req: Request, res: Response) {
        try {
            const response = await animeDataSource.getLatestAnimeResponse("movie")

            const scrapper = new AnimeScrapper(response);
            const animes = scrapper.getLatestAnimesByResponse();

            res.status(200).send(animes)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

    async getLatestSpecials(req: Request, res: Response) {
        try {
            const response = await animeDataSource.getLatestAnimeResponse("special")

            const scrapper = new AnimeScrapper(response);
            const animes = scrapper.getLatestAnimesByResponse();

            res.status(200).send(animes)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

    async getLatestOvas(req: Request, res: Response) {
        try {
            const response = await animeDataSource.getLatestAnimeResponse("ova")

            const scrapper = new AnimeScrapper(response);
            const animes = scrapper.getLatestAnimesByResponse();

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
            console.log(animeId)
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

            const animes = await axios.get<SearchResponse>(`${this.BASE_URL}/Search/${search}`)

            res.status(200).json(animes.data.search)

        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }
}

export const animeController = new AnimeController()