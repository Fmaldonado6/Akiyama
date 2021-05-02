import { OvasResponse, SpecialsReponse, AnimeInfo, MoviesResponse, SearchResponse } from './../../../core/domain/models';
import { BaseController } from "../baseController";
import axios from 'axios'
import { Request, Response } from "express";
import { AnimeResponse, EpisodesResponse } from "../../../core/domain/models";
class AnimeController extends BaseController {

    BASE_URL = "https://aruppi.jeluchu.xyz/apis/animeflv/v1"

    constructor() {
        super()
        this.config()
    }

    config() {
        this.router.get("/latest", (req, res) => this.getLatestAnimes(req, res))
        this.router.get("/episodes", (req, res) => this.getLatestEpisodes(req, res))
        this.router.get("/specials", (req, res) => this.getLatestSpecials(req, res))
        this.router.get("/ovas", (req, res) => this.getLatestOvas(req, res))
        this.router.get("/movies", (req, res) => this.getLatestMovies(req, res))
        this.router.get("/animeInfo/:animeId/:animeTitle", (req, res) => this.getAnimeInfo(req, res))
    }

    async getLatestAnimes(req: Request, res: Response) {
        try {
            const animes = await axios.get<AnimeResponse>(`${this.BASE_URL}/TV/latest/1`)

            res.status(200).json(animes.data.tv)
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
            const animes = await axios.get<MoviesResponse>(`${this.BASE_URL}/Movies/latest/1`)


            res.status(200).json(animes.data.movies)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

    async getLatestSpecials(req: Request, res: Response) {
        try {
            const animes = await axios.get<SpecialsReponse>(`${this.BASE_URL}/Special/latest/1`)

            res.status(200).json(animes.data.special)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

    async getLatestOvas(req: Request, res: Response) {
        try {
            const animes = await axios.get<OvasResponse>(`${this.BASE_URL}/Ova/latest/1`)

            res.status(200).json(animes.data.ova)
        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

    async getAnimeInfo(req: Request, res: Response) {
        try {
            const animeId = req.params.animeId
            const animeTitle = req.params.animeTitle

            const animes = await axios.get<SearchResponse>(`${this.BASE_URL}/Search/${animeTitle}`)

            for (let anime of animes.data.search) {
                if (anime.id == `anime/${animeId}`)
                    return res.status(200).json(anime)
            }

            res.sendStatus(404)

        } catch (error) {
            console.error(error)
            res.sendStatus(500)
        }
    }

}

export const animeController = new AnimeController()