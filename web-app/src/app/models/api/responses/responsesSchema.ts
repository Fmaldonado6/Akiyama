import { EpisodeModel } from '../apiContent/EpisodeModel';
import { AnimeModel } from './../apiContent/AnimeModel';
import { ServerModel } from './../apiContent/ServerModel';

export interface EpisodeResponse {
    episodes: EpisodeModel[]
}

export interface AnimeResponse {
    tv: AnimeModel[]
}
export interface ServerResponse {
    servers: ServerModel[]
}

export interface MovieResponse {
    movies: AnimeModel[]
}

export interface SearchResponse{
    search: AnimeModel[]
}

export interface SpecialsResponse {
    specials: AnimeModel[]
}

export interface OvaResponse {
    ovas: AnimeModel[]
}

export interface InfoResponse {
    info: AnimeModel
}

export enum DataType {
    episodes = 0,
    anime,
    movies,
    specials,
    ovas,
    search
}