export class Server {
    server: string = ""
    title: string = ""
    allowMobile: boolean = false
    code: string = ""
}


export class Episode {
    id: string = ""
    title: string = ""
    poster: string = ""
    episode: number = 0
    servers: Server[] = null
    nextEpisodeDate: string = null
}


export class Anime {
    id: string = ""
    title: string = ""
    type: string = ""
    poster: string = ""
    synopsis: string = ""
    debut: string = ""
    rating: string = ""
    genres: string[] = []
    episodes: Episode[] = []
}


export class InfoItem {

    constructor(
        public type: Type = Type.Anime,
        public animeList: Anime[] = [],
        public episodeList: Episode[] = [],
        public currentStatus = Status.loading,
    ) { }
}


export enum Type {
    Anime,
    Episode
}

export enum Status {
    loading,
    loaded,
    error,
    empty
}
