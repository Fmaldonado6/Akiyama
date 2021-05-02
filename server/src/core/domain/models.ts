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

export class Anime {
    id: string = ""
    title: string = ""
    poster: string = ""
    synopsis: string = ""
    debut: string = ""
    type: string = ""
    genres: string[] = []
    rating: string = ""
    episodes: Episode[] = []
}

export class Episode {
    id: string = ""
    title: string = ""
    poster: string = ""
    episode: number = 0
    servers: Server[] = []
}

export class Server {
    server: string = ""
    title: string = ""
    allowMobile: boolean = false
    url: string = ""
}