export interface AnimeResponse {
    tv: Anime[]
}

export interface EpisodesResponse {
    episodes: Episode[]
}

export interface SpecialsReponse {
    special: Anime[]
}

export interface OvasResponse {
    ova: Anime[]
}

export interface AnimeInfo {
    info: Anime
}


export interface MoviesResponse {
    movies: Anime[]
}

export interface SearchResponse {
    search: Anime[]
}

export interface ServersResponse {
    servers: Server[]
}

export class Anime {
    id: string = ""
    title: string = ""
    image: string = ""
    synopsis: string = ""
    status: string = ""
    type: string = ""
    genres: string[] = []
    rating: string = ""
    nextEpisodeDate?:string 
    episodes: Episode[] = []
}

export class Episode {
    id: string = ""
    title: string = ""
    image: string = ""
    episode: number = 0
    servers?: Server[]
}

export class Server {
    server: string = ""
    title: string = ""
    allowMobile: boolean = false
    code: string = ""
}
