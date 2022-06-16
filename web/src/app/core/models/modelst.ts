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
    title: string = ""
    serverCode: string = ""
    allowMobile: boolean = false
    url: string = ""
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
